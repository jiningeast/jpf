package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudCompanyStaff;
import com.joiest.jpf.common.po.PayCloudCompanyStaffExample;
import com.joiest.jpf.dao.repository.mapper.custom.PayCloudCompanyCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudCompanyStaffMapper;
import com.joiest.jpf.entity.CloudCompanyStaffInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;
import java.util.Map;

public class CloudCompanyStaffServiceFacadeImpl {

    @Autowired
    private PayCloudCompanyStaffMapper payCloudCompanyStaffMapper;

    /*
     * 查询身份证信息通过身份证号
     * */
    public CloudCompanyStaffInfo getCloudIdcardByCardNo(String cardNo){

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
    public int upCloudCompanyStaffByIdcard(String idcard, Map<String,String> map){

        return 2;
        /*PayCloudCompanyStaffExample example = new PayCloudCompanyStaffExample();
        PayCloudCompanyStaffExample.Criteria c = example.createCriteria();
        c.andIdcardEqualTo(idcard);

        CloudCompanyStaffInfo staff = new CloudCompanyStaffInfo();

        staff.setIsActive(map.get("is_active"));
        staff.setTrantype(orderRefundInfo.getTrantype());
        staff.setNotifyTime(orderRefundInfo.getNotifyTime());
        staff.setResponsParam(orderRefundInfo.getResponsParam());
        //orderRefundInfo.setRefundOrderid(orderRefundInfo.getRefundOrderid());
        staff.setStatus(orderRefundInfo.getStatus());

        return payOrderRefundMapper.updateByExampleSelective(orderRe, example);*/
    }

}
