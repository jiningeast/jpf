package com.joiest.jpf.entity;

import java.util.Date;

public class CloudCompactInfo {
    /**
     * 自增ID
     */
    private String id;

    /**
     * 合同内容
     */
    private String content;

    /**
     * 合同类型：1:自由职业者合同模版，2:企业合同模版
     */
    private Byte type;

    /**
     * 更新时间
     */
    private Date created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
