package com.example.board_practice.admin.dto;

import com.example.board_practice.member.entity.SiteUser;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    String userId;
    String name;
    String email;
    String nickname;
    LocalDateTime registeredAt;

    boolean emailAuthYn;
    LocalDateTime emailAuthAt;

    String roleType;
    String userStatus;

    String resetPasswordKey;
    LocalDateTime resetPasswordLimitAt;

    long totalCount;
    long seq;

    public static UserDto fromEntity (SiteUser user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .registeredAt(user.getRegisteredAt())
                .emailAuthYn(user.isEmailAuthYn())
                .emailAuthAt(user.getEmailAuthAt())
                .resetPasswordKey(user.getResetPasswordKey())
                .resetPasswordLimitAt(user.getResetPasswordKeyLimitAt())
                .roleType(user.getRoleType().toString())
                .userStatus(user.getUserStatus())
                .build();
    }
}
