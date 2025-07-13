// br.com.bank_dio.view.Menu.java
package br.com.bank_dio.view;

import java.util.Scanner;

import br.com.bank_dio.exception.*;
import br.com.bank_dio.model.CheckingAccount;
import br.com.bank_dio.model.Client;
import br.com.bank_dio.repository.AccountRepository;
import br.com.bank_dio.repository.ClientRepository;
import br.com.bank_dio.util.ConsolePrinter;
import br.com.bank_dio.util.ValidationUtils;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private ClientRepository clientRepository = new ClientRepository();
    private AccountRepository accountRepository = new AccountRepository();
    private MenuInvestiment menuI = new MenuInvestiment();

    public void showMenu() {
        int option;

        do {
            System.out.println(" ");
            ConsolePrinter.printInfo("===Bem-vindo ao Sistema Bancário DIO===");
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Criar conta");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Ver saldo");
            System.out.println("7 - Ver extrato");
            System.out.println("8 - Fazer investimento");
            System.out.println("9 - Sair");
            ConsolePrinter.printWarning("Escolha um número de 1 a 9: ");
            
            
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ConsolePrinter.printError("Erro: Por favor, digite apenas números!");
                option = 0; // valor que não seja 9 para continuar o loop
                continue;
            }

            switch (option) {
                case 1 -> addClient();
                case 2 -> createAccount();
                case 3 -> deposit();
                case 4 -> withdraw();
                case 5 -> transfer();
                case 6 -> showBalance();
                case 7 -> showHistory();
                case 8 -> makeInvestment();
                case 9 -> ConsolePrinter.printWarning("Saindo do Sistema...");
                default -> ConsolePrinter.printError("Erro: Opção inválida. Por favor, escolha uma opção entre 1-9"); 
                
            }
        } while (option != 9);
    }

    

    private void addClient() {
        try {
            System.out.print("Informe seu Nome: ");
            String name = scanner.nextLine();
            ValidationUtils.validateName(name);
            
            System.out.print("Informe seu CPF (apenas números): ");
            String cpf = scanner.nextLine();
            String validatedCPF = ValidationUtils.validateAndFormatCPF(cpf);

            if (clientRepository.addClient(name.trim(), validatedCPF)) {
                ConsolePrinter.printSuccess("Cliente cadastrado com sucesso!");
            } else {
                throw new DuplicateClient(validatedCPF);
            }
        } catch (BankException e) {
            ConsolePrinter.printError("Erro: " + e.getMessage());
            
        }
    }

    private void createAccount() {
        try {
            System.out.print("Informe o CPF do cliente (apenas números): ");
            String cpf = scanner.nextLine();
            String validatedCPF = ValidationUtils.validateAndFormatCPF(cpf);

            Client client = clientRepository.findByCpf(validatedCPF);
            if (client == null) {
                throw new ClientNotFound(validatedCPF);
            }

            CheckingAccount account = new CheckingAccount();
            account.setClient(client);

            int numero = accountRepository.addAccount(account);
            ConsolePrinter.printSuccess("Conta corrente criada com sucesso! Número: " + numero);
        } catch (BankException e) {
            ConsolePrinter.printError("Erro: " + e.getMessage());
        }
    }

    private void deposit() {
        try {
            System.out.print("Número da conta: ");
            int accountNumber = ValidationUtils.validateAccountNumber(scanner.nextLine());

            System.out.print("Valor R$: ");
            double cashValue = ValidationUtils.validateAmount(scanner.nextLine());

            var account = accountRepository.findByNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFound(String.valueOf(accountNumber));
            }

            account.depositValue(cashValue);
            ConsolePrinter.printSuccess("Depósito realizado com sucesso!");
            
        } catch (BankException e) {
            ConsolePrinter.printError("Erro: " + e.getMessage());
        }
    }

    private void withdraw() {
        try {
            System.out.print("Número da conta: ");
            int accountNumber = ValidationUtils.validateAccountNumber(scanner.nextLine());

            System.out.print("Valor R$: ");
            double cashValue = ValidationUtils.validateAmount(scanner.nextLine());

            var account = accountRepository.findByNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFound(String.valueOf(accountNumber));
            }

            account.withdrawCash(cashValue);
            ConsolePrinter.printSuccess("Saque realizado com sucesso!");
        } catch (BankException e) {
            ConsolePrinter.printError("Erro: " + e.getMessage());
        } catch (Exception e) {
            ConsolePrinter.printError("Erro: Saldo insuficiente para realizar o saque.");
           
        }
    }

    private void transfer() {
        try {
            System.out.print("Conta origem: ");
            int originNum = ValidationUtils.validateAccountNumber(scanner.nextLine());

            System.out.print("Conta destino: ");
            int destinyNum = ValidationUtils.validateAccountNumber(scanner.nextLine());
            
            ValidationUtils.validateDifferentAccounts(originNum, destinyNum);

            System.out.print("Valor R$: ");
            double cashValue = ValidationUtils.validateAmount(scanner.nextLine());

            var userAccount = accountRepository.findByNumber(originNum);
            var destinyAccount = accountRepository.findByNumber(destinyNum);

            if (userAccount == null) {
                throw new AccountNotFound("Conta origem: " + originNum);
            }
            
            if (destinyAccount == null) {
                throw new AccountNotFound("Conta destino: " + destinyNum);
            }

            userAccount.transferCash(destinyAccount, cashValue);
            ConsolePrinter.printSuccess("Transferência (Pix) realizada com sucesso!");
    
        } catch (BankException e) {
            ConsolePrinter.printError("Erro: " + e.getMessage());
            
        } catch (Exception e) {
            ConsolePrinter.printError("Erro: Saldo insuficiente para realizar a transferência (Pix).");
        }
    }

    private void showBalance() {
        try {
            System.out.print("Número da conta: ");
            int accountNum = ValidationUtils.validateAccountNumber(scanner.nextLine());

            var account = accountRepository.findByNumber(accountNum);
            if (account == null) {
                throw new AccountNotFound(String.valueOf(accountNum));
            }

            account.showCash();
        } catch (BankException e) {
            ConsolePrinter.printError("Erro: " + e.getMessage());
        }
    }

    private void showHistory() {
        try {
            System.out.print("Número da conta: ");
            int accountNumber = ValidationUtils.validateAccountNumber(scanner.nextLine());

            var account = accountRepository.findByNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFound(String.valueOf(accountNumber));
            }

            account.showTransactionHistory();
        } catch (BankException e) {
            ConsolePrinter.printError("Erro: " + e.getMessage());
        }
    }

    private void makeInvestment() {
    try {
        System.out.print("Número da conta: ");
        int accountNumber = ValidationUtils.validateAccountNumber(scanner.nextLine());
        
        var account = accountRepository.findByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFound(String.valueOf(accountNumber));
        }
        
        // Passa a conta encontrada para o menu de investimentos
        menuI.showMenuInvestiments(account);
        
    } catch (BankException e) {
        ConsolePrinter.printError("Erro: " + e.getMessage());
    }
}


}