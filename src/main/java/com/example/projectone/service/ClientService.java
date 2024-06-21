package com.example.projectone.service;

import com.example.projectone.Entity.Client;
import com.example.projectone.Repository.ClientRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @SneakyThrows
    public Client updateClient(Long id, Client clientDetails) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();
            existingClient.setName(clientDetails.getName());
            existingClient.setSurName(clientDetails.getSurName());
            existingClient.setPhoneNumber(clientDetails.getPhoneNumber());
            existingClient.setOrders(clientDetails.getOrders());
            return clientRepository.save(existingClient);
        } else {
            throw new Exception("Client with id " + id + " not found");
        }
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}

