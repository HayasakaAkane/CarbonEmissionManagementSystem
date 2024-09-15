package com.example.controller;

import com.example.common.convention.result.Result;
import com.example.common.convention.result.Results;
import com.example.dto.resp.EmissionRecordDto;
import com.example.service.EmissionRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: EmissionRecordController
 * Package: com.example.controller
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/14 15:13
 * @Version 1.0
 */
@RestController
@RequiredArgsConstructor
public class EmissionRecordController {

    private final EmissionRecordService emissionRecordService;

    @GetMapping("/getAllEmissionRecords")
    public Result<List<EmissionRecordDto>> getAllEmissionRecords() {
        return Results.success(emissionRecordService.getAllEmissionRecords());
    }

}
