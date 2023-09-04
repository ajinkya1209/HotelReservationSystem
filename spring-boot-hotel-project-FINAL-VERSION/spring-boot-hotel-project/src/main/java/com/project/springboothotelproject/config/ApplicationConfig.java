package com.project.springboothotelproject.config;
  
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.project.springboothotelproject.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
  
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final CustomUserDetailsService customUserDetailsService;

  // Bean definition for creating and configuring a DaoAuthenticationProvider
  @Bean
  public AuthenticationProvider authenticationProvider() {
  DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
  authProvider.setUserDetailsService(customUserDetailsService);
  authProvider.setPasswordEncoder(passwordEncoder());
  return authProvider; 
  }

  // Bean definition for creating and configuring an AuthenticationManager
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
  return config.getAuthenticationManager(); 
  }

  // Bean definition for creating a BCryptPasswordEncoder instance
  @Bean public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder(); 
}
}
 