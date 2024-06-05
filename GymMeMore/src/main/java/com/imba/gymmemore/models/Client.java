package com.imba.gymmemore.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("Client")
public class Client extends Person{
    private LocalDate joinDate;
    private int classesCount;
    @ManyToOne
    private ProfLevel profLevel;
    @OneToOne
    private BankAccount bankAccount;
    @ManyToMany(mappedBy = "clients")
    private List<GroupClass> groupClasses;
    @OneToOne
    private Membership membership;
    @ManyToMany(mappedBy = "clients")
    private List<Coach> coaches;
}
