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

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(BigDecimal value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(BigDecimal value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(BigDecimal value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(BigDecimal value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<BigDecimal> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<BigDecimal> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andAlertLimitIsNull() {
            addCriterion("alert_limit is null");
            return (Criteria) this;
        }

        public Criteria andAlertLimitIsNotNull() {
            addCriterion("alert_limit is not null");
            return (Criteria) this;
        }

        public Criteria andAlertLimitEqualTo(BigDecimal value) {
            addCriterion("alert_limit =", value, "alertLimit");
            return (Criteria) this;
        }

        public Criteria andAlertLimitNotEqualTo(BigDecimal value) {
            addCriterion("alert_limit <>", value, "alertLimit");
            return (Criteria) this;
        }

        public Criteria andAlertLimitGreaterThan(BigDecimal value) {
            addCriterion("alert_limit >", value, "alertLimit");
            return (Criteria) this;
        }

        public Criteria andAlertLimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("alert_limit >=", value, "alertLimit");
            return (Criteria) this;
        }

        public Criteria andAlertLimitLessThan(BigDecimal value) {
            addCriterion("alert_limit <", value, "alertLimit");
            return (Criteria) this;
        }

        public Criteria andAlertLimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("alert_limit <=", value, "alertLimit");
            return (Criteria) this;
        }

        public Criteria andAlertLimitIn(List<BigDecimal> values) {
            addCriterion("alert_limit in", values, "alertLimit");
            return (Criteria) this;
        }

        public Criteria andAlertLimitNotIn(List<BigDecimal> values) {
            addCriterion("alert_limit not in", values, "alertLimit");
            return (Criteria) this;
        }

        public Criteria andAlertLimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alert_limit between", value1, value2, "alertLimit");
            return (Criteria) this;
        }

        public Criteria andAlertLimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("alert_limit not between", value1, value2, "alertLimit");
            return (Criteria) this;
        }

        public Criteria andAlertSwitchIsNull() {
            addCriterion("alert_switch is null");
            return (Criteria) this;
        }

        public Criteria andAlertSwitchIsNotNull() {
            addCriterion("alert_switch is not null");
            return (Criteria) this;
        }

        public Criteria andAlertSwitchEqualTo(Byte value) {
            addCriterion("alert_switch =", value, "alertSwitch");
            return (Criteria) this;
        }

        public Criteria andAlertSwitchNotEqualTo(Byte value) {
            addCriterion("alert_switch <>", value, "alertSwitch");
            return (Criteria) this;
        }

        public Criteria andAlertSwitchGreaterThan(Byte value) {
            addCriterion("alert_switch >", value, "alertSwitch");
            return (Criteria) this;
        }

        public Criteria andAlertSwitchGreaterThanOrEqualTo(Byte value) {
            addCriterion("alert_switch >=", value, "alertSwitch");
            return (Criteria) this;
        }

        public Criteria andAlertSwitchLessThan(Byte value) {
            addCriterion("alert_switch <", value, "alertSwitch");
            return (Criteria) this;
        }

        public Criteria andAlertSwitchLessThanOrEqualTo(Byte value) {
            addCriterion("alert_switch <=", value, "alertSwitch");
            return (Criteria) this;
        }

        public Criteria andAlertSwitchIn(List<Byte> values) {
            addCriterion("alert_switch in", values, "alertSwitch");
            return (Criteria) this;
        }

        public Criteria andAlertSwitchNotIn(List<Byte> values) {
            addCriterion("alert_switch not in", values, "alertSwitch");
            return (Criteria) this;
        }

        public Criteria andAlertSwitchBetween(Byte value1, Byte value2) {
            addCriterion("alert_switch between", value1, value2, "alertSwitch");
            return (Criteria) this;
        }

        public Criteria andAlertSwitchNotBetween(Byte value1, Byte value2) {
            addCriterion("alert_switch not between", value1, value2, "alertSwitch");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneIsNull() {
            addCriterion("alert_phone is null");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneIsNotNull() {
            addCriterion("alert_phone is not null");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneEqualTo(String value) {
            addCriterion("alert_phone =", value, "alertPhone");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneNotEqualTo(String value) {
            addCriterion("alert_phone <>", value, "alertPhone");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneGreaterThan(String value) {
            addCriterion("alert_phone >", value, "alertPhone");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("alert_phone >=", value, "alertPhone");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneLessThan(String value) {
            addCriterion("alert_phone <", value, "alertPhone");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneLessThanOrEqualTo(String value) {
            addCriterion("alert_phone <=", value, "alertPhone");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneLike(String value) {
            addCriterion("alert_phone like", value, "alertPhone");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneNotLike(String value) {
            addCriterion("alert_phone not like", value, "alertPhone");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneIn(List<String> values) {
            addCriterion("alert_phone in", values, "alertPhone");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneNotIn(List<String> values) {
            addCriterion("alert_phone not in", values, "alertPhone");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneBetween(String value1, String value2) {
            addCriterion("alert_phone between", value1, value2, "alertPhone");
            return (Criteria) this;
        }

        public Criteria andAlertPhoneNotBetween(String value1, String value2) {
            addCriterion("alert_phone not between", value1, value2, "alertPhone");
            return (Criteria) this;
        }

        public Criteria andStopLimitIsNull() {
            addCriterion("stop_limit is null");
            return (Criteria) this;
        }

        public Criteria andStopLimitIsNotNull() {
            addCriterion("stop_limit is not null");
            return (Criteria) this;
        }

        public Criteria andStopLimitEqualTo(BigDecimal value) {
            addCriterion("stop_limit =", value, "stopLimit");
            return (Criteria) this;
        }

        public Criteria andStopLimitNotEqualTo(BigDecimal value) {
            addCriterion("stop_limit <>", value, "stopLimit");
            return (Criteria) this;
        }

        public Criteria andStopLimitGreaterThan(BigDecimal value) {
            addCriterion("stop_limit >", value, "stopLimit");
            return (Criteria) this;
        }

        public Criteria andStopLimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("stop_limit >=", value, "stopLimit");
            return (Criteria) this;
        }

        public Criteria andStopLimitLessThan(BigDecimal value) {
            addCriterion("stop_limit <", value, "stopLimit");
            return (Criteria) this;
        }

        public Criteria andStopLimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("stop_limit <=", value, "stopLimit");
            return (Criteria) this;
        }

        public Criteria andStopLimitIn(List<BigDecimal> values) {
            addCriterion("stop_limit in", values, "stopLimit");
            return (Criteria) this;
        }

        public Criteria andStopLimitNotIn(List<BigDecimal> values) {
            addCriterion("stop_limit not in", values, "stopLimit");
            return (Criteria) this;
        }

        public Criteria andStopLimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("stop_limit between", value1, value2, "stopLimit");
            return (Criteria) this;
        }

        public Criteria andStopLimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("stop_limit not between", value1, value2, "stopLimit");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andAlertPhoneLikeInsensitive(String value) {
            addCriterion("upper(alert_phone) like", value.toUpperCase(), "alertPhone");
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