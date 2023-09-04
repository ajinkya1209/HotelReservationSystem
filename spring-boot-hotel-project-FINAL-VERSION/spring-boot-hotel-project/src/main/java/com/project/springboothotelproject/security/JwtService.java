package com.project.springboothotelproject.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.project.springboothotelproject.repository.GuestRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

//The JwtService class encapsulates the logic for working with JWT tokens
@Service
public class JwtService {
	@Autowired
	private GuestRepository guestRepo;
   /* @Value("${jwt.secret")
    private String SECRET_KEY;*/

    // Secret key used for signing and verifying JWT tokens
    private static final String SECRET_KEY="X2XaRPUdyuksLOXHQYl1Vayc0QX0pB1GGW76bOd3uV8=";

    // Expiration time of JWT tokens in milliseconds
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    // Extracts the username from a JWT token
    public String extractUserName(String token)
    {
        return extractClaim(token,Claims::getSubject);
    }

    // Generates a JWT token based on UserDetails
    public String generateToken(UserDetails userDetails)
    {
        return generateToken(new HashMap<>(),userDetails);
    }

    // Generates a JWT token with additional claims and UserDetails
    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    )
    {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact(); //compact is the method which will generate token
    }

    // Validates if a JWT token is valid for a given UserDetails
    public boolean isTokenValid(String token,UserDetails userDetails)
    {
        final String userName=extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    // Checks if a JWT token has expired
    private boolean isTokenExpired(String token) {
        return  extractExpiration(token).before(new Date());
    }

    // Extracts the expiration date from a JWT token
    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    // Extracts a specific claim from a JWT token
    public <T> T extractClaim(String token, Function<Claims,T> claimResolver)
    {
        final Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    // Extracts all claims from a JWT token
    private Claims extractAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extracts the authority (role) from UserDetails
    public String extractAuthority(UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String authorityName=null;
        for (GrantedAuthority authority : authorities) {
             authorityName = authority.getAuthority();
        }
        return authorityName;
    }

    // Gets the signing key for JWT token validation and generation
    private Key getSignInKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
   
}
