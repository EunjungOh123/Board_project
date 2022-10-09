package com.example.board_practice.admin.controller;

import com.example.board_practice.admin.dto.UserListDto;
import com.example.board_practice.admin.model.UserParam;
import com.example.board_practice.member.service.Impl.UserServiceImpl;
import com.example.board_practice.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final UserServiceImpl userService;

    @GetMapping("/error/denied") // 관리자 권한 없는 경우 관리자 페이지 접속 시 발생하는 에러 페이지
    public String error() {
        return "error/denied";
    }

    @GetMapping("/admin/main") // 관리자 메인 페이지
    public String main() {
        return "admin/main";
    }

    @GetMapping("/admin/member/list")
    public String list (Model model, UserParam param) {

        param.init();

        List<UserListDto> users = userService.list(param);

        long totalCount = 0;
        if(users != null && users.size()>0) {
            totalCount = users.get(0).getTotalCount();
        }

        String queryString = param.getQueryString();
        PageUtil pageUtil = new PageUtil(totalCount, param.getPageSize(), param.getPageIndex(),queryString);

        model.addAttribute("list", users);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("pager", pageUtil.pager());

        return "admin/member/list";
    }
}
