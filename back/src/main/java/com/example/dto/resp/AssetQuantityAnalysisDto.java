package com.example.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: AssetQuantityAnalysisDto
 * Package: com.example.dto.resp
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/13 11:20
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetQuantityAnalysisDto {
    private List<String> assetType;
    private List<Double> assetQuantity;
    private List<Double> assetTransactionQuantity;
}
