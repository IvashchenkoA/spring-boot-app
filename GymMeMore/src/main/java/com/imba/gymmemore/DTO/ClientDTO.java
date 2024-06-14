package com.imba.gymmemore.DTO;

import com.imba.gymmemore.models.Client;
import com.imba.gymmemore.validation.ValidPassword;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClientDTO {
    private Long id;
    @Size(min = 3, max = 25)
    @NotNull(message = "provide first name.")
    private String firstName;
    @Email(message = "email must be valid.")
    @Column(unique = true)
    private String email;
    @Size(min = 3, max = 20)
    @NotNull(message = "provide username.")
    @Column(unique = true)
    private String username;
    @NotNull
    @Size(min = 8, max = 20)
    @ValidPassword
    private String password;
    private LocalDate joinDate;
    private int classesCount;
    private Long bankAccount_Id;
    private Long profLevel_Id;
    private Long membership_Id;

    public Client map(){
        return new Client(firstName,email, username, password);
    }

    public ClientDTO() {
    }

    public ClientDTO(Long id,String firstName, String email, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.username = client.getUsername();
        this.joinDate = client.getJoinDate();
        this.classesCount = client.getClassesCount();
        this.bankAccount_Id = client.getBankAccount().getId();
        this.membership_Id = client.getMembership().getId();
        this.profLevel_Id = client.getProfLevel().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(min = 3, max = 25) @NotNull(message = "provide first name.") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 3, max = 25) @NotNull(message = "provide first name.") String firstName) {
        this.firstName = firstName;
    }

    public @Email(message = "email must be valid.") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "email must be valid.") String email) {
        this.email = email;
    }

    public @Size(min = 3, max = 20) @NotNull String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 3, max = 20) @NotNull String username) {
        this.username = username;
    }

    public @NotNull @Size(min = 8, max = 20) String getPassword() {
        return password;
    }

    public void setPassword(@NotNull @Size(min = 8, max = 20) String password) {
        this.password = password;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public int getClassesCount() {
        return classesCount;
    }

    public void setClassesCount(int classesCount) {
        this.classesCount = classesCount;
    }

    public Long getBankAccount_Id() {
        return bankAccount_Id;
    }

    public void setBankAccount_Id(Long bankAccount_Id) {
        this.bankAccount_Id = bankAccount_Id;
    }

    public Long getProfLevel_Id() {
        return profLevel_Id;
    }

    public void setProfLevel_Id(Long profLevel_Id) {
        this.profLevel_Id = profLevel_Id;
    }

    public Long getMembership_Id() {
        return membership_Id;
    }

    public void setMembership_Id(Long membership_Id) {
        this.membership_Id = membership_Id;
    }
}
