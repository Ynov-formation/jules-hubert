package com.ynov.security.web;

import com.ynov.security.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ynov.security.services.JwtService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SecurityRestRessources {

    private JwtService jwtService;


    public SecurityRestRessources(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @PostMapping("/token")
    public ResponseEntity<Map<String,String>> getToken(@RequestBody UserDto userDto){
        Map<String,String> token = new HashMap<>();

        token.put("acess_token",jwtService.generateToken(userDto.getUsername(),userDto.getPassword()));
        return ResponseEntity.ok(token);
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public String test(){
        return "test";
    }

}
