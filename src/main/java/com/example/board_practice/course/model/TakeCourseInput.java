package com.example.board_practice.course.model;

import lombok.Data;

@Data
public class TakeCourseInput {
    long courseId;
    String userId;

    long takeCourseId; //take_course.id
}
