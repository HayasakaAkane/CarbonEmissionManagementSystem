package com.example.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: ProjectReductionDto
 * Package: com.example.dto.resp
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/12 16:49
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectReductionDto {
    private String name;
    private double value;
}
