package com.imba.gymmemore.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClassType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int capacity;
    @OneToMany(mappedBy = "classType")
    private List<GroupClass> groupClasses;
}
