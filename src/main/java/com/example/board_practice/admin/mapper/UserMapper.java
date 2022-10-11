package com.example.board_practice.admin.mapper;

import com.example.board_practice.admin.dto.UserDto;
import com.example.board_practice.admin.model.UserParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    long selectListCount(UserParam parameter);
    List <UserDto> selectList(UserParam param);

}
