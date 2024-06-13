package com.imba.gymmemore.DTO;

import com.imba.gymmemore.validation.ValidPassword;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClientDTO {
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
