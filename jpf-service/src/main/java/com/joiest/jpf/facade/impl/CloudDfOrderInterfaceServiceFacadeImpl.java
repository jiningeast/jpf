package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudDfOrder;
import com.joiest.jpf.common.po.PayCloudDfOrderExample;
import com.joiest.jpf.common.po.PayCloudDfTask;
import com.joiest.jpf.common.po.PayCloudDfTaskExample;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfOrderMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfTaskMapper;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.entity.CloudDfOrderInterfaceInfo;
import com.joiest.jpf.facade.CloudDfOrderInterfaceServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
            payCloudDfOrder.setRequestDfId(String.valueOf(one.getId()));
            payCloudDfOrder.setRequestOrderid(one.getOrderid());
            payCloudDfOrder.setApplyamt(one.getCommoney());
            payCloudDfOrder.setOrderStdPrice(one.getCommoney());
            payCloudDfOrder.setFinacode(one.getBankcode());
            payCloudDfOrder.setPayeeacct(one.getBankno());
            payCloudDfOrder.setPayeename(one.getBanknickname());
            String payeeAcctAttr = one.getBankacctattr() == 0 ? "PRIVATE" : "PUBLIC";
            payCloudDfOrder.setPayeeacctattr(payeeAcctAttr);
            payCloudDfOrder.setPhoneno(one.getBankphone());
            payCloudDfOrder.setCertno(one.getIdno());
            payCloudDfOrder.setBankname(one.getBankname());
            payCloudDfOrder.setBankprovince(one.getBankprovince());
            payCloudDfOrder.setBankcity(one.getBankcity());
            payCloudDfOrder.setCreated(new Date());
            payCloudDfOrder.setStatus(0);

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

    public List<CloudDfOrderInterfaceInfo> getOrdersList(String batchid)
    {
        PayCloudDfOrderExample example = new PayCloudDfOrderExample();
        PayCloudDfOrderExample.Criteria c = example.createCriteria();
        c.andBatchidEqualTo(batchid);
        c.andStatusEqualTo(0);
        List<PayCloudDfOrder> list = payCloudDfOrderMapper.selectByExample(example);
        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        List<CloudDfOrderInterfaceInfo> resultList = new ArrayList<>();

        for (PayCloudDfOrder one : list)
        {
            CloudDfOrderInterfaceInfo info = new CloudDfOrderInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfOrder.class, CloudDfOrderInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
        }

        return resultList;
    }

    public int updateDfOrderStatus(CloudDfOrderInterfaceInfo info)
    {
        PayCloudDfOrder payCloudDfOrder = new PayCloudDfOrder();
        BeanCopier beanCopier = BeanCopier.create(CloudDfOrderInterfaceInfo.class, PayCloudDfOrder.class, false);
        beanCopier.copy(info, payCloudDfOrder, null);
//        PayCloudDfOrderExample example = new PayCloudDfOrderExample();
//        PayCloudDfOrderExample.Criteria c = example.createCriteria();
//        c.andIdEqualTo(info.getId());
        int res = payCloudDfOrderMapper.updateByPrimaryKeySelective(payCloudDfOrder);

        return res;
    }
    /**
     *根据外来单号查询代付数据  request_orderid
     * **/
    public CloudDfOrderInterfaceInfo getDfOrderByRequestOrderid(String request_orderid){

        PayCloudDfOrderExample example = new PayCloudDfOrderExample();
        PayCloudDfOrderExample.Criteria c = example.createCriteria();
        c.andRequestOrderidEqualTo(request_orderid);

        List<PayCloudDfOrder> getPayCloudDfOrder = payCloudDfOrderMapper.selectByExample(example);

        if(getPayCloudDfOrder == null || getPayCloudDfOrder.isEmpty()) return null;

        PayCloudDfOrder payCloudDfOrder = getPayCloudDfOrder.get(0);

        CloudDfOrderInterfaceInfo cloudDfOrderInterfaceInfo = new CloudDfOrderInterfaceInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudDfOrder.class,CloudDfOrderInterfaceInfo.class,false);
        beanCopier.copy(payCloudDfOrder,cloudDfOrderInterfaceInfo,null);

        return cloudDfOrderInterfaceInfo;
    }
}
