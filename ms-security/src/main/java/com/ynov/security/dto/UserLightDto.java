package com.ynov.security.dto;

import com.ynov.security.entities.UserEntity;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link UserEntity}
 */
@Value
public class UserLightDto implements Serializable {
    String username;
    String password;
}