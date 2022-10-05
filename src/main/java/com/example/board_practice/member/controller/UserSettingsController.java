package com.example.board_practice.member.controller;

import com.example.board_practice.member.dto.ForIdentificationDto;
import com.example.board_practice.member.service.Impl.UserSettingsServiceImpl;
import com.example.board_practice.member.validator.forIdentificationValidatorInher.CheckUserInfoMatchValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/* 비밀번호 재설정 */

@Controller
@RequiredArgsConstructor
public class UserSettingsController {

    private final UserSettingsServiceImpl userSettingsService;
    private final CheckUserInfoMatchValidator userInfoMatchValidator;

    @InitBinder // 특정 컨트롤러애서 바인딩 또는 검증 설정을 변경할 때 사용
    public void validatorBinder(WebDataBinder webDataBinder) {
        // addValidators 메서드를 사용하여 Validator 등록 > 해당 컨트롤러에서는 자동 적용
        webDataBinder.addValidators(userInfoMatchValidator);
    }

    @GetMapping("/member/for-identification") // 임시 비밀번호 발급을 위한 본인 계정 확인 절차 페이지
    public String forIdentification() {
        return "member/for-identification";
    }

    @PostMapping("/member/for-identification")
    public String checkUserIdentification (
            @Valid ForIdentificationDto forIdentificationDto,
            Errors errors, Model model
    ) {
        if (errors.hasErrors()) {
            model.addAttribute("forIdentificationDto", forIdentificationDto);
            Map<String, String> validateMap = userSettingsService.validateHandling(errors);
            for (String key : validateMap.keySet()) {
                model.addAttribute(key, validateMap.get(key));
            }
            return "member/for-identification";
        }
        userSettingsService.sendTemporaryPassword(forIdentificationDto);
        return "member/temporary-password-success";
//        return "redirect:/";
    }

    @GetMapping("/member/reset-password")
    public String findPassword() {
        return "member/reset-password";
    }
}
