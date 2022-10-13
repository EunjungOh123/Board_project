package com.example.board_practice.admin.service;

import com.example.board_practice.admin.dto.CategoryDto;
import com.example.board_practice.admin.dto.InputCategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> list();

    /**
     * 카테고리 신규 추가
     */
    void add(InputCategoryDto categoryDto);

    /**
     * 카테고리 수정
     */
    void update (InputCategoryDto categoryDto);

    /* 카테고리 삭제 */
    void delete (long id);

    boolean validateCategoryName (String categoryName);

}
