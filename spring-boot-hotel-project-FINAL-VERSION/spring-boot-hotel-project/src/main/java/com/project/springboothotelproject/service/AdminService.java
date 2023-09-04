package com.project.springboothotelproject.service;

import com.project.springboothotelproject.enitites.Admin;
import com.project.springboothotelproject.payloads.AdminDto;
import com.project.springboothotelproject.repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper maper;

    // Adds a new admin based on provided AdminDto
    public String addAdmin(AdminDto adminDto)
    {
        // Map AdminDto to Admin entity
        Admin admin=maper.map(adminDto,Admin.class);

        // Encode and set the admin's password
        admin.setAdminPassword(passwordEncoder.encode(adminDto.getAdminPassword()));

        // Save the admin entity in the repository
        adminRepository.save(admin);

        return ("Admin having name "+admin.getAdminName()+" added successfully");
    }

    // Retrieves a list of all admins as AdminDto objects
    public List<AdminDto> getAllAdmin()
    {
        List<Admin> list=adminRepository.findAll();
        List<AdminDto> adminLists=new ArrayList<>();

        // Map each Admin entity to AdminDto and add to the list
        for(Admin a:list)
        {
            adminLists.add(maper.map(a,AdminDto.class));
        }

        return adminLists;
    }
}
