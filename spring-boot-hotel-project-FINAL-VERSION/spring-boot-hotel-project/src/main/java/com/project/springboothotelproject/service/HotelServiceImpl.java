package com.project.springboothotelproject.service;

import com.project.springboothotelproject.enitites.Hotel;
import com.project.springboothotelproject.exceptionhandling.ResourceNotFoundException;
import com.project.springboothotelproject.payloads.HotelDto;
import com.project.springboothotelproject.repository.HotelRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HotelServiceImpl implements HotelService{
    @Autowired
    private HotelRepository hotelRepo;
    @Autowired
    private RoomService roomService;

    @Autowired
    private ModelMapper maper;

    //Adding a new Hotel
    @Override
    public String addHotels(HotelDto hotel) {
        String msg="Added successfully";
        Hotel h=maper.map(hotel, Hotel.class);
        hotelRepo.save(h);
        return ("Hotel "+h.getHotelName()+" "+msg);
    }

    //Retrieving all the details of Hotels
    @Override
    public List<HotelDto> getHotels() {
        List<Hotel> hotelList=hotelRepo.findAll();
        List<HotelDto> hotelDtoList=new ArrayList<>();
        for(Hotel h:hotelList)
        {
            hotelDtoList.add(maper.map(h, HotelDto.class));
        }
        return hotelDtoList;
    }

    //Delete Hotel as per hotel Id
    @Override
    public String deleteHotels(Long hotelId)
    {
        String msg="Deleted Successfully";
        Hotel hotel=hotelRepo.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Invalid Hotel Id!!"));
        msg+=roomService.deleteAllRooms(hotel);
        hotelRepo.delete(hotel);
        return ("Hotel "+hotel.getHotelName()+" "+msg);
    }

    //Edit all the hotel fields as per hotel id
    @Override
    public String editAllFields(Long hotelId,HotelDto hotelDto)
    {
        String msg="Updated Successfully";
        // Finding the hotel by ID or throwing a ResourceNotFoundException
        Hotel hotel=hotelRepo.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("Invalid Hotel Id!!"));

        // Updating hotel details using data from HotelDto
        Hotel updatedDetails=maper.map(hotelDto, Hotel.class);
        hotel.setHotelName(updatedDetails.getHotelName());
        hotel.setContactNo(updatedDetails.getContactNo());
        hotel.setHotelAmenities(updatedDetails.getHotelAmenities());
        hotel.setAboutHotel(updatedDetails.getAboutHotel());
        hotel.setHotelType(updatedDetails.getHotelType());
        hotel.setAddress(updatedDetails.getAddress());

        // Saving the updated hotel entity
        hotelRepo.save(hotel);

        return ("Hotel having id "+hotel.getHotelId()+" "+msg);
    }

    //Getting the hotel cities from the database
    @Override
    public List<String> getAllCities()
    {
        return hotelRepo.findAllCities();
    }
}
