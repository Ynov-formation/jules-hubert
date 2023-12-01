package com.ynov.security.controller;

import com.ynov.security.dto.TokenDto;
import com.ynov.security.dto.UserLightDto;
import com.ynov.security.services.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/authenticate")
    // authentication end point
    public ResponseEntity<Object> authenticate(@RequestBody UserLightDto request) {
        log.info("Appel à l'API /api/auth/authenticate " + request.getUsername());
        try {
            TokenDto response = authenticationService.authenticate(request);
            return response != null ? ResponseEntity.ok(response)
                    : ResponseEntity.status(403).body("Account has been blocked, Retry in 15 Minutes ");
        } catch (Exception e) {
            return ResponseEntity.status(409).body(e);
        }
    }

    @GetMapping("/valid-token")
    public ResponseEntity<?> isTokenValid(@RequestBody TokenDto token) {

        boolean isValid = authenticationService.isTokenvalid(token.getToken());

        if (!isValid){
            return new ResponseEntity<>("invalid token", HttpStatus.UNAUTHORIZED);
        }

        return ResponseEntity.ok(true);
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public String test(){
        return "test";
    }

}
