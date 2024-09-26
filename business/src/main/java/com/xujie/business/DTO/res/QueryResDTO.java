package com.xujie.business.DTO.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 查询返回DTO
 * @author Xujie
 * @since 2024/9/26 19:30
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryResDTO {
    /**
     * code
     */
    private Integer code;
    /**
     * msg
     */
    private String msg;

    private List<ClassQueryResDTO> data;

    private String userName;

    private String userinfo;

}
