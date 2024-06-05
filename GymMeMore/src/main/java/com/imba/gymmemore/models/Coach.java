package com.imba.gymmemore.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("Coach")
public class Coach extends Person{
    private LocalDate hireDate;
    private double rating;
    private int conductedClasses;
    @ManyToOne
    private Branch branch;
    @OneToMany(mappedBy = "coach")
    private List<GroupClass> groupClasses;
    @ManyToMany(mappedBy = "coaches")
    private List<Client> clients;
}
