package com.example.msuser.controller;

import com.example.msuser.dto.UserDto;
import com.example.msuser.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID userId) {

        UserDto userDto = userService.getUserById(userId);

        return userDto == null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.ok(userDto);
    }

    @PostMapping
    @ApiOperation(value = "save a new user and return its ID", response = UUID.class)
    public ResponseEntity<UUID> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
    }

}