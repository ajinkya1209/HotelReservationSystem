package com.project.springboothotelproject.repository;

import com.project.springboothotelproject.enitites.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByAdminEmail(String email);
}
