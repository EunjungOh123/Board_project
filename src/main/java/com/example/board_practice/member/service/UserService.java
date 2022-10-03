package com.example.board_practice.member.service;

import com.example.board_practice.member.dto.UserRegisterDto;

public interface UserService {
     void registerUser (UserRegisterDto registerDto);
     boolean emailAuth(String emailAuthKey);

}
