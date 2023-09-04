package com.project.springboothotelproject.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdminDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2, message = "Name should have at least 2 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Invalid name format")
    private String adminName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email format")
    private String adminEmail;

    /* At least one lowercase letter.
    At least one uppercase letter.
    At least one digit.
    Can have any characters after fulfilling the above conditions.*/
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password should have at least 6 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Invalid password format")
    private String adminPassword;

}
