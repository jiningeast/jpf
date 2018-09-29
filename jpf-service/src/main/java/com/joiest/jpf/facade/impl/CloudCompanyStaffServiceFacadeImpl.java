package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudCompanyStaff;
import com.joiest.jpf.common.po.PayCloudCompanyStaffExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudCompanyCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudCompanyStaffCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyStaffMapper;
import com.joiest.jpf.entity.CloudCompanyStaffInfo;
import com.joiest.jpf.facade.CloudCompanyStaffServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class CloudCompanyStaffServiceFacadeImpl implements CloudCompanyStaffServiceFacade {

    @Autowired
    private PayCloudCompanyStaffMapper payCloudCompanyStaffMapper;

    @Autowired
    private PayCloudCompanyStaffCustomMapper payCloudCompanyStaffCustomMapper;

    /*
     * 查询身份证信息通过身份证号
     * */
    @Override
    public CloudCompanyStaffInfo getCloudCompanyStaffByIdcard(String cardNo){

        PayCloudCompanyStaffExample example = new PayCloudCompanyStaffExample();

        PayCloudCompanyStaffExample.Criteria c = example.createCriteria();
        c.andIdcardEqualTo(cardNo);

        List<PayCloudCompanyStaff> getCompanyStaff = payCloudCompanyStaffMapper.selectByExample(example);
        if(getCompanyStaff == null || getCompanyStaff.isEmpty()){

            return null;
        }
        PayCloudCompanyStaff payCloudCompanyStaff = getCompanyStaff.get(0);

        CloudCompanyStaffInfo cloudCompanyStaffInfo = new CloudCompanyStaffInfo();
        BeanCopier beanCopier = BeanCopier.create( PayCloudCompanyStaff.class, CloudCompanyStaffInfo.class, false);
        beanCopier.copy(payCloudCompanyStaff, cloudCompanyStaffInfo, null);

        return cloudCompanyStaffInfo;
    }


    /*
     * 通过身份证号更新员工信息
     * */
    @Override
    public int upCloudCompanyStaffByIdcard(String idcard, Map<String,String> map){

        PayCloudCompanyStaffExample example = new PayCloudCompanyStaffExample();
        PayCloudCompanyStaffExample.Criteria c = example.createCriteria();
        c.andIdcardEqualTo(idcard);

        PayCloudCompanyStaff staff = new PayCloudCompanyStaff();

        staff.setIsActive(Byte.parseByte(map.get("is_active")));
        staff.setCode(map.get("code"));
        staff.setUcardid(map.get("ucardid"));

        return payCloudCompanyStaffMapper.updateByExampleSelective(staff, example);
    }

    /**
     * 通过主键id更新员工信息
     */
    @Override
    public int upCloudCompanyStaffById(CloudCompanyStaffInfo cloudCompanyStaffInfo){
        PayCloudCompanyStaff payCloudCompanyStaff = new PayCloudCompanyStaff();
        BeanCopier beanCopier = BeanCopier.create( CloudCompanyStaffInfo.class, PayCloudCompanyStaff.class, false);
        beanCopier.copy(cloudCompanyStaffInfo, payCloudCompanyStaff, null);

        return payCloudCompanyStaffMapper.updateByPrimaryKeySelective(payCloudCompanyStaff);
    }

    /*
     *获取员工信息通过id
     * */
    @Override
    public CloudCompanyStaffInfo getCloudCompanyStaffById(String id){


        PayCloudCompanyStaff payCloudCompanyStaff =  payCloudCompanyStaffMapper.selectByPrimaryKey(id);

        if(payCloudCompanyStaff == null){

            return  null;
        }

        CloudCompanyStaffInfo cloudCompanyStaffInfo = new CloudCompanyStaffInfo();
        BeanCopier beanCopier = BeanCopier.create( PayCloudCompanyStaff.class, CloudCompanyStaffInfo.class, false);
        beanCopier.copy(payCloudCompanyStaff, cloudCompanyStaffInfo, null);

        return cloudCompanyStaffInfo;
    }

    @Override
    public String addStaff(CloudCompanyStaffInfo cloudCompanyStaffInfo){
        PayCloudCompanyStaff payCloudCompanyStaff = new PayCloudCompanyStaff();
        BeanCopier beanCopier = BeanCopier.create( CloudCompanyStaffInfo.class, PayCloudCompanyStaff.class, false);
        beanCopier.copy(cloudCompanyStaffInfo, payCloudCompanyStaff, null);

        payCloudCompanyStaffCustomMapper.insertSelective(payCloudCompanyStaff);
        return payCloudCompanyStaff.getId();
    }

    /**
     * 根据多个字段获取员工
     */
    @Override
    public CloudCompanyStaffInfo getStaffByInfo(CloudCompanyStaffInfo cloudCompanyStaffInfo){
        PayCloudCompanyStaffExample e = new PayCloudCompanyStaffExample();
        PayCloudCompanyStaffExample.Criteria c = e.createCriteria();
        if ( cloudCompanyStaffInfo.getIdcard() != null ){
            c.andIdcardEqualTo(cloudCompanyStaffInfo.getIdcard());
        }
        if ( cloudCompanyStaffInfo.getStatus() != null ){
            c.andStatusEqualTo(cloudCompanyStaffInfo.getStatus());
        }
        if ( cloudCompanyStaffInfo.getMobile()!= null ){
            c.andMobileEqualTo(cloudCompanyStaffInfo.getMobile());
        }
        List<PayCloudCompanyStaff> list = payCloudCompanyStaffMapper.selectByExample(e);

        CloudCompanyStaffInfo cloudCompanyStaffInfo1 = new CloudCompanyStaffInfo();
        if ( !list.isEmpty() ){
            BeanCopier beanCopier = BeanCopier.create( PayCloudCompanyStaff.class, CloudCompanyStaffInfo.class, false);
            beanCopier.copy(list.get(0), cloudCompanyStaffInfo1, null);
        }

        return cloudCompanyStaffInfo1;
    }

    /**
     * 根据姓名、银行卡号、身份证号、手机号查询一个人的鉴权记录
     */
    @Override
    public CloudCompanyStaffInfo getOneStaff(CloudCompanyStaffInfo cloudCompanyStaffInfo){
        /*PayCloudCompanyStaffExample e = new PayCloudCompanyStaffExample();
        PayCloudCompanyStaffExample.Criteria c = e.createCriteria();
        c.andNicknameEqualTo(cloudCompanyStaffInfo.getNickname());
        c.andMobileEqualTo(cloudCompanyStaffInfo.getMobile());
        c.andIdcardEqualTo(cloudCompanyStaffInfo.getIdcard());
        c.andMerchNoEqualTo(cloudCompanyStaffInfo.getMerchNo());
        c.andStatusEqualTo(cloudCompanyStaffInfo.getStatus());*/

        PayCloudCompanyStaff searchStaff = new PayCloudCompanyStaff();
        searchStaff.setNickname(cloudCompanyStaffInfo.getNickname());
        searchStaff.setMobile(cloudCompanyStaffInfo.getMobile());
        searchStaff.setIdcard(cloudCompanyStaffInfo.getIdcard());
        searchStaff.setMerchNo(cloudCompanyStaffInfo.getMerchNo());
        searchStaff.setStatus(cloudCompanyStaffInfo.getStatus());
        PayCloudCompanyStaff payCloudCompanyStaff = payCloudCompanyStaffCustomMapper.selectOneStaff(searchStaff);
        /*if ( list != null && list.isEmpty() ){
            payCloudCompanyStaff = list.get(0);
        }*/

        CloudCompanyStaffInfo info = new CloudCompanyStaffInfo();
        if ( payCloudCompanyStaff != null && payCloudCompanyStaff.getId() != null && StringUtils.isNotBlank(payCloudCompanyStaff.getId()) ){
            BeanCopier beanCopier = BeanCopier.create( PayCloudCompanyStaff.class, CloudCompanyStaffInfo.class, false);
            beanCopier.copy(payCloudCompanyStaff, info, null);
        }

        return info;
    }
}
