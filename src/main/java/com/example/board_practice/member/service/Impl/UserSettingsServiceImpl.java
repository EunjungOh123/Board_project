package com.example.board_practice.member.service.Impl;

import com.example.board_practice.member.dto.ForIdentificationDto;
import com.example.board_practice.member.mail.MailSendService;
import com.example.board_practice.member.service.ValidateHandling;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* 비밀번호 변경 위해서 인증키 담긴 메일 보내기 */

@Service
@RequiredArgsConstructor
public class UserSettingsServiceImpl implements ValidateHandling {
    private final MailSendService mailSendService;

    @Transactional
    public void sendTemporaryPassword(ForIdentificationDto passwordDto) {

        String userId = passwordDto.getUserId();
        String email = passwordDto.getEmail();
        String temporaryPassword = UUID.randomUUID().toString();

        String subject = "[회원 비밀번호 변경] 임시 비밀번호를 확인해주세요.";
        String text = mailSendService.TemporaryPasswordTextMessage(userId, temporaryPassword);
        mailSendService.sendMail(email,subject, text);
    }

    @Override
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        /* 본인 인증 실패시 message 값들을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = "valid_" + error.getField();
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }
}
