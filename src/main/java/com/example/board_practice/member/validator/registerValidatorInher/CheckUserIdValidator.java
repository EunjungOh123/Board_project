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
public class CheckUserIdValidator extends RegisterValidator<UserRegisterDto> {

    private final UserRepository userRepository;
    @Override
    protected void doValidate(UserRegisterDto dto, Errors errors) {
        if(userRepository.existsByUserId(dto.toEntity().getUserId())) {
            errors.rejectValue("userId", "아이디 중복 오류",
                    ErrorCode.USERID_ALREADY_USE.getDescription());
        }
    }
}
