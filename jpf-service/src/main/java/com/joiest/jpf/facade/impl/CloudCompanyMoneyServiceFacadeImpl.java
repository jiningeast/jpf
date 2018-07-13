package com.joiest.jpf.facade.impl;


import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudCompanyMoney;
import com.joiest.jpf.common.po.PayCloudCompanyMoneyExample;
import com.joiest.jpf.common.po.PayCloudDfMoney;
import com.joiest.jpf.common.po.PayCloudDfMoneyExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyMoneyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfMoneyMapper;
import com.joiest.jpf.dto.CloudCompanyMoneyRequest;
import com.joiest.jpf.dto.CloudCompanyMoneyResponse;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.entity.CloudCompanyMoneyInfo;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.facade.CloudCompanyMoneyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CloudCompanyMoneyServiceFacadeImpl implements CloudCompanyMoneyServiceFacade {

    @Autowired
    private PayCloudCompanyMoneyMapper payCloudCompanyMoneyMapper;

    @Autowired
    private PayCloudDfMoneyMapper payCloudDfMoneyMapper;

    /*
    * 统计充值总笔数
    * */
    @Override
    public Integer getCount(){
        PayCloudCompanyMoneyExample example = new PayCloudCompanyMoneyExample();
        int count = payCloudCompanyMoneyMapper.countByExample(example);

        return count;
    }

    /*
     * 充值列表页
     * */
    @Override
    public CloudCompanyMoneyResponse getRecords(CloudCompanyMoneyRequest cloudCompanyMoneyRequest){
        CloudCompanyMoneyResponse CloudCompanyMoneyResponse = new CloudCompanyMoneyResponse();

        PayCloudCompanyMoneyExample e = new PayCloudCompanyMoneyExample();
        PayCloudCompanyMoneyExample.Criteria c = e.createCriteria();
        // 构建查询example
        if ( StringUtils.isNotBlank(cloudCompanyMoneyRequest.getAgentNo()) ){
            c.andAgentNoEqualTo(cloudCompanyMoneyRequest.getAgentNo());
        }
        if ( StringUtils.isNotBlank(cloudCompanyMoneyRequest.getFid())  ){
            c.andFidEqualTo(cloudCompanyMoneyRequest.getFid());
        }

        if ( cloudCompanyMoneyRequest.getMontype() !=null  && cloudCompanyMoneyRequest.getMontype().toString() !="" ){
            c.andMontypeEqualTo(cloudCompanyMoneyRequest.getMontype());
        }

        // 添加时间搜索
        Date addtimeStart = new Date();
        if ( StringUtils.isNotBlank(cloudCompanyMoneyRequest.getAddtimeStart()) ){
            addtimeStart = DateUtils.getFdate( cloudCompanyMoneyRequest.getAddtimeStart(), DateUtils.DATEFORMATLONG );
            c.andAddtimeGreaterThan( addtimeStart );
        }
        Date addtimeEnd = new Date();
        if ( StringUtils.isNotBlank(cloudCompanyMoneyRequest.getAddtimeEnd()) ){
            addtimeEnd = DateUtils.getFdate( cloudCompanyMoneyRequest.getAddtimeEnd(), DateUtils.DATEFORMATLONG );
            c.andAddtimeLessThan( addtimeEnd );
        }

        e.setPageNo(cloudCompanyMoneyRequest.getPage());
        e.setPageSize(cloudCompanyMoneyRequest.getRows());
        e.setOrderByClause("id DESC");

        List<PayCloudCompanyMoney> list = payCloudCompanyMoneyMapper.selectByExample(e);
        List<CloudCompanyMoneyInfo> infos = new ArrayList<>();
        for (PayCloudCompanyMoney payCloudCompanyMoney : list) {
            CloudCompanyMoneyInfo CloudCompanyMoneyInfo = new CloudCompanyMoneyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyMoney.class, CloudCompanyMoneyInfo.class, false);
            beanCopier.copy(payCloudCompanyMoney, CloudCompanyMoneyInfo, null);

            infos.add(CloudCompanyMoneyInfo);
        }

        CloudCompanyMoneyResponse.setList(infos);
        CloudCompanyMoneyResponse.setCount(payCloudCompanyMoneyMapper.countByExample(e));


        return CloudCompanyMoneyResponse;
    }

    /*
     * 财务订单审核列表页
     * */
    @Override
    public CloudCompanyMoneyResponse getCaiwuRecords(CloudCompanyMoneyRequest cloudCompanyMoneyRequest){
        CloudCompanyMoneyResponse CloudCompanyMoneyResponse = new CloudCompanyMoneyResponse();

        PayCloudCompanyMoneyExample e = new PayCloudCompanyMoneyExample();
        PayCloudCompanyMoneyExample.Criteria c = e.createCriteria();
        // 构建查询example
        if ( StringUtils.isNotBlank(cloudCompanyMoneyRequest.getAgentNo()) ){
            c.andAgentNoEqualTo(cloudCompanyMoneyRequest.getAgentNo());
        }
        if ( StringUtils.isNotBlank(cloudCompanyMoneyRequest.getFid())  ){
            c.andFidEqualTo(cloudCompanyMoneyRequest.getFid());
        }

        if ( cloudCompanyMoneyRequest.getMontype() !=null  && cloudCompanyMoneyRequest.getMontype().toString() !="" ){
            c.andMontypeEqualTo(cloudCompanyMoneyRequest.getMontype());
        }

        // 添加时间搜索
        Date addtimeStart = new Date();
        if ( StringUtils.isNotBlank(cloudCompanyMoneyRequest.getAddtimeStart()) ){
            addtimeStart = DateUtils.getFdate( cloudCompanyMoneyRequest.getAddtimeStart(), DateUtils.DATEFORMATLONG );
            c.andAddtimeGreaterThan( addtimeStart );
        }
        Date addtimeEnd = new Date();
        if ( StringUtils.isNotBlank(cloudCompanyMoneyRequest.getAddtimeEnd()) ){
            addtimeEnd = DateUtils.getFdate( cloudCompanyMoneyRequest.getAddtimeEnd(), DateUtils.DATEFORMATLONG );
            c.andAddtimeLessThan( addtimeEnd );
        }

        e.setPageNo(cloudCompanyMoneyRequest.getPage());
        e.setPageSize(cloudCompanyMoneyRequest.getRows());
        e.setOrderByClause("id DESC");

        List<PayCloudCompanyMoney> list = payCloudCompanyMoneyMapper.selectByExample(e);
        List<CloudCompanyMoneyInfo> infos = new ArrayList<>();
        for (PayCloudCompanyMoney payCloudCompanyMoney : list) {
            CloudCompanyMoneyInfo CloudCompanyMoneyInfo = new CloudCompanyMoneyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyMoney.class, CloudCompanyMoneyInfo.class, false);
            beanCopier.copy(payCloudCompanyMoney, CloudCompanyMoneyInfo, null);

            infos.add(CloudCompanyMoneyInfo);
        }

        CloudCompanyMoneyResponse.setList(infos);
        CloudCompanyMoneyResponse.setCount(payCloudCompanyMoneyMapper.countByExample(e));


        return CloudCompanyMoneyResponse;
    }

    /*
     * 代付列表
     * */
    @Override
    public GetCloudMoneyDfResponse getAllByfid(String fid){

        GetCloudMoneyDfResponse getCloudMoneyDfResponse = new GetCloudMoneyDfResponse();

        if( StringUtils.isBlank(fid) || fid==null  ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单号不能空");
        }

        PayCloudDfMoneyExample example = new PayCloudDfMoneyExample();
        PayCloudDfMoneyExample.Criteria c = example.createCriteria();
        c.andFidEqualTo(fid);

        List<PayCloudDfMoney> list = payCloudDfMoneyMapper.selectByExample(example);
        List<CloudDfMoneyInterfaceInfo> infos = new ArrayList<>();
        for (PayCloudDfMoney payCloudDfMoney : list) {
            CloudDfMoneyInterfaceInfo cloudDfMoneyInterfaceInfo = new CloudDfMoneyInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoney.class, CloudDfMoneyInterfaceInfo.class, false);
            beanCopier.copy(payCloudDfMoney, cloudDfMoneyInterfaceInfo, null);

            infos.add(cloudDfMoneyInterfaceInfo);
        }

        getCloudMoneyDfResponse.setCount(payCloudDfMoneyMapper.countByExample(example));
        getCloudMoneyDfResponse.setList(infos);

        return getCloudMoneyDfResponse;

    }

    @Override
    public int addRec(CloudCompanyMoneyInfo cloudCompanyMoneyInfo){
        PayCloudCompanyMoney payCloudCompanyMoney = new PayCloudCompanyMoney();

        BeanCopier beanCopier = BeanCopier.create(CloudCompanyMoneyInfo.class, PayCloudCompanyMoney.class, false);
        beanCopier.copy(cloudCompanyMoneyInfo, payCloudCompanyMoney, null);

        return payCloudCompanyMoneyMapper.insert(payCloudCompanyMoney);
    }
}
