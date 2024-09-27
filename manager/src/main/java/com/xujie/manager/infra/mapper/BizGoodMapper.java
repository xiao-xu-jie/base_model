package com.xujie.manager.infra.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xujie.manager.infra.DO.BizGood;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * (BizGood)表数据库访问层
 *
 * @author xujie
 * @since 2024-09-27 19:06:42
 */
public interface BizGoodMapper extends BaseMapper<BizGood> {
  List<BizGood> getByAll(BizGood bizGood);

  /**
   * 批量新增数据（MyBatis原生foreach方法）
   *
   * @param entities List<BizGood> 实例对象列表
   * @return 影响行数
   */
  int insertBatch(@Param("entities") List<BizGood> entities);

  /**
   * 批量新增或按主键更新数据（MyBatis原生foreach方法）
   *
   * @param entities List<BizGood> 实例对象列表
   * @return 影响行数
   * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
   */
  int insertOrUpdateBatch(@Param("entities") List<BizGood> entities);

  int updateById(@Param("updated") BizGood updated, @Param("id") Long id);

  Page<BizGood> getPageList(
      IPage<BizGood> page, @Param(Constants.WRAPPER) QueryWrapper<BizGood> entity);
}
