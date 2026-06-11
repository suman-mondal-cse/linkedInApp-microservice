package com.suman.linkedin.user_service.service;

import com.suman.linkedin.user_service.dto.LoginRequestDto;
import com.suman.linkedin.user_service.dto.SignupRequestDto;
import com.suman.linkedin.user_service.dto.UserDto;
import com.suman.linkedin.user_service.entity.User;
import com.suman.linkedin.user_service.exception.BadRequestException;
import com.suman.linkedin.user_service.exception.ResourceNotFoundException;
import com.suman.linkedin.user_service.repository.UserRepository;
import com.suman.linkedin.user_service.security.JWTService;
import com.suman.linkedin.user_service.utils.PasswordUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JWTService jwtService;

// signup a new user
    public UserDto signUp(SignupRequestDto signupRequestDto){
        boolean exists = userRepository.existsByEmail(signupRequestDto.getEmail());
        if (exists) throw new BadRequestException("User already exists, can't signup again.");

        User user = modelMapper.map(signupRequestDto, User.class);
        user.setPassword(PasswordUtil.hashPassword(signupRequestDto.getPassword()));

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

//    Login a user
    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()->
                new ResourceNotFoundException("User not found with email id: " +loginRequestDto.getEmail()));
        boolean isPasswordMatch = PasswordUtil.checkPassword(loginRequestDto.getPassword(), user.getPassword());

        if (!isPasswordMatch) throw new BadRequestException("Incorrect password.");

        return jwtService.generateAccessToken(user);

    }
}
