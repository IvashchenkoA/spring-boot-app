package com.imba.gymmemore.DTO;

import java.time.LocalDate;

public class AccountDetailsDTO {
    private String name;
    private String bankAccount;
    private String membershipName;
    private LocalDate membershipExpDate;
    private String level;
    private String levelDescr;
    private int classCount;

    public AccountDetailsDTO() {
    }

    public AccountDetailsDTO(String name, String bankAccount, String membershipName, LocalDate membershipExpDate, String level, String levelDescr, int classCount) {
        this.name = name;
        this.bankAccount = bankAccount;
        this.membershipName = membershipName;
        this.membershipExpDate = membershipExpDate;
        this.level = level;
        this.levelDescr = levelDescr;
        this.classCount = classCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public LocalDate getMembershipExpDate() {
        return membershipExpDate;
    }

    public void setMembershipExpDate(LocalDate membershipExpDate) {
        this.membershipExpDate = membershipExpDate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelDescr() {
        return levelDescr;
    }

    public void setLevelDescr(String levelDescr) {
        this.levelDescr = levelDescr;
    }

    public int getClassCount() {
        return classCount;
    }

    public void setClassCount(int classCount) {
        this.classCount = classCount;
    }
}
