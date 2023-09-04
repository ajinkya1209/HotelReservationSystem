package com.project.springboothotelproject.enitites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void testConstructor() {
        Payment actualPayment = new Payment();
        Guest guest = new Guest();
        actualPayment.setGuest(guest);
        actualPayment.setPaymentAmount(1);
        actualPayment.setPaymentMode(PaymentMode.CREDIT_CARD);
        actualPayment.setPaymentStatus("Payment Status");
        Room room = new Room();
        actualPayment.setRoom(room);
        assertSame(guest, actualPayment.getGuest());
        assertEquals(1, actualPayment.getPaymentAmount().intValue());
        assertEquals(PaymentMode.CREDIT_CARD, actualPayment.getPaymentMode());
        assertEquals("Payment Status", actualPayment.getPaymentStatus());
        assertSame(room, actualPayment.getRoom());
    }


    @Test
    void testConstructor2() {
        Guest guest = new Guest();
        Payment actualPayment = new Payment(PaymentMode.CREDIT_CARD, 1, "Payment Status", guest, new Room());
        Guest guest1 = new Guest();
        actualPayment.setGuest(guest1);
        actualPayment.setPaymentAmount(1);
        actualPayment.setPaymentMode(PaymentMode.CREDIT_CARD);
        actualPayment.setPaymentStatus("Payment Status");
        Room room = new Room();
        actualPayment.setRoom(room);
        assertSame(guest1, actualPayment.getGuest());
        assertEquals(1, actualPayment.getPaymentAmount().intValue());
        assertEquals(PaymentMode.CREDIT_CARD, actualPayment.getPaymentMode());
        assertEquals("Payment Status", actualPayment.getPaymentStatus());
        assertSame(room, actualPayment.getRoom());
    }
}

