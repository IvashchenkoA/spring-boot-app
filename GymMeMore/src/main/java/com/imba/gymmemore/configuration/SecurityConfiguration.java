/*
package com.imba.gymmemore.configuration;

import com.imba.gymmemore.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfiguration(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/home", "/home/branches/{id}", "/home/branches", "/home/branches/city/{city}", "/login", "/join", "/membership", "/home-gym.jpg", "/styles.css",
                                "/1.jpg", "/2.jpg", "/3.jpg", "/4.jpg", "/5.jpg", "/my-console", "/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/home/branches").permitAll()
                        .requestMatchers(HttpMethod.POST, "/home/branches/{id}/route").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers("/account/**").hasRole("CLIENT")
                        .requestMatchers("/redirect:account").hasRole("CLIENT")
                        .requestMatchers("/coach/**").hasRole("COACH")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form*/
/**//*

                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .permitAll()
                )
                .userDetailsService(customUserDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
*/
