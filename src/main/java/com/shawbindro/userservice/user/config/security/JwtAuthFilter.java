package com.shawbindro.userservice.user.config.security;

import com.shawbindro.userservice.user.config.security.util.JwtUtility;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtility jwtUtility;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        System.out.println(">>> SHOULD FILTER CHECK: " + request.getServletPath());
        return path.startsWith("/h2-console")
                || path.startsWith("/auth")
                || request.getMethod().equalsIgnoreCase("OPTIONS");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        log.info("JWT FILTER HIT");

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            boolean isValid = jwtUtility.isTokenValid(token);
            log.info("Valid: " + isValid);

            if (isValid) {
                Claims claims = jwtUtility.extractAllClaims(token);
                log.info("ALL CLAIMS: " + claims);

                String userId = jwtUtility.extractUserId(token);
                log.info("userId " + userId);


              String role = jwtUtility.extractRole(token);

                if (role == null || role.isBlank()) {
                    role = "USER"; // ✅ fallback
                }

                List<GrantedAuthority> authorities =
                        List.of(new SimpleGrantedAuthority("ROLE_" + role));

                User principal = new User(
                        userId,
                        "",
                        authorities
                );

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                principal,
                                null,
                                authorities
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);
                log.info("Auth Object: " + SecurityContextHolder.getContext().getAuthentication());
                log.info("Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            }
        }

        filterChain.doFilter(request, response);
    }
}


