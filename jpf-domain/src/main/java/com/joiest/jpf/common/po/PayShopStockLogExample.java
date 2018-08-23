package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayShopStockLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayShopStockLogExample() {
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

        public Criteria andInitMoneyIsNull() {
            addCriterion("init_money is null");
            return (Criteria) this;
        }

        public Criteria andInitMoneyIsNotNull() {
            addCriterion("init_money is not null");
            return (Criteria) this;
        }

        public Criteria andInitMoneyEqualTo(BigDecimal value) {
            addCriterion("init_money =", value, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyNotEqualTo(BigDecimal value) {
            addCriterion("init_money <>", value, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyGreaterThan(BigDecimal value) {
            addCriterion("init_money >", value, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("init_money >=", value, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyLessThan(BigDecimal value) {
            addCriterion("init_money <", value, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("init_money <=", value, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyIn(List<BigDecimal> values) {
            addCriterion("init_money in", values, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyNotIn(List<BigDecimal> values) {
            addCriterion("init_money not in", values, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("init_money between", value1, value2, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("init_money not between", value1, value2, "initMoney");
            return (Criteria) this;
        }

        public Criteria andInitAmountIsNull() {
            addCriterion("init_amount is null");
            return (Criteria) this;
        }

        public Criteria andInitAmountIsNotNull() {
            addCriterion("init_amount is not null");
            return (Criteria) this;
        }

        public Criteria andInitAmountEqualTo(Integer value) {
            addCriterion("init_amount =", value, "initAmount");
            return (Criteria) this;
        }

        public Criteria andInitAmountNotEqualTo(Integer value) {
            addCriterion("init_amount <>", value, "initAmount");
            return (Criteria) this;
        }

        public Criteria andInitAmountGreaterThan(Integer value) {
            addCriterion("init_amount >", value, "initAmount");
            return (Criteria) this;
        }

        public Criteria andInitAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("init_amount >=", value, "initAmount");
            return (Criteria) this;
        }

        public Criteria andInitAmountLessThan(Integer value) {
            addCriterion("init_amount <", value, "initAmount");
            return (Criteria) this;
        }

        public Criteria andInitAmountLessThanOrEqualTo(Integer value) {
            addCriterion("init_amount <=", value, "initAmount");
            return (Criteria) this;
        }

        public Criteria andInitAmountIn(List<Integer> values) {
            addCriterion("init_amount in", values, "initAmount");
            return (Criteria) this;
        }

        public Criteria andInitAmountNotIn(List<Integer> values) {
            addCriterion("init_amount not in", values, "initAmount");
            return (Criteria) this;
        }

        public Criteria andInitAmountBetween(Integer value1, Integer value2) {
            addCriterion("init_amount between", value1, value2, "initAmount");
            return (Criteria) this;
        }

        public Criteria andInitAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("init_amount not between", value1, value2, "initAmount");
            return (Criteria) this;
        }

        public Criteria andDailyInMoneyIsNull() {
            addCriterion("daily_in_money is null");
            return (Criteria) this;
        }

        public Criteria andDailyInMoneyIsNotNull() {
            addCriterion("daily_in_money is not null");
            return (Criteria) this;
        }

        public Criteria andDailyInMoneyEqualTo(BigDecimal value) {
            addCriterion("daily_in_money =", value, "dailyInMoney");
            return (Criteria) this;
        }

        public Criteria andDailyInMoneyNotEqualTo(BigDecimal value) {
            addCriterion("daily_in_money <>", value, "dailyInMoney");
            return (Criteria) this;
        }

        public Criteria andDailyInMoneyGreaterThan(BigDecimal value) {
            addCriterion("daily_in_money >", value, "dailyInMoney");
            return (Criteria) this;
        }

        public Criteria andDailyInMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("daily_in_money >=", value, "dailyInMoney");
            return (Criteria) this;
        }

        public Criteria andDailyInMoneyLessThan(BigDecimal value) {
            addCriterion("daily_in_money <", value, "dailyInMoney");
            return (Criteria) this;
        }

        public Criteria andDailyInMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("daily_in_money <=", value, "dailyInMoney");
            return (Criteria) this;
        }

        public Criteria andDailyInMoneyIn(List<BigDecimal> values) {
            addCriterion("daily_in_money in", values, "dailyInMoney");
            return (Criteria) this;
        }

        public Criteria andDailyInMoneyNotIn(List<BigDecimal> values) {
            addCriterion("daily_in_money not in", values, "dailyInMoney");
            return (Criteria) this;
        }

        public Criteria andDailyInMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("daily_in_money between", value1, value2, "dailyInMoney");
            return (Criteria) this;
        }

        public Criteria andDailyInMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("daily_in_money not between", value1, value2, "dailyInMoney");
            return (Criteria) this;
        }

        public Criteria andDailyInAmountIsNull() {
            addCriterion("daily_in_amount is null");
            return (Criteria) this;
        }

        public Criteria andDailyInAmountIsNotNull() {
            addCriterion("daily_in_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDailyInAmountEqualTo(Integer value) {
            addCriterion("daily_in_amount =", value, "dailyInAmount");
            return (Criteria) this;
        }

        public Criteria andDailyInAmountNotEqualTo(Integer value) {
            addCriterion("daily_in_amount <>", value, "dailyInAmount");
            return (Criteria) this;
        }

        public Criteria andDailyInAmountGreaterThan(Integer value) {
            addCriterion("daily_in_amount >", value, "dailyInAmount");
            return (Criteria) this;
        }

        public Criteria andDailyInAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("daily_in_amount >=", value, "dailyInAmount");
            return (Criteria) this;
        }

        public Criteria andDailyInAmountLessThan(Integer value) {
            addCriterion("daily_in_amount <", value, "dailyInAmount");
            return (Criteria) this;
        }

        public Criteria andDailyInAmountLessThanOrEqualTo(Integer value) {
            addCriterion("daily_in_amount <=", value, "dailyInAmount");
            return (Criteria) this;
        }

        public Criteria andDailyInAmountIn(List<Integer> values) {
            addCriterion("daily_in_amount in", values, "dailyInAmount");
            return (Criteria) this;
        }

        public Criteria andDailyInAmountNotIn(List<Integer> values) {
            addCriterion("daily_in_amount not in", values, "dailyInAmount");
            return (Criteria) this;
        }

        public Criteria andDailyInAmountBetween(Integer value1, Integer value2) {
            addCriterion("daily_in_amount between", value1, value2, "dailyInAmount");
            return (Criteria) this;
        }

        public Criteria andDailyInAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("daily_in_amount not between", value1, value2, "dailyInAmount");
            return (Criteria) this;
        }

        public Criteria andDailyOutMoneyIsNull() {
            addCriterion("daily_out_money is null");
            return (Criteria) this;
        }

        public Criteria andDailyOutMoneyIsNotNull() {
            addCriterion("daily_out_money is not null");
            return (Criteria) this;
        }

        public Criteria andDailyOutMoneyEqualTo(BigDecimal value) {
            addCriterion("daily_out_money =", value, "dailyOutMoney");
            return (Criteria) this;
        }

        public Criteria andDailyOutMoneyNotEqualTo(BigDecimal value) {
            addCriterion("daily_out_money <>", value, "dailyOutMoney");
            return (Criteria) this;
        }

        public Criteria andDailyOutMoneyGreaterThan(BigDecimal value) {
            addCriterion("daily_out_money >", value, "dailyOutMoney");
            return (Criteria) this;
        }

        public Criteria andDailyOutMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("daily_out_money >=", value, "dailyOutMoney");
            return (Criteria) this;
        }

        public Criteria andDailyOutMoneyLessThan(BigDecimal value) {
            addCriterion("daily_out_money <", value, "dailyOutMoney");
            return (Criteria) this;
        }

        public Criteria andDailyOutMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("daily_out_money <=", value, "dailyOutMoney");
            return (Criteria) this;
        }

        public Criteria andDailyOutMoneyIn(List<BigDecimal> values) {
            addCriterion("daily_out_money in", values, "dailyOutMoney");
            return (Criteria) this;
        }

        public Criteria andDailyOutMoneyNotIn(List<BigDecimal> values) {
            addCriterion("daily_out_money not in", values, "dailyOutMoney");
            return (Criteria) this;
        }

        public Criteria andDailyOutMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("daily_out_money between", value1, value2, "dailyOutMoney");
            return (Criteria) this;
        }

        public Criteria andDailyOutMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("daily_out_money not between", value1, value2, "dailyOutMoney");
            return (Criteria) this;
        }

        public Criteria andDailyOutAmountIsNull() {
            addCriterion("daily_out_amount is null");
            return (Criteria) this;
        }

        public Criteria andDailyOutAmountIsNotNull() {
            addCriterion("daily_out_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDailyOutAmountEqualTo(Integer value) {
            addCriterion("daily_out_amount =", value, "dailyOutAmount");
            return (Criteria) this;
        }

        public Criteria andDailyOutAmountNotEqualTo(Integer value) {
            addCriterion("daily_out_amount <>", value, "dailyOutAmount");
            return (Criteria) this;
        }

        public Criteria andDailyOutAmountGreaterThan(Integer value) {
            addCriterion("daily_out_amount >", value, "dailyOutAmount");
            return (Criteria) this;
        }

        public Criteria andDailyOutAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("daily_out_amount >=", value, "dailyOutAmount");
            return (Criteria) this;
        }

        public Criteria andDailyOutAmountLessThan(Integer value) {
            addCriterion("daily_out_amount <", value, "dailyOutAmount");
            return (Criteria) this;
        }

        public Criteria andDailyOutAmountLessThanOrEqualTo(Integer value) {
            addCriterion("daily_out_amount <=", value, "dailyOutAmount");
            return (Criteria) this;
        }

        public Criteria andDailyOutAmountIn(List<Integer> values) {
            addCriterion("daily_out_amount in", values, "dailyOutAmount");
            return (Criteria) this;
        }

        public Criteria andDailyOutAmountNotIn(List<Integer> values) {
            addCriterion("daily_out_amount not in", values, "dailyOutAmount");
            return (Criteria) this;
        }

        public Criteria andDailyOutAmountBetween(Integer value1, Integer value2) {
            addCriterion("daily_out_amount between", value1, value2, "dailyOutAmount");
            return (Criteria) this;
        }

        public Criteria andDailyOutAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("daily_out_amount not between", value1, value2, "dailyOutAmount");
            return (Criteria) this;
        }

        public Criteria andFinalMoneyIsNull() {
            addCriterion("final_money is null");
            return (Criteria) this;
        }

        public Criteria andFinalMoneyIsNotNull() {
            addCriterion("final_money is not null");
            return (Criteria) this;
        }

        public Criteria andFinalMoneyEqualTo(BigDecimal value) {
            addCriterion("final_money =", value, "finalMoney");
            return (Criteria) this;
        }

        public Criteria andFinalMoneyNotEqualTo(BigDecimal value) {
            addCriterion("final_money <>", value, "finalMoney");
            return (Criteria) this;
        }

        public Criteria andFinalMoneyGreaterThan(BigDecimal value) {
            addCriterion("final_money >", value, "finalMoney");
            return (Criteria) this;
        }

        public Criteria andFinalMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("final_money >=", value, "finalMoney");
            return (Criteria) this;
        }

        public Criteria andFinalMoneyLessThan(BigDecimal value) {
            addCriterion("final_money <", value, "finalMoney");
            return (Criteria) this;
        }

        public Criteria andFinalMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("final_money <=", value, "finalMoney");
            return (Criteria) this;
        }

        public Criteria andFinalMoneyIn(List<BigDecimal> values) {
            addCriterion("final_money in", values, "finalMoney");
            return (Criteria) this;
        }

        public Criteria andFinalMoneyNotIn(List<BigDecimal> values) {
            addCriterion("final_money not in", values, "finalMoney");
            return (Criteria) this;
        }

        public Criteria andFinalMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("final_money between", value1, value2, "finalMoney");
            return (Criteria) this;
        }

        public Criteria andFinalMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("final_money not between", value1, value2, "finalMoney");
            return (Criteria) this;
        }

        public Criteria andFinalAmountIsNull() {
            addCriterion("final_amount is null");
            return (Criteria) this;
        }

        public Criteria andFinalAmountIsNotNull() {
            addCriterion("final_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFinalAmountEqualTo(Integer value) {
            addCriterion("final_amount =", value, "finalAmount");
            return (Criteria) this;
        }

        public Criteria andFinalAmountNotEqualTo(Integer value) {
            addCriterion("final_amount <>", value, "finalAmount");
            return (Criteria) this;
        }

        public Criteria andFinalAmountGreaterThan(Integer value) {
            addCriterion("final_amount >", value, "finalAmount");
            return (Criteria) this;
        }

        public Criteria andFinalAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("final_amount >=", value, "finalAmount");
            return (Criteria) this;
        }

        public Criteria andFinalAmountLessThan(Integer value) {
            addCriterion("final_amount <", value, "finalAmount");
            return (Criteria) this;
        }

        public Criteria andFinalAmountLessThanOrEqualTo(Integer value) {
            addCriterion("final_amount <=", value, "finalAmount");
            return (Criteria) this;
        }

        public Criteria andFinalAmountIn(List<Integer> values) {
            addCriterion("final_amount in", values, "finalAmount");
            return (Criteria) this;
        }

        public Criteria andFinalAmountNotIn(List<Integer> values) {
            addCriterion("final_amount not in", values, "finalAmount");
            return (Criteria) this;
        }

        public Criteria andFinalAmountBetween(Integer value1, Integer value2) {
            addCriterion("final_amount between", value1, value2, "finalAmount");
            return (Criteria) this;
        }

        public Criteria andFinalAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("final_amount not between", value1, value2, "finalAmount");
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

        public Criteria andProductNameLikeInsensitive(String value) {
            addCriterion("upper(product_name) like", value.toUpperCase(), "productName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLikeInsensitive(String value) {
            addCriterion("upper(customer_name) like", value.toUpperCase(), "customerName");
            return (Criteria) this;
        }

        public Criteria andOperatorNameLikeInsensitive(String value) {
            addCriterion("upper(operator_name) like", value.toUpperCase(), "operatorName");
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