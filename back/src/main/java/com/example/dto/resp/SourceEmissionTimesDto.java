package com.example.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * ClassName: SourceEmissionTimes
 * Package: com.example.dto.resp
 * Description:
 *
 * @Author natsume
 * @Create 2024/9/12 21:17
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SourceEmissionTimesDto {
    private List<Integer> years;
    private List<String> names;
    private List<Double> data1;
    private List<Double> data2;
    private List<Double> data3;
    private List<Double> data4;
    private List<Double> data5;
}
