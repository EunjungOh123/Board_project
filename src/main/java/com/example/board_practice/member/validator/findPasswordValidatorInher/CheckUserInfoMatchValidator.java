package com.example.board_practice.member.validator.findPasswordValidatorInher;

import com.example.board_practice.member.dto.FindPasswordDto;
import com.example.board_practice.member.entity.SiteUser;
import com.example.board_practice.member.repository.UserRepository;
import com.example.board_practice.member.type.ErrorCode;
import com.example.board_practice.member.validator.FindPasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CheckUserInfoMatchValidator extends FindPasswordValidator<FindPasswordDto> {

    private final UserRepository userRepository;

    @Override
    protected void doValidate(FindPasswordDto dto, Errors errors) {
        Optional<SiteUser> optionalSiteUser
                = userRepository.findByUserIdAndEmailAndName(dto.getUserId(), dto.getEmail(), dto.getName());

        if(!optionalSiteUser.isPresent()) {
            errors.rejectValue("userId", "입력 오류", ErrorCode.CANNOT_FIND_PASSWORD.getDescription());
        }
    }
}
