package com.example.service.impl;

import com.example.dao.entity.UserDo;
import com.example.dao.mapper.UserMapper;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<UserDo> getAllUser() {
        return userMapper.getAllUser();
    }
}
