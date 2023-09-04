package com.project.springboothotelproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    // Bean definition for creating a CorsFilter to handle Cross-Origin Resource Sharing (CORS)
    @Bean
    public CorsFilter corsFilter() {

        // Create a source for URL-based CORS configuration
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();

        // Create a new CorsConfiguration instance
        CorsConfiguration config=new CorsConfiguration();

        // Allow requests from "http://localhost:3000" origin
        config.addAllowedOrigin("http://localhost:3000");

        // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
        config.addAllowedMethod("*");

        // Allow all request headers
        config.addAllowedHeader("*");

        // Allow cookies to be sent with cross-origin requests
        config.setAllowCredentials(true);

        // Register the CorsConfiguration to be applied to all paths
        source.registerCorsConfiguration("/**",config);

        // Create and return a CorsFilter instance with the configured source
        return new CorsFilter(source);
    }
}
