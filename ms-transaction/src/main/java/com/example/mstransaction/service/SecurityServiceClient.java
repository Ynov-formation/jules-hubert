package com.example.mstransaction.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-security", url = "http://localhost:8083")
public interface SecurityServiceClient {

    @GetMapping("/valid-token")
    ResponseEntity<?> getIsTokenValid(@RequestParam String token);

}
