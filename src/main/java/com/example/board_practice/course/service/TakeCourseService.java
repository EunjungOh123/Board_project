package com.example.board_practice.course.service;

import com.example.board_practice.course.dto.TakeCourseDto;
import com.example.board_practice.course.model.ServiceResult;
import com.example.board_practice.course.model.TakeCourseParam;

import java.util.List;

public interface TakeCourseService {

    /**
     * 수강 목록
     */
    List<TakeCourseDto> list(TakeCourseParam parameter);

    /**
     * 수강 상세 정보
     */
    TakeCourseDto detail(long id);

    /**
     * 수강내용 상태 변경
     */
    ServiceResult updateStatus(long id, String status);

    /**
     * 내 수강내역 목록
     */
    List<TakeCourseDto> myCourse(String userId);


    /**
     * 수강신청 취소 처리
     */
    ServiceResult cancel(long id);

}
