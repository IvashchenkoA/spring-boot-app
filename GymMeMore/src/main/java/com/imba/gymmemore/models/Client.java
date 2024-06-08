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
    @OneToOne
    private Membership membership;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<IndividualSession> individualSessions;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Client_GroupClass> client_groupClasses;
}
