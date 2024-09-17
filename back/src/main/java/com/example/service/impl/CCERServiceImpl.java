package com.example.service.impl;

import com.example.dao.mapper.CCERMapper;
import com.example.dto.req.CCERProjectReqDto;
import com.example.dto.resp.CCERGetProjectDto;
import com.example.dto.resp.CCERProjectAmountsDto;
import com.example.dto.resp.CCERProjectDto;
import com.example.dto.resp.CCERProjectReductionAmountsDto;
import com.example.service.CCERService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: CCERServiceImpl
 * Package: com.example.service.impl
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 15:11
 * @Version 1.0
 */
@Service
@RequiredArgsConstructor
public class CCERServiceImpl implements CCERService {

    private final CCERMapper ccerMapper;

    @Override
    public List<CCERProjectDto> getAllProject() {
        return ccerMapper.getAllProject();
    }

    @Override
    public List<CCERProjectAmountsDto> getProjectAmounts() {
        return ccerMapper.getProjectAmounts();
    }

    @Override
    public List<CCERProjectReductionAmountsDto> getReductionAmounts() {
        return ccerMapper.getReductionAmounts();
    }

    @Override
    public void putProject(CCERGetProjectDto requestParam) {
        ccerMapper.insertCCERProject(requestParam);
        int projectId = ccerMapper.getProjectIdFromCCERProject(requestParam);
        requestParam.setProjectId(projectId);
        ccerMapper.insertCCERProjectEmissionProject(requestParam);
        ccerMapper.insertCCERProjectMonitoringReports(requestParam);
        ccerMapper.insertCCERProjectRegistrationInfo(requestParam);
        ccerMapper.insertCCERProjectTransaction(requestParam);
    }

    @Override
    public List<CCERProjectReqDto> getProject() {
        return ccerMapper.getProject();
    }
}
