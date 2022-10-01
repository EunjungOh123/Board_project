package com.example.board_practice.member.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USERID_ALREADY_USE("이미 사용 중인 아이디입니다."),
    NICKNAME_ALREADY_USE("이미 사용 중인 닉네임입니다."),
    EMAIL_ALREADY_USE("이미 가입된 이메일입니다.");

    private final String description;
}
