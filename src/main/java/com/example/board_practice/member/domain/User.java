package com.example.board_practice.member.domain;

import com.example.board_practice.member.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Table(name = "BoardUser_Info")
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
}
