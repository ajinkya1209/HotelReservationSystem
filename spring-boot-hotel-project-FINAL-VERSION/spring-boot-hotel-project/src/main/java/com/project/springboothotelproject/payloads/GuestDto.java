package com.project.springboothotelproject.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.springboothotelproject.enitites.Address;
import com.project.springboothotelproject.enitites.Gender;
import com.project.springboothotelproject.exceptionhandling.ValidGender;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GuestDto {

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Invalid first name format")
    @Size(min = 2, message = "First name should have at least 2 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Invalid last name format")
    @Size(min = 2, message = "Last name should have at least 2 characters")
    private String lastName;


    @ValidGender  //Custom Validation
    private Gender gender;

    @NotNull(message = "Age can't be empty")
    @Positive(message = "Age can't be negative")
    @Min(value = 18,message = "Age can't be less than 18 years")
    @Max(value = 100,message = "Invalid age")
    private Integer age;

    private Address address;

    /* ^(\\+[0-9]{1,3})?: Allows an optional international dialing code (e.g., +1) at the beginning.
        [0-9]{10}$: There should be exactly 10 digits following the optional dialing code. */
    @NotBlank(message = "Contact is required")
    @Pattern(regexp = "^(\\+[0-9]{1,3})?[0-9]{10}$", message = "Invalid contact number format")
    private String contactNo;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email format")
    private String email;

    /* At least one lowercase letter.
    At least one uppercase letter.
    At least one digit.
    Can have any characters after fulfilling the above conditions.*/
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should have at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Invalid password format")
    private String password;
}
