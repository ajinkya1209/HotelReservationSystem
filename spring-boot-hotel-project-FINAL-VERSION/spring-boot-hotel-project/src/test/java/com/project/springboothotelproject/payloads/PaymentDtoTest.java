package com.project.springboothotelproject.payloads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.project.springboothotelproject.enitites.PaymentMode;
import org.junit.jupiter.api.Test;

class PaymentDtoTest {

    @Test
    void testCanEqual() {
        assertFalse((new PaymentDto()).canEqual("Other"));
    }


    @Test
    void testCanEqual2() {
        PaymentDto paymentDto = new PaymentDto();
        assertTrue(paymentDto.canEqual(new PaymentDto()));
    }


    @Test
    void testConstructor() {
        PaymentDto actualPaymentDto = new PaymentDto();
        actualPaymentDto.setGuestId(1L);
        actualPaymentDto.setPaymentAmount(1);
        actualPaymentDto.setPaymentMode(PaymentMode.CREDIT_CARD);
        actualPaymentDto.setRoomId(1L);
        String actualToStringResult = actualPaymentDto.toString();
        assertEquals(1L, actualPaymentDto.getGuestId().longValue());
        assertEquals(1, actualPaymentDto.getPaymentAmount().intValue());
        assertEquals(PaymentMode.CREDIT_CARD, actualPaymentDto.getPaymentMode());
        assertEquals(1L, actualPaymentDto.getRoomId().longValue());
        assertEquals("PaymentDto(guestId=1, roomId=1, paymentMode=CREDIT_CARD, paymentAmount=1)", actualToStringResult);
    }


    @Test
    void testConstructor2() {
        PaymentDto actualPaymentDto = new PaymentDto(1L, 1L, PaymentMode.CREDIT_CARD, 1);
        actualPaymentDto.setGuestId(1L);
        actualPaymentDto.setPaymentAmount(1);
        actualPaymentDto.setPaymentMode(PaymentMode.CREDIT_CARD);
        actualPaymentDto.setRoomId(1L);
        String actualToStringResult = actualPaymentDto.toString();
        assertEquals(1L, actualPaymentDto.getGuestId().longValue());
        assertEquals(1, actualPaymentDto.getPaymentAmount().intValue());
        assertEquals(PaymentMode.CREDIT_CARD, actualPaymentDto.getPaymentMode());
        assertEquals(1L, actualPaymentDto.getRoomId().longValue());
        assertEquals("PaymentDto(guestId=1, roomId=1, paymentMode=CREDIT_CARD, paymentAmount=1)", actualToStringResult);
    }


    @Test
    void testEquals() {
        assertNotEquals(new PaymentDto(), null);
        assertNotEquals(new PaymentDto(), "Different type to PaymentDto");
    }


    @Test
    void testEquals2() {
        PaymentDto paymentDto = new PaymentDto();
        assertEquals(paymentDto, paymentDto);
        int expectedHashCodeResult = paymentDto.hashCode();
        assertEquals(expectedHashCodeResult, paymentDto.hashCode());
    }


    @Test
    void testEquals3() {
        PaymentDto paymentDto = new PaymentDto();
        PaymentDto paymentDto1 = new PaymentDto();
        assertEquals(paymentDto, paymentDto1);
        int expectedHashCodeResult = paymentDto.hashCode();
        assertEquals(expectedHashCodeResult, paymentDto1.hashCode());
    }


    @Test
    void testEquals4() {
        PaymentDto paymentDto = new PaymentDto(1L, 1L, PaymentMode.CREDIT_CARD, 1);
        assertNotEquals(paymentDto, new PaymentDto());
    }


    @Test
    void testEquals5() {
        PaymentDto paymentDto = new PaymentDto();
        assertNotEquals(paymentDto, new PaymentDto(1L, 1L, PaymentMode.CREDIT_CARD, 1));
    }


    @Test
    void testEquals6() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setRoomId(1L);
        assertNotEquals(paymentDto, new PaymentDto());
    }

    @Test
    void testEquals7() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentMode(PaymentMode.CREDIT_CARD);
        assertNotEquals(paymentDto, new PaymentDto());
    }
}

