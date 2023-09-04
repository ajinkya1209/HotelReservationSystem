package com.project.springboothotelproject.payloads;

import com.project.springboothotelproject.enitites.Address;
import com.project.springboothotelproject.enitites.Gender;
import com.project.springboothotelproject.exceptionhandling.ValidGender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//This class is for the update purpose of customer details
public class UpdateGuestDto {

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Invalid first name format")
    @Size(min = 2, message = "First name should have at least 2 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Invalid last name format")
    @Size(min = 2, message = "Last name should have at least 2 characters")
    private String lastName;


    @ValidGender
    private Gender gender;

    @NotNull(message = "Age can't be empty")
    @Positive(message = "Age can't be negative")
    @Min(value = 18,message = "Age can't be less than 18 years")
    @Max(value = 100,message = "Invalid age")
    private Integer age;

    private Address address;

    @NotBlank(message = "Contact is required")
    @Pattern(regexp = "^(\\+[0-9]{1,3})?[0-9]{10}$", message = "Invalid contact number format")
    private String contactNo;
}
