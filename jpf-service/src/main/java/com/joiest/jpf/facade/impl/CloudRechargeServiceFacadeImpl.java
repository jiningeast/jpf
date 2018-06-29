package com.joiest.jpf.facade.impl;


import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayCloudRecharge;
import com.joiest.jpf.common.po.PayCloudRechargeExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudRechargeMapper;
import com.joiest.jpf.dto.CloudRechargeRequest;
import com.joiest.jpf.dto.CloudRechargeResponse;
import com.joiest.jpf.entity.CloudRechargeInfo;
import com.joiest.jpf.facade.CloudRechargeServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.text.SimpleDateFormat;
import java.util.*;



public class CloudRechargeServiceFacadeImpl implements CloudRechargeServiceFacade {

    @Autowired
    private PayCloudRechargeMapper payCloudRechargeMapper;

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
     * 审核详情页页面
     * */
    @Override
    public JpfResponseDto getAuditRecharge(CloudRechargeRequest request){

        String infoId = request.getId();
        String infoKfremarks = request.getKfremarks();
        Byte infoStatus = request.getStatus();

        if( StringUtils.isBlank(infoId) ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayCloudRecharge payCloudRecharge = payCloudRechargeMapper.selectByPrimaryKey(infoId);
        CloudRechargeInfo cloudRechargeInfo = new CloudRechargeInfo();
        if( payCloudRecharge ==null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到相关细心");
        }

        BeanCopier beanCopier = BeanCopier.create(PayCloudRecharge.class, CloudRechargeInfo.class, false);
        beanCopier.copy(payCloudRecharge,cloudRechargeInfo,null);

        //备注信息添加时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date curretDate = new Date();

        PayCloudRecharge recordData = new PayCloudRecharge();
        recordData.setUpdatetime(curretDate);
        recordData.setId(infoId);

        int res = 0;
        switch (cloudRechargeInfo.getStatus()){
            case 1:
                if( infoStatus == 2 || infoStatus == 0){ //更新为已审核通过 或取消申请
                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
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
                if( infoStatus == 4){ //更新为已充值开票中

                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                    //做充值操作   ？？？？？？？？？待做
                    if(res ==1 ){


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
                if( infoStatus == 6){ //更新为已发货
                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                }else{
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());
                }
                break;
            case 6:

                if( infoStatus == 7){ //更新为已完成

                    recordData.setKfremarks(infoKfremarks);
                    recordData.setStatus(infoStatus);
                    res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                }else{
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());
                }
                break;
            default:
                //throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+cloudRechargeInfo.getStatus()+"=="+infoStatus);
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus_cn());

        }
        /*if( infoStatus == 2 && cloudRechargeInfo.getStatus() == 1 ){//更新为已审核通过
            recordData.setKfremarks(infoKfremarks);
            recordData.setStatus(infoStatus);

            res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新
        }
        if( infoStatus == 3 && cloudRechargeInfo.getStatus() == 2 ){//更新为已支付
            recordData.setKfremarks(infoKfremarks);
            recordData.setStatus(infoStatus);
            res = payCloudRechargeMapper.updateByPrimaryKeySelective(recordData); //指定字段更新
        }*/
        if( res !=1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新失败");
        }

        return new JpfResponseDto();
    }


}
