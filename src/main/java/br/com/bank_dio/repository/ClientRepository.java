package br.com.bank_dio.repository;

import java.util.HashMap;

import br.com.bank_dio.model.Client;

public class ClientRepository {

    private HashMap<Long, Client> clients;

    public ClientRepository() {
        this.clients = new HashMap<>();
    }

    // métodos CRUD
    public void addClient (long id, String name, String cpf) {
        clients.put(id, new Client(name, cpf));

    }

    public void findById (long id){
        clients.get(id);

    }

    public void findByCPF (String cpf) {
        clients.values().stream()
            .filter(client -> client.getCpf().equals(cpf))
            .findFirst()
            .orElse(null);

    }

    public void removeByCPF (String cpf){
        clients.values().stream()
            .filter(client -> client.getCpf().equals(cpf))
            .findFirst()
            .orElse(null);

    }

    
/*public void addProduct (long code, String name, int quantity, double price){
        productsInStorage.put(code, new Product(name, price, quantity));
    } */

    
}
