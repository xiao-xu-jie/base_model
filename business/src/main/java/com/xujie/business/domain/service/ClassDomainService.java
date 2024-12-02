package com.xujie.business.domain.service;

import com.xujie.business.DTO.res.QueryResDTO;
import com.xujie.business.domain.BO.QueryClassBO;

public interface ClassDomainService {
  /**
   * 用户查询课程
   *
   * @param queryBO
   * @return
   * @param <T>
   */
  public QueryResDTO queryClassInfo(QueryClassBO queryBO);
}
