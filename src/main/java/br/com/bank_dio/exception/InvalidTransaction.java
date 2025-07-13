package br.com.bank_dio.exception;

public class InvalidTransaction extends BankException {

    public InvalidTransaction(String message) {
        super("Transação inválida: " + message);
    
}
}