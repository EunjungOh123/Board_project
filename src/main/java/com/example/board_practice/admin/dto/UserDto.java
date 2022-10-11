package com.example.board_practice.admin.dto;

import com.example.board_practice.member.entity.SiteUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    String roleType;

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
                .roleType(user.getRoleType().toString()).build();
    }
}
