package com.joiest.jpf.common.custom;

import com.joiest.jpf.common.custom.PayShopOrderCustomExample;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayShopOrderCustomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<PayShopOrderCustomExample.Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayShopOrderCustomExample() {
        oredCriteria = new ArrayList<PayShopOrderCustomExample.Criteria>();
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
    public List<PayShopOrderCustomExample.Criteria> getOredCriteria() {
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
     * @param criteria
     */
  /*  public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }*/


    /**
     * 新加的 and
     * @param criteria
     */
    public void  and(Criteria criteria) {
        oredCriteria.add(criteria);
    }
    /**
     *
     */
    public PayShopOrderCustomExample.Criteria or() {
        PayShopOrderCustomExample.Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     */
    public PayShopOrderCustomExample.Criteria createCriteria() {
        PayShopOrderCustomExample.Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *
     */
    protected PayShopOrderCustomExample.Criteria createCriteriaInternal() {
        PayShopOrderCustomExample.Criteria criteria = new PayShopOrderCustomExample.Criteria();
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
        protected List<PayShopOrderCustomExample.Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<PayShopOrderCustomExample.Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<PayShopOrderCustomExample.Criterion> getAllCriteria() {
            return criteria;
        }

        public List<PayShopOrderCustomExample.Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new PayShopOrderCustomExample.Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new PayShopOrderCustomExample.Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new PayShopOrderCustomExample.Criterion(condition, value1, value2));
        }

        public PayShopOrderCustomExample.Criteria andIdIsNull() {
            addCriterion("id is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeTypeIsNull() {
            addCriterion("charge_type is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeTypeIsNotNull() {
            addCriterion("charge_type is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeTypeEqualTo(Byte value) {
            addCriterion("charge_type =", value, "chargeType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeTypeNotEqualTo(Byte value) {
            addCriterion("charge_type <>", value, "chargeType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeTypeGreaterThan(Byte value) {
            addCriterion("charge_type >", value, "chargeType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("charge_type >=", value, "chargeType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeTypeLessThan(Byte value) {
            addCriterion("charge_type <", value, "chargeType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeTypeLessThanOrEqualTo(Byte value) {
            addCriterion("charge_type <=", value, "chargeType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeTypeIn(List<Byte> values) {
            addCriterion("charge_type in", values, "chargeType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeTypeNotIn(List<Byte> values) {
            addCriterion("charge_type not in", values, "chargeType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeTypeBetween(Byte value1, Byte value2) {
            addCriterion("charge_type between", value1, value2, "chargeType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("charge_type not between", value1, value2, "chargeType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStockCardIdIsNull() {
            addCriterion("stock_card_id is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStockCardIdIsNotNull() {
            addCriterion("stock_card_id is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStockCardIdEqualTo(String value) {
            addCriterion("stock_card_id =", value, "stockCardId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStockCardIdNotEqualTo(String value) {
            addCriterion("stock_card_id <>", value, "stockCardId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStockCardIdGreaterThan(String value) {
            addCriterion("stock_card_id >", value, "stockCardId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStockCardIdGreaterThanOrEqualTo(String value) {
            addCriterion("stock_card_id >=", value, "stockCardId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStockCardIdLessThan(String value) {
            addCriterion("stock_card_id <", value, "stockCardId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStockCardIdLessThanOrEqualTo(String value) {
            addCriterion("stock_card_id <=", value, "stockCardId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStockCardIdIn(List<String> values) {
            addCriterion("stock_card_id in", values, "stockCardId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStockCardIdNotIn(List<String> values) {
            addCriterion("stock_card_id not in", values, "stockCardId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStockCardIdBetween(String value1, String value2) {
            addCriterion("stock_card_id between", value1, value2, "stockCardId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStockCardIdNotBetween(String value1, String value2) {
            addCriterion("stock_card_id not between", value1, value2, "stockCardId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderTypeEqualTo(Byte value) {
            addCriterion("order_type =", value, "orderType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderTypeNotEqualTo(Byte value) {
            addCriterion("order_type <>", value, "orderType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderTypeGreaterThan(Byte value) {
            addCriterion("order_type >", value, "orderType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_type >=", value, "orderType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderTypeLessThan(Byte value) {
            addCriterion("order_type <", value, "orderType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderTypeLessThanOrEqualTo(Byte value) {
            addCriterion("order_type <=", value, "orderType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderTypeIn(List<Byte> values) {
            addCriterion("order_type in", values, "orderType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderTypeNotIn(List<Byte> values) {
            addCriterion("order_type not in", values, "orderType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderTypeBetween(Byte value1, Byte value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoIsNull() {
            addCriterion("foreign_order_no is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoIsNotNull() {
            addCriterion("foreign_order_no is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoEqualTo(String value) {
            addCriterion("foreign_order_no =", value, "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoNotEqualTo(String value) {
            addCriterion("foreign_order_no <>", value, "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoGreaterThan(String value) {
            addCriterion("foreign_order_no >", value, "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("foreign_order_no >=", value, "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoLessThan(String value) {
            addCriterion("foreign_order_no <", value, "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoLessThanOrEqualTo(String value) {
            addCriterion("foreign_order_no <=", value, "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoLike(String value) {
            addCriterion("foreign_order_no like", value, "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoNotLike(String value) {
            addCriterion("foreign_order_no not like", value, "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoIn(List<String> values) {
            addCriterion("foreign_order_no in", values, "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoNotIn(List<String> values) {
            addCriterion("foreign_order_no not in", values, "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoBetween(String value1, String value2) {
            addCriterion("foreign_order_no between", value1, value2, "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoNotBetween(String value1, String value2) {
            addCriterion("foreign_order_no not between", value1, value2, "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentIsNull() {
            addCriterion("requested_content is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentIsNotNull() {
            addCriterion("requested_content is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentEqualTo(String value) {
            addCriterion("requested_content =", value, "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentNotEqualTo(String value) {
            addCriterion("requested_content <>", value, "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentGreaterThan(String value) {
            addCriterion("requested_content >", value, "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentGreaterThanOrEqualTo(String value) {
            addCriterion("requested_content >=", value, "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentLessThan(String value) {
            addCriterion("requested_content <", value, "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentLessThanOrEqualTo(String value) {
            addCriterion("requested_content <=", value, "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentLike(String value) {
            addCriterion("requested_content like", value, "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentNotLike(String value) {
            addCriterion("requested_content not like", value, "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentIn(List<String> values) {
            addCriterion("requested_content in", values, "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentNotIn(List<String> values) {
            addCriterion("requested_content not in", values, "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentBetween(String value1, String value2) {
            addCriterion("requested_content between", value1, value2, "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentNotBetween(String value1, String value2) {
            addCriterion("requested_content not between", value1, value2, "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentIsNull() {
            addCriterion("foreign_request_content is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentIsNotNull() {
            addCriterion("foreign_request_content is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentEqualTo(String value) {
            addCriterion("foreign_request_content =", value, "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentNotEqualTo(String value) {
            addCriterion("foreign_request_content <>", value, "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentGreaterThan(String value) {
            addCriterion("foreign_request_content >", value, "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentGreaterThanOrEqualTo(String value) {
            addCriterion("foreign_request_content >=", value, "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentLessThan(String value) {
            addCriterion("foreign_request_content <", value, "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentLessThanOrEqualTo(String value) {
            addCriterion("foreign_request_content <=", value, "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentLike(String value) {
            addCriterion("foreign_request_content like", value, "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentNotLike(String value) {
            addCriterion("foreign_request_content not like", value, "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentIn(List<String> values) {
            addCriterion("foreign_request_content in", values, "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentNotIn(List<String> values) {
            addCriterion("foreign_request_content not in", values, "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentBetween(String value1, String value2) {
            addCriterion("foreign_request_content between", value1, value2, "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentNotBetween(String value1, String value2) {
            addCriterion("foreign_request_content not between", value1, value2, "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentIsNull() {
            addCriterion("foreign_response_content is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentIsNotNull() {
            addCriterion("foreign_response_content is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentEqualTo(String value) {
            addCriterion("foreign_response_content =", value, "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentNotEqualTo(String value) {
            addCriterion("foreign_response_content <>", value, "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentGreaterThan(String value) {
            addCriterion("foreign_response_content >", value, "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentGreaterThanOrEqualTo(String value) {
            addCriterion("foreign_response_content >=", value, "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentLessThan(String value) {
            addCriterion("foreign_response_content <", value, "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentLessThanOrEqualTo(String value) {
            addCriterion("foreign_response_content <=", value, "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentLike(String value) {
            addCriterion("foreign_response_content like", value, "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentNotLike(String value) {
            addCriterion("foreign_response_content not like", value, "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentIn(List<String> values) {
            addCriterion("foreign_response_content in", values, "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentNotIn(List<String> values) {
            addCriterion("foreign_response_content not in", values, "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentBetween(String value1, String value2) {
            addCriterion("foreign_response_content between", value1, value2, "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentNotBetween(String value1, String value2) {
            addCriterion("foreign_response_content not between", value1, value2, "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerIdEqualTo(String value) {
            addCriterion("customer_id =", value, "customerId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerIdNotEqualTo(String value) {
            addCriterion("customer_id <>", value, "customerId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerIdGreaterThan(String value) {
            addCriterion("customer_id >", value, "customerId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("customer_id >=", value, "customerId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerIdLessThan(String value) {
            addCriterion("customer_id <", value, "customerId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("customer_id <=", value, "customerId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerIdIn(List<String> values) {
            addCriterion("customer_id in", values, "customerId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerIdNotIn(List<String> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerIdBetween(String value1, String value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerIdNotBetween(String value1, String value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductIdEqualTo(String value) {
            addCriterion("product_id =", value, "productId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductIdNotEqualTo(String value) {
            addCriterion("product_id <>", value, "productId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductIdGreaterThan(String value) {
            addCriterion("product_id >", value, "productId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_id >=", value, "productId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductIdLessThan(String value) {
            addCriterion("product_id <", value, "productId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("product_id <=", value, "productId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductIdIn(List<String> values) {
            addCriterion("product_id in", values, "productId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductIdNotIn(List<String> values) {
            addCriterion("product_id not in", values, "productId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductMoneyIsNull() {
            addCriterion("product_money is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductMoneyIsNotNull() {
            addCriterion("product_money is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductMoneyEqualTo(BigDecimal value) {
            addCriterion("product_money =", value, "productMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductMoneyNotEqualTo(BigDecimal value) {
            addCriterion("product_money <>", value, "productMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductMoneyGreaterThan(BigDecimal value) {
            addCriterion("product_money >", value, "productMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_money >=", value, "productMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductMoneyLessThan(BigDecimal value) {
            addCriterion("product_money <", value, "productMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_money <=", value, "productMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductMoneyIn(List<BigDecimal> values) {
            addCriterion("product_money in", values, "productMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductMoneyNotIn(List<BigDecimal> values) {
            addCriterion("product_money not in", values, "productMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_money between", value1, value2, "productMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_money not between", value1, value2, "productMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductDouIsNull() {
            addCriterion("product_dou is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductDouIsNotNull() {
            addCriterion("product_dou is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductDouEqualTo(Integer value) {
            addCriterion("product_dou =", value, "productDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductDouNotEqualTo(Integer value) {
            addCriterion("product_dou <>", value, "productDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductDouGreaterThan(Integer value) {
            addCriterion("product_dou >", value, "productDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductDouGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_dou >=", value, "productDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductDouLessThan(Integer value) {
            addCriterion("product_dou <", value, "productDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductDouLessThanOrEqualTo(Integer value) {
            addCriterion("product_dou <=", value, "productDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductDouIn(List<Integer> values) {
            addCriterion("product_dou in", values, "productDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductDouNotIn(List<Integer> values) {
            addCriterion("product_dou not in", values, "productDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductDouBetween(Integer value1, Integer value2) {
            addCriterion("product_dou between", value1, value2, "productDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductDouNotBetween(Integer value1, Integer value2) {
            addCriterion("product_dou not between", value1, value2, "productDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductInfoIdIsNull() {
            addCriterion("product_info_id is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductInfoIdIsNotNull() {
            addCriterion("product_info_id is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductInfoIdEqualTo(Integer value) {
            addCriterion("product_info_id =", value, "productInfoId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductInfoIdNotEqualTo(Integer value) {
            addCriterion("product_info_id <>", value, "productInfoId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductInfoIdGreaterThan(Integer value) {
            addCriterion("product_info_id >", value, "productInfoId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductInfoIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_info_id >=", value, "productInfoId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductInfoIdLessThan(Integer value) {
            addCriterion("product_info_id <", value, "productInfoId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductInfoIdLessThanOrEqualTo(Integer value) {
            addCriterion("product_info_id <=", value, "productInfoId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductInfoIdIn(List<Integer> values) {
            addCriterion("product_info_id in", values, "productInfoId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductInfoIdNotIn(List<Integer> values) {
            addCriterion("product_info_id not in", values, "productInfoId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductInfoIdBetween(Integer value1, Integer value2) {
            addCriterion("product_info_id between", value1, value2, "productInfoId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductInfoIdNotBetween(Integer value1, Integer value2) {
            addCriterion("product_info_id not between", value1, value2, "productInfoId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalMoneyIsNull() {
            addCriterion("total_money is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalMoneyIsNotNull() {
            addCriterion("total_money is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalMoneyEqualTo(BigDecimal value) {
            addCriterion("total_money =", value, "totalMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalMoneyNotEqualTo(BigDecimal value) {
            addCriterion("total_money <>", value, "totalMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalMoneyGreaterThan(BigDecimal value) {
            addCriterion("total_money >", value, "totalMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_money >=", value, "totalMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalMoneyLessThan(BigDecimal value) {
            addCriterion("total_money <", value, "totalMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_money <=", value, "totalMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalMoneyIn(List<BigDecimal> values) {
            addCriterion("total_money in", values, "totalMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalMoneyNotIn(List<BigDecimal> values) {
            addCriterion("total_money not in", values, "totalMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_money between", value1, value2, "totalMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_money not between", value1, value2, "totalMoney");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalDouIsNull() {
            addCriterion("total_dou is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalDouIsNotNull() {
            addCriterion("total_dou is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalDouEqualTo(Integer value) {
            addCriterion("total_dou =", value, "totalDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalDouNotEqualTo(Integer value) {
            addCriterion("total_dou <>", value, "totalDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalDouGreaterThan(Integer value) {
            addCriterion("total_dou >", value, "totalDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalDouGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_dou >=", value, "totalDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalDouLessThan(Integer value) {
            addCriterion("total_dou <", value, "totalDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalDouLessThanOrEqualTo(Integer value) {
            addCriterion("total_dou <=", value, "totalDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalDouIn(List<Integer> values) {
            addCriterion("total_dou in", values, "totalDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalDouNotIn(List<Integer> values) {
            addCriterion("total_dou not in", values, "totalDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalDouBetween(Integer value1, Integer value2) {
            addCriterion("total_dou between", value1, value2, "totalDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andTotalDouNotBetween(Integer value1, Integer value2) {
            addCriterion("total_dou not between", value1, value2, "totalDou");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPayWayIsNull() {
            addCriterion("pay_way is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPayWayIsNotNull() {
            addCriterion("pay_way is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPayWayEqualTo(Byte value) {
            addCriterion("pay_way =", value, "payWay");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPayWayNotEqualTo(Byte value) {
            addCriterion("pay_way <>", value, "payWay");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPayWayGreaterThan(Byte value) {
            addCriterion("pay_way >", value, "payWay");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPayWayGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_way >=", value, "payWay");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPayWayLessThan(Byte value) {
            addCriterion("pay_way <", value, "payWay");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPayWayLessThanOrEqualTo(Byte value) {
            addCriterion("pay_way <=", value, "payWay");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPayWayIn(List<Byte> values) {
            addCriterion("pay_way in", values, "payWay");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPayWayNotIn(List<Byte> values) {
            addCriterion("pay_way not in", values, "payWay");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPayWayBetween(Byte value1, Byte value2) {
            addCriterion("pay_way between", value1, value2, "payWay");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPayWayNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_way not between", value1, value2, "payWay");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoIsNull() {
            addCriterion("charge_no is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoIsNotNull() {
            addCriterion("charge_no is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoEqualTo(String value) {
            addCriterion("charge_no =", value, "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoNotEqualTo(String value) {
            addCriterion("charge_no <>", value, "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoGreaterThan(String value) {
            addCriterion("charge_no >", value, "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoGreaterThanOrEqualTo(String value) {
            addCriterion("charge_no >=", value, "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoLessThan(String value) {
            addCriterion("charge_no <", value, "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoLessThanOrEqualTo(String value) {
            addCriterion("charge_no <=", value, "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoLike(String value) {
            addCriterion("charge_no like", value, "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoNotLike(String value) {
            addCriterion("charge_no not like", value, "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoIn(List<String> values) {
            addCriterion("charge_no in", values, "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoNotIn(List<String> values) {
            addCriterion("charge_no not in", values, "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoBetween(String value1, String value2) {
            addCriterion("charge_no between", value1, value2, "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoNotBetween(String value1, String value2) {
            addCriterion("charge_no not between", value1, value2, "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailIsNull() {
            addCriterion("coupon_detail is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailIsNotNull() {
            addCriterion("coupon_detail is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailEqualTo(String value) {
            addCriterion("coupon_detail =", value, "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailNotEqualTo(String value) {
            addCriterion("coupon_detail <>", value, "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailGreaterThan(String value) {
            addCriterion("coupon_detail >", value, "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_detail >=", value, "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailLessThan(String value) {
            addCriterion("coupon_detail <", value, "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailLessThanOrEqualTo(String value) {
            addCriterion("coupon_detail <=", value, "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailLike(String value) {
            addCriterion("coupon_detail like", value, "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailNotLike(String value) {
            addCriterion("coupon_detail not like", value, "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailIn(List<String> values) {
            addCriterion("coupon_detail in", values, "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailNotIn(List<String> values) {
            addCriterion("coupon_detail not in", values, "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailBetween(String value1, String value2) {
            addCriterion("coupon_detail between", value1, value2, "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailNotBetween(String value1, String value2) {
            addCriterion("coupon_detail not between", value1, value2, "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusIsNull() {
            addCriterion("recharge_status is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusIsNotNull() {
            addCriterion("recharge_status is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusEqualTo(String value) {
            addCriterion("recharge_status =", value, "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusNotEqualTo(String value) {
            addCriterion("recharge_status <>", value, "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusGreaterThan(String value) {
            addCriterion("recharge_status >", value, "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusGreaterThanOrEqualTo(String value) {
            addCriterion("recharge_status >=", value, "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusLessThan(String value) {
            addCriterion("recharge_status <", value, "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusLessThanOrEqualTo(String value) {
            addCriterion("recharge_status <=", value, "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusLike(String value) {
            addCriterion("recharge_status like", value, "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusNotLike(String value) {
            addCriterion("recharge_status not like", value, "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusIn(List<String> values) {
            addCriterion("recharge_status in", values, "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusNotIn(List<String> values) {
            addCriterion("recharge_status not in", values, "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusBetween(String value1, String value2) {
            addCriterion("recharge_status between", value1, value2, "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusNotBetween(String value1, String value2) {
            addCriterion("recharge_status not between", value1, value2, "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeTimeIsNull() {
            addCriterion("recharge_time is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeTimeIsNotNull() {
            addCriterion("recharge_time is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeTimeEqualTo(Date value) {
            addCriterion("recharge_time =", value, "rechargeTime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeTimeNotEqualTo(Date value) {
            addCriterion("recharge_time <>", value, "rechargeTime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeTimeGreaterThan(Date value) {
            addCriterion("recharge_time >", value, "rechargeTime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("recharge_time >=", value, "rechargeTime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeTimeLessThan(Date value) {
            addCriterion("recharge_time <", value, "rechargeTime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeTimeLessThanOrEqualTo(Date value) {
            addCriterion("recharge_time <=", value, "rechargeTime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeTimeIn(List<Date> values) {
            addCriterion("recharge_time in", values, "rechargeTime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeTimeNotIn(List<Date> values) {
            addCriterion("recharge_time not in", values, "rechargeTime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeTimeBetween(Date value1, Date value2) {
            addCriterion("recharge_time between", value1, value2, "rechargeTime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeTimeNotBetween(Date value1, Date value2) {
            addCriterion("recharge_time not between", value1, value2, "rechargeTime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdIsNull() {
            addCriterion("coupon_active_id is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdIsNotNull() {
            addCriterion("coupon_active_id is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdEqualTo(String value) {
            addCriterion("coupon_active_id =", value, "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdNotEqualTo(String value) {
            addCriterion("coupon_active_id <>", value, "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdGreaterThan(String value) {
            addCriterion("coupon_active_id >", value, "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_active_id >=", value, "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdLessThan(String value) {
            addCriterion("coupon_active_id <", value, "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdLessThanOrEqualTo(String value) {
            addCriterion("coupon_active_id <=", value, "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdLike(String value) {
            addCriterion("coupon_active_id like", value, "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdNotLike(String value) {
            addCriterion("coupon_active_id not like", value, "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdIn(List<String> values) {
            addCriterion("coupon_active_id in", values, "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdNotIn(List<String> values) {
            addCriterion("coupon_active_id not in", values, "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdBetween(String value1, String value2) {
            addCriterion("coupon_active_id between", value1, value2, "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdNotBetween(String value1, String value2) {
            addCriterion("coupon_active_id not between", value1, value2, "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveTypeIsNull() {
            addCriterion("receive_type is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveTypeIsNotNull() {
            addCriterion("receive_type is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveTypeEqualTo(Byte value) {
            addCriterion("receive_type =", value, "receiveType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveTypeNotEqualTo(Byte value) {
            addCriterion("receive_type <>", value, "receiveType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveTypeGreaterThan(Byte value) {
            addCriterion("receive_type >", value, "receiveType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("receive_type >=", value, "receiveType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveTypeLessThan(Byte value) {
            addCriterion("receive_type <", value, "receiveType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveTypeLessThanOrEqualTo(Byte value) {
            addCriterion("receive_type <=", value, "receiveType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveTypeIn(List<Byte> values) {
            addCriterion("receive_type in", values, "receiveType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveTypeNotIn(List<Byte> values) {
            addCriterion("receive_type not in", values, "receiveType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveTypeBetween(Byte value1, Byte value2) {
            addCriterion("receive_type between", value1, value2, "receiveType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("receive_type not between", value1, value2, "receiveType");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueIsNull() {
            addCriterion("receive_value is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueIsNotNull() {
            addCriterion("receive_value is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueEqualTo(String value) {
            addCriterion("receive_value =", value, "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueNotEqualTo(String value) {
            addCriterion("receive_value <>", value, "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueGreaterThan(String value) {
            addCriterion("receive_value >", value, "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueGreaterThanOrEqualTo(String value) {
            addCriterion("receive_value >=", value, "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueLessThan(String value) {
            addCriterion("receive_value <", value, "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueLessThanOrEqualTo(String value) {
            addCriterion("receive_value <=", value, "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueLike(String value) {
            addCriterion("receive_value like", value, "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueNotLike(String value) {
            addCriterion("receive_value not like", value, "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueIn(List<String> values) {
            addCriterion("receive_value in", values, "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueNotIn(List<String> values) {
            addCriterion("receive_value not in", values, "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueBetween(String value1, String value2) {
            addCriterion("receive_value between", value1, value2, "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueNotBetween(String value1, String value2) {
            addCriterion("receive_value not between", value1, value2, "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlIsNull() {
            addCriterion("oss_url is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlIsNotNull() {
            addCriterion("oss_url is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlEqualTo(String value) {
            addCriterion("oss_url =", value, "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlNotEqualTo(String value) {
            addCriterion("oss_url <>", value, "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlGreaterThan(String value) {
            addCriterion("oss_url >", value, "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlGreaterThanOrEqualTo(String value) {
            addCriterion("oss_url >=", value, "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlLessThan(String value) {
            addCriterion("oss_url <", value, "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlLessThanOrEqualTo(String value) {
            addCriterion("oss_url <=", value, "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlLike(String value) {
            addCriterion("oss_url like", value, "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlNotLike(String value) {
            addCriterion("oss_url not like", value, "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlIn(List<String> values) {
            addCriterion("oss_url in", values, "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlNotIn(List<String> values) {
            addCriterion("oss_url not in", values, "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlBetween(String value1, String value2) {
            addCriterion("oss_url between", value1, value2, "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlNotBetween(String value1, String value2) {
            addCriterion("oss_url not between", value1, value2, "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andSourceEqualTo(Byte value) {
            addCriterion("source =", value, "source");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andSourceNotEqualTo(Byte value) {
            addCriterion("source <>", value, "source");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andSourceGreaterThan(Byte value) {
            addCriterion("source >", value, "source");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andSourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("source >=", value, "source");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andSourceLessThan(Byte value) {
            addCriterion("source <", value, "source");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andSourceLessThanOrEqualTo(Byte value) {
            addCriterion("source <=", value, "source");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andSourceIn(List<Byte> values) {
            addCriterion("source in", values, "source");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andSourceNotIn(List<Byte> values) {
            addCriterion("source not in", values, "source");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andSourceBetween(Byte value1, Byte value2) {
            addCriterion("source between", value1, value2, "source");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andSourceNotBetween(Byte value1, Byte value2) {
            addCriterion("source not between", value1, value2, "source");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderIdIsNull() {
            addCriterion("bargain_order_id is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderIdIsNotNull() {
            addCriterion("bargain_order_id is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderIdEqualTo(String value) {
            addCriterion("bargain_order_id =", value, "bargainOrderId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderIdNotEqualTo(String value) {
            addCriterion("bargain_order_id <>", value, "bargainOrderId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderIdGreaterThan(String value) {
            addCriterion("bargain_order_id >", value, "bargainOrderId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("bargain_order_id >=", value, "bargainOrderId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderIdLessThan(String value) {
            addCriterion("bargain_order_id <", value, "bargainOrderId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderIdLessThanOrEqualTo(String value) {
            addCriterion("bargain_order_id <=", value, "bargainOrderId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderIdIn(List<String> values) {
            addCriterion("bargain_order_id in", values, "bargainOrderId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderIdNotIn(List<String> values) {
            addCriterion("bargain_order_id not in", values, "bargainOrderId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderIdBetween(String value1, String value2) {
            addCriterion("bargain_order_id between", value1, value2, "bargainOrderId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderIdNotBetween(String value1, String value2) {
            addCriterion("bargain_order_id not between", value1, value2, "bargainOrderId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoIsNull() {
            addCriterion("bargain_order_no is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoIsNotNull() {
            addCriterion("bargain_order_no is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoEqualTo(String value) {
            addCriterion("bargain_order_no =", value, "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoNotEqualTo(String value) {
            addCriterion("bargain_order_no <>", value, "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoGreaterThan(String value) {
            addCriterion("bargain_order_no >", value, "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("bargain_order_no >=", value, "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoLessThan(String value) {
            addCriterion("bargain_order_no <", value, "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoLessThanOrEqualTo(String value) {
            addCriterion("bargain_order_no <=", value, "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoLike(String value) {
            addCriterion("bargain_order_no like", value, "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoNotLike(String value) {
            addCriterion("bargain_order_no not like", value, "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoIn(List<String> values) {
            addCriterion("bargain_order_no in", values, "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoNotIn(List<String> values) {
            addCriterion("bargain_order_no not in", values, "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoBetween(String value1, String value2) {
            addCriterion("bargain_order_no between", value1, value2, "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoNotBetween(String value1, String value2) {
            addCriterion("bargain_order_no not between", value1, value2, "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAddtimeIsNull() {
            addCriterion("addtime is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAddtimeIsNotNull() {
            addCriterion("addtime is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAddtimeEqualTo(Date value) {
            addCriterion("addtime =", value, "addtime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("addtime <>", value, "addtime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("addtime >", value, "addtime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("addtime >=", value, "addtime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAddtimeLessThan(Date value) {
            addCriterion("addtime <", value, "addtime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("addtime <=", value, "addtime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAddtimeIn(List<Date> values) {
            addCriterion("addtime in", values, "addtime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("addtime not in", values, "addtime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("addtime between", value1, value2, "addtime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("addtime not between", value1, value2, "addtime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPaytimeIsNull() {
            addCriterion("paytime is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPaytimeIsNotNull() {
            addCriterion("paytime is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPaytimeEqualTo(Date value) {
            addCriterion("paytime =", value, "paytime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPaytimeNotEqualTo(Date value) {
            addCriterion("paytime <>", value, "paytime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPaytimeGreaterThan(Date value) {
            addCriterion("paytime >", value, "paytime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPaytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("paytime >=", value, "paytime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPaytimeLessThan(Date value) {
            addCriterion("paytime <", value, "paytime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPaytimeLessThanOrEqualTo(Date value) {
            addCriterion("paytime <=", value, "paytime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPaytimeIn(List<Date> values) {
            addCriterion("paytime in", values, "paytime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPaytimeNotIn(List<Date> values) {
            addCriterion("paytime not in", values, "paytime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPaytimeBetween(Date value1, Date value2) {
            addCriterion("paytime between", value1, value2, "paytime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andPaytimeNotBetween(Date value1, Date value2) {
            addCriterion("paytime not between", value1, value2, "paytime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOrderNoLikeInsensitive(String value) {
            addCriterion("upper(order_no) like", value.toUpperCase(), "orderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignOrderNoLikeInsensitive(String value) {
            addCriterion("upper(foreign_order_no) like", value.toUpperCase(), "foreignOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRequestedContentLikeInsensitive(String value) {
            addCriterion("upper(requested_content) like", value.toUpperCase(), "requestedContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignRequestContentLikeInsensitive(String value) {
            addCriterion("upper(foreign_request_content) like", value.toUpperCase(), "foreignRequestContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andForeignResponseContentLikeInsensitive(String value) {
            addCriterion("upper(foreign_response_content) like", value.toUpperCase(), "foreignResponseContent");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCustomerNameLikeInsensitive(String value) {
            addCriterion("upper(customer_name) like", value.toUpperCase(), "customerName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andProductNameLikeInsensitive(String value) {
            addCriterion("upper(product_name) like", value.toUpperCase(), "productName");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andChargeNoLikeInsensitive(String value) {
            addCriterion("upper(charge_no) like", value.toUpperCase(), "chargeNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponDetailLikeInsensitive(String value) {
            addCriterion("upper(coupon_detail) like", value.toUpperCase(), "couponDetail");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andRechargeStatusLikeInsensitive(String value) {
            addCriterion("upper(recharge_status) like", value.toUpperCase(), "rechargeStatus");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andCouponActiveIdLikeInsensitive(String value) {
            addCriterion("upper(coupon_active_id) like", value.toUpperCase(), "couponActiveId");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andReceiveValueLikeInsensitive(String value) {
            addCriterion("upper(receive_value) like", value.toUpperCase(), "receiveValue");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andOssUrlLikeInsensitive(String value) {
            addCriterion("upper(oss_url) like", value.toUpperCase(), "ossUrl");
            return (PayShopOrderCustomExample.Criteria) this;
        }

        public PayShopOrderCustomExample.Criteria andBargainOrderNoLikeInsensitive(String value) {
            addCriterion("upper(bargain_order_no) like", value.toUpperCase(), "bargainOrderNo");
            return (PayShopOrderCustomExample.Criteria) this;
        }
    }

    public static class Criteria extends PayShopOrderCustomExample.GeneratedCriteria {

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