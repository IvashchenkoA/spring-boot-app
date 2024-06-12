package com.imba.gymmemore.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class MembershipType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "membershipType", fetch = FetchType.EAGER)
    private List<Membership> memberships;
}
