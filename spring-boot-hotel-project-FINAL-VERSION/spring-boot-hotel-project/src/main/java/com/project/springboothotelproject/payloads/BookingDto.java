package com.project.springboothotelproject.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Hotel id is required")
    private Long hotelId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Room id is required")
    private Long roomId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Guest id is required")
    private Long guestId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Check in date must be in future")
    private LocalDate checkInDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Check out date must be in future")
    private LocalDate checkOutDate;
}
