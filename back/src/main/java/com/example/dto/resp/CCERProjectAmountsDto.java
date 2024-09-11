package com.example.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CCERProjectAmountsDto
 * Package: com.example.dto.resp
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 15:47
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CCERProjectAmountsDto {
    private String projectType;
    private int projectAmount;
}
