package com.example.dto.req;

import com.example.dto.resp.EmissionRecordDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: EmissionRecordRequestDto
 * Package: com.example.dto.req
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/15 12:50
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmissionRecordRequestDto {
    private List<EmissionRecordDto> emissionRecords;
}
