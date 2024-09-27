package com.xujie.manager.infra.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujie.manager.infra.DO.BizOrder;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (BizOrder)表数据库访问层
 *
 * @author xujie
 * @since 2024-09-27 19:06:42
 */
public interface BizOrderMapper extends BaseMapper<BizOrder> {

  /**
   * 批量新增数据（MyBatis原生foreach方法）
   *
   * @param entities List<BizOrder> 实例对象列表
   * @return 影响行数
   */
  int insertBatch(@Param("entities") List<BizOrder> entities);

  /**
   * 批量新增或按主键更新数据（MyBatis原生foreach方法）
   *
   * @param entities List<BizOrder> 实例对象列表
   * @return 影响行数
   * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
   */
  int insertOrUpdateBatch(@Param("entities") List<BizOrder> entities);
}
