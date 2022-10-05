package com.example.board_practice.member.validator.registerValidatorInher;

import com.example.board_practice.member.dto.UserRegisterDto;
import com.example.board_practice.member.repository.UserRepository;
import com.example.board_practice.member.type.ErrorCode;
import com.example.board_practice.member.validator.RegisterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckNicknameValidator extends RegisterValidator<UserRegisterDto> {

    private final UserRepository userRepository;
    @Override
    protected void doValidate(UserRegisterDto dto, Errors errors) {
        if(userRepository.existsByNickname(dto.toEntity().getNickname())) {
            errors.rejectValue("nickname","닉네임 중복 오류",
                    ErrorCode.NICKNAME_ALREADY_USE.getDescription());
        }
    }
}
