package com.example.projectone.RestControllers;

import com.example.projectone.DTO.ClientDTO;
import com.example.projectone.Entity.Client;
import com.example.projectone.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/createClient")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createClient(client));
    }

    @GetMapping("/getAllClient")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/getClient/{id}")
    public ClientDTO getClientById(@PathVariable Long id) throws Exception {
        return clientService.getClientById(id);
    }

    @PutMapping("/updateClient")
    public ResponseEntity<Client> updateClient(@RequestParam Long id, @RequestBody Client clientDetails) {
        return ResponseEntity.ok(clientService.updateClient(id, clientDetails));
    }

    @DeleteMapping("/DeleteClient")
    public ResponseEntity<Void> deleteClient(@RequestParam Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}

