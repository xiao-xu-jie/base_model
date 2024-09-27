package com.xujie.business.DTO.req;

import com.xujie.business.DTO.res.ClassQueryResDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 订单创建请求DTO
 * @author Xujie
 * @since 2024/9/27 16:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateReqDTO {
    @NotNull(message = "商品ID不能为空")
    private Long goodId;
    @NotNull(message = "用户不能为空")
    private String user;
    @NotNull(message = "密码不能为空")
    private String pass;
    @Size(min = 1, message = "课程不能为空")
    private List<ClassQueryResDTO> classList;
}
