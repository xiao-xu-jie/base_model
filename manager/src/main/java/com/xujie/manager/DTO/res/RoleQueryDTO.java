package com.xujie.manager.DTO.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色查询DTO
 *
 * @author Xujie
 * @since 2024/9/20 21:55
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleQueryDTO {

    /**
     * 角色code
     */

    private String code;

    /**
     * 角色名称
     */

    private String name;


}
