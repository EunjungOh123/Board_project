package com.example.board_practice.admin.dto;

import com.example.board_practice.admin.entity.Category;
import lombok.Data;

@Data
public class InputCategoryDto {
    Long id;
    String categoryName;
    int sortValue;
    boolean usingYn;

    public Category toEntity() {
        return Category.builder()
                .categoryName(categoryName)
                .sortValue(0)
                .usingYn(true)
                .build();
    }
}
