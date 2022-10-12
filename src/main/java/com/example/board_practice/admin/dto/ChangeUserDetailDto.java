package com.example.board_practice.admin.dto;

import lombok.Data;

@Data
public class ChangeUserDetailDto {

    private String userId;
    private String name;
    private String userStatus;

    private String password;
    private String newPassword;

}
