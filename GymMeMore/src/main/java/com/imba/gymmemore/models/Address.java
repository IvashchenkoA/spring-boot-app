package com.imba.gymmemore.models;

import jakarta.persistence.*;

@Entity
public class Address {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String city;
    private String street;
    private String houseNumber;
    @OneToOne(mappedBy = "address")
    private Branch branch;
}
