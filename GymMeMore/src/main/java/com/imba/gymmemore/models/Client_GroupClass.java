package com.imba.gymmemore.models;

import jakarta.persistence.*;

@Entity
public class Client_GroupClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private GroupClass groupClass;
    @OneToOne
    private Review review;

    public Client_GroupClass() {
    }

    public Client_GroupClass(Client client, GroupClass groupClass) {
        this.client = client;
        this.groupClass = groupClass;
    }
}
