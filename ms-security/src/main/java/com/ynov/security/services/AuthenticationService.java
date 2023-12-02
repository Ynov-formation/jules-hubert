package com.ynov.security.services;

import com.ynov.security.dao.TokenRepository;
import com.ynov.security.dao.UserRepository;
import com.ynov.security.dto.TokenDto;
import com.ynov.security.dto.UserLightDto;
import com.ynov.security.entities.TokenEntity;
import com.ynov.security.entities.TokenType;
import com.ynov.security.entities.UserEntity;
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
            saveUserToken(user, jwtToken);
            return TokenDto
                    .builder()
                    .token(jwtToken)
                    .build();
        }
        return null;
    }

    // save user's token on token table and set expired to fae and revoked to false
    private void saveUserToken(UserEntity user, String jwtToken) {
        var token = TokenEntity.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public boolean isTokenvalid(String token){
        String userName = jwtService.extractUsername(token);

        var user = userRepository.findByEmail(userName).orElseThrow();

        return jwtService.isTokenValid(token, user);
    }
}
