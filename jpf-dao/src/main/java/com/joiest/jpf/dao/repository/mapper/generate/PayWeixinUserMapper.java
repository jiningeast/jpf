package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayWeixinUser;
import com.joiest.jpf.common.po.PayWeixinUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayWeixinUserMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayWeixinUserExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayWeixinUserExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayWeixinUser record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayWeixinUser record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayWeixinUser> selectByExample(PayWeixinUserExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayWeixinUser selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayWeixinUser record, @Param("example") PayWeixinUserExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayWeixinUser record, @Param("example") PayWeixinUserExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayWeixinUser record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayWeixinUser record);
}