package com.example.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: QueryEmissionDto
 * Package: com.example.dto.req
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/15 12:20
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueryEmissionDto {
    private String inputStr;
}
