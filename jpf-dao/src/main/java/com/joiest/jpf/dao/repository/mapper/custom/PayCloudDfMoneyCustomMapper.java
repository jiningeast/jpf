package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayCloudDfMoneyCustom;
import com.joiest.jpf.common.po.PayCloudDfMoneyExample;

import java.util.List;

public interface PayCloudDfMoneyCustomMapper {

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudDfMoneyCustom> selectJoinCompanyStaff(PayCloudDfMoneyExample example);


}
