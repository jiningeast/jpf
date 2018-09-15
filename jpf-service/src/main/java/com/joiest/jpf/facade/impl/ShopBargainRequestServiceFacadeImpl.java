package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayShopBargainRequestCustom;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopBargainRequest;
import com.joiest.jpf.common.po.PayShopBargainRequestExample;
import com.joiest.jpf.common.po.PayShopCompany;
import com.joiest.jpf.common.po.PayShopCompanyExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopBargainRequestCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBargainRequestMapper;
import com.joiest.jpf.dto.GetShopBargainRequestRequest;
import com.joiest.jpf.dto.GetShopBargainRequestResponse;
import com.joiest.jpf.entity.ShopBargainRequestInfo;
import com.joiest.jpf.facade.ShopBargainRequestServiceFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopBargainRequestServiceFacadeImpl implements ShopBargainRequestServiceFacade {

    @Autowired
    private PayShopBargainRequestMapper payShopBargainRequestMapper;

    @Autowired
    private PayShopBargainRequestCustomMapper payShopBargainRequestCustomMapper;

    @Override
    public GetShopBargainRequestResponse getList(GetShopBargainRequestRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayShopBargainRequestExample example = new PayShopBargainRequestExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("ID DESC");

        PayShopBargainRequestExample.Criteria c = example.createCriteria();

        if(request.getStatus()!=null && request.getStatus().toString()!=""){
            c.andStatusEqualTo(request.getStatus());
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(), DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }

        List<PayShopBargainRequestCustom> list = payShopBargainRequestCustomMapper.selectByJoinExample(example);
        List<ShopBargainRequestInfo> infoList = new ArrayList<>();
        for (PayShopBargainRequestCustom one : list)
        {
            ShopBargainRequestInfo info = new ShopBargainRequestInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopBargainRequestCustom.class, ShopBargainRequestInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetShopBargainRequestResponse response = new GetShopBargainRequestResponse();
        response.setList(infoList);
        int count = payShopBargainRequestCustomMapper.countByExample(example);
        response.setCount(count);
        return response;
    }


    /**
     * 停用公司 更改状态 1启用 0停用
     */
    @Override
    public JpfResponseDto delShopBargain(String id, int type)
    {
        if (StringUtils.isBlank(id) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "ID不能为空");
        }
        //查询当前添加的是否存在
        PayShopBargainRequestExample example= new PayShopBargainRequestExample();
        PayShopBargainRequestExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(id);

        List<PayShopBargainRequest> payShopBrainList = payShopBargainRequestMapper.selectByExample(example);
        if(payShopBrainList.isEmpty() || payShopBrainList==null){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "此收购信息不存在");
        }
        Byte defaultStatus =payShopBrainList.get(0).getStatus();
        PayShopBargainRequest payShopBargainRequest = new PayShopBargainRequest();
        Byte status=-1;
        if(type==2){
            status = 0;
            if(defaultStatus==status){
                throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "当前已为关闭状态");
            }
        }else if (type==1){
            status = 1;
            if(defaultStatus==status){
                throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "当前已为开启状态");
            }
        }
        payShopBargainRequest.setStatus(status);
        payShopBargainRequestMapper.updateByExampleSelective(payShopBargainRequest,example);
        return new JpfResponseDto();
    }

    /**
     * 查询 买家信息
     */
    @Override
    public List<PayShopBargainRequest> getOne(GetShopBargainRequestRequest request)
    {
        if (StringUtils.isBlank(request.getCustomerId()) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "用户编号不能为空");
        }
        //查询当前添加的是否存在
        PayShopBargainRequestExample example= new PayShopBargainRequestExample();
        PayShopBargainRequestExample.Criteria c = example.createCriteria();
        c.andCustomerIdEqualTo(request.getCustomerId());

        List<PayShopBargainRequest> payShopBrainList = payShopBargainRequestMapper.selectByExample(example);
        if(payShopBrainList.isEmpty() || payShopBrainList==null){
            return null;
        }

        return payShopBrainList;
    }

    /**
     * 添加卖家信息
     */
    @Override
    public JpfResponseDto add(GetShopBargainRequestRequest request)
    {

        //查询当前添加的是否存在
        PayShopBargainRequestExample example= new PayShopBargainRequestExample();
        PayShopBargainRequestExample.Criteria c = example.createCriteria();
        c.andCustomerIdEqualTo(request.getCustomerId());

        PayShopBargainRequest record = new PayShopBargainRequest();
        record.setCustomerId(request.getCustomerId());
        record.setOffRate(request.getOffRate());
        record.setMinDou(request.getMinDou());
        record.setStatus(request.getStatus());

        int count = 0;
        // 更新买家信息
        if(request.getId() !=null && StringUtils.isNotBlank(request.getId())){
            record.setId(request.getId());
            count = payShopBargainRequestMapper.updateByPrimaryKeySelective(record);
        }else{// 添加买家信息
            record.setAddtime(new Date());
            count = payShopBargainRequestMapper.insertSelective(record);
        }
        if( count !=1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
        }

        return new JpfResponseDto();
    }

}
