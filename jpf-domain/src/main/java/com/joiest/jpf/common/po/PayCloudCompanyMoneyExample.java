package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayCloudCompanyMoneyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayCloudCompanyMoneyExample() {
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

        public Criteria andCommoneyIsNull() {
            addCriterion("commoney is null");
            return (Criteria) this;
        }

        public Criteria andCommoneyIsNotNull() {
            addCriterion("commoney is not null");
            return (Criteria) this;
        }

        public Criteria andCommoneyEqualTo(BigDecimal value) {
            addCriterion("commoney =", value, "commoney");
            return (Criteria) this;
        }

        public Criteria andCommoneyNotEqualTo(BigDecimal value) {
            addCriterion("commoney <>", value, "commoney");
            return (Criteria) this;
        }

        public Criteria andCommoneyGreaterThan(BigDecimal value) {
            addCriterion("commoney >", value, "commoney");
            return (Criteria) this;
        }

        public Criteria andCommoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("commoney >=", value, "commoney");
            return (Criteria) this;
        }

        public Criteria andCommoneyLessThan(BigDecimal value) {
            addCriterion("commoney <", value, "commoney");
            return (Criteria) this;
        }

        public Criteria andCommoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("commoney <=", value, "commoney");
            return (Criteria) this;
        }

        public Criteria andCommoneyIn(List<BigDecimal> values) {
            addCriterion("commoney in", values, "commoney");
            return (Criteria) this;
        }

        public Criteria andCommoneyNotIn(List<BigDecimal> values) {
            addCriterion("commoney not in", values, "commoney");
            return (Criteria) this;
        }

        public Criteria andCommoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commoney between", value1, value2, "commoney");
            return (Criteria) this;
        }

        public Criteria andCommoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("commoney not between", value1, value2, "commoney");
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(String value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(String value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(String value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(String value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(String value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(String value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<String> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<String> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(String value1, String value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(String value1, String value2) {
            addCriterion("uid not between", value1, value2, "uid");
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

        public Criteria andVidIsNull() {
            addCriterion("vid is null");
            return (Criteria) this;
        }

        public Criteria andVidIsNotNull() {
            addCriterion("vid is not null");
            return (Criteria) this;
        }

        public Criteria andVidEqualTo(Byte value) {
            addCriterion("vid =", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotEqualTo(Byte value) {
            addCriterion("vid <>", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidGreaterThan(Byte value) {
            addCriterion("vid >", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidGreaterThanOrEqualTo(Byte value) {
            addCriterion("vid >=", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLessThan(Byte value) {
            addCriterion("vid <", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLessThanOrEqualTo(Byte value) {
            addCriterion("vid <=", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidIn(List<Byte> values) {
            addCriterion("vid in", values, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotIn(List<Byte> values) {
            addCriterion("vid not in", values, "vid");
            return (Criteria) this;
        }

        public Criteria andVidBetween(Byte value1, Byte value2) {
            addCriterion("vid between", value1, value2, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotBetween(Byte value1, Byte value2) {
            addCriterion("vid not between", value1, value2, "vid");
            return (Criteria) this;
        }

        public Criteria andIntroIsNull() {
            addCriterion("intro is null");
            return (Criteria) this;
        }

        public Criteria andIntroIsNotNull() {
            addCriterion("intro is not null");
            return (Criteria) this;
        }

        public Criteria andIntroEqualTo(String value) {
            addCriterion("intro =", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotEqualTo(String value) {
            addCriterion("intro <>", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThan(String value) {
            addCriterion("intro >", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThanOrEqualTo(String value) {
            addCriterion("intro >=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThan(String value) {
            addCriterion("intro <", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThanOrEqualTo(String value) {
            addCriterion("intro <=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLike(String value) {
            addCriterion("intro like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotLike(String value) {
            addCriterion("intro not like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroIn(List<String> values) {
            addCriterion("intro in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotIn(List<String> values) {
            addCriterion("intro not in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroBetween(String value1, String value2) {
            addCriterion("intro between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotBetween(String value1, String value2) {
            addCriterion("intro not between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andMontypeIsNull() {
            addCriterion("montype is null");
            return (Criteria) this;
        }

        public Criteria andMontypeIsNotNull() {
            addCriterion("montype is not null");
            return (Criteria) this;
        }

        public Criteria andMontypeEqualTo(Byte value) {
            addCriterion("montype =", value, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeNotEqualTo(Byte value) {
            addCriterion("montype <>", value, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeGreaterThan(Byte value) {
            addCriterion("montype >", value, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("montype >=", value, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeLessThan(Byte value) {
            addCriterion("montype <", value, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeLessThanOrEqualTo(Byte value) {
            addCriterion("montype <=", value, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeIn(List<Byte> values) {
            addCriterion("montype in", values, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeNotIn(List<Byte> values) {
            addCriterion("montype not in", values, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeBetween(Byte value1, Byte value2) {
            addCriterion("montype between", value1, value2, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeNotBetween(Byte value1, Byte value2) {
            addCriterion("montype not between", value1, value2, "montype");
            return (Criteria) this;
        }

        public Criteria andBatchstatusIsNull() {
            addCriterion("batchstatus is null");
            return (Criteria) this;
        }

        public Criteria andBatchstatusIsNotNull() {
            addCriterion("batchstatus is not null");
            return (Criteria) this;
        }

        public Criteria andBatchstatusEqualTo(Byte value) {
            addCriterion("batchstatus =", value, "batchstatus");
            return (Criteria) this;
        }

        public Criteria andBatchstatusNotEqualTo(Byte value) {
            addCriterion("batchstatus <>", value, "batchstatus");
            return (Criteria) this;
        }

        public Criteria andBatchstatusGreaterThan(Byte value) {
            addCriterion("batchstatus >", value, "batchstatus");
            return (Criteria) this;
        }

        public Criteria andBatchstatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("batchstatus >=", value, "batchstatus");
            return (Criteria) this;
        }

        public Criteria andBatchstatusLessThan(Byte value) {
            addCriterion("batchstatus <", value, "batchstatus");
            return (Criteria) this;
        }

        public Criteria andBatchstatusLessThanOrEqualTo(Byte value) {
            addCriterion("batchstatus <=", value, "batchstatus");
            return (Criteria) this;
        }

        public Criteria andBatchstatusIn(List<Byte> values) {
            addCriterion("batchstatus in", values, "batchstatus");
            return (Criteria) this;
        }

        public Criteria andBatchstatusNotIn(List<Byte> values) {
            addCriterion("batchstatus not in", values, "batchstatus");
            return (Criteria) this;
        }

        public Criteria andBatchstatusBetween(Byte value1, Byte value2) {
            addCriterion("batchstatus between", value1, value2, "batchstatus");
            return (Criteria) this;
        }

        public Criteria andBatchstatusNotBetween(Byte value1, Byte value2) {
            addCriterion("batchstatus not between", value1, value2, "batchstatus");
            return (Criteria) this;
        }

        public Criteria andBatchnoIsNull() {
            addCriterion("batchno is null");
            return (Criteria) this;
        }

        public Criteria andBatchnoIsNotNull() {
            addCriterion("batchno is not null");
            return (Criteria) this;
        }

        public Criteria andBatchnoEqualTo(String value) {
            addCriterion("batchno =", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoNotEqualTo(String value) {
            addCriterion("batchno <>", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoGreaterThan(String value) {
            addCriterion("batchno >", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoGreaterThanOrEqualTo(String value) {
            addCriterion("batchno >=", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoLessThan(String value) {
            addCriterion("batchno <", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoLessThanOrEqualTo(String value) {
            addCriterion("batchno <=", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoLike(String value) {
            addCriterion("batchno like", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoNotLike(String value) {
            addCriterion("batchno not like", value, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoIn(List<String> values) {
            addCriterion("batchno in", values, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoNotIn(List<String> values) {
            addCriterion("batchno not in", values, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoBetween(String value1, String value2) {
            addCriterion("batchno between", value1, value2, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchnoNotBetween(String value1, String value2) {
            addCriterion("batchno not between", value1, value2, "batchno");
            return (Criteria) this;
        }

        public Criteria andBatchitemsIsNull() {
            addCriterion("batchitems is null");
            return (Criteria) this;
        }

        public Criteria andBatchitemsIsNotNull() {
            addCriterion("batchitems is not null");
            return (Criteria) this;
        }

        public Criteria andBatchitemsEqualTo(String value) {
            addCriterion("batchitems =", value, "batchitems");
            return (Criteria) this;
        }

        public Criteria andBatchitemsNotEqualTo(String value) {
            addCriterion("batchitems <>", value, "batchitems");
            return (Criteria) this;
        }

        public Criteria andBatchitemsGreaterThan(String value) {
            addCriterion("batchitems >", value, "batchitems");
            return (Criteria) this;
        }

        public Criteria andBatchitemsGreaterThanOrEqualTo(String value) {
            addCriterion("batchitems >=", value, "batchitems");
            return (Criteria) this;
        }

        public Criteria andBatchitemsLessThan(String value) {
            addCriterion("batchitems <", value, "batchitems");
            return (Criteria) this;
        }

        public Criteria andBatchitemsLessThanOrEqualTo(String value) {
            addCriterion("batchitems <=", value, "batchitems");
            return (Criteria) this;
        }

        public Criteria andBatchitemsIn(List<String> values) {
            addCriterion("batchitems in", values, "batchitems");
            return (Criteria) this;
        }

        public Criteria andBatchitemsNotIn(List<String> values) {
            addCriterion("batchitems not in", values, "batchitems");
            return (Criteria) this;
        }

        public Criteria andBatchitemsBetween(String value1, String value2) {
            addCriterion("batchitems between", value1, value2, "batchitems");
            return (Criteria) this;
        }

        public Criteria andBatchitemsNotBetween(String value1, String value2) {
            addCriterion("batchitems not between", value1, value2, "batchitems");
            return (Criteria) this;
        }

        public Criteria andBatchallmoneyIsNull() {
            addCriterion("batchallmoney is null");
            return (Criteria) this;
        }

        public Criteria andBatchallmoneyIsNotNull() {
            addCriterion("batchallmoney is not null");
            return (Criteria) this;
        }

        public Criteria andBatchallmoneyEqualTo(BigDecimal value) {
            addCriterion("batchallmoney =", value, "batchallmoney");
            return (Criteria) this;
        }

        public Criteria andBatchallmoneyNotEqualTo(BigDecimal value) {
            addCriterion("batchallmoney <>", value, "batchallmoney");
            return (Criteria) this;
        }

        public Criteria andBatchallmoneyGreaterThan(BigDecimal value) {
            addCriterion("batchallmoney >", value, "batchallmoney");
            return (Criteria) this;
        }

        public Criteria andBatchallmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("batchallmoney >=", value, "batchallmoney");
            return (Criteria) this;
        }

        public Criteria andBatchallmoneyLessThan(BigDecimal value) {
            addCriterion("batchallmoney <", value, "batchallmoney");
            return (Criteria) this;
        }

        public Criteria andBatchallmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("batchallmoney <=", value, "batchallmoney");
            return (Criteria) this;
        }

        public Criteria andBatchallmoneyIn(List<BigDecimal> values) {
            addCriterion("batchallmoney in", values, "batchallmoney");
            return (Criteria) this;
        }

        public Criteria andBatchallmoneyNotIn(List<BigDecimal> values) {
            addCriterion("batchallmoney not in", values, "batchallmoney");
            return (Criteria) this;
        }

        public Criteria andBatchallmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("batchallmoney between", value1, value2, "batchallmoney");
            return (Criteria) this;
        }

        public Criteria andBatchallmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("batchallmoney not between", value1, value2, "batchallmoney");
            return (Criteria) this;
        }

        public Criteria andBatchdealitemsIsNull() {
            addCriterion("batchdealitems is null");
            return (Criteria) this;
        }

        public Criteria andBatchdealitemsIsNotNull() {
            addCriterion("batchdealitems is not null");
            return (Criteria) this;
        }

        public Criteria andBatchdealitemsEqualTo(String value) {
            addCriterion("batchdealitems =", value, "batchdealitems");
            return (Criteria) this;
        }

        public Criteria andBatchdealitemsNotEqualTo(String value) {
            addCriterion("batchdealitems <>", value, "batchdealitems");
            return (Criteria) this;
        }

        public Criteria andBatchdealitemsGreaterThan(String value) {
            addCriterion("batchdealitems >", value, "batchdealitems");
            return (Criteria) this;
        }

        public Criteria andBatchdealitemsGreaterThanOrEqualTo(String value) {
            addCriterion("batchdealitems >=", value, "batchdealitems");
            return (Criteria) this;
        }

        public Criteria andBatchdealitemsLessThan(String value) {
            addCriterion("batchdealitems <", value, "batchdealitems");
            return (Criteria) this;
        }

        public Criteria andBatchdealitemsLessThanOrEqualTo(String value) {
            addCriterion("batchdealitems <=", value, "batchdealitems");
            return (Criteria) this;
        }

        public Criteria andBatchdealitemsIn(List<String> values) {
            addCriterion("batchdealitems in", values, "batchdealitems");
            return (Criteria) this;
        }

        public Criteria andBatchdealitemsNotIn(List<String> values) {
            addCriterion("batchdealitems not in", values, "batchdealitems");
            return (Criteria) this;
        }

        public Criteria andBatchdealitemsBetween(String value1, String value2) {
            addCriterion("batchdealitems between", value1, value2, "batchdealitems");
            return (Criteria) this;
        }

        public Criteria andBatchdealitemsNotBetween(String value1, String value2) {
            addCriterion("batchdealitems not between", value1, value2, "batchdealitems");
            return (Criteria) this;
        }

        public Criteria andBatchdealmoneyIsNull() {
            addCriterion("batchdealmoney is null");
            return (Criteria) this;
        }

        public Criteria andBatchdealmoneyIsNotNull() {
            addCriterion("batchdealmoney is not null");
            return (Criteria) this;
        }

        public Criteria andBatchdealmoneyEqualTo(BigDecimal value) {
            addCriterion("batchdealmoney =", value, "batchdealmoney");
            return (Criteria) this;
        }

        public Criteria andBatchdealmoneyNotEqualTo(BigDecimal value) {
            addCriterion("batchdealmoney <>", value, "batchdealmoney");
            return (Criteria) this;
        }

        public Criteria andBatchdealmoneyGreaterThan(BigDecimal value) {
            addCriterion("batchdealmoney >", value, "batchdealmoney");
            return (Criteria) this;
        }

        public Criteria andBatchdealmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("batchdealmoney >=", value, "batchdealmoney");
            return (Criteria) this;
        }

        public Criteria andBatchdealmoneyLessThan(BigDecimal value) {
            addCriterion("batchdealmoney <", value, "batchdealmoney");
            return (Criteria) this;
        }

        public Criteria andBatchdealmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("batchdealmoney <=", value, "batchdealmoney");
            return (Criteria) this;
        }

        public Criteria andBatchdealmoneyIn(List<BigDecimal> values) {
            addCriterion("batchdealmoney in", values, "batchdealmoney");
            return (Criteria) this;
        }

        public Criteria andBatchdealmoneyNotIn(List<BigDecimal> values) {
            addCriterion("batchdealmoney not in", values, "batchdealmoney");
            return (Criteria) this;
        }

        public Criteria andBatchdealmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("batchdealmoney between", value1, value2, "batchdealmoney");
            return (Criteria) this;
        }

        public Criteria andBatchdealmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("batchdealmoney not between", value1, value2, "batchdealmoney");
            return (Criteria) this;
        }

        public Criteria andBatchfailitemsIsNull() {
            addCriterion("batchfailitems is null");
            return (Criteria) this;
        }

        public Criteria andBatchfailitemsIsNotNull() {
            addCriterion("batchfailitems is not null");
            return (Criteria) this;
        }

        public Criteria andBatchfailitemsEqualTo(String value) {
            addCriterion("batchfailitems =", value, "batchfailitems");
            return (Criteria) this;
        }

        public Criteria andBatchfailitemsNotEqualTo(String value) {
            addCriterion("batchfailitems <>", value, "batchfailitems");
            return (Criteria) this;
        }

        public Criteria andBatchfailitemsGreaterThan(String value) {
            addCriterion("batchfailitems >", value, "batchfailitems");
            return (Criteria) this;
        }

        public Criteria andBatchfailitemsGreaterThanOrEqualTo(String value) {
            addCriterion("batchfailitems >=", value, "batchfailitems");
            return (Criteria) this;
        }

        public Criteria andBatchfailitemsLessThan(String value) {
            addCriterion("batchfailitems <", value, "batchfailitems");
            return (Criteria) this;
        }

        public Criteria andBatchfailitemsLessThanOrEqualTo(String value) {
            addCriterion("batchfailitems <=", value, "batchfailitems");
            return (Criteria) this;
        }

        public Criteria andBatchfailitemsIn(List<String> values) {
            addCriterion("batchfailitems in", values, "batchfailitems");
            return (Criteria) this;
        }

        public Criteria andBatchfailitemsNotIn(List<String> values) {
            addCriterion("batchfailitems not in", values, "batchfailitems");
            return (Criteria) this;
        }

        public Criteria andBatchfailitemsBetween(String value1, String value2) {
            addCriterion("batchfailitems between", value1, value2, "batchfailitems");
            return (Criteria) this;
        }

        public Criteria andBatchfailitemsNotBetween(String value1, String value2) {
            addCriterion("batchfailitems not between", value1, value2, "batchfailitems");
            return (Criteria) this;
        }

        public Criteria andBatchfailmoneyIsNull() {
            addCriterion("batchfailmoney is null");
            return (Criteria) this;
        }

        public Criteria andBatchfailmoneyIsNotNull() {
            addCriterion("batchfailmoney is not null");
            return (Criteria) this;
        }

        public Criteria andBatchfailmoneyEqualTo(BigDecimal value) {
            addCriterion("batchfailmoney =", value, "batchfailmoney");
            return (Criteria) this;
        }

        public Criteria andBatchfailmoneyNotEqualTo(BigDecimal value) {
            addCriterion("batchfailmoney <>", value, "batchfailmoney");
            return (Criteria) this;
        }

        public Criteria andBatchfailmoneyGreaterThan(BigDecimal value) {
            addCriterion("batchfailmoney >", value, "batchfailmoney");
            return (Criteria) this;
        }

        public Criteria andBatchfailmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("batchfailmoney >=", value, "batchfailmoney");
            return (Criteria) this;
        }

        public Criteria andBatchfailmoneyLessThan(BigDecimal value) {
            addCriterion("batchfailmoney <", value, "batchfailmoney");
            return (Criteria) this;
        }

        public Criteria andBatchfailmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("batchfailmoney <=", value, "batchfailmoney");
            return (Criteria) this;
        }

        public Criteria andBatchfailmoneyIn(List<BigDecimal> values) {
            addCriterion("batchfailmoney in", values, "batchfailmoney");
            return (Criteria) this;
        }

        public Criteria andBatchfailmoneyNotIn(List<BigDecimal> values) {
            addCriterion("batchfailmoney not in", values, "batchfailmoney");
            return (Criteria) this;
        }

        public Criteria andBatchfailmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("batchfailmoney between", value1, value2, "batchfailmoney");
            return (Criteria) this;
        }

        public Criteria andBatchfailmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("batchfailmoney not between", value1, value2, "batchfailmoney");
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

        public Criteria andTaxmoneyIsNull() {
            addCriterion("taxmoney is null");
            return (Criteria) this;
        }

        public Criteria andTaxmoneyIsNotNull() {
            addCriterion("taxmoney is not null");
            return (Criteria) this;
        }

        public Criteria andTaxmoneyEqualTo(BigDecimal value) {
            addCriterion("taxmoney =", value, "taxmoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoneyNotEqualTo(BigDecimal value) {
            addCriterion("taxmoney <>", value, "taxmoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoneyGreaterThan(BigDecimal value) {
            addCriterion("taxmoney >", value, "taxmoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("taxmoney >=", value, "taxmoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoneyLessThan(BigDecimal value) {
            addCriterion("taxmoney <", value, "taxmoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("taxmoney <=", value, "taxmoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoneyIn(List<BigDecimal> values) {
            addCriterion("taxmoney in", values, "taxmoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoneyNotIn(List<BigDecimal> values) {
            addCriterion("taxmoney not in", values, "taxmoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("taxmoney between", value1, value2, "taxmoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("taxmoney not between", value1, value2, "taxmoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoremoneyIsNull() {
            addCriterion("taxmoremoney is null");
            return (Criteria) this;
        }

        public Criteria andTaxmoremoneyIsNotNull() {
            addCriterion("taxmoremoney is not null");
            return (Criteria) this;
        }

        public Criteria andTaxmoremoneyEqualTo(BigDecimal value) {
            addCriterion("taxmoremoney =", value, "taxmoremoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoremoneyNotEqualTo(BigDecimal value) {
            addCriterion("taxmoremoney <>", value, "taxmoremoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoremoneyGreaterThan(BigDecimal value) {
            addCriterion("taxmoremoney >", value, "taxmoremoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoremoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("taxmoremoney >=", value, "taxmoremoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoremoneyLessThan(BigDecimal value) {
            addCriterion("taxmoremoney <", value, "taxmoremoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoremoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("taxmoremoney <=", value, "taxmoremoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoremoneyIn(List<BigDecimal> values) {
            addCriterion("taxmoremoney in", values, "taxmoremoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoremoneyNotIn(List<BigDecimal> values) {
            addCriterion("taxmoremoney not in", values, "taxmoremoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoremoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("taxmoremoney between", value1, value2, "taxmoremoney");
            return (Criteria) this;
        }

        public Criteria andTaxmoremoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("taxmoremoney not between", value1, value2, "taxmoremoney");
            return (Criteria) this;
        }

        public Criteria andProfitmoneyIsNull() {
            addCriterion("profitmoney is null");
            return (Criteria) this;
        }

        public Criteria andProfitmoneyIsNotNull() {
            addCriterion("profitmoney is not null");
            return (Criteria) this;
        }

        public Criteria andProfitmoneyEqualTo(BigDecimal value) {
            addCriterion("profitmoney =", value, "profitmoney");
            return (Criteria) this;
        }

        public Criteria andProfitmoneyNotEqualTo(BigDecimal value) {
            addCriterion("profitmoney <>", value, "profitmoney");
            return (Criteria) this;
        }

        public Criteria andProfitmoneyGreaterThan(BigDecimal value) {
            addCriterion("profitmoney >", value, "profitmoney");
            return (Criteria) this;
        }

        public Criteria andProfitmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("profitmoney >=", value, "profitmoney");
            return (Criteria) this;
        }

        public Criteria andProfitmoneyLessThan(BigDecimal value) {
            addCriterion("profitmoney <", value, "profitmoney");
            return (Criteria) this;
        }

        public Criteria andProfitmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("profitmoney <=", value, "profitmoney");
            return (Criteria) this;
        }

        public Criteria andProfitmoneyIn(List<BigDecimal> values) {
            addCriterion("profitmoney in", values, "profitmoney");
            return (Criteria) this;
        }

        public Criteria andProfitmoneyNotIn(List<BigDecimal> values) {
            addCriterion("profitmoney not in", values, "profitmoney");
            return (Criteria) this;
        }

        public Criteria andProfitmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profitmoney between", value1, value2, "profitmoney");
            return (Criteria) this;
        }

        public Criteria andProfitmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profitmoney not between", value1, value2, "profitmoney");
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