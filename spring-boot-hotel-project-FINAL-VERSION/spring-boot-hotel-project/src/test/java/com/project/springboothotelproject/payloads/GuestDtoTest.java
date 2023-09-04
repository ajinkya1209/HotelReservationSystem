package com.project.springboothotelproject.payloads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.project.springboothotelproject.enitites.Address;
import com.project.springboothotelproject.enitites.Gender;
import org.junit.jupiter.api.Test;

class GuestDtoTest {

    @Test
    void testCanEqual() {
        assertFalse((new GuestDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        GuestDto guestDto = new GuestDto();
        assertTrue(guestDto.canEqual(new GuestDto()));
    }

    @Test
    void testConstructor() {
        GuestDto actualGuestDto = new GuestDto();
        Address address = new Address("42 Main St", "Oxford", "MD", "216542");

        actualGuestDto.setAddress(address);
        actualGuestDto.setAge(25);
        actualGuestDto.setContactNo("Contact No");
        actualGuestDto.setEmail("jack.doe@example.org");
        actualGuestDto.setFirstName("Jack");
        actualGuestDto.setGender(Gender.MALE);
        actualGuestDto.setLastName("Doe");
        actualGuestDto.setPassword("Jack123");
        String actualToStringResult = actualGuestDto.toString();
        assertSame(address, actualGuestDto.getAddress());
        assertEquals(25, actualGuestDto.getAge().intValue());
        assertEquals("Contact No", actualGuestDto.getContactNo());
        assertEquals("jack.doe@example.org", actualGuestDto.getEmail());
        assertEquals("Jack", actualGuestDto.getFirstName());
        assertEquals(Gender.MALE, actualGuestDto.getGender());
        assertEquals("Doe", actualGuestDto.getLastName());
        assertEquals("Jack123", actualGuestDto.getPassword());
        assertEquals("GuestDto(firstName=Jack, lastName=Doe, gender=MALE, age=25, address=Address(streetAddress=42 Main St,"
                + " city=Oxford, state=MD, zipCode=216542), contactNo=Contact No, email=jack.doe@example.org, password"
                + "=Jack123)", actualToStringResult);
    }



    @Test
    void testEquals() {
        assertNotEquals(new GuestDto(), null);
        assertNotEquals(new GuestDto(), "Different type to GuestDto");
    }


    @Test
    void testEquals2() {
        GuestDto guestDto = new GuestDto();
        assertEquals(guestDto, guestDto);
        int expectedHashCodeResult = guestDto.hashCode();
        assertEquals(expectedHashCodeResult, guestDto.hashCode());
    }


    @Test
    void testEquals3() {
        GuestDto guestDto = new GuestDto();
        GuestDto guestDto1 = new GuestDto();
        assertEquals(guestDto, guestDto1);
        int expectedHashCodeResult = guestDto.hashCode();
        assertEquals(expectedHashCodeResult, guestDto1.hashCode());
    }



    @Test
    void testEquals4() {
        GuestDto guestDto = new GuestDto();
        guestDto.setFirstName("Jane");
        assertNotEquals(guestDto, new GuestDto());
    }


    @Test
    void testEquals5() {
        GuestDto guestDto = new GuestDto();
        guestDto.setLastName("Doe");
        assertNotEquals(guestDto, new GuestDto());
    }


    @Test
    void testEquals6() {
        GuestDto guestDto = new GuestDto();
        guestDto.setGender(Gender.MALE);
        assertNotEquals(guestDto, new GuestDto());
    }


    @Test
    void testEquals7() {
        GuestDto guestDto = new GuestDto();
        guestDto.setAddress(new Address("42 Main St", "Oxford", "MD", "21654"));
        assertNotEquals(guestDto, new GuestDto());
    }


    @Test
    void testEquals8() {
        GuestDto guestDto = new GuestDto();
        guestDto.setContactNo("Contact No");
        assertNotEquals(guestDto, new GuestDto());
    }


    @Test
    void testEquals9() {
        GuestDto guestDto = new GuestDto();
        guestDto.setEmail("jack.doe@example.org");
        assertNotEquals(guestDto, new GuestDto());
    }
}

