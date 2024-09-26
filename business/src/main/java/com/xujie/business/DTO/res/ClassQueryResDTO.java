package com.xujie.business.DTO.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程查询返回DTO
 * @author Xujie
 * @since 2024/9/26 15:48
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassQueryResDTO {
    /**
     * 课程id
     */
    private String id;
    /**
     * 课程名称
     */
    private String name;
}
