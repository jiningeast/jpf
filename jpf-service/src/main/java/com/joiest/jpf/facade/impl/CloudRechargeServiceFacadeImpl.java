package com.joiest.jpf.facade.impl;


import com.google.common.primitives.Bytes;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.BigDecimalCalculateUtils;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudRechargeMapper;
import com.joiest.jpf.dto.CloudRechargeRequest;
import com.joiest.jpf.dto.CloudRechargeResponse;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.entity.CloudRechargeInfo;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import com.joiest.jpf.facade.CloudRechargeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;



public class CloudRechargeServiceFacadeImpl implements CloudRechargeServiceFacade {

    @Autowired
    private PayCloudRechargeMapper payCloudRechargeMapper;

    @Autowired
    private PayCloudCompanyMapper payCloudCompanyMapper;

    @Autowired
    private CloudCompanyServiceFacade cloudCompanyServiceFacade;
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
        e.setPageSize(cloudRechargeRequest.getRows());e.setPageSize(cloudRechargeRequest.getRows());
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
    public CloudRechargeInfo getRecharge(String id){

        if (id == null) {
            //logger.info("求情参数id为空");
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }


        PayCloudRecharge PayCloudRecharge = payCloudRechargeMapper.selectByPrimaryKey(id);


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

        String infoId = request.getId();
        String infoKfremarks = request.getKfremarks();
        Byte infoStatus = request.getStatus();
        String infoPactno = request.getPactno();

        if( StringUtils.isBlank(infoId) ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayCloudRecharge payCloudRecharge = payCloudRechargeMapper.selectByPrimaryKey(infoId);
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
        recordData.setId(infoId);

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
    //@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public JpfResponseDto getCaiwuAuditRecharge(CloudRechargeRequest request){

        String infoId = request.getId();
        String infoKfremarks = request.getKfremarks();
        Byte infoStatus = request.getStatus();

        if( StringUtils.isBlank(infoId) ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayCloudRecharge payCloudRecharge = payCloudRechargeMapper.selectByPrimaryKey(infoId);
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
        recordData.setId(infoId);

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
    public Integer rechargeCompanyMoney(String id){

        //查询充值信息
        PayCloudRecharge payCloudRecharge = payCloudRechargeMapper.selectByPrimaryKey(id);
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

    /*
    * 云账户金额校验
    * */

}
