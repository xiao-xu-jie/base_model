package com.xujie.manager.domain.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.domain.BO.BizUserBO;
import com.xujie.manager.domain.convert.BizUserConvert;
import com.xujie.manager.domain.service.BizUserDomainService;
import com.xujie.manager.infra.DO.BizUser;
import com.xujie.manager.infra.service.BizUserService;
import jakarta.annotation.Resource;
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
  @Resource private BizUserConvert bizUserConvert;

  @Override
  public void add(BizUserBO bizUserBO) {
    bizUserService.addOne(bizUserConvert.convertBO2DO(bizUserBO));
  }

  @Override
  public Page<BizUserBO> getPageList(BizUserBO bizUserBO, Integer pageNum, Integer pageSize) {
    Page<BizUser> pageList =
        bizUserService.getPageList(bizUserConvert.convertBO2DO(bizUserBO), pageNum, pageSize);
    return bizUserConvert.convertPageDO2BO(pageList);
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
