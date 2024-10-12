package com.xujie.business.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.xujie.business.common.enums.CertificationStatusEnum;
import com.xujie.business.domain.BO.BizCertificationBO;
import com.xujie.business.domain.service.CertificationDomainService;
import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Sa-Token 权限验证接口实现
 *
 * @author Xujie
 * @since 2024/10/11 18:25
 */
@Component
public class StpInterfaceImpl implements StpInterface {
  @Resource private CertificationDomainService certificationDomainService;

  @Override
  public List<String> getPermissionList(Object o, String s) {
    return List.of();
  }

  @Override
  public List<String> getRoleList(Object o, String s) {
    Long userId = Long.valueOf(o.toString());
    BizCertificationBO myCertification = certificationDomainService.getMyCertification(userId);
    if (myCertification == null) {
      return List.of();
    } else {
      if (myCertification.getCertStatus().equals(CertificationStatusEnum.APPROVED)) {
        return List.of("CERTIFIED");
      }
    }
    return List.of();
  }
}
