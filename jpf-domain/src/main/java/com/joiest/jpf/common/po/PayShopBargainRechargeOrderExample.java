package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayShopBargainRechargeOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayShopBargainRechargeOrderExample() {
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

        public Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(Integer value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(Integer value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(Integer value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(Integer value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(Integer value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<Integer> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<Integer> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(Integer value1, Integer value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(Integer value1, Integer value2) {
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

        public Criteria andItemNameIsNull() {
            addCriterion("item_name is null");
            return (Criteria) this;
        }

        public Criteria andItemNameIsNotNull() {
            addCriterion("item_name is not null");
            return (Criteria) this;
        }

        public Criteria andItemNameEqualTo(String value) {
            addCriterion("item_name =", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotEqualTo(String value) {
            addCriterion("item_name <>", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThan(String value) {
            addCriterion("item_name >", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameGreaterThanOrEqualTo(String value) {
            addCriterion("item_name >=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThan(String value) {
            addCriterion("item_name <", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLessThanOrEqualTo(String value) {
            addCriterion("item_name <=", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameLike(String value) {
            addCriterion("item_name like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotLike(String value) {
            addCriterion("item_name not like", value, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameIn(List<String> values) {
            addCriterion("item_name in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotIn(List<String> values) {
            addCriterion("item_name not in", values, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameBetween(String value1, String value2) {
            addCriterion("item_name between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andItemNameNotBetween(String value1, String value2) {
            addCriterion("item_name not between", value1, value2, "itemName");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andFacePriceIsNull() {
            addCriterion("face_price is null");
            return (Criteria) this;
        }

        public Criteria andFacePriceIsNotNull() {
            addCriterion("face_price is not null");
            return (Criteria) this;
        }

        public Criteria andFacePriceEqualTo(BigDecimal value) {
            addCriterion("face_price =", value, "facePrice");
            return (Criteria) this;
        }

        public Criteria andFacePriceNotEqualTo(BigDecimal value) {
            addCriterion("face_price <>", value, "facePrice");
            return (Criteria) this;
        }

        public Criteria andFacePriceGreaterThan(BigDecimal value) {
            addCriterion("face_price >", value, "facePrice");
            return (Criteria) this;
        }

        public Criteria andFacePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("face_price >=", value, "facePrice");
            return (Criteria) this;
        }

        public Criteria andFacePriceLessThan(BigDecimal value) {
            addCriterion("face_price <", value, "facePrice");
            return (Criteria) this;
        }

        public Criteria andFacePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("face_price <=", value, "facePrice");
            return (Criteria) this;
        }

        public Criteria andFacePriceIn(List<BigDecimal> values) {
            addCriterion("face_price in", values, "facePrice");
            return (Criteria) this;
        }

        public Criteria andFacePriceNotIn(List<BigDecimal> values) {
            addCriterion("face_price not in", values, "facePrice");
            return (Criteria) this;
        }

        public Criteria andFacePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("face_price between", value1, value2, "facePrice");
            return (Criteria) this;
        }

        public Criteria andFacePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("face_price not between", value1, value2, "facePrice");
            return (Criteria) this;
        }

        public Criteria andAmtIsNull() {
            addCriterion("amt is null");
            return (Criteria) this;
        }

        public Criteria andAmtIsNotNull() {
            addCriterion("amt is not null");
            return (Criteria) this;
        }

        public Criteria andAmtEqualTo(Integer value) {
            addCriterion("amt =", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotEqualTo(Integer value) {
            addCriterion("amt <>", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtGreaterThan(Integer value) {
            addCriterion("amt >", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtGreaterThanOrEqualTo(Integer value) {
            addCriterion("amt >=", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtLessThan(Integer value) {
            addCriterion("amt <", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtLessThanOrEqualTo(Integer value) {
            addCriterion("amt <=", value, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtIn(List<Integer> values) {
            addCriterion("amt in", values, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotIn(List<Integer> values) {
            addCriterion("amt not in", values, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtBetween(Integer value1, Integer value2) {
            addCriterion("amt between", value1, value2, "amt");
            return (Criteria) this;
        }

        public Criteria andAmtNotBetween(Integer value1, Integer value2) {
            addCriterion("amt not between", value1, value2, "amt");
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

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("amount not between", value1, value2, "amount");
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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

        public Criteria andInfoStatusIsNull() {
            addCriterion("info_status is null");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIsNotNull() {
            addCriterion("info_status is not null");
            return (Criteria) this;
        }

        public Criteria andInfoStatusEqualTo(Integer value) {
            addCriterion("info_status =", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNotEqualTo(Integer value) {
            addCriterion("info_status <>", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusGreaterThan(Integer value) {
            addCriterion("info_status >", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("info_status >=", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusLessThan(Integer value) {
            addCriterion("info_status <", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusLessThanOrEqualTo(Integer value) {
            addCriterion("info_status <=", value, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusIn(List<Integer> values) {
            addCriterion("info_status in", values, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNotIn(List<Integer> values) {
            addCriterion("info_status not in", values, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusBetween(Integer value1, Integer value2) {
            addCriterion("info_status between", value1, value2, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andInfoStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("info_status not between", value1, value2, "infoStatus");
            return (Criteria) this;
        }

        public Criteria andModuleIsNull() {
            addCriterion("module is null");
            return (Criteria) this;
        }

        public Criteria andModuleIsNotNull() {
            addCriterion("module is not null");
            return (Criteria) this;
        }

        public Criteria andModuleEqualTo(String value) {
            addCriterion("module =", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotEqualTo(String value) {
            addCriterion("module <>", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleGreaterThan(String value) {
            addCriterion("module >", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleGreaterThanOrEqualTo(String value) {
            addCriterion("module >=", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLessThan(String value) {
            addCriterion("module <", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLessThanOrEqualTo(String value) {
            addCriterion("module <=", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLike(String value) {
            addCriterion("module like", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotLike(String value) {
            addCriterion("module not like", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleIn(List<String> values) {
            addCriterion("module in", values, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotIn(List<String> values) {
            addCriterion("module not in", values, "module");
            return (Criteria) this;
        }

        public Criteria andModuleBetween(String value1, String value2) {
            addCriterion("module between", value1, value2, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotBetween(String value1, String value2) {
            addCriterion("module not between", value1, value2, "module");
            return (Criteria) this;
        }

        public Criteria andPullCompanyIdIsNull() {
            addCriterion("pull_company_id is null");
            return (Criteria) this;
        }

        public Criteria andPullCompanyIdIsNotNull() {
            addCriterion("pull_company_id is not null");
            return (Criteria) this;
        }

        public Criteria andPullCompanyIdEqualTo(String value) {
            addCriterion("pull_company_id =", value, "pullCompanyId");
            return (Criteria) this;
        }

        public Criteria andPullCompanyIdNotEqualTo(String value) {
            addCriterion("pull_company_id <>", value, "pullCompanyId");
            return (Criteria) this;
        }

        public Criteria andPullCompanyIdGreaterThan(String value) {
            addCriterion("pull_company_id >", value, "pullCompanyId");
            return (Criteria) this;
        }

        public Criteria andPullCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("pull_company_id >=", value, "pullCompanyId");
            return (Criteria) this;
        }

        public Criteria andPullCompanyIdLessThan(String value) {
            addCriterion("pull_company_id <", value, "pullCompanyId");
            return (Criteria) this;
        }

        public Criteria andPullCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("pull_company_id <=", value, "pullCompanyId");
            return (Criteria) this;
        }

        public Criteria andPullCompanyIdIn(List<String> values) {
            addCriterion("pull_company_id in", values, "pullCompanyId");
            return (Criteria) this;
        }

        public Criteria andPullCompanyIdNotIn(List<String> values) {
            addCriterion("pull_company_id not in", values, "pullCompanyId");
            return (Criteria) this;
        }

        public Criteria andPullCompanyIdBetween(String value1, String value2) {
            addCriterion("pull_company_id between", value1, value2, "pullCompanyId");
            return (Criteria) this;
        }

        public Criteria andPullCompanyIdNotBetween(String value1, String value2) {
            addCriterion("pull_company_id not between", value1, value2, "pullCompanyId");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoIsNull() {
            addCriterion("pull_merch_no is null");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoIsNotNull() {
            addCriterion("pull_merch_no is not null");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoEqualTo(String value) {
            addCriterion("pull_merch_no =", value, "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoNotEqualTo(String value) {
            addCriterion("pull_merch_no <>", value, "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoGreaterThan(String value) {
            addCriterion("pull_merch_no >", value, "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoGreaterThanOrEqualTo(String value) {
            addCriterion("pull_merch_no >=", value, "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoLessThan(String value) {
            addCriterion("pull_merch_no <", value, "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoLessThanOrEqualTo(String value) {
            addCriterion("pull_merch_no <=", value, "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoLike(String value) {
            addCriterion("pull_merch_no like", value, "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoNotLike(String value) {
            addCriterion("pull_merch_no not like", value, "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoIn(List<String> values) {
            addCriterion("pull_merch_no in", values, "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoNotIn(List<String> values) {
            addCriterion("pull_merch_no not in", values, "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoBetween(String value1, String value2) {
            addCriterion("pull_merch_no between", value1, value2, "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoNotBetween(String value1, String value2) {
            addCriterion("pull_merch_no not between", value1, value2, "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoIsNull() {
            addCriterion("pull_order_no is null");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoIsNotNull() {
            addCriterion("pull_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoEqualTo(String value) {
            addCriterion("pull_order_no =", value, "pullOrderNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoNotEqualTo(String value) {
            addCriterion("pull_order_no <>", value, "pullOrderNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoGreaterThan(String value) {
            addCriterion("pull_order_no >", value, "pullOrderNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("pull_order_no >=", value, "pullOrderNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoLessThan(String value) {
            addCriterion("pull_order_no <", value, "pullOrderNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoLessThanOrEqualTo(String value) {
            addCriterion("pull_order_no <=", value, "pullOrderNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoLike(String value) {
            addCriterion("pull_order_no like", value, "pullOrderNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoNotLike(String value) {
            addCriterion("pull_order_no not like", value, "pullOrderNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoIn(List<String> values) {
            addCriterion("pull_order_no in", values, "pullOrderNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoNotIn(List<String> values) {
            addCriterion("pull_order_no not in", values, "pullOrderNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoBetween(String value1, String value2) {
            addCriterion("pull_order_no between", value1, value2, "pullOrderNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoNotBetween(String value1, String value2) {
            addCriterion("pull_order_no not between", value1, value2, "pullOrderNo");
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

        public Criteria andForeignOrderNoLikeInsensitive(String value) {
            addCriterion("upper(foreign_order_no) like", value.toUpperCase(), "foreignOrderNo");
            return (Criteria) this;
        }

        public Criteria andItemNameLikeInsensitive(String value) {
            addCriterion("upper(item_name) like", value.toUpperCase(), "itemName");
            return (Criteria) this;
        }

        public Criteria andChargeNoLikeInsensitive(String value) {
            addCriterion("upper(charge_no) like", value.toUpperCase(), "chargeNo");
            return (Criteria) this;
        }

        public Criteria andRechargeStatusLikeInsensitive(String value) {
            addCriterion("upper(recharge_status) like", value.toUpperCase(), "rechargeStatus");
            return (Criteria) this;
        }

        public Criteria andModuleLikeInsensitive(String value) {
            addCriterion("upper(module) like", value.toUpperCase(), "module");
            return (Criteria) this;
        }

        public Criteria andPullMerchNoLikeInsensitive(String value) {
            addCriterion("upper(pull_merch_no) like", value.toUpperCase(), "pullMerchNo");
            return (Criteria) this;
        }

        public Criteria andPullOrderNoLikeInsensitive(String value) {
            addCriterion("upper(pull_order_no) like", value.toUpperCase(), "pullOrderNo");
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