package com.example.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.req.UserDto;
import com.example.dto.req.UserRegisterDto;
import com.example.dto.resp.CompanyDto;

import java.util.List;

/**
 * ClassName: UserMapper
 * Package: com.example.dao.mapper
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 10:05
 * @Version 1.0
 */
public interface UserMapper extends BaseMapper<UserDto> {

    List<UserDto> getAllUser();

    String getPasswordByUsername(String username);

    int getUserByUsername(String username);

    int insertUser(UserRegisterDto requestParam);

    int getCompanyIdByCompanyName(String company);

    List<CompanyDto> getCompaniesData();
}
