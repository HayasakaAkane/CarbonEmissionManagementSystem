package com.example.service;

import com.example.dto.resp.*;

import java.util.List;

/**
 * ClassName: AssetService
 * Package: com.example.service
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/12 14:26
 * @Version 1.0
 */
public interface AssetService {
    List<EmissionSourceDto> getAllEmissionSource();

    List<ComplianceYearEmissionRequiredDto> getYearReq();

    List<ProjectReductionDto> getProjectReduction();

    SourceEmissionTimesDto getSourceEmissionTimes();

    TransactionAnalysisDto getTranctionAnalysis();

    AssetAnalysisDto getAssetAnalysis();

    AssetQuantityAnalysisDto getAssetQuantityAnalysis();
}
