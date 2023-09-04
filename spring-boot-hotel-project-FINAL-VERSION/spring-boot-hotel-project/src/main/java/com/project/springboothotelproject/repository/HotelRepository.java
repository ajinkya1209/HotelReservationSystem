package com.project.springboothotelproject.repository;

import com.project.springboothotelproject.enitites.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    public default List<String> findAllCities() {
        return findAll().stream()
                .map(hotel -> hotel.getAddress().getCity())
                .distinct()
                .collect(Collectors.toList());
    }
}
