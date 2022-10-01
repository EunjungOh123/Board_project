package com.example.board_practice.member.service;

import com.example.board_practice.member.domain.User;
import com.example.board_practice.member.dto.UserRegisterDto;
import com.example.board_practice.member.exception.UserException;
import com.example.board_practice.member.repository.UserRepository;
import com.example.board_practice.member.type.ErrorCode;
import com.example.board_practice.member.type.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void signupUser (UserRegisterDto registerDto) {
        validateSignupUser(registerDto);
        User user = registerDto.toEntity();
        user.setRegisteredAt(LocalDateTime.now());
        user.setRoleType(RoleType.USER);
        userRepository.save(user);
    }
    private void validateSignupUser (UserRegisterDto registerDto) {
        if(userRepository.existsByUserId(registerDto.getUserId()) == true) {
            throw new UserException(ErrorCode.USERID_ALREADY_USE);
        }
        if(userRepository.existsByNickname(registerDto.getNickname()) == true) {
            throw new UserException(ErrorCode.NICKNAME_ALREADY_USE);
        }
        if(userRepository.existsByEmail(registerDto.getEmail()) == true) {
            throw new UserException(ErrorCode.EMAIL_ALREADY_USE);
        }
    }
}
