package com.project.springboothotelproject.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type",nullable = false)
    private RoomType roomType;

    @Column(name = "about",nullable = false)
    private String aboutRoom;

    @Column(name = "room_price",nullable = false)
    private Integer roomPrice;

    @Column(name = "room_facilities",nullable = false)
    private String roomFacilities;

    @Column(name = "roomStatus",nullable = false)
    private String roomStatus;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
