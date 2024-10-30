package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.BizUserBO;
import com.xujie.manager.domain.convert.BizUserConvert;
import com.xujie.manager.domain.service.BizUserDomainService;
import com.xujie.manager.infra.DO.BizCertification;
import com.xujie.manager.infra.DO.BizUser;
import com.xujie.manager.infra.service.BizUserCertService;
import com.xujie.manager.infra.service.BizUserService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (BizUser)表服务实现类
 *
 * @author xujie
 * @since 2024-10-28 09:14:51
 */
@Slf4j
@Service
public class BizUserDomainServiceImpl implements BizUserDomainService {
  @Resource private BizUserService bizUserService;
  @Resource private BizUserCertService bizUserCertService;
  @Resource private BizUserConvert bizUserConvert;

  @Override
  public void add(BizUserBO bizUserBO) {
    bizUserService.addOne(bizUserConvert.convertBO2DO(bizUserBO));
  }

  @Override
  public Page<BizUserBO> getPageList(BizUserBO bizUserBO, Integer pageNum, Integer pageSize) {
    Page<BizUser> pageList =
        bizUserService.getPageList(bizUserConvert.convertBO2DO(bizUserBO), pageNum, pageSize);
    List<BizUser> records = pageList.getRecords();
    Page<BizUserBO> bizUserBOPage = bizUserConvert.convertPageDO2BO(pageList);
    if (records.isEmpty()) {
      return bizUserBOPage;
    }
    List<BizCertification> certListByUserIds =
        bizUserCertService.getCertListByUserIds(records.stream().map(BizUser::getId).toList());

    bizUserBOPage
        .getRecords()
        .forEach(
            item -> {
              certListByUserIds.stream()
                  .filter(cert -> cert.getUserId().equals(item.getId()))
                  .findFirst()
                  .ifPresent(cert -> setCertStatus(item, cert));
            });
    return bizUserBOPage;
  }

  private void setCertStatus(BizUserBO bizUserBO, BizCertification cert) {
    switch (cert.getCertStatus()) {
      case 0, 1, 2:
        bizUserBO.setCertificationStatus(cert.getCertStatus());
        bizUserBO.setCertName(cert.getCertType());
        break;
      default:
        break;
    }
  }

  @Override
  public void delete(Long[] ids) {
    bizUserService.deleteBatch(ids);
  }

  @Override
  public void update(BizUserBO bizUserBO) {
    bizUserService.updateOne(bizUserBO.getId(), bizUserConvert.convertBO2DO(bizUserBO));
  }
}
