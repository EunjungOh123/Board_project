package com.example.board_practice.course.mapper;

import com.example.board_practice.course.dto.CourseDto;
import com.example.board_practice.course.model.CourseParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    long selectListCount(CourseParam param);
    List<CourseDto> selectList(CourseParam param);
}
