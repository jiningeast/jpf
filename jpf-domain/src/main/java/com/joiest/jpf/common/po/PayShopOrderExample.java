package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayShopOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayShopOrderExample() {
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

        public Criteria andChargeTypeIsNull() {
            addCriterion("charge_type is null");
            return (Criteria) this;
        }

        public Criteria andChargeTypeIsNotNull() {
            addCriterion("charge_type is not null");
            return (Criteria) this;
        }

        public Criteria andChargeTypeEqualTo(Byte value) {
            addCriterion("charge_type =", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotEqualTo(Byte value) {
            addCriterion("charge_type <>", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeGreaterThan(Byte value) {
            addCriterion("charge_type >", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("charge_type >=", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeLessThan(Byte value) {
            addCriterion("charge_type <", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeLessThanOrEqualTo(Byte value) {
            addCriterion("charge_type <=", value, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeIn(List<Byte> values) {
            addCriterion("charge_type in", values, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotIn(List<Byte> values) {
            addCriterion("charge_type not in", values, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeBetween(Byte value1, Byte value2) {
            addCriterion("charge_type between", value1, value2, "chargeType");
            return (Criteria) this;
        }

        public Criteria andChargeTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("charge_type not between", value1, value2, "chargeType");
            return (Criteria) this;
        }

        public Criteria andStockCardIdIsNull() {
            addCriterion("stock_card_id is null");
            return (Criteria) this;
        }

        public Criteria andStockCardIdIsNotNull() {
            addCriterion("stock_card_id is not null");
            return (Criteria) this;
        }

        public Criteria andStockCardIdEqualTo(String value) {
            addCriterion("stock_card_id =", value, "stockCardId");
            return (Criteria) this;
        }

        public Criteria andStockCardIdNotEqualTo(String value) {
            addCriterion("stock_card_id <>", value, "stockCardId");
            return (Criteria) this;
        }

        public Criteria andStockCardIdGreaterThan(String value) {
            addCriterion("stock_card_id >", value, "stockCardId");
            return (Criteria) this;
        }

        public Criteria andStockCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("stock_card_id >=", value, "stockCardId");
            return (Criteria) this;
        }

        public Criteria andStockCardIdLessThan(String value) {
            addCriterion("stock_card_id <", value, "stockCardId");
            return (Criteria) this;
        }

        public Criteria andStockCardIdLessThanOrEqualTo(String value) {
            addCriterion("stock_card_id <=", value, "stockCardId");
            return (Criteria) this;
        }

        public Criteria andStockCardIdIn(List<String> values) {
            addCriterion("stock_card_id in", values, "stockCardId");
            return (Criteria) this;
        }

        public Criteria andStockCardIdNotIn(List<String> values) {
            addCriterion("stock_card_id not in", values, "stockCardId");
            return (Criteria) this;
        }

        public Criteria andStockCardIdBetween(String value1, String value2) {
            addCriterion("stock_card_id between", value1, value2, "stockCardId");
            return (Criteria) this;
        }

        public Criteria andStockCardIdNotBetween(String value1, String value2) {
            addCriterion("stock_card_id not between", value1, value2, "stockCardId");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(Byte value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(Byte value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(Byte value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(Byte value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(Byte value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<Byte> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<Byte> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(Byte value1, Byte value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoIsNull() {
            addCriterion("foreign_order_no is null");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoIsNotNull() {
            addCriterion("foreign_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoEqualTo(String value) {
            addCriterion("foreign_order_no =", value, "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoNotEqualTo(String value) {
            addCriterion("foreign_order_no <>", value, "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoGreaterThan(String value) {
            addCriterion("foreign_order_no >", value, "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("foreign_order_no >=", value, "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoLessThan(String value) {
            addCriterion("foreign_order_no <", value, "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoLessThanOrEqualTo(String value) {
            addCriterion("foreign_order_no <=", value, "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoLike(String value) {
            addCriterion("foreign_order_no like", value, "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoNotLike(String value) {
            addCriterion("foreign_order_no not like", value, "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoIn(List<String> values) {
            addCriterion("foreign_order_no in", values, "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoNotIn(List<String> values) {
            addCriterion("foreign_order_no not in", values, "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoBetween(String value1, String value2) {
            addCriterion("foreign_order_no between", value1, value2, "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoNotBetween(String value1, String value2) {
            addCriterion("foreign_order_no not between", value1, value2, "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andRequestedContentIsNull() {
            addCriterion("requested_content is null");
            return (Criteria) this;
        }

        public Criteria andRequestedContentIsNotNull() {
            addCriterion("requested_content is not null");
            return (Criteria) this;
        }

        public Criteria andRequestedContentEqualTo(String value) {
            addCriterion("requested_content =", value, "requestedContent");
            return (Criteria) this;
        }

        public Criteria andRequestedContentNotEqualTo(String value) {
            addCriterion("requested_content <>", value, "requestedContent");
            return (Criteria) this;
        }

        public Criteria andRequestedContentGreaterThan(String value) {
            addCriterion("requested_content >", value, "requestedContent");
            return (Criteria) this;
        }

        public Criteria andRequestedContentGreaterThanOrEqualTo(String value) {
            addCriterion("requested_content >=", value, "requestedContent");
            return (Criteria) this;
        }

        public Criteria andRequestedContentLessThan(String value) {
            addCriterion("requested_content <", value, "requestedContent");
            return (Criteria) this;
        }

        public Criteria andRequestedContentLessThanOrEqualTo(String value) {
            addCriterion("requested_content <=", value, "requestedContent");
            return (Criteria) this;
        }

        public Criteria andRequestedContentLike(String value) {
            addCriterion("requested_content like", value, "requestedContent");
            return (Criteria) this;
        }

        public Criteria andRequestedContentNotLike(String value) {
            addCriterion("requested_content not like", value, "requestedContent");
            return (Criteria) this;
        }

        public Criteria andRequestedContentIn(List<String> values) {
            addCriterion("requested_content in", values, "requestedContent");
            return (Criteria) this;
        }

        public Criteria andRequestedContentNotIn(List<String> values) {
            addCriterion("requested_content not in", values, "requestedContent");
            return (Criteria) this;
        }

        public Criteria andRequestedContentBetween(String value1, String value2) {
            addCriterion("requested_content between", value1, value2, "requestedContent");
            return (Criteria) this;
        }

        public Criteria andRequestedContentNotBetween(String value1, String value2) {
            addCriterion("requested_content not between", value1, value2, "requestedContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentIsNull() {
            addCriterion("foreign_request_content is null");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentIsNotNull() {
            addCriterion("foreign_request_content is not null");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentEqualTo(String value) {
            addCriterion("foreign_request_content =", value, "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentNotEqualTo(String value) {
            addCriterion("foreign_request_content <>", value, "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentGreaterThan(String value) {
            addCriterion("foreign_request_content >", value, "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentGreaterThanOrEqualTo(String value) {
            addCriterion("foreign_request_content >=", value, "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentLessThan(String value) {
            addCriterion("foreign_request_content <", value, "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentLessThanOrEqualTo(String value) {
            addCriterion("foreign_request_content <=", value, "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentLike(String value) {
            addCriterion("foreign_request_content like", value, "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentNotLike(String value) {
            addCriterion("foreign_request_content not like", value, "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentIn(List<String> values) {
            addCriterion("foreign_request_content in", values, "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentNotIn(List<String> values) {
            addCriterion("foreign_request_content not in", values, "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentBetween(String value1, String value2) {
            addCriterion("foreign_request_content between", value1, value2, "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentNotBetween(String value1, String value2) {
            addCriterion("foreign_request_content not between", value1, value2, "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentIsNull() {
            addCriterion("foreign_response_content is null");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentIsNotNull() {
            addCriterion("foreign_response_content is not null");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentEqualTo(String value) {
            addCriterion("foreign_response_content =", value, "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentNotEqualTo(String value) {
            addCriterion("foreign_response_content <>", value, "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentGreaterThan(String value) {
            addCriterion("foreign_response_content >", value, "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentGreaterThanOrEqualTo(String value) {
            addCriterion("foreign_response_content >=", value, "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentLessThan(String value) {
            addCriterion("foreign_response_content <", value, "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentLessThanOrEqualTo(String value) {
            addCriterion("foreign_response_content <=", value, "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentLike(String value) {
            addCriterion("foreign_response_content like", value, "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentNotLike(String value) {
            addCriterion("foreign_response_content not like", value, "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentIn(List<String> values) {
            addCriterion("foreign_response_content in", values, "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentNotIn(List<String> values) {
            addCriterion("foreign_response_content not in", values, "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentBetween(String value1, String value2) {
            addCriterion("foreign_response_content between", value1, value2, "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentNotBetween(String value1, String value2) {
            addCriterion("foreign_response_content not between", value1, value2, "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(String value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(String value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(String value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(String value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<String> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<String> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(String value1, String value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(String value1, String value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
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

        public Criteria andProductMoneyIsNull() {
            addCriterion("product_money is null");
            return (Criteria) this;
        }

        public Criteria andProductMoneyIsNotNull() {
            addCriterion("product_money is not null");
            return (Criteria) this;
        }

        public Criteria andProductMoneyEqualTo(BigDecimal value) {
            addCriterion("product_money =", value, "productMoney");
            return (Criteria) this;
        }

        public Criteria andProductMoneyNotEqualTo(BigDecimal value) {
            addCriterion("product_money <>", value, "productMoney");
            return (Criteria) this;
        }

        public Criteria andProductMoneyGreaterThan(BigDecimal value) {
            addCriterion("product_money >", value, "productMoney");
            return (Criteria) this;
        }

        public Criteria andProductMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_money >=", value, "productMoney");
            return (Criteria) this;
        }

        public Criteria andProductMoneyLessThan(BigDecimal value) {
            addCriterion("product_money <", value, "productMoney");
            return (Criteria) this;
        }

        public Criteria andProductMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_money <=", value, "productMoney");
            return (Criteria) this;
        }

        public Criteria andProductMoneyIn(List<BigDecimal> values) {
            addCriterion("product_money in", values, "productMoney");
            return (Criteria) this;
        }

        public Criteria andProductMoneyNotIn(List<BigDecimal> values) {
            addCriterion("product_money not in", values, "productMoney");
            return (Criteria) this;
        }

        public Criteria andProductMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_money between", value1, value2, "productMoney");
            return (Criteria) this;
        }

        public Criteria andProductMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_money not between", value1, value2, "productMoney");
            return (Criteria) this;
        }

        public Criteria andProductDouIsNull() {
            addCriterion("product_dou is null");
            return (Criteria) this;
        }

        public Criteria andProductDouIsNotNull() {
            addCriterion("product_dou is not null");
            return (Criteria) this;
        }

        public Criteria andProductDouEqualTo(Integer value) {
            addCriterion("product_dou =", value, "productDou");
            return (Criteria) this;
        }

        public Criteria andProductDouNotEqualTo(Integer value) {
            addCriterion("product_dou <>", value, "productDou");
            return (Criteria) this;
        }

        public Criteria andProductDouGreaterThan(Integer value) {
            addCriterion("product_dou >", value, "productDou");
            return (Criteria) this;
        }

        public Criteria andProductDouGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_dou >=", value, "productDou");
            return (Criteria) this;
        }

        public Criteria andProductDouLessThan(Integer value) {
            addCriterion("product_dou <", value, "productDou");
            return (Criteria) this;
        }

        public Criteria andProductDouLessThanOrEqualTo(Integer value) {
            addCriterion("product_dou <=", value, "productDou");
            return (Criteria) this;
        }

        public Criteria andProductDouIn(List<Integer> values) {
            addCriterion("product_dou in", values, "productDou");
            return (Criteria) this;
        }

        public Criteria andProductDouNotIn(List<Integer> values) {
            addCriterion("product_dou not in", values, "productDou");
            return (Criteria) this;
        }

        public Criteria andProductDouBetween(Integer value1, Integer value2) {
            addCriterion("product_dou between", value1, value2, "productDou");
            return (Criteria) this;
        }

        public Criteria andProductDouNotBetween(Integer value1, Integer value2) {
            addCriterion("product_dou not between", value1, value2, "productDou");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdIsNull() {
            addCriterion("product_info_id is null");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdIsNotNull() {
            addCriterion("product_info_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdEqualTo(Integer value) {
            addCriterion("product_info_id =", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdNotEqualTo(Integer value) {
            addCriterion("product_info_id <>", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdGreaterThan(Integer value) {
            addCriterion("product_info_id >", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_info_id >=", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdLessThan(Integer value) {
            addCriterion("product_info_id <", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_info_id <=", value, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdIn(List<Integer> values) {
            addCriterion("product_info_id in", values, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdNotIn(List<Integer> values) {
            addCriterion("product_info_id not in", values, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("product_info_id between", value1, value2, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_info_id not between", value1, value2, "productInfoId");
            return (Criteria) this;
        }

        public Criteria andWnProductIdIsNull() {
            addCriterion("wn_product_id is null");
            return (Criteria) this;
        }

        public Criteria andWnProductIdIsNotNull() {
            addCriterion("wn_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andWnProductIdEqualTo(String value) {
            addCriterion("wn_product_id =", value, "wnProductId");
            return (Criteria) this;
        }

        public Criteria andWnProductIdNotEqualTo(String value) {
            addCriterion("wn_product_id <>", value, "wnProductId");
            return (Criteria) this;
        }

        public Criteria andWnProductIdGreaterThan(String value) {
            addCriterion("wn_product_id >", value, "wnProductId");
            return (Criteria) this;
        }

        public Criteria andWnProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("wn_product_id >=", value, "wnProductId");
            return (Criteria) this;
        }

        public Criteria andWnProductIdLessThan(String value) {
            addCriterion("wn_product_id <", value, "wnProductId");
            return (Criteria) this;
        }

        public Criteria andWnProductIdLessThanOrEqualTo(String value) {
            addCriterion("wn_product_id <=", value, "wnProductId");
            return (Criteria) this;
        }

        public Criteria andWnProductIdIn(List<String> values) {
            addCriterion("wn_product_id in", values, "wnProductId");
            return (Criteria) this;
        }

        public Criteria andWnProductIdNotIn(List<String> values) {
            addCriterion("wn_product_id not in", values, "wnProductId");
            return (Criteria) this;
        }

        public Criteria andWnProductIdBetween(String value1, String value2) {
            addCriterion("wn_product_id between", value1, value2, "wnProductId");
            return (Criteria) this;
        }

        public Criteria andWnProductIdNotBetween(String value1, String value2) {
            addCriterion("wn_product_id not between", value1, value2, "wnProductId");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
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

        public Criteria andTotalDouIsNull() {
            addCriterion("total_dou is null");
            return (Criteria) this;
        }

        public Criteria andTotalDouIsNotNull() {
            addCriterion("total_dou is not null");
            return (Criteria) this;
        }

        public Criteria andTotalDouEqualTo(Integer value) {
            addCriterion("total_dou =", value, "totalDou");
            return (Criteria) this;
        }

        public Criteria andTotalDouNotEqualTo(Integer value) {
            addCriterion("total_dou <>", value, "totalDou");
            return (Criteria) this;
        }

        public Criteria andTotalDouGreaterThan(Integer value) {
            addCriterion("total_dou >", value, "totalDou");
            return (Criteria) this;
        }

        public Criteria andTotalDouGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_dou >=", value, "totalDou");
            return (Criteria) this;
        }

        public Criteria andTotalDouLessThan(Integer value) {
            addCriterion("total_dou <", value, "totalDou");
            return (Criteria) this;
        }

        public Criteria andTotalDouLessThanOrEqualTo(Integer value) {
            addCriterion("total_dou <=", value, "totalDou");
            return (Criteria) this;
        }

        public Criteria andTotalDouIn(List<Integer> values) {
            addCriterion("total_dou in", values, "totalDou");
            return (Criteria) this;
        }

        public Criteria andTotalDouNotIn(List<Integer> values) {
            addCriterion("total_dou not in", values, "totalDou");
            return (Criteria) this;
        }

        public Criteria andTotalDouBetween(Integer value1, Integer value2) {
            addCriterion("total_dou between", value1, value2, "totalDou");
            return (Criteria) this;
        }

        public Criteria andTotalDouNotBetween(Integer value1, Integer value2) {
            addCriterion("total_dou not between", value1, value2, "totalDou");
            return (Criteria) this;
        }

        public Criteria andPayWayIsNull() {
            addCriterion("pay_way is null");
            return (Criteria) this;
        }

        public Criteria andPayWayIsNotNull() {
            addCriterion("pay_way is not null");
            return (Criteria) this;
        }

        public Criteria andPayWayEqualTo(Byte value) {
            addCriterion("pay_way =", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotEqualTo(Byte value) {
            addCriterion("pay_way <>", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayGreaterThan(Byte value) {
            addCriterion("pay_way >", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_way >=", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLessThan(Byte value) {
            addCriterion("pay_way <", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayLessThanOrEqualTo(Byte value) {
            addCriterion("pay_way <=", value, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayIn(List<Byte> values) {
            addCriterion("pay_way in", values, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotIn(List<Byte> values) {
            addCriterion("pay_way not in", values, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayBetween(Byte value1, Byte value2) {
            addCriterion("pay_way between", value1, value2, "payWay");
            return (Criteria) this;
        }

        public Criteria andPayWayNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_way not between", value1, value2, "payWay");
            return (Criteria) this;
        }

        public Criteria andChargeNoIsNull() {
            addCriterion("charge_no is null");
            return (Criteria) this;
        }

        public Criteria andChargeNoIsNotNull() {
            addCriterion("charge_no is not null");
            return (Criteria) this;
        }

        public Criteria andChargeNoEqualTo(String value) {
            addCriterion("charge_no =", value, "chargeNo");
            return (Criteria) this;
        }

        public Criteria andChargeNoNotEqualTo(String value) {
            addCriterion("charge_no <>", value, "chargeNo");
            return (Criteria) this;
        }

        public Criteria andChargeNoGreaterThan(String value) {
            addCriterion("charge_no >", value, "chargeNo");
            return (Criteria) this;
        }

        public Criteria andChargeNoGreaterThanOrEqualTo(String value) {
            addCriterion("charge_no >=", value, "chargeNo");
            return (Criteria) this;
        }

        public Criteria andChargeNoLessThan(String value) {
            addCriterion("charge_no <", value, "chargeNo");
            return (Criteria) this;
        }

        public Criteria andChargeNoLessThanOrEqualTo(String value) {
            addCriterion("charge_no <=", value, "chargeNo");
            return (Criteria) this;
        }

        public Criteria andChargeNoLike(String value) {
            addCriterion("charge_no like", value, "chargeNo");
            return (Criteria) this;
        }

        public Criteria andChargeNoNotLike(String value) {
            addCriterion("charge_no not like", value, "chargeNo");
            return (Criteria) this;
        }

        public Criteria andChargeNoIn(List<String> values) {
            addCriterion("charge_no in", values, "chargeNo");
            return (Criteria) this;
        }

        public Criteria andChargeNoNotIn(List<String> values) {
            addCriterion("charge_no not in", values, "chargeNo");
            return (Criteria) this;
        }

        public Criteria andChargeNoBetween(String value1, String value2) {
            addCriterion("charge_no between", value1, value2, "chargeNo");
            return (Criteria) this;
        }

        public Criteria andChargeNoNotBetween(String value1, String value2) {
            addCriterion("charge_no not between", value1, value2, "chargeNo");
            return (Criteria) this;
        }

        public Criteria andCouponDetailIsNull() {
            addCriterion("coupon_detail is null");
            return (Criteria) this;
        }

        public Criteria andCouponDetailIsNotNull() {
            addCriterion("coupon_detail is not null");
            return (Criteria) this;
        }

        public Criteria andCouponDetailEqualTo(String value) {
            addCriterion("coupon_detail =", value, "couponDetail");
            return (Criteria) this;
        }

        public Criteria andCouponDetailNotEqualTo(String value) {
            addCriterion("coupon_detail <>", value, "couponDetail");
            return (Criteria) this;
        }

        public Criteria andCouponDetailGreaterThan(String value) {
            addCriterion("coupon_detail >", value, "couponDetail");
            return (Criteria) this;
        }

        public Criteria andCouponDetailGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_detail >=", value, "couponDetail");
            return (Criteria) this;
        }

        public Criteria andCouponDetailLessThan(String value) {
            addCriterion("coupon_detail <", value, "couponDetail");
            return (Criteria) this;
        }

        public Criteria andCouponDetailLessThanOrEqualTo(String value) {
            addCriterion("coupon_detail <=", value, "couponDetail");
            return (Criteria) this;
        }

        public Criteria andCouponDetailLike(String value) {
            addCriterion("coupon_detail like", value, "couponDetail");
            return (Criteria) this;
        }

        public Criteria andCouponDetailNotLike(String value) {
            addCriterion("coupon_detail not like", value, "couponDetail");
            return (Criteria) this;
        }

        public Criteria andCouponDetailIn(List<String> values) {
            addCriterion("coupon_detail in", values, "couponDetail");
            return (Criteria) this;
        }

        public Criteria andCouponDetailNotIn(List<String> values) {
            addCriterion("coupon_detail not in", values, "couponDetail");
            return (Criteria) this;
        }

        public Criteria andCouponDetailBetween(String value1, String value2) {
            addCriterion("coupon_detail between", value1, value2, "couponDetail");
            return (Criteria) this;
        }

        public Criteria andCouponDetailNotBetween(String value1, String value2) {
            addCriterion("coupon_detail not between", value1, value2, "couponDetail");
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

        public Criteria andRechargeStatusIsNull() {
            addCriterion("recharge_status is null");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusIsNotNull() {
            addCriterion("recharge_status is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusEqualTo(String value) {
            addCriterion("recharge_status =", value, "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusNotEqualTo(String value) {
            addCriterion("recharge_status <>", value, "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusGreaterThan(String value) {
            addCriterion("recharge_status >", value, "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("recharge_status >=", value, "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusLessThan(String value) {
            addCriterion("recharge_status <", value, "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusLessThanOrEqualTo(String value) {
            addCriterion("recharge_status <=", value, "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusLike(String value) {
            addCriterion("recharge_status like", value, "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusNotLike(String value) {
            addCriterion("recharge_status not like", value, "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusIn(List<String> values) {
            addCriterion("recharge_status in", values, "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusNotIn(List<String> values) {
            addCriterion("recharge_status not in", values, "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusBetween(String value1, String value2) {
            addCriterion("recharge_status between", value1, value2, "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusNotBetween(String value1, String value2) {
            addCriterion("recharge_status not between", value1, value2, "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeIsNull() {
            addCriterion("recharge_time is null");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeIsNotNull() {
            addCriterion("recharge_time is not null");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeEqualTo(Date value) {
            addCriterion("recharge_time =", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeNotEqualTo(Date value) {
            addCriterion("recharge_time <>", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeGreaterThan(Date value) {
            addCriterion("recharge_time >", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("recharge_time >=", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeLessThan(Date value) {
            addCriterion("recharge_time <", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeLessThanOrEqualTo(Date value) {
            addCriterion("recharge_time <=", value, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeIn(List<Date> values) {
            addCriterion("recharge_time in", values, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeNotIn(List<Date> values) {
            addCriterion("recharge_time not in", values, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeBetween(Date value1, Date value2) {
            addCriterion("recharge_time between", value1, value2, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andRechargeTimeNotBetween(Date value1, Date value2) {
            addCriterion("recharge_time not between", value1, value2, "rechargeTime");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdIsNull() {
            addCriterion("coupon_active_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdIsNotNull() {
            addCriterion("coupon_active_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdEqualTo(String value) {
            addCriterion("coupon_active_id =", value, "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdNotEqualTo(String value) {
            addCriterion("coupon_active_id <>", value, "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdGreaterThan(String value) {
            addCriterion("coupon_active_id >", value, "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_active_id >=", value, "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdLessThan(String value) {
            addCriterion("coupon_active_id <", value, "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdLessThanOrEqualTo(String value) {
            addCriterion("coupon_active_id <=", value, "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdLike(String value) {
            addCriterion("coupon_active_id like", value, "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdNotLike(String value) {
            addCriterion("coupon_active_id not like", value, "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdIn(List<String> values) {
            addCriterion("coupon_active_id in", values, "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdNotIn(List<String> values) {
            addCriterion("coupon_active_id not in", values, "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdBetween(String value1, String value2) {
            addCriterion("coupon_active_id between", value1, value2, "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdNotBetween(String value1, String value2) {
            addCriterion("coupon_active_id not between", value1, value2, "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeIsNull() {
            addCriterion("receive_type is null");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeIsNotNull() {
            addCriterion("receive_type is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeEqualTo(Byte value) {
            addCriterion("receive_type =", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeNotEqualTo(Byte value) {
            addCriterion("receive_type <>", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeGreaterThan(Byte value) {
            addCriterion("receive_type >", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("receive_type >=", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeLessThan(Byte value) {
            addCriterion("receive_type <", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeLessThanOrEqualTo(Byte value) {
            addCriterion("receive_type <=", value, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeIn(List<Byte> values) {
            addCriterion("receive_type in", values, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeNotIn(List<Byte> values) {
            addCriterion("receive_type not in", values, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeBetween(Byte value1, Byte value2) {
            addCriterion("receive_type between", value1, value2, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("receive_type not between", value1, value2, "receiveType");
            return (Criteria) this;
        }

        public Criteria andReceiveValueIsNull() {
            addCriterion("receive_value is null");
            return (Criteria) this;
        }

        public Criteria andReceiveValueIsNotNull() {
            addCriterion("receive_value is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveValueEqualTo(String value) {
            addCriterion("receive_value =", value, "receiveValue");
            return (Criteria) this;
        }

        public Criteria andReceiveValueNotEqualTo(String value) {
            addCriterion("receive_value <>", value, "receiveValue");
            return (Criteria) this;
        }

        public Criteria andReceiveValueGreaterThan(String value) {
            addCriterion("receive_value >", value, "receiveValue");
            return (Criteria) this;
        }

        public Criteria andReceiveValueGreaterThanOrEqualTo(String value) {
            addCriterion("receive_value >=", value, "receiveValue");
            return (Criteria) this;
        }

        public Criteria andReceiveValueLessThan(String value) {
            addCriterion("receive_value <", value, "receiveValue");
            return (Criteria) this;
        }

        public Criteria andReceiveValueLessThanOrEqualTo(String value) {
            addCriterion("receive_value <=", value, "receiveValue");
            return (Criteria) this;
        }

        public Criteria andReceiveValueLike(String value) {
            addCriterion("receive_value like", value, "receiveValue");
            return (Criteria) this;
        }

        public Criteria andReceiveValueNotLike(String value) {
            addCriterion("receive_value not like", value, "receiveValue");
            return (Criteria) this;
        }

        public Criteria andReceiveValueIn(List<String> values) {
            addCriterion("receive_value in", values, "receiveValue");
            return (Criteria) this;
        }

        public Criteria andReceiveValueNotIn(List<String> values) {
            addCriterion("receive_value not in", values, "receiveValue");
            return (Criteria) this;
        }

        public Criteria andReceiveValueBetween(String value1, String value2) {
            addCriterion("receive_value between", value1, value2, "receiveValue");
            return (Criteria) this;
        }

        public Criteria andReceiveValueNotBetween(String value1, String value2) {
            addCriterion("receive_value not between", value1, value2, "receiveValue");
            return (Criteria) this;
        }

        public Criteria andOssUrlIsNull() {
            addCriterion("oss_url is null");
            return (Criteria) this;
        }

        public Criteria andOssUrlIsNotNull() {
            addCriterion("oss_url is not null");
            return (Criteria) this;
        }

        public Criteria andOssUrlEqualTo(String value) {
            addCriterion("oss_url =", value, "ossUrl");
            return (Criteria) this;
        }

        public Criteria andOssUrlNotEqualTo(String value) {
            addCriterion("oss_url <>", value, "ossUrl");
            return (Criteria) this;
        }

        public Criteria andOssUrlGreaterThan(String value) {
            addCriterion("oss_url >", value, "ossUrl");
            return (Criteria) this;
        }

        public Criteria andOssUrlGreaterThanOrEqualTo(String value) {
            addCriterion("oss_url >=", value, "ossUrl");
            return (Criteria) this;
        }

        public Criteria andOssUrlLessThan(String value) {
            addCriterion("oss_url <", value, "ossUrl");
            return (Criteria) this;
        }

        public Criteria andOssUrlLessThanOrEqualTo(String value) {
            addCriterion("oss_url <=", value, "ossUrl");
            return (Criteria) this;
        }

        public Criteria andOssUrlLike(String value) {
            addCriterion("oss_url like", value, "ossUrl");
            return (Criteria) this;
        }

        public Criteria andOssUrlNotLike(String value) {
            addCriterion("oss_url not like", value, "ossUrl");
            return (Criteria) this;
        }

        public Criteria andOssUrlIn(List<String> values) {
            addCriterion("oss_url in", values, "ossUrl");
            return (Criteria) this;
        }

        public Criteria andOssUrlNotIn(List<String> values) {
            addCriterion("oss_url not in", values, "ossUrl");
            return (Criteria) this;
        }

        public Criteria andOssUrlBetween(String value1, String value2) {
            addCriterion("oss_url between", value1, value2, "ossUrl");
            return (Criteria) this;
        }

        public Criteria andOssUrlNotBetween(String value1, String value2) {
            addCriterion("oss_url not between", value1, value2, "ossUrl");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Byte value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Byte value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Byte value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Byte value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Byte value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Byte> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Byte> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Byte value1, Byte value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Byte value1, Byte value2) {
            addCriterion("source not between", value1, value2, "source");
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

        public Criteria andBargainOrderIdIsNull() {
            addCriterion("bargain_order_id is null");
            return (Criteria) this;
        }

        public Criteria andBargainOrderIdIsNotNull() {
            addCriterion("bargain_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andBargainOrderIdEqualTo(String value) {
            addCriterion("bargain_order_id =", value, "bargainOrderId");
            return (Criteria) this;
        }

        public Criteria andBargainOrderIdNotEqualTo(String value) {
            addCriterion("bargain_order_id <>", value, "bargainOrderId");
            return (Criteria) this;
        }

        public Criteria andBargainOrderIdGreaterThan(String value) {
            addCriterion("bargain_order_id >", value, "bargainOrderId");
            return (Criteria) this;
        }

        public Criteria andBargainOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("bargain_order_id >=", value, "bargainOrderId");
            return (Criteria) this;
        }

        public Criteria andBargainOrderIdLessThan(String value) {
            addCriterion("bargain_order_id <", value, "bargainOrderId");
            return (Criteria) this;
        }

        public Criteria andBargainOrderIdLessThanOrEqualTo(String value) {
            addCriterion("bargain_order_id <=", value, "bargainOrderId");
            return (Criteria) this;
        }

        public Criteria andBargainOrderIdIn(List<String> values) {
            addCriterion("bargain_order_id in", values, "bargainOrderId");
            return (Criteria) this;
        }

        public Criteria andBargainOrderIdNotIn(List<String> values) {
            addCriterion("bargain_order_id not in", values, "bargainOrderId");
            return (Criteria) this;
        }

        public Criteria andBargainOrderIdBetween(String value1, String value2) {
            addCriterion("bargain_order_id between", value1, value2, "bargainOrderId");
            return (Criteria) this;
        }

        public Criteria andBargainOrderIdNotBetween(String value1, String value2) {
            addCriterion("bargain_order_id not between", value1, value2, "bargainOrderId");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoIsNull() {
            addCriterion("bargain_order_no is null");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoIsNotNull() {
            addCriterion("bargain_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoEqualTo(String value) {
            addCriterion("bargain_order_no =", value, "bargainOrderNo");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoNotEqualTo(String value) {
            addCriterion("bargain_order_no <>", value, "bargainOrderNo");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoGreaterThan(String value) {
            addCriterion("bargain_order_no >", value, "bargainOrderNo");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("bargain_order_no >=", value, "bargainOrderNo");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoLessThan(String value) {
            addCriterion("bargain_order_no <", value, "bargainOrderNo");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoLessThanOrEqualTo(String value) {
            addCriterion("bargain_order_no <=", value, "bargainOrderNo");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoLike(String value) {
            addCriterion("bargain_order_no like", value, "bargainOrderNo");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoNotLike(String value) {
            addCriterion("bargain_order_no not like", value, "bargainOrderNo");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoIn(List<String> values) {
            addCriterion("bargain_order_no in", values, "bargainOrderNo");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoNotIn(List<String> values) {
            addCriterion("bargain_order_no not in", values, "bargainOrderNo");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoBetween(String value1, String value2) {
            addCriterion("bargain_order_no between", value1, value2, "bargainOrderNo");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoNotBetween(String value1, String value2) {
            addCriterion("bargain_order_no not between", value1, value2, "bargainOrderNo");
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

        public Criteria andCouponDetailSaleIsNull() {
            addCriterion("coupon_detail_sale is null");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleIsNotNull() {
            addCriterion("coupon_detail_sale is not null");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleEqualTo(String value) {
            addCriterion("coupon_detail_sale =", value, "couponDetailSale");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleNotEqualTo(String value) {
            addCriterion("coupon_detail_sale <>", value, "couponDetailSale");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleGreaterThan(String value) {
            addCriterion("coupon_detail_sale >", value, "couponDetailSale");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_detail_sale >=", value, "couponDetailSale");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleLessThan(String value) {
            addCriterion("coupon_detail_sale <", value, "couponDetailSale");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleLessThanOrEqualTo(String value) {
            addCriterion("coupon_detail_sale <=", value, "couponDetailSale");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleLike(String value) {
            addCriterion("coupon_detail_sale like", value, "couponDetailSale");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleNotLike(String value) {
            addCriterion("coupon_detail_sale not like", value, "couponDetailSale");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleIn(List<String> values) {
            addCriterion("coupon_detail_sale in", values, "couponDetailSale");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleNotIn(List<String> values) {
            addCriterion("coupon_detail_sale not in", values, "couponDetailSale");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleBetween(String value1, String value2) {
            addCriterion("coupon_detail_sale between", value1, value2, "couponDetailSale");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleNotBetween(String value1, String value2) {
            addCriterion("coupon_detail_sale not between", value1, value2, "couponDetailSale");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNull() {
            addCriterion("product_type is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNotNull() {
            addCriterion("product_type is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeEqualTo(Integer value) {
            addCriterion("product_type =", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotEqualTo(Integer value) {
            addCriterion("product_type <>", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThan(Integer value) {
            addCriterion("product_type >", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type >=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThan(Integer value) {
            addCriterion("product_type <", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThanOrEqualTo(Integer value) {
            addCriterion("product_type <=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIn(List<Integer> values) {
            addCriterion("product_type in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotIn(List<Integer> values) {
            addCriterion("product_type not in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeBetween(Integer value1, Integer value2) {
            addCriterion("product_type between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("product_type not between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andOrderNoLikeInsensitive(String value) {
            addCriterion("upper(order_no) like", value.toUpperCase(), "orderNo");
            return (Criteria) this;
        }

        public Criteria andForeignOrderNoLikeInsensitive(String value) {
            addCriterion("upper(foreign_order_no) like", value.toUpperCase(), "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andRequestedContentLikeInsensitive(String value) {
            addCriterion("upper(requested_content) like", value.toUpperCase(), "requestedContent");
            return (Criteria) this;
        }

        public Criteria andForeignRequestContentLikeInsensitive(String value) {
            addCriterion("upper(foreign_request_content) like", value.toUpperCase(), "foreignRequestContent");
            return (Criteria) this;
        }

        public Criteria andForeignResponseContentLikeInsensitive(String value) {
            addCriterion("upper(foreign_response_content) like", value.toUpperCase(), "foreignResponseContent");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLikeInsensitive(String value) {
            addCriterion("upper(customer_name) like", value.toUpperCase(), "customerName");
            return (Criteria) this;
        }

        public Criteria andProductNameLikeInsensitive(String value) {
            addCriterion("upper(product_name) like", value.toUpperCase(), "productName");
            return (Criteria) this;
        }

        public Criteria andChargeNoLikeInsensitive(String value) {
            addCriterion("upper(charge_no) like", value.toUpperCase(), "chargeNo");
            return (Criteria) this;
        }

        public Criteria andCouponDetailLikeInsensitive(String value) {
            addCriterion("upper(coupon_detail) like", value.toUpperCase(), "couponDetail");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusLikeInsensitive(String value) {
            addCriterion("upper(recharge_status) like", value.toUpperCase(), "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andCouponActiveIdLikeInsensitive(String value) {
            addCriterion("upper(coupon_active_id) like", value.toUpperCase(), "couponActiveId");
            return (Criteria) this;
        }

        public Criteria andReceiveValueLikeInsensitive(String value) {
            addCriterion("upper(receive_value) like", value.toUpperCase(), "receiveValue");
            return (Criteria) this;
        }

        public Criteria andOssUrlLikeInsensitive(String value) {
            addCriterion("upper(oss_url) like", value.toUpperCase(), "ossUrl");
            return (Criteria) this;
        }

        public Criteria andBargainOrderNoLikeInsensitive(String value) {
            addCriterion("upper(bargain_order_no) like", value.toUpperCase(), "bargainOrderNo");
            return (Criteria) this;
        }

        public Criteria andCouponDetailSaleLikeInsensitive(String value) {
            addCriterion("upper(coupon_detail_sale) like", value.toUpperCase(), "couponDetailSale");
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