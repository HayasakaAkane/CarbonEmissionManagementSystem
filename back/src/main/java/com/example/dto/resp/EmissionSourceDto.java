package com.example.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: EmissionSourceDto
 * Package: com.example.dto.resp
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/12 16:04
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmissionSourceDto {
    private int sourceId;
    private String name;
    private String type;
    private String location;
    private String status;
    private int complianceYear;
    private double emissionsRequired;
    private String latestEmissionDate;
    private double latestCO2Emissions;
}
