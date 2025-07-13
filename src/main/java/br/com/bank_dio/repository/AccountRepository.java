package br.com.bank_dio.repository;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


import br.com.bank_dio.model.Account;

public class AccountRepository {

    
    private List<Account> accounts;
    private static AtomicInteger accountNumberGenerator = new AtomicInteger(1000); // numeros automaticos

    public AccountRepository() {
        this.accounts = new ArrayList<>();
    }

    public int addAccount(Account account) {
        int number = accountNumberGenerator.incrementAndGet();
        account.setNumber(number);
        accounts.add(account);
        return number;
    }


    public Account findByNumber(int number) {
       for (Account acc : accounts) {
            if (acc.getNumber() == number) {
                return acc;
            }
        }
        return null;
    }

   
    public boolean removeByNumber(int number) {
        return accounts.removeIf(acc -> acc.getNumber() == number);
    }

    public List<Account> findAccountsByClientCpf(String cpf) {
        List<Account> result = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc.getClient().getCpf().equals(cpf)) {
                result.add(acc);
            }
        }
        return result;
    }

    public void listAccounts() {
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

}
