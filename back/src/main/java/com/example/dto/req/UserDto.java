package com.example.dto.req;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: UserDo
 * Package: com.example.dao.entity
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 10:06
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String password;
}
