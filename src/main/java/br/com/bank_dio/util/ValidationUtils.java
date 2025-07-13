package br.com.bank_dio.util;

import br.com.bank_dio.exception.InvalidInput;

public class ValidationUtils {

    private static final double MAX_TRANSACTION_VALUE = 999999.99;
    
    public static void validateName(String name) throws InvalidInput {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInput("Nome não pode estar vazio!");
        }
        
        if (name.trim().length() < 2) {
            throw new InvalidInput("Nome deve ter pelo menos 2 caracteres!");
        }
    }
    
    public static String validateAndFormatCPF(String cpf) throws InvalidInput {
        if (cpf == null) {
            throw new InvalidInput("CPF não pode ser nulo!");
        }
        
        String cleanCPF = cpf.trim().replaceAll("[^0-9]", "");
        
        if (!cleanCPF.matches("\\d{11}")) {
            throw new InvalidInput("CPF deve conter exatamente 11 dígitos!");
        }
        
        return cleanCPF;
    }
    
    public static int validateAccountNumber(String input) throws InvalidInput {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInput("Número da conta não pode estar vazio!");
        }
        
        try {
            int accountNumber = Integer.parseInt(input.trim());
            if (accountNumber <= 0) {
                throw new InvalidInput("Número da conta deve ser positivo!");
            }
            return accountNumber;
        } catch (NumberFormatException e) {
            throw new InvalidInput("Número da conta deve conter apenas dígitos!");
        }
    }
    
    public static double validateAmount(String input) throws InvalidInput {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInput("Valor não pode estar vazio!");
        }
        
        try {
            double amount = Double.parseDouble(input.trim().replace(",", "."));
            
            if (amount <= 0) {
                throw new InvalidInput("Valor deve ser positivo!");
            }
            
            if (amount > MAX_TRANSACTION_VALUE) {
                throw new InvalidInput("Valor muito alto! Máximo permitido: R$ " + 
                    String.format("%.2f", MAX_TRANSACTION_VALUE));
            }
            
            return amount;
        } catch (NumberFormatException e) {
            throw new InvalidInput("Valor deve ser um número válido (use ponto ou vírgula para decimais)!");
        }
    }
    
    public static void validateDifferentAccounts(int originAccount, int destinyAccount) throws InvalidInput {
        if (originAccount == destinyAccount) {
            throw new InvalidInput("Conta origem e destino não podem ser iguais!");
        }
    }
    
}
