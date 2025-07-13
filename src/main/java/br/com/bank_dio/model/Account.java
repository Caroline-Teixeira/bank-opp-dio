package br.com.bank_dio.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.bank_dio.exception.InvalidTransaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Account {

    
    private int number;
    private double cashBalance;
    private Client client;
    private List<Transaction> transaction = new ArrayList<>();
    private List<Investiment> investiment = new ArrayList<>();

    //MÉTODOS


    //Para registrar a transação
    public void registerTransaction(TransactionType type, double value){
        Transaction transaction = new Transaction(type, value, LocalDateTime.now());
        this.transaction.add(transaction); // histórico
    }

    // Para registar investimento
    public void registerInvestiment(InvestimentType type, double investedValue, double bankReturn) {
        Investiment investiment = new Investiment(type, investedValue, bankReturn);
        this.investiment.add(investiment);

    }

    public void depositValue (double value) throws InvalidTransaction{
       if (value > 0){
            this.cashBalance += value;

            //Nova transação (invoca o método)
            registerTransaction(TransactionType.DEPOSIT, value);

            System.out.println(String.format("Valor to depósito: R$ %.2f", value));
            System.out.println(String.format("Valor do balanço atual: R$ %.2f", cashBalance));
       } 
       else {
            throw new InvalidTransaction("Valor deve ser positivo");

       }

    }

    public void withdrawCash (double value) throws InvalidTransaction{
        if (value > 0 && value <= cashBalance) {
            this.cashBalance -= value;

            // Nova transação 
            registerTransaction(TransactionType.WITHDRAW, value);

            System.out.println(String.format("Valor do saque: R$ %.2f", value));
            System.out.println(String.format("Valor do balanço atual: R$ %.2f", cashBalance));
       } 
       else if (value <= 0) {
            throw new InvalidTransaction("Valor do saque deve ser positivo");

       }
       else {
            throw new InvalidTransaction("Saldo insuficiente para realizar o saque");
       }
        

    }

    public void transferCash (Account destinationAccount, double value) throws InvalidTransaction{
        
        if (value <= 0) {
            throw new InvalidTransaction("Valor da transferência deve ser positivo");
        }
        
        if (value > cashBalance) {
            throw new InvalidTransaction("Saldo insuficiente para realizar a transferência");
        }
        
        if (destinationAccount == null) {
            throw new InvalidTransaction("Conta de destino inválida");
        }
        this.cashBalance -= value;
        destinationAccount.cashBalance += value; // deposito na outra conta

        this.registerTransaction(TransactionType.TRANSFER, value);
        destinationAccount.registerTransaction(TransactionType.TRANSFER_RECEIVED, value); 
        
        
        System.out.println(String.format("Valor de transferência: R$ %.2f", value));
        System.out.println(String.format("Valor do balanço atual: R$ %.2f", cashBalance));
        
       
    
    }
    
    public double showCash (){
        System.out.println(String.format("Valor do balanço atual: R$ %.2f", cashBalance));
        return cashBalance;
    
    }

    public void showTransactionHistory (){
        if (transaction.isEmpty()){
            System.out.println("Nenhuma transação foi realizada");
        }
        else {
            System.out.println("Histórico de transações");
            for (Transaction listT : transaction){
                System.out.println(listT);
            }
        }
    }

    //  métodos de investimento 

    // CDB - Certificado de Depósito Bancário (rendimento: 8% ao ano)
    public void investimentCDB(double value) throws InvalidTransaction {
    if (value <= 0) {
        throw new InvalidTransaction("Valor do investimento deve ser positivo");
    }
    
    if (value > cashBalance) {
        throw new InvalidTransaction("Saldo insuficiente para realizar o investimento");
    }
    
    
    this.cashBalance -= value;
    
    // Calcula o rendimento (8% ao ano, simulando rendimento diário)
    double bankReturn = value * 0.08 / 365; // Rendimento diário aproximado
    
    // Registra o investimento
    registerInvestiment(InvestimentType.CDB, value, bankReturn);
    
    // Registra como transação
    registerTransaction(TransactionType.INVESTMENT, value);
    
    System.out.println(String.format("Investimento CDB realizado: R$ %.2f", value));
    System.out.println(String.format("Rendimento estimado (diário): R$ %.2f", bankReturn));
    System.out.println(String.format("Saldo atual: R$ %.2f", cashBalance));
}

    // LCI - Letra de Crédito Imobiliário (rendimento: 6% ao ano)
    public void investimentLCI(double value) throws InvalidTransaction {
    if (value <= 0) {
        throw new InvalidTransaction("Valor do investimento deve ser positivo");
    }
    
    if (value > cashBalance) {
        throw new InvalidTransaction("Saldo insuficiente para realizar o investimento");
    }
    
    
    this.cashBalance -= value;
    
    // Calcula o rendimento (6% ao ano, simulando rendimento diário)
    double bankReturn = value * 0.06 / 365; // Rendimento diário aproximado
    
    // Registra o investimento
    registerInvestiment(InvestimentType.LCI, value, bankReturn);
    
    // Registra como transação
    registerTransaction(TransactionType.INVESTMENT, value);
    
    System.out.println(String.format("Investimento LCI realizado: R$ %.2f", value));
    System.out.println(String.format("Rendimento estimado (diário): R$ %.2f", bankReturn));
    System.out.println(String.format("Saldo atual: R$ %.2f", cashBalance));
}

    // Tesouro Direto (rendimento: 10% ao ano)
    public void investimentTD(double value) throws InvalidTransaction {
    if (value <= 0) {
        throw new InvalidTransaction("Valor do investimento deve ser positivo");
    }
    
    if (value > cashBalance) {
        throw new InvalidTransaction("Saldo insuficiente para realizar o investimento");
    }
    
    
    this.cashBalance -= value;
    
    // Calcula o rendimento (10% ao ano, simulando rendimento diário)
    double bankReturn = value * 0.10 / 365; // Rendimento diário aproximado
    
    // Registra o investimento
    registerInvestiment(InvestimentType.TESOURO_DIRETO, value, bankReturn);
    
    // Registra como transação
    registerTransaction(TransactionType.INVESTMENT, value);
    
    System.out.println(String.format("Investimento Tesouro Direto realizado: R$ %.2f", value));
    System.out.println(String.format("Rendimento estimado (diário): R$ %.2f", bankReturn));
    System.out.println(String.format("Saldo atual: R$ %.2f", cashBalance));
}

    // Fundo Imobiliário (rendimento: 12% ao ano)
    public void investimentFI(double value) throws InvalidTransaction {
        if (value <= 0) {
            throw new InvalidTransaction("Valor do investimento deve ser positivo");
    }
    
    if (value > cashBalance) {
        throw new InvalidTransaction("Saldo insuficiente para realizar o investimento");
    }
    
    // Deduz do saldo da conta
    this.cashBalance -= value;
    
    // Calcula o rendimento (12% ao ano, simulando rendimento diário)
    double bankReturn = value * 0.12 / 365; // Rendimento diário aproximado
    
    // Registra o investimento
    registerInvestiment(InvestimentType.FUNDO_IMOBILIARIO, value, bankReturn);
    
    // Registra como transação
    registerTransaction(TransactionType.INVESTMENT, value);
    
    System.out.println(String.format("Investimento Fundo Imobiliário realizado: R$ %.2f", value));
    System.out.println(String.format("Rendimento estimado (diário): R$ %.2f", bankReturn));
    System.out.println(String.format("Saldo atual: R$ %.2f", cashBalance));
}


    // histórico 
    public void showInvestimentHistory (){
        if (investiment.isEmpty()){
            System.out.println("Nenhum investimento foi realizado");

        }
        else{
            System.out.println("Histórico de investimentos");
            for (Investiment listI : investiment){
                System.out.println(listI);
            }
        }

    }
}
