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

    public GroupClass() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    public List<Client_GroupClass> getClient_groupClasses() {
        return client_groupClasses;
    }

    public void setClient_groupClasses(List<Client_GroupClass> client_groupClasses) {
        this.client_groupClasses = client_groupClasses;
    }
}
