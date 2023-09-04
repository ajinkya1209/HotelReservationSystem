package com.project.springboothotelproject.service;

import com.project.springboothotelproject.enitites.Guest;
import com.project.springboothotelproject.exceptionhandling.ResourceNotFoundException;
import com.project.springboothotelproject.payloads.GuestDto;
import com.project.springboothotelproject.payloads.UpdateGuestDto;
import com.project.springboothotelproject.repository.GuestRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GuestServiceImpl implements GuestService {
    @Autowired
    private GuestRepository guestRepo;
    @Autowired
    private ModelMapper maper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Add new guest based on provided GuestDto
    @Override
    public String addGuest(GuestDto guestDto) {

        // Mapping GuestDto to Guest entity
       Guest guest=maper.map(guestDto, Guest.class);
        // Encoding the guest's password before saving
       guest.setPassword(passwordEncoder.encode(guestDto.getPassword()));
       guestRepo.save(guest);
       return  ("Guest id "+guest.getGuestId()+" Added successfully");
    }

    // Retrieves a list of all guests
    @Override
    public List<GuestDto> getAllGuests() {
        List<Guest> list=guestRepo.findAll();
        List<GuestDto> guestLists=new ArrayList<>();
        for(Guest g:list)
        {
            guestLists.add(maper.map(g,GuestDto.class));
        }

        return guestLists;
    }

    // Updating the details of particular guest as per guest id
    @Override
    public String updateGuest(Long guestId, UpdateGuestDto guestDto) {

        // Finding the guest by ID or throwing a ResourceNotFoundException
        Guest guest=guestRepo.findById(guestId)
                .orElseThrow(()-> new ResourceNotFoundException("Invalid Guest Id"));

        // Updating guest details using data from UpdateGuestDto
        guest.setFirstName(guestDto.getFirstName());
        guest.setLastName(guestDto.getLastName());
        guest.setAge(guestDto.getAge());
        guest.setGender(guestDto.getGender());
        guest.setAddress(guestDto.getAddress());
        guest.setContactNo(guestDto.getContactNo());

        // Saving the updated guest entity
        guestRepo.save(guest);

        return ("Guest having ID "+guest.getGuestId()+" updated successfully");
    }
}
