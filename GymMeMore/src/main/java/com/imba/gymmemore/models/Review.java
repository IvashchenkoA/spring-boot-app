package com.imba.gymmemore.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double rating;
    private String feedback;
    @OneToOne
    @Nullable
    private IndividualSession individualSession;
    @OneToOne
    @Nullable
    private Client_GroupClass client_groupClass;

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Nullable
    public IndividualSession getIndividualSession() {
        return individualSession;
    }

    public void setIndividualSession(@Nullable IndividualSession individualSession) {
        this.individualSession = individualSession;
    }

    @Nullable
    public Client_GroupClass getClient_groupClass() {
        return client_groupClass;
    }

    public void setClient_groupClass(@Nullable Client_GroupClass client_groupClass) {
        this.client_groupClass = client_groupClass;
    }
}
