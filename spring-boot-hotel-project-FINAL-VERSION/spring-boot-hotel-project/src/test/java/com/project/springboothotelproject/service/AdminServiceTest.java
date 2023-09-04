package com.project.springboothotelproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.springboothotelproject.enitites.Admin;
import com.project.springboothotelproject.payloads.AdminDto;
import com.project.springboothotelproject.repository.AdminRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AdminService.class})
@ExtendWith(SpringExtension.class)
class AdminServiceTest {
    @MockBean
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;


    @Test
    void testAddAdmin() {
        Admin admin = new Admin();
        admin.setAdminEmail("jane.doe@example.org");
        admin.setAdminId(1L);
        admin.setAdminName("Admin");
        admin.setAdminPassword("Admin123");
        when(adminRepository.save(Mockito.<Admin>any())).thenReturn(admin);

        Admin admin2 = new Admin();
        admin2.setAdminEmail("jake.doe@example.org");
        admin2.setAdminId(1L);
        admin2.setAdminName("Admin");
        admin2.setAdminPassword("Admin12345");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Admin>>any())).thenReturn(admin2);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        assertEquals("Admin having name Admin added successfully",
                adminService.addAdmin(new AdminDto("Admin", "jane.doe@example.org", "Admin12345")));
        verify(adminRepository).save(Mockito.<Admin>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Admin>>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    @Test
    void testAddAdmin2() {
        Admin admin = new Admin();
        admin.setAdminEmail("raj@example.org");
        admin.setAdminId(2L);
        admin.setAdminName("Raj");
        admin.setAdminPassword("Raj123");
        when(adminRepository.save(Mockito.<Admin>any())).thenReturn(admin);

        Admin admin2 = new Admin();
        admin2.setAdminEmail("rajudoe@example.org");
        admin2.setAdminId(2L);
        admin2.setAdminName("Raju");
        admin2.setAdminPassword("Raju123");
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Admin>>any())).thenReturn(admin2);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        assertEquals("Admin having name Raju added successfully",
        adminService.addAdmin(new AdminDto("Raj", "raj@example.org", "Raj123")));
        verify(adminRepository).save(Mockito.<Admin>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Admin>>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }
}

