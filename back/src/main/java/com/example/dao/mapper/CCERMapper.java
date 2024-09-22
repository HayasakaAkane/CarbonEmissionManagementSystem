package com.example.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dto.req.CCERProjectReqDto;
import com.example.dto.req.ModifyStatusDto;
import com.example.dto.resp.CCERGetProjectDto;
import com.example.dto.resp.CCERProjectAmountsDto;
import com.example.dto.resp.CCERProjectDto;
import com.example.dto.resp.CCERProjectReductionAmountsDto;

import java.util.List;

/**
 * ClassName: CCERMapper
 * Package: com.example.dao.mapper
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/11 15:11
 * @Version 1.0
 */
public interface CCERMapper extends BaseMapper<CCERProjectDto> {

    List<CCERProjectDto> getAllProject();

    List<CCERProjectAmountsDto> getProjectAmounts();

    List<CCERProjectReductionAmountsDto> getReductionAmounts();

    void insertCCERProject(CCERGetProjectDto requestParam);
    void insertCCERProjectEmissionProject(CCERGetProjectDto requestParam);
    void insertCCERProjectMonitoringReports(CCERGetProjectDto requestParam);
    void insertCCERProjectRegistrationInfo(CCERGetProjectDto requestParam);
    void insertCCERProjectTransaction(CCERGetProjectDto requestPrams);

    List<CCERProjectReqDto> getProject();

    int getProjectIdFromCCERProject(CCERGetProjectDto requestParam);

    void modifyStatus(ModifyStatusDto requestParam);
}
