package com.example.board_practice.member.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
@RequiredArgsConstructor
@Component
public class MailSendService {

    private final JavaMailSender javaMailSender;
    private final String from = "qazwsxedc4084@gmail.com";

    public void sendMail (String email, String subject, String text) {
        final MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                messageHelper.setFrom(from);
                messageHelper.setTo(email);
                messageHelper.setSubject(subject);
                messageHelper.setText(text, true);
            }
        };
        try {
            javaMailSender.send(messagePreparator);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String createRegisterTextMessage (String userId, String emailAuthKey) {
        String text = "";
        text += "<h2 style=\"color: #2e6c80;\">";
        text += userId + "님 회원가입을 축하드립니다!</h2>";
        text += "<h4>원활한 사이트 이용을 위해 이메일 인증을 완료해주세요.</h4>" +
                "<p>아래 링크를 클릭하면 인증이 완료됩니다.</p>";
        text += "<div><a href=\"http://localhost:8080/member/email-auth?id=";
        text += emailAuthKey +"\">가입 완료</a></div>";
        return text;
    }
    public String TemporaryPasswordTextMessage (String userId, String temporaryPassword) {
        String text = "";
        text += "<h2 style=\"color: #2e6c80;\">"+userId +"님 안녕하세요.</h2>";
        text += "<h3>비밀번호 재발급을 위한 메일입니다.</h3>";
        text += "<h3>임시 비밀번호 : " +temporaryPassword + "</h3>";
        text += "<h4>사이트에서 임시 비밀번호를 통해 로그인 후 비밀번호를 변경해주세요.</h4>";
        return text;
    }
}
