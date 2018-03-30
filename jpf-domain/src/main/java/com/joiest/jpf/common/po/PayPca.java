package com.joiest.jpf.common.po;

import java.io.Serializable;

public class PayPca implements Serializable {
    /**
     * 
     */
    private Integer catid;

    /**
     * 
     */
    private Integer pid;

    /**
     * 
     */
    private String catpath;

    /**
     * 
     */
    private String catpathCn;

    /**
     * 
     */
    private String cat;

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private String postcode;

    /**
     * 
     */
    private String phonecode;

    /**
     * 
     */
    private Integer xuhao;

    private static final long serialVersionUID = 1L;

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCatpath() {
        return catpath;
    }

    public void setCatpath(String catpath) {
        this.catpath = catpath == null ? null : catpath.trim();
    }

    public String getCatpathCn() {
        return catpathCn;
    }

    public void setCatpathCn(String catpathCn) {
        this.catpathCn = catpathCn == null ? null : catpathCn.trim();
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat == null ? null : cat.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getPhonecode() {
        return phonecode;
    }

    public void setPhonecode(String phonecode) {
        this.phonecode = phonecode == null ? null : phonecode.trim();
    }

    public Integer getXuhao() {
        return xuhao;
    }

    public void setXuhao(Integer xuhao) {
        this.xuhao = xuhao;
    }

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", catid=").append(catid);
        sb.append(", pid=").append(pid);
        sb.append(", catpath=").append(catpath);
        sb.append(", catpathCn=").append(catpathCn);
        sb.append(", cat=").append(cat);
        sb.append(", code=").append(code);
        sb.append(", postcode=").append(postcode);
        sb.append(", phonecode=").append(phonecode);
        sb.append(", xuhao=").append(xuhao);
        sb.append("]");
        return sb.toString();
    }

    /**
     *
     * @param that
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PayPca other = (PayPca) that;
        return (this.getCatid() == null ? other.getCatid() == null : this.getCatid().equals(other.getCatid()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getCatpath() == null ? other.getCatpath() == null : this.getCatpath().equals(other.getCatpath()))
            && (this.getCatpathCn() == null ? other.getCatpathCn() == null : this.getCatpathCn().equals(other.getCatpathCn()))
            && (this.getCat() == null ? other.getCat() == null : this.getCat().equals(other.getCat()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getPostcode() == null ? other.getPostcode() == null : this.getPostcode().equals(other.getPostcode()))
            && (this.getPhonecode() == null ? other.getPhonecode() == null : this.getPhonecode().equals(other.getPhonecode()))
            && (this.getXuhao() == null ? other.getXuhao() == null : this.getXuhao().equals(other.getXuhao()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCatid() == null) ? 0 : getCatid().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getCatpath() == null) ? 0 : getCatpath().hashCode());
        result = prime * result + ((getCatpathCn() == null) ? 0 : getCatpathCn().hashCode());
        result = prime * result + ((getCat() == null) ? 0 : getCat().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getPostcode() == null) ? 0 : getPostcode().hashCode());
        result = prime * result + ((getPhonecode() == null) ? 0 : getPhonecode().hashCode());
        result = prime * result + ((getXuhao() == null) ? 0 : getXuhao().hashCode());
        return result;
    }
}