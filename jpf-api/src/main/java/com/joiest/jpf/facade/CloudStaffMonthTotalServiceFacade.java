package com.joiest.jpf.facade;

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
     * @param month
     * @param busstaffid
     * @return
     */
    public int addStaffMonthTotal(String month, Long busstaffid);

    /**
     * 更新
     * @param id
     * @return
     */
    public int updateStaffMonthTotal(Long id);
}
