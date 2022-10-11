package com.example.board_practice.member.service;

import com.example.board_practice.admin.dto.UserDto;
import com.example.board_practice.admin.model.UserParam;
import com.example.board_practice.member.dto.UserRegisterDto;

import java.util.List;

public interface UserService {

     /**
      * 회원 가입 (유효성 검사로 중복 체크)
      */
     void registerUser (UserRegisterDto registerDto);

     /**
      * 이메일 인증키를 통해 계정을 활성화 (GUEST > USER)
      */
     boolean emailAuth(String emailAuthKey);

     /**
      * 회원 목록 리턴(관리자에서만 사용 가능)
      */
     List<UserDto> list (UserParam param);

}
