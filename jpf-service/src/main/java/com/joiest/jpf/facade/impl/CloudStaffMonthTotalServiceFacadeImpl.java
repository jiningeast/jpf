package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayCloudStaffMonthTotalCustom;
import com.joiest.jpf.common.po.PayCloudCompanyStaff;
import com.joiest.jpf.common.po.PayCloudCompanyStaffExample;
import com.joiest.jpf.common.po.PayCloudStaffMonthTotal;
import com.joiest.jpf.common.po.PayCloudStaffMonthTotalExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudStaffMonthTotalCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyStaffMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudStaffMonthTotalMapper;
import com.joiest.jpf.dto.GetCloudStaffMonthTotalRequest;
import com.joiest.jpf.dto.GetCloudStaffMonthTotalResponse;
import com.joiest.jpf.dto.ModifyCloudStaffMonthTotalRequest;
import com.joiest.jpf.entity.CloudStaffMonthTotalInfo;
import com.joiest.jpf.entity.CloudStaffMonthTotalInterfaceInfo;
import com.joiest.jpf.facade.CloudStaffMonthTotalServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class CloudStaffMonthTotalServiceFacadeImpl implements CloudStaffMonthTotalServiceFacade {

    @Autowired
    private PayCloudStaffMonthTotalMapper payCloudStaffMonthTotalMapper;

    @Autowired
    private PayCloudStaffMonthTotalCustomMapper payCloudStaffMonthTotalCustomMapper;

    @Autowired

    private PayCloudCompanyStaffMapper  payCloudCompanyStaffMapper;

    @Override
    public CloudStaffMonthTotalInterfaceInfo getStaffMonthTotal(String month, Long busstaffid)
    {
        PayCloudStaffMonthTotalExample example = new PayCloudStaffMonthTotalExample();
        PayCloudStaffMonthTotalExample.Criteria c = example.createCriteria();
        c.andMonthEqualTo(month);
        c.andBusstaffidEqualTo(busstaffid);
        List<PayCloudStaffMonthTotal> list = payCloudStaffMonthTotalMapper.selectByExample(example);
        if ( list.isEmpty() || list == null )
        {
            return null;
        }
        CloudStaffMonthTotalInterfaceInfo info = new CloudStaffMonthTotalInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayCloudStaffMonthTotal.class, CloudStaffMonthTotalInterfaceInfo.class, false);
        beanCopier.copy(list.get(0), info, null);

        return info;
    }

    @Override
    public int addStaffMonthTotal(ModifyCloudStaffMonthTotalRequest request) {
        PayCloudStaffMonthTotal payCloudStaffMonthTotal = new PayCloudStaffMonthTotal();
        BeanCopier beanCopier = BeanCopier.create(ModifyCloudStaffMonthTotalRequest.class, PayCloudStaffMonthTotal.class, false);
        beanCopier.copy(request, payCloudStaffMonthTotal, null);

        return payCloudStaffMonthTotalMapper.insertSelective(payCloudStaffMonthTotal);
    }

    @Override
    public int updateStaffMonthTotal(ModifyCloudStaffMonthTotalRequest request) {
        PayCloudStaffMonthTotal payCloudStaffMonthTotal = new PayCloudStaffMonthTotal();
        BeanCopier beanCopier = BeanCopier.create(ModifyCloudStaffMonthTotalRequest.class, PayCloudStaffMonthTotal.class, false);
        beanCopier.copy(request, payCloudStaffMonthTotal, null);
        PayCloudStaffMonthTotalExample example = new PayCloudStaffMonthTotalExample();
        PayCloudStaffMonthTotalExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(request.getId());

        return payCloudStaffMonthTotalMapper.updateByExampleSelective(payCloudStaffMonthTotal, example);
    }

    /*
    * 获取限额的列表
    */
    @Override
    public GetCloudStaffMonthTotalResponse getList(GetCloudStaffMonthTotalRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayCloudStaffMonthTotalExample example = new PayCloudStaffMonthTotalExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("created DESC");

        PayCloudStaffMonthTotalExample.Criteria c = example.createCriteria();

        if ( StringUtils.isNotBlank(request.getNickname()))
        {
            //当前用户的id搜索的转换
            PayCloudCompanyStaffExample exStaff=new PayCloudCompanyStaffExample();
            PayCloudCompanyStaffExample.Criteria cstaff=exStaff.createCriteria();
            cstaff.andNicknameLike("%"+request.getNickname()+"%");
            List<PayCloudCompanyStaff> listStaff=payCloudCompanyStaffMapper.selectByExample(exStaff);
            if(listStaff !=null && !listStaff.isEmpty()){
                String  staffid=listStaff.get(0).getId();
                c.andBusstaffidEqualTo(Long.valueOf(staffid));
            }

        }
        if( StringUtils.isNotBlank(request.getMonth())){
            c.andMonthEqualTo(request.getMonth());
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andCreatedGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(), DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andCreatedLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }
        if(request.getStatus()!=null && request.getStatus().toString()!=""){
            c.andStatusEqualTo(request.getStatus());
        }
        List<PayCloudStaffMonthTotalCustom> list = payCloudStaffMonthTotalCustomMapper.selectByExampleJoin(example);
        List<CloudStaffMonthTotalInfo> infoList = new ArrayList<>();
        for (PayCloudStaffMonthTotalCustom one : list)
        {
            CloudStaffMonthTotalInfo info = new CloudStaffMonthTotalInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudStaffMonthTotalCustom.class, CloudStaffMonthTotalInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetCloudStaffMonthTotalResponse response = new GetCloudStaffMonthTotalResponse();
        response.setList(infoList);
        int count = payCloudStaffMonthTotalCustomMapper.countByExample(example);
        response.setCount(count);
        return response;
    }
}
