package com.example.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.example.common.convention.result.Result;
import com.example.common.convention.result.Results;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: LoginAuthInterceptor
 * Package: com.example.interceptor
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 14:11
 * @Version 1.0
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求方式
        // 如果请求方式是options，预检请求，直接放行
        if("OPTIONS".equals(request.getMethod())){
            return true;
        }
        // 2.从请求头获取token
        // String token = request.getHeader("token");
        // 3.如果token为空，返回错误提示
        // if(StrUtil.isEmpty(token)){
        //     responseNoLoginInfo(response);
        //     return false;
        // }

        // 4.如果token不为空，拿着token查询redis
        // String sysJson = redisTemplate.opsForValue().get("user:login" + token);

        // 5.如果redis查询不到数据，返回错误提示
        // if(StrUtil.isEmpty(sysJson)){
        //     responseNoLoginInfo(response);
        //     return false;
        // }

        // 6.如果redis查询到用户信息，把用户信息放到ThreadLocal里面
        // SysUser sysUser = JSON.parseObject(sysJson, SysUser.class);
        // AuthContextUtil.set(sysUser);

        // 7.把redis用户信息数据更新过期时间
        // redisTemplate.expire("user:login" + token, 30, TimeUnit.MINUTES);

        // 8.放行

        return true;
    }

    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Void> result = Results.failure(Result.SUCCESS_CODE, "未登录");
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // threadLocal删除
    }
}
