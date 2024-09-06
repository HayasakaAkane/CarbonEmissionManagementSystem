package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: test
 * Package: com.example.controller
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/6 17:13
 * @Version 1.0
 */
@RestController
public class test {

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

}
