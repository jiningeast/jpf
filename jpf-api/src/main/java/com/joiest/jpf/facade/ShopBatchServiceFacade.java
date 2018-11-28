package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopBatch;
import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.dto.ShopBatchResponse;
import com.joiest.jpf.entity.ShopBatchInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ShopBatchServiceFacade {

    /**
     * 获取批量欣券批次
     */
    public ShopBatchResponse getBatches(ShopBatchRequest shopBatchRequest);

    /**
     * 新增批次及券
     */
    public JpfResponseDto addBatchCoupon(ShopBatchRequest shopBatchRequest, HttpServletResponse httpResponse);

    /**
     * 根据主键id获取批次
     */
    public ShopBatchInfo getBatchById(String id);

    /**
     * 根据主键id更新状态
     */
    public int updateColumnById(ShopBatchInfo shopBatchInfo);

    /**
     * 发送邮件和短信
     */
    public int sendEmail(String batchId) throws Exception;

    /**
     * 根据批次号查找批次
     */
    public PayShopBatch getBatchByBatchNo(String batchNo);

}
