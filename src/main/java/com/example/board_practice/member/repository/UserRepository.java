package com.example.board_practice.member.repository;

import com.example.board_practice.member.domain.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    boolean existsByUserId (String userId);
    boolean existsByNickname (String nickname);
    boolean existsByEmail (String email);
    Optional<SiteUser> findByEmailAuthKey (String emailAuthKey);
    Optional<SiteUser> findByUserId (String userId);
}
