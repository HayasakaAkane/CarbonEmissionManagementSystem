package com.example.service;

import com.example.dao.entity.UserDo;

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

    List<UserDo> getAllUser();

}
