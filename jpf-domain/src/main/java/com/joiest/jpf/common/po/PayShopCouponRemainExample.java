package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayShopCouponRemainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayShopCouponRemainExample() {
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

        public Criteria andCouponIdIsNull() {
            addCriterion("coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNotNull() {
            addCriterion("coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdEqualTo(String value) {
            addCriterion("coupon_id =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(String value) {
            addCriterion("coupon_id <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(String value) {
            addCriterion("coupon_id >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_id >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(String value) {
            addCriterion("coupon_id <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(String value) {
            addCriterion("coupon_id <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<String> values) {
            addCriterion("coupon_id in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<String> values) {
            addCriterion("coupon_id not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(String value1, String value2) {
            addCriterion("coupon_id between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(String value1, String value2) {
            addCriterion("coupon_id not between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponNoIsNull() {
            addCriterion("coupon_no is null");
            return (Criteria) this;
        }

        public Criteria andCouponNoIsNotNull() {
            addCriterion("coupon_no is not null");
            return (Criteria) this;
        }

        public Criteria andCouponNoEqualTo(String value) {
            addCriterion("coupon_no =", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotEqualTo(String value) {
            addCriterion("coupon_no <>", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoGreaterThan(String value) {
            addCriterion("coupon_no >", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_no >=", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLessThan(String value) {
            addCriterion("coupon_no <", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLessThanOrEqualTo(String value) {
            addCriterion("coupon_no <=", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLike(String value) {
            addCriterion("coupon_no like", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotLike(String value) {
            addCriterion("coupon_no not like", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoIn(List<String> values) {
            addCriterion("coupon_no in", values, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotIn(List<String> values) {
            addCriterion("coupon_no not in", values, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoBetween(String value1, String value2) {
            addCriterion("coupon_no between", value1, value2, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotBetween(String value1, String value2) {
            addCriterion("coupon_no not between", value1, value2, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeIsNull() {
            addCriterion("coupon_active_code is null");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeIsNotNull() {
            addCriterion("coupon_active_code is not null");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeEqualTo(String value) {
            addCriterion("coupon_active_code =", value, "couponActiveCode");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeNotEqualTo(String value) {
            addCriterion("coupon_active_code <>", value, "couponActiveCode");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeGreaterThan(String value) {
            addCriterion("coupon_active_code >", value, "couponActiveCode");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_active_code >=", value, "couponActiveCode");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeLessThan(String value) {
            addCriterion("coupon_active_code <", value, "couponActiveCode");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeLessThanOrEqualTo(String value) {
            addCriterion("coupon_active_code <=", value, "couponActiveCode");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeLike(String value) {
            addCriterion("coupon_active_code like", value, "couponActiveCode");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeNotLike(String value) {
            addCriterion("coupon_active_code not like", value, "couponActiveCode");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeIn(List<String> values) {
            addCriterion("coupon_active_code in", values, "couponActiveCode");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeNotIn(List<String> values) {
            addCriterion("coupon_active_code not in", values, "couponActiveCode");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeBetween(String value1, String value2) {
            addCriterion("coupon_active_code between", value1, value2, "couponActiveCode");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeNotBetween(String value1, String value2) {
            addCriterion("coupon_active_code not between", value1, value2, "couponActiveCode");
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

        public Criteria andCouponDouIsNull() {
            addCriterion("coupon_dou is null");
            return (Criteria) this;
        }

        public Criteria andCouponDouIsNotNull() {
            addCriterion("coupon_dou is not null");
            return (Criteria) this;
        }

        public Criteria andCouponDouEqualTo(Integer value) {
            addCriterion("coupon_dou =", value, "couponDou");
            return (Criteria) this;
        }

        public Criteria andCouponDouNotEqualTo(Integer value) {
            addCriterion("coupon_dou <>", value, "couponDou");
            return (Criteria) this;
        }

        public Criteria andCouponDouGreaterThan(Integer value) {
            addCriterion("coupon_dou >", value, "couponDou");
            return (Criteria) this;
        }

        public Criteria andCouponDouGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_dou >=", value, "couponDou");
            return (Criteria) this;
        }

        public Criteria andCouponDouLessThan(Integer value) {
            addCriterion("coupon_dou <", value, "couponDou");
            return (Criteria) this;
        }

        public Criteria andCouponDouLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_dou <=", value, "couponDou");
            return (Criteria) this;
        }

        public Criteria andCouponDouIn(List<Integer> values) {
            addCriterion("coupon_dou in", values, "couponDou");
            return (Criteria) this;
        }

        public Criteria andCouponDouNotIn(List<Integer> values) {
            addCriterion("coupon_dou not in", values, "couponDou");
            return (Criteria) this;
        }

        public Criteria andCouponDouBetween(Integer value1, Integer value2) {
            addCriterion("coupon_dou between", value1, value2, "couponDou");
            return (Criteria) this;
        }

        public Criteria andCouponDouNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_dou not between", value1, value2, "couponDou");
            return (Criteria) this;
        }

        public Criteria andSaleDouNoIsNull() {
            addCriterion("sale_dou_no is null");
            return (Criteria) this;
        }

        public Criteria andSaleDouNoIsNotNull() {
            addCriterion("sale_dou_no is not null");
            return (Criteria) this;
        }

        public Criteria andSaleDouNoEqualTo(Integer value) {
            addCriterion("sale_dou_no =", value, "saleDouNo");
            return (Criteria) this;
        }

        public Criteria andSaleDouNoNotEqualTo(Integer value) {
            addCriterion("sale_dou_no <>", value, "saleDouNo");
            return (Criteria) this;
        }

        public Criteria andSaleDouNoGreaterThan(Integer value) {
            addCriterion("sale_dou_no >", value, "saleDouNo");
            return (Criteria) this;
        }

        public Criteria andSaleDouNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_dou_no >=", value, "saleDouNo");
            return (Criteria) this;
        }

        public Criteria andSaleDouNoLessThan(Integer value) {
            addCriterion("sale_dou_no <", value, "saleDouNo");
            return (Criteria) this;
        }

        public Criteria andSaleDouNoLessThanOrEqualTo(Integer value) {
            addCriterion("sale_dou_no <=", value, "saleDouNo");
            return (Criteria) this;
        }

        public Criteria andSaleDouNoIn(List<Integer> values) {
            addCriterion("sale_dou_no in", values, "saleDouNo");
            return (Criteria) this;
        }

        public Criteria andSaleDouNoNotIn(List<Integer> values) {
            addCriterion("sale_dou_no not in", values, "saleDouNo");
            return (Criteria) this;
        }

        public Criteria andSaleDouNoBetween(Integer value1, Integer value2) {
            addCriterion("sale_dou_no between", value1, value2, "saleDouNo");
            return (Criteria) this;
        }

        public Criteria andSaleDouNoNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_dou_no not between", value1, value2, "saleDouNo");
            return (Criteria) this;
        }

        public Criteria andCouponDouLeftIsNull() {
            addCriterion("coupon_dou_left is null");
            return (Criteria) this;
        }

        public Criteria andCouponDouLeftIsNotNull() {
            addCriterion("coupon_dou_left is not null");
            return (Criteria) this;
        }

        public Criteria andCouponDouLeftEqualTo(Integer value) {
            addCriterion("coupon_dou_left =", value, "couponDouLeft");
            return (Criteria) this;
        }

        public Criteria andCouponDouLeftNotEqualTo(Integer value) {
            addCriterion("coupon_dou_left <>", value, "couponDouLeft");
            return (Criteria) this;
        }

        public Criteria andCouponDouLeftGreaterThan(Integer value) {
            addCriterion("coupon_dou_left >", value, "couponDouLeft");
            return (Criteria) this;
        }

        public Criteria andCouponDouLeftGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_dou_left >=", value, "couponDouLeft");
            return (Criteria) this;
        }

        public Criteria andCouponDouLeftLessThan(Integer value) {
            addCriterion("coupon_dou_left <", value, "couponDouLeft");
            return (Criteria) this;
        }

        public Criteria andCouponDouLeftLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_dou_left <=", value, "couponDouLeft");
            return (Criteria) this;
        }

        public Criteria andCouponDouLeftIn(List<Integer> values) {
            addCriterion("coupon_dou_left in", values, "couponDouLeft");
            return (Criteria) this;
        }

        public Criteria andCouponDouLeftNotIn(List<Integer> values) {
            addCriterion("coupon_dou_left not in", values, "couponDouLeft");
            return (Criteria) this;
        }

        public Criteria andCouponDouLeftBetween(Integer value1, Integer value2) {
            addCriterion("coupon_dou_left between", value1, value2, "couponDouLeft");
            return (Criteria) this;
        }

        public Criteria andCouponDouLeftNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_dou_left not between", value1, value2, "couponDouLeft");
            return (Criteria) this;
        }

        public Criteria andSaleDouYesIsNull() {
            addCriterion("sale_dou_yes is null");
            return (Criteria) this;
        }

        public Criteria andSaleDouYesIsNotNull() {
            addCriterion("sale_dou_yes is not null");
            return (Criteria) this;
        }

        public Criteria andSaleDouYesEqualTo(Integer value) {
            addCriterion("sale_dou_yes =", value, "saleDouYes");
            return (Criteria) this;
        }

        public Criteria andSaleDouYesNotEqualTo(Integer value) {
            addCriterion("sale_dou_yes <>", value, "saleDouYes");
            return (Criteria) this;
        }

        public Criteria andSaleDouYesGreaterThan(Integer value) {
            addCriterion("sale_dou_yes >", value, "saleDouYes");
            return (Criteria) this;
        }

        public Criteria andSaleDouYesGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_dou_yes >=", value, "saleDouYes");
            return (Criteria) this;
        }

        public Criteria andSaleDouYesLessThan(Integer value) {
            addCriterion("sale_dou_yes <", value, "saleDouYes");
            return (Criteria) this;
        }

        public Criteria andSaleDouYesLessThanOrEqualTo(Integer value) {
            addCriterion("sale_dou_yes <=", value, "saleDouYes");
            return (Criteria) this;
        }

        public Criteria andSaleDouYesIn(List<Integer> values) {
            addCriterion("sale_dou_yes in", values, "saleDouYes");
            return (Criteria) this;
        }

        public Criteria andSaleDouYesNotIn(List<Integer> values) {
            addCriterion("sale_dou_yes not in", values, "saleDouYes");
            return (Criteria) this;
        }

        public Criteria andSaleDouYesBetween(Integer value1, Integer value2) {
            addCriterion("sale_dou_yes between", value1, value2, "saleDouYes");
            return (Criteria) this;
        }

        public Criteria andSaleDouYesNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_dou_yes not between", value1, value2, "saleDouYes");
            return (Criteria) this;
        }

        public Criteria andSaleDouLeftIsNull() {
            addCriterion("sale_dou_left is null");
            return (Criteria) this;
        }

        public Criteria andSaleDouLeftIsNotNull() {
            addCriterion("sale_dou_left is not null");
            return (Criteria) this;
        }

        public Criteria andSaleDouLeftEqualTo(Integer value) {
            addCriterion("sale_dou_left =", value, "saleDouLeft");
            return (Criteria) this;
        }

        public Criteria andSaleDouLeftNotEqualTo(Integer value) {
            addCriterion("sale_dou_left <>", value, "saleDouLeft");
            return (Criteria) this;
        }

        public Criteria andSaleDouLeftGreaterThan(Integer value) {
            addCriterion("sale_dou_left >", value, "saleDouLeft");
            return (Criteria) this;
        }

        public Criteria andSaleDouLeftGreaterThanOrEqualTo(Integer value) {
            addCriterion("sale_dou_left >=", value, "saleDouLeft");
            return (Criteria) this;
        }

        public Criteria andSaleDouLeftLessThan(Integer value) {
            addCriterion("sale_dou_left <", value, "saleDouLeft");
            return (Criteria) this;
        }

        public Criteria andSaleDouLeftLessThanOrEqualTo(Integer value) {
            addCriterion("sale_dou_left <=", value, "saleDouLeft");
            return (Criteria) this;
        }

        public Criteria andSaleDouLeftIn(List<Integer> values) {
            addCriterion("sale_dou_left in", values, "saleDouLeft");
            return (Criteria) this;
        }

        public Criteria andSaleDouLeftNotIn(List<Integer> values) {
            addCriterion("sale_dou_left not in", values, "saleDouLeft");
            return (Criteria) this;
        }

        public Criteria andSaleDouLeftBetween(Integer value1, Integer value2) {
            addCriterion("sale_dou_left between", value1, value2, "saleDouLeft");
            return (Criteria) this;
        }

        public Criteria andSaleDouLeftNotBetween(Integer value1, Integer value2) {
            addCriterion("sale_dou_left not between", value1, value2, "saleDouLeft");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNull() {
            addCriterion("expire_time is null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIsNotNull() {
            addCriterion("expire_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpireTimeEqualTo(Date value) {
            addCriterion("expire_time =", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotEqualTo(Date value) {
            addCriterion("expire_time <>", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThan(Date value) {
            addCriterion("expire_time >", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expire_time >=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThan(Date value) {
            addCriterion("expire_time <", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("expire_time <=", value, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeIn(List<Date> values) {
            addCriterion("expire_time in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotIn(List<Date> values) {
            addCriterion("expire_time not in", values, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeBetween(Date value1, Date value2) {
            addCriterion("expire_time between", value1, value2, "expireTime");
            return (Criteria) this;
        }

        public Criteria andExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("expire_time not between", value1, value2, "expireTime");
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

        public Criteria andPercentIsNull() {
            addCriterion("percent is null");
            return (Criteria) this;
        }

        public Criteria andPercentIsNotNull() {
            addCriterion("percent is not null");
            return (Criteria) this;
        }

        public Criteria andPercentEqualTo(BigDecimal value) {
            addCriterion("percent =", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotEqualTo(BigDecimal value) {
            addCriterion("percent <>", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThan(BigDecimal value) {
            addCriterion("percent >", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("percent >=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThan(BigDecimal value) {
            addCriterion("percent <", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("percent <=", value, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentIn(List<BigDecimal> values) {
            addCriterion("percent in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotIn(List<BigDecimal> values) {
            addCriterion("percent not in", values, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("percent between", value1, value2, "percent");
            return (Criteria) this;
        }

        public Criteria andPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("percent not between", value1, value2, "percent");
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

        public Criteria andSalestatusIsNull() {
            addCriterion("saleStatus is null");
            return (Criteria) this;
        }

        public Criteria andSalestatusIsNotNull() {
            addCriterion("saleStatus is not null");
            return (Criteria) this;
        }

        public Criteria andSalestatusEqualTo(Byte value) {
            addCriterion("saleStatus =", value, "salestatus");
            return (Criteria) this;
        }

        public Criteria andSalestatusNotEqualTo(Byte value) {
            addCriterion("saleStatus <>", value, "salestatus");
            return (Criteria) this;
        }

        public Criteria andSalestatusGreaterThan(Byte value) {
            addCriterion("saleStatus >", value, "salestatus");
            return (Criteria) this;
        }

        public Criteria andSalestatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("saleStatus >=", value, "salestatus");
            return (Criteria) this;
        }

        public Criteria andSalestatusLessThan(Byte value) {
            addCriterion("saleStatus <", value, "salestatus");
            return (Criteria) this;
        }

        public Criteria andSalestatusLessThanOrEqualTo(Byte value) {
            addCriterion("saleStatus <=", value, "salestatus");
            return (Criteria) this;
        }

        public Criteria andSalestatusIn(List<Byte> values) {
            addCriterion("saleStatus in", values, "salestatus");
            return (Criteria) this;
        }

        public Criteria andSalestatusNotIn(List<Byte> values) {
            addCriterion("saleStatus not in", values, "salestatus");
            return (Criteria) this;
        }

        public Criteria andSalestatusBetween(Byte value1, Byte value2) {
            addCriterion("saleStatus between", value1, value2, "salestatus");
            return (Criteria) this;
        }

        public Criteria andSalestatusNotBetween(Byte value1, Byte value2) {
            addCriterion("saleStatus not between", value1, value2, "salestatus");
            return (Criteria) this;
        }

        public Criteria andCouponNoLikeInsensitive(String value) {
            addCriterion("upper(coupon_no) like", value.toUpperCase(), "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponActiveCodeLikeInsensitive(String value) {
            addCriterion("upper(coupon_active_code) like", value.toUpperCase(), "couponActiveCode");
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