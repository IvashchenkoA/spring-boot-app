package com.imba.gymmemore.models;

import jakarta.persistence.*;

@Entity
public class BankAccount {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String number;
    private double amount;
    @OneToOne
    private Client client;
}
