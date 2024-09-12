package com.example.controller;

import com.example.common.convention.result.Result;
import com.example.common.convention.result.Results;
import com.example.dto.req.UserDto;
import com.example.dto.req.UserRegisterDto;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/user/login")
    public Result<String> login(@RequestBody UserDto userDto) {
        String token = userService.login(userDto);
        if(token.length() > 6) {
            return Results.success(token);
        }
        return Results.failure(token);
    }

    @PostMapping("/user/register")
    public Result<Void> register(@RequestBody UserRegisterDto requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

}
