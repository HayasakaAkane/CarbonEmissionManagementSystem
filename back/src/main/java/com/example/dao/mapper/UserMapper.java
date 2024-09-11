package com.example.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dao.entity.UserDo;
import org.apache.ibatis.annotations.Select;

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
public interface UserMapper extends BaseMapper<UserDo> {

    List<UserDo> getAllUser();
}
