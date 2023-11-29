package com.example.msuser.service;

import com.example.msuser.dao.UserRepository;
import com.example.msuser.dto.UserDto;
import com.example.msuser.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private static final UserMapper userMapper = UserMapper.instance;

    public UUID saveUser(UserDto userDto) {
        return Optional.of(userDto)
                .map(userMapper::toUser)
                .map(userRepository::save)
                .map(savedUser -> savedUser.getId())
                .orElseThrow();
    }

    public UserDto getUserById(UUID userId){
        return userRepository.findById(userId)
                .map(userMapper::toUserDto)
                .orElse(null);
    }
}
