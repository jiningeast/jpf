package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudDfOrder;
import com.joiest.jpf.common.po.PayCloudDfOrderExample;
import com.joiest.jpf.common.po.PayCloudDfTask;
import com.joiest.jpf.common.po.PayCloudDfTaskExample;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfOrderMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfTaskMapper;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.facade.CloudDfOrderInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class CloudDfOrderInterfaceServiceFacadeImpl implements CloudDfOrderInterfaceServiceFacade {

    @Autowired
    private PayCloudDfOrderMapper payCloudDfOrderMapper;

    @Autowired
    private PayCloudDfTaskMapper payCloudDfTaskMapper;

    @Override
    @Transactional
    public int addOrder(String batchid, String dfid, List<CloudDfMoneyInterfaceInfo> list, String batchid_self) {
        int res = 0;
        for ( CloudDfMoneyInterfaceInfo one :list )
        {
            PayCloudDfOrder payCloudDfOrder = new PayCloudDfOrder();
            payCloudDfOrder.setOrderid(Long.parseLong(ToolUtils.createOrderid()));
            payCloudDfOrder.setBatchid(batchid_self);
            payCloudDfOrder.setRequestBatchno(batchid);
            payCloudDfOrder.setRequestDfId(dfid);
            payCloudDfOrder.setApplyamt(one.getCommoney());
            payCloudDfOrder.setOrderStdPrice(one.getCommoney());
            payCloudDfOrder.setFinacode(one.getBankcode());    //TODO df_money 需要添加此字段
            payCloudDfOrder.setPayeeacct(one.getBankno());
            payCloudDfOrder.setPayeename(one.getBanknickname());
            String payeeAcctAttr = one.getBankacctattr() == 0 ? "PRIVATE" : "PUBLIC";
            payCloudDfOrder.setPayeeacctattr(payeeAcctAttr);     //TODO  df_money 此字段为0
            payCloudDfOrder.setPhoneno(one.getBankphone());
            payCloudDfOrder.setCertno(one.getIdno());    //TODO df_money 添加此字段
            payCloudDfOrder.setCreated(new Date());
            payCloudDfOrder.setStatus(0);


//            payCloudDfOrder
            res =  payCloudDfOrderMapper.insertSelective(payCloudDfOrder);
            if ( res != 1 )
            {
                return res;
            }
        }
        //更新任务状态
        PayCloudDfTaskExample example = new PayCloudDfTaskExample();
        PayCloudDfTaskExample.Criteria c = example.createCriteria();
        c.andBatchidEqualTo(batchid_self);
        PayCloudDfTask payCloudDfTask = new PayCloudDfTask();
        payCloudDfTask.setInsertStatus(2);
        payCloudDfTask.setUpdated(new Date());
        int res_up = payCloudDfTaskMapper.updateByExampleSelective(payCloudDfTask, example);
        return res;
    }
    /**
     *根据外来单号查询代付数据  request_orderid
     * **/
    public CloudDfMoneyInterfaceInfo getDfOrderByRequestOrderid(String request_orderid){

        PayCloudDfOrderExample example = new PayCloudDfOrderExample();
        PayCloudDfOrderExample.Criteria c = example.createCriteria();
        //c.


        return null;
    }
}
