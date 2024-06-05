package com.imba.gymmemore.models;

import jakarta.persistence.*;

import java.util.List;
@Entity
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String firstName;
    private String email;
    private String username;
    private String password;
    private List<Coach> coaches;
    private List<Client> clients;
}
