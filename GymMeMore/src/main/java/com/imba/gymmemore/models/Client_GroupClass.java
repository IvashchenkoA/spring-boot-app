package com.imba.gymmemore.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
public class Client_GroupClass {
    @PrimaryKeyJoinColumn
    private Client client;
    @PrimaryKeyJoinColumn
    private GroupClass groupClass;
    private Review review;
}
