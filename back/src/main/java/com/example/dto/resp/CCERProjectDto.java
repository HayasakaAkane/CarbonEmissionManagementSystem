package com.example.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: CCERProjectDto
 * Package: com.example.dto.resp
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 15:13
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CCERProjectDto {
    private int projectId;

    private String projectName;

    private String projectType;

    private String location;

    private String status;

    private Date registrationDate;

    private Date validUntil;
}
