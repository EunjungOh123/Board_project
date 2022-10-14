package com.example.board_practice.course.controller;

import com.example.board_practice.admin.service.CategoryService;
import com.example.board_practice.course.dto.CourseDto;
import com.example.board_practice.course.dto.InputCourseDto;
import com.example.board_practice.course.model.CourseParam;
import com.example.board_practice.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminCourseController extends PageController{

    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping("/admin/course/list")
    public String list (Model model, CourseParam parameter) {

        parameter.init();
        List<CourseDto> courseList = courseService.list(parameter);

        long totalCount = 0;

        if (!CollectionUtils.isEmpty(courseList)) {
            totalCount = courseList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", courseList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);
        return "admin/course/list";
    }

    @GetMapping(value = {"/admin/course/add", "/admin/course/edit"})
    public String add (Model model, HttpServletRequest request, InputCourseDto courseDto) {
        // 카테고리 정보 뷰로 내려준다
        model.addAttribute("category", categoryService.list());

        // 웹 서버로 요청 시, 요청에 사용된 URL 로부터 URI 값을 리턴
        boolean editMode = request.getRequestURI().contains("/edit");

        CourseDto detail = new CourseDto();
        if (editMode) {
            long id = courseDto.getId();
            CourseDto existCourse = courseService.getById(id);
            if (existCourse == null) {
                // error 처리
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "error/no-course-info";
            }
            detail = existCourse;
        }

        model.addAttribute("editMode", editMode);
        model.addAttribute("detail", detail);

        return "admin/course/add";
    }
    @PostMapping(value = {"/admin/course/add", "/admin/course/edit"})
    public String addSubmit (Model model, HttpServletRequest request, InputCourseDto courseDto) {
        boolean editMode = request.getRequestURI().contains("/edit");

        if (editMode) {
            long id = courseDto.getId();
            CourseDto existCourse = courseService.getById(id);
            if (existCourse == null) {
                // error 처리
                model.addAttribute("message", "강좌정보가 존재하지 않습니다.");
                return "error/no-course-info";
            }

            courseService.set(courseDto);

        } else {
            courseService.add(courseDto);
        }

        return "redirect:/admin/course/list";
    }
}
