package br.com.bank_dio.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public abstract class Account {

    @Getter
    @Setter
    private int number;

    @Getter
    @Setter
    private double cash;

    @Getter
    @Setter
    private Client client;

    @Getter
    @Setter
    private List<Transaction> transation;

    //métodos 
    public void depositValue (double value){}

    public void withdrawCash (double value){}

    public void transferCash (Account destination, double value){}
    
    public double showCash (){
        return cash;
    
    }

    public void showHistory (){}
}
