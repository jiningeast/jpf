package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayCloudDfMoneyFreezeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayCloudDfMoneyFreezeExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCompanyidIsNull() {
            addCriterion("companyid is null");
            return (Criteria) this;
        }

        public Criteria andCompanyidIsNotNull() {
            addCriterion("companyid is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyidEqualTo(String value) {
            addCriterion("companyid =", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotEqualTo(String value) {
            addCriterion("companyid <>", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThan(String value) {
            addCriterion("companyid >", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThanOrEqualTo(String value) {
            addCriterion("companyid >=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThan(String value) {
            addCriterion("companyid <", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThanOrEqualTo(String value) {
            addCriterion("companyid <=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidIn(List<String> values) {
            addCriterion("companyid in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotIn(List<String> values) {
            addCriterion("companyid not in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidBetween(String value1, String value2) {
            addCriterion("companyid between", value1, value2, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotBetween(String value1, String value2) {
            addCriterion("companyid not between", value1, value2, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyCloudmoneyIsNull() {
            addCriterion("company_cloudmoney is null");
            return (Criteria) this;
        }

        public Criteria andCompanyCloudmoneyIsNotNull() {
            addCriterion("company_cloudmoney is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyCloudmoneyEqualTo(BigDecimal value) {
            addCriterion("company_cloudmoney =", value, "companyCloudmoney");
            return (Criteria) this;
        }

        public Criteria andCompanyCloudmoneyNotEqualTo(BigDecimal value) {
            addCriterion("company_cloudmoney <>", value, "companyCloudmoney");
            return (Criteria) this;
        }

        public Criteria andCompanyCloudmoneyGreaterThan(BigDecimal value) {
            addCriterion("company_cloudmoney >", value, "companyCloudmoney");
            return (Criteria) this;
        }

        public Criteria andCompanyCloudmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("company_cloudmoney >=", value, "companyCloudmoney");
            return (Criteria) this;
        }

        public Criteria andCompanyCloudmoneyLessThan(BigDecimal value) {
            addCriterion("company_cloudmoney <", value, "companyCloudmoney");
            return (Criteria) this;
        }

        public Criteria andCompanyCloudmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("company_cloudmoney <=", value, "companyCloudmoney");
            return (Criteria) this;
        }

        public Criteria andCompanyCloudmoneyIn(List<BigDecimal> values) {
            addCriterion("company_cloudmoney in", values, "companyCloudmoney");
            return (Criteria) this;
        }

        public Criteria andCompanyCloudmoneyNotIn(List<BigDecimal> values) {
            addCriterion("company_cloudmoney not in", values, "companyCloudmoney");
            return (Criteria) this;
        }

        public Criteria andCompanyCloudmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("company_cloudmoney between", value1, value2, "companyCloudmoney");
            return (Criteria) this;
        }

        public Criteria andCompanyCloudmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("company_cloudmoney not between", value1, value2, "companyCloudmoney");
            return (Criteria) this;
        }

        public Criteria andCompanyAdvanceMoneyIsNull() {
            addCriterion("company_advance_money is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAdvanceMoneyIsNotNull() {
            addCriterion("company_advance_money is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAdvanceMoneyEqualTo(BigDecimal value) {
            addCriterion("company_advance_money =", value, "companyAdvanceMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyAdvanceMoneyNotEqualTo(BigDecimal value) {
            addCriterion("company_advance_money <>", value, "companyAdvanceMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyAdvanceMoneyGreaterThan(BigDecimal value) {
            addCriterion("company_advance_money >", value, "companyAdvanceMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyAdvanceMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("company_advance_money >=", value, "companyAdvanceMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyAdvanceMoneyLessThan(BigDecimal value) {
            addCriterion("company_advance_money <", value, "companyAdvanceMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyAdvanceMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("company_advance_money <=", value, "companyAdvanceMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyAdvanceMoneyIn(List<BigDecimal> values) {
            addCriterion("company_advance_money in", values, "companyAdvanceMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyAdvanceMoneyNotIn(List<BigDecimal> values) {
            addCriterion("company_advance_money not in", values, "companyAdvanceMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyAdvanceMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("company_advance_money between", value1, value2, "companyAdvanceMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyAdvanceMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("company_advance_money not between", value1, value2, "companyAdvanceMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyFreezeMoneyIsNull() {
            addCriterion("company_freeze_money is null");
            return (Criteria) this;
        }

        public Criteria andCompanyFreezeMoneyIsNotNull() {
            addCriterion("company_freeze_money is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyFreezeMoneyEqualTo(BigDecimal value) {
            addCriterion("company_freeze_money =", value, "companyFreezeMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyFreezeMoneyNotEqualTo(BigDecimal value) {
            addCriterion("company_freeze_money <>", value, "companyFreezeMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyFreezeMoneyGreaterThan(BigDecimal value) {
            addCriterion("company_freeze_money >", value, "companyFreezeMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyFreezeMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("company_freeze_money >=", value, "companyFreezeMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyFreezeMoneyLessThan(BigDecimal value) {
            addCriterion("company_freeze_money <", value, "companyFreezeMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyFreezeMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("company_freeze_money <=", value, "companyFreezeMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyFreezeMoneyIn(List<BigDecimal> values) {
            addCriterion("company_freeze_money in", values, "companyFreezeMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyFreezeMoneyNotIn(List<BigDecimal> values) {
            addCriterion("company_freeze_money not in", values, "companyFreezeMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyFreezeMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("company_freeze_money between", value1, value2, "companyFreezeMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyFreezeMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("company_freeze_money not between", value1, value2, "companyFreezeMoney");
            return (Criteria) this;
        }

        public Criteria andCompanyMoneyIdIsNull() {
            addCriterion("company_money_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyMoneyIdIsNotNull() {
            addCriterion("company_money_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyMoneyIdEqualTo(String value) {
            addCriterion("company_money_id =", value, "companyMoneyId");
            return (Criteria) this;
        }

        public Criteria andCompanyMoneyIdNotEqualTo(String value) {
            addCriterion("company_money_id <>", value, "companyMoneyId");
            return (Criteria) this;
        }

        public Criteria andCompanyMoneyIdGreaterThan(String value) {
            addCriterion("company_money_id >", value, "companyMoneyId");
            return (Criteria) this;
        }

        public Criteria andCompanyMoneyIdGreaterThanOrEqualTo(String value) {
            addCriterion("company_money_id >=", value, "companyMoneyId");
            return (Criteria) this;
        }

        public Criteria andCompanyMoneyIdLessThan(String value) {
            addCriterion("company_money_id <", value, "companyMoneyId");
            return (Criteria) this;
        }

        public Criteria andCompanyMoneyIdLessThanOrEqualTo(String value) {
            addCriterion("company_money_id <=", value, "companyMoneyId");
            return (Criteria) this;
        }

        public Criteria andCompanyMoneyIdIn(List<String> values) {
            addCriterion("company_money_id in", values, "companyMoneyId");
            return (Criteria) this;
        }

        public Criteria andCompanyMoneyIdNotIn(List<String> values) {
            addCriterion("company_money_id not in", values, "companyMoneyId");
            return (Criteria) this;
        }

        public Criteria andCompanyMoneyIdBetween(String value1, String value2) {
            addCriterion("company_money_id between", value1, value2, "companyMoneyId");
            return (Criteria) this;
        }

        public Criteria andCompanyMoneyIdNotBetween(String value1, String value2) {
            addCriterion("company_money_id not between", value1, value2, "companyMoneyId");
            return (Criteria) this;
        }

        public Criteria andDfMoneyIdIsNull() {
            addCriterion("df_money_id is null");
            return (Criteria) this;
        }

        public Criteria andDfMoneyIdIsNotNull() {
            addCriterion("df_money_id is not null");
            return (Criteria) this;
        }

        public Criteria andDfMoneyIdEqualTo(String value) {
            addCriterion("df_money_id =", value, "dfMoneyId");
            return (Criteria) this;
        }

        public Criteria andDfMoneyIdNotEqualTo(String value) {
            addCriterion("df_money_id <>", value, "dfMoneyId");
            return (Criteria) this;
        }

        public Criteria andDfMoneyIdGreaterThan(String value) {
            addCriterion("df_money_id >", value, "dfMoneyId");
            return (Criteria) this;
        }

        public Criteria andDfMoneyIdGreaterThanOrEqualTo(String value) {
            addCriterion("df_money_id >=", value, "dfMoneyId");
            return (Criteria) this;
        }

        public Criteria andDfMoneyIdLessThan(String value) {
            addCriterion("df_money_id <", value, "dfMoneyId");
            return (Criteria) this;
        }

        public Criteria andDfMoneyIdLessThanOrEqualTo(String value) {
            addCriterion("df_money_id <=", value, "dfMoneyId");
            return (Criteria) this;
        }

        public Criteria andDfMoneyIdIn(List<String> values) {
            addCriterion("df_money_id in", values, "dfMoneyId");
            return (Criteria) this;
        }

        public Criteria andDfMoneyIdNotIn(List<String> values) {
            addCriterion("df_money_id not in", values, "dfMoneyId");
            return (Criteria) this;
        }

        public Criteria andDfMoneyIdBetween(String value1, String value2) {
            addCriterion("df_money_id between", value1, value2, "dfMoneyId");
            return (Criteria) this;
        }

        public Criteria andDfMoneyIdNotBetween(String value1, String value2) {
            addCriterion("df_money_id not between", value1, value2, "dfMoneyId");
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

        public Criteria andOrderidLike(String value) {
            addCriterion("orderid like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotLike(String value) {
            addCriterion("orderid not like", value, "orderid");
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

        public Criteria andFreezeMoneyIsNull() {
            addCriterion("freeze_money is null");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyIsNotNull() {
            addCriterion("freeze_money is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyEqualTo(BigDecimal value) {
            addCriterion("freeze_money =", value, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyNotEqualTo(BigDecimal value) {
            addCriterion("freeze_money <>", value, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyGreaterThan(BigDecimal value) {
            addCriterion("freeze_money >", value, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_money >=", value, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyLessThan(BigDecimal value) {
            addCriterion("freeze_money <", value, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_money <=", value, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyIn(List<BigDecimal> values) {
            addCriterion("freeze_money in", values, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyNotIn(List<BigDecimal> values) {
            addCriterion("freeze_money not in", values, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_money between", value1, value2, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andFreezeMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_money not between", value1, value2, "freezeMoney");
            return (Criteria) this;
        }

        public Criteria andInfoIsNull() {
            addCriterion("info is null");
            return (Criteria) this;
        }

        public Criteria andInfoIsNotNull() {
            addCriterion("info is not null");
            return (Criteria) this;
        }

        public Criteria andInfoEqualTo(String value) {
            addCriterion("info =", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotEqualTo(String value) {
            addCriterion("info <>", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThan(String value) {
            addCriterion("info >", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoGreaterThanOrEqualTo(String value) {
            addCriterion("info >=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThan(String value) {
            addCriterion("info <", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLessThanOrEqualTo(String value) {
            addCriterion("info <=", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoLike(String value) {
            addCriterion("info like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotLike(String value) {
            addCriterion("info not like", value, "info");
            return (Criteria) this;
        }

        public Criteria andInfoIn(List<String> values) {
            addCriterion("info in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotIn(List<String> values) {
            addCriterion("info not in", values, "info");
            return (Criteria) this;
        }

        public Criteria andInfoBetween(String value1, String value2) {
            addCriterion("info between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andInfoNotBetween(String value1, String value2) {
            addCriterion("info not between", value1, value2, "info");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andMoneyStatusIsNull() {
            addCriterion("money_status is null");
            return (Criteria) this;
        }

        public Criteria andMoneyStatusIsNotNull() {
            addCriterion("money_status is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyStatusEqualTo(Integer value) {
            addCriterion("money_status =", value, "moneyStatus");
            return (Criteria) this;
        }

        public Criteria andMoneyStatusNotEqualTo(Integer value) {
            addCriterion("money_status <>", value, "moneyStatus");
            return (Criteria) this;
        }

        public Criteria andMoneyStatusGreaterThan(Integer value) {
            addCriterion("money_status >", value, "moneyStatus");
            return (Criteria) this;
        }

        public Criteria andMoneyStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_status >=", value, "moneyStatus");
            return (Criteria) this;
        }

        public Criteria andMoneyStatusLessThan(Integer value) {
            addCriterion("money_status <", value, "moneyStatus");
            return (Criteria) this;
        }

        public Criteria andMoneyStatusLessThanOrEqualTo(Integer value) {
            addCriterion("money_status <=", value, "moneyStatus");
            return (Criteria) this;
        }

        public Criteria andMoneyStatusIn(List<Integer> values) {
            addCriterion("money_status in", values, "moneyStatus");
            return (Criteria) this;
        }

        public Criteria andMoneyStatusNotIn(List<Integer> values) {
            addCriterion("money_status not in", values, "moneyStatus");
            return (Criteria) this;
        }

        public Criteria andMoneyStatusBetween(Integer value1, Integer value2) {
            addCriterion("money_status between", value1, value2, "moneyStatus");
            return (Criteria) this;
        }

        public Criteria andMoneyStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("money_status not between", value1, value2, "moneyStatus");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("addtime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("addtime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Date value) {
            addCriterion("addtime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("addtime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("addtime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("addtime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Date value) {
            addCriterion("addtime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("addtime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Date> values) {
            addCriterion("addtime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("addtime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("addtime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("addtime not between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNull() {
            addCriterion("operator_id is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIsNotNull() {
            addCriterion("operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorIdEqualTo(Integer value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(Integer value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(Integer value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(Integer value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(Integer value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<Integer> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<Integer> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(Integer value1, Integer value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("operator_id not between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNull() {
            addCriterion("operator_name is null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIsNotNull() {
            addCriterion("operator_name is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorNameEqualTo(String value) {
            addCriterion("operator_name =", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotEqualTo(String value) {
            addCriterion("operator_name <>", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThan(String value) {
            addCriterion("operator_name >", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("operator_name >=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThan(String value) {
            addCriterion("operator_name <", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLessThanOrEqualTo(String value) {
            addCriterion("operator_name <=", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLike(String value) {
            addCriterion("operator_name like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotLike(String value) {
            addCriterion("operator_name not like", value, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameIn(List<String> values) {
            addCriterion("operator_name in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotIn(List<String> values) {
            addCriterion("operator_name not in", values, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameBetween(String value1, String value2) {
            addCriterion("operator_name between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameNotBetween(String value1, String value2) {
            addCriterion("operator_name not between", value1, value2, "operatorName");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeIsNull() {
            addCriterion("operator_time is null");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeIsNotNull() {
            addCriterion("operator_time is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeEqualTo(Date value) {
            addCriterion("operator_time =", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeNotEqualTo(Date value) {
            addCriterion("operator_time <>", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeGreaterThan(Date value) {
            addCriterion("operator_time >", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operator_time >=", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeLessThan(Date value) {
            addCriterion("operator_time <", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeLessThanOrEqualTo(Date value) {
            addCriterion("operator_time <=", value, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeIn(List<Date> values) {
            addCriterion("operator_time in", values, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeNotIn(List<Date> values) {
            addCriterion("operator_time not in", values, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeBetween(Date value1, Date value2) {
            addCriterion("operator_time between", value1, value2, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andOperatorTimeNotBetween(Date value1, Date value2) {
            addCriterion("operator_time not between", value1, value2, "operatorTime");
            return (Criteria) this;
        }

        public Criteria andFinanceIdIsNull() {
            addCriterion("finance_id is null");
            return (Criteria) this;
        }

        public Criteria andFinanceIdIsNotNull() {
            addCriterion("finance_id is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceIdEqualTo(Integer value) {
            addCriterion("finance_id =", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdNotEqualTo(Integer value) {
            addCriterion("finance_id <>", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdGreaterThan(Integer value) {
            addCriterion("finance_id >", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("finance_id >=", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdLessThan(Integer value) {
            addCriterion("finance_id <", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdLessThanOrEqualTo(Integer value) {
            addCriterion("finance_id <=", value, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdIn(List<Integer> values) {
            addCriterion("finance_id in", values, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdNotIn(List<Integer> values) {
            addCriterion("finance_id not in", values, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdBetween(Integer value1, Integer value2) {
            addCriterion("finance_id between", value1, value2, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("finance_id not between", value1, value2, "financeId");
            return (Criteria) this;
        }

        public Criteria andFinanceNameIsNull() {
            addCriterion("finance_name is null");
            return (Criteria) this;
        }

        public Criteria andFinanceNameIsNotNull() {
            addCriterion("finance_name is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceNameEqualTo(String value) {
            addCriterion("finance_name =", value, "financeName");
            return (Criteria) this;
        }

        public Criteria andFinanceNameNotEqualTo(String value) {
            addCriterion("finance_name <>", value, "financeName");
            return (Criteria) this;
        }

        public Criteria andFinanceNameGreaterThan(String value) {
            addCriterion("finance_name >", value, "financeName");
            return (Criteria) this;
        }

        public Criteria andFinanceNameGreaterThanOrEqualTo(String value) {
            addCriterion("finance_name >=", value, "financeName");
            return (Criteria) this;
        }

        public Criteria andFinanceNameLessThan(String value) {
            addCriterion("finance_name <", value, "financeName");
            return (Criteria) this;
        }

        public Criteria andFinanceNameLessThanOrEqualTo(String value) {
            addCriterion("finance_name <=", value, "financeName");
            return (Criteria) this;
        }

        public Criteria andFinanceNameLike(String value) {
            addCriterion("finance_name like", value, "financeName");
            return (Criteria) this;
        }

        public Criteria andFinanceNameNotLike(String value) {
            addCriterion("finance_name not like", value, "financeName");
            return (Criteria) this;
        }

        public Criteria andFinanceNameIn(List<String> values) {
            addCriterion("finance_name in", values, "financeName");
            return (Criteria) this;
        }

        public Criteria andFinanceNameNotIn(List<String> values) {
            addCriterion("finance_name not in", values, "financeName");
            return (Criteria) this;
        }

        public Criteria andFinanceNameBetween(String value1, String value2) {
            addCriterion("finance_name between", value1, value2, "financeName");
            return (Criteria) this;
        }

        public Criteria andFinanceNameNotBetween(String value1, String value2) {
            addCriterion("finance_name not between", value1, value2, "financeName");
            return (Criteria) this;
        }

        public Criteria andFinanceTimeIsNull() {
            addCriterion("finance_time is null");
            return (Criteria) this;
        }

        public Criteria andFinanceTimeIsNotNull() {
            addCriterion("finance_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceTimeEqualTo(Date value) {
            addCriterion("finance_time =", value, "financeTime");
            return (Criteria) this;
        }

        public Criteria andFinanceTimeNotEqualTo(Date value) {
            addCriterion("finance_time <>", value, "financeTime");
            return (Criteria) this;
        }

        public Criteria andFinanceTimeGreaterThan(Date value) {
            addCriterion("finance_time >", value, "financeTime");
            return (Criteria) this;
        }

        public Criteria andFinanceTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finance_time >=", value, "financeTime");
            return (Criteria) this;
        }

        public Criteria andFinanceTimeLessThan(Date value) {
            addCriterion("finance_time <", value, "financeTime");
            return (Criteria) this;
        }

        public Criteria andFinanceTimeLessThanOrEqualTo(Date value) {
            addCriterion("finance_time <=", value, "financeTime");
            return (Criteria) this;
        }

        public Criteria andFinanceTimeIn(List<Date> values) {
            addCriterion("finance_time in", values, "financeTime");
            return (Criteria) this;
        }

        public Criteria andFinanceTimeNotIn(List<Date> values) {
            addCriterion("finance_time not in", values, "financeTime");
            return (Criteria) this;
        }

        public Criteria andFinanceTimeBetween(Date value1, Date value2) {
            addCriterion("finance_time between", value1, value2, "financeTime");
            return (Criteria) this;
        }

        public Criteria andFinanceTimeNotBetween(Date value1, Date value2) {
            addCriterion("finance_time not between", value1, value2, "financeTime");
            return (Criteria) this;
        }

        public Criteria andOrderidLikeInsensitive(String value) {
            addCriterion("upper(orderid) like", value.toUpperCase(), "orderid");
            return (Criteria) this;
        }

        public Criteria andInfoLikeInsensitive(String value) {
            addCriterion("upper(info) like", value.toUpperCase(), "info");
            return (Criteria) this;
        }

        public Criteria andRemarksLikeInsensitive(String value) {
            addCriterion("upper(remarks) like", value.toUpperCase(), "remarks");
            return (Criteria) this;
        }

        public Criteria andReturnContentLikeInsensitive(String value) {
            addCriterion("upper(return_content) like", value.toUpperCase(), "returnContent");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLikeInsensitive(String value) {
            addCriterion("upper(operator_name) like", value.toUpperCase(), "operatorName");
            return (Criteria) this;
        }

        public Criteria andFinanceNameLikeInsensitive(String value) {
            addCriterion("upper(finance_name) like", value.toUpperCase(), "financeName");
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