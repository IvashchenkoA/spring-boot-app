package com.imba.gymmemore.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClassType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int capacity;
    @OneToMany(mappedBy = "classType", fetch = FetchType.EAGER)
    private List<GroupClass> groupClasses;

    public ClassType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<GroupClass> getGroupClasses() {
        return groupClasses;
    }

    public void setGroupClasses(List<GroupClass> groupClasses) {
        this.groupClasses = groupClasses;
    }
}
