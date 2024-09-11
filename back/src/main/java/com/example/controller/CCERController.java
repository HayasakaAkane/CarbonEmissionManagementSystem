package com.example.controller;

import com.example.common.convention.result.Result;
import com.example.common.convention.result.Results;
import com.example.dto.resp.CCERProjectAmountsDto;
import com.example.dto.resp.CCERProjectDto;
import com.example.dto.resp.CCERProjectReductionAmountsDto;
import com.example.service.CCERService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: CCERController
 * Package: com.example.controller
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 15:10
 * @Version 1.0
 */
@RestController
@RequiredArgsConstructor
public class CCERController {

    private final CCERService ccerService;

    @RequestMapping("/ccer/projects")
    public Result<List<CCERProjectDto>> getAllProject() {
        return Results.success(ccerService.getAllProject());
    }

    @RequestMapping("/ccer/projectAmounts")
    public Result<List<CCERProjectAmountsDto>> getProjectAmounts() {
        return Results.success(ccerService.getProjectAmounts());
    }

    @RequestMapping("/ccer/projectReductionAmounts")
    public Result<List<CCERProjectReductionAmountsDto>> getProjectReductionAmount(){
        return Results.success(ccerService.getReductionAmounts());
    }
}
