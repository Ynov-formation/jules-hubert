package com.example.mscustomer.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class Commande {
    private Long id;
    private String designation;
    private String description;
    private LocalDateTime date;
}
