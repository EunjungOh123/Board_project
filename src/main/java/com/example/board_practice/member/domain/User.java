package com.example.board_practice.member.domain;

import com.example.board_practice.member.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Table
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    private String userId;

    private String password;

    private String passwordCheck;

    private String name;

    @Column(unique = true)
    private String nickname;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private LocalDateTime registeredAt;
    private LocalDateTime updatedAt;
    private LocalDateTime emailAuthAt; // 이메일 인증 날짜

    private boolean emailAuthYn;
    private String emailAuthKey;
}
