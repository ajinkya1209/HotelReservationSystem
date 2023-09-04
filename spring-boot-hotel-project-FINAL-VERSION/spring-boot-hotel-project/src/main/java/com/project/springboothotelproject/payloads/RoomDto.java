package com.project.springboothotelproject.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.springboothotelproject.enitites.RoomType;
import com.project.springboothotelproject.exceptionhandling.ValidRoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoomDto {

    @ValidRoomType //Custom validation
    private RoomType roomType;

    @NotBlank(message = "Room Description is required")
    @Size(min = 15,message = "Description should be minimum 15 characters long")
    private String aboutRoom;

    @NotBlank(message = "Room facilities is required")
    @Size(min=10,message = "Room Facilities should be minimum 10 characters long")
    private String roomFacilities;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Hotel id is required")
    private Long hotelId;
}
