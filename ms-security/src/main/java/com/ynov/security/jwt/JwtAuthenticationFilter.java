package com.ynov.security.jwt;

import com.ynov.security.dao.TokenRepository;
import com.ynov.security.exception.InvalidTokenException;
import com.ynov.security.services.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(
         @NonNull HttpServletRequest request,
         @NonNull HttpServletResponse response,
         @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
    	
    	log.info("-->"+request.getRequestURL());
    	log.info("-->"+request.getLocalAddr());
    	log.info("-->"+request.getServerName());
    	
        if (request.getServletPath().contains("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        String userLogin = null;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        try {
            //extract username from a token
            userLogin = jwtService.extractUsername(jwt);
        }catch (ExpiredJwtException ex){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        if (userLogin != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userLogin);
            Boolean isTokenValid = null;
            //verify if token is not expired and not revoked
                isTokenValid = tokenRepository.findByToken(jwt)
                        .map(t -> !t.isExpired() && !t.isRevoked())
                        .orElse(false);

            if(!isTokenValid){
               InvalidTokenException ex = new InvalidTokenException("invalid token");
               try {
                   throw ex;
               } catch (InvalidTokenException exp) {
                   response.setStatus(HttpStatus.UNAUTHORIZED.value());
               }
            }
            //Implementation of interface Authentication which extends the interface Principal
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
