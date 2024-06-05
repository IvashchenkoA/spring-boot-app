package com.imba.gymmemore.models;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Branch {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;
    @OneToMany(mappedBy = "branch")
    private List<Coach> coaches;
    @OneToOne(mappedBy = "branch")
    private Address address;
}
