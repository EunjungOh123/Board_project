package com.example.board_practice.member.repository;

import com.example.board_practice.member.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId (String userId);
    boolean existsByNickname (String nickname);
    boolean existsByEmail (String email);
}
