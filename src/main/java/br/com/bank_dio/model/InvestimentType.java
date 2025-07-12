package br.com.bank_dio.model;

public enum InvestimentType {

    CDB("Certificado de Depósito Bancário"),
    LCI("Letra de Crédito Imobiliário"),
    TESOURO_DIRETO("Tesouro Direto"),
    FUNDO_IMOBILIARIO("Fundo Imobiliário");

    private final String descriptionType;

    private InvestimentType(String descriptionType) {
        this.descriptionType = descriptionType;
    }

    public String getDescriptionType() {
        return descriptionType;
    }
}
