/*
package com.shawbindro.userservice.user.config.security;

import com.shawbindro.userservice.user.config.security.util.JwtUtility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
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
import java.util.List;
import java.util.stream.Collectors;

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

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            // 🔥 STEP 1: Parse FIRST (this throws exception if expired)
            Claims claims = jwtUtility.extractAllClaims(token);

            String userId = claims.get("userId", String.class);
            String email = claims.getSubject();

            List<String> roles = claims.get("authorities", List.class);

            List<GrantedAuthority> authorities = roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            if (roles == null || roles.isEmpty()) {
                roles = List.of("ROLE_CUSTOMER");
            }

            CustomUserDetails userDetails =
                    new CustomUserDetails(userId, email, authorities);

            // 🔥 STEP 2: Now validation (safe)
            if (jwtUtility.isTokenValid(token)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(authToken);

                log.info("Auth set for user: " + email);
            }

        } catch (ExpiredJwtException e) {
            log.warn("Token expired");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // ✅ KEY
            return;

        } catch (Exception e) {
            log.error("Invalid token");

            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        filterChain.doFilter(request, response);
    }

}


*/
