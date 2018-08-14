package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayCloudDfMoneyInterfaceCustom;
import com.joiest.jpf.common.po.PayCloudDfMoneyExample;

import java.util.List;

public interface PayCloudDfMoneyInterfaceCustomMapper {


    List<PayCloudDfMoneyInterfaceCustom> getUserMonthList(PayCloudDfMoneyExample example);

    //根据合同号获取当前合同的批次详情
    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudDfMoneyInterfaceCustom> selectListDf(PayCloudDfMoneyExample example);


    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExampleList(PayCloudDfMoneyExample example);
}