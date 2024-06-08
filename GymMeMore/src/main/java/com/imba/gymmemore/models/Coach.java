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
    @OneToMany(mappedBy = "coach", fetch = FetchType.EAGER)
    private List<GroupClass> groupClasses;
    @OneToMany(mappedBy = "coach", fetch = FetchType.EAGER)
    private List<IndividualSession> individualSessions;
}
