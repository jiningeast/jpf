package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayCloudRechargeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected long pageNo;

    protected long pageSize;

    protected List<Criteria> oredCriteria;

    /**
     *
     */
    public PayCloudRechargeExample() {
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

        public Criteria andNeedidIsNull() {
            addCriterion("needid is null");
            return (Criteria) this;
        }

        public Criteria andNeedidIsNotNull() {
            addCriterion("needid is not null");
            return (Criteria) this;
        }

        public Criteria andNeedidEqualTo(String value) {
            addCriterion("needid =", value, "needid");
            return (Criteria) this;
        }

        public Criteria andNeedidNotEqualTo(String value) {
            addCriterion("needid <>", value, "needid");
            return (Criteria) this;
        }

        public Criteria andNeedidGreaterThan(String value) {
            addCriterion("needid >", value, "needid");
            return (Criteria) this;
        }

        public Criteria andNeedidGreaterThanOrEqualTo(String value) {
            addCriterion("needid >=", value, "needid");
            return (Criteria) this;
        }

        public Criteria andNeedidLessThan(String value) {
            addCriterion("needid <", value, "needid");
            return (Criteria) this;
        }

        public Criteria andNeedidLessThanOrEqualTo(String value) {
            addCriterion("needid <=", value, "needid");
            return (Criteria) this;
        }

        public Criteria andNeedidIn(List<String> values) {
            addCriterion("needid in", values, "needid");
            return (Criteria) this;
        }

        public Criteria andNeedidNotIn(List<String> values) {
            addCriterion("needid not in", values, "needid");
            return (Criteria) this;
        }

        public Criteria andNeedidBetween(String value1, String value2) {
            addCriterion("needid between", value1, value2, "needid");
            return (Criteria) this;
        }

        public Criteria andNeedidNotBetween(String value1, String value2) {
            addCriterion("needid not between", value1, value2, "needid");
            return (Criteria) this;
        }

        public Criteria andPactnoIsNull() {
            addCriterion("pactno is null");
            return (Criteria) this;
        }

        public Criteria andPactnoIsNotNull() {
            addCriterion("pactno is not null");
            return (Criteria) this;
        }

        public Criteria andPactnoEqualTo(String value) {
            addCriterion("pactno =", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoNotEqualTo(String value) {
            addCriterion("pactno <>", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoGreaterThan(String value) {
            addCriterion("pactno >", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoGreaterThanOrEqualTo(String value) {
            addCriterion("pactno >=", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoLessThan(String value) {
            addCriterion("pactno <", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoLessThanOrEqualTo(String value) {
            addCriterion("pactno <=", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoLike(String value) {
            addCriterion("pactno like", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoNotLike(String value) {
            addCriterion("pactno not like", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoIn(List<String> values) {
            addCriterion("pactno in", values, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoNotIn(List<String> values) {
            addCriterion("pactno not in", values, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoBetween(String value1, String value2) {
            addCriterion("pactno between", value1, value2, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoNotBetween(String value1, String value2) {
            addCriterion("pactno not between", value1, value2, "pactno");
            return (Criteria) this;
        }

        public Criteria andFidIsNull() {
            addCriterion("fid is null");
            return (Criteria) this;
        }

        public Criteria andFidIsNotNull() {
            addCriterion("fid is not null");
            return (Criteria) this;
        }

        public Criteria andFidEqualTo(String value) {
            addCriterion("fid =", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotEqualTo(String value) {
            addCriterion("fid <>", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThan(String value) {
            addCriterion("fid >", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidGreaterThanOrEqualTo(String value) {
            addCriterion("fid >=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThan(String value) {
            addCriterion("fid <", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLessThanOrEqualTo(String value) {
            addCriterion("fid <=", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidLike(String value) {
            addCriterion("fid like", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotLike(String value) {
            addCriterion("fid not like", value, "fid");
            return (Criteria) this;
        }

        public Criteria andFidIn(List<String> values) {
            addCriterion("fid in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotIn(List<String> values) {
            addCriterion("fid not in", values, "fid");
            return (Criteria) this;
        }

        public Criteria andFidBetween(String value1, String value2) {
            addCriterion("fid between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andFidNotBetween(String value1, String value2) {
            addCriterion("fid not between", value1, value2, "fid");
            return (Criteria) this;
        }

        public Criteria andAgentNoIsNull() {
            addCriterion("agent_no is null");
            return (Criteria) this;
        }

        public Criteria andAgentNoIsNotNull() {
            addCriterion("agent_no is not null");
            return (Criteria) this;
        }

        public Criteria andAgentNoEqualTo(String value) {
            addCriterion("agent_no =", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoNotEqualTo(String value) {
            addCriterion("agent_no <>", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoGreaterThan(String value) {
            addCriterion("agent_no >", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoGreaterThanOrEqualTo(String value) {
            addCriterion("agent_no >=", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoLessThan(String value) {
            addCriterion("agent_no <", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoLessThanOrEqualTo(String value) {
            addCriterion("agent_no <=", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoLike(String value) {
            addCriterion("agent_no like", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoNotLike(String value) {
            addCriterion("agent_no not like", value, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoIn(List<String> values) {
            addCriterion("agent_no in", values, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoNotIn(List<String> values) {
            addCriterion("agent_no not in", values, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoBetween(String value1, String value2) {
            addCriterion("agent_no between", value1, value2, "agentNo");
            return (Criteria) this;
        }

        public Criteria andAgentNoNotBetween(String value1, String value2) {
            addCriterion("agent_no not between", value1, value2, "agentNo");
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

        public Criteria andPaywayIsNull() {
            addCriterion("payway is null");
            return (Criteria) this;
        }

        public Criteria andPaywayIsNotNull() {
            addCriterion("payway is not null");
            return (Criteria) this;
        }

        public Criteria andPaywayEqualTo(Byte value) {
            addCriterion("payway =", value, "payway");
            return (Criteria) this;
        }

        public Criteria andPaywayNotEqualTo(Byte value) {
            addCriterion("payway <>", value, "payway");
            return (Criteria) this;
        }

        public Criteria andPaywayGreaterThan(Byte value) {
            addCriterion("payway >", value, "payway");
            return (Criteria) this;
        }

        public Criteria andPaywayGreaterThanOrEqualTo(Byte value) {
            addCriterion("payway >=", value, "payway");
            return (Criteria) this;
        }

        public Criteria andPaywayLessThan(Byte value) {
            addCriterion("payway <", value, "payway");
            return (Criteria) this;
        }

        public Criteria andPaywayLessThanOrEqualTo(Byte value) {
            addCriterion("payway <=", value, "payway");
            return (Criteria) this;
        }

        public Criteria andPaywayIn(List<Byte> values) {
            addCriterion("payway in", values, "payway");
            return (Criteria) this;
        }

        public Criteria andPaywayNotIn(List<Byte> values) {
            addCriterion("payway not in", values, "payway");
            return (Criteria) this;
        }

        public Criteria andPaywayBetween(Byte value1, Byte value2) {
            addCriterion("payway between", value1, value2, "payway");
            return (Criteria) this;
        }

        public Criteria andPaywayNotBetween(Byte value1, Byte value2) {
            addCriterion("payway not between", value1, value2, "payway");
            return (Criteria) this;
        }

        public Criteria andEmployeeUidIsNull() {
            addCriterion("employee_uid is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeUidIsNotNull() {
            addCriterion("employee_uid is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeUidEqualTo(String value) {
            addCriterion("employee_uid =", value, "employeeUid");
            return (Criteria) this;
        }

        public Criteria andEmployeeUidNotEqualTo(String value) {
            addCriterion("employee_uid <>", value, "employeeUid");
            return (Criteria) this;
        }

        public Criteria andEmployeeUidGreaterThan(String value) {
            addCriterion("employee_uid >", value, "employeeUid");
            return (Criteria) this;
        }

        public Criteria andEmployeeUidGreaterThanOrEqualTo(String value) {
            addCriterion("employee_uid >=", value, "employeeUid");
            return (Criteria) this;
        }

        public Criteria andEmployeeUidLessThan(String value) {
            addCriterion("employee_uid <", value, "employeeUid");
            return (Criteria) this;
        }

        public Criteria andEmployeeUidLessThanOrEqualTo(String value) {
            addCriterion("employee_uid <=", value, "employeeUid");
            return (Criteria) this;
        }

        public Criteria andEmployeeUidIn(List<String> values) {
            addCriterion("employee_uid in", values, "employeeUid");
            return (Criteria) this;
        }

        public Criteria andEmployeeUidNotIn(List<String> values) {
            addCriterion("employee_uid not in", values, "employeeUid");
            return (Criteria) this;
        }

        public Criteria andEmployeeUidBetween(String value1, String value2) {
            addCriterion("employee_uid between", value1, value2, "employeeUid");
            return (Criteria) this;
        }

        public Criteria andEmployeeUidNotBetween(String value1, String value2) {
            addCriterion("employee_uid not between", value1, value2, "employeeUid");
            return (Criteria) this;
        }

        public Criteria andLinkphoneIsNull() {
            addCriterion("linkphone is null");
            return (Criteria) this;
        }

        public Criteria andLinkphoneIsNotNull() {
            addCriterion("linkphone is not null");
            return (Criteria) this;
        }

        public Criteria andLinkphoneEqualTo(String value) {
            addCriterion("linkphone =", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneNotEqualTo(String value) {
            addCriterion("linkphone <>", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneGreaterThan(String value) {
            addCriterion("linkphone >", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneGreaterThanOrEqualTo(String value) {
            addCriterion("linkphone >=", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneLessThan(String value) {
            addCriterion("linkphone <", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneLessThanOrEqualTo(String value) {
            addCriterion("linkphone <=", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneLike(String value) {
            addCriterion("linkphone like", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneNotLike(String value) {
            addCriterion("linkphone not like", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneIn(List<String> values) {
            addCriterion("linkphone in", values, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneNotIn(List<String> values) {
            addCriterion("linkphone not in", values, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneBetween(String value1, String value2) {
            addCriterion("linkphone between", value1, value2, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneNotBetween(String value1, String value2) {
            addCriterion("linkphone not between", value1, value2, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkemailIsNull() {
            addCriterion("linkemail is null");
            return (Criteria) this;
        }

        public Criteria andLinkemailIsNotNull() {
            addCriterion("linkemail is not null");
            return (Criteria) this;
        }

        public Criteria andLinkemailEqualTo(String value) {
            addCriterion("linkemail =", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailNotEqualTo(String value) {
            addCriterion("linkemail <>", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailGreaterThan(String value) {
            addCriterion("linkemail >", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailGreaterThanOrEqualTo(String value) {
            addCriterion("linkemail >=", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailLessThan(String value) {
            addCriterion("linkemail <", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailLessThanOrEqualTo(String value) {
            addCriterion("linkemail <=", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailLike(String value) {
            addCriterion("linkemail like", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailNotLike(String value) {
            addCriterion("linkemail not like", value, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailIn(List<String> values) {
            addCriterion("linkemail in", values, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailNotIn(List<String> values) {
            addCriterion("linkemail not in", values, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailBetween(String value1, String value2) {
            addCriterion("linkemail between", value1, value2, "linkemail");
            return (Criteria) this;
        }

        public Criteria andLinkemailNotBetween(String value1, String value2) {
            addCriterion("linkemail not between", value1, value2, "linkemail");
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

        public Criteria andRealmoneyIsNull() {
            addCriterion("realmoney is null");
            return (Criteria) this;
        }

        public Criteria andRealmoneyIsNotNull() {
            addCriterion("realmoney is not null");
            return (Criteria) this;
        }

        public Criteria andRealmoneyEqualTo(BigDecimal value) {
            addCriterion("realmoney =", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyNotEqualTo(BigDecimal value) {
            addCriterion("realmoney <>", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyGreaterThan(BigDecimal value) {
            addCriterion("realmoney >", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("realmoney >=", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyLessThan(BigDecimal value) {
            addCriterion("realmoney <", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("realmoney <=", value, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyIn(List<BigDecimal> values) {
            addCriterion("realmoney in", values, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyNotIn(List<BigDecimal> values) {
            addCriterion("realmoney not in", values, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("realmoney between", value1, value2, "realmoney");
            return (Criteria) this;
        }

        public Criteria andRealmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("realmoney not between", value1, value2, "realmoney");
            return (Criteria) this;
        }

        public Criteria andFeemoneyIsNull() {
            addCriterion("feemoney is null");
            return (Criteria) this;
        }

        public Criteria andFeemoneyIsNotNull() {
            addCriterion("feemoney is not null");
            return (Criteria) this;
        }

        public Criteria andFeemoneyEqualTo(BigDecimal value) {
            addCriterion("feemoney =", value, "feemoney");
            return (Criteria) this;
        }

        public Criteria andFeemoneyNotEqualTo(BigDecimal value) {
            addCriterion("feemoney <>", value, "feemoney");
            return (Criteria) this;
        }

        public Criteria andFeemoneyGreaterThan(BigDecimal value) {
            addCriterion("feemoney >", value, "feemoney");
            return (Criteria) this;
        }

        public Criteria andFeemoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("feemoney >=", value, "feemoney");
            return (Criteria) this;
        }

        public Criteria andFeemoneyLessThan(BigDecimal value) {
            addCriterion("feemoney <", value, "feemoney");
            return (Criteria) this;
        }

        public Criteria andFeemoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("feemoney <=", value, "feemoney");
            return (Criteria) this;
        }

        public Criteria andFeemoneyIn(List<BigDecimal> values) {
            addCriterion("feemoney in", values, "feemoney");
            return (Criteria) this;
        }

        public Criteria andFeemoneyNotIn(List<BigDecimal> values) {
            addCriterion("feemoney not in", values, "feemoney");
            return (Criteria) this;
        }

        public Criteria andFeemoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("feemoney between", value1, value2, "feemoney");
            return (Criteria) this;
        }

        public Criteria andFeemoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("feemoney not between", value1, value2, "feemoney");
            return (Criteria) this;
        }

        public Criteria andAgentFeemoneyIsNull() {
            addCriterion("agent_feemoney is null");
            return (Criteria) this;
        }

        public Criteria andAgentFeemoneyIsNotNull() {
            addCriterion("agent_feemoney is not null");
            return (Criteria) this;
        }

        public Criteria andAgentFeemoneyEqualTo(BigDecimal value) {
            addCriterion("agent_feemoney =", value, "agentFeemoney");
            return (Criteria) this;
        }

        public Criteria andAgentFeemoneyNotEqualTo(BigDecimal value) {
            addCriterion("agent_feemoney <>", value, "agentFeemoney");
            return (Criteria) this;
        }

        public Criteria andAgentFeemoneyGreaterThan(BigDecimal value) {
            addCriterion("agent_feemoney >", value, "agentFeemoney");
            return (Criteria) this;
        }

        public Criteria andAgentFeemoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agent_feemoney >=", value, "agentFeemoney");
            return (Criteria) this;
        }

        public Criteria andAgentFeemoneyLessThan(BigDecimal value) {
            addCriterion("agent_feemoney <", value, "agentFeemoney");
            return (Criteria) this;
        }

        public Criteria andAgentFeemoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agent_feemoney <=", value, "agentFeemoney");
            return (Criteria) this;
        }

        public Criteria andAgentFeemoneyIn(List<BigDecimal> values) {
            addCriterion("agent_feemoney in", values, "agentFeemoney");
            return (Criteria) this;
        }

        public Criteria andAgentFeemoneyNotIn(List<BigDecimal> values) {
            addCriterion("agent_feemoney not in", values, "agentFeemoney");
            return (Criteria) this;
        }

        public Criteria andAgentFeemoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agent_feemoney between", value1, value2, "agentFeemoney");
            return (Criteria) this;
        }

        public Criteria andAgentFeemoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agent_feemoney not between", value1, value2, "agentFeemoney");
            return (Criteria) this;
        }

        public Criteria andSalesFeemoneyIsNull() {
            addCriterion("sales_feemoney is null");
            return (Criteria) this;
        }

        public Criteria andSalesFeemoneyIsNotNull() {
            addCriterion("sales_feemoney is not null");
            return (Criteria) this;
        }

        public Criteria andSalesFeemoneyEqualTo(BigDecimal value) {
            addCriterion("sales_feemoney =", value, "salesFeemoney");
            return (Criteria) this;
        }

        public Criteria andSalesFeemoneyNotEqualTo(BigDecimal value) {
            addCriterion("sales_feemoney <>", value, "salesFeemoney");
            return (Criteria) this;
        }

        public Criteria andSalesFeemoneyGreaterThan(BigDecimal value) {
            addCriterion("sales_feemoney >", value, "salesFeemoney");
            return (Criteria) this;
        }

        public Criteria andSalesFeemoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_feemoney >=", value, "salesFeemoney");
            return (Criteria) this;
        }

        public Criteria andSalesFeemoneyLessThan(BigDecimal value) {
            addCriterion("sales_feemoney <", value, "salesFeemoney");
            return (Criteria) this;
        }

        public Criteria andSalesFeemoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_feemoney <=", value, "salesFeemoney");
            return (Criteria) this;
        }

        public Criteria andSalesFeemoneyIn(List<BigDecimal> values) {
            addCriterion("sales_feemoney in", values, "salesFeemoney");
            return (Criteria) this;
        }

        public Criteria andSalesFeemoneyNotIn(List<BigDecimal> values) {
            addCriterion("sales_feemoney not in", values, "salesFeemoney");
            return (Criteria) this;
        }

        public Criteria andSalesFeemoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_feemoney between", value1, value2, "salesFeemoney");
            return (Criteria) this;
        }

        public Criteria andSalesFeemoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_feemoney not between", value1, value2, "salesFeemoney");
            return (Criteria) this;
        }

        public Criteria andAgentRateIsNull() {
            addCriterion("agent_rate is null");
            return (Criteria) this;
        }

        public Criteria andAgentRateIsNotNull() {
            addCriterion("agent_rate is not null");
            return (Criteria) this;
        }

        public Criteria andAgentRateEqualTo(BigDecimal value) {
            addCriterion("agent_rate =", value, "agentRate");
            return (Criteria) this;
        }

        public Criteria andAgentRateNotEqualTo(BigDecimal value) {
            addCriterion("agent_rate <>", value, "agentRate");
            return (Criteria) this;
        }

        public Criteria andAgentRateGreaterThan(BigDecimal value) {
            addCriterion("agent_rate >", value, "agentRate");
            return (Criteria) this;
        }

        public Criteria andAgentRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("agent_rate >=", value, "agentRate");
            return (Criteria) this;
        }

        public Criteria andAgentRateLessThan(BigDecimal value) {
            addCriterion("agent_rate <", value, "agentRate");
            return (Criteria) this;
        }

        public Criteria andAgentRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("agent_rate <=", value, "agentRate");
            return (Criteria) this;
        }

        public Criteria andAgentRateIn(List<BigDecimal> values) {
            addCriterion("agent_rate in", values, "agentRate");
            return (Criteria) this;
        }

        public Criteria andAgentRateNotIn(List<BigDecimal> values) {
            addCriterion("agent_rate not in", values, "agentRate");
            return (Criteria) this;
        }

        public Criteria andAgentRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agent_rate between", value1, value2, "agentRate");
            return (Criteria) this;
        }

        public Criteria andAgentRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("agent_rate not between", value1, value2, "agentRate");
            return (Criteria) this;
        }

        public Criteria andSalesRateIsNull() {
            addCriterion("sales_rate is null");
            return (Criteria) this;
        }

        public Criteria andSalesRateIsNotNull() {
            addCriterion("sales_rate is not null");
            return (Criteria) this;
        }

        public Criteria andSalesRateEqualTo(BigDecimal value) {
            addCriterion("sales_rate =", value, "salesRate");
            return (Criteria) this;
        }

        public Criteria andSalesRateNotEqualTo(BigDecimal value) {
            addCriterion("sales_rate <>", value, "salesRate");
            return (Criteria) this;
        }

        public Criteria andSalesRateGreaterThan(BigDecimal value) {
            addCriterion("sales_rate >", value, "salesRate");
            return (Criteria) this;
        }

        public Criteria andSalesRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_rate >=", value, "salesRate");
            return (Criteria) this;
        }

        public Criteria andSalesRateLessThan(BigDecimal value) {
            addCriterion("sales_rate <", value, "salesRate");
            return (Criteria) this;
        }

        public Criteria andSalesRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sales_rate <=", value, "salesRate");
            return (Criteria) this;
        }

        public Criteria andSalesRateIn(List<BigDecimal> values) {
            addCriterion("sales_rate in", values, "salesRate");
            return (Criteria) this;
        }

        public Criteria andSalesRateNotIn(List<BigDecimal> values) {
            addCriterion("sales_rate not in", values, "salesRate");
            return (Criteria) this;
        }

        public Criteria andSalesRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_rate between", value1, value2, "salesRate");
            return (Criteria) this;
        }

        public Criteria andSalesRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sales_rate not between", value1, value2, "salesRate");
            return (Criteria) this;
        }

        public Criteria andImgurlIsNull() {
            addCriterion("imgurl is null");
            return (Criteria) this;
        }

        public Criteria andImgurlIsNotNull() {
            addCriterion("imgurl is not null");
            return (Criteria) this;
        }

        public Criteria andImgurlEqualTo(String value) {
            addCriterion("imgurl =", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotEqualTo(String value) {
            addCriterion("imgurl <>", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlGreaterThan(String value) {
            addCriterion("imgurl >", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlGreaterThanOrEqualTo(String value) {
            addCriterion("imgurl >=", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLessThan(String value) {
            addCriterion("imgurl <", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLessThanOrEqualTo(String value) {
            addCriterion("imgurl <=", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlLike(String value) {
            addCriterion("imgurl like", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotLike(String value) {
            addCriterion("imgurl not like", value, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlIn(List<String> values) {
            addCriterion("imgurl in", values, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotIn(List<String> values) {
            addCriterion("imgurl not in", values, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlBetween(String value1, String value2) {
            addCriterion("imgurl between", value1, value2, "imgurl");
            return (Criteria) this;
        }

        public Criteria andImgurlNotBetween(String value1, String value2) {
            addCriterion("imgurl not between", value1, value2, "imgurl");
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

        public Criteria andShenhetimeIsNull() {
            addCriterion("shenhetime is null");
            return (Criteria) this;
        }

        public Criteria andShenhetimeIsNotNull() {
            addCriterion("shenhetime is not null");
            return (Criteria) this;
        }

        public Criteria andShenhetimeEqualTo(Date value) {
            addCriterion("shenhetime =", value, "shenhetime");
            return (Criteria) this;
        }

        public Criteria andShenhetimeNotEqualTo(Date value) {
            addCriterion("shenhetime <>", value, "shenhetime");
            return (Criteria) this;
        }

        public Criteria andShenhetimeGreaterThan(Date value) {
            addCriterion("shenhetime >", value, "shenhetime");
            return (Criteria) this;
        }

        public Criteria andShenhetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("shenhetime >=", value, "shenhetime");
            return (Criteria) this;
        }

        public Criteria andShenhetimeLessThan(Date value) {
            addCriterion("shenhetime <", value, "shenhetime");
            return (Criteria) this;
        }

        public Criteria andShenhetimeLessThanOrEqualTo(Date value) {
            addCriterion("shenhetime <=", value, "shenhetime");
            return (Criteria) this;
        }

        public Criteria andShenhetimeIn(List<Date> values) {
            addCriterion("shenhetime in", values, "shenhetime");
            return (Criteria) this;
        }

        public Criteria andShenhetimeNotIn(List<Date> values) {
            addCriterion("shenhetime not in", values, "shenhetime");
            return (Criteria) this;
        }

        public Criteria andShenhetimeBetween(Date value1, Date value2) {
            addCriterion("shenhetime between", value1, value2, "shenhetime");
            return (Criteria) this;
        }

        public Criteria andShenhetimeNotBetween(Date value1, Date value2) {
            addCriterion("shenhetime not between", value1, value2, "shenhetime");
            return (Criteria) this;
        }

        public Criteria andChargetimeIsNull() {
            addCriterion("chargetime is null");
            return (Criteria) this;
        }

        public Criteria andChargetimeIsNotNull() {
            addCriterion("chargetime is not null");
            return (Criteria) this;
        }

        public Criteria andChargetimeEqualTo(Date value) {
            addCriterion("chargetime =", value, "chargetime");
            return (Criteria) this;
        }

        public Criteria andChargetimeNotEqualTo(Date value) {
            addCriterion("chargetime <>", value, "chargetime");
            return (Criteria) this;
        }

        public Criteria andChargetimeGreaterThan(Date value) {
            addCriterion("chargetime >", value, "chargetime");
            return (Criteria) this;
        }

        public Criteria andChargetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("chargetime >=", value, "chargetime");
            return (Criteria) this;
        }

        public Criteria andChargetimeLessThan(Date value) {
            addCriterion("chargetime <", value, "chargetime");
            return (Criteria) this;
        }

        public Criteria andChargetimeLessThanOrEqualTo(Date value) {
            addCriterion("chargetime <=", value, "chargetime");
            return (Criteria) this;
        }

        public Criteria andChargetimeIn(List<Date> values) {
            addCriterion("chargetime in", values, "chargetime");
            return (Criteria) this;
        }

        public Criteria andChargetimeNotIn(List<Date> values) {
            addCriterion("chargetime not in", values, "chargetime");
            return (Criteria) this;
        }

        public Criteria andChargetimeBetween(Date value1, Date value2) {
            addCriterion("chargetime between", value1, value2, "chargetime");
            return (Criteria) this;
        }

        public Criteria andChargetimeNotBetween(Date value1, Date value2) {
            addCriterion("chargetime not between", value1, value2, "chargetime");
            return (Criteria) this;
        }

        public Criteria andPacttimeIsNull() {
            addCriterion("pacttime is null");
            return (Criteria) this;
        }

        public Criteria andPacttimeIsNotNull() {
            addCriterion("pacttime is not null");
            return (Criteria) this;
        }

        public Criteria andPacttimeEqualTo(Date value) {
            addCriterion("pacttime =", value, "pacttime");
            return (Criteria) this;
        }

        public Criteria andPacttimeNotEqualTo(Date value) {
            addCriterion("pacttime <>", value, "pacttime");
            return (Criteria) this;
        }

        public Criteria andPacttimeGreaterThan(Date value) {
            addCriterion("pacttime >", value, "pacttime");
            return (Criteria) this;
        }

        public Criteria andPacttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pacttime >=", value, "pacttime");
            return (Criteria) this;
        }

        public Criteria andPacttimeLessThan(Date value) {
            addCriterion("pacttime <", value, "pacttime");
            return (Criteria) this;
        }

        public Criteria andPacttimeLessThanOrEqualTo(Date value) {
            addCriterion("pacttime <=", value, "pacttime");
            return (Criteria) this;
        }

        public Criteria andPacttimeIn(List<Date> values) {
            addCriterion("pacttime in", values, "pacttime");
            return (Criteria) this;
        }

        public Criteria andPacttimeNotIn(List<Date> values) {
            addCriterion("pacttime not in", values, "pacttime");
            return (Criteria) this;
        }

        public Criteria andPacttimeBetween(Date value1, Date value2) {
            addCriterion("pacttime between", value1, value2, "pacttime");
            return (Criteria) this;
        }

        public Criteria andPacttimeNotBetween(Date value1, Date value2) {
            addCriterion("pacttime not between", value1, value2, "pacttime");
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

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }
}