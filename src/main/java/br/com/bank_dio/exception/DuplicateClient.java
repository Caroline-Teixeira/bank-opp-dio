package br.com.bank_dio.exception;

public class DuplicateClient extends BankException {

    public DuplicateClient(String cpf) {
        super("Cliente já cadastrado" + cpf);
        
    }
    
}
