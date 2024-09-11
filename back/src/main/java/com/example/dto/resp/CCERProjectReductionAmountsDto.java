package com.example.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CCERProjectReductionAmountsDto
 * Package: com.example.dto.resp
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 16:09
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CCERProjectReductionAmountsDto {
    private String projectType;
    private double projectReductionAmount;
}
