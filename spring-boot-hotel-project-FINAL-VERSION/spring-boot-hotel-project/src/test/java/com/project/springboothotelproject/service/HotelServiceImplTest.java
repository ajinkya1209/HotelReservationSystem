package com.project.springboothotelproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.springboothotelproject.enitites.Address;
import com.project.springboothotelproject.enitites.Hotel;
import com.project.springboothotelproject.enitites.HotelType;
import com.project.springboothotelproject.exceptionhandling.ResourceNotFoundException;
import com.project.springboothotelproject.payloads.HotelDto;
import com.project.springboothotelproject.repository.HotelRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {HotelServiceImpl.class})
@ExtendWith(SpringExtension.class)
class HotelServiceImplTest {
    @MockBean
    private HotelRepository hotelRepository;

    @Autowired
    private HotelServiceImpl hotelServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private RoomService roomService;

    @Test
    void testAddHotels() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreetAddress("42 Main St");
        address.setZipCode("21654");

        Hotel hotel = new Hotel();
        hotel.setAboutHotel("About Hotel");
        hotel.setAddress(address);
        hotel.setContactNo("Contact No");
        hotel.setHotelAmenities("Hotel Amenities");
        hotel.setHotelId(1L);
        hotel.setHotelName("Hotel Name");
        hotel.setHotelType(HotelType.TWO_STAR);
        hotel.setRoomCapacity(1);
        when(hotelRepository.save(Mockito.<Hotel>any())).thenReturn(hotel);

        Address address2 = new Address();
        address2.setCity("Oxford");
        address2.setState("MD");
        address2.setStreetAddress("42 Main St");
        address2.setZipCode("21654");

        Hotel hotel2 = new Hotel();
        hotel2.setAboutHotel("About Hotel");
        hotel2.setAddress(address2);
        hotel2.setContactNo("Contact No");
        hotel2.setHotelAmenities("Hotel Amenities");
        hotel2.setHotelId(1L);
        hotel2.setHotelName("Hotel Name");
        hotel2.setHotelType(HotelType.TWO_STAR);
        hotel2.setRoomCapacity(1);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Hotel>>any())).thenReturn(hotel2);
        assertEquals("Hotel Hotel Name Added successfully", hotelServiceImpl.addHotels(new HotelDto()));
        verify(hotelRepository).save(Mockito.<Hotel>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Hotel>>any());
    }


    @Test
    void testAddHotels2() {
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Hotel>>any()))
                .thenThrow(new ResourceNotFoundException("Added successfully"));
        assertThrows(ResourceNotFoundException.class, () -> hotelServiceImpl.addHotels(new HotelDto()));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Hotel>>any());
    }

    @Test
    void testGetHotels() {
        when(hotelRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(hotelServiceImpl.getHotels().isEmpty());
        verify(hotelRepository).findAll();
    }


    @Test
    void testGetHotels2() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreetAddress("42 Main St");
        address.setZipCode("21654");

        Hotel hotel = new Hotel();
        hotel.setAboutHotel("About Hotel");
        hotel.setAddress(address);
        hotel.setContactNo("Contact No");
        hotel.setHotelAmenities("Hotel Amenities");
        hotel.setHotelId(1L);
        hotel.setHotelName("Hotel Name");
        hotel.setHotelType(HotelType.TWO_STAR);
        hotel.setRoomCapacity(1);

        ArrayList<Hotel> hotelList = new ArrayList<>();
        hotelList.add(hotel);
        when(hotelRepository.findAll()).thenReturn(hotelList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<HotelDto>>any())).thenReturn(new HotelDto());
        assertEquals(1, hotelServiceImpl.getHotels().size());
        verify(hotelRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<HotelDto>>any());
    }

    @Test
    void testGetHotels3() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreetAddress("42 Main St");
        address.setZipCode("21654");

        Hotel hotel = new Hotel();
        hotel.setAboutHotel("About Hotel");
        hotel.setAddress(address);
        hotel.setContactNo("Contact No");
        hotel.setHotelAmenities("Hotel Amenities");
        hotel.setHotelId(1L);
        hotel.setHotelName("Hotel Name");
        hotel.setHotelType(HotelType.TWO_STAR);
        hotel.setRoomCapacity(1);

        ArrayList<Hotel> hotelList = new ArrayList<>();
        hotelList.add(hotel);
        when(hotelRepository.findAll()).thenReturn(hotelList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<HotelDto>>any()))
                .thenThrow(new ResourceNotFoundException("Msg"));
        assertThrows(ResourceNotFoundException.class, () -> hotelServiceImpl.getHotels());
        verify(hotelRepository).findAll();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<HotelDto>>any());
    }

    @Test
    void testDeleteHotels() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreetAddress("42 Main St");
        address.setZipCode("21654");

        Hotel hotel = new Hotel();
        hotel.setAboutHotel("About Hotel");
        hotel.setAddress(address);
        hotel.setContactNo("Contact No");
        hotel.setHotelAmenities("Hotel Amenities");
        hotel.setHotelId(1L);
        hotel.setHotelName("Hotel Name");
        hotel.setHotelType(HotelType.TWO_STAR);
        hotel.setRoomCapacity(1);
        Optional<Hotel> ofResult = Optional.of(hotel);
        doNothing().when(hotelRepository).delete(Mockito.<Hotel>any());
        when(hotelRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(roomService.deleteAllRooms(Mockito.<Hotel>any())).thenReturn("Delete All Rooms");
        assertEquals("Hotel Hotel Name Deleted SuccessfullyDelete All Rooms", hotelServiceImpl.deleteHotels(1L));
        verify(hotelRepository).findById(Mockito.<Long>any());
        verify(hotelRepository).delete(Mockito.<Hotel>any());
        verify(roomService).deleteAllRooms(Mockito.<Hotel>any());
    }

    @Test
    void testDeleteHotels2() {
        Address address = new Address();
        address.setCity("Oxford");
        address.setState("MD");
        address.setStreetAddress("42 Main St");
        address.setZipCode("21654");

        Hotel hotel = new Hotel();
        hotel.setAboutHotel("About Hotel");
        hotel.setAddress(address);
        hotel.setContactNo("Contact No");
        hotel.setHotelAmenities("Hotel Amenities");
        hotel.setHotelId(1L);
        hotel.setHotelName("Hotel Name");
        hotel.setHotelType(HotelType.TWO_STAR);
        hotel.setRoomCapacity(1);
        Optional<Hotel> ofResult = Optional.of(hotel);
        when(hotelRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(roomService.deleteAllRooms(Mockito.<Hotel>any()))
                .thenThrow(new ResourceNotFoundException("Deleted Successfully"));
        assertThrows(ResourceNotFoundException.class, () -> hotelServiceImpl.deleteHotels(1L));
        verify(hotelRepository).findById(Mockito.<Long>any());
        verify(roomService).deleteAllRooms(Mockito.<Hotel>any());
    }

    @Test
    void testDeleteHotels3() {
        Optional<Hotel> emptyResult = Optional.empty();
        when(hotelRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> hotelServiceImpl.deleteHotels(1L));
        verify(hotelRepository).findById(Mockito.<Long>any());
    }


    @Test
    void testGetAllCities() {
        ArrayList<String> stringList = new ArrayList<>();
        when(hotelRepository.findAllCities()).thenReturn(stringList);
        List<String> actualAllCities = hotelServiceImpl.getAllCities();
        assertSame(stringList, actualAllCities);
        assertTrue(actualAllCities.isEmpty());
        verify(hotelRepository).findAllCities();
    }

    @Test
    void testGetAllCities2() {
        when(hotelRepository.findAllCities()).thenThrow(new ResourceNotFoundException("Msg"));
        assertThrows(ResourceNotFoundException.class, () -> hotelServiceImpl.getAllCities());
        verify(hotelRepository).findAllCities();
    }
}

