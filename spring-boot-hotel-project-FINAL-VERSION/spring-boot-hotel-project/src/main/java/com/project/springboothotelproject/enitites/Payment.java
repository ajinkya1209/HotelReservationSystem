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
@Table(name = "payment")
public class Payment extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_mode",nullable = false)
    private PaymentMode paymentMode;

    @Column(name = "payment_amount",nullable = false)
    private Integer paymentAmount;

    @Column(name = "payment_status",nullable = false)
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
