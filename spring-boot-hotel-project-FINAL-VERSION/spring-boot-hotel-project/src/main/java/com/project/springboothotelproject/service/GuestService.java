package com.project.springboothotelproject.service;

import com.project.springboothotelproject.payloads.GuestDto;
import com.project.springboothotelproject.payloads.UpdateGuestDto;

import java.util.List;
public interface GuestService {
    String addGuest(GuestDto guestDto);
    List<GuestDto> getAllGuests();
    String updateGuest(Long guestId, UpdateGuestDto guestDto);
}
