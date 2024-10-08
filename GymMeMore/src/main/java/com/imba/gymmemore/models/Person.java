package com.imba.gymmemore.models;

import com.imba.gymmemore.validation.ValidPassword;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Person_type")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Size(min = 3, max = 25)
    @NotNull(message = "provide first name.")
    private String firstName;
    @Email(message = "email must be valid.")
    private String email;
    @Size(min = 3, max = 20)
    @NotNull
    private String username;
    @NotNull
    @Size(min = 8, max = 20)
    @ValidPassword
    private String password;

    public Person(String firstName, String email, String username, String password) {
        this.firstName = firstName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Person() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}
