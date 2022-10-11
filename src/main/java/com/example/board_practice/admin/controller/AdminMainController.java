package com.example.board_practice.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminMainController {

    @GetMapping("/error/denied") // 관리자 권한 없는 경우 관리자 페이지 접속 시 발생하는 에러 페이지
    public String error() {
        return "error/denied";
    }

    @GetMapping("/admin/main") // 관리자 메인 페이지
    public String main() {
        return "admin/main";
    }
}
