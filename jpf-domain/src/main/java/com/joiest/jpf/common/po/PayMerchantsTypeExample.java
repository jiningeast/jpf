package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.List;

public class PayMerchantsTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayMerchantsTypeExample() {
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

        public Criteria andCatidIsNull() {
            addCriterion("catid is null");
            return (Criteria) this;
        }

        public Criteria andCatidIsNotNull() {
            addCriterion("catid is not null");
            return (Criteria) this;
        }

        public Criteria andCatidEqualTo(Integer value) {
            addCriterion("catid =", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidNotEqualTo(Integer value) {
            addCriterion("catid <>", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidGreaterThan(Integer value) {
            addCriterion("catid >", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidGreaterThanOrEqualTo(Integer value) {
            addCriterion("catid >=", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidLessThan(Integer value) {
            addCriterion("catid <", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidLessThanOrEqualTo(Integer value) {
            addCriterion("catid <=", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidIn(List<Integer> values) {
            addCriterion("catid in", values, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidNotIn(List<Integer> values) {
            addCriterion("catid not in", values, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidBetween(Integer value1, Integer value2) {
            addCriterion("catid between", value1, value2, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidNotBetween(Integer value1, Integer value2) {
            addCriterion("catid not between", value1, value2, "catid");
            return (Criteria) this;
        }

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(String value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(String value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(String value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(String value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(String value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(String value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLike(String value) {
            addCriterion("pid like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotLike(String value) {
            addCriterion("pid not like", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<String> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<String> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(String value1, String value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(String value1, String value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andCatpathIsNull() {
            addCriterion("catpath is null");
            return (Criteria) this;
        }

        public Criteria andCatpathIsNotNull() {
            addCriterion("catpath is not null");
            return (Criteria) this;
        }

        public Criteria andCatpathEqualTo(String value) {
            addCriterion("catpath =", value, "catpath");
            return (Criteria) this;
        }

        public Criteria andCatpathNotEqualTo(String value) {
            addCriterion("catpath <>", value, "catpath");
            return (Criteria) this;
        }

        public Criteria andCatpathGreaterThan(String value) {
            addCriterion("catpath >", value, "catpath");
            return (Criteria) this;
        }

        public Criteria andCatpathGreaterThanOrEqualTo(String value) {
            addCriterion("catpath >=", value, "catpath");
            return (Criteria) this;
        }

        public Criteria andCatpathLessThan(String value) {
            addCriterion("catpath <", value, "catpath");
            return (Criteria) this;
        }

        public Criteria andCatpathLessThanOrEqualTo(String value) {
            addCriterion("catpath <=", value, "catpath");
            return (Criteria) this;
        }

        public Criteria andCatpathLike(String value) {
            addCriterion("catpath like", value, "catpath");
            return (Criteria) this;
        }

        public Criteria andCatpathNotLike(String value) {
            addCriterion("catpath not like", value, "catpath");
            return (Criteria) this;
        }

        public Criteria andCatpathIn(List<String> values) {
            addCriterion("catpath in", values, "catpath");
            return (Criteria) this;
        }

        public Criteria andCatpathNotIn(List<String> values) {
            addCriterion("catpath not in", values, "catpath");
            return (Criteria) this;
        }

        public Criteria andCatpathBetween(String value1, String value2) {
            addCriterion("catpath between", value1, value2, "catpath");
            return (Criteria) this;
        }

        public Criteria andCatpathNotBetween(String value1, String value2) {
            addCriterion("catpath not between", value1, value2, "catpath");
            return (Criteria) this;
        }

        public Criteria andCatIsNull() {
            addCriterion("cat is null");
            return (Criteria) this;
        }

        public Criteria andCatIsNotNull() {
            addCriterion("cat is not null");
            return (Criteria) this;
        }

        public Criteria andCatEqualTo(String value) {
            addCriterion("cat =", value, "cat");
            return (Criteria) this;
        }

        public Criteria andCatNotEqualTo(String value) {
            addCriterion("cat <>", value, "cat");
            return (Criteria) this;
        }

        public Criteria andCatGreaterThan(String value) {
            addCriterion("cat >", value, "cat");
            return (Criteria) this;
        }

        public Criteria andCatGreaterThanOrEqualTo(String value) {
            addCriterion("cat >=", value, "cat");
            return (Criteria) this;
        }

        public Criteria andCatLessThan(String value) {
            addCriterion("cat <", value, "cat");
            return (Criteria) this;
        }

        public Criteria andCatLessThanOrEqualTo(String value) {
            addCriterion("cat <=", value, "cat");
            return (Criteria) this;
        }

        public Criteria andCatLike(String value) {
            addCriterion("cat like", value, "cat");
            return (Criteria) this;
        }

        public Criteria andCatNotLike(String value) {
            addCriterion("cat not like", value, "cat");
            return (Criteria) this;
        }

        public Criteria andCatIn(List<String> values) {
            addCriterion("cat in", values, "cat");
            return (Criteria) this;
        }

        public Criteria andCatNotIn(List<String> values) {
            addCriterion("cat not in", values, "cat");
            return (Criteria) this;
        }

        public Criteria andCatBetween(String value1, String value2) {
            addCriterion("cat between", value1, value2, "cat");
            return (Criteria) this;
        }

        public Criteria andCatNotBetween(String value1, String value2) {
            addCriterion("cat not between", value1, value2, "cat");
            return (Criteria) this;
        }

        public Criteria andPidLikeInsensitive(String value) {
            addCriterion("upper(pid) like", value.toUpperCase(), "pid");
            return (Criteria) this;
        }

        public Criteria andCatpathLikeInsensitive(String value) {
            addCriterion("upper(catpath) like", value.toUpperCase(), "catpath");
            return (Criteria) this;
        }

        public Criteria andCatLikeInsensitive(String value) {
            addCriterion("upper(cat) like", value.toUpperCase(), "cat");
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