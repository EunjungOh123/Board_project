package com.example.board_practice.member.controller;

import com.example.board_practice.member.dto.UserRegisterDto;
import com.example.board_practice.member.exception.UserException;
import com.example.board_practice.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(@Valid UserRegisterDto registerDto, Errors errors, Model model) {
        try {
            /* post 요청시 넘어온 user 입력값에서 Validation에 걸리는 경우 */
            if (errors.hasErrors()) {
                /* 회원가입 실패시 입력 데이터 유지 */
                model.addAttribute("registerDto", registerDto);
                /* 회원가입 실패시 message 값들을 모델에 매핑해서 뷰로 전달 */
                Map <String, String> validateMap = new HashMap<>();

                for (FieldError error : errors.getFieldErrors()) {
                    String validKeyName = "valid_" + error.getField();
                    validateMap.put(validKeyName, error.getDefaultMessage());
                }

                // map.keySet() -> 모든 key 값 가져온다
                // 가져온 키로 반복문을 통해 키와 에러 메세지로 매핑
                for (String key : validateMap.keySet()) {
                    model.addAttribute(key, validateMap.get(key));
                }
                return "member/register";
            }
            userService.signupUser(registerDto);
        } catch (UserException e) {
            model.addAttribute("message",e.getErrorMessage());
            return "member/register_fail";
        }
        return "member/register_success";
    }

    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }
}
