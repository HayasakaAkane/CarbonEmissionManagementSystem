package com.example.service;

import com.example.dto.req.CCERProjectReqDto;
import com.example.dto.resp.CCERGetProjectDto;
import com.example.dto.resp.CCERProjectAmountsDto;
import com.example.dto.resp.CCERProjectDto;
import com.example.dto.resp.CCERProjectReductionAmountsDto;

import java.util.List;

/**
 * ClassName: CCERService
 * Package: com.example.service
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 15:11
 * @Version 1.0
 */
public interface CCERService {
    List<CCERProjectDto> getAllProject();

    List<CCERProjectAmountsDto> getProjectAmounts();

    List<CCERProjectReductionAmountsDto> getReductionAmounts();

    void putProject(CCERGetProjectDto requestParam);

    List<CCERProjectReqDto> getProject();
}
