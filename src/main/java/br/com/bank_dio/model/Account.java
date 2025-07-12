package br.com.bank_dio.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public void depositValue (double value){
       if (value > 0){
            this.cashBalance += value;

            //Nova transação (invoca o método)
            registerTransaction(TransactionType.DEPOSIT, value);

            System.out.println(String.format("Valor to depósito: R$ %.2f", value));
            System.out.println(String.format("Valor do balanço atual: R$", cashBalance));
       } 
       else {
            System.out.println("Impossível fazer depósito");

       }

    }

    public void withdrawCash (double value){
        if (value > 0 && value <= cashBalance) {
            this.cashBalance -= value;

            // Nova transação 
            registerTransaction(TransactionType.WITHDRAW, value);

            System.out.println(String.format("Valor do saque: R$ %.2f", value));
            System.out.println(String.format("Valor do balanço atual: R$", cashBalance));
       } 
       else {
            System.out.println("Impossível sacar dinheiro");

       }
        

    }

    public void transferCash (Account destinationAccount, double value){
        if (value > 0 && value <= cashBalance) {
            this.cashBalance -= value;
            destinationAccount.cashBalance += value; // deposito na outra conta

        this.registerTransaction(TransactionType.TRANSFER, value);
        destinationAccount.registerTransaction(TransactionType.TRANSFER_RECEIVED, value); 
        
        
        System.out.println(String.format("Valor de transferência: R$ %.2f", value));
        System.out.println(String.format("Valor do balanço atual: R$ %.2f", cashBalance));
       } 
       else {
            System.out.println("Impossível transferir dinheiro");

       }
    
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
