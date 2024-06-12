package com.imba.gymmemore.models;

import jakarta.persistence.*;

import java.util.List;
@Entity
public class Branch {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String description;
    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    private List<Coach> coaches;
    @OneToOne(mappedBy = "branch", fetch = FetchType.EAGER)
    private Address address;

    public Branch() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
