package com.joiest.jpf.facade.impl;


import com.joiest.jpf.common.custom.PayCloudCompanyMoneyCustom;
import com.joiest.jpf.common.custom.PayCloudDfMoneyCustom;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudCompanyMoney;
import com.joiest.jpf.common.po.PayCloudCompanyMoneyExample;
import com.joiest.jpf.common.po.PayCloudDfMoneyExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudCompanyMoneyCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudDfMoneyCustomMapper;
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

import java.math.BigDecimal;
import java.util.*;


public class CloudCompanyMoneyServiceFacadeImpl implements CloudCompanyMoneyServiceFacade {

    @Autowired
    private PayCloudCompanyMoneyMapper payCloudCompanyMoneyMapper;


    @Autowired
    private PayCloudCompanyMoneyCustomMapper payCloudCompanyMoneyCustomMapper;

    @Autowired
    private PayCloudDfMoneyMapper payCloudDfMoneyMapper;

    @Autowired
    private PayCloudDfMoneyCustomMapper payCloudDfMoneyCustomMapper;

    /*
    * 统计总数
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

        CloudCompanyMoneyResponse cloudCompanyMoneyResponse = new CloudCompanyMoneyResponse();

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
            CloudCompanyMoneyInfo cloudCompanyMoneyInfo = new CloudCompanyMoneyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyMoney.class, CloudCompanyMoneyInfo.class, false);
            beanCopier.copy(payCloudCompanyMoney, cloudCompanyMoneyInfo, null);

            infos.add(cloudCompanyMoneyInfo);
        }

        cloudCompanyMoneyResponse.setList(infos);
        cloudCompanyMoneyResponse.setCount(payCloudCompanyMoneyMapper.countByExample(e));


        return cloudCompanyMoneyResponse;
    }
    // 获取统计数据
    @Override
    public Map<String, Object> getStatistics(CloudCompanyMoneyRequest cloudCompanyMoneyRequest){

        PayCloudCompanyMoneyExample example = new PayCloudCompanyMoneyExample();

        PayCloudCompanyMoneyExample.Criteria c = example.createCriteria();
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
        // 汇总统计
        Map<String, Object> map = new HashMap<>();
        map.put("allCount", (long)payCloudCompanyMoneyMapper.countByExample(example));
        BigDecimal allMoney = payCloudCompanyMoneyCustomMapper.allMoney(example);
        allMoney = allMoney == null ? new BigDecimal(0) : allMoney;
        map.put("allMoney", allMoney);
        BigDecimal allServiceMoney = payCloudCompanyMoneyCustomMapper.allServiceMoney(example);
        allServiceMoney = allServiceMoney == null ? new BigDecimal(0) : allServiceMoney;
        map.put("allServiceMoney", allServiceMoney);
        BigDecimal addedMoney = payCloudCompanyMoneyCustomMapper.addedMoney(example);
        addedMoney = addedMoney == null ? new BigDecimal(0) : addedMoney;
        map.put("addedMoney", addedMoney);
        BigDecimal addedMoneyPay = payCloudCompanyMoneyCustomMapper.addedMoneyPay(example);
        addedMoneyPay = addedMoneyPay == null ? new BigDecimal(0) : addedMoneyPay;
        map.put("addedMoneyPay", addedMoneyPay);
        BigDecimal totalGross = payCloudCompanyMoneyCustomMapper.totalGross(example);
        totalGross = totalGross == null ? new BigDecimal(0) : totalGross;
        map.put("totalGross", totalGross);
        return map;


    }
    /*
     * 财务订单审核列表页
     * */
    @Override
    public CloudCompanyMoneyResponse getCaiwuRecords(CloudCompanyMoneyRequest cloudCompanyMoneyRequest){

        CloudCompanyMoneyResponse cloudCompanyMoneyResponse = new CloudCompanyMoneyResponse();

        PayCloudCompanyMoneyExample example = new PayCloudCompanyMoneyExample();

        PayCloudCompanyMoneyExample.Criteria c = example.createCriteria();
        // 构建查询example
        if ( StringUtils.isNotBlank(cloudCompanyMoneyRequest.getAgentNo()) ){
            c.andAgentNoEqualTo(cloudCompanyMoneyRequest.getAgentNo());
        }
        if ( StringUtils.isNotBlank(cloudCompanyMoneyRequest.getFid())  ){
            c.andFidLike("%"+cloudCompanyMoneyRequest.getFid()+"%");
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
        example.setPageNo(cloudCompanyMoneyRequest.getPage());
        example.setPageSize(cloudCompanyMoneyRequest.getRows());
        example.setOrderByClause("id DESC");

        List<PayCloudCompanyMoneyCustom> list = payCloudCompanyMoneyCustomMapper.selectByExampleandCompany(example);
        List<CloudCompanyMoneyInfo> infos = new ArrayList<>();
        for (PayCloudCompanyMoneyCustom payCloudCompanyMoney : list) {
            CloudCompanyMoneyInfo cloudCompanyMoneyInfo = new CloudCompanyMoneyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyMoneyCustom.class, CloudCompanyMoneyInfo.class, false);
            beanCopier.copy(payCloudCompanyMoney, cloudCompanyMoneyInfo, null);

            infos.add(cloudCompanyMoneyInfo);
        }

        cloudCompanyMoneyResponse.setList(infos);
        cloudCompanyMoneyResponse.setCount(payCloudCompanyMoneyMapper.countByExample(example));


        // 给统计赋值
        Map<String, Object> map = this.getStatistics(cloudCompanyMoneyRequest);
        cloudCompanyMoneyResponse.setAllCount((long)map.get("allCount"));
        cloudCompanyMoneyResponse.setAllMoney((BigDecimal)map.get("allMoney"));
        cloudCompanyMoneyResponse.setAllServiceMoney((BigDecimal)map.get("allServiceMoney"));
        cloudCompanyMoneyResponse.setAddedMoney((BigDecimal)map.get("addedMoney"));
        cloudCompanyMoneyResponse.setAddedMoneyPay((BigDecimal)map.get("addedMoneyPay"));
        cloudCompanyMoneyResponse.setTotalGross((BigDecimal)map.get("totalGross"));

        return cloudCompanyMoneyResponse;
    }

    /*
     * 代付列表
     * */
    @Override
    public GetCloudMoneyDfResponse getAllBycompanyMoneyId(String companyMoneyId){

        GetCloudMoneyDfResponse getCloudMoneyDfResponse = new GetCloudMoneyDfResponse();

        if( StringUtils.isBlank(companyMoneyId) || companyMoneyId==null  ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "参数不能为空");
        }

        PayCloudDfMoneyExample example = new PayCloudDfMoneyExample();
        PayCloudDfMoneyExample.Criteria c = example.createCriteria();
        c.andCompanyMoneyIdEqualTo(companyMoneyId);

        //List<PayCloudDfMoney> getRecords = payCloudDfMoneyMapper.selectByExample(example);
        //关联查询用户签约状态
        List<PayCloudDfMoneyCustom> list = payCloudDfMoneyCustomMapper.selectJoinCompanyStaff(example);
        List<CloudDfMoneyInterfaceInfo> infos = new ArrayList<>();
        for (PayCloudDfMoneyCustom payCloudDfMoneyCustom : list) {
            CloudDfMoneyInterfaceInfo cloudDfMoneyInterfaceInfo = new CloudDfMoneyInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoneyCustom.class, CloudDfMoneyInterfaceInfo.class, false);
            beanCopier.copy(payCloudDfMoneyCustom, cloudDfMoneyInterfaceInfo, null);

            infos.add(cloudDfMoneyInterfaceInfo);
        }

        /*List<CloudDfMoneyInterfaceInfo> infos = new ArrayList<>();
        for (PayCloudDfMoney payCloudDfMoney : getRecords) {
            CloudDfMoneyInterfaceInfo cloudDfMoneyInterfaceInfo = new CloudDfMoneyInterfaceInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudDfMoney.class, CloudDfMoneyInterfaceInfo.class, false);
            beanCopier.copy(payCloudDfMoney, cloudDfMoneyInterfaceInfo, null);

            infos.add(cloudDfMoneyInterfaceInfo);
        }*/

        getCloudMoneyDfResponse.setCount(payCloudDfMoneyMapper.countByExample(example));
        getCloudMoneyDfResponse.setList(infos);

        return getCloudMoneyDfResponse;

    }

    /**
     * 新增代付订单
     */
    @Override
    public CloudCompanyMoneyInfo getRecById(String id){

        if( StringUtils.isBlank(id) || id==null  ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单号不能为空");
        }

        PayCloudCompanyMoneyExample example = new PayCloudCompanyMoneyExample();
        PayCloudCompanyMoneyExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(id);

        List<PayCloudCompanyMoney> payCloudCompanyMoney = payCloudCompanyMoneyMapper.selectByExample(example);
        CloudCompanyMoneyInfo cloudCompanyMoneyInfo = new CloudCompanyMoneyInfo();
        if ( payCloudCompanyMoney.isEmpty() || payCloudCompanyMoney == null)
        {
            return null;
        }

        BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyMoney.class,CloudCompanyMoneyInfo.class,false);
        beanCopier.copy(payCloudCompanyMoney.get(0),cloudCompanyMoneyInfo,null);



        return cloudCompanyMoneyInfo;
    }

    /**
     * 根据订单号更新 代付明细状态
     * fid  订单号
     */
    @Override
    public JpfResponseDto updateRecById(PayCloudCompanyMoney record, String id){

        PayCloudCompanyMoneyExample example = new PayCloudCompanyMoneyExample();
        PayCloudCompanyMoneyExample.Criteria c = example.createCriteria();

        if( StringUtils.isBlank(id) || id==null  ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单号不能为空");
        }
        c.andIdEqualTo(id);

        int count = payCloudCompanyMoneyMapper.updateByExampleSelective(record,example);
        if(count !=1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "数据更新失败");
        }

        return new JpfResponseDto();


    }
    @Override
    public int addRec(CloudCompanyMoneyInfo cloudCompanyMoneyInfo){
        PayCloudCompanyMoney payCloudCompanyMoney = new PayCloudCompanyMoney();

        BeanCopier beanCopier = BeanCopier.create(CloudCompanyMoneyInfo.class, PayCloudCompanyMoney.class, false);
        beanCopier.copy(cloudCompanyMoneyInfo, payCloudCompanyMoney, null);

        return payCloudCompanyMoneyMapper.insert(payCloudCompanyMoney);
    }

    /**
     * 根据批次号获取订单
     */
    @Override
    public CloudCompanyMoneyInfo getRecByBatchNo(String batchNo){

        PayCloudCompanyMoneyExample e = new PayCloudCompanyMoneyExample();
        PayCloudCompanyMoneyExample.Criteria c = e.createCriteria();
        c.andBatchnoEqualTo(batchNo);
        List<PayCloudCompanyMoney> list = payCloudCompanyMoneyMapper.selectByExample(e);

        CloudCompanyMoneyInfo cloudCompanyMoneyInfo = new CloudCompanyMoneyInfo();
        BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyMoney.class, CloudCompanyMoneyInfo.class, false);
        beanCopier.copy(list.get(0), cloudCompanyMoneyInfo, null);

        return cloudCompanyMoneyInfo;
    }

    /**
     * 更新记录
     */
    @Override
    public int updateColumn(CloudCompanyMoneyInfo cloudCompanyMoneyInfo){
        PayCloudCompanyMoney payCloudCompanyMoney = new PayCloudCompanyMoney();

        BeanCopier beanCopier = BeanCopier.create(CloudCompanyMoneyInfo.class, PayCloudCompanyMoney.class, false);
        beanCopier.copy(cloudCompanyMoneyInfo, payCloudCompanyMoney, null);

         return payCloudCompanyMoneyMapper.updateByPrimaryKeySelective(payCloudCompanyMoney);

    }

    /**
     * 根据合同编号获取记录
     */
    @Override
    public CloudCompanyMoneyInfo getRecByFid(String fid){
        PayCloudCompanyMoneyExample e = new PayCloudCompanyMoneyExample();
        PayCloudCompanyMoneyExample.Criteria c = e.createCriteria();
        c.andFidEqualTo(fid);

        List<PayCloudCompanyMoney> list = payCloudCompanyMoneyMapper.selectByExample(e);
        CloudCompanyMoneyInfo cloudCompanyMoneyInfo = new CloudCompanyMoneyInfo();

        if ( !list.isEmpty() ){
            BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyMoney.class, CloudCompanyMoneyInfo.class, false);
            beanCopier.copy(list.get(0), cloudCompanyMoneyInfo, null);
        }

        return cloudCompanyMoneyInfo;
    }

    /**
     * 查询批次记录表 更新代付明细数据
     */
    public List<CloudCompanyMoneyInfo> searchCompanyMoneyAll(CloudCompanyMoneyRequest cloudCompanyMoneyRequest){

        PayCloudCompanyMoneyExample e = new PayCloudCompanyMoneyExample();
        PayCloudCompanyMoneyExample.Criteria c = e.createCriteria();
        if(cloudCompanyMoneyRequest.getMontype() != null){
            c.andMontypeEqualTo(cloudCompanyMoneyRequest.getMontype()); //处理中
        }
        if( cloudCompanyMoneyRequest.getId() != null ){
            c.andIdEqualTo(cloudCompanyMoneyRequest.getId()); //主键
        }

        List<PayCloudCompanyMoney> list = payCloudCompanyMoneyMapper.selectByExample(e);

        List<CloudCompanyMoneyInfo> infos = new ArrayList<>();

        if ( list.size() > 0  ){

            //取出所有代付申请的批次订单记录
            for(PayCloudCompanyMoney payCloudCompanyMoney:list){
                CloudCompanyMoneyInfo cloudCompanyMoneyInfo = new CloudCompanyMoneyInfo();
                BeanCopier beanCopier = BeanCopier.create(PayCloudCompanyMoney.class,CloudCompanyMoneyInfo.class,false);
                beanCopier.copy(payCloudCompanyMoney,cloudCompanyMoneyInfo,null);
                infos.add(cloudCompanyMoneyInfo);
            }

        }else{ //未匹配到数据
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到相关数据");
        }

        return infos;
    }

    /**
     * 查找所有订单
     */
    public List<PayCloudCompanyMoney> getAllRecords(){
        PayCloudCompanyMoneyExample e = new PayCloudCompanyMoneyExample();
        return payCloudCompanyMoneyMapper.selectByExample(e);
    }

}
