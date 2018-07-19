package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayCloudDfQorderStreamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayCloudDfQorderStreamExample() {
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

        public Criteria andRequestOrderidIsNull() {
            addCriterion("request_orderid is null");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidIsNotNull() {
            addCriterion("request_orderid is not null");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidEqualTo(String value) {
            addCriterion("request_orderid =", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidNotEqualTo(String value) {
            addCriterion("request_orderid <>", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidGreaterThan(String value) {
            addCriterion("request_orderid >", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("request_orderid >=", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidLessThan(String value) {
            addCriterion("request_orderid <", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidLessThanOrEqualTo(String value) {
            addCriterion("request_orderid <=", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidLike(String value) {
            addCriterion("request_orderid like", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidNotLike(String value) {
            addCriterion("request_orderid not like", value, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidIn(List<String> values) {
            addCriterion("request_orderid in", values, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidNotIn(List<String> values) {
            addCriterion("request_orderid not in", values, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidBetween(String value1, String value2) {
            addCriterion("request_orderid between", value1, value2, "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestOrderidNotBetween(String value1, String value2) {
            addCriterion("request_orderid not between", value1, value2, "requestOrderid");
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

        public Criteria andTranamtIsNull() {
            addCriterion("tranAmt is null");
            return (Criteria) this;
        }

        public Criteria andTranamtIsNotNull() {
            addCriterion("tranAmt is not null");
            return (Criteria) this;
        }

        public Criteria andTranamtEqualTo(BigDecimal value) {
            addCriterion("tranAmt =", value, "tranamt");
            return (Criteria) this;
        }

        public Criteria andTranamtNotEqualTo(BigDecimal value) {
            addCriterion("tranAmt <>", value, "tranamt");
            return (Criteria) this;
        }

        public Criteria andTranamtGreaterThan(BigDecimal value) {
            addCriterion("tranAmt >", value, "tranamt");
            return (Criteria) this;
        }

        public Criteria andTranamtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("tranAmt >=", value, "tranamt");
            return (Criteria) this;
        }

        public Criteria andTranamtLessThan(BigDecimal value) {
            addCriterion("tranAmt <", value, "tranamt");
            return (Criteria) this;
        }

        public Criteria andTranamtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("tranAmt <=", value, "tranamt");
            return (Criteria) this;
        }

        public Criteria andTranamtIn(List<BigDecimal> values) {
            addCriterion("tranAmt in", values, "tranamt");
            return (Criteria) this;
        }

        public Criteria andTranamtNotIn(List<BigDecimal> values) {
            addCriterion("tranAmt not in", values, "tranamt");
            return (Criteria) this;
        }

        public Criteria andTranamtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tranAmt between", value1, value2, "tranamt");
            return (Criteria) this;
        }

        public Criteria andTranamtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("tranAmt not between", value1, value2, "tranamt");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIsNull() {
            addCriterion("orderStatus is null");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIsNotNull() {
            addCriterion("orderStatus is not null");
            return (Criteria) this;
        }

        public Criteria andOrderstatusEqualTo(String value) {
            addCriterion("orderStatus =", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotEqualTo(String value) {
            addCriterion("orderStatus <>", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusGreaterThan(String value) {
            addCriterion("orderStatus >", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusGreaterThanOrEqualTo(String value) {
            addCriterion("orderStatus >=", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLessThan(String value) {
            addCriterion("orderStatus <", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLessThanOrEqualTo(String value) {
            addCriterion("orderStatus <=", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLike(String value) {
            addCriterion("orderStatus like", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotLike(String value) {
            addCriterion("orderStatus not like", value, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusIn(List<String> values) {
            addCriterion("orderStatus in", values, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotIn(List<String> values) {
            addCriterion("orderStatus not in", values, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusBetween(String value1, String value2) {
            addCriterion("orderStatus between", value1, value2, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andOrderstatusNotBetween(String value1, String value2) {
            addCriterion("orderStatus not between", value1, value2, "orderstatus");
            return (Criteria) this;
        }

        public Criteria andRequestparamIsNull() {
            addCriterion("requestParam is null");
            return (Criteria) this;
        }

        public Criteria andRequestparamIsNotNull() {
            addCriterion("requestParam is not null");
            return (Criteria) this;
        }

        public Criteria andRequestparamEqualTo(String value) {
            addCriterion("requestParam =", value, "requestparam");
            return (Criteria) this;
        }

        public Criteria andRequestparamNotEqualTo(String value) {
            addCriterion("requestParam <>", value, "requestparam");
            return (Criteria) this;
        }

        public Criteria andRequestparamGreaterThan(String value) {
            addCriterion("requestParam >", value, "requestparam");
            return (Criteria) this;
        }

        public Criteria andRequestparamGreaterThanOrEqualTo(String value) {
            addCriterion("requestParam >=", value, "requestparam");
            return (Criteria) this;
        }

        public Criteria andRequestparamLessThan(String value) {
            addCriterion("requestParam <", value, "requestparam");
            return (Criteria) this;
        }

        public Criteria andRequestparamLessThanOrEqualTo(String value) {
            addCriterion("requestParam <=", value, "requestparam");
            return (Criteria) this;
        }

        public Criteria andRequestparamLike(String value) {
            addCriterion("requestParam like", value, "requestparam");
            return (Criteria) this;
        }

        public Criteria andRequestparamNotLike(String value) {
            addCriterion("requestParam not like", value, "requestparam");
            return (Criteria) this;
        }

        public Criteria andRequestparamIn(List<String> values) {
            addCriterion("requestParam in", values, "requestparam");
            return (Criteria) this;
        }

        public Criteria andRequestparamNotIn(List<String> values) {
            addCriterion("requestParam not in", values, "requestparam");
            return (Criteria) this;
        }

        public Criteria andRequestparamBetween(String value1, String value2) {
            addCriterion("requestParam between", value1, value2, "requestparam");
            return (Criteria) this;
        }

        public Criteria andRequestparamNotBetween(String value1, String value2) {
            addCriterion("requestParam not between", value1, value2, "requestparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamIsNull() {
            addCriterion("responseParam is null");
            return (Criteria) this;
        }

        public Criteria andResponseparamIsNotNull() {
            addCriterion("responseParam is not null");
            return (Criteria) this;
        }

        public Criteria andResponseparamEqualTo(String value) {
            addCriterion("responseParam =", value, "responseparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamNotEqualTo(String value) {
            addCriterion("responseParam <>", value, "responseparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamGreaterThan(String value) {
            addCriterion("responseParam >", value, "responseparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamGreaterThanOrEqualTo(String value) {
            addCriterion("responseParam >=", value, "responseparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamLessThan(String value) {
            addCriterion("responseParam <", value, "responseparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamLessThanOrEqualTo(String value) {
            addCriterion("responseParam <=", value, "responseparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamLike(String value) {
            addCriterion("responseParam like", value, "responseparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamNotLike(String value) {
            addCriterion("responseParam not like", value, "responseparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamIn(List<String> values) {
            addCriterion("responseParam in", values, "responseparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamNotIn(List<String> values) {
            addCriterion("responseParam not in", values, "responseparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamBetween(String value1, String value2) {
            addCriterion("responseParam between", value1, value2, "responseparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamNotBetween(String value1, String value2) {
            addCriterion("responseParam not between", value1, value2, "responseparam");
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

        public Criteria andRequestOrderidLikeInsensitive(String value) {
            addCriterion("upper(request_orderid) like", value.toUpperCase(), "requestOrderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLikeInsensitive(String value) {
            addCriterion("upper(orderid) like", value.toUpperCase(), "orderid");
            return (Criteria) this;
        }

        public Criteria andTrannoLikeInsensitive(String value) {
            addCriterion("upper(tranNo) like", value.toUpperCase(), "tranno");
            return (Criteria) this;
        }

        public Criteria andOrderstatusLikeInsensitive(String value) {
            addCriterion("upper(orderStatus) like", value.toUpperCase(), "orderstatus");
            return (Criteria) this;
        }

        public Criteria andRequestparamLikeInsensitive(String value) {
            addCriterion("upper(requestParam) like", value.toUpperCase(), "requestparam");
            return (Criteria) this;
        }

        public Criteria andResponseparamLikeInsensitive(String value) {
            addCriterion("upper(responseParam) like", value.toUpperCase(), "responseparam");
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