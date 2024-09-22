package com.example.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: CCERGetProjectDto
 * Package: com.example.dto.req
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/16 12:24
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CCERGetProjectDto {
    private int projectId;
    private String projectName;
    private String projectType;
    private String status;
    private String location;
    private Date registrationDate;
    private Date validUntil;
    private int year;
    private double reductionAmount;
    private Date verificationDate;
    private Date reportDate;
    private String reportDetails;
    private String registrationNumber;
    private String issuingAuthority;
    private Date issueDate;
    private String buyer;
    private String seller;
    private Date transactionDate;
    private double quantity;
    private double pricePerUnit;
    private double totalAmount;
}
