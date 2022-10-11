package com.example.board_practice.member.exception;

public class ResetPasswordException extends RuntimeException{
    public ResetPasswordException(String error) {
        super(error);
    }
}
