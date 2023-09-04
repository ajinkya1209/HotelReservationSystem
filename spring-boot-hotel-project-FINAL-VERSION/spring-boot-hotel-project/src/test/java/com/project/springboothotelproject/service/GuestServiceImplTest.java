package com.project.springboothotelproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.springboothotelproject.enitites.Address;
import com.project.springboothotelproject.enitites.Gender;
import com.project.springboothotelproject.enitites.Guest;
import com.project.springboothotelproject.exceptionhandling.ResourceNotFoundException;
import com.project.springboothotelproject.payloads.GuestDto;
import com.project.springboothotelproject.payloads.UpdateGuestDto;
import com.project.springboothotelproject.repository.GuestRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {GuestServiceImpl.class})
@ExtendWith(SpringExtension.class)
class GuestServiceImplTest {
    @MockBean
    private GuestRepository guestRepository;

    @Autowired
    private GuestServiceImpl guestServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void testAddGuest() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreetAddress("42 Main St");
        address.setZipCode("21654");

        Guest guest = new Guest();
        guest.setAddress(address);
        guest.setAge(1);
        guest.setContactNo("Contact No");
        guest.setEmail("jane.doe@example.org");
        guest.setFirstName("Jane");
        guest.setGender(Gender.MALE);
        guest.setGuestId(1L);
        guest.setLastName("Doe");
        guest.setPassword("Jane123");
        when(guestRepository.save(Mockito.<Guest>any())).thenReturn(guest);

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setState("MD");
        address2.setStreetAddress("42 Main St");
        address2.setZipCode("21654");

        Guest guest2 = new Guest();
        guest2.setAddress(address2);
        guest2.setAge(1);
        guest2.setContactNo("Contact No");
        guest2.setEmail("jane.doe@example.org");
        guest2.setFirstName("Jane");
        guest2.setGender(Gender.MALE);
        guest2.setGuestId(1L);
        guest2.setLastName("Doe");
        guest2.setPassword("Jane123");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Guest>>any())).thenReturn(guest2);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        assertEquals("Guest id 1 Added successfully", guestServiceImpl.addGuest(new GuestDto()));
        verify(guestRepository).save(Mockito.<Guest>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Guest>>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    @Test
    void testAddGuest2() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreetAddress("42 Main St");
        address.setZipCode("21654");

        Guest guest = new Guest();
        guest.setAddress(address);
        guest.setAge(1);
        guest.setContactNo("Contact No");
        guest.setEmail("jane.doe@example.org");
        guest.setFirstName("Jane");
        guest.setGender(Gender.MALE);
        guest.setGuestId(1L);
        guest.setLastName("Doe");
        guest.setPassword("Jane123");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Guest>>any())).thenReturn(guest);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new ResourceNotFoundException("Msg"));
        assertThrows(ResourceNotFoundException.class, () -> guestServiceImpl.addGuest(new GuestDto()));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Guest>>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }


    @Test
    void testGetAllGuests() {
        when(guestRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(guestServiceImpl.getAllGuests().isEmpty());
        verify(guestRepository).findAll();
    }


    @Test
    void testGetAllGuests2() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreetAddress("42 Main St");
        address.setZipCode("21654");

        Guest guest = new Guest();
        guest.setAddress(address);
        guest.setAge(1);
        guest.setContactNo("Contact No");
        guest.setEmail("jane.doe@example.org");
        guest.setFirstName("Jane");
        guest.setGender(Gender.MALE);
        guest.setGuestId(1L);
        guest.setLastName("Doe");
        guest.setPassword("Jane123");

        ArrayList<Guest> guestList = new ArrayList<>();
        guestList.add(guest);
        when(guestRepository.findAll()).thenReturn(guestList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<GuestDto>>any())).thenReturn(new GuestDto());
        assertEquals(1, guestServiceImpl.getAllGuests().size());
        verify(guestRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<GuestDto>>any());
    }

    @Test
    void testGetAllGuests3() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreetAddress("42 Main St");
        address.setZipCode("21654");

        Guest guest = new Guest();
        guest.setAddress(address);
        guest.setAge(1);
        guest.setContactNo("Contact No");
        guest.setEmail("jane.doe@example.org");
        guest.setFirstName("Jane");
        guest.setGender(Gender.MALE);
        guest.setGuestId(1L);
        guest.setLastName("Doe");
        guest.setPassword("Jane123");

        ArrayList<Guest> guestList = new ArrayList<>();
        guestList.add(guest);
        when(guestRepository.findAll()).thenReturn(guestList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<GuestDto>>any()))
                .thenThrow(new ResourceNotFoundException("Msg"));
        assertThrows(ResourceNotFoundException.class, () -> guestServiceImpl.getAllGuests());
        verify(guestRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<GuestDto>>any());
    }


    @Test
    void testUpdateGuest() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreetAddress("42 Main St");
        address.setZipCode("21654");

        Guest guest = new Guest();
        guest.setAddress(address);
        guest.setAge(1);
        guest.setContactNo("Contact No");
        guest.setEmail("jane.doe@example.org");
        guest.setFirstName("Jane");
        guest.setGender(Gender.MALE);
        guest.setGuestId(1L);
        guest.setLastName("Doe");
        guest.setPassword("Jane123");
        Optional<Guest> ofResult = Optional.of(guest);

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setState("MD");
        address2.setStreetAddress("42 Main St");
        address2.setZipCode("21654");

        Guest guest2 = new Guest();
        guest2.setAddress(address2);
        guest2.setAge(1);
        guest2.setContactNo("Contact No");
        guest2.setEmail("jane.doe@example.org");
        guest2.setFirstName("Jane");
        guest2.setGender(Gender.MALE);
        guest2.setGuestId(1L);
        guest2.setLastName("Doe");
        guest2.setPassword("Jane123");
        when(guestRepository.save(Mockito.<Guest>any())).thenReturn(guest2);
        when(guestRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertEquals("Guest having ID 1 updated successfully", guestServiceImpl.updateGuest(1L, new UpdateGuestDto()));
        verify(guestRepository).save(Mockito.<Guest>any());
        verify(guestRepository).findById(Mockito.<Long>any());
    }

    @Test
    void testUpdateGuest2() {
        Optional<Guest> emptyResult = Optional.empty();
        when(guestRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> guestServiceImpl.updateGuest(1L, new UpdateGuestDto()));
        verify(guestRepository).findById(Mockito.<Long>any());
    }
}

