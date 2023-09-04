package com.project.springboothotelproject.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.springboothotelproject.enitites.Address;
import com.project.springboothotelproject.enitites.HotelType;
import com.project.springboothotelproject.exceptionhandling.ValidHotelType;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelDto {

	    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	    private Long hotelId;

        @NotBlank(message = "Hotel name is required")
        @Size(min=3,message = "Hotel name should be minimum 3 characters long")
        @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Invalid name format")
        private String hotelName;

        @NotBlank(message = "Description is required")
        @Size(min=25,message="Hotel description should be minimum 25 characters long")
        private String aboutHotel;


        private Address address;

        /* ^(\\+[0-9]{1,3})?: Allows an optional international dialing code (e.g., +1) at the beginning.
        [0-9]{10}$: There should be exactly 10 digits following the optional dialing code. */
        @NotBlank(message = "Contact is required")
        @Pattern(regexp = "^(\\+[0-9]{1,3})?[0-9]{10}$", message = "Invalid contact number format")
        private String contactNo;

        @ValidHotelType  // Custom Validation
        private HotelType hotelType;

        @NotBlank(message = "Hotel Amenities is required")
        @Size(min=10,message = "Hotel Amenities should be minimum 10 characters long")
        private String hotelAmenities;

        @NotNull(message = "Room capacity can't be empty")
        @Min(value = 10,message = "Room capacity should be minimum 10")
        @Max(value = 10000, message = "Room capacity cannot exceed 1000")
        @Positive(message = "Room capacity can't be a negative value")
        private Integer roomCapacity;

}
