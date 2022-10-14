package com.example.board_practice.admin.mapper;

import com.example.board_practice.admin.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDto> select(CategoryDto parameter);
}
