package com.example.msuser.web;

import com.example.msuser.dao.UserRepository;
import com.example.msuser.entities.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer/v1")
public class CustomerRestRessources {

    private UserRepository clientRepository;

    public CustomerRestRessources(UserRepository produitRepository) {
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
