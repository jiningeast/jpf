package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.List;

public class PayVirtualExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayVirtualExample() {
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

        public Criteria andCatidEqualTo(Byte value) {
            addCriterion("catid =", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidNotEqualTo(Byte value) {
            addCriterion("catid <>", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidGreaterThan(Byte value) {
            addCriterion("catid >", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidGreaterThanOrEqualTo(Byte value) {
            addCriterion("catid >=", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidLessThan(Byte value) {
            addCriterion("catid <", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidLessThanOrEqualTo(Byte value) {
            addCriterion("catid <=", value, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidIn(List<Byte> values) {
            addCriterion("catid in", values, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidNotIn(List<Byte> values) {
            addCriterion("catid not in", values, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidBetween(Byte value1, Byte value2) {
            addCriterion("catid between", value1, value2, "catid");
            return (Criteria) this;
        }

        public Criteria andCatidNotBetween(Byte value1, Byte value2) {
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

        public Criteria andPidEqualTo(Byte value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Byte value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Byte value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Byte value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Byte value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Byte value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Byte> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Byte> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Byte value1, Byte value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Byte value1, Byte value2) {
            addCriterion("pid not between", value1, value2, "pid");
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

        public Criteria andXuhaoIsNull() {
            addCriterion("xuhao is null");
            return (Criteria) this;
        }

        public Criteria andXuhaoIsNotNull() {
            addCriterion("xuhao is not null");
            return (Criteria) this;
        }

        public Criteria andXuhaoEqualTo(Integer value) {
            addCriterion("xuhao =", value, "xuhao");
            return (Criteria) this;
        }

        public Criteria andXuhaoNotEqualTo(Integer value) {
            addCriterion("xuhao <>", value, "xuhao");
            return (Criteria) this;
        }

        public Criteria andXuhaoGreaterThan(Integer value) {
            addCriterion("xuhao >", value, "xuhao");
            return (Criteria) this;
        }

        public Criteria andXuhaoGreaterThanOrEqualTo(Integer value) {
            addCriterion("xuhao >=", value, "xuhao");
            return (Criteria) this;
        }

        public Criteria andXuhaoLessThan(Integer value) {
            addCriterion("xuhao <", value, "xuhao");
            return (Criteria) this;
        }

        public Criteria andXuhaoLessThanOrEqualTo(Integer value) {
            addCriterion("xuhao <=", value, "xuhao");
            return (Criteria) this;
        }

        public Criteria andXuhaoIn(List<Integer> values) {
            addCriterion("xuhao in", values, "xuhao");
            return (Criteria) this;
        }

        public Criteria andXuhaoNotIn(List<Integer> values) {
            addCriterion("xuhao not in", values, "xuhao");
            return (Criteria) this;
        }

        public Criteria andXuhaoBetween(Integer value1, Integer value2) {
            addCriterion("xuhao between", value1, value2, "xuhao");
            return (Criteria) this;
        }

        public Criteria andXuhaoNotBetween(Integer value1, Integer value2) {
            addCriterion("xuhao not between", value1, value2, "xuhao");
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