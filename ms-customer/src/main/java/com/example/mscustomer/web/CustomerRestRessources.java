package com.example.mscustomer.web;

import com.example.mscustomer.dao.ClientRepository;
import com.example.mscustomer.entities.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer/v1")
public class CustomerRestRessources {

    private ClientRepository clientRepository;

    public CustomerRestRessources(ClientRepository produitRepository) {
        this.clientRepository = produitRepository;
    }

    @GetMapping("/clients")
    public List<Client> listeClients() {
        return clientRepository.findAll();
    }
    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable(name="id") Long id) {
        return clientRepository.findById(id).get();
    }
}
