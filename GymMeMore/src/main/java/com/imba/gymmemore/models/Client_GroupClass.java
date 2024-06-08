package com.imba.gymmemore.models;

import jakarta.persistence.*;

@Entity
public class Client_GroupClass {
    @Id
    @ManyToOne
    private Client client;
    @Id
    @ManyToOne
    private GroupClass groupClass;
    @OneToOne
    private Review review;
}
