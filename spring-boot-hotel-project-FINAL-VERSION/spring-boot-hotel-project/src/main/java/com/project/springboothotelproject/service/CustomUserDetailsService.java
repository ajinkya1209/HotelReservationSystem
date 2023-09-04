package com.project.springboothotelproject.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.springboothotelproject.enitites.Admin;
import com.project.springboothotelproject.enitites.Guest;
import com.project.springboothotelproject.repository.AdminRepository;
import com.project.springboothotelproject.repository.GuestRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private GuestRepository guestRepository;
	@Autowired
	private AdminRepository adminRepository;

	//Method to fetch user details based on the provided username.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Check if the username corresponds to an admin
		Optional<Admin> adminOptional=adminRepository.findByAdminEmail(username);
		if(adminOptional.isPresent()) {
			Admin admin=adminOptional.get();
			// Return a User object with admin username, password, and ROLE_ADMIN authority
			return new User(admin.getUsername(),admin.getPassword(),Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
		}

		// Check if the username corresponds to a guest
		Optional<Guest> guestOptional=guestRepository.findByEmail(username);
		if(guestOptional.isPresent()) {
			Guest guest=guestOptional.get();
			// Return a User object with guest's username, password, and ROLE_GUEST authority
			return new User(guest.getEmail(),guest.getPassword(),Collections.singletonList(new SimpleGrantedAuthority("ROLE_GUEST")));
		}

		// If no user is found, throw an exception
		throw new UsernameNotFoundException("User not found with username: "+username);
	}
}
