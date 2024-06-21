package com.example.projectone.Controllers;

import com.example.projectone.Entity.Client;
import com.example.projectone.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/createClient")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createClient(client));
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

