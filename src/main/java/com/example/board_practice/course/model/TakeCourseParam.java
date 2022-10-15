package com.example.board_practice.course.model;

import com.example.board_practice.admin.model.CommonParam;
import lombok.Data;

@Data
public class TakeCourseParam extends CommonParam {

    long id;
    String status;

    String userId;


    long searchCourseId;
}
