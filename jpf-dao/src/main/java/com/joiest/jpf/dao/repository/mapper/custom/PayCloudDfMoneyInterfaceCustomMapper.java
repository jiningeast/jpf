package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayCloudDfMoneyInterfaceCustom;
import com.joiest.jpf.common.po.PayCloudDfMoneyExample;

import java.util.List;

public interface PayCloudDfMoneyInterfaceCustomMapper {


    List<PayCloudDfMoneyInterfaceCustom> getUserMonthList(PayCloudDfMoneyExample example);
}