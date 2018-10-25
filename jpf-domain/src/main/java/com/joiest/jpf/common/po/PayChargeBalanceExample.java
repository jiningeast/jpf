package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayChargeBalanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayChargeBalanceExample() {
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

        public Criteria andWnBalanceIsNull() {
            addCriterion("wn_balance is null");
            return (Criteria) this;
        }

        public Criteria andWnBalanceIsNotNull() {
            addCriterion("wn_balance is not null");
            return (Criteria) this;
        }

        public Criteria andWnBalanceEqualTo(BigDecimal value) {
            addCriterion("wn_balance =", value, "wnBalance");
            return (Criteria) this;
        }

        public Criteria andWnBalanceNotEqualTo(BigDecimal value) {
            addCriterion("wn_balance <>", value, "wnBalance");
            return (Criteria) this;
        }

        public Criteria andWnBalanceGreaterThan(BigDecimal value) {
            addCriterion("wn_balance >", value, "wnBalance");
            return (Criteria) this;
        }

        public Criteria andWnBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wn_balance >=", value, "wnBalance");
            return (Criteria) this;
        }

        public Criteria andWnBalanceLessThan(BigDecimal value) {
            addCriterion("wn_balance <", value, "wnBalance");
            return (Criteria) this;
        }

        public Criteria andWnBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wn_balance <=", value, "wnBalance");
            return (Criteria) this;
        }

        public Criteria andWnBalanceIn(List<BigDecimal> values) {
            addCriterion("wn_balance in", values, "wnBalance");
            return (Criteria) this;
        }

        public Criteria andWnBalanceNotIn(List<BigDecimal> values) {
            addCriterion("wn_balance not in", values, "wnBalance");
            return (Criteria) this;
        }

        public Criteria andWnBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wn_balance between", value1, value2, "wnBalance");
            return (Criteria) this;
        }

        public Criteria andWnBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wn_balance not between", value1, value2, "wnBalance");
            return (Criteria) this;
        }

        public Criteria andWnAlertLimitIsNull() {
            addCriterion("wn_alert_limit is null");
            return (Criteria) this;
        }

        public Criteria andWnAlertLimitIsNotNull() {
            addCriterion("wn_alert_limit is not null");
            return (Criteria) this;
        }

        public Criteria andWnAlertLimitEqualTo(BigDecimal value) {
            addCriterion("wn_alert_limit =", value, "wnAlertLimit");
            return (Criteria) this;
        }

        public Criteria andWnAlertLimitNotEqualTo(BigDecimal value) {
            addCriterion("wn_alert_limit <>", value, "wnAlertLimit");
            return (Criteria) this;
        }

        public Criteria andWnAlertLimitGreaterThan(BigDecimal value) {
            addCriterion("wn_alert_limit >", value, "wnAlertLimit");
            return (Criteria) this;
        }

        public Criteria andWnAlertLimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wn_alert_limit >=", value, "wnAlertLimit");
            return (Criteria) this;
        }

        public Criteria andWnAlertLimitLessThan(BigDecimal value) {
            addCriterion("wn_alert_limit <", value, "wnAlertLimit");
            return (Criteria) this;
        }

        public Criteria andWnAlertLimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wn_alert_limit <=", value, "wnAlertLimit");
            return (Criteria) this;
        }

        public Criteria andWnAlertLimitIn(List<BigDecimal> values) {
            addCriterion("wn_alert_limit in", values, "wnAlertLimit");
            return (Criteria) this;
        }

        public Criteria andWnAlertLimitNotIn(List<BigDecimal> values) {
            addCriterion("wn_alert_limit not in", values, "wnAlertLimit");
            return (Criteria) this;
        }

        public Criteria andWnAlertLimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wn_alert_limit between", value1, value2, "wnAlertLimit");
            return (Criteria) this;
        }

        public Criteria andWnAlertLimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wn_alert_limit not between", value1, value2, "wnAlertLimit");
            return (Criteria) this;
        }

        public Criteria andWnAlertSwitchIsNull() {
            addCriterion("wn_alert_switch is null");
            return (Criteria) this;
        }

        public Criteria andWnAlertSwitchIsNotNull() {
            addCriterion("wn_alert_switch is not null");
            return (Criteria) this;
        }

        public Criteria andWnAlertSwitchEqualTo(Byte value) {
            addCriterion("wn_alert_switch =", value, "wnAlertSwitch");
            return (Criteria) this;
        }

        public Criteria andWnAlertSwitchNotEqualTo(Byte value) {
            addCriterion("wn_alert_switch <>", value, "wnAlertSwitch");
            return (Criteria) this;
        }

        public Criteria andWnAlertSwitchGreaterThan(Byte value) {
            addCriterion("wn_alert_switch >", value, "wnAlertSwitch");
            return (Criteria) this;
        }

        public Criteria andWnAlertSwitchGreaterThanOrEqualTo(Byte value) {
            addCriterion("wn_alert_switch >=", value, "wnAlertSwitch");
            return (Criteria) this;
        }

        public Criteria andWnAlertSwitchLessThan(Byte value) {
            addCriterion("wn_alert_switch <", value, "wnAlertSwitch");
            return (Criteria) this;
        }

        public Criteria andWnAlertSwitchLessThanOrEqualTo(Byte value) {
            addCriterion("wn_alert_switch <=", value, "wnAlertSwitch");
            return (Criteria) this;
        }

        public Criteria andWnAlertSwitchIn(List<Byte> values) {
            addCriterion("wn_alert_switch in", values, "wnAlertSwitch");
            return (Criteria) this;
        }

        public Criteria andWnAlertSwitchNotIn(List<Byte> values) {
            addCriterion("wn_alert_switch not in", values, "wnAlertSwitch");
            return (Criteria) this;
        }

        public Criteria andWnAlertSwitchBetween(Byte value1, Byte value2) {
            addCriterion("wn_alert_switch between", value1, value2, "wnAlertSwitch");
            return (Criteria) this;
        }

        public Criteria andWnAlertSwitchNotBetween(Byte value1, Byte value2) {
            addCriterion("wn_alert_switch not between", value1, value2, "wnAlertSwitch");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneIsNull() {
            addCriterion("wn_alert_phone is null");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneIsNotNull() {
            addCriterion("wn_alert_phone is not null");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneEqualTo(String value) {
            addCriterion("wn_alert_phone =", value, "wnAlertPhone");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneNotEqualTo(String value) {
            addCriterion("wn_alert_phone <>", value, "wnAlertPhone");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneGreaterThan(String value) {
            addCriterion("wn_alert_phone >", value, "wnAlertPhone");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("wn_alert_phone >=", value, "wnAlertPhone");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneLessThan(String value) {
            addCriterion("wn_alert_phone <", value, "wnAlertPhone");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneLessThanOrEqualTo(String value) {
            addCriterion("wn_alert_phone <=", value, "wnAlertPhone");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneLike(String value) {
            addCriterion("wn_alert_phone like", value, "wnAlertPhone");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneNotLike(String value) {
            addCriterion("wn_alert_phone not like", value, "wnAlertPhone");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneIn(List<String> values) {
            addCriterion("wn_alert_phone in", values, "wnAlertPhone");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneNotIn(List<String> values) {
            addCriterion("wn_alert_phone not in", values, "wnAlertPhone");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneBetween(String value1, String value2) {
            addCriterion("wn_alert_phone between", value1, value2, "wnAlertPhone");
            return (Criteria) this;
        }

        public Criteria andWnAlertPhoneNotBetween(String value1, String value2) {
            addCriterion("wn_alert_phone not between", value1, value2, "wnAlertPhone");
            return (Criteria) this;
        }

        public Criteria andWnStopLimitIsNull() {
            addCriterion("wn_stop_limit is null");
            return (Criteria) this;
        }

        public Criteria andWnStopLimitIsNotNull() {
            addCriterion("wn_stop_limit is not null");
            return (Criteria) this;
        }

        public Criteria andWnStopLimitEqualTo(BigDecimal value) {
            addCriterion("wn_stop_limit =", value, "wnStopLimit");
            return (Criteria) this;
        }

        public Criteria andWnStopLimitNotEqualTo(BigDecimal value) {
            addCriterion("wn_stop_limit <>", value, "wnStopLimit");
            return (Criteria) this;
        }

        public Criteria andWnStopLimitGreaterThan(BigDecimal value) {
            addCriterion("wn_stop_limit >", value, "wnStopLimit");
            return (Criteria) this;
        }

        public Criteria andWnStopLimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("wn_stop_limit >=", value, "wnStopLimit");
            return (Criteria) this;
        }

        public Criteria andWnStopLimitLessThan(BigDecimal value) {
            addCriterion("wn_stop_limit <", value, "wnStopLimit");
            return (Criteria) this;
        }

        public Criteria andWnStopLimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("wn_stop_limit <=", value, "wnStopLimit");
            return (Criteria) this;
        }

        public Criteria andWnStopLimitIn(List<BigDecimal> values) {
            addCriterion("wn_stop_limit in", values, "wnStopLimit");
            return (Criteria) this;
        }

        public Criteria andWnStopLimitNotIn(List<BigDecimal> values) {
            addCriterion("wn_stop_limit not in", values, "wnStopLimit");
            return (Criteria) this;
        }

        public Criteria andWnStopLimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wn_stop_limit between", value1, value2, "wnStopLimit");
            return (Criteria) this;
        }

        public Criteria andWnStopLimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("wn_stop_limit not between", value1, value2, "wnStopLimit");
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

        public Criteria andWnAlertPhoneLikeInsensitive(String value) {
            addCriterion("upper(wn_alert_phone) like", value.toUpperCase(), "wnAlertPhone");
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