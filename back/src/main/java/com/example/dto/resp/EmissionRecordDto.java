package com.example.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: EmissionRecordDto
 * Package: com.example.dto.resp
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/14 15:16
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmissionRecordDto {
    private String dataOrigin;
    private double emissionAmount;
    private String verificationStatus;
    private String emissionType;
    private String emissionDate;
    private String source;
    private String unit;
}
