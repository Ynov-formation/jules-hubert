package com.example.mscustomer;

import com.example.mscustomer.dao.ClientRepository;
import com.example.mscustomer.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class MsCustomerApplication implements CommandLineRunner {
    @Autowired
    private ClientRepository clientRepository;

    public static void main(String[] args) {
        SpringApplication.run(MsCustomerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//                clientRepository.saveAll(List.of(
//                Client.builder()
//                        .nom("John")
//                        .prenom("DOE")
//                        .dateNaissance(LocalDate.of(1990, 1, 1))
//                        .email("johndoe@gmail.com")
//                        .build()));
    }

}
