package com.example.board_practice.course.controller;

import com.example.board_practice.admin.dto.CategoryDto;
import com.example.board_practice.admin.service.CategoryService;
import com.example.board_practice.course.dto.CourseDto;
import com.example.board_practice.course.model.CourseParam;
import com.example.board_practice.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CategoryService categoryService;


    @GetMapping("/course")
    public String course(Model model, CourseParam parameter) {
        List<CourseDto> list = courseService.frontList(parameter);
        model.addAttribute("list", list);

        int courseTotalCount = 0;

        List<CategoryDto> categoryList = categoryService.frontList(CategoryDto.builder().build());
        if (categoryList != null) {
            for(CategoryDto x : categoryList) {
                courseTotalCount += x.getCourseCount();
            }
        }

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("courseTotalCount", courseTotalCount);

        return "course/index";
    }

    @GetMapping("/course/{id}")
    public String courseDetail(Model model
            , CourseParam parameter) {

        CourseDto detail = courseService.frontDetail(parameter.getId());
        model.addAttribute("detail", detail);

        return "course/detail";
    }
}
