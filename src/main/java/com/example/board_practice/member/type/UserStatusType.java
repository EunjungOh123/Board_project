package com.example.board_practice.member.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusType {

    /**
     * 현재 가입 요청중
     */

    MEMBER_STATUS_REQ("REQUEST"),

    /**
     * 현재 이용중인 상태
     */

    MEMBER_STATUS_AVAILABLE("AVAILABLE"),

    /**
     * 현재 정지된 상태
     */

    MEMBER_STATUS_STOP("STOP");

    private final String description;
}
