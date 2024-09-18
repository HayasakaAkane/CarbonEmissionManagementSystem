package com.example.service.impl;

import com.example.dao.mapper.EmissionRecordMapper;
import com.example.dto.req.EmissionRecordRequestDto;
import com.example.dto.resp.DataOriginDto;
import com.example.dto.resp.EmissionRecordDto;
import com.example.service.EmissionRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * ClassName: EmissionRecordServiceImpl
 * Package: com.example.service.impl
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/14 15:14
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class EmissionRecordServiceImpl implements EmissionRecordService {

    private final EmissionRecordMapper emissionRecordMapper;

    @Override
    public List<EmissionRecordDto> getAllEmissionRecords() {
        return emissionRecordMapper.getAllEmissionRecords();
    }

    @Override
    public List<EmissionRecordDto> queryEmission(String inputStr) {
        return emissionRecordMapper.queryEmission(inputStr);
    }

    @Override
    public List<EmissionRecordDto> deleteEmission(EmissionRecordRequestDto requestParam) {
        for(EmissionRecordDto emissionRecordDto : requestParam.getEmissionRecords()) {
            emissionRecordMapper.deleteEmission(emissionRecordDto);
        }
        return emissionRecordMapper.getAllEmissionRecords();
    }

    @Override
    public List<EmissionRecordDto> deleteOneRowEmission(EmissionRecordDto emissionRecordDto) {
        emissionRecordMapper.deleteEmission(emissionRecordDto);
        return emissionRecordMapper.getAllEmissionRecords();
    }

    @Override
    public List<EmissionRecordDto> addEmission(EmissionRecordDto emissionRecordDto) {
        emissionRecordMapper.addEmission(emissionRecordDto);
        return emissionRecordMapper.getAllEmissionRecords();
    }

    @Override
    public List<EmissionRecordDto> updateEmission(EmissionRecordDto emissionRecordDto) {
        emissionRecordMapper.updateEmission(emissionRecordDto);
        return emissionRecordMapper.getAllEmissionRecords();
    }

    @Override
    public List<DataOriginDto> getDataOrigin() {
        return emissionRecordMapper.getDataOrigin();
    }

}
