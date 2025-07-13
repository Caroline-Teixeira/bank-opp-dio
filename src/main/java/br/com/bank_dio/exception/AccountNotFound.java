package br.com.bank_dio.exception;

public class AccountNotFound extends BankException{

    public AccountNotFound(String message) {
        super("Conta não encontrada: " + message);
    }
    
}
