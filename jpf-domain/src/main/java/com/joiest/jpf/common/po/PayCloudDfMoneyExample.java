package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayCloudDfMoneyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayCloudDfMoneyExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
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

        public Criteria andBusstaffidIsNull() {
            addCriterion("busstaffid is null");
            return (Criteria) this;
        }

        public Criteria andBusstaffidIsNotNull() {
            addCriterion("busstaffid is not null");
            return (Criteria) this;
        }

        public Criteria andBusstaffidEqualTo(Long value) {
            addCriterion("busstaffid =", value, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidNotEqualTo(Long value) {
            addCriterion("busstaffid <>", value, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidGreaterThan(Long value) {
            addCriterion("busstaffid >", value, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidGreaterThanOrEqualTo(Long value) {
            addCriterion("busstaffid >=", value, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidLessThan(Long value) {
            addCriterion("busstaffid <", value, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidLessThanOrEqualTo(Long value) {
            addCriterion("busstaffid <=", value, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidIn(List<Long> values) {
            addCriterion("busstaffid in", values, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidNotIn(List<Long> values) {
            addCriterion("busstaffid not in", values, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidBetween(Long value1, Long value2) {
            addCriterion("busstaffid between", value1, value2, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andBusstaffidNotBetween(Long value1, Long value2) {
            addCriterion("busstaffid not between", value1, value2, "busstaffid");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
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

        public Criteria andBanknoIsNull() {
            addCriterion("bankno is null");
            return (Criteria) this;
        }

        public Criteria andBanknoIsNotNull() {
            addCriterion("bankno is not null");
            return (Criteria) this;
        }

        public Criteria andBanknoEqualTo(String value) {
            addCriterion("bankno =", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoNotEqualTo(String value) {
            addCriterion("bankno <>", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoGreaterThan(String value) {
            addCriterion("bankno >", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoGreaterThanOrEqualTo(String value) {
            addCriterion("bankno >=", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoLessThan(String value) {
            addCriterion("bankno <", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoLessThanOrEqualTo(String value) {
            addCriterion("bankno <=", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoLike(String value) {
            addCriterion("bankno like", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoNotLike(String value) {
            addCriterion("bankno not like", value, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoIn(List<String> values) {
            addCriterion("bankno in", values, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoNotIn(List<String> values) {
            addCriterion("bankno not in", values, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoBetween(String value1, String value2) {
            addCriterion("bankno between", value1, value2, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknoNotBetween(String value1, String value2) {
            addCriterion("bankno not between", value1, value2, "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknicknameIsNull() {
            addCriterion("banknickname is null");
            return (Criteria) this;
        }

        public Criteria andBanknicknameIsNotNull() {
            addCriterion("banknickname is not null");
            return (Criteria) this;
        }

        public Criteria andBanknicknameEqualTo(String value) {
            addCriterion("banknickname =", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameNotEqualTo(String value) {
            addCriterion("banknickname <>", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameGreaterThan(String value) {
            addCriterion("banknickname >", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameGreaterThanOrEqualTo(String value) {
            addCriterion("banknickname >=", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameLessThan(String value) {
            addCriterion("banknickname <", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameLessThanOrEqualTo(String value) {
            addCriterion("banknickname <=", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameLike(String value) {
            addCriterion("banknickname like", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameNotLike(String value) {
            addCriterion("banknickname not like", value, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameIn(List<String> values) {
            addCriterion("banknickname in", values, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameNotIn(List<String> values) {
            addCriterion("banknickname not in", values, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameBetween(String value1, String value2) {
            addCriterion("banknickname between", value1, value2, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBanknicknameNotBetween(String value1, String value2) {
            addCriterion("banknickname not between", value1, value2, "banknickname");
            return (Criteria) this;
        }

        public Criteria andBankphoneIsNull() {
            addCriterion("bankphone is null");
            return (Criteria) this;
        }

        public Criteria andBankphoneIsNotNull() {
            addCriterion("bankphone is not null");
            return (Criteria) this;
        }

        public Criteria andBankphoneEqualTo(String value) {
            addCriterion("bankphone =", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneNotEqualTo(String value) {
            addCriterion("bankphone <>", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneGreaterThan(String value) {
            addCriterion("bankphone >", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneGreaterThanOrEqualTo(String value) {
            addCriterion("bankphone >=", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneLessThan(String value) {
            addCriterion("bankphone <", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneLessThanOrEqualTo(String value) {
            addCriterion("bankphone <=", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneLike(String value) {
            addCriterion("bankphone like", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneNotLike(String value) {
            addCriterion("bankphone not like", value, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneIn(List<String> values) {
            addCriterion("bankphone in", values, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneNotIn(List<String> values) {
            addCriterion("bankphone not in", values, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneBetween(String value1, String value2) {
            addCriterion("bankphone between", value1, value2, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBankphoneNotBetween(String value1, String value2) {
            addCriterion("bankphone not between", value1, value2, "bankphone");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNull() {
            addCriterion("bankname is null");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNotNull() {
            addCriterion("bankname is not null");
            return (Criteria) this;
        }

        public Criteria andBanknameEqualTo(String value) {
            addCriterion("bankname =", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotEqualTo(String value) {
            addCriterion("bankname <>", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThan(String value) {
            addCriterion("bankname >", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThanOrEqualTo(String value) {
            addCriterion("bankname >=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThan(String value) {
            addCriterion("bankname <", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThanOrEqualTo(String value) {
            addCriterion("bankname <=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLike(String value) {
            addCriterion("bankname like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotLike(String value) {
            addCriterion("bankname not like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameIn(List<String> values) {
            addCriterion("bankname in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotIn(List<String> values) {
            addCriterion("bankname not in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameBetween(String value1, String value2) {
            addCriterion("bankname between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotBetween(String value1, String value2) {
            addCriterion("bankname not between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBankprovinceIsNull() {
            addCriterion("bankprovince is null");
            return (Criteria) this;
        }

        public Criteria andBankprovinceIsNotNull() {
            addCriterion("bankprovince is not null");
            return (Criteria) this;
        }

        public Criteria andBankprovinceEqualTo(String value) {
            addCriterion("bankprovince =", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotEqualTo(String value) {
            addCriterion("bankprovince <>", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceGreaterThan(String value) {
            addCriterion("bankprovince >", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceGreaterThanOrEqualTo(String value) {
            addCriterion("bankprovince >=", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLessThan(String value) {
            addCriterion("bankprovince <", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLessThanOrEqualTo(String value) {
            addCriterion("bankprovince <=", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLike(String value) {
            addCriterion("bankprovince like", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotLike(String value) {
            addCriterion("bankprovince not like", value, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceIn(List<String> values) {
            addCriterion("bankprovince in", values, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotIn(List<String> values) {
            addCriterion("bankprovince not in", values, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceBetween(String value1, String value2) {
            addCriterion("bankprovince between", value1, value2, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankprovinceNotBetween(String value1, String value2) {
            addCriterion("bankprovince not between", value1, value2, "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankcityIsNull() {
            addCriterion("bankcity is null");
            return (Criteria) this;
        }

        public Criteria andBankcityIsNotNull() {
            addCriterion("bankcity is not null");
            return (Criteria) this;
        }

        public Criteria andBankcityEqualTo(String value) {
            addCriterion("bankcity =", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotEqualTo(String value) {
            addCriterion("bankcity <>", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityGreaterThan(String value) {
            addCriterion("bankcity >", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityGreaterThanOrEqualTo(String value) {
            addCriterion("bankcity >=", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityLessThan(String value) {
            addCriterion("bankcity <", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityLessThanOrEqualTo(String value) {
            addCriterion("bankcity <=", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityLike(String value) {
            addCriterion("bankcity like", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotLike(String value) {
            addCriterion("bankcity not like", value, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityIn(List<String> values) {
            addCriterion("bankcity in", values, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotIn(List<String> values) {
            addCriterion("bankcity not in", values, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityBetween(String value1, String value2) {
            addCriterion("bankcity between", value1, value2, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBankcityNotBetween(String value1, String value2) {
            addCriterion("bankcity not between", value1, value2, "bankcity");
            return (Criteria) this;
        }

        public Criteria andBanktypeIsNull() {
            addCriterion("banktype is null");
            return (Criteria) this;
        }

        public Criteria andBanktypeIsNotNull() {
            addCriterion("banktype is not null");
            return (Criteria) this;
        }

        public Criteria andBanktypeEqualTo(String value) {
            addCriterion("banktype =", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeNotEqualTo(String value) {
            addCriterion("banktype <>", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeGreaterThan(String value) {
            addCriterion("banktype >", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeGreaterThanOrEqualTo(String value) {
            addCriterion("banktype >=", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeLessThan(String value) {
            addCriterion("banktype <", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeLessThanOrEqualTo(String value) {
            addCriterion("banktype <=", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeLike(String value) {
            addCriterion("banktype like", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeNotLike(String value) {
            addCriterion("banktype not like", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeIn(List<String> values) {
            addCriterion("banktype in", values, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeNotIn(List<String> values) {
            addCriterion("banktype not in", values, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeBetween(String value1, String value2) {
            addCriterion("banktype between", value1, value2, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeNotBetween(String value1, String value2) {
            addCriterion("banktype not between", value1, value2, "banktype");
            return (Criteria) this;
        }

        public Criteria andBankacctattrIsNull() {
            addCriterion("bankAcctAttr is null");
            return (Criteria) this;
        }

        public Criteria andBankacctattrIsNotNull() {
            addCriterion("bankAcctAttr is not null");
            return (Criteria) this;
        }

        public Criteria andBankacctattrEqualTo(Integer value) {
            addCriterion("bankAcctAttr =", value, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrNotEqualTo(Integer value) {
            addCriterion("bankAcctAttr <>", value, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrGreaterThan(Integer value) {
            addCriterion("bankAcctAttr >", value, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrGreaterThanOrEqualTo(Integer value) {
            addCriterion("bankAcctAttr >=", value, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrLessThan(Integer value) {
            addCriterion("bankAcctAttr <", value, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrLessThanOrEqualTo(Integer value) {
            addCriterion("bankAcctAttr <=", value, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrIn(List<Integer> values) {
            addCriterion("bankAcctAttr in", values, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrNotIn(List<Integer> values) {
            addCriterion("bankAcctAttr not in", values, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrBetween(Integer value1, Integer value2) {
            addCriterion("bankAcctAttr between", value1, value2, "bankacctattr");
            return (Criteria) this;
        }

        public Criteria andBankacctattrNotBetween(Integer value1, Integer value2) {
            addCriterion("bankAcctAttr not between", value1, value2, "bankacctattr");
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

        public Criteria andRealnameIsNull() {
            addCriterion("realname is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("realname is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("realname =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("realname <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("realname >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("realname >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("realname <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("realname <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("realname like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("realname not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("realname in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("realname not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("realname between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("realname not between", value1, value2, "realname");
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

        public Criteria andMontypeEqualTo(Integer value) {
            addCriterion("montype =", value, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeNotEqualTo(Integer value) {
            addCriterion("montype <>", value, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeGreaterThan(Integer value) {
            addCriterion("montype >", value, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("montype >=", value, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeLessThan(Integer value) {
            addCriterion("montype <", value, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeLessThanOrEqualTo(Integer value) {
            addCriterion("montype <=", value, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeIn(List<Integer> values) {
            addCriterion("montype in", values, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeNotIn(List<Integer> values) {
            addCriterion("montype not in", values, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeBetween(Integer value1, Integer value2) {
            addCriterion("montype between", value1, value2, "montype");
            return (Criteria) this;
        }

        public Criteria andMontypeNotBetween(Integer value1, Integer value2) {
            addCriterion("montype not between", value1, value2, "montype");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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

        public Criteria andVidEqualTo(Integer value) {
            addCriterion("vid =", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotEqualTo(Integer value) {
            addCriterion("vid <>", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidGreaterThan(Integer value) {
            addCriterion("vid >", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidGreaterThanOrEqualTo(Integer value) {
            addCriterion("vid >=", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLessThan(Integer value) {
            addCriterion("vid <", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidLessThanOrEqualTo(Integer value) {
            addCriterion("vid <=", value, "vid");
            return (Criteria) this;
        }

        public Criteria andVidIn(List<Integer> values) {
            addCriterion("vid in", values, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotIn(List<Integer> values) {
            addCriterion("vid not in", values, "vid");
            return (Criteria) this;
        }

        public Criteria andVidBetween(Integer value1, Integer value2) {
            addCriterion("vid between", value1, value2, "vid");
            return (Criteria) this;
        }

        public Criteria andVidNotBetween(Integer value1, Integer value2) {
            addCriterion("vid not between", value1, value2, "vid");
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

        public Criteria andIsActiveEqualTo(Integer value) {
            addCriterion("is_active =", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotEqualTo(Integer value) {
            addCriterion("is_active <>", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThan(Integer value) {
            addCriterion("is_active >", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_active >=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThan(Integer value) {
            addCriterion("is_active <", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveLessThanOrEqualTo(Integer value) {
            addCriterion("is_active <=", value, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveIn(List<Integer> values) {
            addCriterion("is_active in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotIn(List<Integer> values) {
            addCriterion("is_active not in", values, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveBetween(Integer value1, Integer value2) {
            addCriterion("is_active between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andIsActiveNotBetween(Integer value1, Integer value2) {
            addCriterion("is_active not between", value1, value2, "isActive");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
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

        public Criteria andOperastateIsNull() {
            addCriterion("operastate is null");
            return (Criteria) this;
        }

        public Criteria andOperastateIsNotNull() {
            addCriterion("operastate is not null");
            return (Criteria) this;
        }

        public Criteria andOperastateEqualTo(Integer value) {
            addCriterion("operastate =", value, "operastate");
            return (Criteria) this;
        }

        public Criteria andOperastateNotEqualTo(Integer value) {
            addCriterion("operastate <>", value, "operastate");
            return (Criteria) this;
        }

        public Criteria andOperastateGreaterThan(Integer value) {
            addCriterion("operastate >", value, "operastate");
            return (Criteria) this;
        }

        public Criteria andOperastateGreaterThanOrEqualTo(Integer value) {
            addCriterion("operastate >=", value, "operastate");
            return (Criteria) this;
        }

        public Criteria andOperastateLessThan(Integer value) {
            addCriterion("operastate <", value, "operastate");
            return (Criteria) this;
        }

        public Criteria andOperastateLessThanOrEqualTo(Integer value) {
            addCriterion("operastate <=", value, "operastate");
            return (Criteria) this;
        }

        public Criteria andOperastateIn(List<Integer> values) {
            addCriterion("operastate in", values, "operastate");
            return (Criteria) this;
        }

        public Criteria andOperastateNotIn(List<Integer> values) {
            addCriterion("operastate not in", values, "operastate");
            return (Criteria) this;
        }

        public Criteria andOperastateBetween(Integer value1, Integer value2) {
            addCriterion("operastate between", value1, value2, "operastate");
            return (Criteria) this;
        }

        public Criteria andOperastateNotBetween(Integer value1, Integer value2) {
            addCriterion("operastate not between", value1, value2, "operastate");
            return (Criteria) this;
        }

        public Criteria andTrannoIsNull() {
            addCriterion("tranNo is null");
            return (Criteria) this;
        }

        public Criteria andTrannoIsNotNull() {
            addCriterion("tranNo is not null");
            return (Criteria) this;
        }

        public Criteria andTrannoEqualTo(String value) {
            addCriterion("tranNo =", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotEqualTo(String value) {
            addCriterion("tranNo <>", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoGreaterThan(String value) {
            addCriterion("tranNo >", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoGreaterThanOrEqualTo(String value) {
            addCriterion("tranNo >=", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLessThan(String value) {
            addCriterion("tranNo <", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLessThanOrEqualTo(String value) {
            addCriterion("tranNo <=", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLike(String value) {
            addCriterion("tranNo like", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotLike(String value) {
            addCriterion("tranNo not like", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoIn(List<String> values) {
            addCriterion("tranNo in", values, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotIn(List<String> values) {
            addCriterion("tranNo not in", values, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoBetween(String value1, String value2) {
            addCriterion("tranNo between", value1, value2, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotBetween(String value1, String value2) {
            addCriterion("tranNo not between", value1, value2, "tranno");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(String value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(String value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(String value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(String value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(String value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLike(String value) {
            addCriterion("orderid like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotLike(String value) {
            addCriterion("orderid not like", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<String> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<String> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(String value1, String value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(String value1, String value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidIsNull() {
            addCriterion("ordernewid is null");
            return (Criteria) this;
        }

        public Criteria andOrdernewidIsNotNull() {
            addCriterion("ordernewid is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernewidEqualTo(String value) {
            addCriterion("ordernewid =", value, "ordernewid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidNotEqualTo(String value) {
            addCriterion("ordernewid <>", value, "ordernewid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidGreaterThan(String value) {
            addCriterion("ordernewid >", value, "ordernewid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidGreaterThanOrEqualTo(String value) {
            addCriterion("ordernewid >=", value, "ordernewid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidLessThan(String value) {
            addCriterion("ordernewid <", value, "ordernewid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidLessThanOrEqualTo(String value) {
            addCriterion("ordernewid <=", value, "ordernewid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidLike(String value) {
            addCriterion("ordernewid like", value, "ordernewid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidNotLike(String value) {
            addCriterion("ordernewid not like", value, "ordernewid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidIn(List<String> values) {
            addCriterion("ordernewid in", values, "ordernewid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidNotIn(List<String> values) {
            addCriterion("ordernewid not in", values, "ordernewid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidBetween(String value1, String value2) {
            addCriterion("ordernewid between", value1, value2, "ordernewid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidNotBetween(String value1, String value2) {
            addCriterion("ordernewid not between", value1, value2, "ordernewid");
            return (Criteria) this;
        }

        public Criteria andPayablemoneyIsNull() {
            addCriterion("payablemoney is null");
            return (Criteria) this;
        }

        public Criteria andPayablemoneyIsNotNull() {
            addCriterion("payablemoney is not null");
            return (Criteria) this;
        }

        public Criteria andPayablemoneyEqualTo(BigDecimal value) {
            addCriterion("payablemoney =", value, "payablemoney");
            return (Criteria) this;
        }

        public Criteria andPayablemoneyNotEqualTo(BigDecimal value) {
            addCriterion("payablemoney <>", value, "payablemoney");
            return (Criteria) this;
        }

        public Criteria andPayablemoneyGreaterThan(BigDecimal value) {
            addCriterion("payablemoney >", value, "payablemoney");
            return (Criteria) this;
        }

        public Criteria andPayablemoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("payablemoney >=", value, "payablemoney");
            return (Criteria) this;
        }

        public Criteria andPayablemoneyLessThan(BigDecimal value) {
            addCriterion("payablemoney <", value, "payablemoney");
            return (Criteria) this;
        }

        public Criteria andPayablemoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("payablemoney <=", value, "payablemoney");
            return (Criteria) this;
        }

        public Criteria andPayablemoneyIn(List<BigDecimal> values) {
            addCriterion("payablemoney in", values, "payablemoney");
            return (Criteria) this;
        }

        public Criteria andPayablemoneyNotIn(List<BigDecimal> values) {
            addCriterion("payablemoney not in", values, "payablemoney");
            return (Criteria) this;
        }

        public Criteria andPayablemoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payablemoney between", value1, value2, "payablemoney");
            return (Criteria) this;
        }

        public Criteria andPayablemoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("payablemoney not between", value1, value2, "payablemoney");
            return (Criteria) this;
        }

        public Criteria andWithholdmoneyIsNull() {
            addCriterion("withholdmoney is null");
            return (Criteria) this;
        }

        public Criteria andWithholdmoneyIsNotNull() {
            addCriterion("withholdmoney is not null");
            return (Criteria) this;
        }

        public Criteria andWithholdmoneyEqualTo(BigDecimal value) {
            addCriterion("withholdmoney =", value, "withholdmoney");
            return (Criteria) this;
        }

        public Criteria andWithholdmoneyNotEqualTo(BigDecimal value) {
            addCriterion("withholdmoney <>", value, "withholdmoney");
            return (Criteria) this;
        }

        public Criteria andWithholdmoneyGreaterThan(BigDecimal value) {
            addCriterion("withholdmoney >", value, "withholdmoney");
            return (Criteria) this;
        }

        public Criteria andWithholdmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("withholdmoney >=", value, "withholdmoney");
            return (Criteria) this;
        }

        public Criteria andWithholdmoneyLessThan(BigDecimal value) {
            addCriterion("withholdmoney <", value, "withholdmoney");
            return (Criteria) this;
        }

        public Criteria andWithholdmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("withholdmoney <=", value, "withholdmoney");
            return (Criteria) this;
        }

        public Criteria andWithholdmoneyIn(List<BigDecimal> values) {
            addCriterion("withholdmoney in", values, "withholdmoney");
            return (Criteria) this;
        }

        public Criteria andWithholdmoneyNotIn(List<BigDecimal> values) {
            addCriterion("withholdmoney not in", values, "withholdmoney");
            return (Criteria) this;
        }

        public Criteria andWithholdmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("withholdmoney between", value1, value2, "withholdmoney");
            return (Criteria) this;
        }

        public Criteria andWithholdmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("withholdmoney not between", value1, value2, "withholdmoney");
            return (Criteria) this;
        }

        public Criteria andInvostatusIsNull() {
            addCriterion("invostatus is null");
            return (Criteria) this;
        }

        public Criteria andInvostatusIsNotNull() {
            addCriterion("invostatus is not null");
            return (Criteria) this;
        }

        public Criteria andInvostatusEqualTo(Integer value) {
            addCriterion("invostatus =", value, "invostatus");
            return (Criteria) this;
        }

        public Criteria andInvostatusNotEqualTo(Integer value) {
            addCriterion("invostatus <>", value, "invostatus");
            return (Criteria) this;
        }

        public Criteria andInvostatusGreaterThan(Integer value) {
            addCriterion("invostatus >", value, "invostatus");
            return (Criteria) this;
        }

        public Criteria andInvostatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("invostatus >=", value, "invostatus");
            return (Criteria) this;
        }

        public Criteria andInvostatusLessThan(Integer value) {
            addCriterion("invostatus <", value, "invostatus");
            return (Criteria) this;
        }

        public Criteria andInvostatusLessThanOrEqualTo(Integer value) {
            addCriterion("invostatus <=", value, "invostatus");
            return (Criteria) this;
        }

        public Criteria andInvostatusIn(List<Integer> values) {
            addCriterion("invostatus in", values, "invostatus");
            return (Criteria) this;
        }

        public Criteria andInvostatusNotIn(List<Integer> values) {
            addCriterion("invostatus not in", values, "invostatus");
            return (Criteria) this;
        }

        public Criteria andInvostatusBetween(Integer value1, Integer value2) {
            addCriterion("invostatus between", value1, value2, "invostatus");
            return (Criteria) this;
        }

        public Criteria andInvostatusNotBetween(Integer value1, Integer value2) {
            addCriterion("invostatus not between", value1, value2, "invostatus");
            return (Criteria) this;
        }

        public Criteria andAgentNoLikeInsensitive(String value) {
            addCriterion("upper(agent_no) like", value.toUpperCase(), "agentNo");
            return (Criteria) this;
        }

        public Criteria andMerchNoLikeInsensitive(String value) {
            addCriterion("upper(merch_no) like", value.toUpperCase(), "merchNo");
            return (Criteria) this;
        }

        public Criteria andFidLikeInsensitive(String value) {
            addCriterion("upper(fid) like", value.toUpperCase(), "fid");
            return (Criteria) this;
        }

        public Criteria andUsernameLikeInsensitive(String value) {
            addCriterion("upper(username) like", value.toUpperCase(), "username");
            return (Criteria) this;
        }

        public Criteria andBanknoLikeInsensitive(String value) {
            addCriterion("upper(bankno) like", value.toUpperCase(), "bankno");
            return (Criteria) this;
        }

        public Criteria andBanknicknameLikeInsensitive(String value) {
            addCriterion("upper(banknickname) like", value.toUpperCase(), "banknickname");
            return (Criteria) this;
        }

        public Criteria andBankphoneLikeInsensitive(String value) {
            addCriterion("upper(bankphone) like", value.toUpperCase(), "bankphone");
            return (Criteria) this;
        }

        public Criteria andBanknameLikeInsensitive(String value) {
            addCriterion("upper(bankname) like", value.toUpperCase(), "bankname");
            return (Criteria) this;
        }

        public Criteria andBankprovinceLikeInsensitive(String value) {
            addCriterion("upper(bankprovince) like", value.toUpperCase(), "bankprovince");
            return (Criteria) this;
        }

        public Criteria andBankcityLikeInsensitive(String value) {
            addCriterion("upper(bankcity) like", value.toUpperCase(), "bankcity");
            return (Criteria) this;
        }

        public Criteria andBanktypeLikeInsensitive(String value) {
            addCriterion("upper(banktype) like", value.toUpperCase(), "banktype");
            return (Criteria) this;
        }

        public Criteria andRealnameLikeInsensitive(String value) {
            addCriterion("upper(realname) like", value.toUpperCase(), "realname");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }

        public Criteria andContentLikeInsensitive(String value) {
            addCriterion("upper(content) like", value.toUpperCase(), "content");
            return (Criteria) this;
        }

        public Criteria andTrannoLikeInsensitive(String value) {
            addCriterion("upper(tranNo) like", value.toUpperCase(), "tranno");
            return (Criteria) this;
        }

        public Criteria andOrderidLikeInsensitive(String value) {
            addCriterion("upper(orderid) like", value.toUpperCase(), "orderid");
            return (Criteria) this;
        }

        public Criteria andOrdernewidLikeInsensitive(String value) {
            addCriterion("upper(ordernewid) like", value.toUpperCase(), "ordernewid");
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