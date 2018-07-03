package com.joiest.jpf.common.po;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PayCloudStaffBanksExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayCloudStaffBanksExample() {
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

        public Criteria andIdEqualTo(BigInteger value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(BigInteger value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(BigInteger value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(BigInteger value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(BigInteger value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(BigInteger value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<BigInteger> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<BigInteger> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(BigInteger value1, BigInteger value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(BigInteger value1, BigInteger value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStaffidIsNull() {
            addCriterion("staffid is null");
            return (Criteria) this;
        }

        public Criteria andStaffidIsNotNull() {
            addCriterion("staffid is not null");
            return (Criteria) this;
        }

        public Criteria andStaffidEqualTo(Long value) {
            addCriterion("staffid =", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidNotEqualTo(Long value) {
            addCriterion("staffid <>", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidGreaterThan(Long value) {
            addCriterion("staffid >", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidGreaterThanOrEqualTo(Long value) {
            addCriterion("staffid >=", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidLessThan(Long value) {
            addCriterion("staffid <", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidLessThanOrEqualTo(Long value) {
            addCriterion("staffid <=", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidIn(List<Long> values) {
            addCriterion("staffid in", values, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidNotIn(List<Long> values) {
            addCriterion("staffid not in", values, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidBetween(Long value1, Long value2) {
            addCriterion("staffid between", value1, value2, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidNotBetween(Long value1, Long value2) {
            addCriterion("staffid not between", value1, value2, "staffid");
            return (Criteria) this;
        }

        public Criteria andBankidIsNull() {
            addCriterion("bankid is null");
            return (Criteria) this;
        }

        public Criteria andBankidIsNotNull() {
            addCriterion("bankid is not null");
            return (Criteria) this;
        }

        public Criteria andBankidEqualTo(Long value) {
            addCriterion("bankid =", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidNotEqualTo(Long value) {
            addCriterion("bankid <>", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidGreaterThan(Long value) {
            addCriterion("bankid >", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidGreaterThanOrEqualTo(Long value) {
            addCriterion("bankid >=", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidLessThan(Long value) {
            addCriterion("bankid <", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidLessThanOrEqualTo(Long value) {
            addCriterion("bankid <=", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidIn(List<Long> values) {
            addCriterion("bankid in", values, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidNotIn(List<Long> values) {
            addCriterion("bankid not in", values, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidBetween(Long value1, Long value2) {
            addCriterion("bankid between", value1, value2, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidNotBetween(Long value1, Long value2) {
            addCriterion("bankid not between", value1, value2, "bankid");
            return (Criteria) this;
        }

        public Criteria andBanknoIsNull() {
            addCriterion("bankno is null");
            return (Criteria) this;
        }

        public Criteria andBanknoIsNotNull() {
            addCriterion("bankno is not null");
            return (Criteria) this;
        }

        public Criteria andBanknoEqualTo(String value) {
            addCriterion("bankno =", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoNotEqualTo(String value) {
            addCriterion("bankno <>", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoGreaterThan(String value) {
            addCriterion("bankno >", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoGreaterThanOrEqualTo(String value) {
            addCriterion("bankno >=", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoLessThan(String value) {
            addCriterion("bankno <", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoLessThanOrEqualTo(String value) {
            addCriterion("bankno <=", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoLike(String value) {
            addCriterion("bankno like", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoNotLike(String value) {
            addCriterion("bankno not like", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoIn(List<String> values) {
            addCriterion("bankno in", values, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoNotIn(List<String> values) {
            addCriterion("bankno not in", values, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoBetween(String value1, String value2) {
            addCriterion("bankno between", value1, value2, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoNotBetween(String value1, String value2) {
            addCriterion("bankno not between", value1, value2, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknicknameIsNull() {
            addCriterion("banknickname is null");
            return (Criteria) this;
        }

        public Criteria andBanknicknameIsNotNull() {
            addCriterion("banknickname is not null");
            return (Criteria) this;
        }

        public Criteria andBanknicknameEqualTo(String value) {
            addCriterion("banknickname =", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameNotEqualTo(String value) {
            addCriterion("banknickname <>", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameGreaterThan(String value) {
            addCriterion("banknickname >", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameGreaterThanOrEqualTo(String value) {
            addCriterion("banknickname >=", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameLessThan(String value) {
            addCriterion("banknickname <", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameLessThanOrEqualTo(String value) {
            addCriterion("banknickname <=", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameLike(String value) {
            addCriterion("banknickname like", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameNotLike(String value) {
            addCriterion("banknickname not like", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameIn(List<String> values) {
            addCriterion("banknickname in", values, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameNotIn(List<String> values) {
            addCriterion("banknickname not in", values, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameBetween(String value1, String value2) {
            addCriterion("banknickname between", value1, value2, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameNotBetween(String value1, String value2) {
            addCriterion("banknickname not between", value1, value2, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBankphoneIsNull() {
            addCriterion("bankphone is null");
            return (Criteria) this;
        }

        public Criteria andBankphoneIsNotNull() {
            addCriterion("bankphone is not null");
            return (Criteria) this;
        }

        public Criteria andBankphoneEqualTo(String value) {
            addCriterion("bankphone =", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneNotEqualTo(String value) {
            addCriterion("bankphone <>", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneGreaterThan(String value) {
            addCriterion("bankphone >", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneGreaterThanOrEqualTo(String value) {
            addCriterion("bankphone >=", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneLessThan(String value) {
            addCriterion("bankphone <", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneLessThanOrEqualTo(String value) {
            addCriterion("bankphone <=", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneLike(String value) {
            addCriterion("bankphone like", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneNotLike(String value) {
            addCriterion("bankphone not like", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneIn(List<String> values) {
            addCriterion("bankphone in", values, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneNotIn(List<String> values) {
            addCriterion("bankphone not in", values, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneBetween(String value1, String value2) {
            addCriterion("bankphone between", value1, value2, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneNotBetween(String value1, String value2) {
            addCriterion("bankphone not between", value1, value2, "bankphone");
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

        public Criteria andBankprovinceIsNull() {
            addCriterion("bankprovince is null");
            return (Criteria) this;
        }

        public Criteria andBankprovinceIsNotNull() {
            addCriterion("bankprovince is not null");
            return (Criteria) this;
        }

        public Criteria andBankprovinceEqualTo(String value) {
            addCriterion("bankprovince =", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotEqualTo(String value) {
            addCriterion("bankprovince <>", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceGreaterThan(String value) {
            addCriterion("bankprovince >", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceGreaterThanOrEqualTo(String value) {
            addCriterion("bankprovince >=", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLessThan(String value) {
            addCriterion("bankprovince <", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLessThanOrEqualTo(String value) {
            addCriterion("bankprovince <=", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLike(String value) {
            addCriterion("bankprovince like", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotLike(String value) {
            addCriterion("bankprovince not like", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceIn(List<String> values) {
            addCriterion("bankprovince in", values, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotIn(List<String> values) {
            addCriterion("bankprovince not in", values, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceBetween(String value1, String value2) {
            addCriterion("bankprovince between", value1, value2, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotBetween(String value1, String value2) {
            addCriterion("bankprovince not between", value1, value2, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankcityIsNull() {
            addCriterion("bankcity is null");
            return (Criteria) this;
        }

        public Criteria andBankcityIsNotNull() {
            addCriterion("bankcity is not null");
            return (Criteria) this;
        }

        public Criteria andBankcityEqualTo(String value) {
            addCriterion("bankcity =", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotEqualTo(String value) {
            addCriterion("bankcity <>", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityGreaterThan(String value) {
            addCriterion("bankcity >", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityGreaterThanOrEqualTo(String value) {
            addCriterion("bankcity >=", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityLessThan(String value) {
            addCriterion("bankcity <", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityLessThanOrEqualTo(String value) {
            addCriterion("bankcity <=", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityLike(String value) {
            addCriterion("bankcity like", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotLike(String value) {
            addCriterion("bankcity not like", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityIn(List<String> values) {
            addCriterion("bankcity in", values, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotIn(List<String> values) {
            addCriterion("bankcity not in", values, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityBetween(String value1, String value2) {
            addCriterion("bankcity between", value1, value2, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotBetween(String value1, String value2) {
            addCriterion("bankcity not between", value1, value2, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankActiveIsNull() {
            addCriterion("bank_active is null");
            return (Criteria) this;
        }

        public Criteria andBankActiveIsNotNull() {
            addCriterion("bank_active is not null");
            return (Criteria) this;
        }

        public Criteria andBankActiveEqualTo(String value) {
            addCriterion("bank_active =", value, "bankActive");
            return (Criteria) this;
        }

        public Criteria andBankActiveNotEqualTo(String value) {
            addCriterion("bank_active <>", value, "bankActive");
            return (Criteria) this;
        }

        public Criteria andBankActiveGreaterThan(String value) {
            addCriterion("bank_active >", value, "bankActive");
            return (Criteria) this;
        }

        public Criteria andBankActiveGreaterThanOrEqualTo(String value) {
            addCriterion("bank_active >=", value, "bankActive");
            return (Criteria) this;
        }

        public Criteria andBankActiveLessThan(String value) {
            addCriterion("bank_active <", value, "bankActive");
            return (Criteria) this;
        }

        public Criteria andBankActiveLessThanOrEqualTo(String value) {
            addCriterion("bank_active <=", value, "bankActive");
            return (Criteria) this;
        }

        public Criteria andBankActiveIn(List<String> values) {
            addCriterion("bank_active in", values, "bankActive");
            return (Criteria) this;
        }

        public Criteria andBankActiveNotIn(List<String> values) {
            addCriterion("bank_active not in", values, "bankActive");
            return (Criteria) this;
        }

        public Criteria andBankActiveBetween(String value1, String value2) {
            addCriterion("bank_active between", value1, value2, "bankActive");
            return (Criteria) this;
        }

        public Criteria andBankActiveNotBetween(String value1, String value2) {
            addCriterion("bank_active not between", value1, value2, "bankActive");
            return (Criteria) this;
        }

        public Criteria andBankacctattrIsNull() {
            addCriterion("bankAcctAttr is null");
            return (Criteria) this;
        }

        public Criteria andBankacctattrIsNotNull() {
            addCriterion("bankAcctAttr is not null");
            return (Criteria) this;
        }

        public Criteria andBankacctattrEqualTo(String value) {
            addCriterion("bankAcctAttr =", value, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrNotEqualTo(String value) {
            addCriterion("bankAcctAttr <>", value, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrGreaterThan(String value) {
            addCriterion("bankAcctAttr >", value, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrGreaterThanOrEqualTo(String value) {
            addCriterion("bankAcctAttr >=", value, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrLessThan(String value) {
            addCriterion("bankAcctAttr <", value, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrLessThanOrEqualTo(String value) {
            addCriterion("bankAcctAttr <=", value, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrIn(List<String> values) {
            addCriterion("bankAcctAttr in", values, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrNotIn(List<String> values) {
            addCriterion("bankAcctAttr not in", values, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrBetween(String value1, String value2) {
            addCriterion("bankAcctAttr between", value1, value2, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrNotBetween(String value1, String value2) {
            addCriterion("bankAcctAttr not between", value1, value2, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBanknoLikeInsensitive(String value) {
            addCriterion("upper(bankno) like", value.toUpperCase(), "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknicknameLikeInsensitive(String value) {
            addCriterion("upper(banknickname) like", value.toUpperCase(), "banknickname");
            return (Criteria) this;
        }

        public Criteria andBankphoneLikeInsensitive(String value) {
            addCriterion("upper(bankphone) like", value.toUpperCase(), "bankphone");
            return (Criteria) this;
        }

        public Criteria andBanknameLikeInsensitive(String value) {
            addCriterion("upper(bankname) like", value.toUpperCase(), "bankname");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLikeInsensitive(String value) {
            addCriterion("upper(bankprovince) like", value.toUpperCase(), "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankcityLikeInsensitive(String value) {
            addCriterion("upper(bankcity) like", value.toUpperCase(), "bankcity");
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