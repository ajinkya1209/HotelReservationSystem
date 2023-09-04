package com.project.springboothotelproject.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    @Column(name = "hotel_name", length = 30, nullable = false)
    private String hotelName;

    @Column(name="hotel_desc", nullable = false)
    private String aboutHotel;

    @Embedded
    private Address address;

    @Column(name="hotel_contact", nullable = false,unique = true)
    private String contactNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "hotel_type",nullable = false)
    private HotelType hotelType;

    @Column(name = "hotel_amenities",nullable = false)
    private String hotelAmenities;

    @Column(name = "room_capacity",nullable = false)
    private Integer roomCapacity;
}
