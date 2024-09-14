package com.example.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: AssetAnalysisDto
 * Package: com.example.dto.resp
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/13 10:49
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetAnalysisDto {
    private List<Integer> years;
    private List<String> assetType;
    private List<Double> assetTransactionQuantity1;
    private List<Double> assetTransactionQuantity2;
    private List<Double> assetTransactionQuantity3;
    private List<Double> assetTransactionQuantity4;
    private List<Double> assetTransactionQuantity5;
}
