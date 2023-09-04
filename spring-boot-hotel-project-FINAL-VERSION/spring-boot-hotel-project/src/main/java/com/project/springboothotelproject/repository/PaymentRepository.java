package com.project.springboothotelproject.repository;

import com.project.springboothotelproject.enitites.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByRoomId(Long roomId);
}
