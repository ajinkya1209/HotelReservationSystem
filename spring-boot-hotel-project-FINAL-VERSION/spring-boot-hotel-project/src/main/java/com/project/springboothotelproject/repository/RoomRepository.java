package com.project.springboothotelproject.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.springboothotelproject.enitites.Room;
@Repository
public interface RoomRepository extends JpaRepository<Room,Long> {
    Optional<List<Room>> findAllByHotelHotelId(Long hotelId);
    public default Long findSumofRoomsByHotelId(Long hotelId) {
        return findAll().stream()
                .filter(room -> room.getHotel().getHotelId().equals(hotelId))
                .count();
    }
}
