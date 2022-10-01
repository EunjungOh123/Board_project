package com.example.board_practice.member.exception;

import com.example.board_practice.member.type.ErrorCode;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserException extends RuntimeException{
    private ErrorCode errorCode;
    private String errorMessage;

    public UserException (ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
