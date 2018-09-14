package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayShopBargainOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayShopBargainOrderExample() {
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

        public Criteria andBargainRequestIdIsNull() {
            addCriterion("bargain_request_id is null");
            return (Criteria) this;
        }

        public Criteria andBargainRequestIdIsNotNull() {
            addCriterion("bargain_request_id is not null");
            return (Criteria) this;
        }

        public Criteria andBargainRequestIdEqualTo(String value) {
            addCriterion("bargain_request_id =", value, "bargainRequestId");
            return (Criteria) this;
        }

        public Criteria andBargainRequestIdNotEqualTo(String value) {
            addCriterion("bargain_request_id <>", value, "bargainRequestId");
            return (Criteria) this;
        }

        public Criteria andBargainRequestIdGreaterThan(String value) {
            addCriterion("bargain_request_id >", value, "bargainRequestId");
            return (Criteria) this;
        }

        public Criteria andBargainRequestIdGreaterThanOrEqualTo(String value) {
            addCriterion("bargain_request_id >=", value, "bargainRequestId");
            return (Criteria) this;
        }

        public Criteria andBargainRequestIdLessThan(String value) {
            addCriterion("bargain_request_id <", value, "bargainRequestId");
            return (Criteria) this;
        }

        public Criteria andBargainRequestIdLessThanOrEqualTo(String value) {
            addCriterion("bargain_request_id <=", value, "bargainRequestId");
            return (Criteria) this;
        }

        public Criteria andBargainRequestIdIn(List<String> values) {
            addCriterion("bargain_request_id in", values, "bargainRequestId");
            return (Criteria) this;
        }

        public Criteria andBargainRequestIdNotIn(List<String> values) {
            addCriterion("bargain_request_id not in", values, "bargainRequestId");
            return (Criteria) this;
        }

        public Criteria andBargainRequestIdBetween(String value1, String value2) {
            addCriterion("bargain_request_id between", value1, value2, "bargainRequestId");
            return (Criteria) this;
        }

        public Criteria andBargainRequestIdNotBetween(String value1, String value2) {
            addCriterion("bargain_request_id not between", value1, value2, "bargainRequestId");
            return (Criteria) this;
        }

        public Criteria andBuyerCustomerIdIsNull() {
            addCriterion("buyer_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andBuyerCustomerIdIsNotNull() {
            addCriterion("buyer_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerCustomerIdEqualTo(String value) {
            addCriterion("buyer_customer_id =", value, "buyerCustomerId");
            return (Criteria) this;
        }

        public Criteria andBuyerCustomerIdNotEqualTo(String value) {
            addCriterion("buyer_customer_id <>", value, "buyerCustomerId");
            return (Criteria) this;
        }

        public Criteria andBuyerCustomerIdGreaterThan(String value) {
            addCriterion("buyer_customer_id >", value, "buyerCustomerId");
            return (Criteria) this;
        }

        public Criteria andBuyerCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_customer_id >=", value, "buyerCustomerId");
            return (Criteria) this;
        }

        public Criteria andBuyerCustomerIdLessThan(String value) {
            addCriterion("buyer_customer_id <", value, "buyerCustomerId");
            return (Criteria) this;
        }

        public Criteria andBuyerCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("buyer_customer_id <=", value, "buyerCustomerId");
            return (Criteria) this;
        }

        public Criteria andBuyerCustomerIdIn(List<String> values) {
            addCriterion("buyer_customer_id in", values, "buyerCustomerId");
            return (Criteria) this;
        }

        public Criteria andBuyerCustomerIdNotIn(List<String> values) {
            addCriterion("buyer_customer_id not in", values, "buyerCustomerId");
            return (Criteria) this;
        }

        public Criteria andBuyerCustomerIdBetween(String value1, String value2) {
            addCriterion("buyer_customer_id between", value1, value2, "buyerCustomerId");
            return (Criteria) this;
        }

        public Criteria andBuyerCustomerIdNotBetween(String value1, String value2) {
            addCriterion("buyer_customer_id not between", value1, value2, "buyerCustomerId");
            return (Criteria) this;
        }

        public Criteria andSellerCustomerIdIsNull() {
            addCriterion("seller_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andSellerCustomerIdIsNotNull() {
            addCriterion("seller_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andSellerCustomerIdEqualTo(String value) {
            addCriterion("seller_customer_id =", value, "sellerCustomerId");
            return (Criteria) this;
        }

        public Criteria andSellerCustomerIdNotEqualTo(String value) {
            addCriterion("seller_customer_id <>", value, "sellerCustomerId");
            return (Criteria) this;
        }

        public Criteria andSellerCustomerIdGreaterThan(String value) {
            addCriterion("seller_customer_id >", value, "sellerCustomerId");
            return (Criteria) this;
        }

        public Criteria andSellerCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("seller_customer_id >=", value, "sellerCustomerId");
            return (Criteria) this;
        }

        public Criteria andSellerCustomerIdLessThan(String value) {
            addCriterion("seller_customer_id <", value, "sellerCustomerId");
            return (Criteria) this;
        }

        public Criteria andSellerCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("seller_customer_id <=", value, "sellerCustomerId");
            return (Criteria) this;
        }

        public Criteria andSellerCustomerIdIn(List<String> values) {
            addCriterion("seller_customer_id in", values, "sellerCustomerId");
            return (Criteria) this;
        }

        public Criteria andSellerCustomerIdNotIn(List<String> values) {
            addCriterion("seller_customer_id not in", values, "sellerCustomerId");
            return (Criteria) this;
        }

        public Criteria andSellerCustomerIdBetween(String value1, String value2) {
            addCriterion("seller_customer_id between", value1, value2, "sellerCustomerId");
            return (Criteria) this;
        }

        public Criteria andSellerCustomerIdNotBetween(String value1, String value2) {
            addCriterion("seller_customer_id not between", value1, value2, "sellerCustomerId");
            return (Criteria) this;
        }

        public Criteria andOffRateIsNull() {
            addCriterion("off_rate is null");
            return (Criteria) this;
        }

        public Criteria andOffRateIsNotNull() {
            addCriterion("off_rate is not null");
            return (Criteria) this;
        }

        public Criteria andOffRateEqualTo(Double value) {
            addCriterion("off_rate =", value, "offRate");
            return (Criteria) this;
        }

        public Criteria andOffRateNotEqualTo(Double value) {
            addCriterion("off_rate <>", value, "offRate");
            return (Criteria) this;
        }

        public Criteria andOffRateGreaterThan(Double value) {
            addCriterion("off_rate >", value, "offRate");
            return (Criteria) this;
        }

        public Criteria andOffRateGreaterThanOrEqualTo(Double value) {
            addCriterion("off_rate >=", value, "offRate");
            return (Criteria) this;
        }

        public Criteria andOffRateLessThan(Double value) {
            addCriterion("off_rate <", value, "offRate");
            return (Criteria) this;
        }

        public Criteria andOffRateLessThanOrEqualTo(Double value) {
            addCriterion("off_rate <=", value, "offRate");
            return (Criteria) this;
        }

        public Criteria andOffRateIn(List<Double> values) {
            addCriterion("off_rate in", values, "offRate");
            return (Criteria) this;
        }

        public Criteria andOffRateNotIn(List<Double> values) {
            addCriterion("off_rate not in", values, "offRate");
            return (Criteria) this;
        }

        public Criteria andOffRateBetween(Double value1, Double value2) {
            addCriterion("off_rate between", value1, value2, "offRate");
            return (Criteria) this;
        }

        public Criteria andOffRateNotBetween(Double value1, Double value2) {
            addCriterion("off_rate not between", value1, value2, "offRate");
            return (Criteria) this;
        }

        public Criteria andMinDouIsNull() {
            addCriterion("min_dou is null");
            return (Criteria) this;
        }

        public Criteria andMinDouIsNotNull() {
            addCriterion("min_dou is not null");
            return (Criteria) this;
        }

        public Criteria andMinDouEqualTo(Integer value) {
            addCriterion("min_dou =", value, "minDou");
            return (Criteria) this;
        }

        public Criteria andMinDouNotEqualTo(Integer value) {
            addCriterion("min_dou <>", value, "minDou");
            return (Criteria) this;
        }

        public Criteria andMinDouGreaterThan(Integer value) {
            addCriterion("min_dou >", value, "minDou");
            return (Criteria) this;
        }

        public Criteria andMinDouGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_dou >=", value, "minDou");
            return (Criteria) this;
        }

        public Criteria andMinDouLessThan(Integer value) {
            addCriterion("min_dou <", value, "minDou");
            return (Criteria) this;
        }

        public Criteria andMinDouLessThanOrEqualTo(Integer value) {
            addCriterion("min_dou <=", value, "minDou");
            return (Criteria) this;
        }

        public Criteria andMinDouIn(List<Integer> values) {
            addCriterion("min_dou in", values, "minDou");
            return (Criteria) this;
        }

        public Criteria andMinDouNotIn(List<Integer> values) {
            addCriterion("min_dou not in", values, "minDou");
            return (Criteria) this;
        }

        public Criteria andMinDouBetween(Integer value1, Integer value2) {
            addCriterion("min_dou between", value1, value2, "minDou");
            return (Criteria) this;
        }

        public Criteria andMinDouNotBetween(Integer value1, Integer value2) {
            addCriterion("min_dou not between", value1, value2, "minDou");
            return (Criteria) this;
        }

        public Criteria andDouIsNull() {
            addCriterion("dou is null");
            return (Criteria) this;
        }

        public Criteria andDouIsNotNull() {
            addCriterion("dou is not null");
            return (Criteria) this;
        }

        public Criteria andDouEqualTo(Integer value) {
            addCriterion("dou =", value, "dou");
            return (Criteria) this;
        }

        public Criteria andDouNotEqualTo(Integer value) {
            addCriterion("dou <>", value, "dou");
            return (Criteria) this;
        }

        public Criteria andDouGreaterThan(Integer value) {
            addCriterion("dou >", value, "dou");
            return (Criteria) this;
        }

        public Criteria andDouGreaterThanOrEqualTo(Integer value) {
            addCriterion("dou >=", value, "dou");
            return (Criteria) this;
        }

        public Criteria andDouLessThan(Integer value) {
            addCriterion("dou <", value, "dou");
            return (Criteria) this;
        }

        public Criteria andDouLessThanOrEqualTo(Integer value) {
            addCriterion("dou <=", value, "dou");
            return (Criteria) this;
        }

        public Criteria andDouIn(List<Integer> values) {
            addCriterion("dou in", values, "dou");
            return (Criteria) this;
        }

        public Criteria andDouNotIn(List<Integer> values) {
            addCriterion("dou not in", values, "dou");
            return (Criteria) this;
        }

        public Criteria andDouBetween(Integer value1, Integer value2) {
            addCriterion("dou between", value1, value2, "dou");
            return (Criteria) this;
        }

        public Criteria andDouNotBetween(Integer value1, Integer value2) {
            addCriterion("dou not between", value1, value2, "dou");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNull() {
            addCriterion("real_name is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("real_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("real_name =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("real_name <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("real_name >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_name >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("real_name <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("real_name <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("real_name like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("real_name not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("real_name in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("real_name not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("real_name between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("real_name not between", value1, value2, "realName");
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

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andBankIdIsNull() {
            addCriterion("bank_id is null");
            return (Criteria) this;
        }

        public Criteria andBankIdIsNotNull() {
            addCriterion("bank_id is not null");
            return (Criteria) this;
        }

        public Criteria andBankIdEqualTo(String value) {
            addCriterion("bank_id =", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotEqualTo(String value) {
            addCriterion("bank_id <>", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdGreaterThan(String value) {
            addCriterion("bank_id >", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdGreaterThanOrEqualTo(String value) {
            addCriterion("bank_id >=", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdLessThan(String value) {
            addCriterion("bank_id <", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdLessThanOrEqualTo(String value) {
            addCriterion("bank_id <=", value, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdIn(List<String> values) {
            addCriterion("bank_id in", values, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotIn(List<String> values) {
            addCriterion("bank_id not in", values, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdBetween(String value1, String value2) {
            addCriterion("bank_id between", value1, value2, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankIdNotBetween(String value1, String value2) {
            addCriterion("bank_id not between", value1, value2, "bankId");
            return (Criteria) this;
        }

        public Criteria andBankBrankIsNull() {
            addCriterion("bank_brank is null");
            return (Criteria) this;
        }

        public Criteria andBankBrankIsNotNull() {
            addCriterion("bank_brank is not null");
            return (Criteria) this;
        }

        public Criteria andBankBrankEqualTo(String value) {
            addCriterion("bank_brank =", value, "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankBrankNotEqualTo(String value) {
            addCriterion("bank_brank <>", value, "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankBrankGreaterThan(String value) {
            addCriterion("bank_brank >", value, "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankBrankGreaterThanOrEqualTo(String value) {
            addCriterion("bank_brank >=", value, "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankBrankLessThan(String value) {
            addCriterion("bank_brank <", value, "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankBrankLessThanOrEqualTo(String value) {
            addCriterion("bank_brank <=", value, "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankBrankLike(String value) {
            addCriterion("bank_brank like", value, "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankBrankNotLike(String value) {
            addCriterion("bank_brank not like", value, "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankBrankIn(List<String> values) {
            addCriterion("bank_brank in", values, "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankBrankNotIn(List<String> values) {
            addCriterion("bank_brank not in", values, "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankBrankBetween(String value1, String value2) {
            addCriterion("bank_brank between", value1, value2, "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankBrankNotBetween(String value1, String value2) {
            addCriterion("bank_brank not between", value1, value2, "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankNoIsNull() {
            addCriterion("bank_no is null");
            return (Criteria) this;
        }

        public Criteria andBankNoIsNotNull() {
            addCriterion("bank_no is not null");
            return (Criteria) this;
        }

        public Criteria andBankNoEqualTo(String value) {
            addCriterion("bank_no =", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotEqualTo(String value) {
            addCriterion("bank_no <>", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoGreaterThan(String value) {
            addCriterion("bank_no >", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoGreaterThanOrEqualTo(String value) {
            addCriterion("bank_no >=", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLessThan(String value) {
            addCriterion("bank_no <", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLessThanOrEqualTo(String value) {
            addCriterion("bank_no <=", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLike(String value) {
            addCriterion("bank_no like", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotLike(String value) {
            addCriterion("bank_no not like", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoIn(List<String> values) {
            addCriterion("bank_no in", values, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotIn(List<String> values) {
            addCriterion("bank_no not in", values, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoBetween(String value1, String value2) {
            addCriterion("bank_no between", value1, value2, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotBetween(String value1, String value2) {
            addCriterion("bank_no not between", value1, value2, "bankNo");
            return (Criteria) this;
        }

        public Criteria andFindcodeIsNull() {
            addCriterion("findcode is null");
            return (Criteria) this;
        }

        public Criteria andFindcodeIsNotNull() {
            addCriterion("findcode is not null");
            return (Criteria) this;
        }

        public Criteria andFindcodeEqualTo(String value) {
            addCriterion("findcode =", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeNotEqualTo(String value) {
            addCriterion("findcode <>", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeGreaterThan(String value) {
            addCriterion("findcode >", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeGreaterThanOrEqualTo(String value) {
            addCriterion("findcode >=", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeLessThan(String value) {
            addCriterion("findcode <", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeLessThanOrEqualTo(String value) {
            addCriterion("findcode <=", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeLike(String value) {
            addCriterion("findcode like", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeNotLike(String value) {
            addCriterion("findcode not like", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeIn(List<String> values) {
            addCriterion("findcode in", values, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeNotIn(List<String> values) {
            addCriterion("findcode not in", values, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeBetween(String value1, String value2) {
            addCriterion("findcode between", value1, value2, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeNotBetween(String value1, String value2) {
            addCriterion("findcode not between", value1, value2, "findcode");
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

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
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

        public Criteria andOperatorIdEqualTo(String value) {
            addCriterion("operator_id =", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotEqualTo(String value) {
            addCriterion("operator_id <>", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThan(String value) {
            addCriterion("operator_id >", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("operator_id >=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThan(String value) {
            addCriterion("operator_id <", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdLessThanOrEqualTo(String value) {
            addCriterion("operator_id <=", value, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdIn(List<String> values) {
            addCriterion("operator_id in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotIn(List<String> values) {
            addCriterion("operator_id not in", values, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdBetween(String value1, String value2) {
            addCriterion("operator_id between", value1, value2, "operatorId");
            return (Criteria) this;
        }

        public Criteria andOperatorIdNotBetween(String value1, String value2) {
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

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
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

        public Criteria andOrderNoLikeInsensitive(String value) {
            addCriterion("upper(order_no) like", value.toUpperCase(), "orderNo");
            return (Criteria) this;
        }

        public Criteria andRealNameLikeInsensitive(String value) {
            addCriterion("upper(real_name) like", value.toUpperCase(), "realName");
            return (Criteria) this;
        }

        public Criteria andIdnoLikeInsensitive(String value) {
            addCriterion("upper(idno) like", value.toUpperCase(), "idno");
            return (Criteria) this;
        }

        public Criteria andPhoneLikeInsensitive(String value) {
            addCriterion("upper(phone) like", value.toUpperCase(), "phone");
            return (Criteria) this;
        }

        public Criteria andBankBrankLikeInsensitive(String value) {
            addCriterion("upper(bank_brank) like", value.toUpperCase(), "bankBrank");
            return (Criteria) this;
        }

        public Criteria andBankNoLikeInsensitive(String value) {
            addCriterion("upper(bank_no) like", value.toUpperCase(), "bankNo");
            return (Criteria) this;
        }

        public Criteria andFindcodeLikeInsensitive(String value) {
            addCriterion("upper(findcode) like", value.toUpperCase(), "findcode");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLikeInsensitive(String value) {
            addCriterion("upper(operator_name) like", value.toUpperCase(), "operatorName");
            return (Criteria) this;
        }

        public Criteria andMemoLikeInsensitive(String value) {
            addCriterion("upper(memo) like", value.toUpperCase(), "memo");
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