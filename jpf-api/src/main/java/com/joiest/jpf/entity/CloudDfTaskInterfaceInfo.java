package com.joiest.jpf.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CloudDfTaskInterfaceInfo {
    /**
     *
     */
    private Long id;

    /**
     * 打款批次id
     */
    private String batchid;

    /**
     * 外来批次编号
     */
    private String requestBatchno;

    /**
     * 单独打款id df_money.id
     */
    private String requestDfId;

    /**
     * 外来请求信息
     */
    private String requestStr;

    /**
     * 订单总数
     */
    private Integer orderCount;

    /**
     * 订单总金额
     */
    private BigDecimal orderMoney;

    /**
     * 已打款数量
     */
    private Integer alreadyCount;

    /**
     * 已打款金额
     */
    private BigDecimal alreadyMoney;

    /**
     * 失败订单数量
     */
    private Integer failCount;

    /**
     * 失败金额
     */
    private BigDecimal failMoney;

    /**
     * 数据写入状态 0未写入 1处理中 2处理完成
     */
    private Integer insertStatus;

    /**
     * 状态 0未处理 1处理中 2全部完成 3部分完成
     */
    private Integer status;

    /**
     * 添加时间
     */
    private Date created;

    /**
     * 更新时间
     */
    private Date updated;
}
