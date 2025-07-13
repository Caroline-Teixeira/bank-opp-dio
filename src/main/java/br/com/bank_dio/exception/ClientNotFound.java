package br.com.bank_dio.exception;

public class ClientNotFound extends BankException{

    public ClientNotFound(String message) {
        super("Cliente não encontrado: " + message);
  
    }
    
}
