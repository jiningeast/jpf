package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayShopBatchCouponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayShopBatchCouponExample() {
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

        public Criteria andBatchIdIsNull() {
            addCriterion("batch_id is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(String value) {
            addCriterion("batch_id =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(String value) {
            addCriterion("batch_id <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(String value) {
            addCriterion("batch_id >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(String value) {
            addCriterion("batch_id >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(String value) {
            addCriterion("batch_id <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(String value) {
            addCriterion("batch_id <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<String> values) {
            addCriterion("batch_id in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<String> values) {
            addCriterion("batch_id not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(String value1, String value2) {
            addCriterion("batch_id between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(String value1, String value2) {
            addCriterion("batch_id not between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNull() {
            addCriterion("batch_no is null");
            return (Criteria) this;
        }

        public Criteria andBatchNoIsNotNull() {
            addCriterion("batch_no is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNoEqualTo(String value) {
            addCriterion("batch_no =", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotEqualTo(String value) {
            addCriterion("batch_no <>", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThan(String value) {
            addCriterion("batch_no >", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoGreaterThanOrEqualTo(String value) {
            addCriterion("batch_no >=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThan(String value) {
            addCriterion("batch_no <", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLessThanOrEqualTo(String value) {
            addCriterion("batch_no <=", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoLike(String value) {
            addCriterion("batch_no like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotLike(String value) {
            addCriterion("batch_no not like", value, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoIn(List<String> values) {
            addCriterion("batch_no in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotIn(List<String> values) {
            addCriterion("batch_no not in", values, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoBetween(String value1, String value2) {
            addCriterion("batch_no between", value1, value2, "batchNo");
            return (Criteria) this;
        }

        public Criteria andBatchNoNotBetween(String value1, String value2) {
            addCriterion("batch_no not between", value1, value2, "batchNo");
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

        public Criteria andActiveCodeIsNull() {
            addCriterion("active_code is null");
            return (Criteria) this;
        }

        public Criteria andActiveCodeIsNotNull() {
            addCriterion("active_code is not null");
            return (Criteria) this;
        }

        public Criteria andActiveCodeEqualTo(String value) {
            addCriterion("active_code =", value, "activeCode");
            return (Criteria) this;
        }

        public Criteria andActiveCodeNotEqualTo(String value) {
            addCriterion("active_code <>", value, "activeCode");
            return (Criteria) this;
        }

        public Criteria andActiveCodeGreaterThan(String value) {
            addCriterion("active_code >", value, "activeCode");
            return (Criteria) this;
        }

        public Criteria andActiveCodeGreaterThanOrEqualTo(String value) {
            addCriterion("active_code >=", value, "activeCode");
            return (Criteria) this;
        }

        public Criteria andActiveCodeLessThan(String value) {
            addCriterion("active_code <", value, "activeCode");
            return (Criteria) this;
        }

        public Criteria andActiveCodeLessThanOrEqualTo(String value) {
            addCriterion("active_code <=", value, "activeCode");
            return (Criteria) this;
        }

        public Criteria andActiveCodeLike(String value) {
            addCriterion("active_code like", value, "activeCode");
            return (Criteria) this;
        }

        public Criteria andActiveCodeNotLike(String value) {
            addCriterion("active_code not like", value, "activeCode");
            return (Criteria) this;
        }

        public Criteria andActiveCodeIn(List<String> values) {
            addCriterion("active_code in", values, "activeCode");
            return (Criteria) this;
        }

        public Criteria andActiveCodeNotIn(List<String> values) {
            addCriterion("active_code not in", values, "activeCode");
            return (Criteria) this;
        }

        public Criteria andActiveCodeBetween(String value1, String value2) {
            addCriterion("active_code between", value1, value2, "activeCode");
            return (Criteria) this;
        }

        public Criteria andActiveCodeNotBetween(String value1, String value2) {
            addCriterion("active_code not between", value1, value2, "activeCode");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Integer value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Integer value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Integer value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Integer value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Integer> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Integer> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Integer value1, Integer value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("money not between", value1, value2, "money");
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

        public Criteria andIsActiveIsNull() {
            addCriterion("is_active is null");
            return (Criteria) this;
        }

        public Criteria andIsActiveIsNotNull() {
            addCriterion("is_active is not null");
            return (Criteria) this;
        }

        public Criteria andIsActiveEqualTo(Byte value) {
            addCriterion("is_active =", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotEqualTo(Byte value) {
            addCriterion("is_active <>", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThan(Byte value) {
            addCriterion("is_active >", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_active >=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThan(Byte value) {
            addCriterion("is_active <", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThanOrEqualTo(Byte value) {
            addCriterion("is_active <=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveIn(List<Byte> values) {
            addCriterion("is_active in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotIn(List<Byte> values) {
            addCriterion("is_active not in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveBetween(Byte value1, Byte value2) {
            addCriterion("is_active between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotBetween(Byte value1, Byte value2) {
            addCriterion("is_active not between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andActiveCustomerIdIsNull() {
            addCriterion("active_customer_id is null");
            return (Criteria) this;
        }

        public Criteria andActiveCustomerIdIsNotNull() {
            addCriterion("active_customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andActiveCustomerIdEqualTo(String value) {
            addCriterion("active_customer_id =", value, "activeCustomerId");
            return (Criteria) this;
        }

        public Criteria andActiveCustomerIdNotEqualTo(String value) {
            addCriterion("active_customer_id <>", value, "activeCustomerId");
            return (Criteria) this;
        }

        public Criteria andActiveCustomerIdGreaterThan(String value) {
            addCriterion("active_customer_id >", value, "activeCustomerId");
            return (Criteria) this;
        }

        public Criteria andActiveCustomerIdGreaterThanOrEqualTo(String value) {
            addCriterion("active_customer_id >=", value, "activeCustomerId");
            return (Criteria) this;
        }

        public Criteria andActiveCustomerIdLessThan(String value) {
            addCriterion("active_customer_id <", value, "activeCustomerId");
            return (Criteria) this;
        }

        public Criteria andActiveCustomerIdLessThanOrEqualTo(String value) {
            addCriterion("active_customer_id <=", value, "activeCustomerId");
            return (Criteria) this;
        }

        public Criteria andActiveCustomerIdIn(List<String> values) {
            addCriterion("active_customer_id in", values, "activeCustomerId");
            return (Criteria) this;
        }

        public Criteria andActiveCustomerIdNotIn(List<String> values) {
            addCriterion("active_customer_id not in", values, "activeCustomerId");
            return (Criteria) this;
        }

        public Criteria andActiveCustomerIdBetween(String value1, String value2) {
            addCriterion("active_customer_id between", value1, value2, "activeCustomerId");
            return (Criteria) this;
        }

        public Criteria andActiveCustomerIdNotBetween(String value1, String value2) {
            addCriterion("active_customer_id not between", value1, value2, "activeCustomerId");
            return (Criteria) this;
        }

        public Criteria andActivePhoneIsNull() {
            addCriterion("active_phone is null");
            return (Criteria) this;
        }

        public Criteria andActivePhoneIsNotNull() {
            addCriterion("active_phone is not null");
            return (Criteria) this;
        }

        public Criteria andActivePhoneEqualTo(String value) {
            addCriterion("active_phone =", value, "activePhone");
            return (Criteria) this;
        }

        public Criteria andActivePhoneNotEqualTo(String value) {
            addCriterion("active_phone <>", value, "activePhone");
            return (Criteria) this;
        }

        public Criteria andActivePhoneGreaterThan(String value) {
            addCriterion("active_phone >", value, "activePhone");
            return (Criteria) this;
        }

        public Criteria andActivePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("active_phone >=", value, "activePhone");
            return (Criteria) this;
        }

        public Criteria andActivePhoneLessThan(String value) {
            addCriterion("active_phone <", value, "activePhone");
            return (Criteria) this;
        }

        public Criteria andActivePhoneLessThanOrEqualTo(String value) {
            addCriterion("active_phone <=", value, "activePhone");
            return (Criteria) this;
        }

        public Criteria andActivePhoneLike(String value) {
            addCriterion("active_phone like", value, "activePhone");
            return (Criteria) this;
        }

        public Criteria andActivePhoneNotLike(String value) {
            addCriterion("active_phone not like", value, "activePhone");
            return (Criteria) this;
        }

        public Criteria andActivePhoneIn(List<String> values) {
            addCriterion("active_phone in", values, "activePhone");
            return (Criteria) this;
        }

        public Criteria andActivePhoneNotIn(List<String> values) {
            addCriterion("active_phone not in", values, "activePhone");
            return (Criteria) this;
        }

        public Criteria andActivePhoneBetween(String value1, String value2) {
            addCriterion("active_phone between", value1, value2, "activePhone");
            return (Criteria) this;
        }

        public Criteria andActivePhoneNotBetween(String value1, String value2) {
            addCriterion("active_phone not between", value1, value2, "activePhone");
            return (Criteria) this;
        }

        public Criteria andActiveNameIsNull() {
            addCriterion("active_name is null");
            return (Criteria) this;
        }

        public Criteria andActiveNameIsNotNull() {
            addCriterion("active_name is not null");
            return (Criteria) this;
        }

        public Criteria andActiveNameEqualTo(String value) {
            addCriterion("active_name =", value, "activeName");
            return (Criteria) this;
        }

        public Criteria andActiveNameNotEqualTo(String value) {
            addCriterion("active_name <>", value, "activeName");
            return (Criteria) this;
        }

        public Criteria andActiveNameGreaterThan(String value) {
            addCriterion("active_name >", value, "activeName");
            return (Criteria) this;
        }

        public Criteria andActiveNameGreaterThanOrEqualTo(String value) {
            addCriterion("active_name >=", value, "activeName");
            return (Criteria) this;
        }

        public Criteria andActiveNameLessThan(String value) {
            addCriterion("active_name <", value, "activeName");
            return (Criteria) this;
        }

        public Criteria andActiveNameLessThanOrEqualTo(String value) {
            addCriterion("active_name <=", value, "activeName");
            return (Criteria) this;
        }

        public Criteria andActiveNameLike(String value) {
            addCriterion("active_name like", value, "activeName");
            return (Criteria) this;
        }

        public Criteria andActiveNameNotLike(String value) {
            addCriterion("active_name not like", value, "activeName");
            return (Criteria) this;
        }

        public Criteria andActiveNameIn(List<String> values) {
            addCriterion("active_name in", values, "activeName");
            return (Criteria) this;
        }

        public Criteria andActiveNameNotIn(List<String> values) {
            addCriterion("active_name not in", values, "activeName");
            return (Criteria) this;
        }

        public Criteria andActiveNameBetween(String value1, String value2) {
            addCriterion("active_name between", value1, value2, "activeName");
            return (Criteria) this;
        }

        public Criteria andActiveNameNotBetween(String value1, String value2) {
            addCriterion("active_name not between", value1, value2, "activeName");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNull() {
            addCriterion("active_time is null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNotNull() {
            addCriterion("active_time is not null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeEqualTo(Date value) {
            addCriterion("active_time =", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotEqualTo(Date value) {
            addCriterion("active_time <>", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThan(Date value) {
            addCriterion("active_time >", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("active_time >=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThan(Date value) {
            addCriterion("active_time <", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThanOrEqualTo(Date value) {
            addCriterion("active_time <=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIn(List<Date> values) {
            addCriterion("active_time in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotIn(List<Date> values) {
            addCriterion("active_time not in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeBetween(Date value1, Date value2) {
            addCriterion("active_time between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotBetween(Date value1, Date value2) {
            addCriterion("active_time not between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andExpireMonthIsNull() {
            addCriterion("expire_month is null");
            return (Criteria) this;
        }

        public Criteria andExpireMonthIsNotNull() {
            addCriterion("expire_month is not null");
            return (Criteria) this;
        }

        public Criteria andExpireMonthEqualTo(Integer value) {
            addCriterion("expire_month =", value, "expireMonth");
            return (Criteria) this;
        }

        public Criteria andExpireMonthNotEqualTo(Integer value) {
            addCriterion("expire_month <>", value, "expireMonth");
            return (Criteria) this;
        }

        public Criteria andExpireMonthGreaterThan(Integer value) {
            addCriterion("expire_month >", value, "expireMonth");
            return (Criteria) this;
        }

        public Criteria andExpireMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("expire_month >=", value, "expireMonth");
            return (Criteria) this;
        }

        public Criteria andExpireMonthLessThan(Integer value) {
            addCriterion("expire_month <", value, "expireMonth");
            return (Criteria) this;
        }

        public Criteria andExpireMonthLessThanOrEqualTo(Integer value) {
            addCriterion("expire_month <=", value, "expireMonth");
            return (Criteria) this;
        }

        public Criteria andExpireMonthIn(List<Integer> values) {
            addCriterion("expire_month in", values, "expireMonth");
            return (Criteria) this;
        }

        public Criteria andExpireMonthNotIn(List<Integer> values) {
            addCriterion("expire_month not in", values, "expireMonth");
            return (Criteria) this;
        }

        public Criteria andExpireMonthBetween(Integer value1, Integer value2) {
            addCriterion("expire_month between", value1, value2, "expireMonth");
            return (Criteria) this;
        }

        public Criteria andExpireMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("expire_month not between", value1, value2, "expireMonth");
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

        public Criteria andIsExpiredIsNull() {
            addCriterion("is_expired is null");
            return (Criteria) this;
        }

        public Criteria andIsExpiredIsNotNull() {
            addCriterion("is_expired is not null");
            return (Criteria) this;
        }

        public Criteria andIsExpiredEqualTo(Byte value) {
            addCriterion("is_expired =", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredNotEqualTo(Byte value) {
            addCriterion("is_expired <>", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredGreaterThan(Byte value) {
            addCriterion("is_expired >", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_expired >=", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredLessThan(Byte value) {
            addCriterion("is_expired <", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredLessThanOrEqualTo(Byte value) {
            addCriterion("is_expired <=", value, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredIn(List<Byte> values) {
            addCriterion("is_expired in", values, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredNotIn(List<Byte> values) {
            addCriterion("is_expired not in", values, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredBetween(Byte value1, Byte value2) {
            addCriterion("is_expired between", value1, value2, "isExpired");
            return (Criteria) this;
        }

        public Criteria andIsExpiredNotBetween(Byte value1, Byte value2) {
            addCriterion("is_expired not between", value1, value2, "isExpired");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("send_time is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("send_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterion("send_time =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterion("send_time <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterion("send_time >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("send_time >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterion("send_time <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterion("send_time <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterion("send_time in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterion("send_time not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterion("send_time between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterion("send_time not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNull() {
            addCriterion("send_type is null");
            return (Criteria) this;
        }

        public Criteria andSendTypeIsNotNull() {
            addCriterion("send_type is not null");
            return (Criteria) this;
        }

        public Criteria andSendTypeEqualTo(Byte value) {
            addCriterion("send_type =", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotEqualTo(Byte value) {
            addCriterion("send_type <>", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThan(Byte value) {
            addCriterion("send_type >", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("send_type >=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThan(Byte value) {
            addCriterion("send_type <", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeLessThanOrEqualTo(Byte value) {
            addCriterion("send_type <=", value, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeIn(List<Byte> values) {
            addCriterion("send_type in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotIn(List<Byte> values) {
            addCriterion("send_type not in", values, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeBetween(Byte value1, Byte value2) {
            addCriterion("send_type between", value1, value2, "sendType");
            return (Criteria) this;
        }

        public Criteria andSendTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("send_type not between", value1, value2, "sendType");
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

        public Criteria andBatchNoLikeInsensitive(String value) {
            addCriterion("upper(batch_no) like", value.toUpperCase(), "batchNo");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLikeInsensitive(String value) {
            addCriterion("upper(company_name) like", value.toUpperCase(), "companyName");
            return (Criteria) this;
        }

        public Criteria andCouponNoLikeInsensitive(String value) {
            addCriterion("upper(coupon_no) like", value.toUpperCase(), "couponNo");
            return (Criteria) this;
        }

        public Criteria andActiveCodeLikeInsensitive(String value) {
            addCriterion("upper(active_code) like", value.toUpperCase(), "activeCode");
            return (Criteria) this;
        }

        public Criteria andActivePhoneLikeInsensitive(String value) {
            addCriterion("upper(active_phone) like", value.toUpperCase(), "activePhone");
            return (Criteria) this;
        }

        public Criteria andActiveNameLikeInsensitive(String value) {
            addCriterion("upper(active_name) like", value.toUpperCase(), "activeName");
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