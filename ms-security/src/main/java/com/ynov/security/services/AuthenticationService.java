package com.ynov.security.services;

import com.ynov.security.dao.TokenRepository;
import com.ynov.security.dao.UserRepository;
import com.ynov.security.dto.TokenDto;
import com.ynov.security.dto.UserLightDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    //Authentication Method that generate an access token valid for 4 hours
    public TokenDto authenticate(UserLightDto request) {
        var user = userRepository.findByEmail(request.getUsername())
                .orElseThrow();

        //verify that the account is not locked after 5 login attempts
        if (user.isAccountNonLocked()) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            var jwtToken = jwtService.generateToken(user);
            return TokenDto
                    .builder()
                    .token(jwtToken)
                    .isValid(true)
                    .build();
        }
        return null;
    }

    public boolean isTokenvalid(String token){
        String userName = jwtService.extractUsername(token);

        var user = userRepository.findByEmail(userName).orElseThrow();

        return jwtService.isTokenValid(token, user);
    }
}
