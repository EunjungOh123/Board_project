package com.example.board_practice.member.service.Impl;

import com.example.board_practice.member.domain.SiteUser;
import com.example.board_practice.member.dto.UserRegisterDto;
import com.example.board_practice.member.mail.MailSendService;
import com.example.board_practice.member.repository.UserRepository;
import com.example.board_practice.member.service.UserService;
import com.example.board_practice.member.type.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailSendService mailSendService;

    @Override
    @Transactional
    public void registerUser (UserRegisterDto registerDto) {
        SiteUser user = registerDto.toEntity();
        String encPassword = BCrypt.hashpw(registerDto.getPassword(), BCrypt.gensalt());
        user.setRoleType(RoleType.GUEST)
                .setEmailAuthYn(false)
                .setPassword(encPassword)
                .setEmailAuthKey(UUID.randomUUID().toString());

        userRepository.save(user);

        String text = mailSendService.createTextMessage(registerDto.getUserId(), user.getEmailAuthKey());
        mailSendService.sendMail(registerDto.getEmail(), text);
    }
    @Override
    public boolean emailAuth(String emailAuthKey) {
        Optional<SiteUser> optionalUser =  userRepository.findByEmailAuthKey(emailAuthKey);
        if(!optionalUser.isPresent()) {
            return false;
        }
        SiteUser user = optionalUser.get();
        user.setEmailAuthYn(true).
                setEmailAuthAt(LocalDateTime.now())
                        .setRoleType(RoleType.USER);
        userRepository.save(user);
        return true;
    }

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
