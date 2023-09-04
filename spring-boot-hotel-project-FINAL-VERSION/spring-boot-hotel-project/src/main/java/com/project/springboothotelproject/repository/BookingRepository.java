package com.project.springboothotelproject.repository;

import com.project.springboothotelproject.enitites.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BookingRepository extends JpaRepository<Booking,String> {
    Optional<Booking> findByGuestGuestId(Long guestId);

    Optional<List<Booking>> findAllByGuestGuestId(Long guestId);

    Booking findByRoomId(Long id);
}
