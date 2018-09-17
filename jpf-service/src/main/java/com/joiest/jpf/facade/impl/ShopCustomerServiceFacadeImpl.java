package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopBargainOrder;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.po.PayShopCustomerExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCustomerMapper;
import com.joiest.jpf.dto.GetShopCustomerRequest;
import com.joiest.jpf.dto.GetShopCustomerResponse;
import com.joiest.jpf.entity.ShopCustomerInfo;
import com.joiest.jpf.facade.ShopCustomerServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopCustomerServiceFacadeImpl implements ShopCustomerServiceFacade {

    @Autowired
    private PayShopCustomerMapper payShopCustomerMapper;

    /**
     * 公司列表---后台
     */
    @Override
    public GetShopCustomerResponse getList(GetShopCustomerRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayShopCustomerExample example = new PayShopCustomerExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("addtime DESC");

        PayShopCustomerExample.Criteria c = example.createCriteria();
        if ( StringUtils.isNotBlank(request.getNickname()))
        {
            String  nickname="";
            try {
                nickname = URLEncoder.encode(request.getNickname().toString(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            c.andNicknameEqualTo( nickname);
        }
        if( StringUtils.isNotBlank(request.getCompanyName())){
            c.andCompanyNameEqualTo(request.getCompanyName());
        }
        if( StringUtils.isNotBlank(request.getPhone())){
            c.andPhoneEqualTo(request.getPhone());
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }
        if(request.getStatus()!=null && request.getStatus().toString()!=""){
            c.andStatusEqualTo(request.getStatus());
        }
        if(request.getIsVerify()!=null && request.getIsVerify().toString()!=""){
            c.andIsVerifyEqualTo(request.getIsVerify());
        }
        List<PayShopCustomer> list = payShopCustomerMapper.selectByExample(example);
        List<ShopCustomerInfo> infoList = new ArrayList<>();
        for (PayShopCustomer one : list)
        {
            ShopCustomerInfo info = new ShopCustomerInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCustomer.class, ShopCustomerInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetShopCustomerResponse response = new GetShopCustomerResponse();
        response.setList(infoList);
        int count = payShopCustomerMapper.countByExample(example);
        response.setCount(count);
        return response;
    }



    /**
     * 停用公司 更改状态 1启用 0停用
     */
    @Override
    public JpfResponseDto delCompanyCustomer(String id,int type)
    {
        if ( StringUtils.isBlank(id) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        //查询当前添加的是否存在
        PayShopCustomerExample example= new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(id);

        List<PayShopCustomer> payShopCustomerList = payShopCustomerMapper.selectByExample(example);
        if(payShopCustomerList.isEmpty() || payShopCustomerList==null){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "此条记录不存在");
        }
        Byte defaultStatus =payShopCustomerList.get(0).getStatus();
        PayShopCustomer payShopCustomer = new PayShopCustomer();
        Byte status=-1;
        if(type==2){
            status = 0;
            if(defaultStatus==status){
                throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "该客户已被冻结");
            }
        }else if (type==1){
            status = 1;
            if(defaultStatus==status){
                throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "该客户已被恢复正常");
            }
        }
        // 创建日期
        Date d = new Date();
        payShopCustomer.setStatus(status);
        payShopCustomer.setUpdatetime(d);
        payShopCustomerMapper.updateByExampleSelective(payShopCustomer,example);
        return new JpfResponseDto();
    }

    /**
     * 停用公司 更改状态 1启用 0停用
     */
    @Override
    public JpfResponseDto editCompanyCustomer(GetShopCustomerRequest request)
    {
        if ( StringUtils.isBlank(request.getId()) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        //查询当前添加的是否存在
        PayShopCustomerExample example= new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(request.getId());

        List<PayShopCustomer> payShopCustomerList = payShopCustomerMapper.selectByExample(example);
        if(payShopCustomerList.isEmpty() || payShopCustomerList==null){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "此条记录不存在");
        }
        Byte defaultType =payShopCustomerList.get(0).getType();
        PayShopCustomer payShopCustomer = new PayShopCustomer();
        if(defaultType==request.getType()){
            String word = defaultType == 1 ? "特殊用户":"正常用户";
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "不能更新状态为："+word);
        }

        // 创建日期
        Date d = new Date();
        payShopCustomer.setType(request.getType());
        payShopCustomer.setUpdatetime(d);
        int count = payShopCustomerMapper.updateByExampleSelective(payShopCustomer,example);
        if(count != 1 ){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "更新失败");
        }
        return new JpfResponseDto();
    }

    @Override
    public PayShopCustomer getCustomerByPhone(String phone){
        PayShopCustomerExample e = new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = e.createCriteria();
        c.andPhoneEqualTo(phone);
        List<PayShopCustomer> list = payShopCustomerMapper.selectByExample(e);

        return list.get(0);
    }

    @Override
    public PayShopCustomer getCustomerById(String id) {
        PayShopCustomerExample e = new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = e.createCriteria();
        c.andIdEqualTo(id);
        List<PayShopCustomer> list = payShopCustomerMapper.selectByExample(e);


        try {
            list.get(0).setNickname( URLDecoder.decode( list.get(0).getNickname(),"UTF-8" ));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        return list.get(0);
    }

    /**
     * 更新用户信息
     * */
    public int upCustomerInfo(ShopCustomerInfo shopCustomerInfo){

       PayShopCustomer payShopCustomer = new PayShopCustomer();

       BeanCopier beanCopier = BeanCopier.create(ShopCustomerInfo.class,PayShopCustomer.class,false);
       beanCopier.copy(shopCustomerInfo,payShopCustomer,null);

        return payShopCustomerMapper.updateByPrimaryKeySelective(payShopCustomer);
    }


}
