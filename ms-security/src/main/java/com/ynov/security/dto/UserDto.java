package com.ynov.security.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.ynov.security.entities.User}
 */
@Value
public class UserDto implements Serializable {
    String username;
    String password;
}