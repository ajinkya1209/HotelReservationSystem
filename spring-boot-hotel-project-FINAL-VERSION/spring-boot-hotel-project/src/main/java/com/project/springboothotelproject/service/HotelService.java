package com.project.springboothotelproject.service;

import com.project.springboothotelproject.payloads.HotelDto;
import java.util.List;

public interface HotelService {
    String addHotels(HotelDto hotel);
    List<HotelDto> getHotels();
    String deleteHotels(Long hotelId);
    String editAllFields(Long hotelId,HotelDto hotelDto);
    List<String> getAllCities();
}
