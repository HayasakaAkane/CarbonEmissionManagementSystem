package com.example.service.impl;

import com.example.common.convention.exception.ClientException;
import com.example.dto.req.UserDto;
import com.example.dao.mapper.UserMapper;
import com.example.dto.req.UserRegisterDto;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * ClassName: UserServiceImpl
 * Package: com.example.service.impl
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 10:03
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Override
    public String login(UserDto userDto) {
        String password = userMapper.getPasswordByUsername(userDto.getUsername());
        if(password == null) {
            throw new ClientException("用户不存在");
        } else if(!userDto.getPassword().equals(password)) {
            throw new ClientException("密码错误");
        }

        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    @Override
    public void register(UserRegisterDto requestParam) {
        int count = userMapper.getUserByUsername(requestParam.getUsername());
        if(count != 0) {
            throw new ClientException("用户已存在");
        }

        requestParam.setCompanyId(userMapper.getCompanyIdByCompanyName(requestParam.getCompany()));

        int insert = userMapper.insertUser(requestParam);
        if(insert < 1){
            throw new ClientException("用户记录新增失败");
        }
    }
}
