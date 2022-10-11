package com.example.board_practice.member.dto;

import lombok.*;

/* 비밀번호 재발급을 위한 본인 인증 데이터 전달에 활용될 객체 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindPasswordDto {

    private String userId;

    private String name;

    private String email;

    private String password;
    private String id;
}
