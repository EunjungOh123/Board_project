package com.example.board_practice.course.model;

import com.example.board_practice.admin.model.CommonParam;
import lombok.Data;

@Data
public class CourseParam extends CommonParam {
    long id;
    long categoryId;
}
