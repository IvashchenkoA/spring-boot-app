package com.imba.gymmemore.DTO;

public class ClassDetailsDTO {
    private Long classId;
    private String coachName;
    private String coachBranch;
    private double popularity;
    private double rating;

    public ClassDetailsDTO(Long classId, String coachName, String coachBranch, double popularity, double rating) {
        this.classId = classId;
        this.coachName = coachName;
        this.coachBranch = coachBranch;
        this.popularity = popularity;
        this.rating = rating;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getCoachBranch() {
        return coachBranch;
    }

    public void setCoachBranch(String coachBranch) {
        this.coachBranch = coachBranch;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}