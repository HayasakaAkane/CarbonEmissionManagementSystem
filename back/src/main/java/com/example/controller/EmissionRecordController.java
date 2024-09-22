package com.example.controller;

import com.example.common.convention.result.Result;
import com.example.common.convention.result.Results;
import com.example.dto.req.EmissionRecordRequestDto;
import com.example.dto.req.QueryEmissionDto;
import com.example.dto.resp.DataOriginDto;
import com.example.dto.resp.EmissionRecordDto;
import com.example.service.EmissionRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Result<Map<String, List<EmissionRecordDto>>> getAllEmissionRecords() {
        Map<String, List<EmissionRecordDto>> map = new HashMap<>();
        map.put("result", emissionRecordService.getAllEmissionRecords());
        return Results.success(map);
    }

    @PostMapping("/queryEmission")
    public Result<List<EmissionRecordDto>> queryEmission(@RequestBody QueryEmissionDto requestParam) {
        return Results.success(emissionRecordService.queryEmission(requestParam.getInputStr()));
    }

    @PostMapping("/deleteEmission")
    public Result<List<EmissionRecordDto>> deleteEmission(@RequestBody EmissionRecordRequestDto requestParam) {
        return Results.success(emissionRecordService.deleteEmission(requestParam));
    }

    @PostMapping("/deleteOneRowEmission")
    public Result<List<EmissionRecordDto>> deleteOneRowEmission(@RequestBody EmissionRecordDto emissionRecordDto) {
        return Results.success(emissionRecordService.deleteOneRowEmission(emissionRecordDto));
    }

    @PostMapping("/addEmission")
    public Result<List<EmissionRecordDto>> addEmission(@RequestBody EmissionRecordDto emissionRecordDto) {
        return Results.success(emissionRecordService.addEmission(emissionRecordDto));
    }

    @PostMapping("/updateEmission")
    public Result<List<EmissionRecordDto>> updateEmission(@RequestBody EmissionRecordDto emissionRecordDto) {
        return Results.success(emissionRecordService.updateEmission(emissionRecordDto));
    }

    @GetMapping("/getDataOrigin")
    public Result<List<DataOriginDto>> getDataOrigin() {
        return Results.success(emissionRecordService.getDataOrigin());
    }
}
