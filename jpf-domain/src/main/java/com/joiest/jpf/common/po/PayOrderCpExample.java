package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayOrderCpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayOrderCpExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *
     * @param orderByClause
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     * @param distinct
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * @param criteria
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     *
     * @param pageNo
     */
    public void setPageNo(long pageNo) {
        this.pageNo=pageNo;
    }

    /**
     *
     */
    public long getPageNo() {
        return pageNo;
    }

    /**
     *
     * @param pageSize
     */
    public void setPageSize(long pageSize) {
        this.pageSize=pageSize;
    }

    /**
     *
     */
    public long getPageSize() {
        return pageSize;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMerchNoIsNull() {
            addCriterion("merch_no is null");
            return (Criteria) this;
        }

        public Criteria andMerchNoIsNotNull() {
            addCriterion("merch_no is not null");
            return (Criteria) this;
        }

        public Criteria andMerchNoEqualTo(String value) {
            addCriterion("merch_no =", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoNotEqualTo(String value) {
            addCriterion("merch_no <>", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoGreaterThan(String value) {
            addCriterion("merch_no >", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoGreaterThanOrEqualTo(String value) {
            addCriterion("merch_no >=", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoLessThan(String value) {
            addCriterion("merch_no <", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoLessThanOrEqualTo(String value) {
            addCriterion("merch_no <=", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoLike(String value) {
            addCriterion("merch_no like", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoNotLike(String value) {
            addCriterion("merch_no not like", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoIn(List<String> values) {
            addCriterion("merch_no in", values, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoNotIn(List<String> values) {
            addCriterion("merch_no not in", values, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoBetween(String value1, String value2) {
            addCriterion("merch_no between", value1, value2, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoNotBetween(String value1, String value2) {
            addCriterion("merch_no not between", value1, value2, "merchNo");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(String value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(String value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(String value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(String value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(String value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<String> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<String> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(String value1, String value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(String value1, String value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andMtsidIsNull() {
            addCriterion("mtsid is null");
            return (Criteria) this;
        }

        public Criteria andMtsidIsNotNull() {
            addCriterion("mtsid is not null");
            return (Criteria) this;
        }

        public Criteria andMtsidEqualTo(Long value) {
            addCriterion("mtsid =", value, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidNotEqualTo(Long value) {
            addCriterion("mtsid <>", value, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidGreaterThan(Long value) {
            addCriterion("mtsid >", value, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidGreaterThanOrEqualTo(Long value) {
            addCriterion("mtsid >=", value, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidLessThan(Long value) {
            addCriterion("mtsid <", value, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidLessThanOrEqualTo(Long value) {
            addCriterion("mtsid <=", value, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidIn(List<Long> values) {
            addCriterion("mtsid in", values, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidNotIn(List<Long> values) {
            addCriterion("mtsid not in", values, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidBetween(Long value1, Long value2) {
            addCriterion("mtsid between", value1, value2, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidNotBetween(Long value1, Long value2) {
            addCriterion("mtsid not between", value1, value2, "mtsid");
            return (Criteria) this;
        }

        public Criteria andInterestmodeIsNull() {
            addCriterion("interestmode is null");
            return (Criteria) this;
        }

        public Criteria andInterestmodeIsNotNull() {
            addCriterion("interestmode is not null");
            return (Criteria) this;
        }

        public Criteria andInterestmodeEqualTo(Long value) {
            addCriterion("interestmode =", value, "interestmode");
            return (Criteria) this;
        }

        public Criteria andInterestmodeNotEqualTo(Long value) {
            addCriterion("interestmode <>", value, "interestmode");
            return (Criteria) this;
        }

        public Criteria andInterestmodeGreaterThan(Long value) {
            addCriterion("interestmode >", value, "interestmode");
            return (Criteria) this;
        }

        public Criteria andInterestmodeGreaterThanOrEqualTo(Long value) {
            addCriterion("interestmode >=", value, "interestmode");
            return (Criteria) this;
        }

        public Criteria andInterestmodeLessThan(Long value) {
            addCriterion("interestmode <", value, "interestmode");
            return (Criteria) this;
        }

        public Criteria andInterestmodeLessThanOrEqualTo(Long value) {
            addCriterion("interestmode <=", value, "interestmode");
            return (Criteria) this;
        }

        public Criteria andInterestmodeIn(List<Long> values) {
            addCriterion("interestmode in", values, "interestmode");
            return (Criteria) this;
        }

        public Criteria andInterestmodeNotIn(List<Long> values) {
            addCriterion("interestmode not in", values, "interestmode");
            return (Criteria) this;
        }

        public Criteria andInterestmodeBetween(Long value1, Long value2) {
            addCriterion("interestmode between", value1, value2, "interestmode");
            return (Criteria) this;
        }

        public Criteria andInterestmodeNotBetween(Long value1, Long value2) {
            addCriterion("interestmode not between", value1, value2, "interestmode");
            return (Criteria) this;
        }

        public Criteria andSubmeridIsNull() {
            addCriterion("submerid is null");
            return (Criteria) this;
        }

        public Criteria andSubmeridIsNotNull() {
            addCriterion("submerid is not null");
            return (Criteria) this;
        }

        public Criteria andSubmeridEqualTo(String value) {
            addCriterion("submerid =", value, "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmeridNotEqualTo(String value) {
            addCriterion("submerid <>", value, "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmeridGreaterThan(String value) {
            addCriterion("submerid >", value, "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmeridGreaterThanOrEqualTo(String value) {
            addCriterion("submerid >=", value, "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmeridLessThan(String value) {
            addCriterion("submerid <", value, "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmeridLessThanOrEqualTo(String value) {
            addCriterion("submerid <=", value, "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmeridLike(String value) {
            addCriterion("submerid like", value, "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmeridNotLike(String value) {
            addCriterion("submerid not like", value, "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmeridIn(List<String> values) {
            addCriterion("submerid in", values, "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmeridNotIn(List<String> values) {
            addCriterion("submerid not in", values, "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmeridBetween(String value1, String value2) {
            addCriterion("submerid between", value1, value2, "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmeridNotBetween(String value1, String value2) {
            addCriterion("submerid not between", value1, value2, "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmernameIsNull() {
            addCriterion("submername is null");
            return (Criteria) this;
        }

        public Criteria andSubmernameIsNotNull() {
            addCriterion("submername is not null");
            return (Criteria) this;
        }

        public Criteria andSubmernameEqualTo(String value) {
            addCriterion("submername =", value, "submername");
            return (Criteria) this;
        }

        public Criteria andSubmernameNotEqualTo(String value) {
            addCriterion("submername <>", value, "submername");
            return (Criteria) this;
        }

        public Criteria andSubmernameGreaterThan(String value) {
            addCriterion("submername >", value, "submername");
            return (Criteria) this;
        }

        public Criteria andSubmernameGreaterThanOrEqualTo(String value) {
            addCriterion("submername >=", value, "submername");
            return (Criteria) this;
        }

        public Criteria andSubmernameLessThan(String value) {
            addCriterion("submername <", value, "submername");
            return (Criteria) this;
        }

        public Criteria andSubmernameLessThanOrEqualTo(String value) {
            addCriterion("submername <=", value, "submername");
            return (Criteria) this;
        }

        public Criteria andSubmernameLike(String value) {
            addCriterion("submername like", value, "submername");
            return (Criteria) this;
        }

        public Criteria andSubmernameNotLike(String value) {
            addCriterion("submername not like", value, "submername");
            return (Criteria) this;
        }

        public Criteria andSubmernameIn(List<String> values) {
            addCriterion("submername in", values, "submername");
            return (Criteria) this;
        }

        public Criteria andSubmernameNotIn(List<String> values) {
            addCriterion("submername not in", values, "submername");
            return (Criteria) this;
        }

        public Criteria andSubmernameBetween(String value1, String value2) {
            addCriterion("submername between", value1, value2, "submername");
            return (Criteria) this;
        }

        public Criteria andSubmernameNotBetween(String value1, String value2) {
            addCriterion("submername not between", value1, value2, "submername");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrIsNull() {
            addCriterion("submerabbr is null");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrIsNotNull() {
            addCriterion("submerabbr is not null");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrEqualTo(String value) {
            addCriterion("submerabbr =", value, "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrNotEqualTo(String value) {
            addCriterion("submerabbr <>", value, "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrGreaterThan(String value) {
            addCriterion("submerabbr >", value, "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrGreaterThanOrEqualTo(String value) {
            addCriterion("submerabbr >=", value, "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrLessThan(String value) {
            addCriterion("submerabbr <", value, "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrLessThanOrEqualTo(String value) {
            addCriterion("submerabbr <=", value, "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrLike(String value) {
            addCriterion("submerabbr like", value, "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrNotLike(String value) {
            addCriterion("submerabbr not like", value, "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrIn(List<String> values) {
            addCriterion("submerabbr in", values, "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrNotIn(List<String> values) {
            addCriterion("submerabbr not in", values, "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrBetween(String value1, String value2) {
            addCriterion("submerabbr between", value1, value2, "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrNotBetween(String value1, String value2) {
            addCriterion("submerabbr not between", value1, value2, "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSignednameIsNull() {
            addCriterion("signedname is null");
            return (Criteria) this;
        }

        public Criteria andSignednameIsNotNull() {
            addCriterion("signedname is not null");
            return (Criteria) this;
        }

        public Criteria andSignednameEqualTo(String value) {
            addCriterion("signedname =", value, "signedname");
            return (Criteria) this;
        }

        public Criteria andSignednameNotEqualTo(String value) {
            addCriterion("signedname <>", value, "signedname");
            return (Criteria) this;
        }

        public Criteria andSignednameGreaterThan(String value) {
            addCriterion("signedname >", value, "signedname");
            return (Criteria) this;
        }

        public Criteria andSignednameGreaterThanOrEqualTo(String value) {
            addCriterion("signedname >=", value, "signedname");
            return (Criteria) this;
        }

        public Criteria andSignednameLessThan(String value) {
            addCriterion("signedname <", value, "signedname");
            return (Criteria) this;
        }

        public Criteria andSignednameLessThanOrEqualTo(String value) {
            addCriterion("signedname <=", value, "signedname");
            return (Criteria) this;
        }

        public Criteria andSignednameLike(String value) {
            addCriterion("signedname like", value, "signedname");
            return (Criteria) this;
        }

        public Criteria andSignednameNotLike(String value) {
            addCriterion("signedname not like", value, "signedname");
            return (Criteria) this;
        }

        public Criteria andSignednameIn(List<String> values) {
            addCriterion("signedname in", values, "signedname");
            return (Criteria) this;
        }

        public Criteria andSignednameNotIn(List<String> values) {
            addCriterion("signedname not in", values, "signedname");
            return (Criteria) this;
        }

        public Criteria andSignednameBetween(String value1, String value2) {
            addCriterion("signedname between", value1, value2, "signedname");
            return (Criteria) this;
        }

        public Criteria andSignednameNotBetween(String value1, String value2) {
            addCriterion("signedname not between", value1, value2, "signedname");
            return (Criteria) this;
        }

        public Criteria andIdtypeIsNull() {
            addCriterion("idtype is null");
            return (Criteria) this;
        }

        public Criteria andIdtypeIsNotNull() {
            addCriterion("idtype is not null");
            return (Criteria) this;
        }

        public Criteria andIdtypeEqualTo(Byte value) {
            addCriterion("idtype =", value, "idtype");
            return (Criteria) this;
        }

        public Criteria andIdtypeNotEqualTo(Byte value) {
            addCriterion("idtype <>", value, "idtype");
            return (Criteria) this;
        }

        public Criteria andIdtypeGreaterThan(Byte value) {
            addCriterion("idtype >", value, "idtype");
            return (Criteria) this;
        }

        public Criteria andIdtypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("idtype >=", value, "idtype");
            return (Criteria) this;
        }

        public Criteria andIdtypeLessThan(Byte value) {
            addCriterion("idtype <", value, "idtype");
            return (Criteria) this;
        }

        public Criteria andIdtypeLessThanOrEqualTo(Byte value) {
            addCriterion("idtype <=", value, "idtype");
            return (Criteria) this;
        }

        public Criteria andIdtypeIn(List<Byte> values) {
            addCriterion("idtype in", values, "idtype");
            return (Criteria) this;
        }

        public Criteria andIdtypeNotIn(List<Byte> values) {
            addCriterion("idtype not in", values, "idtype");
            return (Criteria) this;
        }

        public Criteria andIdtypeBetween(Byte value1, Byte value2) {
            addCriterion("idtype between", value1, value2, "idtype");
            return (Criteria) this;
        }

        public Criteria andIdtypeNotBetween(Byte value1, Byte value2) {
            addCriterion("idtype not between", value1, value2, "idtype");
            return (Criteria) this;
        }

        public Criteria andIdnoIsNull() {
            addCriterion("idno is null");
            return (Criteria) this;
        }

        public Criteria andIdnoIsNotNull() {
            addCriterion("idno is not null");
            return (Criteria) this;
        }

        public Criteria andIdnoEqualTo(String value) {
            addCriterion("idno =", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoNotEqualTo(String value) {
            addCriterion("idno <>", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoGreaterThan(String value) {
            addCriterion("idno >", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoGreaterThanOrEqualTo(String value) {
            addCriterion("idno >=", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoLessThan(String value) {
            addCriterion("idno <", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoLessThanOrEqualTo(String value) {
            addCriterion("idno <=", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoLike(String value) {
            addCriterion("idno like", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoNotLike(String value) {
            addCriterion("idno not like", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoIn(List<String> values) {
            addCriterion("idno in", values, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoNotIn(List<String> values) {
            addCriterion("idno not in", values, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoBetween(String value1, String value2) {
            addCriterion("idno between", value1, value2, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoNotBetween(String value1, String value2) {
            addCriterion("idno not between", value1, value2, "idno");
            return (Criteria) this;
        }

        public Criteria andMobilenoIsNull() {
            addCriterion("mobileno is null");
            return (Criteria) this;
        }

        public Criteria andMobilenoIsNotNull() {
            addCriterion("mobileno is not null");
            return (Criteria) this;
        }

        public Criteria andMobilenoEqualTo(String value) {
            addCriterion("mobileno =", value, "mobileno");
            return (Criteria) this;
        }

        public Criteria andMobilenoNotEqualTo(String value) {
            addCriterion("mobileno <>", value, "mobileno");
            return (Criteria) this;
        }

        public Criteria andMobilenoGreaterThan(String value) {
            addCriterion("mobileno >", value, "mobileno");
            return (Criteria) this;
        }

        public Criteria andMobilenoGreaterThanOrEqualTo(String value) {
            addCriterion("mobileno >=", value, "mobileno");
            return (Criteria) this;
        }

        public Criteria andMobilenoLessThan(String value) {
            addCriterion("mobileno <", value, "mobileno");
            return (Criteria) this;
        }

        public Criteria andMobilenoLessThanOrEqualTo(String value) {
            addCriterion("mobileno <=", value, "mobileno");
            return (Criteria) this;
        }

        public Criteria andMobilenoLike(String value) {
            addCriterion("mobileno like", value, "mobileno");
            return (Criteria) this;
        }

        public Criteria andMobilenoNotLike(String value) {
            addCriterion("mobileno not like", value, "mobileno");
            return (Criteria) this;
        }

        public Criteria andMobilenoIn(List<String> values) {
            addCriterion("mobileno in", values, "mobileno");
            return (Criteria) this;
        }

        public Criteria andMobilenoNotIn(List<String> values) {
            addCriterion("mobileno not in", values, "mobileno");
            return (Criteria) this;
        }

        public Criteria andMobilenoBetween(String value1, String value2) {
            addCriterion("mobileno between", value1, value2, "mobileno");
            return (Criteria) this;
        }

        public Criteria andMobilenoNotBetween(String value1, String value2) {
            addCriterion("mobileno not between", value1, value2, "mobileno");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeIsNull() {
            addCriterion("selectfinacode is null");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeIsNotNull() {
            addCriterion("selectfinacode is not null");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeEqualTo(String value) {
            addCriterion("selectfinacode =", value, "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeNotEqualTo(String value) {
            addCriterion("selectfinacode <>", value, "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeGreaterThan(String value) {
            addCriterion("selectfinacode >", value, "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeGreaterThanOrEqualTo(String value) {
            addCriterion("selectfinacode >=", value, "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeLessThan(String value) {
            addCriterion("selectfinacode <", value, "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeLessThanOrEqualTo(String value) {
            addCriterion("selectfinacode <=", value, "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeLike(String value) {
            addCriterion("selectfinacode like", value, "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeNotLike(String value) {
            addCriterion("selectfinacode not like", value, "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeIn(List<String> values) {
            addCriterion("selectfinacode in", values, "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeNotIn(List<String> values) {
            addCriterion("selectfinacode not in", values, "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeBetween(String value1, String value2) {
            addCriterion("selectfinacode between", value1, value2, "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeNotBetween(String value1, String value2) {
            addCriterion("selectfinacode not between", value1, value2, "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andBankaccounttypeIsNull() {
            addCriterion("bankaccounttype is null");
            return (Criteria) this;
        }

        public Criteria andBankaccounttypeIsNotNull() {
            addCriterion("bankaccounttype is not null");
            return (Criteria) this;
        }

        public Criteria andBankaccounttypeEqualTo(Byte value) {
            addCriterion("bankaccounttype =", value, "bankaccounttype");
            return (Criteria) this;
        }

        public Criteria andBankaccounttypeNotEqualTo(Byte value) {
            addCriterion("bankaccounttype <>", value, "bankaccounttype");
            return (Criteria) this;
        }

        public Criteria andBankaccounttypeGreaterThan(Byte value) {
            addCriterion("bankaccounttype >", value, "bankaccounttype");
            return (Criteria) this;
        }

        public Criteria andBankaccounttypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("bankaccounttype >=", value, "bankaccounttype");
            return (Criteria) this;
        }

        public Criteria andBankaccounttypeLessThan(Byte value) {
            addCriterion("bankaccounttype <", value, "bankaccounttype");
            return (Criteria) this;
        }

        public Criteria andBankaccounttypeLessThanOrEqualTo(Byte value) {
            addCriterion("bankaccounttype <=", value, "bankaccounttype");
            return (Criteria) this;
        }

        public Criteria andBankaccounttypeIn(List<Byte> values) {
            addCriterion("bankaccounttype in", values, "bankaccounttype");
            return (Criteria) this;
        }

        public Criteria andBankaccounttypeNotIn(List<Byte> values) {
            addCriterion("bankaccounttype not in", values, "bankaccounttype");
            return (Criteria) this;
        }

        public Criteria andBankaccounttypeBetween(Byte value1, Byte value2) {
            addCriterion("bankaccounttype between", value1, value2, "bankaccounttype");
            return (Criteria) this;
        }

        public Criteria andBankaccounttypeNotBetween(Byte value1, Byte value2) {
            addCriterion("bankaccounttype not between", value1, value2, "bankaccounttype");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNull() {
            addCriterion("bankname is null");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNotNull() {
            addCriterion("bankname is not null");
            return (Criteria) this;
        }

        public Criteria andBanknameEqualTo(String value) {
            addCriterion("bankname =", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotEqualTo(String value) {
            addCriterion("bankname <>", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThan(String value) {
            addCriterion("bankname >", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThanOrEqualTo(String value) {
            addCriterion("bankname >=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThan(String value) {
            addCriterion("bankname <", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThanOrEqualTo(String value) {
            addCriterion("bankname <=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLike(String value) {
            addCriterion("bankname like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotLike(String value) {
            addCriterion("bankname not like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameIn(List<String> values) {
            addCriterion("bankname in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotIn(List<String> values) {
            addCriterion("bankname not in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameBetween(String value1, String value2) {
            addCriterion("bankname between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotBetween(String value1, String value2) {
            addCriterion("bankname not between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberIsNull() {
            addCriterion("bankaccountnumber is null");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberIsNotNull() {
            addCriterion("bankaccountnumber is not null");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberEqualTo(String value) {
            addCriterion("bankaccountnumber =", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberNotEqualTo(String value) {
            addCriterion("bankaccountnumber <>", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberGreaterThan(String value) {
            addCriterion("bankaccountnumber >", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberGreaterThanOrEqualTo(String value) {
            addCriterion("bankaccountnumber >=", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberLessThan(String value) {
            addCriterion("bankaccountnumber <", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberLessThanOrEqualTo(String value) {
            addCriterion("bankaccountnumber <=", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberLike(String value) {
            addCriterion("bankaccountnumber like", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberNotLike(String value) {
            addCriterion("bankaccountnumber not like", value, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberIn(List<String> values) {
            addCriterion("bankaccountnumber in", values, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberNotIn(List<String> values) {
            addCriterion("bankaccountnumber not in", values, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberBetween(String value1, String value2) {
            addCriterion("bankaccountnumber between", value1, value2, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberNotBetween(String value1, String value2) {
            addCriterion("bankaccountnumber not between", value1, value2, "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andCvn2IsNull() {
            addCriterion("cvn2 is null");
            return (Criteria) this;
        }

        public Criteria andCvn2IsNotNull() {
            addCriterion("cvn2 is not null");
            return (Criteria) this;
        }

        public Criteria andCvn2EqualTo(Long value) {
            addCriterion("cvn2 =", value, "cvn2");
            return (Criteria) this;
        }

        public Criteria andCvn2NotEqualTo(Long value) {
            addCriterion("cvn2 <>", value, "cvn2");
            return (Criteria) this;
        }

        public Criteria andCvn2GreaterThan(Long value) {
            addCriterion("cvn2 >", value, "cvn2");
            return (Criteria) this;
        }

        public Criteria andCvn2GreaterThanOrEqualTo(Long value) {
            addCriterion("cvn2 >=", value, "cvn2");
            return (Criteria) this;
        }

        public Criteria andCvn2LessThan(Long value) {
            addCriterion("cvn2 <", value, "cvn2");
            return (Criteria) this;
        }

        public Criteria andCvn2LessThanOrEqualTo(Long value) {
            addCriterion("cvn2 <=", value, "cvn2");
            return (Criteria) this;
        }

        public Criteria andCvn2In(List<Long> values) {
            addCriterion("cvn2 in", values, "cvn2");
            return (Criteria) this;
        }

        public Criteria andCvn2NotIn(List<Long> values) {
            addCriterion("cvn2 not in", values, "cvn2");
            return (Criteria) this;
        }

        public Criteria andCvn2Between(Long value1, Long value2) {
            addCriterion("cvn2 between", value1, value2, "cvn2");
            return (Criteria) this;
        }

        public Criteria andCvn2NotBetween(Long value1, Long value2) {
            addCriterion("cvn2 not between", value1, value2, "cvn2");
            return (Criteria) this;
        }

        public Criteria andValiditycardIsNull() {
            addCriterion("validitycard is null");
            return (Criteria) this;
        }

        public Criteria andValiditycardIsNotNull() {
            addCriterion("validitycard is not null");
            return (Criteria) this;
        }

        public Criteria andValiditycardEqualTo(String value) {
            addCriterion("validitycard =", value, "validitycard");
            return (Criteria) this;
        }

        public Criteria andValiditycardNotEqualTo(String value) {
            addCriterion("validitycard <>", value, "validitycard");
            return (Criteria) this;
        }

        public Criteria andValiditycardGreaterThan(String value) {
            addCriterion("validitycard >", value, "validitycard");
            return (Criteria) this;
        }

        public Criteria andValiditycardGreaterThanOrEqualTo(String value) {
            addCriterion("validitycard >=", value, "validitycard");
            return (Criteria) this;
        }

        public Criteria andValiditycardLessThan(String value) {
            addCriterion("validitycard <", value, "validitycard");
            return (Criteria) this;
        }

        public Criteria andValiditycardLessThanOrEqualTo(String value) {
            addCriterion("validitycard <=", value, "validitycard");
            return (Criteria) this;
        }

        public Criteria andValiditycardIn(List<String> values) {
            addCriterion("validitycard in", values, "validitycard");
            return (Criteria) this;
        }

        public Criteria andValiditycardNotIn(List<String> values) {
            addCriterion("validitycard not in", values, "validitycard");
            return (Criteria) this;
        }

        public Criteria andValiditycardBetween(String value1, String value2) {
            addCriterion("validitycard between", value1, value2, "validitycard");
            return (Criteria) this;
        }

        public Criteria andValiditycardNotBetween(String value1, String value2) {
            addCriterion("validitycard not between", value1, value2, "validitycard");
            return (Criteria) this;
        }

        public Criteria andValidityyearIsNull() {
            addCriterion("validityyear is null");
            return (Criteria) this;
        }

        public Criteria andValidityyearIsNotNull() {
            addCriterion("validityyear is not null");
            return (Criteria) this;
        }

        public Criteria andValidityyearEqualTo(Date value) {
            addCriterion("validityyear =", value, "validityyear");
            return (Criteria) this;
        }

        public Criteria andValidityyearNotEqualTo(Date value) {
            addCriterion("validityyear <>", value, "validityyear");
            return (Criteria) this;
        }

        public Criteria andValidityyearGreaterThan(Date value) {
            addCriterion("validityyear >", value, "validityyear");
            return (Criteria) this;
        }

        public Criteria andValidityyearGreaterThanOrEqualTo(Date value) {
            addCriterion("validityyear >=", value, "validityyear");
            return (Criteria) this;
        }

        public Criteria andValidityyearLessThan(Date value) {
            addCriterion("validityyear <", value, "validityyear");
            return (Criteria) this;
        }

        public Criteria andValidityyearLessThanOrEqualTo(Date value) {
            addCriterion("validityyear <=", value, "validityyear");
            return (Criteria) this;
        }

        public Criteria andValidityyearIn(List<Date> values) {
            addCriterion("validityyear in", values, "validityyear");
            return (Criteria) this;
        }

        public Criteria andValidityyearNotIn(List<Date> values) {
            addCriterion("validityyear not in", values, "validityyear");
            return (Criteria) this;
        }

        public Criteria andValidityyearBetween(Date value1, Date value2) {
            addCriterion("validityyear between", value1, value2, "validityyear");
            return (Criteria) this;
        }

        public Criteria andValidityyearNotBetween(Date value1, Date value2) {
            addCriterion("validityyear not between", value1, value2, "validityyear");
            return (Criteria) this;
        }

        public Criteria andClientipIsNull() {
            addCriterion("clientIp is null");
            return (Criteria) this;
        }

        public Criteria andClientipIsNotNull() {
            addCriterion("clientIp is not null");
            return (Criteria) this;
        }

        public Criteria andClientipEqualTo(String value) {
            addCriterion("clientIp =", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipNotEqualTo(String value) {
            addCriterion("clientIp <>", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipGreaterThan(String value) {
            addCriterion("clientIp >", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipGreaterThanOrEqualTo(String value) {
            addCriterion("clientIp >=", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipLessThan(String value) {
            addCriterion("clientIp <", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipLessThanOrEqualTo(String value) {
            addCriterion("clientIp <=", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipLike(String value) {
            addCriterion("clientIp like", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipNotLike(String value) {
            addCriterion("clientIp not like", value, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipIn(List<String> values) {
            addCriterion("clientIp in", values, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipNotIn(List<String> values) {
            addCriterion("clientIp not in", values, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipBetween(String value1, String value2) {
            addCriterion("clientIp between", value1, value2, "clientip");
            return (Criteria) this;
        }

        public Criteria andClientipNotBetween(String value1, String value2) {
            addCriterion("clientIp not between", value1, value2, "clientip");
            return (Criteria) this;
        }

        public Criteria andTrannoIsNull() {
            addCriterion("tranNo is null");
            return (Criteria) this;
        }

        public Criteria andTrannoIsNotNull() {
            addCriterion("tranNo is not null");
            return (Criteria) this;
        }

        public Criteria andTrannoEqualTo(String value) {
            addCriterion("tranNo =", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotEqualTo(String value) {
            addCriterion("tranNo <>", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoGreaterThan(String value) {
            addCriterion("tranNo >", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoGreaterThanOrEqualTo(String value) {
            addCriterion("tranNo >=", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLessThan(String value) {
            addCriterion("tranNo <", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLessThanOrEqualTo(String value) {
            addCriterion("tranNo <=", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLike(String value) {
            addCriterion("tranNo like", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotLike(String value) {
            addCriterion("tranNo not like", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoIn(List<String> values) {
            addCriterion("tranNo in", values, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotIn(List<String> values) {
            addCriterion("tranNo not in", values, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoBetween(String value1, String value2) {
            addCriterion("tranNo between", value1, value2, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotBetween(String value1, String value2) {
            addCriterion("tranNo not between", value1, value2, "tranno");
            return (Criteria) this;
        }

        public Criteria andSignstatusIsNull() {
            addCriterion("signstatus is null");
            return (Criteria) this;
        }

        public Criteria andSignstatusIsNotNull() {
            addCriterion("signstatus is not null");
            return (Criteria) this;
        }

        public Criteria andSignstatusEqualTo(String value) {
            addCriterion("signstatus =", value, "signstatus");
            return (Criteria) this;
        }

        public Criteria andSignstatusNotEqualTo(String value) {
            addCriterion("signstatus <>", value, "signstatus");
            return (Criteria) this;
        }

        public Criteria andSignstatusGreaterThan(String value) {
            addCriterion("signstatus >", value, "signstatus");
            return (Criteria) this;
        }

        public Criteria andSignstatusGreaterThanOrEqualTo(String value) {
            addCriterion("signstatus >=", value, "signstatus");
            return (Criteria) this;
        }

        public Criteria andSignstatusLessThan(String value) {
            addCriterion("signstatus <", value, "signstatus");
            return (Criteria) this;
        }

        public Criteria andSignstatusLessThanOrEqualTo(String value) {
            addCriterion("signstatus <=", value, "signstatus");
            return (Criteria) this;
        }

        public Criteria andSignstatusLike(String value) {
            addCriterion("signstatus like", value, "signstatus");
            return (Criteria) this;
        }

        public Criteria andSignstatusNotLike(String value) {
            addCriterion("signstatus not like", value, "signstatus");
            return (Criteria) this;
        }

        public Criteria andSignstatusIn(List<String> values) {
            addCriterion("signstatus in", values, "signstatus");
            return (Criteria) this;
        }

        public Criteria andSignstatusNotIn(List<String> values) {
            addCriterion("signstatus not in", values, "signstatus");
            return (Criteria) this;
        }

        public Criteria andSignstatusBetween(String value1, String value2) {
            addCriterion("signstatus between", value1, value2, "signstatus");
            return (Criteria) this;
        }

        public Criteria andSignstatusNotBetween(String value1, String value2) {
            addCriterion("signstatus not between", value1, value2, "signstatus");
            return (Criteria) this;
        }

        public Criteria andSysagreenoIsNull() {
            addCriterion("sysagreeno is null");
            return (Criteria) this;
        }

        public Criteria andSysagreenoIsNotNull() {
            addCriterion("sysagreeno is not null");
            return (Criteria) this;
        }

        public Criteria andSysagreenoEqualTo(String value) {
            addCriterion("sysagreeno =", value, "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andSysagreenoNotEqualTo(String value) {
            addCriterion("sysagreeno <>", value, "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andSysagreenoGreaterThan(String value) {
            addCriterion("sysagreeno >", value, "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andSysagreenoGreaterThanOrEqualTo(String value) {
            addCriterion("sysagreeno >=", value, "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andSysagreenoLessThan(String value) {
            addCriterion("sysagreeno <", value, "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andSysagreenoLessThanOrEqualTo(String value) {
            addCriterion("sysagreeno <=", value, "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andSysagreenoLike(String value) {
            addCriterion("sysagreeno like", value, "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andSysagreenoNotLike(String value) {
            addCriterion("sysagreeno not like", value, "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andSysagreenoIn(List<String> values) {
            addCriterion("sysagreeno in", values, "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andSysagreenoNotIn(List<String> values) {
            addCriterion("sysagreeno not in", values, "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andSysagreenoBetween(String value1, String value2) {
            addCriterion("sysagreeno between", value1, value2, "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andSysagreenoNotBetween(String value1, String value2) {
            addCriterion("sysagreeno not between", value1, value2, "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andReturnContentIsNull() {
            addCriterion("return_content is null");
            return (Criteria) this;
        }

        public Criteria andReturnContentIsNotNull() {
            addCriterion("return_content is not null");
            return (Criteria) this;
        }

        public Criteria andReturnContentEqualTo(String value) {
            addCriterion("return_content =", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentNotEqualTo(String value) {
            addCriterion("return_content <>", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentGreaterThan(String value) {
            addCriterion("return_content >", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentGreaterThanOrEqualTo(String value) {
            addCriterion("return_content >=", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentLessThan(String value) {
            addCriterion("return_content <", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentLessThanOrEqualTo(String value) {
            addCriterion("return_content <=", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentLike(String value) {
            addCriterion("return_content like", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentNotLike(String value) {
            addCriterion("return_content not like", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentIn(List<String> values) {
            addCriterion("return_content in", values, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentNotIn(List<String> values) {
            addCriterion("return_content not in", values, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentBetween(String value1, String value2) {
            addCriterion("return_content between", value1, value2, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentNotBetween(String value1, String value2) {
            addCriterion("return_content not between", value1, value2, "returnContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentIsNull() {
            addCriterion("notify_content is null");
            return (Criteria) this;
        }

        public Criteria andNotifyContentIsNotNull() {
            addCriterion("notify_content is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyContentEqualTo(String value) {
            addCriterion("notify_content =", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentNotEqualTo(String value) {
            addCriterion("notify_content <>", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentGreaterThan(String value) {
            addCriterion("notify_content >", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentGreaterThanOrEqualTo(String value) {
            addCriterion("notify_content >=", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentLessThan(String value) {
            addCriterion("notify_content <", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentLessThanOrEqualTo(String value) {
            addCriterion("notify_content <=", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentLike(String value) {
            addCriterion("notify_content like", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentNotLike(String value) {
            addCriterion("notify_content not like", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentIn(List<String> values) {
            addCriterion("notify_content in", values, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentNotIn(List<String> values) {
            addCriterion("notify_content not in", values, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentBetween(String value1, String value2) {
            addCriterion("notify_content between", value1, value2, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentNotBetween(String value1, String value2) {
            addCriterion("notify_content not between", value1, value2, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andMerchNoLikeInsensitive(String value) {
            addCriterion("upper(merch_no) like", value.toUpperCase(), "merchNo");
            return (Criteria) this;
        }

        public Criteria andSubmeridLikeInsensitive(String value) {
            addCriterion("upper(submerid) like", value.toUpperCase(), "submerid");
            return (Criteria) this;
        }

        public Criteria andSubmernameLikeInsensitive(String value) {
            addCriterion("upper(submername) like", value.toUpperCase(), "submername");
            return (Criteria) this;
        }

        public Criteria andSubmerabbrLikeInsensitive(String value) {
            addCriterion("upper(submerabbr) like", value.toUpperCase(), "submerabbr");
            return (Criteria) this;
        }

        public Criteria andSignednameLikeInsensitive(String value) {
            addCriterion("upper(signedname) like", value.toUpperCase(), "signedname");
            return (Criteria) this;
        }

        public Criteria andIdnoLikeInsensitive(String value) {
            addCriterion("upper(idno) like", value.toUpperCase(), "idno");
            return (Criteria) this;
        }

        public Criteria andMobilenoLikeInsensitive(String value) {
            addCriterion("upper(mobileno) like", value.toUpperCase(), "mobileno");
            return (Criteria) this;
        }

        public Criteria andSelectfinacodeLikeInsensitive(String value) {
            addCriterion("upper(selectfinacode) like", value.toUpperCase(), "selectfinacode");
            return (Criteria) this;
        }

        public Criteria andBanknameLikeInsensitive(String value) {
            addCriterion("upper(bankname) like", value.toUpperCase(), "bankname");
            return (Criteria) this;
        }

        public Criteria andBankaccountnumberLikeInsensitive(String value) {
            addCriterion("upper(bankaccountnumber) like", value.toUpperCase(), "bankaccountnumber");
            return (Criteria) this;
        }

        public Criteria andClientipLikeInsensitive(String value) {
            addCriterion("upper(clientIp) like", value.toUpperCase(), "clientip");
            return (Criteria) this;
        }

        public Criteria andTrannoLikeInsensitive(String value) {
            addCriterion("upper(tranNo) like", value.toUpperCase(), "tranno");
            return (Criteria) this;
        }

        public Criteria andSignstatusLikeInsensitive(String value) {
            addCriterion("upper(signstatus) like", value.toUpperCase(), "signstatus");
            return (Criteria) this;
        }

        public Criteria andSysagreenoLikeInsensitive(String value) {
            addCriterion("upper(sysagreeno) like", value.toUpperCase(), "sysagreeno");
            return (Criteria) this;
        }

        public Criteria andReturnContentLikeInsensitive(String value) {
            addCriterion("upper(return_content) like", value.toUpperCase(), "returnContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentLikeInsensitive(String value) {
            addCriterion("upper(notify_content) like", value.toUpperCase(), "notifyContent");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}