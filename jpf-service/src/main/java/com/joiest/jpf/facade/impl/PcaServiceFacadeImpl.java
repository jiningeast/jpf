package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.dao.repository.mapper.custom.PayOrderPayMerMessageCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayOrderPayMessageCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderPayMerMessageMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayOrderPayMessageMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayPcaMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PaySmsMessageMapper;
import com.joiest.jpf.dto.AddSmsMessageRequest;
import com.joiest.jpf.dto.ModifyPayMessageRequest;
import com.joiest.jpf.dto.ModifyPayOrderPayMerRequest;
import com.joiest.jpf.entity.PcaInfo;
import com.joiest.jpf.facade.PcaServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class PcaServiceFacadeImpl implements PcaServiceFacade {

    @Autowired
    private PayPcaMapper payPcaMapper;

    //添加短信
    @Autowired
    private PaySmsMessageMapper paySmsMessageMapper;

    //添加编辑聚合支付流水
    @Autowired
    private PayOrderPayMessageMapper payOrderPayMessageMapper;

    @Autowired
    private PayOrderPayMessageCustomMapper payOrderPayMessageCustomMapper;

    //添加编辑商户支付流水
    @Autowired
    private PayOrderPayMerMessageMapper payOrderPayMerMessageMapper;

    @Autowired
    private PayOrderPayMerMessageCustomMapper payOrderPayMerMessageCustomMapper;

    @Override
    public List<PcaInfo> getPcas(String pid) {

        PayPcaExample example = new PayPcaExample();
        PayPcaExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(pid)) {
            criteria.andPidEqualTo(Integer.valueOf(pid));
        }else{
            criteria.andPidEqualTo(0);
        }
        List<PayPca> pcas = payPcaMapper.selectByExample(example);
        List<PcaInfo> pcaInfos = new ArrayList<>();
        for (PayPca payPca : pcas) {
            PcaInfo pcaInfo = new PcaInfo();
            BeanCopier beanCopier = BeanCopier.create(PayPca.class, PcaInfo.class, false);
            beanCopier.copy(payPca, pcaInfo, null);
            pcaInfos.add(pcaInfo);
        }
        return pcaInfos;
    }

    @Override
    public List<PcaInfo> getPcasInner(String pid) {

        PayPcaExample example = new PayPcaExample();
        PayPcaExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(pid)) {
            criteria.andPidEqualTo(Integer.valueOf(pid));
        }else{
            criteria.andPidEqualTo(0);
        }
        criteria.andCatidLessThan(3358);
        List<PayPca> pcas = payPcaMapper.selectByExample(example);
        List<PcaInfo> pcaInfos = new ArrayList<>();
        for (PayPca payPca : pcas) {
            PcaInfo pcaInfo = new PcaInfo();
            BeanCopier beanCopier = BeanCopier.create(PayPca.class, PcaInfo.class, false);
            beanCopier.copy(payPca, pcaInfo, null);
            pcaInfos.add(pcaInfo);
        }
        return pcaInfos;
    }


    /**
     * 获取地区通过catid
     * @param catid
     * @return
     */
    @Override
    public PcaInfo getPcaByCatid(Integer catid){

        PayPca payPca = payPcaMapper.selectByPrimaryKey(catid);
        if(payPca == null){

            return null;
        }
        PcaInfo pcaInfo = new PcaInfo();

        BeanCopier beanCopier = BeanCopier.create(PayPca.class,PcaInfo.class,false);
        beanCopier.copy(payPca,pcaInfo,null);
        return pcaInfo;
    }

    @Override
    public List<PcaInfo> getPca(long page, long pageSize) {
        PayPcaExample example = new PayPcaExample();
        if ( page <= 0 )
        {
            page = 1;
        }else
        {
            example.setPageNo(page);
        }
        if ( pageSize <= 0 )
        {
            pageSize = 10;
        }else
        {
            example.setPageSize(pageSize);
        }
        List<PayPca> pcas = payPcaMapper.selectByExample(example);
        List<PcaInfo> pcaInfos = new ArrayList<>();
        for (PayPca payPca : pcas) {
            PcaInfo pcaInfo = new PcaInfo();
            BeanCopier beanCopier = BeanCopier.create(PayPca.class, PcaInfo.class, false);
            beanCopier.copy(payPca, pcaInfo, null);
            pcaInfos.add(pcaInfo);
        }

        return pcaInfos;
    }

    @Override
    public Integer getPcaCount() {
        PayPcaExample example = new PayPcaExample();
        Integer count = payPcaMapper.countByExample(example);

        return count;
    }

    /**
     * 添加发送短信记录
     * @param request
     * @return
     */
    @Override
    public int addSmsMessage(AddSmsMessageRequest request)
    {
        PaySmsMessage paySmsMessage = new PaySmsMessage();
        BeanCopier beanCopier = BeanCopier.create(AddSmsMessageRequest.class, PaySmsMessage.class, false);
        beanCopier.copy(request, paySmsMessage, null);
        return paySmsMessageMapper.insertSelective(paySmsMessage);
    }

    /**
     * 添加聚合订单回调信息
     */
    public int addPayMessage(ModifyPayMessageRequest request)
    {
        PayOrderPayMessage payOrderPayMessage = new PayOrderPayMessage();
        BeanCopier beanCopier = BeanCopier.create(ModifyPayMessageRequest.class, PayOrderPayMessage.class, false);
        beanCopier.copy(request,payOrderPayMessage,null);
        int res = payOrderPayMessageCustomMapper.insertSelective(payOrderPayMessage);
        return new Long(payOrderPayMessage.getId()).intValue();
    }

    /**
     *更新聚合订单回调信息
     */
    public int modifyPayMessage(ModifyPayMessageRequest request)
    {
        PayOrderPayMessage payOrderPayMessage = new PayOrderPayMessage();
        BeanCopier beanCopier = BeanCopier.create(ModifyPayMessageRequest.class, PayOrderPayMessage.class, false);
        beanCopier.copy(request,payOrderPayMessage,null);
        PayOrderPayMessageExample example = new PayOrderPayMessageExample();
        PayOrderPayMessageExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(request.getId());
        return payOrderPayMessageCustomMapper.updateByExampleSelective(payOrderPayMessage,example);
    }


    /**
     * 添加商户订单回调信息
     */
    public int addPayMerMessage(ModifyPayOrderPayMerRequest request)
    {
        PayOrderPayMerMessage payOrderPayMerMessage = new PayOrderPayMerMessage();
        BeanCopier beanCopier = BeanCopier.create(ModifyPayOrderPayMerRequest.class, PayOrderPayMerMessage.class, false);
        beanCopier.copy(request,payOrderPayMerMessage,null);
        int res = payOrderPayMerMessageCustomMapper.insertSelective(payOrderPayMerMessage);
        return new Long(payOrderPayMerMessage.getId()).intValue();
    }

    /**
     *更新商户订单回调信息
     */
    public int modifyPayMerMessage(ModifyPayOrderPayMerRequest request)
    {
        PayOrderPayMerMessage payOrderPayMerMessage = new PayOrderPayMerMessage();
        BeanCopier beanCopier = BeanCopier.create(ModifyPayOrderPayMerRequest.class, PayOrderPayMerMessage.class, false);
        beanCopier.copy(request,payOrderPayMerMessage,null);
        PayOrderPayMerMessageExample example = new PayOrderPayMerMessageExample();
        PayOrderPayMerMessageExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(request.getId());
        return payOrderPayMerMessageCustomMapper.updateByExampleSelective(payOrderPayMerMessage,example);
    }

    public PcaInfo getCats(String catid){
        PayPcaExample example = new PayPcaExample();
        PayPcaExample.Criteria criteria = example.createCriteria();
        criteria.andCatidEqualTo(Integer.valueOf(catid));
        List<PayPca> pcas = payPcaMapper.selectByExample(example);
        if(pcas.size() <= 0 || pcas == null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "无效catid");
        }

        PcaInfo pcaInfo = new PcaInfo();
        for (PayPca one : pcas)
        {
            BeanCopier beanCopier = BeanCopier.create(PayPca.class, PcaInfo.class, false);
            beanCopier.copy(one, pcaInfo, null);
        }
        return pcaInfo;
    }
}
