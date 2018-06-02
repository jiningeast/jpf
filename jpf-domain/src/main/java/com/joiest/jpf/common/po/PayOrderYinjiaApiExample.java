package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayOrderYinjiaApiExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayOrderYinjiaApiExample() {
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

        public Criteria andForeignOrderidIsNull() {
            addCriterion("foreign_orderid is null");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidIsNotNull() {
            addCriterion("foreign_orderid is not null");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidEqualTo(String value) {
            addCriterion("foreign_orderid =", value, "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidNotEqualTo(String value) {
            addCriterion("foreign_orderid <>", value, "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidGreaterThan(String value) {
            addCriterion("foreign_orderid >", value, "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("foreign_orderid >=", value, "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidLessThan(String value) {
            addCriterion("foreign_orderid <", value, "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidLessThanOrEqualTo(String value) {
            addCriterion("foreign_orderid <=", value, "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidLike(String value) {
            addCriterion("foreign_orderid like", value, "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidNotLike(String value) {
            addCriterion("foreign_orderid not like", value, "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidIn(List<String> values) {
            addCriterion("foreign_orderid in", values, "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidNotIn(List<String> values) {
            addCriterion("foreign_orderid not in", values, "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidBetween(String value1, String value2) {
            addCriterion("foreign_orderid between", value1, value2, "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignOrderidNotBetween(String value1, String value2) {
            addCriterion("foreign_orderid not between", value1, value2, "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidIsNull() {
            addCriterion("sign_orderid is null");
            return (Criteria) this;
        }

        public Criteria andSignOrderidIsNotNull() {
            addCriterion("sign_orderid is not null");
            return (Criteria) this;
        }

        public Criteria andSignOrderidEqualTo(Long value) {
            addCriterion("sign_orderid =", value, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidNotEqualTo(Long value) {
            addCriterion("sign_orderid <>", value, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidGreaterThan(Long value) {
            addCriterion("sign_orderid >", value, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidGreaterThanOrEqualTo(Long value) {
            addCriterion("sign_orderid >=", value, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidLessThan(Long value) {
            addCriterion("sign_orderid <", value, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidLessThanOrEqualTo(Long value) {
            addCriterion("sign_orderid <=", value, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidIn(List<Long> values) {
            addCriterion("sign_orderid in", values, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidNotIn(List<Long> values) {
            addCriterion("sign_orderid not in", values, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidBetween(Long value1, Long value2) {
            addCriterion("sign_orderid between", value1, value2, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidNotBetween(Long value1, Long value2) {
            addCriterion("sign_orderid not between", value1, value2, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignRequestIsNull() {
            addCriterion("foreign_request is null");
            return (Criteria) this;
        }

        public Criteria andForeignRequestIsNotNull() {
            addCriterion("foreign_request is not null");
            return (Criteria) this;
        }

        public Criteria andForeignRequestEqualTo(String value) {
            addCriterion("foreign_request =", value, "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andForeignRequestNotEqualTo(String value) {
            addCriterion("foreign_request <>", value, "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andForeignRequestGreaterThan(String value) {
            addCriterion("foreign_request >", value, "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andForeignRequestGreaterThanOrEqualTo(String value) {
            addCriterion("foreign_request >=", value, "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andForeignRequestLessThan(String value) {
            addCriterion("foreign_request <", value, "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andForeignRequestLessThanOrEqualTo(String value) {
            addCriterion("foreign_request <=", value, "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andForeignRequestLike(String value) {
            addCriterion("foreign_request like", value, "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andForeignRequestNotLike(String value) {
            addCriterion("foreign_request not like", value, "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andForeignRequestIn(List<String> values) {
            addCriterion("foreign_request in", values, "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andForeignRequestNotIn(List<String> values) {
            addCriterion("foreign_request not in", values, "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andForeignRequestBetween(String value1, String value2) {
            addCriterion("foreign_request between", value1, value2, "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andForeignRequestNotBetween(String value1, String value2) {
            addCriterion("foreign_request not between", value1, value2, "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andReturnUrlIsNull() {
            addCriterion("return_url is null");
            return (Criteria) this;
        }

        public Criteria andReturnUrlIsNotNull() {
            addCriterion("return_url is not null");
            return (Criteria) this;
        }

        public Criteria andReturnUrlEqualTo(String value) {
            addCriterion("return_url =", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlNotEqualTo(String value) {
            addCriterion("return_url <>", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlGreaterThan(String value) {
            addCriterion("return_url >", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlGreaterThanOrEqualTo(String value) {
            addCriterion("return_url >=", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlLessThan(String value) {
            addCriterion("return_url <", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlLessThanOrEqualTo(String value) {
            addCriterion("return_url <=", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlLike(String value) {
            addCriterion("return_url like", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlNotLike(String value) {
            addCriterion("return_url not like", value, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlIn(List<String> values) {
            addCriterion("return_url in", values, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlNotIn(List<String> values) {
            addCriterion("return_url not in", values, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlBetween(String value1, String value2) {
            addCriterion("return_url between", value1, value2, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andReturnUrlNotBetween(String value1, String value2) {
            addCriterion("return_url not between", value1, value2, "returnUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlIsNull() {
            addCriterion("notify_url is null");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlIsNotNull() {
            addCriterion("notify_url is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlEqualTo(String value) {
            addCriterion("notify_url =", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotEqualTo(String value) {
            addCriterion("notify_url <>", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlGreaterThan(String value) {
            addCriterion("notify_url >", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("notify_url >=", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLessThan(String value) {
            addCriterion("notify_url <", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLessThanOrEqualTo(String value) {
            addCriterion("notify_url <=", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLike(String value) {
            addCriterion("notify_url like", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotLike(String value) {
            addCriterion("notify_url not like", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlIn(List<String> values) {
            addCriterion("notify_url in", values, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotIn(List<String> values) {
            addCriterion("notify_url not in", values, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlBetween(String value1, String value2) {
            addCriterion("notify_url between", value1, value2, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotBetween(String value1, String value2) {
            addCriterion("notify_url not between", value1, value2, "notifyUrl");
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

        public Criteria andPaytypeIsNull() {
            addCriterion("paytype is null");
            return (Criteria) this;
        }

        public Criteria andPaytypeIsNotNull() {
            addCriterion("paytype is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypeEqualTo(Integer value) {
            addCriterion("paytype =", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotEqualTo(Integer value) {
            addCriterion("paytype <>", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThan(Integer value) {
            addCriterion("paytype >", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("paytype >=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThan(Integer value) {
            addCriterion("paytype <", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeLessThanOrEqualTo(Integer value) {
            addCriterion("paytype <=", value, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeIn(List<Integer> values) {
            addCriterion("paytype in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotIn(List<Integer> values) {
            addCriterion("paytype not in", values, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeBetween(Integer value1, Integer value2) {
            addCriterion("paytype between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andPaytypeNotBetween(Integer value1, Integer value2) {
            addCriterion("paytype not between", value1, value2, "paytype");
            return (Criteria) this;
        }

        public Criteria andOrderPayPriceIsNull() {
            addCriterion("order_pay_price is null");
            return (Criteria) this;
        }

        public Criteria andOrderPayPriceIsNotNull() {
            addCriterion("order_pay_price is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPayPriceEqualTo(BigDecimal value) {
            addCriterion("order_pay_price =", value, "orderPayPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPayPriceNotEqualTo(BigDecimal value) {
            addCriterion("order_pay_price <>", value, "orderPayPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPayPriceGreaterThan(BigDecimal value) {
            addCriterion("order_pay_price >", value, "orderPayPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPayPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_pay_price >=", value, "orderPayPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPayPriceLessThan(BigDecimal value) {
            addCriterion("order_pay_price <", value, "orderPayPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPayPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_pay_price <=", value, "orderPayPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPayPriceIn(List<BigDecimal> values) {
            addCriterion("order_pay_price in", values, "orderPayPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPayPriceNotIn(List<BigDecimal> values) {
            addCriterion("order_pay_price not in", values, "orderPayPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPayPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_pay_price between", value1, value2, "orderPayPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPayPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_pay_price not between", value1, value2, "orderPayPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceIsNull() {
            addCriterion("order_std_price is null");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceIsNotNull() {
            addCriterion("order_std_price is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceEqualTo(BigDecimal value) {
            addCriterion("order_std_price =", value, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceNotEqualTo(BigDecimal value) {
            addCriterion("order_std_price <>", value, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceGreaterThan(BigDecimal value) {
            addCriterion("order_std_price >", value, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_std_price >=", value, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceLessThan(BigDecimal value) {
            addCriterion("order_std_price <", value, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_std_price <=", value, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceIn(List<BigDecimal> values) {
            addCriterion("order_std_price in", values, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceNotIn(List<BigDecimal> values) {
            addCriterion("order_std_price not in", values, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_std_price between", value1, value2, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andOrderStdPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_std_price not between", value1, value2, "orderStdPrice");
            return (Criteria) this;
        }

        public Criteria andProductAccountIsNull() {
            addCriterion("product_account is null");
            return (Criteria) this;
        }

        public Criteria andProductAccountIsNotNull() {
            addCriterion("product_account is not null");
            return (Criteria) this;
        }

        public Criteria andProductAccountEqualTo(Integer value) {
            addCriterion("product_account =", value, "productAccount");
            return (Criteria) this;
        }

        public Criteria andProductAccountNotEqualTo(Integer value) {
            addCriterion("product_account <>", value, "productAccount");
            return (Criteria) this;
        }

        public Criteria andProductAccountGreaterThan(Integer value) {
            addCriterion("product_account >", value, "productAccount");
            return (Criteria) this;
        }

        public Criteria andProductAccountGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_account >=", value, "productAccount");
            return (Criteria) this;
        }

        public Criteria andProductAccountLessThan(Integer value) {
            addCriterion("product_account <", value, "productAccount");
            return (Criteria) this;
        }

        public Criteria andProductAccountLessThanOrEqualTo(Integer value) {
            addCriterion("product_account <=", value, "productAccount");
            return (Criteria) this;
        }

        public Criteria andProductAccountIn(List<Integer> values) {
            addCriterion("product_account in", values, "productAccount");
            return (Criteria) this;
        }

        public Criteria andProductAccountNotIn(List<Integer> values) {
            addCriterion("product_account not in", values, "productAccount");
            return (Criteria) this;
        }

        public Criteria andProductAccountBetween(Integer value1, Integer value2) {
            addCriterion("product_account between", value1, value2, "productAccount");
            return (Criteria) this;
        }

        public Criteria andProductAccountNotBetween(Integer value1, Integer value2) {
            addCriterion("product_account not between", value1, value2, "productAccount");
            return (Criteria) this;
        }

        public Criteria andPayDetailIsNull() {
            addCriterion("pay_detail is null");
            return (Criteria) this;
        }

        public Criteria andPayDetailIsNotNull() {
            addCriterion("pay_detail is not null");
            return (Criteria) this;
        }

        public Criteria andPayDetailEqualTo(String value) {
            addCriterion("pay_detail =", value, "payDetail");
            return (Criteria) this;
        }

        public Criteria andPayDetailNotEqualTo(String value) {
            addCriterion("pay_detail <>", value, "payDetail");
            return (Criteria) this;
        }

        public Criteria andPayDetailGreaterThan(String value) {
            addCriterion("pay_detail >", value, "payDetail");
            return (Criteria) this;
        }

        public Criteria andPayDetailGreaterThanOrEqualTo(String value) {
            addCriterion("pay_detail >=", value, "payDetail");
            return (Criteria) this;
        }

        public Criteria andPayDetailLessThan(String value) {
            addCriterion("pay_detail <", value, "payDetail");
            return (Criteria) this;
        }

        public Criteria andPayDetailLessThanOrEqualTo(String value) {
            addCriterion("pay_detail <=", value, "payDetail");
            return (Criteria) this;
        }

        public Criteria andPayDetailLike(String value) {
            addCriterion("pay_detail like", value, "payDetail");
            return (Criteria) this;
        }

        public Criteria andPayDetailNotLike(String value) {
            addCriterion("pay_detail not like", value, "payDetail");
            return (Criteria) this;
        }

        public Criteria andPayDetailIn(List<String> values) {
            addCriterion("pay_detail in", values, "payDetail");
            return (Criteria) this;
        }

        public Criteria andPayDetailNotIn(List<String> values) {
            addCriterion("pay_detail not in", values, "payDetail");
            return (Criteria) this;
        }

        public Criteria andPayDetailBetween(String value1, String value2) {
            addCriterion("pay_detail between", value1, value2, "payDetail");
            return (Criteria) this;
        }

        public Criteria andPayDetailNotBetween(String value1, String value2) {
            addCriterion("pay_detail not between", value1, value2, "payDetail");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNull() {
            addCriterion("paytime is null");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNotNull() {
            addCriterion("paytime is not null");
            return (Criteria) this;
        }

        public Criteria andPaytimeEqualTo(Date value) {
            addCriterion("paytime =", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotEqualTo(Date value) {
            addCriterion("paytime <>", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThan(Date value) {
            addCriterion("paytime >", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("paytime >=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThan(Date value) {
            addCriterion("paytime <", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThanOrEqualTo(Date value) {
            addCriterion("paytime <=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeIn(List<Date> values) {
            addCriterion("paytime in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotIn(List<Date> values) {
            addCriterion("paytime not in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeBetween(Date value1, Date value2) {
            addCriterion("paytime between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotBetween(Date value1, Date value2) {
            addCriterion("paytime not between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("pay_status is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(Byte value) {
            addCriterion("pay_status =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(Byte value) {
            addCriterion("pay_status <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(Byte value) {
            addCriterion("pay_status >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_status >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(Byte value) {
            addCriterion("pay_status <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(Byte value) {
            addCriterion("pay_status <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<Byte> values) {
            addCriterion("pay_status in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<Byte> values) {
            addCriterion("pay_status not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(Byte value1, Byte value2) {
            addCriterion("pay_status between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_status not between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusIsNull() {
            addCriterion("refund_status is null");
            return (Criteria) this;
        }

        public Criteria andRefundStatusIsNotNull() {
            addCriterion("refund_status is not null");
            return (Criteria) this;
        }

        public Criteria andRefundStatusEqualTo(Byte value) {
            addCriterion("refund_status =", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusNotEqualTo(Byte value) {
            addCriterion("refund_status <>", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusGreaterThan(Byte value) {
            addCriterion("refund_status >", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("refund_status >=", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusLessThan(Byte value) {
            addCriterion("refund_status <", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusLessThanOrEqualTo(Byte value) {
            addCriterion("refund_status <=", value, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusIn(List<Byte> values) {
            addCriterion("refund_status in", values, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusNotIn(List<Byte> values) {
            addCriterion("refund_status not in", values, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusBetween(Byte value1, Byte value2) {
            addCriterion("refund_status between", value1, value2, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andRefundStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("refund_status not between", value1, value2, "refundStatus");
            return (Criteria) this;
        }

        public Criteria andUserOperateStatusIsNull() {
            addCriterion("user_operate_status is null");
            return (Criteria) this;
        }

        public Criteria andUserOperateStatusIsNotNull() {
            addCriterion("user_operate_status is not null");
            return (Criteria) this;
        }

        public Criteria andUserOperateStatusEqualTo(Byte value) {
            addCriterion("user_operate_status =", value, "userOperateStatus");
            return (Criteria) this;
        }

        public Criteria andUserOperateStatusNotEqualTo(Byte value) {
            addCriterion("user_operate_status <>", value, "userOperateStatus");
            return (Criteria) this;
        }

        public Criteria andUserOperateStatusGreaterThan(Byte value) {
            addCriterion("user_operate_status >", value, "userOperateStatus");
            return (Criteria) this;
        }

        public Criteria andUserOperateStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("user_operate_status >=", value, "userOperateStatus");
            return (Criteria) this;
        }

        public Criteria andUserOperateStatusLessThan(Byte value) {
            addCriterion("user_operate_status <", value, "userOperateStatus");
            return (Criteria) this;
        }

        public Criteria andUserOperateStatusLessThanOrEqualTo(Byte value) {
            addCriterion("user_operate_status <=", value, "userOperateStatus");
            return (Criteria) this;
        }

        public Criteria andUserOperateStatusIn(List<Byte> values) {
            addCriterion("user_operate_status in", values, "userOperateStatus");
            return (Criteria) this;
        }

        public Criteria andUserOperateStatusNotIn(List<Byte> values) {
            addCriterion("user_operate_status not in", values, "userOperateStatus");
            return (Criteria) this;
        }

        public Criteria andUserOperateStatusBetween(Byte value1, Byte value2) {
            addCriterion("user_operate_status between", value1, value2, "userOperateStatus");
            return (Criteria) this;
        }

        public Criteria andUserOperateStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("user_operate_status not between", value1, value2, "userOperateStatus");
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

        public Criteria andForeignOrderidLikeInsensitive(String value) {
            addCriterion("upper(foreign_orderid) like", value.toUpperCase(), "foreignOrderid");
            return (Criteria) this;
        }

        public Criteria andForeignRequestLikeInsensitive(String value) {
            addCriterion("upper(foreign_request) like", value.toUpperCase(), "foreignRequest");
            return (Criteria) this;
        }

        public Criteria andReturnUrlLikeInsensitive(String value) {
            addCriterion("upper(return_url) like", value.toUpperCase(), "returnUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLikeInsensitive(String value) {
            addCriterion("upper(notify_url) like", value.toUpperCase(), "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andPayDetailLikeInsensitive(String value) {
            addCriterion("upper(pay_detail) like", value.toUpperCase(), "payDetail");
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