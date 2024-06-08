package com.imba.gymmemore.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class IndividualSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime scheduledTime;
    @OneToOne
    private Review review;
    @ManyToOne
    private Coach coach;
    @ManyToOne
    private Client client;
}
