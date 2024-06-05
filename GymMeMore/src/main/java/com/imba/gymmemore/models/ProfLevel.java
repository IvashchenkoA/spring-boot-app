package com.imba.gymmemore.models;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class ProfLevel {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "profLevel")
    private List<Client> clients;
}
