package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayShopStockOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayShopStockOrderExample() {
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

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
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

        public Criteria andImportedAmountIsNull() {
            addCriterion("imported_amount is null");
            return (Criteria) this;
        }

        public Criteria andImportedAmountIsNotNull() {
            addCriterion("imported_amount is not null");
            return (Criteria) this;
        }

        public Criteria andImportedAmountEqualTo(Integer value) {
            addCriterion("imported_amount =", value, "importedAmount");
            return (Criteria) this;
        }

        public Criteria andImportedAmountNotEqualTo(Integer value) {
            addCriterion("imported_amount <>", value, "importedAmount");
            return (Criteria) this;
        }

        public Criteria andImportedAmountGreaterThan(Integer value) {
            addCriterion("imported_amount >", value, "importedAmount");
            return (Criteria) this;
        }

        public Criteria andImportedAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("imported_amount >=", value, "importedAmount");
            return (Criteria) this;
        }

        public Criteria andImportedAmountLessThan(Integer value) {
            addCriterion("imported_amount <", value, "importedAmount");
            return (Criteria) this;
        }

        public Criteria andImportedAmountLessThanOrEqualTo(Integer value) {
            addCriterion("imported_amount <=", value, "importedAmount");
            return (Criteria) this;
        }

        public Criteria andImportedAmountIn(List<Integer> values) {
            addCriterion("imported_amount in", values, "importedAmount");
            return (Criteria) this;
        }

        public Criteria andImportedAmountNotIn(List<Integer> values) {
            addCriterion("imported_amount not in", values, "importedAmount");
            return (Criteria) this;
        }

        public Criteria andImportedAmountBetween(Integer value1, Integer value2) {
            addCriterion("imported_amount between", value1, value2, "importedAmount");
            return (Criteria) this;
        }

        public Criteria andImportedAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("imported_amount not between", value1, value2, "importedAmount");
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

        public Criteria andCheckOperatorIdIsNull() {
            addCriterion("check_operator_id is null");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorIdIsNotNull() {
            addCriterion("check_operator_id is not null");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorIdEqualTo(String value) {
            addCriterion("check_operator_id =", value, "checkOperatorId");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorIdNotEqualTo(String value) {
            addCriterion("check_operator_id <>", value, "checkOperatorId");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorIdGreaterThan(String value) {
            addCriterion("check_operator_id >", value, "checkOperatorId");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("check_operator_id >=", value, "checkOperatorId");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorIdLessThan(String value) {
            addCriterion("check_operator_id <", value, "checkOperatorId");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorIdLessThanOrEqualTo(String value) {
            addCriterion("check_operator_id <=", value, "checkOperatorId");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorIdIn(List<String> values) {
            addCriterion("check_operator_id in", values, "checkOperatorId");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorIdNotIn(List<String> values) {
            addCriterion("check_operator_id not in", values, "checkOperatorId");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorIdBetween(String value1, String value2) {
            addCriterion("check_operator_id between", value1, value2, "checkOperatorId");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorIdNotBetween(String value1, String value2) {
            addCriterion("check_operator_id not between", value1, value2, "checkOperatorId");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameIsNull() {
            addCriterion("check_operator_name is null");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameIsNotNull() {
            addCriterion("check_operator_name is not null");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameEqualTo(String value) {
            addCriterion("check_operator_name =", value, "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameNotEqualTo(String value) {
            addCriterion("check_operator_name <>", value, "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameGreaterThan(String value) {
            addCriterion("check_operator_name >", value, "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameGreaterThanOrEqualTo(String value) {
            addCriterion("check_operator_name >=", value, "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameLessThan(String value) {
            addCriterion("check_operator_name <", value, "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameLessThanOrEqualTo(String value) {
            addCriterion("check_operator_name <=", value, "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameLike(String value) {
            addCriterion("check_operator_name like", value, "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameNotLike(String value) {
            addCriterion("check_operator_name not like", value, "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameIn(List<String> values) {
            addCriterion("check_operator_name in", values, "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameNotIn(List<String> values) {
            addCriterion("check_operator_name not in", values, "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameBetween(String value1, String value2) {
            addCriterion("check_operator_name between", value1, value2, "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameNotBetween(String value1, String value2) {
            addCriterion("check_operator_name not between", value1, value2, "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
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

        public Criteria andPaywayIdIsNull() {
            addCriterion("payway_id is null");
            return (Criteria) this;
        }

        public Criteria andPaywayIdIsNotNull() {
            addCriterion("payway_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaywayIdEqualTo(Integer value) {
            addCriterion("payway_id =", value, "paywayId");
            return (Criteria) this;
        }

        public Criteria andPaywayIdNotEqualTo(Integer value) {
            addCriterion("payway_id <>", value, "paywayId");
            return (Criteria) this;
        }

        public Criteria andPaywayIdGreaterThan(Integer value) {
            addCriterion("payway_id >", value, "paywayId");
            return (Criteria) this;
        }

        public Criteria andPaywayIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("payway_id >=", value, "paywayId");
            return (Criteria) this;
        }

        public Criteria andPaywayIdLessThan(Integer value) {
            addCriterion("payway_id <", value, "paywayId");
            return (Criteria) this;
        }

        public Criteria andPaywayIdLessThanOrEqualTo(Integer value) {
            addCriterion("payway_id <=", value, "paywayId");
            return (Criteria) this;
        }

        public Criteria andPaywayIdIn(List<Integer> values) {
            addCriterion("payway_id in", values, "paywayId");
            return (Criteria) this;
        }

        public Criteria andPaywayIdNotIn(List<Integer> values) {
            addCriterion("payway_id not in", values, "paywayId");
            return (Criteria) this;
        }

        public Criteria andPaywayIdBetween(Integer value1, Integer value2) {
            addCriterion("payway_id between", value1, value2, "paywayId");
            return (Criteria) this;
        }

        public Criteria andPaywayIdNotBetween(Integer value1, Integer value2) {
            addCriterion("payway_id not between", value1, value2, "paywayId");
            return (Criteria) this;
        }

        public Criteria andPaywayCnIsNull() {
            addCriterion("payway_cn is null");
            return (Criteria) this;
        }

        public Criteria andPaywayCnIsNotNull() {
            addCriterion("payway_cn is not null");
            return (Criteria) this;
        }

        public Criteria andPaywayCnEqualTo(String value) {
            addCriterion("payway_cn =", value, "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaywayCnNotEqualTo(String value) {
            addCriterion("payway_cn <>", value, "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaywayCnGreaterThan(String value) {
            addCriterion("payway_cn >", value, "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaywayCnGreaterThanOrEqualTo(String value) {
            addCriterion("payway_cn >=", value, "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaywayCnLessThan(String value) {
            addCriterion("payway_cn <", value, "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaywayCnLessThanOrEqualTo(String value) {
            addCriterion("payway_cn <=", value, "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaywayCnLike(String value) {
            addCriterion("payway_cn like", value, "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaywayCnNotLike(String value) {
            addCriterion("payway_cn not like", value, "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaywayCnIn(List<String> values) {
            addCriterion("payway_cn in", values, "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaywayCnNotIn(List<String> values) {
            addCriterion("payway_cn not in", values, "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaywayCnBetween(String value1, String value2) {
            addCriterion("payway_cn between", value1, value2, "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaywayCnNotBetween(String value1, String value2) {
            addCriterion("payway_cn not between", value1, value2, "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeIdIsNull() {
            addCriterion("paytype_id is null");
            return (Criteria) this;
        }

        public Criteria andPaytypeIdIsNotNull() {
            addCriterion("paytype_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypeIdEqualTo(Integer value) {
            addCriterion("paytype_id =", value, "paytypeId");
            return (Criteria) this;
        }

        public Criteria andPaytypeIdNotEqualTo(Integer value) {
            addCriterion("paytype_id <>", value, "paytypeId");
            return (Criteria) this;
        }

        public Criteria andPaytypeIdGreaterThan(Integer value) {
            addCriterion("paytype_id >", value, "paytypeId");
            return (Criteria) this;
        }

        public Criteria andPaytypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("paytype_id >=", value, "paytypeId");
            return (Criteria) this;
        }

        public Criteria andPaytypeIdLessThan(Integer value) {
            addCriterion("paytype_id <", value, "paytypeId");
            return (Criteria) this;
        }

        public Criteria andPaytypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("paytype_id <=", value, "paytypeId");
            return (Criteria) this;
        }

        public Criteria andPaytypeIdIn(List<Integer> values) {
            addCriterion("paytype_id in", values, "paytypeId");
            return (Criteria) this;
        }

        public Criteria andPaytypeIdNotIn(List<Integer> values) {
            addCriterion("paytype_id not in", values, "paytypeId");
            return (Criteria) this;
        }

        public Criteria andPaytypeIdBetween(Integer value1, Integer value2) {
            addCriterion("paytype_id between", value1, value2, "paytypeId");
            return (Criteria) this;
        }

        public Criteria andPaytypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("paytype_id not between", value1, value2, "paytypeId");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnIsNull() {
            addCriterion("paytype_cn is null");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnIsNotNull() {
            addCriterion("paytype_cn is not null");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnEqualTo(String value) {
            addCriterion("paytype_cn =", value, "paytypeCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnNotEqualTo(String value) {
            addCriterion("paytype_cn <>", value, "paytypeCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnGreaterThan(String value) {
            addCriterion("paytype_cn >", value, "paytypeCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnGreaterThanOrEqualTo(String value) {
            addCriterion("paytype_cn >=", value, "paytypeCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnLessThan(String value) {
            addCriterion("paytype_cn <", value, "paytypeCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnLessThanOrEqualTo(String value) {
            addCriterion("paytype_cn <=", value, "paytypeCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnLike(String value) {
            addCriterion("paytype_cn like", value, "paytypeCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnNotLike(String value) {
            addCriterion("paytype_cn not like", value, "paytypeCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnIn(List<String> values) {
            addCriterion("paytype_cn in", values, "paytypeCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnNotIn(List<String> values) {
            addCriterion("paytype_cn not in", values, "paytypeCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnBetween(String value1, String value2) {
            addCriterion("paytype_cn between", value1, value2, "paytypeCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnNotBetween(String value1, String value2) {
            addCriterion("paytype_cn not between", value1, value2, "paytypeCn");
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

        public Criteria andCardtimeIsNull() {
            addCriterion("cardtime is null");
            return (Criteria) this;
        }

        public Criteria andCardtimeIsNotNull() {
            addCriterion("cardtime is not null");
            return (Criteria) this;
        }

        public Criteria andCardtimeEqualTo(Date value) {
            addCriterion("cardtime =", value, "cardtime");
            return (Criteria) this;
        }

        public Criteria andCardtimeNotEqualTo(Date value) {
            addCriterion("cardtime <>", value, "cardtime");
            return (Criteria) this;
        }

        public Criteria andCardtimeGreaterThan(Date value) {
            addCriterion("cardtime >", value, "cardtime");
            return (Criteria) this;
        }

        public Criteria andCardtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cardtime >=", value, "cardtime");
            return (Criteria) this;
        }

        public Criteria andCardtimeLessThan(Date value) {
            addCriterion("cardtime <", value, "cardtime");
            return (Criteria) this;
        }

        public Criteria andCardtimeLessThanOrEqualTo(Date value) {
            addCriterion("cardtime <=", value, "cardtime");
            return (Criteria) this;
        }

        public Criteria andCardtimeIn(List<Date> values) {
            addCriterion("cardtime in", values, "cardtime");
            return (Criteria) this;
        }

        public Criteria andCardtimeNotIn(List<Date> values) {
            addCriterion("cardtime not in", values, "cardtime");
            return (Criteria) this;
        }

        public Criteria andCardtimeBetween(Date value1, Date value2) {
            addCriterion("cardtime between", value1, value2, "cardtime");
            return (Criteria) this;
        }

        public Criteria andCardtimeNotBetween(Date value1, Date value2) {
            addCriterion("cardtime not between", value1, value2, "cardtime");
            return (Criteria) this;
        }

        public Criteria andIsUploadIsNull() {
            addCriterion("is_upload is null");
            return (Criteria) this;
        }

        public Criteria andIsUploadIsNotNull() {
            addCriterion("is_upload is not null");
            return (Criteria) this;
        }

        public Criteria andIsUploadEqualTo(Byte value) {
            addCriterion("is_upload =", value, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadNotEqualTo(Byte value) {
            addCriterion("is_upload <>", value, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadGreaterThan(Byte value) {
            addCriterion("is_upload >", value, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_upload >=", value, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadLessThan(Byte value) {
            addCriterion("is_upload <", value, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadLessThanOrEqualTo(Byte value) {
            addCriterion("is_upload <=", value, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadIn(List<Byte> values) {
            addCriterion("is_upload in", values, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadNotIn(List<Byte> values) {
            addCriterion("is_upload not in", values, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadBetween(Byte value1, Byte value2) {
            addCriterion("is_upload between", value1, value2, "isUpload");
            return (Criteria) this;
        }

        public Criteria andIsUploadNotBetween(Byte value1, Byte value2) {
            addCriterion("is_upload not between", value1, value2, "isUpload");
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

        public Criteria andOperatorNameLikeInsensitive(String value) {
            addCriterion("upper(operator_name) like", value.toUpperCase(), "operatorName");
            return (Criteria) this;
        }

        public Criteria andCheckOperatorNameLikeInsensitive(String value) {
            addCriterion("upper(check_operator_name) like", value.toUpperCase(), "checkOperatorName");
            return (Criteria) this;
        }

        public Criteria andPaywayCnLikeInsensitive(String value) {
            addCriterion("upper(payway_cn) like", value.toUpperCase(), "paywayCn");
            return (Criteria) this;
        }

        public Criteria andPaytypeCnLikeInsensitive(String value) {
            addCriterion("upper(paytype_cn) like", value.toUpperCase(), "paytypeCn");
            return (Criteria) this;
        }

        public Criteria andMemoLikeInsensitive(String value) {
            addCriterion("upper(memo) like", value.toUpperCase(), "memo");
            return (Criteria) this;
        }

        public Criteria andOssUrlLikeInsensitive(String value) {
            addCriterion("upper(oss_url) like", value.toUpperCase(), "ossUrl");
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