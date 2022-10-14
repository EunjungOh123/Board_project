package com.example.board_practice.course.service;

import com.example.board_practice.course.dto.CourseDto;
import com.example.board_practice.course.dto.InputCourseDto;
import com.example.board_practice.course.model.CourseParam;

import java.util.List;

public interface CourseService {

    /**
     * 강좌 등록
     */
    void add (InputCourseDto courseDto);

    /**
     * 강좌 정보수정
     */
    boolean set (InputCourseDto courseDto);

    /**
     * 강좌 목록
     */
    List<CourseDto> list (CourseParam param);

    /**
     * 강좌 상세정보
     */
    CourseDto getById(long id);

    /**
     * 강좌 내용 삭제 (선택 삭제)
     */
    void delete (String idList);
}
