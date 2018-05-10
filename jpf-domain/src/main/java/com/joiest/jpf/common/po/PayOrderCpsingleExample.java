package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayOrderCpsingleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayOrderCpsingleExample() {
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

        public Criteria andTdorderidIsNull() {
            addCriterion("tdorderid is null");
            return (Criteria) this;
        }

        public Criteria andTdorderidIsNotNull() {
            addCriterion("tdorderid is not null");
            return (Criteria) this;
        }

        public Criteria andTdorderidEqualTo(String value) {
            addCriterion("tdorderid =", value, "tdorderid");
            return (Criteria) this;
        }

        public Criteria andTdorderidNotEqualTo(String value) {
            addCriterion("tdorderid <>", value, "tdorderid");
            return (Criteria) this;
        }

        public Criteria andTdorderidGreaterThan(String value) {
            addCriterion("tdorderid >", value, "tdorderid");
            return (Criteria) this;
        }

        public Criteria andTdorderidGreaterThanOrEqualTo(String value) {
            addCriterion("tdorderid >=", value, "tdorderid");
            return (Criteria) this;
        }

        public Criteria andTdorderidLessThan(String value) {
            addCriterion("tdorderid <", value, "tdorderid");
            return (Criteria) this;
        }

        public Criteria andTdorderidLessThanOrEqualTo(String value) {
            addCriterion("tdorderid <=", value, "tdorderid");
            return (Criteria) this;
        }

        public Criteria andTdorderidIn(List<String> values) {
            addCriterion("tdorderid in", values, "tdorderid");
            return (Criteria) this;
        }

        public Criteria andTdorderidNotIn(List<String> values) {
            addCriterion("tdorderid not in", values, "tdorderid");
            return (Criteria) this;
        }

        public Criteria andTdorderidBetween(String value1, String value2) {
            addCriterion("tdorderid between", value1, value2, "tdorderid");
            return (Criteria) this;
        }

        public Criteria andTdorderidNotBetween(String value1, String value2) {
            addCriterion("tdorderid not between", value1, value2, "tdorderid");
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

        public Criteria andTdorderpriceIsNull() {
            addCriterion("tdorderprice is null");
            return (Criteria) this;
        }

        public Criteria andTdorderpriceIsNotNull() {
            addCriterion("tdorderprice is not null");
            return (Criteria) this;
        }

        public Criteria andTdorderpriceEqualTo(BigDecimal value) {
            addCriterion("tdorderprice =", value, "tdorderprice");
            return (Criteria) this;
        }

        public Criteria andTdorderpriceNotEqualTo(BigDecimal value) {
            addCriterion("tdorderprice <>", value, "tdorderprice");
            return (Criteria) this;
        }

        public Criteria andTdorderpriceGreaterThan(BigDecimal value) {
            addCriterion("tdorderprice >", value, "tdorderprice");
            return (Criteria) this;
        }

        public Criteria andTdorderpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tdorderprice >=", value, "tdorderprice");
            return (Criteria) this;
        }

        public Criteria andTdorderpriceLessThan(BigDecimal value) {
            addCriterion("tdorderprice <", value, "tdorderprice");
            return (Criteria) this;
        }

        public Criteria andTdorderpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tdorderprice <=", value, "tdorderprice");
            return (Criteria) this;
        }

        public Criteria andTdorderpriceIn(List<BigDecimal> values) {
            addCriterion("tdorderprice in", values, "tdorderprice");
            return (Criteria) this;
        }

        public Criteria andTdorderpriceNotIn(List<BigDecimal> values) {
            addCriterion("tdorderprice not in", values, "tdorderprice");
            return (Criteria) this;
        }

        public Criteria andTdorderpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tdorderprice between", value1, value2, "tdorderprice");
            return (Criteria) this;
        }

        public Criteria andTdorderpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tdorderprice not between", value1, value2, "tdorderprice");
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

        public Criteria andSingletypeIsNull() {
            addCriterion("singletype is null");
            return (Criteria) this;
        }

        public Criteria andSingletypeIsNotNull() {
            addCriterion("singletype is not null");
            return (Criteria) this;
        }

        public Criteria andSingletypeEqualTo(Byte value) {
            addCriterion("singletype =", value, "singletype");
            return (Criteria) this;
        }

        public Criteria andSingletypeNotEqualTo(Byte value) {
            addCriterion("singletype <>", value, "singletype");
            return (Criteria) this;
        }

        public Criteria andSingletypeGreaterThan(Byte value) {
            addCriterion("singletype >", value, "singletype");
            return (Criteria) this;
        }

        public Criteria andSingletypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("singletype >=", value, "singletype");
            return (Criteria) this;
        }

        public Criteria andSingletypeLessThan(Byte value) {
            addCriterion("singletype <", value, "singletype");
            return (Criteria) this;
        }

        public Criteria andSingletypeLessThanOrEqualTo(Byte value) {
            addCriterion("singletype <=", value, "singletype");
            return (Criteria) this;
        }

        public Criteria andSingletypeIn(List<Byte> values) {
            addCriterion("singletype in", values, "singletype");
            return (Criteria) this;
        }

        public Criteria andSingletypeNotIn(List<Byte> values) {
            addCriterion("singletype not in", values, "singletype");
            return (Criteria) this;
        }

        public Criteria andSingletypeBetween(Byte value1, Byte value2) {
            addCriterion("singletype between", value1, value2, "singletype");
            return (Criteria) this;
        }

        public Criteria andSingletypeNotBetween(Byte value1, Byte value2) {
            addCriterion("singletype not between", value1, value2, "singletype");
            return (Criteria) this;
        }

        public Criteria andSinglestatusIsNull() {
            addCriterion("singlestatus is null");
            return (Criteria) this;
        }

        public Criteria andSinglestatusIsNotNull() {
            addCriterion("singlestatus is not null");
            return (Criteria) this;
        }

        public Criteria andSinglestatusEqualTo(Byte value) {
            addCriterion("singlestatus =", value, "singlestatus");
            return (Criteria) this;
        }

        public Criteria andSinglestatusNotEqualTo(Byte value) {
            addCriterion("singlestatus <>", value, "singlestatus");
            return (Criteria) this;
        }

        public Criteria andSinglestatusGreaterThan(Byte value) {
            addCriterion("singlestatus >", value, "singlestatus");
            return (Criteria) this;
        }

        public Criteria andSinglestatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("singlestatus >=", value, "singlestatus");
            return (Criteria) this;
        }

        public Criteria andSinglestatusLessThan(Byte value) {
            addCriterion("singlestatus <", value, "singlestatus");
            return (Criteria) this;
        }

        public Criteria andSinglestatusLessThanOrEqualTo(Byte value) {
            addCriterion("singlestatus <=", value, "singlestatus");
            return (Criteria) this;
        }

        public Criteria andSinglestatusIn(List<Byte> values) {
            addCriterion("singlestatus in", values, "singlestatus");
            return (Criteria) this;
        }

        public Criteria andSinglestatusNotIn(List<Byte> values) {
            addCriterion("singlestatus not in", values, "singlestatus");
            return (Criteria) this;
        }

        public Criteria andSinglestatusBetween(Byte value1, Byte value2) {
            addCriterion("singlestatus between", value1, value2, "singlestatus");
            return (Criteria) this;
        }

        public Criteria andSinglestatusNotBetween(Byte value1, Byte value2) {
            addCriterion("singlestatus not between", value1, value2, "singlestatus");
            return (Criteria) this;
        }

        public Criteria andOperateContentIsNull() {
            addCriterion("operate_content is null");
            return (Criteria) this;
        }

        public Criteria andOperateContentIsNotNull() {
            addCriterion("operate_content is not null");
            return (Criteria) this;
        }

        public Criteria andOperateContentEqualTo(String value) {
            addCriterion("operate_content =", value, "operateContent");
            return (Criteria) this;
        }

        public Criteria andOperateContentNotEqualTo(String value) {
            addCriterion("operate_content <>", value, "operateContent");
            return (Criteria) this;
        }

        public Criteria andOperateContentGreaterThan(String value) {
            addCriterion("operate_content >", value, "operateContent");
            return (Criteria) this;
        }

        public Criteria andOperateContentGreaterThanOrEqualTo(String value) {
            addCriterion("operate_content >=", value, "operateContent");
            return (Criteria) this;
        }

        public Criteria andOperateContentLessThan(String value) {
            addCriterion("operate_content <", value, "operateContent");
            return (Criteria) this;
        }

        public Criteria andOperateContentLessThanOrEqualTo(String value) {
            addCriterion("operate_content <=", value, "operateContent");
            return (Criteria) this;
        }

        public Criteria andOperateContentLike(String value) {
            addCriterion("operate_content like", value, "operateContent");
            return (Criteria) this;
        }

        public Criteria andOperateContentNotLike(String value) {
            addCriterion("operate_content not like", value, "operateContent");
            return (Criteria) this;
        }

        public Criteria andOperateContentIn(List<String> values) {
            addCriterion("operate_content in", values, "operateContent");
            return (Criteria) this;
        }

        public Criteria andOperateContentNotIn(List<String> values) {
            addCriterion("operate_content not in", values, "operateContent");
            return (Criteria) this;
        }

        public Criteria andOperateContentBetween(String value1, String value2) {
            addCriterion("operate_content between", value1, value2, "operateContent");
            return (Criteria) this;
        }

        public Criteria andOperateContentNotBetween(String value1, String value2) {
            addCriterion("operate_content not between", value1, value2, "operateContent");
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

        public Criteria andRefundContentIsNull() {
            addCriterion("refund_content is null");
            return (Criteria) this;
        }

        public Criteria andRefundContentIsNotNull() {
            addCriterion("refund_content is not null");
            return (Criteria) this;
        }

        public Criteria andRefundContentEqualTo(String value) {
            addCriterion("refund_content =", value, "refundContent");
            return (Criteria) this;
        }

        public Criteria andRefundContentNotEqualTo(String value) {
            addCriterion("refund_content <>", value, "refundContent");
            return (Criteria) this;
        }

        public Criteria andRefundContentGreaterThan(String value) {
            addCriterion("refund_content >", value, "refundContent");
            return (Criteria) this;
        }

        public Criteria andRefundContentGreaterThanOrEqualTo(String value) {
            addCriterion("refund_content >=", value, "refundContent");
            return (Criteria) this;
        }

        public Criteria andRefundContentLessThan(String value) {
            addCriterion("refund_content <", value, "refundContent");
            return (Criteria) this;
        }

        public Criteria andRefundContentLessThanOrEqualTo(String value) {
            addCriterion("refund_content <=", value, "refundContent");
            return (Criteria) this;
        }

        public Criteria andRefundContentLike(String value) {
            addCriterion("refund_content like", value, "refundContent");
            return (Criteria) this;
        }

        public Criteria andRefundContentNotLike(String value) {
            addCriterion("refund_content not like", value, "refundContent");
            return (Criteria) this;
        }

        public Criteria andRefundContentIn(List<String> values) {
            addCriterion("refund_content in", values, "refundContent");
            return (Criteria) this;
        }

        public Criteria andRefundContentNotIn(List<String> values) {
            addCriterion("refund_content not in", values, "refundContent");
            return (Criteria) this;
        }

        public Criteria andRefundContentBetween(String value1, String value2) {
            addCriterion("refund_content between", value1, value2, "refundContent");
            return (Criteria) this;
        }

        public Criteria andRefundContentNotBetween(String value1, String value2) {
            addCriterion("refund_content not between", value1, value2, "refundContent");
            return (Criteria) this;
        }

        public Criteria andOperateContentLikeInsensitive(String value) {
            addCriterion("upper(operate_content) like", value.toUpperCase(), "operateContent");
            return (Criteria) this;
        }

        public Criteria andRefundContentLikeInsensitive(String value) {
            addCriterion("upper(refund_content) like", value.toUpperCase(), "refundContent");
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