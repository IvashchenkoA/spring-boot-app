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
}
