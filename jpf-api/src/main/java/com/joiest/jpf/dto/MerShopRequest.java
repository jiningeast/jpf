package com.joiest.jpf.dto;

import javax.validation.constraints.Min;
import java.util.Date;

public class MerShopRequest {

    /**
     * ID
     */
    private Long id;

    /**
     * 商户ID
     */
    private Long mtsid;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 层级筛选
     */
    private int type;

    /**
     * 添加时间-起始时间
     */
    private String addtimeStart;

    /**
     * 添加时间-截止时间
     */
    private String addtimeEnd;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 页码
     */
    @Min(1)
    private long page;

    /**
     * 查询条数
     */
    @Min(10)
    private long rows;

    private String path;

    /**
     * 是否删除
     */
    private Integer isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMtsid() {
        return mtsid;
    }

    public void setMtsid(Long mtsid) {
        this.mtsid = mtsid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
