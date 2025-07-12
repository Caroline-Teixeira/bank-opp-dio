package br.com.bank_dio.model;

public enum TransactionType {

    DEPOSIT("Depósito"),
    WITHDRAW("Saque"),
    TRANSFER("Transferência enviada"),
    TRANSFER_RECEIVED("Transferência recebida"),
    INVESTMENT("Investimento");

    // enum com descrição
    private final String descriptionType;
    
    //construtor
    private TransactionType(String descriptionType) {
        this.descriptionType = descriptionType;
    }

    //getter
    public String getDescriptionType() {
        return descriptionType;
    }

    

}

    
