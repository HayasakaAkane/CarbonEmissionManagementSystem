package com.example.service.impl;

import com.example.dao.mapper.EmissionRecordMapper;
import com.example.dto.resp.EmissionRecordDto;
import com.example.service.EmissionRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
