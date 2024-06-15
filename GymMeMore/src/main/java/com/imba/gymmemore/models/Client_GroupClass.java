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

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public GroupClass getGroupClass() {
        return groupClass;
    }

    public void setGroupClass(GroupClass groupClass) {
        this.groupClass = groupClass;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}
