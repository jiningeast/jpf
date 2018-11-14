package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayChargeCompanyMoneyStreamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayChargeCompanyMoneyStreamExample() {
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

        public Criteria andStreamNoIsNull() {
            addCriterion("stream_no is null");
            return (Criteria) this;
        }

        public Criteria andStreamNoIsNotNull() {
            addCriterion("stream_no is not null");
            return (Criteria) this;
        }

        public Criteria andStreamNoEqualTo(String value) {
            addCriterion("stream_no =", value, "streamNo");
            return (Criteria) this;
        }

        public Criteria andStreamNoNotEqualTo(String value) {
            addCriterion("stream_no <>", value, "streamNo");
            return (Criteria) this;
        }

        public Criteria andStreamNoGreaterThan(String value) {
            addCriterion("stream_no >", value, "streamNo");
            return (Criteria) this;
        }

        public Criteria andStreamNoGreaterThanOrEqualTo(String value) {
            addCriterion("stream_no >=", value, "streamNo");
            return (Criteria) this;
        }

        public Criteria andStreamNoLessThan(String value) {
            addCriterion("stream_no <", value, "streamNo");
            return (Criteria) this;
        }

        public Criteria andStreamNoLessThanOrEqualTo(String value) {
            addCriterion("stream_no <=", value, "streamNo");
            return (Criteria) this;
        }

        public Criteria andStreamNoLike(String value) {
            addCriterion("stream_no like", value, "streamNo");
            return (Criteria) this;
        }

        public Criteria andStreamNoNotLike(String value) {
            addCriterion("stream_no not like", value, "streamNo");
            return (Criteria) this;
        }

        public Criteria andStreamNoIn(List<String> values) {
            addCriterion("stream_no in", values, "streamNo");
            return (Criteria) this;
        }

        public Criteria andStreamNoNotIn(List<String> values) {
            addCriterion("stream_no not in", values, "streamNo");
            return (Criteria) this;
        }

        public Criteria andStreamNoBetween(String value1, String value2) {
            addCriterion("stream_no between", value1, value2, "streamNo");
            return (Criteria) this;
        }

        public Criteria andStreamNoNotBetween(String value1, String value2) {
            addCriterion("stream_no not between", value1, value2, "streamNo");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andMerchNoIsNull() {
            addCriterion("merch_no is null");
            return (Criteria) this;
        }

        public Criteria andMerchNoIsNotNull() {
            addCriterion("merch_no is not null");
            return (Criteria) this;
        }

        public Criteria andMerchNoEqualTo(String value) {
            addCriterion("merch_no =", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoNotEqualTo(String value) {
            addCriterion("merch_no <>", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoGreaterThan(String value) {
            addCriterion("merch_no >", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoGreaterThanOrEqualTo(String value) {
            addCriterion("merch_no >=", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoLessThan(String value) {
            addCriterion("merch_no <", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoLessThanOrEqualTo(String value) {
            addCriterion("merch_no <=", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoLike(String value) {
            addCriterion("merch_no like", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoNotLike(String value) {
            addCriterion("merch_no not like", value, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoIn(List<String> values) {
            addCriterion("merch_no in", values, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoNotIn(List<String> values) {
            addCriterion("merch_no not in", values, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoBetween(String value1, String value2) {
            addCriterion("merch_no between", value1, value2, "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoNotBetween(String value1, String value2) {
            addCriterion("merch_no not between", value1, value2, "merchNo");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
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

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductValueIsNull() {
            addCriterion("product_value is null");
            return (Criteria) this;
        }

        public Criteria andProductValueIsNotNull() {
            addCriterion("product_value is not null");
            return (Criteria) this;
        }

        public Criteria andProductValueEqualTo(BigDecimal value) {
            addCriterion("product_value =", value, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueNotEqualTo(BigDecimal value) {
            addCriterion("product_value <>", value, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueGreaterThan(BigDecimal value) {
            addCriterion("product_value >", value, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_value >=", value, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueLessThan(BigDecimal value) {
            addCriterion("product_value <", value, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_value <=", value, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueIn(List<BigDecimal> values) {
            addCriterion("product_value in", values, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueNotIn(List<BigDecimal> values) {
            addCriterion("product_value not in", values, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_value between", value1, value2, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_value not between", value1, value2, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductBidPriceIsNull() {
            addCriterion("product_bid_price is null");
            return (Criteria) this;
        }

        public Criteria andProductBidPriceIsNotNull() {
            addCriterion("product_bid_price is not null");
            return (Criteria) this;
        }

        public Criteria andProductBidPriceEqualTo(BigDecimal value) {
            addCriterion("product_bid_price =", value, "productBidPrice");
            return (Criteria) this;
        }

        public Criteria andProductBidPriceNotEqualTo(BigDecimal value) {
            addCriterion("product_bid_price <>", value, "productBidPrice");
            return (Criteria) this;
        }

        public Criteria andProductBidPriceGreaterThan(BigDecimal value) {
            addCriterion("product_bid_price >", value, "productBidPrice");
            return (Criteria) this;
        }

        public Criteria andProductBidPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_bid_price >=", value, "productBidPrice");
            return (Criteria) this;
        }

        public Criteria andProductBidPriceLessThan(BigDecimal value) {
            addCriterion("product_bid_price <", value, "productBidPrice");
            return (Criteria) this;
        }

        public Criteria andProductBidPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_bid_price <=", value, "productBidPrice");
            return (Criteria) this;
        }

        public Criteria andProductBidPriceIn(List<BigDecimal> values) {
            addCriterion("product_bid_price in", values, "productBidPrice");
            return (Criteria) this;
        }

        public Criteria andProductBidPriceNotIn(List<BigDecimal> values) {
            addCriterion("product_bid_price not in", values, "productBidPrice");
            return (Criteria) this;
        }

        public Criteria andProductBidPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_bid_price between", value1, value2, "productBidPrice");
            return (Criteria) this;
        }

        public Criteria andProductBidPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_bid_price not between", value1, value2, "productBidPrice");
            return (Criteria) this;
        }

        public Criteria andProductSalePriceIsNull() {
            addCriterion("product_sale_price is null");
            return (Criteria) this;
        }

        public Criteria andProductSalePriceIsNotNull() {
            addCriterion("product_sale_price is not null");
            return (Criteria) this;
        }

        public Criteria andProductSalePriceEqualTo(BigDecimal value) {
            addCriterion("product_sale_price =", value, "productSalePrice");
            return (Criteria) this;
        }

        public Criteria andProductSalePriceNotEqualTo(BigDecimal value) {
            addCriterion("product_sale_price <>", value, "productSalePrice");
            return (Criteria) this;
        }

        public Criteria andProductSalePriceGreaterThan(BigDecimal value) {
            addCriterion("product_sale_price >", value, "productSalePrice");
            return (Criteria) this;
        }

        public Criteria andProductSalePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_sale_price >=", value, "productSalePrice");
            return (Criteria) this;
        }

        public Criteria andProductSalePriceLessThan(BigDecimal value) {
            addCriterion("product_sale_price <", value, "productSalePrice");
            return (Criteria) this;
        }

        public Criteria andProductSalePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_sale_price <=", value, "productSalePrice");
            return (Criteria) this;
        }

        public Criteria andProductSalePriceIn(List<BigDecimal> values) {
            addCriterion("product_sale_price in", values, "productSalePrice");
            return (Criteria) this;
        }

        public Criteria andProductSalePriceNotIn(List<BigDecimal> values) {
            addCriterion("product_sale_price not in", values, "productSalePrice");
            return (Criteria) this;
        }

        public Criteria andProductSalePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_sale_price between", value1, value2, "productSalePrice");
            return (Criteria) this;
        }

        public Criteria andProductSalePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_sale_price not between", value1, value2, "productSalePrice");
            return (Criteria) this;
        }

        public Criteria andProductInterfacePriceIsNull() {
            addCriterion("product_interface_price is null");
            return (Criteria) this;
        }

        public Criteria andProductInterfacePriceIsNotNull() {
            addCriterion("product_interface_price is not null");
            return (Criteria) this;
        }

        public Criteria andProductInterfacePriceEqualTo(BigDecimal value) {
            addCriterion("product_interface_price =", value, "productInterfacePrice");
            return (Criteria) this;
        }

        public Criteria andProductInterfacePriceNotEqualTo(BigDecimal value) {
            addCriterion("product_interface_price <>", value, "productInterfacePrice");
            return (Criteria) this;
        }

        public Criteria andProductInterfacePriceGreaterThan(BigDecimal value) {
            addCriterion("product_interface_price >", value, "productInterfacePrice");
            return (Criteria) this;
        }

        public Criteria andProductInterfacePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_interface_price >=", value, "productInterfacePrice");
            return (Criteria) this;
        }

        public Criteria andProductInterfacePriceLessThan(BigDecimal value) {
            addCriterion("product_interface_price <", value, "productInterfacePrice");
            return (Criteria) this;
        }

        public Criteria andProductInterfacePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_interface_price <=", value, "productInterfacePrice");
            return (Criteria) this;
        }

        public Criteria andProductInterfacePriceIn(List<BigDecimal> values) {
            addCriterion("product_interface_price in", values, "productInterfacePrice");
            return (Criteria) this;
        }

        public Criteria andProductInterfacePriceNotIn(List<BigDecimal> values) {
            addCriterion("product_interface_price not in", values, "productInterfacePrice");
            return (Criteria) this;
        }

        public Criteria andProductInterfacePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_interface_price between", value1, value2, "productInterfacePrice");
            return (Criteria) this;
        }

        public Criteria andProductInterfacePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_interface_price not between", value1, value2, "productInterfacePrice");
            return (Criteria) this;
        }

        public Criteria andProductAmountIsNull() {
            addCriterion("product_amount is null");
            return (Criteria) this;
        }

        public Criteria andProductAmountIsNotNull() {
            addCriterion("product_amount is not null");
            return (Criteria) this;
        }

        public Criteria andProductAmountEqualTo(Integer value) {
            addCriterion("product_amount =", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotEqualTo(Integer value) {
            addCriterion("product_amount <>", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountGreaterThan(Integer value) {
            addCriterion("product_amount >", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_amount >=", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountLessThan(Integer value) {
            addCriterion("product_amount <", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountLessThanOrEqualTo(Integer value) {
            addCriterion("product_amount <=", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountIn(List<Integer> values) {
            addCriterion("product_amount in", values, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotIn(List<Integer> values) {
            addCriterion("product_amount not in", values, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountBetween(Integer value1, Integer value2) {
            addCriterion("product_amount between", value1, value2, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("product_amount not between", value1, value2, "productAmount");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIsNull() {
            addCriterion("total_money is null");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIsNotNull() {
            addCriterion("total_money is not null");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyEqualTo(BigDecimal value) {
            addCriterion("total_money =", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyNotEqualTo(BigDecimal value) {
            addCriterion("total_money <>", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyGreaterThan(BigDecimal value) {
            addCriterion("total_money >", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_money >=", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyLessThan(BigDecimal value) {
            addCriterion("total_money <", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_money <=", value, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyIn(List<BigDecimal> values) {
            addCriterion("total_money in", values, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyNotIn(List<BigDecimal> values) {
            addCriterion("total_money not in", values, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_money between", value1, value2, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andTotalMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_money not between", value1, value2, "totalMoney");
            return (Criteria) this;
        }

        public Criteria andInterfaceTypeIsNull() {
            addCriterion("interface_type is null");
            return (Criteria) this;
        }

        public Criteria andInterfaceTypeIsNotNull() {
            addCriterion("interface_type is not null");
            return (Criteria) this;
        }

        public Criteria andInterfaceTypeEqualTo(Byte value) {
            addCriterion("interface_type =", value, "interfaceType");
            return (Criteria) this;
        }

        public Criteria andInterfaceTypeNotEqualTo(Byte value) {
            addCriterion("interface_type <>", value, "interfaceType");
            return (Criteria) this;
        }

        public Criteria andInterfaceTypeGreaterThan(Byte value) {
            addCriterion("interface_type >", value, "interfaceType");
            return (Criteria) this;
        }

        public Criteria andInterfaceTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("interface_type >=", value, "interfaceType");
            return (Criteria) this;
        }

        public Criteria andInterfaceTypeLessThan(Byte value) {
            addCriterion("interface_type <", value, "interfaceType");
            return (Criteria) this;
        }

        public Criteria andInterfaceTypeLessThanOrEqualTo(Byte value) {
            addCriterion("interface_type <=", value, "interfaceType");
            return (Criteria) this;
        }

        public Criteria andInterfaceTypeIn(List<Byte> values) {
            addCriterion("interface_type in", values, "interfaceType");
            return (Criteria) this;
        }

        public Criteria andInterfaceTypeNotIn(List<Byte> values) {
            addCriterion("interface_type not in", values, "interfaceType");
            return (Criteria) this;
        }

        public Criteria andInterfaceTypeBetween(Byte value1, Byte value2) {
            addCriterion("interface_type between", value1, value2, "interfaceType");
            return (Criteria) this;
        }

        public Criteria andInterfaceTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("interface_type not between", value1, value2, "interfaceType");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoIsNull() {
            addCriterion("interface_order_no is null");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoIsNotNull() {
            addCriterion("interface_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoEqualTo(String value) {
            addCriterion("interface_order_no =", value, "interfaceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoNotEqualTo(String value) {
            addCriterion("interface_order_no <>", value, "interfaceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoGreaterThan(String value) {
            addCriterion("interface_order_no >", value, "interfaceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("interface_order_no >=", value, "interfaceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoLessThan(String value) {
            addCriterion("interface_order_no <", value, "interfaceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoLessThanOrEqualTo(String value) {
            addCriterion("interface_order_no <=", value, "interfaceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoLike(String value) {
            addCriterion("interface_order_no like", value, "interfaceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoNotLike(String value) {
            addCriterion("interface_order_no not like", value, "interfaceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoIn(List<String> values) {
            addCriterion("interface_order_no in", values, "interfaceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoNotIn(List<String> values) {
            addCriterion("interface_order_no not in", values, "interfaceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoBetween(String value1, String value2) {
            addCriterion("interface_order_no between", value1, value2, "interfaceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoNotBetween(String value1, String value2) {
            addCriterion("interface_order_no not between", value1, value2, "interfaceOrderNo");
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

        public Criteria andStreamTypeIsNull() {
            addCriterion("stream_type is null");
            return (Criteria) this;
        }

        public Criteria andStreamTypeIsNotNull() {
            addCriterion("stream_type is not null");
            return (Criteria) this;
        }

        public Criteria andStreamTypeEqualTo(Byte value) {
            addCriterion("stream_type =", value, "streamType");
            return (Criteria) this;
        }

        public Criteria andStreamTypeNotEqualTo(Byte value) {
            addCriterion("stream_type <>", value, "streamType");
            return (Criteria) this;
        }

        public Criteria andStreamTypeGreaterThan(Byte value) {
            addCriterion("stream_type >", value, "streamType");
            return (Criteria) this;
        }

        public Criteria andStreamTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("stream_type >=", value, "streamType");
            return (Criteria) this;
        }

        public Criteria andStreamTypeLessThan(Byte value) {
            addCriterion("stream_type <", value, "streamType");
            return (Criteria) this;
        }

        public Criteria andStreamTypeLessThanOrEqualTo(Byte value) {
            addCriterion("stream_type <=", value, "streamType");
            return (Criteria) this;
        }

        public Criteria andStreamTypeIn(List<Byte> values) {
            addCriterion("stream_type in", values, "streamType");
            return (Criteria) this;
        }

        public Criteria andStreamTypeNotIn(List<Byte> values) {
            addCriterion("stream_type not in", values, "streamType");
            return (Criteria) this;
        }

        public Criteria andStreamTypeBetween(Byte value1, Byte value2) {
            addCriterion("stream_type between", value1, value2, "streamType");
            return (Criteria) this;
        }

        public Criteria andStreamTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("stream_type not between", value1, value2, "streamType");
            return (Criteria) this;
        }

        public Criteria andNewMoneyIsNull() {
            addCriterion("new_money is null");
            return (Criteria) this;
        }

        public Criteria andNewMoneyIsNotNull() {
            addCriterion("new_money is not null");
            return (Criteria) this;
        }

        public Criteria andNewMoneyEqualTo(BigDecimal value) {
            addCriterion("new_money =", value, "newMoney");
            return (Criteria) this;
        }

        public Criteria andNewMoneyNotEqualTo(BigDecimal value) {
            addCriterion("new_money <>", value, "newMoney");
            return (Criteria) this;
        }

        public Criteria andNewMoneyGreaterThan(BigDecimal value) {
            addCriterion("new_money >", value, "newMoney");
            return (Criteria) this;
        }

        public Criteria andNewMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("new_money >=", value, "newMoney");
            return (Criteria) this;
        }

        public Criteria andNewMoneyLessThan(BigDecimal value) {
            addCriterion("new_money <", value, "newMoney");
            return (Criteria) this;
        }

        public Criteria andNewMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("new_money <=", value, "newMoney");
            return (Criteria) this;
        }

        public Criteria andNewMoneyIn(List<BigDecimal> values) {
            addCriterion("new_money in", values, "newMoney");
            return (Criteria) this;
        }

        public Criteria andNewMoneyNotIn(List<BigDecimal> values) {
            addCriterion("new_money not in", values, "newMoney");
            return (Criteria) this;
        }

        public Criteria andNewMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("new_money between", value1, value2, "newMoney");
            return (Criteria) this;
        }

        public Criteria andNewMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("new_money not between", value1, value2, "newMoney");
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

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Byte value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Byte value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Byte value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Byte value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Byte value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Byte> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Byte> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Byte value1, Byte value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Byte value1, Byte value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
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

        public Criteria andConsumerOrderNoIsNull() {
            addCriterion("consumer_order_no is null");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoIsNotNull() {
            addCriterion("consumer_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoEqualTo(String value) {
            addCriterion("consumer_order_no =", value, "consumerOrderNo");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoNotEqualTo(String value) {
            addCriterion("consumer_order_no <>", value, "consumerOrderNo");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoGreaterThan(String value) {
            addCriterion("consumer_order_no >", value, "consumerOrderNo");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("consumer_order_no >=", value, "consumerOrderNo");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoLessThan(String value) {
            addCriterion("consumer_order_no <", value, "consumerOrderNo");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoLessThanOrEqualTo(String value) {
            addCriterion("consumer_order_no <=", value, "consumerOrderNo");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoLike(String value) {
            addCriterion("consumer_order_no like", value, "consumerOrderNo");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoNotLike(String value) {
            addCriterion("consumer_order_no not like", value, "consumerOrderNo");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoIn(List<String> values) {
            addCriterion("consumer_order_no in", values, "consumerOrderNo");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoNotIn(List<String> values) {
            addCriterion("consumer_order_no not in", values, "consumerOrderNo");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoBetween(String value1, String value2) {
            addCriterion("consumer_order_no between", value1, value2, "consumerOrderNo");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoNotBetween(String value1, String value2) {
            addCriterion("consumer_order_no not between", value1, value2, "consumerOrderNo");
            return (Criteria) this;
        }

        public Criteria andStreamNoLikeInsensitive(String value) {
            addCriterion("upper(stream_no) like", value.toUpperCase(), "streamNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLikeInsensitive(String value) {
            addCriterion("upper(company_name) like", value.toUpperCase(), "companyName");
            return (Criteria) this;
        }

        public Criteria andMerchNoLikeInsensitive(String value) {
            addCriterion("upper(merch_no) like", value.toUpperCase(), "merchNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLikeInsensitive(String value) {
            addCriterion("upper(order_no) like", value.toUpperCase(), "orderNo");
            return (Criteria) this;
        }

        public Criteria andProductNameLikeInsensitive(String value) {
            addCriterion("upper(product_name) like", value.toUpperCase(), "productName");
            return (Criteria) this;
        }

        public Criteria andInterfaceOrderNoLikeInsensitive(String value) {
            addCriterion("upper(interface_order_no) like", value.toUpperCase(), "interfaceOrderNo");
            return (Criteria) this;
        }

        public Criteria andMemoLikeInsensitive(String value) {
            addCriterion("upper(memo) like", value.toUpperCase(), "memo");
            return (Criteria) this;
        }

        public Criteria andConsumerOrderNoLikeInsensitive(String value) {
            addCriterion("upper(consumer_order_no) like", value.toUpperCase(), "consumerOrderNo");
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