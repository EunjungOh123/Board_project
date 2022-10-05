package com.example.board_practice.member.validator.registerValidatorInher;

import com.example.board_practice.member.dto.UserRegisterDto;
import com.example.board_practice.member.type.ErrorCode;
import com.example.board_practice.member.validator.RegisterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckPasswordMatchValidator extends RegisterValidator<UserRegisterDto> {
    @Override
    protected void doValidate(UserRegisterDto dto, Errors errors) {
        if(!dto.getPassword().equals(dto.getPasswordCheck())) {
            errors.rejectValue("passwordCheck", "비밀번호 확인 오류",
                    ErrorCode.PASSWORD_PASSWORDCHECK_UNMATCH.getDescription());
        }
    }
}
