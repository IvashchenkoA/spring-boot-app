package com.imba.gymmemore.DTO;

public class CoachDetailsDTO {
    private Long id;
    private String name;
    private String hireDate;
    private double rating;
    private int conductedClasses;
    private String branch;

    public CoachDetailsDTO() {
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
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
}
