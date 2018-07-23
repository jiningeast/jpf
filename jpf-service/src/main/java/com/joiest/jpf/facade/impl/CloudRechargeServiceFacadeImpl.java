package com.joiest.jpf.facade.impl;


import com.joiest.jpf.common.constant.EnumConstants;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ValidatorUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyAgentMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanySalesMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudRechargeMapper;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.entity.CloudRechargeInfo;
import com.joiest.jpf.entity.RechargeNeedInfo;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import com.joiest.jpf.facade.CloudRechargeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;



public class CloudRechargeServiceFacadeImpl implements CloudRechargeServiceFacade {

    private static final Logger logger = LogManager.getLogger(CloudRechargeServiceFacadeImpl.class);

    @Autowired
    private PayCloudRechargeMapper payCloudRechargeMapper;

    @Autowired
    private PayCloudCompanyMapper payCloudCompanyMapper;

    @Autowired
    private CloudCompanyServiceFacade cloudCompanyServiceFacade;

    @Autowired
    private PayCloudCompanySalesMapper payCloudCompanySalesMapper;

    @Autowired
    private PayCloudCompanyAgentMapper payCloudCompanyAgentMapper;
    /*
    * 统计充值总笔数
    * */
    @Override
    public Integer getCloudRechargeCount(){
        PayCloudRechargeExample example = new PayCloudRechargeExample();
        int count = payCloudRechargeMapper.countByExample(example);

        return count;
    }

    /*
     * 充值列表页
     * */
    @Override
    public CloudRechargeResponse getRecords(CloudRechargeRequest cloudRechargeRequest){
        CloudRechargeResponse cloudRechargeResponse = new CloudRechargeResponse();

       /* //定义财务角色对应的状态值
        List<Byte> statusArr=new ArrayList<Byte>();
        statusArr.add((byte)0);  //已支付(已上传凭证)
        statusArr.add((byte)1); //已支付(已上传凭证)
        statusArr.add((byte)2); //已支付(已上传凭证)*/

        PayCloudRechargeExample e = new PayCloudRechargeExample();
        PayCloudRechargeExample.Criteria c = e.createCriteria();
        // 构建查询example
        if ( StringUtils.isNotBlank(cloudRechargeRequest.getAgentNo()) ){
            c.andAgentNoEqualTo(cloudRechargeRequest.getAgentNo());
        }
        if ( StringUtils.isNotBlank(cloudRechargeRequest.getFid())  ){
            c.andFidEqualTo(cloudRechargeRequest.getFid());
        }
        if ( StringUtils.isNotBlank(cloudRechargeRequest.getLinkphone()) ){
            c.andLinkphoneLike(cloudRechargeRequest.getLinkphone());
        }
        if ( cloudRechargeRequest.getStatus() != null  ){
            c.andStatusEqualTo(cloudRechargeRequest.getStatus());
        }
        if ( cloudRechargeRequest.getPactno() != null ){
            c.andPactnoEqualTo(cloudRechargeRequest.getPactno());
        }
        // 添加时间搜索
        Date addtimeStart = new Date();
        if ( StringUtils.isNotBlank(cloudRechargeRequest.getAddtimeStart()) ){
            addtimeStart = DateUtils.getFdate( cloudRechargeRequest.getAddtimeStart(), DateUtils.DATEFORMATLONG );
            c.andAddtimeGreaterThan( addtimeStart );
        }
        Date addtimeEnd = new Date();
        if ( StringUtils.isNotBlank(cloudRechargeRequest.getAddtimeEnd()) ){
            addtimeEnd = DateUtils.getFdate( cloudRechargeRequest.getAddtimeEnd(), DateUtils.DATEFORMATLONG );
            c.andAddtimeLessThan( addtimeEnd );
        }
        if( cloudRechargeRequest.getStatusArr() != null ){
            c.andStatusIn(cloudRechargeRequest.getStatusArr()); //查询指定状态值数据
        }


        //System.out.println(cloudRechargeRequest.getStatus());
        //System.out.println(222222);
        e.setPageNo(cloudRechargeRequest.getPage());
        e.setPageSize(cloudRechargeRequest.getRows());
        e.setOrderByClause("id DESC");

        List<PayCloudRecharge> list = payCloudRechargeMapper.selectByExample(e);
        List<CloudRechargeInfo> infos = new ArrayList<>();
        for (PayCloudRecharge payCloudRecharge : list) {
            CloudRechargeInfo cloudRechargeInfo = new CloudRechargeInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudRecharge.class, CloudRechargeInfo.class, false);
            beanCopier.copy(payCloudRecharge, cloudRechargeInfo, null);

            infos.add(cloudRechargeInfo);
        }

        cloudRechargeResponse.setList(infos);
        cloudRechargeResponse.setCount(payCloudRechargeMapper.countByExample(e));


        return cloudRechargeResponse;
    }

    /*
     * 财务列表页
     * */
    @Override
    public CloudRechargeResponse getCaiwuRecords(CloudRechargeRequest cloudRechargeRequest){
        CloudRechargeResponse cloudRechargeResponse = new CloudRechargeResponse();

        /*//定义财务角色对应的状态值
        List<Byte> statusArr=new ArrayList<Byte>();
        statusArr.add((byte)3);  //已支付(已上传凭证)
        statusArr.add((byte)4); //已支付(已上传凭证)*/

        PayCloudRechargeExample e = new PayCloudRechargeExample();
        PayCloudRechargeExample.Criteria c = e.createCriteria();
        // 构建查询example
        if ( StringUtils.isNotBlank(cloudRechargeRequest.getAgentNo()) ){
            c.andAgentNoEqualTo(cloudRechargeRequest.getAgentNo());
        }
        if ( StringUtils.isNotBlank(cloudRechargeRequest.getFid())  ){
            c.andFidEqualTo(cloudRechargeRequest.getFid());
        }
        if ( StringUtils.isNotBlank(cloudRechargeRequest.getLinkphone()) ){
            c.andLinkphoneLike(cloudRechargeRequest.getLinkphone());
        }
        if ( cloudRechargeRequest.getStatus() != null  ){
            c.andStatusEqualTo(cloudRechargeRequest.getStatus());
        }
        // 添加时间搜索
        Date addtimeStart = new Date();
        if ( StringUtils.isNotBlank(cloudRechargeRequest.getAddtimeStart()) ){
            addtimeStart = DateUtils.getFdate( cloudRechargeRequest.getAddtimeStart(), DateUtils.DATEFORMATLONG );
            c.andAddtimeGreaterThan( addtimeStart );
        }
        Date addtimeEnd = new Date();
        if ( StringUtils.isNotBlank(cloudRechargeRequest.getAddtimeEnd()) ){
            addtimeEnd = DateUtils.getFdate( cloudRechargeRequest.getAddtimeEnd(), DateUtils.DATEFORMATLONG );
            c.andAddtimeLessThan( addtimeEnd );
        }
        if( cloudRechargeRequest.getStatusArr() != null ){
            c.andStatusIn(cloudRechargeRequest.getStatusArr()); //查询指定状态值数据
        }

        //System.out.println(cloudRechargeRequest.getStatus());
        //System.out.println(222222);
        e.setPageNo(cloudRechargeRequest.getPage());
        e.setPageSize(cloudRechargeRequest.getRows());
        e.setOrderByClause("id DESC");

        List<PayCloudRecharge> list = payCloudRechargeMapper.selectByExample(e);
        List<CloudRechargeInfo> infos = new ArrayList<>();
        for (PayCloudRecharge payCloudRecharge : list) {
            CloudRechargeInfo cloudRechargeInfo = new CloudRechargeInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudRecharge.class, CloudRechargeInfo.class, false);
            beanCopier.copy(payCloudRecharge, cloudRechargeInfo, null);

            infos.add(cloudRechargeInfo);
        }

        cloudRechargeResponse.setList(infos);
        cloudRechargeResponse.setCount(payCloudRechargeMapper.countByExample(e));


        return cloudRechargeResponse;
    }

    /*
    * 审核详情页页面
     * */
    @Override
    public CloudRechargeInfo getRecharge(Long id){

        if (id == null) {
            //logger.info("求情参数id为空");
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }


        PayCloudRecharge PayCloudRecharge = payCloudRechargeMapper.selectByPrimaryKey(Long.valueOf(id));


        CloudRechargeInfo cloudRechargeInfo = new CloudRechargeInfo();
        if ( PayCloudRecharge == null ){
            return  cloudRechargeInfo;
        }

        BeanCopier beanCopier = BeanCopier.create(PayCloudRecharge.class, CloudRechargeInfo.class, false);
        beanCopier.copy(PayCloudRecharge,cloudRechargeInfo,null);

        //备注信息添加时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        if(cloudRechargeInfo.getKfremarks() != null && StringUtils.isNotBlank(cloudRechargeInfo.getKfremarks()) ){

            cloudRechargeInfo.setKfremarks(cloudRechargeInfo.getKfremarks()+"&#13;&#10;["+ df.format(new Date()) + "]");
        }else{
            cloudRechargeInfo.setKfremarks("["+ df.format(new Date()) + "] ");

        }

        return cloudRechargeInfo;

    }

    /*
     * 审核充值记录状态
     * */
    @Override
    //@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public JpfResponseDto getAuditRecharge(CloudRechargeRequest request){

        Long infoId = request.getId();
        String infoKfremarks = request.getKfremarks();
        Byte infoStatus = request.getStatus();
        String infoPactno = request.getPactno();

        if( infoId == null  ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayCloudRecharge payCloudRecharge = payCloudRechargeMapper.selectByPrimaryKey(Long.valueOf(infoId));
        CloudRechargeInfo cloudRechargeInfo = new CloudRechargeInfo();
        if( payCloudRecharge ==null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到相关信息");
        }

        BeanCopier beanCopier = BeanCopier.create(PayCloudRecharge.class, CloudRechargeInfo.class, false);
        beanCopier.copy(payCloudRecharge,cloudRechargeInfo,null);

        //备注信息添加时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date curretDate = new Date();

        PayCloudRecharge recordData = new PayCloudRecharge();
        recordData.setUpdatetime(curretDate);
        recordData.setId(Long.valueOf(infoId));

        //添加实物
        int res = 0;
        switch (cloudRechargeInfo.getStatus()){
            case 1:
                if( infoStatus == 2 || infoStatus == 0){ //更新为已审核通过 或取消申请
                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    if( infoStatus == 2 ){//审核通过添加合同编号
                        recordData.setPactno(infoPactno);
                        if( StringUtils.isBlank(infoPactno) || infoPactno==null ){
                            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "合同编号不能为空");
                        }
                    }
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                }
                break;
            case 2:

                if( infoStatus == 3){ //更新为已支付
                    if( StringUtils.isBlank(cloudRechargeInfo.getImgurl()) || cloudRechargeInfo.getImgurl() ==null ){
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "请先上传付款凭证");
                    }
                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新//

                }
                break;
            case 3:
                /*if( infoStatus == 4){ //更新为已充值开票中

                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    recordData.setChargetime(curretDate);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                    //做充值操作
                    if(res ==1 ){
                        Integer rechargeRes =  this.rechargeCompanyMoney(infoId);
                        if( rechargeRes != 1 ){
                            throw new JpfException(JpfErrorInfo.DAL_ERROR, "账户充值失败");
                        }else{
                            //充值成功后续操作 ？？？？？

                        }
                    }

                }else{
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());
                }*/
                break;
            case 4:
                /*if( infoStatus == 5){ //更新为已充值已开票

                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                }else{
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());
                }*/
                break;
            case 5:
                /*if( infoStatus == 6){ //更新为已发货
                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                }else{
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());
                }*/
                break;
            case 6:

                /*if( infoStatus == 7){ //更新为已完成

                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                }else{
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());
                }*/
                break;
            default:
                //throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+cloudRechargeInfo.getStatus()+"=="+infoStatus);
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());

        }

        if( res !=1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新失败");
        }

        return new JpfResponseDto();
    }

    /*
     * 审核充值记录状态
     * */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public JpfResponseDto getCaiwuAuditRecharge(CloudRechargeRequest request){

        Long infoId = request.getId();
        String infoKfremarks = request.getKfremarks();
        Byte infoStatus = request.getStatus();

        if( infoId == null  ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayCloudRecharge payCloudRecharge = payCloudRechargeMapper.selectByPrimaryKey(Long.valueOf(infoId));
        CloudRechargeInfo cloudRechargeInfo = new CloudRechargeInfo();
        if( payCloudRecharge ==null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到相关信息");
        }

        BeanCopier beanCopier = BeanCopier.create(PayCloudRecharge.class, CloudRechargeInfo.class, false);
        beanCopier.copy(payCloudRecharge,cloudRechargeInfo,null);

        //备注信息添加时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date curretDate = new Date();

        PayCloudRecharge recordData = new PayCloudRecharge();
        recordData.setUpdatetime(curretDate);
        recordData.setId(Long.valueOf(infoId));

        //添加实物
        int res = 0;
        switch (cloudRechargeInfo.getStatus()){
            case 1:
                /*if( infoStatus == 2 || infoStatus == 0){ //更新为已审核通过 或取消申请
                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                }*/
                break;
            case 2:

                /*if( infoStatus == 3){ //更新为已支付
                    if( StringUtils.isBlank(cloudRechargeInfo.getImgurl()) || cloudRechargeInfo.getImgurl() ==null ){
                        throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "请先上传付款凭证");
                    }
                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新//

                }*/
                break;
            case 3:
                if( infoStatus == 4){ //更新为已充值开票中

                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    recordData.setChargetime(curretDate);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                    //做充值操作
                    if(res ==1 ){
                        //企业账户充值
                        Integer rechargeRes =  this.rechargeCompanyMoney(infoId);
                        if( rechargeRes !=1 ){
                            throw new JpfException(JpfErrorInfo.DAL_ERROR, "账户充值失败");
                        }else{
                            //充值成功后续操作 ？？？？？

                        }
                    }

                }else{
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());
                }
                break;
            case 4:
                if( infoStatus == 5){ //更新为已充值已开票

                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                }else{
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());
                }
                break;
            case 5:
                /*if( infoStatus == 6){ //更新为已发货
                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                }else{
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());
                }*/
                break;
            case 6:

                /*if( infoStatus == 7){ //更新为已完成

                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                }else{
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());
                }*/
                break;
            default:
                //throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+cloudRechargeInfo.getStatus()+"=="+infoStatus);
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());

        }

        if( res !=1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新失败");
        }

        return new JpfResponseDto();
    }

    /*
    * 给公司账户充值金额
    * */
    public Integer rechargeCompanyMoney(Long id){

        //查询充值信息
        PayCloudRecharge payCloudRecharge = payCloudRechargeMapper.selectByPrimaryKey(Long.valueOf(id));
        CloudRechargeInfo cloudRechargeInfo = new CloudRechargeInfo();
        if( payCloudRecharge ==null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到相关信息");
        }

        BeanCopier beanCopier = BeanCopier.create(PayCloudRecharge.class, CloudRechargeInfo.class, false);
        beanCopier.copy(payCloudRecharge,cloudRechargeInfo,null);

        //查询充值账户信息
        String merch_no = cloudRechargeInfo.getMerchNo(); //商户编号
        BigDecimal money = cloudRechargeInfo.getMoney(); //充值金额

        PayCloudCompanyExample example = new PayCloudCompanyExample();
        PayCloudCompanyExample.Criteria c = example.createCriteria();
        c.andMerchNoEqualTo(merch_no);

        List<PayCloudCompany> list =  payCloudCompanyMapper.selectByExample(example);
        if( list ==null || list.isEmpty() ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到商户相关信息");
        }

        PayCloudCompany payCloudCompany = list.get(0);
        CloudCompanyInfo cloudCompanyInfo = new CloudCompanyInfo();
        BeanCopier beanReCopier = BeanCopier.create(PayCloudCompany.class, CloudCompanyInfo.class, false);
        beanReCopier.copy(payCloudCompany, cloudCompanyInfo, null);

        String comId = cloudCompanyInfo.getId();
        Boolean res = cloudCompanyServiceFacade.checkCompanyMoneyVerify(comId);
        int rechargeRet = 0; //是否充值成功
        if(res){
            //金额校验
            String merchNo = cloudCompanyInfo.getMerchNo();
            BigDecimal cloudMoney = cloudCompanyInfo.getCloudmoney();
            BigDecimal rechargeMoney = cloudRechargeInfo.getMoney();
            BigDecimal afterRechargeMoney = cloudMoney.add(rechargeMoney); //充值后金额
            String code = Md5Encrypt.md5(comId+cloudMoney+"test","UTF-8");   //加密规则：  id+金额+key
            Date updated = new Date();

            PayCloudCompany payCloudCompany_insert = new PayCloudCompany();
            payCloudCompany_insert.setCloudmoney(afterRechargeMoney);
            payCloudCompany_insert.setCloudcode(code);
            payCloudCompany_insert.setUpdated(updated);
            PayCloudCompanyExample CompanyExample = new PayCloudCompanyExample();
            PayCloudCompanyExample.Criteria comExample = CompanyExample.createCriteria();
            comExample.andMerchNoEqualTo(merchNo);
            rechargeRet = payCloudCompanyMapper.updateByExampleSelective(payCloudCompany_insert,CompanyExample);


        }else{
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "金额校验失败");
        }

        return rechargeRet;
    }

    @Override
    public GetRechargeNeedResponse getRechargeNeedInfo(GetRechargeNeedRequest request,String pageNo,String pageSize) {
        //参数转换
        Long reqId = null;
        if (StringUtils.isNotBlank(request.getId())) {
            reqId = Long.valueOf(request.getId());
        }
        Byte reqPayway = null;
        if (StringUtils.isNotBlank(request.getPayway())) {
            reqPayway= Byte.valueOf(request.getPayway());
        }
        Long reqEmployeeUid = null;
        if (StringUtils.isNotBlank(request.getEmployeeUid())) {
            reqEmployeeUid = Long.valueOf(request.getEmployeeUid());
        }
        Byte reqStatus = null;
        if (StringUtils.isNotBlank(request.getStatus())) {
            reqStatus = Byte.valueOf(request.getStatus());
        }
        Byte reqPactstatus = null;
        if (StringUtils.isNotBlank(request.getPactstatus())) {
            reqPactstatus = Byte.valueOf(request.getPactstatus());
        }
        Long reqPageNo = null;
        if (StringUtils.isNotBlank(pageNo)) {
            reqPageNo = Long.valueOf(pageNo);
        }
        Long reqPageSize = null;
        if (StringUtils.isNotBlank(pageSize)) {
            reqPageSize = Long.valueOf(pageSize);
        }
        List<String> statusList = new ArrayList<>();
        if (request.getStatusList()!=null&&!request.getStatusList().isEmpty()) {
            statusList = request.getStatusList();
        }
        List<Byte> reqStatusList = new ArrayList<>();
        for (String s : statusList) {
            reqStatusList.add(new Byte(s));
        }

        PayCloudRechargeExample example = new PayCloudRechargeExample();
        PayCloudRechargeExample.Criteria exampleCriteria = example.createCriteria();
        if (null != reqId) {
            exampleCriteria.andIdEqualTo(reqId);
        }
        if (StringUtils.isNotBlank(request.getFid())) {
            exampleCriteria.andFidEqualTo(request.getFid());
        }
        if (StringUtils.isNotBlank(request.getPactno())) {
            exampleCriteria.andPactnoEqualTo(request.getPactno());
        }
        if (StringUtils.isNotBlank(request.getAgentNo())) {
            exampleCriteria.andAgentNoEqualTo(request.getAgentNo());
        }
        if (StringUtils.isNotBlank(request.getMerchNo())) {
            exampleCriteria.andMerchNoEqualTo(request.getMerchNo());
        }
        if (null != reqPayway) {
            exampleCriteria.andPaywayEqualTo(reqPayway);
        }
        if (null != reqEmployeeUid) {
            exampleCriteria.andEmployeeUidEqualTo(reqEmployeeUid);
        }
        if (null != reqStatus) {
            exampleCriteria.andStatusEqualTo(reqStatus);
        }
        if (null != reqStatusList && !reqStatusList.isEmpty()) {
            exampleCriteria.andStatusIn(reqStatusList);
        }
        if (StringUtils.isNotBlank(request.getAddtimeStart()) && StringUtils.isNotBlank(request.getAddtimeEnd())) {
            exampleCriteria.andAddtimeBetween(DateUtils.getFdate(request.getAddtimeStart(), DateUtils.DATEFORMATSHORT ),DateUtils.addInteger(request.getAddtimeEnd(),5,1,DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getShenhetimeStart()) && StringUtils.isNotBlank(request.getShenhetimeEnd())) {
            exampleCriteria.andShenhetimeBetween(DateUtils.getFdate(request.getShenhetimeStart(), DateUtils.DATEFORMATSHORT ),DateUtils.addInteger(request.getShenhetimeEnd(),5,1,DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getChargetimeStart()) && StringUtils.isNotBlank(request.getChargetimeEnd())) {
            exampleCriteria.andChargetimeBetween(DateUtils.getFdate(request.getChargetimeStart(), DateUtils.DATEFORMATSHORT), DateUtils.addInteger(request.getChargetimeEnd(),5,1,DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getPacttimeStart()) && StringUtils.isNotBlank(request.getPacttimeEnd())) {
            exampleCriteria.andPacttimeBetween(DateUtils.getFdate(request.getPacttimeStart(), DateUtils.DATEFORMATSHORT), DateUtils.addInteger(request.getPacttimeEnd(),5,1,DateUtils.DATEFORMATSHORT));
        }
        if (null != reqPactstatus) {
            exampleCriteria.andPactstatusEqualTo(reqPactstatus);
        }
        int count = payCloudRechargeMapper.countByExample(example);

        if (reqPageNo==null||reqPageNo <= 0) {
            reqPageNo = Long.valueOf(1);
        }
        if (reqPageSize==null||reqPageSize <= 0) {
            reqPageSize = Long.valueOf(20);
        }
        example.setPageNo(reqPageNo);
        example.setPageSize(reqPageSize);
        example.setOrderByClause("addtime DESC");
        List<PayCloudRecharge> payCloudRecharges = payCloudRechargeMapper.selectByExample(example);
        List<RechargeNeedInfo> info = new ArrayList<>();
        for (PayCloudRecharge payCloudRecharge : payCloudRecharges) {
            RechargeNeedInfo rechargeNeedInfo = new RechargeNeedInfo();
            BeanCopier beanCopier = BeanCopier.create(PayCloudRecharge.class, RechargeNeedInfo.class, false);
            beanCopier.copy(payCloudRecharge, rechargeNeedInfo, null);
            info.add(rechargeNeedInfo);
        }
        GetRechargeNeedResponse response = new GetRechargeNeedResponse();
        response.setCount(count);
        response.setInfo(info);
        return response;
    }

    public String createOrderid(){
        int pre = getRandomInt(100,999);
        int last = getRandomInt(100,999);
        String middle = String.valueOf(System.currentTimeMillis());
        middle = middle.substring(3,middle.length());

        return ""+pre+middle+last;
    }

    // 生成指定范围内的随机整数
    public int getRandomInt(int min, int max){
        Random random = new Random();
        int randomInt = random.nextInt(max)%(max-min+1) + min;

        return randomInt;
    }

    @Override
    public JpfResponseDto rechargeNeedRelease(CloudRechargeNeedReleaseRequest request) {
        ValidatorUtils.validateInterface(request);
        //参数类型转换
        BigDecimal reqMoney = new BigDecimal(request.getMoney());
        BigDecimal reqFeemoney = new BigDecimal(request.getFeemoney());
        BigDecimal reqRealmoney = new BigDecimal(request.getRealmoney());
        Byte reqPayway = Byte.valueOf(request.getPayway());
        //代理公司校验
        PayCloudCompanyAgentExample payCloudCompanyAgentExample = new PayCloudCompanyAgentExample();
        PayCloudCompanyAgentExample.Criteria companyAgentCriteria = payCloudCompanyAgentExample.createCriteria();
        companyAgentCriteria.andAgentNoEqualTo(request.getAgentNo());
        List<PayCloudCompanyAgent> cloudCompanyAgentList = payCloudCompanyAgentMapper.selectByExample(payCloudCompanyAgentExample);
        if (cloudCompanyAgentList == null || cloudCompanyAgentList.isEmpty()) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "该"+request.getAgentNo()+"代理企业不存在");
        }
        //业务公司校验
        PayCloudCompanySalesExample payCloudCompanySalesExample = new PayCloudCompanySalesExample();
        PayCloudCompanySalesExample.Criteria c = payCloudCompanySalesExample.createCriteria();
        c.andSalesNoEqualTo(request.getMerchNo());
        List<PayCloudCompanySales> payCloudCompanySalesList = payCloudCompanySalesMapper.selectByExample(payCloudCompanySalesExample);
        if (payCloudCompanySalesList == null || payCloudCompanySalesList.isEmpty()) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "该" + request.getMerchNo() + "企业不存在");
        }
        PayCloudCompanySales payCloudCompanySales = payCloudCompanySalesList.get(0);
        BigDecimal feemoney = reqMoney.multiply(payCloudCompanySales.getSalesRate());//计算手续费
        BigDecimal realmoney = feemoney.add(reqMoney);//计算实际汇款金额

        //校验与前端的手续费或者实际汇款金额是否一致
        if (feemoney.compareTo(reqFeemoney)!=0||realmoney.compareTo(reqRealmoney)!=0) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "企业手续费或实际汇款金额不一致");
        }
        PayCloudRecharge record = new PayCloudRecharge();
        record.setFid(createOrderid());
        record.setNeedid(request.getNeedid());
        record.setNeedcatpath(request.getNeedcatpath());
        record.setAgentNo(request.getAgentNo());
        record.setMerchNo(request.getMerchNo());
        record.setPayway(reqPayway);
        record.setStatus(EnumConstants.RechargeStatus.APPLYING.value());
        record.setMoney(reqMoney);
        record.setRealmoney(reqRealmoney);
        record.setFeemoney(reqFeemoney);
        record.setSalesRate(payCloudCompanySales.getSalesRate());
        record.setAddtime(Calendar.getInstance().getTime());
        record.setPacttime(DateUtils.getFdate(request.getPacttime(),DateUtils.DATEFORMATLONG));
        record.setPactstatus(EnumConstants.RechargePactStatus.UNCONFIRMED.value());
        record.setKfremarks("发布需求");
        payCloudRechargeMapper.insertSelective(record);
        return new JpfResponseDto();
    }

    @Override
    public JpfResponseDto rechargeNeedDelete(String merchNo,String agentNo,String id,String fid) {
        //参数转换
        Long reqId = Long.valueOf(id);

        if (reqId == null) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "请求参数[id]不能为空");
        }
        PayCloudRecharge payCloudRecharge = payCloudRechargeMapper.selectByPrimaryKey(reqId);
        if (payCloudRecharge == null) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "操作记录[id]不能不存在");
        }
        if (!payCloudRecharge.getFid().equals(fid)) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "操作记录[fid]订单号不一致");
        }
        if (!payCloudRecharge.getAgentNo().equals(agentNo)) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "操作记录[agentNo]代理商户号不一致");
        }
        if (!payCloudRecharge.getMerchNo().equals(merchNo)) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "操作记录[merchNo]企业商户号不一致");
        }
        if (!payCloudRecharge.getStatus().equals(EnumConstants.RechargeStatus.APPLYING.value())) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "非初始状态，不可以操作");
        }
        payCloudRechargeMapper.deleteByPrimaryKey(reqId);
        return new JpfResponseDto();
    }

    @Override
    public JpfResponseDto rechargeNeedVoucher(String merchNo,String agentNo,String id,String fid,String imgurl) {
        //参数转换
        Long reqId = Long.valueOf(id);
        if (reqId == null) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "请求参数[id]不能为空");
        }
        if (imgurl == null) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "请求参数[imgurl]不能为空");
        }
        PayCloudRecharge payCloudRecharge = payCloudRechargeMapper.selectByPrimaryKey(reqId);
        if (payCloudRecharge == null) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "操作记录[id]不能不存在");
        }
        if (!payCloudRecharge.getFid().equals(fid)) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "操作记录[fid]订单号不一致");
        }
        if (!payCloudRecharge.getAgentNo().equals(agentNo)) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "操作记录[agentNo]代理商户号不一致");
        }
        if (!payCloudRecharge.getMerchNo().equals(merchNo)) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "操作记录[merchNo]企业商户号不一致");
        }
        if (!payCloudRecharge.getStatus().equals(EnumConstants.RechargeStatus.AUDIT.value())) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "状态不匹配，不可以操作");
        }
        PayCloudRechargeExample payCloudRechargeExample = new PayCloudRechargeExample();
        PayCloudRechargeExample.Criteria payCloudRechargeExampleCriteria = payCloudRechargeExample.createCriteria();
        payCloudRechargeExampleCriteria.andIdEqualTo(reqId);
        payCloudRechargeExampleCriteria.andStatusEqualTo(EnumConstants.RechargeStatus.AUDIT.value());
        PayCloudRecharge record = new PayCloudRecharge();
        record.setImgurl(imgurl);
        record.setStatus(EnumConstants.RechargeStatus.PAY.value());
        payCloudRechargeMapper.updateByExampleSelective(record, payCloudRechargeExample);
        return new JpfResponseDto();
    }

    @Override
    public JpfResponseDto rechargeNeedAffirm(String merchNo,String agentNo,String id,String fid) {
        //参数转换
        Long reqId = Long.valueOf(id);

        if (reqId == null) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "请求参数[id]不能为空");
        }
        PayCloudRecharge payCloudRecharge = payCloudRechargeMapper.selectByPrimaryKey(reqId);
        if (payCloudRecharge == null) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "操作记录[id]不能不存在");
        }
        if (!payCloudRecharge.getFid().equals(fid)) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "操作记录[fid]订单号不一致");
        }
        if (!payCloudRecharge.getAgentNo().equals(agentNo)) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "操作记录[agentNo]代理商户号不一致");
        }
        if (!payCloudRecharge.getMerchNo().equals(merchNo)) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), "操作记录[merchNo]企业商户号不一致");
        }
        if (payCloudRecharge.getPactstatus().equals(EnumConstants.RechargePactStatus.CONFIRMED.value())) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "已验收，不需要重复验收");
        }
        if (!payCloudRecharge.getStatus().equals(EnumConstants.RechargeStatus.APPLYING.value())&&
                !payCloudRecharge.getStatus().equals(EnumConstants.RechargeStatus.AUDIT.value())&&
                !payCloudRecharge.getStatus().equals(EnumConstants.RechargeStatus.PAY.value())&&
                !payCloudRecharge.getStatus().equals(EnumConstants.RechargeStatus.CANCEL.value())) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "状态不匹配，不可以操作");
        }
        if (payCloudRecharge.getPacttime().getTime() > Calendar.getInstance().getTime().getTime()) {
            throw new JpfInterfaceException(JpfInterfaceErrorInfo.FAIL.getCode(), "未到验收时间，不可以操作");
        }
        PayCloudRechargeExample payCloudRechargeExample = new PayCloudRechargeExample();
        PayCloudRechargeExample.Criteria payCloudRechargeExampleCriteria = payCloudRechargeExample.createCriteria();
        payCloudRechargeExampleCriteria.andIdEqualTo(reqId);
        payCloudRechargeExampleCriteria.andPactstatusEqualTo(EnumConstants.RechargePactStatus.UNCONFIRMED.value());
        payCloudRechargeExampleCriteria.andStatusIn(Arrays.asList(EnumConstants.RechargeStatus.APPLYING.value(),EnumConstants.RechargeStatus.AUDIT.value(),EnumConstants.RechargeStatus.PAY.value(),EnumConstants.RechargeStatus.CANCEL.value()));
        PayCloudRecharge record = new PayCloudRecharge();
        record.setPactstatus(EnumConstants.RechargePactStatus.CONFIRMED.value());
        payCloudRechargeMapper.updateByExampleSelective(record, payCloudRechargeExample);
        return new JpfResponseDto();
    }

    /**
     * 根据合同编号获取记录
     */
    @Override
    public CloudRechargeInfo getRecByPactno(String pactno){
        PayCloudRechargeExample e = new PayCloudRechargeExample();
        PayCloudRechargeExample.Criteria c = e.createCriteria();
        c.andPactnoEqualTo(pactno);
        c.andStatusGreaterThanOrEqualTo((byte)4);

        List<PayCloudRecharge> list = payCloudRechargeMapper.selectByExample(e);
        CloudRechargeInfo cloudRechargeInfo = new CloudRechargeInfo();

        if ( !list.isEmpty() ){
            BeanCopier beanCopier = BeanCopier.create(PayCloudRecharge.class, CloudRechargeInfo.class, false);
            beanCopier.copy(list.get(0), cloudRechargeInfo, null);
        }

        return cloudRechargeInfo;
    }
}
