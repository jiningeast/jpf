package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudCompany;
import com.joiest.jpf.common.po.PayCloudDfMoney;
import com.joiest.jpf.common.po.PayCloudDfMoneyFreeze;
import com.joiest.jpf.common.po.PayCloudDfMoneyFreezeExample;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfMoneyFreezeMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfMoneyMapper;
import com.joiest.jpf.dto.GetCloudDfMoneyFreezeRequest;
import com.joiest.jpf.dto.GetCloudDfMoneyFreezeResponse;
import com.joiest.jpf.entity.CloudDfMoneyFreezeInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.CloudDfMoneyFreezeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CloudDfMoneyFreezeServiceFacadeImpl implements CloudDfMoneyFreezeServiceFacade {

    @Autowired
    private PayCloudDfMoneyFreezeMapper payCloudDfMoneyFreezeMapper;

    @Autowired
    private PayCloudDfMoneyMapper payCloudDfMoneyMapper;

    @Autowired
    private PayCloudCompanyMapper payCloudCompanyMapper;

    /**
     * 添加一条冻结信息
     */
    @Override
    public int add(CloudDfMoneyFreezeInfo freezeInfo) {
        PayCloudDfMoneyFreeze payCloudDfMoneyFreeze = new PayCloudDfMoneyFreeze();
        BeanCopier beanCopier = BeanCopier.create(CloudDfMoneyFreezeInfo.class, PayCloudDfMoneyFreeze.class, false);
        beanCopier.copy(freezeInfo, payCloudDfMoneyFreeze, null);

        return payCloudDfMoneyFreezeMapper.insertSelective(payCloudDfMoneyFreeze);
    }

    /**
     * 根据主键id查询单条信息
     */
    @Override
    public CloudDfMoneyFreezeInfo getRecordByPrimaryKey(String id){
        if ( StringUtils.isBlank(id) ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR);
        }

        PayCloudDfMoneyFreeze payCloudDfMoneyFreeze = payCloudDfMoneyFreezeMapper.selectByPrimaryKey(id);
        CloudDfMoneyFreezeInfo cloudDfMoneyFreezeInfo = new CloudDfMoneyFreezeInfo();

        if ( payCloudDfMoneyFreeze != null ){
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoneyFreeze.class, CloudDfMoneyFreezeInfo.class, false);
            beanCopier.copy(payCloudDfMoneyFreeze, cloudDfMoneyFreezeInfo, null);
        }

        return cloudDfMoneyFreezeInfo;
    }

    /**
     * 查询冻结信息
     */
    @Override
    public GetCloudDfMoneyFreezeResponse getRecords(GetCloudDfMoneyFreezeRequest request){
        GetCloudDfMoneyFreezeResponse response = new GetCloudDfMoneyFreezeResponse();

        PayCloudDfMoneyFreezeExample e = new PayCloudDfMoneyFreezeExample();
        PayCloudDfMoneyFreezeExample.Criteria c = e.createCriteria();
        if ( StringUtils.isNotBlank(request.getId()) ){
            c.andIdEqualTo(request.getId());
        }
        if ( StringUtils.isNotBlank(""+request.getPage()) ){
            e.setPageNo(request.getPage());
        }else{
            e.setPageNo(1);
        }
        if ( StringUtils.isNotBlank(""+request.getRows()) ){
            e.setPageSize(request.getRows());
        }else{
            e.setPageSize(10);
        }
        List<PayCloudDfMoneyFreeze> list = payCloudDfMoneyFreezeMapper.selectByExample(e);
        int count = payCloudDfMoneyFreezeMapper.countByExample(e);
        response.setCount(count);

        List<CloudDfMoneyFreezeInfo> infos = new ArrayList<>();
        for ( PayCloudDfMoneyFreeze payCloudDfMoneyFreeze:list ){
            CloudDfMoneyFreezeInfo cloudDfMoneyFreezeInfo = new CloudDfMoneyFreezeInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoneyFreeze.class, CloudDfMoneyFreezeInfo.class, false);
            beanCopier.copy(payCloudDfMoneyFreeze, cloudDfMoneyFreezeInfo, null);
            infos.add(cloudDfMoneyFreezeInfo);
        }
        response.setList(infos);

        return response;
    }

    /**
     * 根据主键id更新单条信息
     */
    @Override
    public int updateColumnByPrimaryKey(CloudDfMoneyFreezeInfo cloudDfMoneyFreezeInfo){
        PayCloudDfMoneyFreeze payCloudDfMoneyFreeze = new PayCloudDfMoneyFreeze();

        BeanCopier beanCopier = BeanCopier.create(CloudDfMoneyFreezeInfo.class, PayCloudDfMoneyFreeze.class, false);
        beanCopier.copy(cloudDfMoneyFreezeInfo, payCloudDfMoneyFreeze, null);

        return payCloudDfMoneyFreezeMapper.updateByPrimaryKeySelective(payCloudDfMoneyFreeze);
    }

    /**
     * 解冻操作
     */
    @Override
    @Transactional
    public int unfreeze(String freezeId,UserInfo userInfo){
        // 获取冻结详情
        CloudDfMoneyFreezeInfo cloudDfMoneyFreezeInfo = getRecordByPrimaryKey(freezeId);

        // 更新冻结表
        cloudDfMoneyFreezeInfo.setStatus(3);    // 1:冻结default 2:运营申请解冻 3:财务审核通过 4:财务拒绝
        cloudDfMoneyFreezeInfo.setMoneyStatus(2);   // 1:金额未解冻default 2:已解冻
        cloudDfMoneyFreezeInfo.setFinanceId(userInfo.getId());
        cloudDfMoneyFreezeInfo.setFinanceName(userInfo.getUserName());
        cloudDfMoneyFreezeInfo.setFinanceTime(new Date());
        updateColumnByPrimaryKey(cloudDfMoneyFreezeInfo);

        // 更新批次详情表
        PayCloudDfMoney payCloudDfMoney = new PayCloudDfMoney();
        payCloudDfMoney.setId(Long.parseLong(cloudDfMoneyFreezeInfo.getDfMoneyId()));
        payCloudDfMoney.setIsFreeze((byte)1);
        payCloudDfMoney.setUpdatetime(new Date());
        payCloudDfMoneyMapper.updateByPrimaryKeySelective(payCloudDfMoney);

        // 获取企业信息验证冻结校验码，余额校验码
        PayCloudCompany payCloudCompany = payCloudCompanyMapper.selectByPrimaryKey(cloudDfMoneyFreezeInfo.getCompanyId());
        Boolean isValidated = ToolUtils.ValidateCode(payCloudCompany.getFreezeCode(),payCloudCompany.getId(),""+payCloudCompany.getFreezeMoney());
        if ( !isValidated ){
            throw new JpfException("10010","冻结金额校验码错误");
        }
        isValidated = ToolUtils.ValidateCode(payCloudCompany.getCloudcode(),payCloudCompany.getId(),""+payCloudCompany.getCloudmoney());
        if ( !isValidated ){
            throw new JpfException("10010","账户余额校验码错误");
        }
        if ( payCloudCompany.getFreezeMoney().compareTo(cloudDfMoneyFreezeInfo.getFreezeMoney()) < 0 ){
            throw new JpfException("10010","解冻金额大于企业总冻结金额");
        }
        // 更新企业表
        Double companyFreezeMoney = payCloudCompany.getFreezeMoney().doubleValue();
        Double unfreezeMoney = cloudDfMoneyFreezeInfo.getFreezeMoney().doubleValue();
        Double companyMoney = payCloudCompany.getCloudmoney().doubleValue();
        payCloudCompany.setFreezeMoney(new BigDecimal(companyFreezeMoney - unfreezeMoney));
        payCloudCompany.setFreezeCode(ToolUtils.CreateCode(""+payCloudCompany.getFreezeMoney(),payCloudCompany.getId()));
        payCloudCompany.setCloudmoney(new BigDecimal(companyMoney +unfreezeMoney));
        payCloudCompany.setCloudcode(ToolUtils.CreateCode(""+payCloudCompany.getCloudmoney(),payCloudCompany.getId()));
        payCloudCompany.setUpdated(new Date());
        if ( payCloudCompany.getFreezeMoney().compareTo(new BigDecimal("0")) == 0 ){
            // 如果扣完此次的解冻金额后企业的总冻结金额是0则给企业解冻
            payCloudCompany.setIsFreeze((byte)1);
        }
        payCloudCompanyMapper.updateByPrimaryKeySelective(payCloudCompany);

        return 1;
    }
}
