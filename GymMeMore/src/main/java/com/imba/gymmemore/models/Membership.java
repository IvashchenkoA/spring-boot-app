package com.imba.gymmemore.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @DateTimeFormat
    private LocalDate expiryDate;
    @OneToOne(mappedBy="membership")
    private Client client;
    @ManyToOne
    private MembershipType membershipType;

    public Membership() {
    }

    public Membership(LocalDate expiryDate, MembershipType membershipType) {
        this.expiryDate = expiryDate;
        this.membershipType = membershipType;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }
}
