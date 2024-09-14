package com.example.service.impl;

import com.example.dao.mapper.AssetMapper;
import com.example.dto.resp.*;
import com.example.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ClassName: AssetServiceImpl
 * Package: com.example.service.impl
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/12 14:26
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetMapper assetMapper;

    @Override
    public List<EmissionSourceDto> getAllEmissionSource() {
        return assetMapper.getAllEmissionSource();
    }

    @Override
    public List<ComplianceYearEmissionRequiredDto> getYearReq() {
        return assetMapper.getYearReq();
    }

    @Override
    public List<ProjectReductionDto> getProjectReduction() {
        return assetMapper.getProjectReduction();
    }

    @Override
    public SourceEmissionTimesDto getSourceEmissionTimes() {
        SourceEmissionTimesDto sourceEmissionTimesDto = new SourceEmissionTimesDto();
        List<Integer> years = assetMapper.getEmissionSourceYear();
        List<String> names = assetMapper.getEmissionSourceName();

        sourceEmissionTimesDto.setNames(names);
        sourceEmissionTimesDto.setYears(years);
        sourceEmissionTimesDto.setData1(assetMapper.getCO2Emissions(names.get(0)));
        sourceEmissionTimesDto.setData2(assetMapper.getCO2Emissions(names.get(1)));
        sourceEmissionTimesDto.setData3(assetMapper.getCO2Emissions(names.get(2)));
        sourceEmissionTimesDto.setData4(assetMapper.getCO2Emissions(names.get(3)));
        sourceEmissionTimesDto.setData5(assetMapper.getCO2Emissions(names.get(4)));

        return sourceEmissionTimesDto;
    }

    @Override
    public TransactionAnalysisDto getTranctionAnalysis() {
        TransactionAnalysisDto transactionAnalysisDto = new TransactionAnalysisDto();

        List<String> transaction = assetMapper.getTransactionType();
        transactionAnalysisDto.setTransaction(transaction);
        transactionAnalysisDto.setTransactionQuantity(assetMapper.getTransactionQuantity());
        transactionAnalysisDto.setTransactionPrice(assetMapper.getTransactionPrice());

        return transactionAnalysisDto;
    }

    @Override
    public AssetAnalysisDto getAssetAnalysis() {
        AssetAnalysisDto assetAnalysisDto = new AssetAnalysisDto();
        assetAnalysisDto.setYears(assetMapper.getAssetAnalysisYear());
        List<String> assetTypes = assetMapper.getAssetAnalysisAssetType();
        assetAnalysisDto.setAssetType(assetTypes);
        assetAnalysisDto.setAssetTransactionQuantity1(assetMapper.getAssetTransactionQuantity(assetTypes.get((0))));
        assetAnalysisDto.setAssetTransactionQuantity2(assetMapper.getAssetTransactionQuantity(assetTypes.get((1))));
        assetAnalysisDto.setAssetTransactionQuantity3(assetMapper.getAssetTransactionQuantity(assetTypes.get((2))));
        assetAnalysisDto.setAssetTransactionQuantity4(assetMapper.getAssetTransactionQuantity(assetTypes.get((3))));
        assetAnalysisDto.setAssetTransactionQuantity5(assetMapper.getAssetTransactionQuantity(assetTypes.get((4))));

        return null;
    }

    @Override
    public AssetQuantityAnalysisDto getAssetQuantityAnalysis() {
        AssetQuantityAnalysisDto assetQuantityAnalysisDto = new AssetQuantityAnalysisDto();

        assetQuantityAnalysisDto.setAssetType(assetMapper.getAssetType());
        assetQuantityAnalysisDto.setAssetQuantity(assetMapper.getAssetQuantity());
        assetQuantityAnalysisDto.setAssetTransactionQuantity(assetMapper.getAssetTransactionQuantity2());
        return assetQuantityAnalysisDto;
    }
}
