package com.example.service;

import com.example.dto.resp.EmissionRecordDto;

import java.util.List;

/**
 * ClassName: EmissionRecordService
 * Package: com.example.service
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/14 15:14
 * @Version 1.0
 */
public interface EmissionRecordService {
    List<EmissionRecordDto> getAllEmissionRecords();
}
