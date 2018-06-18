package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayOrdersMoneyDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayOrdersMoneyDetailExample() {
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

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(Long value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(Long value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(Long value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(Long value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(Long value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(Long value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<Long> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<Long> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(Long value1, Long value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(Long value1, Long value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andStageIdIsNull() {
            addCriterion("stage_id is null");
            return (Criteria) this;
        }

        public Criteria andStageIdIsNotNull() {
            addCriterion("stage_id is not null");
            return (Criteria) this;
        }

        public Criteria andStageIdEqualTo(String value) {
            addCriterion("stage_id =", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdNotEqualTo(String value) {
            addCriterion("stage_id <>", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdGreaterThan(String value) {
            addCriterion("stage_id >", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdGreaterThanOrEqualTo(String value) {
            addCriterion("stage_id >=", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdLessThan(String value) {
            addCriterion("stage_id <", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdLessThanOrEqualTo(String value) {
            addCriterion("stage_id <=", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdLike(String value) {
            addCriterion("stage_id like", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdNotLike(String value) {
            addCriterion("stage_id not like", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdIn(List<String> values) {
            addCriterion("stage_id in", values, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdNotIn(List<String> values) {
            addCriterion("stage_id not in", values, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdBetween(String value1, String value2) {
            addCriterion("stage_id between", value1, value2, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdNotBetween(String value1, String value2) {
            addCriterion("stage_id not between", value1, value2, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageNameIsNull() {
            addCriterion("stage_name is null");
            return (Criteria) this;
        }

        public Criteria andStageNameIsNotNull() {
            addCriterion("stage_name is not null");
            return (Criteria) this;
        }

        public Criteria andStageNameEqualTo(String value) {
            addCriterion("stage_name =", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotEqualTo(String value) {
            addCriterion("stage_name <>", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameGreaterThan(String value) {
            addCriterion("stage_name >", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameGreaterThanOrEqualTo(String value) {
            addCriterion("stage_name >=", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameLessThan(String value) {
            addCriterion("stage_name <", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameLessThanOrEqualTo(String value) {
            addCriterion("stage_name <=", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameLike(String value) {
            addCriterion("stage_name like", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotLike(String value) {
            addCriterion("stage_name not like", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameIn(List<String> values) {
            addCriterion("stage_name in", values, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotIn(List<String> values) {
            addCriterion("stage_name not in", values, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameBetween(String value1, String value2) {
            addCriterion("stage_name between", value1, value2, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotBetween(String value1, String value2) {
            addCriterion("stage_name not between", value1, value2, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageRateIsNull() {
            addCriterion("stage_rate is null");
            return (Criteria) this;
        }

        public Criteria andStageRateIsNotNull() {
            addCriterion("stage_rate is not null");
            return (Criteria) this;
        }

        public Criteria andStageRateEqualTo(String value) {
            addCriterion("stage_rate =", value, "stageRate");
            return (Criteria) this;
        }

        public Criteria andStageRateNotEqualTo(String value) {
            addCriterion("stage_rate <>", value, "stageRate");
            return (Criteria) this;
        }

        public Criteria andStageRateGreaterThan(String value) {
            addCriterion("stage_rate >", value, "stageRate");
            return (Criteria) this;
        }

        public Criteria andStageRateGreaterThanOrEqualTo(String value) {
            addCriterion("stage_rate >=", value, "stageRate");
            return (Criteria) this;
        }

        public Criteria andStageRateLessThan(String value) {
            addCriterion("stage_rate <", value, "stageRate");
            return (Criteria) this;
        }

        public Criteria andStageRateLessThanOrEqualTo(String value) {
            addCriterion("stage_rate <=", value, "stageRate");
            return (Criteria) this;
        }

        public Criteria andStageRateLike(String value) {
            addCriterion("stage_rate like", value, "stageRate");
            return (Criteria) this;
        }

        public Criteria andStageRateNotLike(String value) {
            addCriterion("stage_rate not like", value, "stageRate");
            return (Criteria) this;
        }

        public Criteria andStageRateIn(List<String> values) {
            addCriterion("stage_rate in", values, "stageRate");
            return (Criteria) this;
        }

        public Criteria andStageRateNotIn(List<String> values) {
            addCriterion("stage_rate not in", values, "stageRate");
            return (Criteria) this;
        }

        public Criteria andStageRateBetween(String value1, String value2) {
            addCriterion("stage_rate between", value1, value2, "stageRate");
            return (Criteria) this;
        }

        public Criteria andStageRateNotBetween(String value1, String value2) {
            addCriterion("stage_rate not between", value1, value2, "stageRate");
            return (Criteria) this;
        }

        public Criteria andStageMoneyIsNull() {
            addCriterion("stage_money is null");
            return (Criteria) this;
        }

        public Criteria andStageMoneyIsNotNull() {
            addCriterion("stage_money is not null");
            return (Criteria) this;
        }

        public Criteria andStageMoneyEqualTo(BigDecimal value) {
            addCriterion("stage_money =", value, "stageMoney");
            return (Criteria) this;
        }

        public Criteria andStageMoneyNotEqualTo(BigDecimal value) {
            addCriterion("stage_money <>", value, "stageMoney");
            return (Criteria) this;
        }

        public Criteria andStageMoneyGreaterThan(BigDecimal value) {
            addCriterion("stage_money >", value, "stageMoney");
            return (Criteria) this;
        }

        public Criteria andStageMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("stage_money >=", value, "stageMoney");
            return (Criteria) this;
        }

        public Criteria andStageMoneyLessThan(BigDecimal value) {
            addCriterion("stage_money <", value, "stageMoney");
            return (Criteria) this;
        }

        public Criteria andStageMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("stage_money <=", value, "stageMoney");
            return (Criteria) this;
        }

        public Criteria andStageMoneyIn(List<BigDecimal> values) {
            addCriterion("stage_money in", values, "stageMoney");
            return (Criteria) this;
        }

        public Criteria andStageMoneyNotIn(List<BigDecimal> values) {
            addCriterion("stage_money not in", values, "stageMoney");
            return (Criteria) this;
        }

        public Criteria andStageMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("stage_money between", value1, value2, "stageMoney");
            return (Criteria) this;
        }

        public Criteria andStageMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("stage_money not between", value1, value2, "stageMoney");
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

        public Criteria andMerchRateIsNull() {
            addCriterion("merch_rate is null");
            return (Criteria) this;
        }

        public Criteria andMerchRateIsNotNull() {
            addCriterion("merch_rate is not null");
            return (Criteria) this;
        }

        public Criteria andMerchRateEqualTo(String value) {
            addCriterion("merch_rate =", value, "merchRate");
            return (Criteria) this;
        }

        public Criteria andMerchRateNotEqualTo(String value) {
            addCriterion("merch_rate <>", value, "merchRate");
            return (Criteria) this;
        }

        public Criteria andMerchRateGreaterThan(String value) {
            addCriterion("merch_rate >", value, "merchRate");
            return (Criteria) this;
        }

        public Criteria andMerchRateGreaterThanOrEqualTo(String value) {
            addCriterion("merch_rate >=", value, "merchRate");
            return (Criteria) this;
        }

        public Criteria andMerchRateLessThan(String value) {
            addCriterion("merch_rate <", value, "merchRate");
            return (Criteria) this;
        }

        public Criteria andMerchRateLessThanOrEqualTo(String value) {
            addCriterion("merch_rate <=", value, "merchRate");
            return (Criteria) this;
        }

        public Criteria andMerchRateLike(String value) {
            addCriterion("merch_rate like", value, "merchRate");
            return (Criteria) this;
        }

        public Criteria andMerchRateNotLike(String value) {
            addCriterion("merch_rate not like", value, "merchRate");
            return (Criteria) this;
        }

        public Criteria andMerchRateIn(List<String> values) {
            addCriterion("merch_rate in", values, "merchRate");
            return (Criteria) this;
        }

        public Criteria andMerchRateNotIn(List<String> values) {
            addCriterion("merch_rate not in", values, "merchRate");
            return (Criteria) this;
        }

        public Criteria andMerchRateBetween(String value1, String value2) {
            addCriterion("merch_rate between", value1, value2, "merchRate");
            return (Criteria) this;
        }

        public Criteria andMerchRateNotBetween(String value1, String value2) {
            addCriterion("merch_rate not between", value1, value2, "merchRate");
            return (Criteria) this;
        }

        public Criteria andMerchMoneyIsNull() {
            addCriterion("merch_money is null");
            return (Criteria) this;
        }

        public Criteria andMerchMoneyIsNotNull() {
            addCriterion("merch_money is not null");
            return (Criteria) this;
        }

        public Criteria andMerchMoneyEqualTo(BigDecimal value) {
            addCriterion("merch_money =", value, "merchMoney");
            return (Criteria) this;
        }

        public Criteria andMerchMoneyNotEqualTo(BigDecimal value) {
            addCriterion("merch_money <>", value, "merchMoney");
            return (Criteria) this;
        }

        public Criteria andMerchMoneyGreaterThan(BigDecimal value) {
            addCriterion("merch_money >", value, "merchMoney");
            return (Criteria) this;
        }

        public Criteria andMerchMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("merch_money >=", value, "merchMoney");
            return (Criteria) this;
        }

        public Criteria andMerchMoneyLessThan(BigDecimal value) {
            addCriterion("merch_money <", value, "merchMoney");
            return (Criteria) this;
        }

        public Criteria andMerchMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("merch_money <=", value, "merchMoney");
            return (Criteria) this;
        }

        public Criteria andMerchMoneyIn(List<BigDecimal> values) {
            addCriterion("merch_money in", values, "merchMoney");
            return (Criteria) this;
        }

        public Criteria andMerchMoneyNotIn(List<BigDecimal> values) {
            addCriterion("merch_money not in", values, "merchMoney");
            return (Criteria) this;
        }

        public Criteria andMerchMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("merch_money between", value1, value2, "merchMoney");
            return (Criteria) this;
        }

        public Criteria andMerchMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("merch_money not between", value1, value2, "merchMoney");
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

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andStageIdLikeInsensitive(String value) {
            addCriterion("upper(stage_id) like", value.toUpperCase(), "stageId");
            return (Criteria) this;
        }

        public Criteria andStageNameLikeInsensitive(String value) {
            addCriterion("upper(stage_name) like", value.toUpperCase(), "stageName");
            return (Criteria) this;
        }

        public Criteria andStageRateLikeInsensitive(String value) {
            addCriterion("upper(stage_rate) like", value.toUpperCase(), "stageRate");
            return (Criteria) this;
        }

        public Criteria andMerchRateLikeInsensitive(String value) {
            addCriterion("upper(merch_rate) like", value.toUpperCase(), "merchRate");
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