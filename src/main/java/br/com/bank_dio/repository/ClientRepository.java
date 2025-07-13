package br.com.bank_dio.repository;

import java.util.ArrayList;

import java.util.List;


import br.com.bank_dio.model.Client;

public class ClientRepository {

    private List<Client> clients;


    public ClientRepository() {
        this.clients = new ArrayList<>();
    }

    // métodos CRUD
    public boolean addClient (String name, String cpf) {
            for (Client c : clients) {
            if (c.getCpf().equals(cpf)) {
                return false; // já existe
            }
    }
        clients.add(new Client(name, cpf));
        return true;

    }


   
    public Client findByCpf(String cpf) {
        for (Client c : clients) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
    }
    return null;
}

    public boolean removeByCPF(String cpf) {
        return clients.removeIf(c -> c.getCpf().equals(cpf));
    
}

// Lista clientes - debugg
    public void listClients() {
        for (Client c : clients) {
        System.out.println(c);
        
    }

}
    
}
