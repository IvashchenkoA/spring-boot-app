package com.imba.gymmemore.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private int price;
    private LocalDate expiryDate;
    @OneToOne
    private Client client;
    @ManyToOne
    private MembershipType membershipType;
}
