package com.example.board_practice.member.service;

import com.example.board_practice.member.domain.User;
import com.example.board_practice.member.dto.UserRegisterDto;
import com.example.board_practice.member.repository.UserRepository;
import com.example.board_practice.member.type.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void registerUser (UserRegisterDto registerDto) {
        User user = registerDto.toEntity();
        user.setRegisteredAt(LocalDateTime.now());
        user.setRoleType(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public Map<String, String> validateRegisterHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        /* 회원가입 실패시 message 값들을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = "valid_" + error.getField();
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
}
