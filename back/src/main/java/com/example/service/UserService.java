package com.example.service;

import com.example.dto.req.UserDto;
import com.example.dto.req.UserRegisterDto;
import com.example.dto.resp.CompanyDto;

import java.util.List;

/**
 * ClassName: UserService
 * Package: com.example.service
 * Description: 用户接口层
 *
 * @Author natsume
 * @Create 2024/9/11 10:02
 * @Version 1.0
 */
public interface UserService {
    String login(UserDto userDto);

    void register(UserRegisterDto requestParam);

    List<CompanyDto> getCompaniesData();
}
