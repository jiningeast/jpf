package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetCloudStaffMonthTotalRequest;
import com.joiest.jpf.dto.GetCloudStaffMonthTotalResponse;
import com.joiest.jpf.dto.ModifyCloudStaffMonthTotalRequest;
import com.joiest.jpf.entity.CloudStaffMonthTotalInterfaceInfo;

public interface CloudStaffMonthTotalServiceFacade {

    /**
     * 获取某月总额
     * @param month
     * @param busstaffid
     * @return
     */
    public CloudStaffMonthTotalInterfaceInfo getStaffMonthTotal(String month, Long busstaffid);

    /**
     * 添加个人记录
     * @param request
     * @return
     */
    public int addStaffMonthTotal(ModifyCloudStaffMonthTotalRequest request);


    /**
     * 更新
     * @param request
     * @return
     */
    public int updateStaffMonthTotal(ModifyCloudStaffMonthTotalRequest request);

    /**
     * 获取列表
     * @param request
     * @return
     */
    /**
     * 代理列表---后台
     */
    public GetCloudStaffMonthTotalResponse getList(GetCloudStaffMonthTotalRequest request);
}
