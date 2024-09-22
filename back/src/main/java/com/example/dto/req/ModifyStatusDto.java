package com.example.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: ModifyStatusDto
 * Package: com.example.dto.req
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/19 16:45
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModifyStatusDto {
    private int projectId;
    private String status;
}
