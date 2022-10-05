package com.example.board_practice.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/* 비밀번호 재발급을 위한 본인 인증 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForIdentificationDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String userId;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String email;

}
