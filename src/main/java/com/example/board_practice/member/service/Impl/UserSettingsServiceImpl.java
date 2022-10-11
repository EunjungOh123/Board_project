package com.example.board_practice.member.service.Impl;

import com.example.board_practice.member.dto.FindPasswordDto;
import com.example.board_practice.member.entity.SiteUser;
import com.example.board_practice.member.exception.ResetPasswordException;
import com.example.board_practice.member.mail.MailSendService;
import com.example.board_practice.member.repository.UserRepository;
import com.example.board_practice.member.service.ValidateHandling;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/* 비밀번호 변경 위해서 인증키 담긴 메일 보내기 */

@Service
@RequiredArgsConstructor
public class UserSettingsServiceImpl extends ValidateHandling {
    private final MailSendService mailSendService;
    private final UserRepository userRepository;

    /**
     * 본인 인증 후 비밀번호 재설정을 위한 링크 이메일로 전송
     */
    @Transactional
    public void sendResetPasswordKey(FindPasswordDto passwordDto) {

        String userId = passwordDto.getUserId();
        String email = passwordDto.getEmail();
        String resetPasswordKey = UUID.randomUUID().toString();

        String subject = "[회원 비밀번호 분실] 비밀번호 재설정을 위한 인증 완료해주세요.";
        String text = mailSendService.resetPasswordTextMessage(userId, resetPasswordKey);
        mailSendService.sendMail(email,subject, text);

        SiteUser user = userRepository.findByUserIdAndEmailAndName
                (passwordDto.getUserId(),passwordDto.getEmail(),passwordDto.getName()).get();


        user.setResetPasswordKey(resetPasswordKey)
                .setResetPasswordKeyLimitAt(LocalDateTime.now().plusDays(1));

        userRepository.save(user);
    }
    /**
     * 입력 받은 uuid 대해서 password로 초기화
     */
    public boolean resetPassword (String uuid, String password) {
        Optional<SiteUser> optionalUser = userRepository.findByResetPasswordKey(uuid);
        if (!optionalUser.isPresent()) {
            throw new ResetPasswordException("일치하는 정보가 없습니다.");
        }

        SiteUser user = optionalUser.get();

        if(user.getResetPasswordKeyLimitAt() == null) {
            throw new ResetPasswordException("유효한 날짜가 아닙니다.");
        } else if (user.getResetPasswordKeyLimitAt().isBefore(LocalDateTime.now())) {
            throw new ResetPasswordException("유효한 날짜가 아닙니다.");
        }

        String encPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPassword(encPassword);
        userRepository.save(user);
        return true;
    }
    /**
     * 입력 받은 uuid 값이 유효한지 확인
     */
    public boolean checkResetPasswordKey (String uuid) {
        Optional<SiteUser> optionalUser = userRepository.findByResetPasswordKey(uuid);
        if (!optionalUser.isPresent()) {
            return false;
        }
        SiteUser user = optionalUser.get();

        if(user.getResetPasswordKeyLimitAt() == null) {
            return false;
        }
        if (user.getResetPasswordKeyLimitAt().isBefore(LocalDateTime.now())) {
            return false;
        }
        return true;
    }
}
