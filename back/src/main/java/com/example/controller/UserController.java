package com.example.controller;

import com.example.common.convention.result.Result;
import com.example.common.convention.result.Results;
import com.example.dao.entity.UserDo;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: UserController
 * Package: com.example.controller
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 10:09
 * @Version 1.0
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping("/user/login")
    public Result<List<UserDo>> login() {
        List<UserDo> users = userService.getAllUser();
        return Results.success(users);
    }

}
