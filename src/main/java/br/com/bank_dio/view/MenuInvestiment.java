package br.com.bank_dio.view;

import java.util.Scanner;


import br.com.bank_dio.exception.BankException;
import br.com.bank_dio.model.Account;


import br.com.bank_dio.util.ConsolePrinter;
import br.com.bank_dio.util.ValidationUtils;

public class MenuInvestiment {

    private Scanner scanner = new Scanner(System.in);
    
    

    public void showMenuInvestiments(Account account){

        
        
        int option2;
        do {
            System.out.println(" ");
            ConsolePrinter.printInfo("Escolha um investimento: ");
            System.out.println("1 - CDB - Certificado de Depósito Bancário");
            System.out.println("2 - LCI - Letra de Crédito Imobiliário");
            System.out.println("3 - TESOURO DIRETO");
            System.out.println("4 - FUNDO IMOBILIARIO");
            System.out.println("5 - Mostrar Histórico de Investimentos");
            System.out.println("6 - Voltar");
          

            try {
                option2 = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ConsolePrinter.printError("Erro: Por favor, digite apenas números!");
                option2 = 0; // para continuar o loop
                continue;
            }

             switch (option2) {
                case 1 -> investimentCDB(account);
                case 2 -> investimentLCI(account);
                case 3 -> investimentTD(account);
                case 4 -> investmentFI(account);
                case 5 -> showInvestmentHistory(account);
                case 6 -> ConsolePrinter.printWarning("Voltando...");
                default -> ConsolePrinter.printError("Erro: Opção inválida. Por favor, escolha uma opção entre 1-6"); 
                
            }

        } while (option2 != 6);
        
    
    

    }

    // histórico
    private void showInvestmentHistory(Account account) {
       ConsolePrinter.printInfo("=== HISTÓRICO DE INVESTIMENTOS ===");
        account.showInvestimentHistory();
    }

    // CDB 
    private void investimentCDB(Account account) {
        try {
            System.out.print("Valor para investir em CDB R$: ");
            double cashValue = ValidationUtils.validateAmount(scanner.nextLine());
            
            account.investimentCDB(cashValue);
            ConsolePrinter.printSuccess("Investimento CDB realizado com sucesso!");
            
        } catch (BankException e) {
            ConsolePrinter.printError("Erro: " + e.getMessage());
        }
    }

    // LCI
    private void investimentLCI(Account account) {
        try {
            System.out.print("Valor para investir em LCI R$: ");
            double cashValue = ValidationUtils.validateAmount(scanner.nextLine());
            
            account.investimentLCI(cashValue);
            ConsolePrinter.printSuccess("Investimento LCI realizado com sucesso!");
            
        } catch (BankException e) {
            ConsolePrinter.printError("Erro: " + e.getMessage());
        }
    }

    // TD
    private void investimentTD(Account account) {
        try {
            System.out.print("Valor para investir em Tesouro Direto R$: ");
            double cashValue = ValidationUtils.validateAmount(scanner.nextLine());
            
            account.investimentTD(cashValue);
            ConsolePrinter.printSuccess("Investimento 'Tesouro Direto' realizado com sucesso!");
            
        } catch (BankException e) {
            ConsolePrinter.printError("Erro: " + e.getMessage());
        }
    }
        // Fundo imobiliário
    private void investmentFI(Account account){
        try {
            System.out.print("Valor para investir em Fundo Imobiliário R$: ");
            double cashValue = ValidationUtils.validateAmount(scanner.nextLine());
            
            account.investimentFI(cashValue);
            ConsolePrinter.printSuccess("Investimento 'Fundo Imobiliário' realizado com sucesso!");
            
        } catch (BankException e) {
            ConsolePrinter.printError("Erro: " + e.getMessage());
        }
    }


}



