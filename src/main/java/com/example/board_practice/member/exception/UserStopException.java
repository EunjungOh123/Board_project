package com.example.board_practice.member.exception;

public class UserStopException extends RuntimeException{
    public UserStopException(String error) {
        super(error);
    }
}
