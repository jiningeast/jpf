package com.joiest.jpf.dto;

import com.joiest.jpf.common.po.PayShopOrder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GetShopStockOrderRequest {
    /**
     * 主键id
     */
    private String id;

    /**
     * 采购订单号
     */
    private String orderNo;

    /**
     * 订单金额
     */
    private BigDecimal money;

    /**
     * 后台操作人id
     */
    private String operatorId;

    /**
     * 后台操作人姓名
     */
    private String operatorName;

    /**
     * 0=取消 1=新建，待提交 2=已提交，待审批 3=已审批，待付款 4=已付款，完成
     */
    private Byte status;

    /**
     * 状态数组
     */
    private List<Byte> statusArr;
    /**
     * 付款方式id
     */
    private Integer paywayId;

    /**
     * 付款方式名称
     */
    private String paywayCn;

    /**
     *
     */
    private Integer paytypeId;

    /**
     *
     */
    private String paytypeCn;

    /**
     * 备注
     */
    private String memo;

    /**
     * 上传的excel文件远程路径
     */
    private String ossUrl;

    /**
     * 商品采购入库时间【导入excel时间】
     */
    private Date cardtime;

    /**
     * 商品是否采购    1 是  2否
     */
    private Byte isUpload;

    /**
     * 总商品数量
     */
    private Integer productAmount;

    /**
     * 成功导入数量
     */
    private Integer importedAmount;

    /**
     * 审核人id
     */
    private String checkOperatorId;

    /**
     * 审核人姓名
     */
    private String checkOperatorName;

    /**
     * 审核时间
     */
    private Date checkTime;

    /**
     * 修改时间
     */
    private Date updatetime;
    /**
     *
     */
    private Date addtime;

    /**
     * 页码
     */
    private Long page;

    /**
     * 总条数
     */
    private Long rows;

    /**
     * 开始时间
     */
    private String addtimeStart;

    /**
     * 结束时间
     */
    private String addtimeEnd;


    /**
     * 前台传的付款方式id
     */
    private Integer typeNum;

    /**
     * 前台传的付款方式名称
     */
    private String  typeName;


    /**
     * 前台传的付款周期id
     */
    private Integer supplierNum;

    /**
     * 前台传的付款周期名称
     */
    private String  supplierName;


    /**
     * 商品详情
     */
    private String productsAll;

    /**
     * 订单总数量
     */
    private Integer countProducts;

    /**
     * 订单总金额
     */
    private BigDecimal countMoneys;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getPaywayId() {
        return paywayId;
    }

    public void setPaywayId(Integer paywayId) {
        this.paywayId = paywayId;
    }

    public String getPaywayCn() {
        return paywayCn;
    }

    public void setPaywayCn(String paywayCn) {
        this.paywayCn = paywayCn == null ? null : paywayCn.trim();
    }

    public Integer getPaytypeId() {
        return paytypeId;
    }

    public void setPaytypeId(Integer paytypeId) {
        this.paytypeId = paytypeId;
    }

    public String getPaytypeCn() {
        return paytypeCn;
    }

    public void setPaytypeCn(String paytypeCn) {
        this.paytypeCn = paytypeCn == null ? null : paytypeCn.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAddtimeStart() {
        return addtimeStart;
    }

    public void setAddtimeStart(String addtimeStart) {
        this.addtimeStart = addtimeStart;
    }

    public String getAddtimeEnd() {
        return addtimeEnd;
    }

    public void setAddtimeEnd(String addtimeEnd) {
        this.addtimeEnd = addtimeEnd;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public Integer getTypeNum() {
        return typeNum;
    }

    public void setTypeNum(Integer typeNum) {
        this.typeNum = typeNum;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getSupplierNum() {
        return supplierNum;
    }

    public void setSupplierNum(Integer supplierNum) {
        this.supplierNum = supplierNum;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProductsAll() {
        return productsAll;
    }

    public void setProductsAll(String productsAll) {
        this.productsAll = productsAll;
    }


    public BigDecimal getCountMoneys() {
        return countMoneys;
    }

    public void setCountMoneys(BigDecimal countMoneys) {
        this.countMoneys = countMoneys;
    }

    public String getOssUrl() {
        return ossUrl;
    }

    public void setOssUrl(String ossUrl) {
        this.ossUrl = ossUrl;
    }

    public Date getCardtime() {
        return cardtime;
    }

    public void setCardtime(Date cardtime) {
        this.cardtime = cardtime;
    }

    public Byte getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(Byte isUpload) {
        this.isUpload = isUpload;
    }

    public List<Byte> getStatusArr() {
        return statusArr;
    }

    public void setStatusArr(List<Byte> statusArr) {
        this.statusArr = statusArr;
    }

    public Integer getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Integer productAmount) {
        this.productAmount = productAmount;
    }

    public Integer getImportedAmount() {
        return importedAmount;
    }

    public void setImportedAmount(Integer importedAmount) {
        this.importedAmount = importedAmount;
    }

    public String getCheckOperatorId() {
        return checkOperatorId;
    }

    public void setCheckOperatorId(String checkOperatorId) {
        this.checkOperatorId = checkOperatorId;
    }

    public String getCheckOperatorName() {
        return checkOperatorName;
    }

    public void setCheckOperatorName(String checkOperatorName) {
        this.checkOperatorName = checkOperatorName;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getCountProducts() {
        return countProducts;
    }

    public void setCountProducts(Integer countProducts) {
        this.countProducts = countProducts;
    }
}
