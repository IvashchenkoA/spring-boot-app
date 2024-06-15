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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getConductedClasses() {
        return conductedClasses;
    }

    public void setConductedClasses(int conductedClasses) {
        this.conductedClasses = conductedClasses;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<GroupClass> getGroupClasses() {
        return groupClasses;
    }

    public void setGroupClasses(List<GroupClass> groupClasses) {
        this.groupClasses = groupClasses;
    }

    public List<IndividualSession> getIndividualSessions() {
        return individualSessions;
    }

    public void setIndividualSessions(List<IndividualSession> individualSessions) {
        this.individualSessions = individualSessions;
    }
}
