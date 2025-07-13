// br.com.bank_dio.view.Menu.java
package br.com.bank_dio.view;

import java.util.Scanner;

import br.com.bank_dio.exception.*;
import br.com.bank_dio.model.CheckingAccount;
import br.com.bank_dio.model.Client;
import br.com.bank_dio.repository.AccountRepository;
import br.com.bank_dio.repository.ClientRepository;
import br.com.bank_dio.util.ValidationUtils;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private ClientRepository clientRepository = new ClientRepository();
    private AccountRepository accountRepository = new AccountRepository();

    public void showMenu() {
        int option;

        do {
            System.out.println(" ");
            System.out.println("===Bem-vindo ao Sistema Bancário DIO===");
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Criar conta");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Ver saldo");
            System.out.println("7 - Ver extrato");
            System.out.println("9 - Sair");
            System.out.print("Escolha um número de 1 a 9: ");
            System.out.println(" ");
            
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite apenas números!");
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
                case 9 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Erro: Opção inválida. Por favor, escolha uma opção entre 1-7 ou 9.");
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
                System.out.println("Cliente cadastrado com sucesso!");
            } else {
                throw new DuplicateClient(validatedCPF);
            }
        } catch (BankException e) {
            System.out.println("Erro: " + e.getMessage());
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
            System.out.println("Conta corrente criada com sucesso! Número: " + numero);
        } catch (BankException e) {
            System.out.println("Erro: " + e.getMessage());
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
            System.out.println("Depósito realizado com sucesso!");
        } catch (BankException e) {
            System.out.println("Erro: " + e.getMessage());
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
            System.out.println("✓ Saque realizado com sucesso!");
        } catch (BankException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Saldo insuficiente para realizar o saque.");
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
            System.out.println("✓ Transferência realizada com sucesso!");
        } catch (BankException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: Saldo insuficiente para realizar a transferência.");
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
            System.out.println("Erro: " + e.getMessage());
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
            System.out.println("Erro: " + e.getMessage());
        }
    }
}