package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayShopBargainRechargeViewExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayShopBargainRechargeViewExample() {
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

        public Criteria andBoidIsNull() {
            addCriterion("boid is null");
            return (Criteria) this;
        }

        public Criteria andBoidIsNotNull() {
            addCriterion("boid is not null");
            return (Criteria) this;
        }

        public Criteria andBoidEqualTo(Long value) {
            addCriterion("boid =", value, "boid");
            return (Criteria) this;
        }

        public Criteria andBoidNotEqualTo(Long value) {
            addCriterion("boid <>", value, "boid");
            return (Criteria) this;
        }

        public Criteria andBoidGreaterThan(Long value) {
            addCriterion("boid >", value, "boid");
            return (Criteria) this;
        }

        public Criteria andBoidGreaterThanOrEqualTo(Long value) {
            addCriterion("boid >=", value, "boid");
            return (Criteria) this;
        }

        public Criteria andBoidLessThan(Long value) {
            addCriterion("boid <", value, "boid");
            return (Criteria) this;
        }

        public Criteria andBoidLessThanOrEqualTo(Long value) {
            addCriterion("boid <=", value, "boid");
            return (Criteria) this;
        }

        public Criteria andBoidIn(List<Long> values) {
            addCriterion("boid in", values, "boid");
            return (Criteria) this;
        }

        public Criteria andBoidNotIn(List<Long> values) {
            addCriterion("boid not in", values, "boid");
            return (Criteria) this;
        }

        public Criteria andBoidBetween(Long value1, Long value2) {
            addCriterion("boid between", value1, value2, "boid");
            return (Criteria) this;
        }

        public Criteria andBoidNotBetween(Long value1, Long value2) {
            addCriterion("boid not between", value1, value2, "boid");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
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

        public Criteria andTransferPriceIsNull() {
            addCriterion("transfer_price is null");
            return (Criteria) this;
        }

        public Criteria andTransferPriceIsNotNull() {
            addCriterion("transfer_price is not null");
            return (Criteria) this;
        }

        public Criteria andTransferPriceEqualTo(BigDecimal value) {
            addCriterion("transfer_price =", value, "transferPrice");
            return (Criteria) this;
        }

        public Criteria andTransferPriceNotEqualTo(BigDecimal value) {
            addCriterion("transfer_price <>", value, "transferPrice");
            return (Criteria) this;
        }

        public Criteria andTransferPriceGreaterThan(BigDecimal value) {
            addCriterion("transfer_price >", value, "transferPrice");
            return (Criteria) this;
        }

        public Criteria andTransferPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("transfer_price >=", value, "transferPrice");
            return (Criteria) this;
        }

        public Criteria andTransferPriceLessThan(BigDecimal value) {
            addCriterion("transfer_price <", value, "transferPrice");
            return (Criteria) this;
        }

        public Criteria andTransferPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("transfer_price <=", value, "transferPrice");
            return (Criteria) this;
        }

        public Criteria andTransferPriceIn(List<BigDecimal> values) {
            addCriterion("transfer_price in", values, "transferPrice");
            return (Criteria) this;
        }

        public Criteria andTransferPriceNotIn(List<BigDecimal> values) {
            addCriterion("transfer_price not in", values, "transferPrice");
            return (Criteria) this;
        }

        public Criteria andTransferPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transfer_price between", value1, value2, "transferPrice");
            return (Criteria) this;
        }

        public Criteria andTransferPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transfer_price not between", value1, value2, "transferPrice");
            return (Criteria) this;
        }

        public Criteria andAlreadyPriceIsNull() {
            addCriterion("already_price is null");
            return (Criteria) this;
        }

        public Criteria andAlreadyPriceIsNotNull() {
            addCriterion("already_price is not null");
            return (Criteria) this;
        }

        public Criteria andAlreadyPriceEqualTo(BigDecimal value) {
            addCriterion("already_price =", value, "alreadyPrice");
            return (Criteria) this;
        }

        public Criteria andAlreadyPriceNotEqualTo(BigDecimal value) {
            addCriterion("already_price <>", value, "alreadyPrice");
            return (Criteria) this;
        }

        public Criteria andAlreadyPriceGreaterThan(BigDecimal value) {
            addCriterion("already_price >", value, "alreadyPrice");
            return (Criteria) this;
        }

        public Criteria andAlreadyPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("already_price >=", value, "alreadyPrice");
            return (Criteria) this;
        }

        public Criteria andAlreadyPriceLessThan(BigDecimal value) {
            addCriterion("already_price <", value, "alreadyPrice");
            return (Criteria) this;
        }

        public Criteria andAlreadyPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("already_price <=", value, "alreadyPrice");
            return (Criteria) this;
        }

        public Criteria andAlreadyPriceIn(List<BigDecimal> values) {
            addCriterion("already_price in", values, "alreadyPrice");
            return (Criteria) this;
        }

        public Criteria andAlreadyPriceNotIn(List<BigDecimal> values) {
            addCriterion("already_price not in", values, "alreadyPrice");
            return (Criteria) this;
        }

        public Criteria andAlreadyPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("already_price between", value1, value2, "alreadyPrice");
            return (Criteria) this;
        }

        public Criteria andAlreadyPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("already_price not between", value1, value2, "alreadyPrice");
            return (Criteria) this;
        }

        public Criteria andRemainPriceIsNull() {
            addCriterion("remain_price is null");
            return (Criteria) this;
        }

        public Criteria andRemainPriceIsNotNull() {
            addCriterion("remain_price is not null");
            return (Criteria) this;
        }

        public Criteria andRemainPriceEqualTo(BigDecimal value) {
            addCriterion("remain_price =", value, "remainPrice");
            return (Criteria) this;
        }

        public Criteria andRemainPriceNotEqualTo(BigDecimal value) {
            addCriterion("remain_price <>", value, "remainPrice");
            return (Criteria) this;
        }

        public Criteria andRemainPriceGreaterThan(BigDecimal value) {
            addCriterion("remain_price >", value, "remainPrice");
            return (Criteria) this;
        }

        public Criteria andRemainPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("remain_price >=", value, "remainPrice");
            return (Criteria) this;
        }

        public Criteria andRemainPriceLessThan(BigDecimal value) {
            addCriterion("remain_price <", value, "remainPrice");
            return (Criteria) this;
        }

        public Criteria andRemainPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("remain_price <=", value, "remainPrice");
            return (Criteria) this;
        }

        public Criteria andRemainPriceIn(List<BigDecimal> values) {
            addCriterion("remain_price in", values, "remainPrice");
            return (Criteria) this;
        }

        public Criteria andRemainPriceNotIn(List<BigDecimal> values) {
            addCriterion("remain_price not in", values, "remainPrice");
            return (Criteria) this;
        }

        public Criteria andRemainPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remain_price between", value1, value2, "remainPrice");
            return (Criteria) this;
        }

        public Criteria andRemainPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remain_price not between", value1, value2, "remainPrice");
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

        public Criteria andOrderNoLikeInsensitive(String value) {
            addCriterion("upper(order_no) like", value.toUpperCase(), "orderNo");
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