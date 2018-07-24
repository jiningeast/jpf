package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayCloudDfTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayCloudDfTaskExample() {
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

        public Criteria andBatchidIsNull() {
            addCriterion("batchid is null");
            return (Criteria) this;
        }

        public Criteria andBatchidIsNotNull() {
            addCriterion("batchid is not null");
            return (Criteria) this;
        }

        public Criteria andBatchidEqualTo(String value) {
            addCriterion("batchid =", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidNotEqualTo(String value) {
            addCriterion("batchid <>", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidGreaterThan(String value) {
            addCriterion("batchid >", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidGreaterThanOrEqualTo(String value) {
            addCriterion("batchid >=", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidLessThan(String value) {
            addCriterion("batchid <", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidLessThanOrEqualTo(String value) {
            addCriterion("batchid <=", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidLike(String value) {
            addCriterion("batchid like", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidNotLike(String value) {
            addCriterion("batchid not like", value, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidIn(List<String> values) {
            addCriterion("batchid in", values, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidNotIn(List<String> values) {
            addCriterion("batchid not in", values, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidBetween(String value1, String value2) {
            addCriterion("batchid between", value1, value2, "batchid");
            return (Criteria) this;
        }

        public Criteria andBatchidNotBetween(String value1, String value2) {
            addCriterion("batchid not between", value1, value2, "batchid");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoIsNull() {
            addCriterion("request_batchno is null");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoIsNotNull() {
            addCriterion("request_batchno is not null");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoEqualTo(String value) {
            addCriterion("request_batchno =", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoNotEqualTo(String value) {
            addCriterion("request_batchno <>", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoGreaterThan(String value) {
            addCriterion("request_batchno >", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoGreaterThanOrEqualTo(String value) {
            addCriterion("request_batchno >=", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoLessThan(String value) {
            addCriterion("request_batchno <", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoLessThanOrEqualTo(String value) {
            addCriterion("request_batchno <=", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoLike(String value) {
            addCriterion("request_batchno like", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoNotLike(String value) {
            addCriterion("request_batchno not like", value, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoIn(List<String> values) {
            addCriterion("request_batchno in", values, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoNotIn(List<String> values) {
            addCriterion("request_batchno not in", values, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoBetween(String value1, String value2) {
            addCriterion("request_batchno between", value1, value2, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoNotBetween(String value1, String value2) {
            addCriterion("request_batchno not between", value1, value2, "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdIsNull() {
            addCriterion("request_df_id is null");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdIsNotNull() {
            addCriterion("request_df_id is not null");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdEqualTo(String value) {
            addCriterion("request_df_id =", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdNotEqualTo(String value) {
            addCriterion("request_df_id <>", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdGreaterThan(String value) {
            addCriterion("request_df_id >", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdGreaterThanOrEqualTo(String value) {
            addCriterion("request_df_id >=", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdLessThan(String value) {
            addCriterion("request_df_id <", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdLessThanOrEqualTo(String value) {
            addCriterion("request_df_id <=", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdLike(String value) {
            addCriterion("request_df_id like", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdNotLike(String value) {
            addCriterion("request_df_id not like", value, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdIn(List<String> values) {
            addCriterion("request_df_id in", values, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdNotIn(List<String> values) {
            addCriterion("request_df_id not in", values, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdBetween(String value1, String value2) {
            addCriterion("request_df_id between", value1, value2, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdNotBetween(String value1, String value2) {
            addCriterion("request_df_id not between", value1, value2, "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestStrIsNull() {
            addCriterion("request_str is null");
            return (Criteria) this;
        }

        public Criteria andRequestStrIsNotNull() {
            addCriterion("request_str is not null");
            return (Criteria) this;
        }

        public Criteria andRequestStrEqualTo(String value) {
            addCriterion("request_str =", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrNotEqualTo(String value) {
            addCriterion("request_str <>", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrGreaterThan(String value) {
            addCriterion("request_str >", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrGreaterThanOrEqualTo(String value) {
            addCriterion("request_str >=", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrLessThan(String value) {
            addCriterion("request_str <", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrLessThanOrEqualTo(String value) {
            addCriterion("request_str <=", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrLike(String value) {
            addCriterion("request_str like", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrNotLike(String value) {
            addCriterion("request_str not like", value, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrIn(List<String> values) {
            addCriterion("request_str in", values, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrNotIn(List<String> values) {
            addCriterion("request_str not in", values, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrBetween(String value1, String value2) {
            addCriterion("request_str between", value1, value2, "requestStr");
            return (Criteria) this;
        }

        public Criteria andRequestStrNotBetween(String value1, String value2) {
            addCriterion("request_str not between", value1, value2, "requestStr");
            return (Criteria) this;
        }

        public Criteria andOrderCountIsNull() {
            addCriterion("order_count is null");
            return (Criteria) this;
        }

        public Criteria andOrderCountIsNotNull() {
            addCriterion("order_count is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCountEqualTo(Integer value) {
            addCriterion("order_count =", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotEqualTo(Integer value) {
            addCriterion("order_count <>", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountGreaterThan(Integer value) {
            addCriterion("order_count >", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_count >=", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLessThan(Integer value) {
            addCriterion("order_count <", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLessThanOrEqualTo(Integer value) {
            addCriterion("order_count <=", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountIn(List<Integer> values) {
            addCriterion("order_count in", values, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotIn(List<Integer> values) {
            addCriterion("order_count not in", values, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountBetween(Integer value1, Integer value2) {
            addCriterion("order_count between", value1, value2, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotBetween(Integer value1, Integer value2) {
            addCriterion("order_count not between", value1, value2, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyIsNull() {
            addCriterion("order_money is null");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyIsNotNull() {
            addCriterion("order_money is not null");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyEqualTo(BigDecimal value) {
            addCriterion("order_money =", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyNotEqualTo(BigDecimal value) {
            addCriterion("order_money <>", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyGreaterThan(BigDecimal value) {
            addCriterion("order_money >", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_money >=", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyLessThan(BigDecimal value) {
            addCriterion("order_money <", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_money <=", value, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyIn(List<BigDecimal> values) {
            addCriterion("order_money in", values, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyNotIn(List<BigDecimal> values) {
            addCriterion("order_money not in", values, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_money between", value1, value2, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andOrderMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_money not between", value1, value2, "orderMoney");
            return (Criteria) this;
        }

        public Criteria andAlreadyCountIsNull() {
            addCriterion("already_count is null");
            return (Criteria) this;
        }

        public Criteria andAlreadyCountIsNotNull() {
            addCriterion("already_count is not null");
            return (Criteria) this;
        }

        public Criteria andAlreadyCountEqualTo(Integer value) {
            addCriterion("already_count =", value, "alreadyCount");
            return (Criteria) this;
        }

        public Criteria andAlreadyCountNotEqualTo(Integer value) {
            addCriterion("already_count <>", value, "alreadyCount");
            return (Criteria) this;
        }

        public Criteria andAlreadyCountGreaterThan(Integer value) {
            addCriterion("already_count >", value, "alreadyCount");
            return (Criteria) this;
        }

        public Criteria andAlreadyCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("already_count >=", value, "alreadyCount");
            return (Criteria) this;
        }

        public Criteria andAlreadyCountLessThan(Integer value) {
            addCriterion("already_count <", value, "alreadyCount");
            return (Criteria) this;
        }

        public Criteria andAlreadyCountLessThanOrEqualTo(Integer value) {
            addCriterion("already_count <=", value, "alreadyCount");
            return (Criteria) this;
        }

        public Criteria andAlreadyCountIn(List<Integer> values) {
            addCriterion("already_count in", values, "alreadyCount");
            return (Criteria) this;
        }

        public Criteria andAlreadyCountNotIn(List<Integer> values) {
            addCriterion("already_count not in", values, "alreadyCount");
            return (Criteria) this;
        }

        public Criteria andAlreadyCountBetween(Integer value1, Integer value2) {
            addCriterion("already_count between", value1, value2, "alreadyCount");
            return (Criteria) this;
        }

        public Criteria andAlreadyCountNotBetween(Integer value1, Integer value2) {
            addCriterion("already_count not between", value1, value2, "alreadyCount");
            return (Criteria) this;
        }

        public Criteria andAlreadyMoneyIsNull() {
            addCriterion("already_money is null");
            return (Criteria) this;
        }

        public Criteria andAlreadyMoneyIsNotNull() {
            addCriterion("already_money is not null");
            return (Criteria) this;
        }

        public Criteria andAlreadyMoneyEqualTo(BigDecimal value) {
            addCriterion("already_money =", value, "alreadyMoney");
            return (Criteria) this;
        }

        public Criteria andAlreadyMoneyNotEqualTo(BigDecimal value) {
            addCriterion("already_money <>", value, "alreadyMoney");
            return (Criteria) this;
        }

        public Criteria andAlreadyMoneyGreaterThan(BigDecimal value) {
            addCriterion("already_money >", value, "alreadyMoney");
            return (Criteria) this;
        }

        public Criteria andAlreadyMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("already_money >=", value, "alreadyMoney");
            return (Criteria) this;
        }

        public Criteria andAlreadyMoneyLessThan(BigDecimal value) {
            addCriterion("already_money <", value, "alreadyMoney");
            return (Criteria) this;
        }

        public Criteria andAlreadyMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("already_money <=", value, "alreadyMoney");
            return (Criteria) this;
        }

        public Criteria andAlreadyMoneyIn(List<BigDecimal> values) {
            addCriterion("already_money in", values, "alreadyMoney");
            return (Criteria) this;
        }

        public Criteria andAlreadyMoneyNotIn(List<BigDecimal> values) {
            addCriterion("already_money not in", values, "alreadyMoney");
            return (Criteria) this;
        }

        public Criteria andAlreadyMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("already_money between", value1, value2, "alreadyMoney");
            return (Criteria) this;
        }

        public Criteria andAlreadyMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("already_money not between", value1, value2, "alreadyMoney");
            return (Criteria) this;
        }

        public Criteria andFailCountIsNull() {
            addCriterion("fail_count is null");
            return (Criteria) this;
        }

        public Criteria andFailCountIsNotNull() {
            addCriterion("fail_count is not null");
            return (Criteria) this;
        }

        public Criteria andFailCountEqualTo(Integer value) {
            addCriterion("fail_count =", value, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountNotEqualTo(Integer value) {
            addCriterion("fail_count <>", value, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountGreaterThan(Integer value) {
            addCriterion("fail_count >", value, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("fail_count >=", value, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountLessThan(Integer value) {
            addCriterion("fail_count <", value, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountLessThanOrEqualTo(Integer value) {
            addCriterion("fail_count <=", value, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountIn(List<Integer> values) {
            addCriterion("fail_count in", values, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountNotIn(List<Integer> values) {
            addCriterion("fail_count not in", values, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountBetween(Integer value1, Integer value2) {
            addCriterion("fail_count between", value1, value2, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailCountNotBetween(Integer value1, Integer value2) {
            addCriterion("fail_count not between", value1, value2, "failCount");
            return (Criteria) this;
        }

        public Criteria andFailMoneyIsNull() {
            addCriterion("fail_money is null");
            return (Criteria) this;
        }

        public Criteria andFailMoneyIsNotNull() {
            addCriterion("fail_money is not null");
            return (Criteria) this;
        }

        public Criteria andFailMoneyEqualTo(BigDecimal value) {
            addCriterion("fail_money =", value, "failMoney");
            return (Criteria) this;
        }

        public Criteria andFailMoneyNotEqualTo(BigDecimal value) {
            addCriterion("fail_money <>", value, "failMoney");
            return (Criteria) this;
        }

        public Criteria andFailMoneyGreaterThan(BigDecimal value) {
            addCriterion("fail_money >", value, "failMoney");
            return (Criteria) this;
        }

        public Criteria andFailMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fail_money >=", value, "failMoney");
            return (Criteria) this;
        }

        public Criteria andFailMoneyLessThan(BigDecimal value) {
            addCriterion("fail_money <", value, "failMoney");
            return (Criteria) this;
        }

        public Criteria andFailMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fail_money <=", value, "failMoney");
            return (Criteria) this;
        }

        public Criteria andFailMoneyIn(List<BigDecimal> values) {
            addCriterion("fail_money in", values, "failMoney");
            return (Criteria) this;
        }

        public Criteria andFailMoneyNotIn(List<BigDecimal> values) {
            addCriterion("fail_money not in", values, "failMoney");
            return (Criteria) this;
        }

        public Criteria andFailMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fail_money between", value1, value2, "failMoney");
            return (Criteria) this;
        }

        public Criteria andFailMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fail_money not between", value1, value2, "failMoney");
            return (Criteria) this;
        }

        public Criteria andInsertStatusIsNull() {
            addCriterion("insert_status is null");
            return (Criteria) this;
        }

        public Criteria andInsertStatusIsNotNull() {
            addCriterion("insert_status is not null");
            return (Criteria) this;
        }

        public Criteria andInsertStatusEqualTo(Integer value) {
            addCriterion("insert_status =", value, "insertStatus");
            return (Criteria) this;
        }

        public Criteria andInsertStatusNotEqualTo(Integer value) {
            addCriterion("insert_status <>", value, "insertStatus");
            return (Criteria) this;
        }

        public Criteria andInsertStatusGreaterThan(Integer value) {
            addCriterion("insert_status >", value, "insertStatus");
            return (Criteria) this;
        }

        public Criteria andInsertStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("insert_status >=", value, "insertStatus");
            return (Criteria) this;
        }

        public Criteria andInsertStatusLessThan(Integer value) {
            addCriterion("insert_status <", value, "insertStatus");
            return (Criteria) this;
        }

        public Criteria andInsertStatusLessThanOrEqualTo(Integer value) {
            addCriterion("insert_status <=", value, "insertStatus");
            return (Criteria) this;
        }

        public Criteria andInsertStatusIn(List<Integer> values) {
            addCriterion("insert_status in", values, "insertStatus");
            return (Criteria) this;
        }

        public Criteria andInsertStatusNotIn(List<Integer> values) {
            addCriterion("insert_status not in", values, "insertStatus");
            return (Criteria) this;
        }

        public Criteria andInsertStatusBetween(Integer value1, Integer value2) {
            addCriterion("insert_status between", value1, value2, "insertStatus");
            return (Criteria) this;
        }

        public Criteria andInsertStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("insert_status not between", value1, value2, "insertStatus");
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

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Date value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Date value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Date value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Date value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Date> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Date> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Date value1, Date value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("updated not between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andBatchidLikeInsensitive(String value) {
            addCriterion("upper(batchid) like", value.toUpperCase(), "batchid");
            return (Criteria) this;
        }

        public Criteria andRequestBatchnoLikeInsensitive(String value) {
            addCriterion("upper(request_batchno) like", value.toUpperCase(), "requestBatchno");
            return (Criteria) this;
        }

        public Criteria andRequestDfIdLikeInsensitive(String value) {
            addCriterion("upper(request_df_id) like", value.toUpperCase(), "requestDfId");
            return (Criteria) this;
        }

        public Criteria andRequestStrLikeInsensitive(String value) {
            addCriterion("upper(request_str) like", value.toUpperCase(), "requestStr");
            return (Criteria) this;
        }

        public Criteria andRemarksLikeInsensitive(String value) {
            addCriterion("upper(remarks) like", value.toUpperCase(), "remarks");
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