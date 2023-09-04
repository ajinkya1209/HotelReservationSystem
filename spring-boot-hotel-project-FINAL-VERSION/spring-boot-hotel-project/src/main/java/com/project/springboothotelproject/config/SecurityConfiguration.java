package com.project.springboothotelproject.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.project.springboothotelproject.security.JwtAuthenticationEntryPoint;
import com.project.springboothotelproject.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Import(CorsConfig.class)
public class SecurityConfiguration {
    private final JwtAuthenticationEntryPoint point;
	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final CorsConfigurationSource corsConfigurationSource; // Importing the CorsConfig for CORS configuration

	// Bean definition for creating a SecurityFilterChain
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                    auth ->auth
                           .requestMatchers(
                                    new AntPathRequestMatcher("/hotel/**")).hasRole("ADMIN")
                           .requestMatchers(
                                   new AntPathRequestMatcher("/room/**")).hasRole("ADMIN")
                           
                            .requestMatchers(
                                    new AntPathRequestMatcher("/booking/**")).hasRole("GUEST")
                            .requestMatchers(
                                    new AntPathRequestMatcher("/payment/**")).hasRole("GUEST")
                             .requestMatchers(
                                    new AntPathRequestMatcher("/authenticate/login")).permitAll()
							.requestMatchers(
									new AntPathRequestMatcher("/admin/add-admin")).permitAll()
							.requestMatchers(
                            		 new AntPathRequestMatcher("/guest/**")).permitAll()
                             .anyRequest().authenticated())
            .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
			.cors(cors -> cors.configurationSource(corsConfigurationSource))
    ;
    return http.build();
	}
}
