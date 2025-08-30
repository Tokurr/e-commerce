package com.example.ecoomerce.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    @Email(message = "Please provide a valid email address")
    String mail;
    @NotBlank(message = "First name cannot be empty")
    String firstName;
    @NotBlank(message = "Last name cannot be empty")

    String lastName;
    @Pattern(
            regexp = "^(\\+90|0)?5\\d{9}$",
            message = "Please provide a valid phone number (e.g., 05554443322)"
    )
    String phoneNumber;

    Boolean isActive;

    public User(String mail, String firstName, String lastName, String phoneNumber,Boolean isActive) {
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

}
