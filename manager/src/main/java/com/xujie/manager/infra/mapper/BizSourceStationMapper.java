package com.xujie.manager.infra.mapper;
import java.util.Date;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.manager.infra.DO.BizSourceStation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (BizSourceStation)表数据库访问层
 *
 * @author xujie
 * @since 2024-09-27 19:06:42
 */
public interface BizSourceStationMapper extends BaseMapper<BizSourceStation> {

  /**
   * 批量新增数据（MyBatis原生foreach方法）
   *
   * @param entities List<BizSourceStation> 实例对象列表
   * @return 影响行数
   */
  int insertBatch(@Param("entities") List<BizSourceStation> entities);

  /**
   * 批量新增或按主键更新数据（MyBatis原生foreach方法）
   *
   * @param entities List<BizSourceStation> 实例对象列表
   * @return 影响行数
   * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
   */
  int insertOrUpdateBatch(@Param("entities") List<BizSourceStation> entities);

    List<BizSourceStation> getByAll(BizSourceStation bizSourceStation);


}
