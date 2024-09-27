package com.xujie.business.infra.service;

import com.xujie.business.infra.DO.BizSourceStation;

import java.util.List;

public interface SourceStationService {
    List<BizSourceStation>  getSourceStationListByEntity(BizSourceStation bizSourceStation);
}
