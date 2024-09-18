package com.example.dto.req;

import lombok.*;

import java.util.Date;

/**
 * ClassName: CCERProjectDto
 * Package: com.example.dto.req
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/15 22:55
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CCERProjectReqDto {
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
