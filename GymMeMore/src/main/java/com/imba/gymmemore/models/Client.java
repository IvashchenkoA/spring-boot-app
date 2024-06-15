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
    @JoinColumn(name = "prof_level_id")
    private ProfLevel profLevel;
    @OneToOne
    @JoinColumn(name = "bankAccount_Id", unique = true)
    private BankAccount bankAccount;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "membership_Id", unique = true)
    private Membership membership;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<IndividualSession> individualSessions;
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private List<Client_GroupClass> client_groupClasses;

    public Client() {
    }

    public Client(String firstName, String email, String username, String password) {
        super(firstName, email, username, password);
    }

    public Client(String firstName, String email, String username, String password,
                  Membership membership,BankAccount bankAccount, ProfLevel profLevel) {
        super(firstName, email, username, password);
        this.bankAccount = bankAccount;
        this.membership = membership;
        this.profLevel = profLevel;
    }
    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public int getClassesCount() {
        return classesCount;
    }

    public void setClassesCount(int classesCount) {
        this.classesCount = classesCount;
    }

    public ProfLevel getProfLevel() {
        return profLevel;
    }

    public void setProfLevel(ProfLevel profLevel) {
        this.profLevel = profLevel;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public List<IndividualSession> getIndividualSessions() {
        return individualSessions;
    }

    public void setIndividualSessions(List<IndividualSession> individualSessions) {
        this.individualSessions = individualSessions;
    }

    public List<Client_GroupClass> getClient_groupClasses() {
        return client_groupClasses;
    }

    public void setClient_groupClasses(List<Client_GroupClass> client_groupClasses) {
        this.client_groupClasses = client_groupClasses;
    }
}
