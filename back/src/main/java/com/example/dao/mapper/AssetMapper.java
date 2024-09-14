package com.example.dao.mapper;

import com.example.dto.resp.ComplianceYearEmissionRequiredDto;
import com.example.dto.resp.EmissionSourceDto;
import com.example.dto.resp.ProjectReductionDto;

import java.util.List;

/**
 * ClassName: AssetMapper
 * Package: com.example.dao.mapper
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/12 14:27
 * @Version 1.0
 */
public interface AssetMapper {
    public List<EmissionSourceDto> getAllEmissionSource();

    List<ComplianceYearEmissionRequiredDto> getYearReq();

    List<ProjectReductionDto> getProjectReduction();

    List<String> getEmissionSourceName();

    List<Integer> getEmissionSourceYear();

    List<Double> getCO2Emissions(String name);

    List<String> getTransactionType();

    List<Double> getTransactionQuantity();

    List<Double> getTransactionPrice();

    List<Integer> getAssetAnalysisYear();

    List<String> getAssetAnalysisAssetType();

    List<Double> getAssetTransactionQuantity(String assetType);

    List<String> getAssetType();

    List<Double> getAssetQuantity();

    List<Double> getAssetTransactionQuantity2();
}
