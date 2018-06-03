package com.joiest.jpf.facade;

import com.joiest.jpf.dto.AddSmsMessageRequest;
import com.joiest.jpf.dto.ModifyPayMessageRequest;
import com.joiest.jpf.dto.ModifyPayOrderPayMerRequest;
import com.joiest.jpf.entity.PcaInfo;

import java.util.List;

public interface PcaServiceFacade {

    /**
     * 获取省市区。不传之默认获取省
     * @param pid
     * @return
     */
    public List<PcaInfo> getPcas(String pid);

    /**
     * 分页获取地区信息
     * @param page
     * @param pageSize
     * @return
     */
    public List<PcaInfo> getPca(long page, long pageSize);

    public Integer getPcaCount();

    /**
     * 添加发送短信记录
     */
    public int addSmsMessage(AddSmsMessageRequest request);

    /**
     * 添加聚合订单回调信息
     */
    public int addPayMessage(ModifyPayMessageRequest request);

    /**
     * 更新聚合订单回调信息
     */
    public int modifyPayMessage(ModifyPayMessageRequest request);

    /**
     * 添加商户订单回调信息
     */
    public int addPayMerMessage(ModifyPayOrderPayMerRequest request);

    /**
     * 更新商户订单回调信息
     */
    public int modifyPayMerMessage(ModifyPayOrderPayMerRequest request);
}
