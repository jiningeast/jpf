package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudCompanyStaffInfo;

import java.util.Map;

public interface CloudCompanyStaffServiceFacade {

    /*
    * 获取员工信息通过身份证号
    * */
    public CloudCompanyStaffInfo getCloudCompanyStaffByIdcard(String cardNo);

    /*
    * 通过身份证号更新员工信息
    * */
    public int upCloudCompanyStaffByIdcard(String idcard, Map<String,String> map);

    /**
     * 通过主键id更新员工信息
     */
    public int upCloudCompanyStaffById(CloudCompanyStaffInfo cloudCompanyStaffInfo);

    /*
     *获取员工信息通过身主键
    * */
    public CloudCompanyStaffInfo getCloudCompanyStaffById(String id);

    /**
     * 插入员工信息
     */
    public String addStaff(CloudCompanyStaffInfo cloudCompanyStaffInfo);

    /**
     * 根据多个字段获取员工
     */
    public CloudCompanyStaffInfo getStaffByInfo(CloudCompanyStaffInfo cloudCompanyStaffInfo);

    /**
     * 根据姓名、银行卡号、身份证号、手机号查询一个人的鉴权记录
     */
    public CloudCompanyStaffInfo getOneStaff(CloudCompanyStaffInfo cloudCompanyStaffInfo);

}
