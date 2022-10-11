package com.example.board_practice.admin.controller;

import com.example.board_practice.admin.dto.UserDto;
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

    @GetMapping("/admin/member/list")
    public String list (Model model, UserParam param) {

        param.init();

        List<UserDto> users = userService.list(param);

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
