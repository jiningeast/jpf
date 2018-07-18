package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayCloudDfMoneyCustom;
import com.joiest.jpf.common.po.PayCloudDfMoney;
import com.joiest.jpf.common.po.PayCloudDfMoneyExample;

import java.util.List;

public interface PayCloudDfMoneyCustomMapper {

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudDfMoneyCustom> selectJoinCompanyStaff(PayCloudDfMoneyExample example);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayCloudDfMoney record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudDfMoney record);
}
