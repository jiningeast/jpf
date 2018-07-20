package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayCloudDfMoneyCustom;
import com.joiest.jpf.common.custom.PayCloudDfMoneyInterfaceCustom;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudDfMoney;
import com.joiest.jpf.common.po.PayCloudDfMoneyExample;
import com.joiest.jpf.common.util.BigDecimalCalculateUtils;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudDfMoneyCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudDfMoneyInterfaceCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfMoneyMapper;
import com.joiest.jpf.dto.CloudDfMoneyRequest;
import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.entity.CloudDfMoneyInfo;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigDecimal;
import java.util.*;

public class CloudDfMoneyServiceFacadeImpl implements CloudDfMoneyServiceFacade {

    @Autowired
    private PayCloudDfMoneyMapper payCloudDfMoneyMapper;

    @Autowired
    private PayCloudDfMoneyInterfaceCustomMapper payCloudDfMoneyInterfaceCustomMapper;

    @Autowired
    private PayCloudDfMoneyCustomMapper payCloudDfMoneyCustomMapper;

    @Override
    public GetCloudMoneyDfResponse getDfMoneyList(String start, String end, String uid, long pageNo, long pageSize, int flag)
    {
        PayCloudDfMoneyExample e = new PayCloudDfMoneyExample();
        if (flag == 1 )
        {
            e.setPageNo(pageNo);
            e.setPageSize(pageSize);
        }
        e.setOrderByClause("addtime DESC");

        PayCloudDfMoneyExample.Criteria c = e.createCriteria();
        c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(start,DateUtils.DATEFORMATLONG));
        c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(end,DateUtils.DATEFORMATLONG));
        c.andUidEqualTo(Long.parseLong(uid));

        List<PayCloudDfMoney> list = payCloudDfMoneyMapper.selectByExample(e);
        List<CloudDfMoneyInterfaceInfo> listnew = new ArrayList<>();

        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        Double monthTotal = 0.00;
        for (PayCloudDfMoney one : list)
        {
            CloudDfMoneyInterfaceInfo info = new CloudDfMoneyInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoney.class, CloudDfMoneyInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            listnew.add(info);

            monthTotal = BigDecimalCalculateUtils.add(new Double(one.getCommoney().toString()), monthTotal);
        }
        GetCloudMoneyDfResponse response = new GetCloudMoneyDfResponse();
        response.setList(listnew);
        response.setMonthTotal(new BigDecimal(monthTotal));
        return response;
    }

    @Override
    public Double getUserDfTotalMoney(String uid)
    {
        PayCloudDfMoneyExample e = new PayCloudDfMoneyExample();
        e.setOrderByClause("addtime DESC");

        PayCloudDfMoneyExample.Criteria c = e.createCriteria();
        c.andUidEqualTo(Long.parseLong(uid));

        List<PayCloudDfMoney> list = payCloudDfMoneyMapper.selectByExample(e);
        List<CloudDfMoneyInterfaceInfo> listnew = new ArrayList<>();

        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        Double monthTotal = 0.00;
        for (PayCloudDfMoney one : list)
        {
            monthTotal = BigDecimalCalculateUtils.add(new Double(one.getCommoney().toString()), monthTotal);
        }
        return monthTotal;
    }

    @Override
    public List<CloudDfMoneyInterfaceInfo> getUserMonthList(Long uid)
    {
        PayCloudDfMoneyExample example = new PayCloudDfMoneyExample();
        example.setOrderByClause("addtime DESC");
        PayCloudDfMoneyExample.Criteria c = example.createCriteria();
        c.andUidEqualTo(uid);
        List<PayCloudDfMoneyInterfaceCustom> list = payCloudDfMoneyInterfaceCustomMapper.getUserMonthList(example);

        List<CloudDfMoneyInterfaceInfo> response = new ArrayList<>();
        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        for (PayCloudDfMoneyInterfaceCustom one : list)
        {
            CloudDfMoneyInterfaceInfo info = new CloudDfMoneyInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoneyInterfaceCustom.class, CloudDfMoneyInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            response.add(info);
        }

        return response;
    }

    /*
     **根据订单号更新 代付明细状态
     * fid  订单号
     */
    @Override
    public JpfResponseDto updateDfRecordsByFid(PayCloudDfMoney record,String fid){

        PayCloudDfMoneyExample example = new PayCloudDfMoneyExample();
        PayCloudDfMoneyExample.Criteria c = example.createCriteria();

        if( StringUtils.isBlank(fid) || fid==null  ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单号不能为空");
        }
        c.andFidEqualTo(fid);

        int count = payCloudDfMoneyMapper.updateByExample(record,example);
        if(count !=1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "数据更新失败");
        }

        return new JpfResponseDto();


    }


    //获取充值记录数据通过主键
    public CloudDfMoneyInterfaceInfo getDfMoneyById(Long id){


        PayCloudDfMoney payCloudDfMoney = payCloudDfMoneyMapper.selectByPrimaryKey(id);

        if(payCloudDfMoney == null) return null;

        CloudDfMoneyInterfaceInfo cloudDfMoneyInterfaceInfo = new CloudDfMoneyInterfaceInfo();

        BeanCopier beanCopier = BeanCopier.create( PayCloudDfMoney.class, CloudDfMoneyInterfaceInfo.class, false);

        beanCopier.copy(payCloudDfMoney, cloudDfMoneyInterfaceInfo, null);

        return cloudDfMoneyInterfaceInfo;
    }
    //更新代付状态
    public int updateDfMoneyActive(Map<String,String> dfMoney, Long id){

        PayCloudDfMoney payCloudDfMoney = new PayCloudDfMoney();

        payCloudDfMoney.setIsActive(new Integer(dfMoney.get("is_active")));
        payCloudDfMoney.setUpdatetime(new Date());
        payCloudDfMoney.setId(id);


        return payCloudDfMoneyMapper.updateByPrimaryKeySelective(payCloudDfMoney);
    }

    //更新代付状态
    public int updateDfMoneyActiveById(CloudDfMoneyRequest request,Long id){

        PayCloudDfMoney payCloudDfMoney = new PayCloudDfMoney();
        if( request.getMontype() != null ){
            payCloudDfMoney.setMontype(request.getMontype());
        }
        payCloudDfMoney.setUpdatetime(new Date());
        payCloudDfMoney.setId(id);

        return payCloudDfMoneyMapper.updateByPrimaryKeySelective(payCloudDfMoney);
    }

    //根据主键字符串更新代付明细
    public JpfResponseDto updateDfRecordsByids(PayCloudDfMoney record, List<Long> ids){

        PayCloudDfMoneyExample example = new PayCloudDfMoneyExample();
        PayCloudDfMoneyExample.Criteria c = example.createCriteria();
        c.andIdIn(ids);


        int count = payCloudDfMoneyMapper.updateByExampleSelective(record,example);
        if( count <= 0 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "代付操作失败");
        }
        return new JpfResponseDto();
    }

    @Override
    public GetCloudMoneyDfResponse getDfDetailList(String batchId, String dfid, long pageNo, long pageSize, int flag)
    {
        PayCloudDfMoneyExample e = new PayCloudDfMoneyExample();
        if (flag == 1 )
        {
            e.setPageNo(pageNo);
            e.setPageSize(pageSize);
        }

        e.setOrderByClause("id ASC");

        PayCloudDfMoneyExample.Criteria c = e.createCriteria();
        c.andCompanyMoneyIdEqualTo(batchId);
        c.andIsActiveEqualTo(1);
        if ( !dfid.equals("0") )
        {
            String[] dfid_arr = dfid.split(",");
            List<String> dfidList_str = Arrays.asList(dfid_arr);
            List<Long> dfidList_Long = new ArrayList<>();
            for (String one : dfidList_str)
            {
                dfidList_Long.add(Long.parseLong(one));
            }
            c.andIdIn(dfidList_Long);
        }

        List<PayCloudDfMoney> list = payCloudDfMoneyMapper.selectByExample(e);
        List<CloudDfMoneyInterfaceInfo> listnew = new ArrayList<>();

        if ( list.isEmpty() || list == null )
        {
            return null;
        }

        Double monthTotal = 0.00;
        for (PayCloudDfMoney one : list)
        {
            CloudDfMoneyInterfaceInfo info = new CloudDfMoneyInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoney.class, CloudDfMoneyInterfaceInfo.class, false);
            beanCopier.copy(one, info, null);
            listnew.add(info);

            monthTotal = BigDecimalCalculateUtils.add(new Double(one.getCommoney().toString()), monthTotal);
        }
        GetCloudMoneyDfResponse response = new GetCloudMoneyDfResponse();
        response.setList(listnew);
        response.setMonthTotal(new BigDecimal(monthTotal.toString()));
        response.setCount(list.size());
        return response;
    }


    @Override
    public long addDfMoney(CloudDfMoneyInfo cloudDfMoneyInfo){
        PayCloudDfMoney payCloudDfMoney = new PayCloudDfMoney();
        BeanCopier beanCopier = BeanCopier.create(CloudDfMoneyInfo.class, PayCloudDfMoney.class, false);
        beanCopier.copy(cloudDfMoneyInfo, payCloudDfMoney, null);

        payCloudDfMoneyCustomMapper.insert(payCloudDfMoney);

        return payCloudDfMoney.getId();
    }

    @Override
    public List<CloudDfMoneyInfo> getAllBySective(CloudDfMoneyRequest request){

        PayCloudDfMoneyExample example = new PayCloudDfMoneyExample();
        PayCloudDfMoneyExample.Criteria c = example.createCriteria();
        if( request.getCompanyMoneyId() != null ){
            c.andCompanyMoneyIdEqualTo(request.getCompanyMoneyId());
        }
        if( request.getIdsStr() != null ){
            c.andIdIn(request.getIdsStr());
        }

        List<PayCloudDfMoneyCustom> list = payCloudDfMoneyCustomMapper.selectJoinCompanyStaff(example);
        List<CloudDfMoneyInfo> infos = new ArrayList<>();
        for (PayCloudDfMoneyCustom payCloudDfMoneyCustom : list) {
            CloudDfMoneyInfo cloudDfMoneyInfo = new CloudDfMoneyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoneyCustom.class, CloudDfMoneyInfo.class, false);
            beanCopier.copy(payCloudDfMoneyCustom, cloudDfMoneyInfo, null);

            infos.add(cloudDfMoneyInfo);
        }
        /*List<PayCloudDfMoney> list = payCloudDfMoneyMapper.selectByExample(example);
        List<CloudDfMoneyInfo> infos = new ArrayList<>();
        if(list.isEmpty() || list==null ){
            return null;
        }
        for(PayCloudDfMoney payCloudDfMoney:list){
            CloudDfMoneyInfo cloudDfMoneyInfo = new CloudDfMoneyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoney.class,CloudDfMoneyInfo.class,false);
            beanCopier.copy(payCloudDfMoney,cloudDfMoneyInfo,null);
            infos.add(cloudDfMoneyInfo);
        }*/

        return infos;

    }

}
