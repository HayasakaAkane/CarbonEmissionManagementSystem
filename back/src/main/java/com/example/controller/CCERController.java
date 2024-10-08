package com.example.controller;

import com.example.common.convention.result.Result;
import com.example.common.convention.result.Results;
import com.example.dto.req.CCERProjectReqDto;
import com.example.dto.req.ModifyStatusDto;
import com.example.dto.resp.CCERGetProjectDto;
import com.example.dto.resp.CCERProjectAmountsDto;
import com.example.dto.resp.CCERProjectDto;
import com.example.dto.resp.CCERProjectReductionAmountsDto;
import com.example.service.CCERService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/ccer/putProject")
    public Result<Void> putProject(@RequestBody CCERGetProjectDto requestParam) {
        ccerService.putProject(requestParam);
        return Results.success();
    }


    @GetMapping("/ccer/getProject")
    public Result<List<CCERProjectReqDto>> getProject() {
        return Results.success(ccerService.getProject());
    }

    @PutMapping("/ccer/modifyStatus")
    public Result<Void> modifyStatus(@RequestBody ModifyStatusDto requestParam) {
        ccerService.modifyStatus(requestParam);
        return Results.success();
    }
}
