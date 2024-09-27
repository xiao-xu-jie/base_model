package com.xujie.business.infra.service.impl;

import com.xujie.business.infra.DO.BizSourceStation;
import com.xujie.business.infra.mapper.BizSourceStationMapper;
import com.xujie.business.infra.service.SourceStationService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 货源站点服务实现类
 * @author Xujie
 * @since 2024/9/27 14:18
 **/

@Slf4j
@Service
public class SourceStationServiceImpl implements SourceStationService {
    @Resource
    private BizSourceStationMapper bizSourceStationMapper;
    @Override
    public List<BizSourceStation> getSourceStationListByEntity(BizSourceStation bizSourceStation) {
        return bizSourceStationMapper.getByAll(bizSourceStation);
    }
}
