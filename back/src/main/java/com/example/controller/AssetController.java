package com.example.controller;

import com.example.common.convention.result.Result;
import com.example.common.convention.result.Results;
import com.example.dto.resp.*;
import com.example.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: AssetController
 * Package: com.example.controller
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/12 14:26
 * @Version 1.0
 */
@RestController
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @GetMapping("/getAllEmissionSource")
    public Result<Map<String, List<EmissionSourceDto>>> getAllEmissionSource(){
        Map<String, List<EmissionSourceDto>> map = new HashMap<>();
        map.put("result", assetService.getAllEmissionSource());
        return Results.success(map);
    }

    @GetMapping("/getYearReq")
    public Result<Map<String, List<ComplianceYearEmissionRequiredDto>>> getYearReq() {
        Map<String, List<ComplianceYearEmissionRequiredDto>> map = new HashMap<>();
        map.put("complianceYearEmissionRequired", assetService.getYearReq());
        return Results.success(map);
    }

    @GetMapping("/getProjectReduction")
    public Result<Map<String, List<ProjectReductionDto>>> getProjectReduction() {
        Map<String, List<ProjectReductionDto>> map = new HashMap<>();
        map.put("projectReductions", assetService.getProjectReduction());
        return Results.success(map);
    }

    @GetMapping("/getSourceEmissionTimes")
    public Result<SourceEmissionTimesDto> getSourceEmissionTimes() {
        return Results.success(assetService.getSourceEmissionTimes());
    }

    @GetMapping("/getTranctionAnalysis")
    public Result<TransactionAnalysisDto> getTranctionAnalysis() {
        return Results.success(assetService.getTranctionAnalysis());
    }

    @GetMapping("/getAssetAnalysis")
    public Result<AssetAnalysisDto> getAssetAnalysis() {
        return Results.success(assetService.getAssetAnalysis());
    }

    @GetMapping("/getAssetQuantityAnalysis")
    public Result<AssetQuantityAnalysisDto> getAssetQuantityAnalysis() {
        return Results.success(assetService.getAssetQuantityAnalysis());
    }
}
