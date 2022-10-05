package com.example.board_practice.member.validator.forIdentificationValidatorInher;

import com.example.board_practice.member.dto.ForIdentificationDto;
import com.example.board_practice.member.entity.SiteUser;
import com.example.board_practice.member.repository.UserRepository;
import com.example.board_practice.member.type.ErrorCode;
import com.example.board_practice.member.validator.ForIdentificationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CheckUserInfoMatchValidator extends ForIdentificationValidator<ForIdentificationDto> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(ForIdentificationDto dto, Errors errors) {
        Optional<SiteUser> optionalUser1 = userRepository.findByUserId(dto.getUserId());
        Optional<SiteUser> optionalUser2 = userRepository.findByEmail(dto.getEmail());
        if (!optionalUser1.isPresent()) { // 아이디가 존재하지 않는다면
            errors.rejectValue("userId", "아이디 오류",
                    ErrorCode.CANNOT_FIND_PASSWORD.getDescription());
        } else if(!optionalUser2.isPresent()) { // 아이디는 존재하지만 이메일 입력이 잘못된 경우
            errors.rejectValue("email", "이메일 오류",
                    ErrorCode.CANNOT_FIND_PASSWORD_UNMATCH_EMAIL.getDescription());
        } else if (optionalUser1.get().getUserId().equals(optionalUser2.get().getUserId())) {
            errors.rejectValue("wrongInput", "입력 오류",
                    ErrorCode.CANNOT_FIND_PASSWORD.getDescription());
        }
    }
}
