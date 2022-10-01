package com.example.board_practice.member.dto;

// 회원가입의 Form 데이터 전달에 활용할 객체

import com.example.board_practice.member.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterDto {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 2, max = 15, message = "아이디를 2~10자 사이로 입력해주세요.")
    private String userId;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{6,12}",
            message = "영문자와 숫자, 특수기호가 적어도 1개 이상 포함된 6자~12자여야 합니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인은 필수 입력 값입니다.")
    private String passwordCheck;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(min = 2, max = 8, message = "이름을 2~8자 사이로 입력해주세요.")
    private String name;
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Size(min = 2, max = 8, message = "닉네임을 2~8자 사이로 입력해주세요.")
    private String nickname;
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;

    public User toEntity () {
        return User.builder()
                .userId(userId)
                .password(password)
                .passwordCheck(passwordCheck)
                .name(name)
                .nickname(nickname)
                .email(email)
                .build();
    }
}