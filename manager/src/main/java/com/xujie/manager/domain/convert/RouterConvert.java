package com.xujie.manager.domain.convert;

import com.xujie.manager.DTO.res.RouterResDTO;
import com.xujie.manager.DTO.res.UserLoginResDTO;
import com.xujie.manager.domain.BO.RouterBO;
import com.xujie.manager.domain.BO.UserBO;
import com.xujie.manager.infra.DO.SysRouters;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Xujie
 * @since 2024/9/17 22:30
 **/

@Mapper(componentModel = "spring")
public interface RouterConvert {
    List<RouterBO> convertDo2Bo(List<SysRouters> list);


    List<RouterResDTO> convertBO2DO(List<RouterBO> routerBOS);

    UserLoginResDTO convertBo2LoginDTO(UserBO userBO);


}
