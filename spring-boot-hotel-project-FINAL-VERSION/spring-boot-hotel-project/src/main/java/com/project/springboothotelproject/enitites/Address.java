package com.project.springboothotelproject.enitites;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
// The @Embedded annotation is used to indicate that this class will be embedded
// within other entity classes, allowing multiple fields to be mapped into a single component.
// This class represents an embeddable component for storing address details.
public class Address {
    @NotBlank(message = "Street address can't be empty")
    @Size(min = 3, message = "address should be minimum 3 characters long")
    private String streetAddress;

    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Invalid format")
    @Size(max = 25, message = "City length exceeded ")
    private String city;

    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "Invalid format")
    @Size(max = 25, message = "State length exceeded")
    private String state;

    @Pattern(regexp = "^(?!0\\d)(?!\\d{0,5}$)\\d{6,}$", message = "Invalid format")
    private String zipCode;
}
