package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.constant.EnumConstants;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayVirtual;
import com.joiest.jpf.common.po.PayVirtualExample;
import com.joiest.jpf.common.util.SHA1;
import com.joiest.jpf.dao.repository.mapper.generate.PayVirtualMapper;
import com.joiest.jpf.dto.GetValueResponse;
import com.joiest.jpf.dto.LoginVerifyResponse;
import com.joiest.jpf.entity.VirtualInfo;
import com.joiest.jpf.facade.VirtualServiceFacade;
import com.joiest.jpf.common.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class VirtualServiceFacadeImpl implements VirtualServiceFacade {

    private static final Logger logger = LogManager.getLogger(VirtualServiceFacadeImpl.class);

    @Autowired
    private PayVirtualMapper payVirtualMapper;

    @Override
    public List<VirtualInfo> getVirtual(String cat, String intro, long pageNo, long pageSize) {
        if (pageNo<=0) {
            pageNo = 1;
        }
        if (pageSize<=0) {
            pageSize = 20;
        }
        PayVirtualExample example = new PayVirtualExample();
        example.setPageNo(pageNo);
        example.setPageSize(pageSize);
        example.setOrderByClause("catid DESC");
        PayVirtualExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotBlank(cat)) {
            c.andCatLike( "%" + cat.trim() + "%" );
        }
        List<PayVirtual> payVirtualList = payVirtualMapper.selectByExample(example);
        List<VirtualInfo> VirtualInfoList = new ArrayList<>();
        for (PayVirtual PayVirtual : payVirtualList) {
            VirtualInfo VirtualInfo = new VirtualInfo();
            BeanCopier beanCopier = BeanCopier.create(PayVirtual.class, VirtualInfo.class, false);
            beanCopier.copy(PayVirtual, VirtualInfo, null);
            VirtualInfoList.add(VirtualInfo);
        }
        return VirtualInfoList;
    }

    @Override
    public int getVirtualCount(String cat){
        PayVirtualExample example = new PayVirtualExample();
        PayVirtualExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotBlank(cat)) {
            c.andCatLike(cat);
        }
        return payVirtualMapper.countByExample(example);
    }

    @Override
    public JpfResponseDto addVirtual(String cat, String intro){
        //不能置空的操作
        if (StringUtils.isBlank(cat)){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"虚拟分类的名称不能为空");
        }
        if (StringUtils.isBlank(intro)){
            throw  new JpfException(JpfErrorInfo.INVALID_PARAMETER,"虚拟分类简介不能为空");
        }
        //new 定义
        PayVirtualExample example = new PayVirtualExample();
        PayVirtualExample.Criteria c = example.createCriteria();
        c.andCatEqualTo(cat.trim());
        List<PayVirtual> payVirtualList = payVirtualMapper.selectByExample(example);
        if (payVirtualList != null && !payVirtualList.isEmpty()){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"虚拟分类已经存在");
        }
        PayVirtual payVirtual = new PayVirtual();
        payVirtual.setCat(cat);
        payVirtual.setIntro(SHA1.getInstance().getMySHA1Code(intro));
        int index = payVirtualMapper.insertSelective(payVirtual);
        if(index !=1){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER.DAL_ERROR,"添加失败");
        }
        return new JpfResponseDto();
    }
}
