package com.joiest.jpf.entity;

import java.io.Serializable;
import java.util.Date;

public class ChargeProductTypeInfo implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 商品类型名称
     */
    private String typeName;

    /**
     * 图片地址
     */
    private String imgurl;

    /**
     * 商品类型状态 0:显示 1:不显示
     */
    private Byte status;

    /**
     * 后台添加人id
     */
    private String operatorId;

    /**
     * 后台添加人姓名
     */
    private String operatorName;

    /**
     * 添加时间
     */
    private Date addtime;

    /**
     * 更新时间
     */
    private Date updatetime;


}