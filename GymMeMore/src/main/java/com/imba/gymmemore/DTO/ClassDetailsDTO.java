package com.imba.gymmemore.DTO;

public class ClassDetailsDTO {
    private Long id;
    private String coachFirstName;
    private String branchName;
    private String classDescription;
    private double classRating;
    private double classPopularity;
    private double coachRating;

    public ClassDetailsDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoachFirstName() {
        return coachFirstName;
    }

    public void setCoachFirstName(String coachFirstName) {
        this.coachFirstName = coachFirstName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public double getClassRating() {
        return classRating;
    }

    public void setClassRating(double classRating) {
        this.classRating = classRating;
    }

    public double getClassPopularity() {
        return classPopularity;
    }

    public void setClassPopularity(double classPopularity) {
        this.classPopularity = classPopularity;
    }

    public double getCoachRating() {
        return coachRating;
    }

    public void setCoachRating(double coachRating) {
        this.coachRating = coachRating;
    }
}