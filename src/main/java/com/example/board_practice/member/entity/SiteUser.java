package com.example.board_practice.member.entity;

import com.example.board_practice.baseEntity.TimeEntity;
import com.example.board_practice.member.type.RoleType;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
@Table(name = "user_info")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true) //  Chain 형태로 이어서 원하는 set 메서드를 생성
public class SiteUser extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    private String userId;

    private String password;

/*    private String passwordCheck;  유효성 검사에만 사용되므로 db에 저장될 필요는 없을 듯 */
    private String name;

    @Column(unique = true)
    private String nickname;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private LocalDateTime emailAuthAt; // 이메일 인증 날짜

    private boolean emailAuthYn;
    private String emailAuthKey;
}
