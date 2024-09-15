package com.example.dao.mapper;

import com.example.dto.resp.EmissionRecordDto;

import java.util.List;

/**
 * ClassName: EmissionRecordMapper
 * Package: com.example.dao.mapper
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/14 15:14
 * @Version 1.0
 */
public interface EmissionRecordMapper {
    List<EmissionRecordDto> getAllEmissionRecords();
}
