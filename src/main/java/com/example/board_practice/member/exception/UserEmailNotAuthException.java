package com.example.board_practice.member.exception;

public class UserEmailNotAuthException extends RuntimeException {
    public UserEmailNotAuthException(String error) {
        super(error);
    }
}
