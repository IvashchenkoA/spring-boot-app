package com.imba.gymmemore.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class GroupClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private LocalDateTime scheduledTime;
    private double rating;
    private double popularity;
    @ManyToOne
    private Coach coach;
    @ManyToMany(mappedBy = "groupClasses")
    private List<Client> clients;
    @ManyToOne
    private ClassType classType;
}
