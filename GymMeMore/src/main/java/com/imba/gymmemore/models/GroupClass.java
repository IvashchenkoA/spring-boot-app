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
    @ManyToOne
    private ClassType classType;
    @OneToMany(mappedBy = "groupClass", fetch = FetchType.EAGER)
    private List<Client_GroupClass> client_groupClasses;
}
