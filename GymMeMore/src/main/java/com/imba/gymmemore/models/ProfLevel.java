package com.imba.gymmemore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
@Entity
public class ProfLevel {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull
    @Size(min = 1, max = 20)
    private String name;
    @NotNull
    @Size(min = 1, max = 100)
    private String description;
    @OneToMany(mappedBy = "profLevel", fetch = FetchType.EAGER)
    private List<Client> clients;

    public ProfLevel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ProfLevel() {
    }

    public @NotNull @Size(min = 1, max = 20) String getName() {
        return name;
    }

    public void setName(@NotNull @Size(min = 1, max = 20) String name) {
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public @NotNull @Size(min = 1, max = 100) String getDescription() {
        return description;
    }

    public void setDescription(@NotNull @Size(min = 1, max = 100) String description) {
        this.description = description;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
