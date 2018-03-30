package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.List;

public class PayPcaExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayPcaExample() {
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

        public Criteria andPidEqualTo(Integer value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Integer value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Integer value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Integer value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Integer value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Integer> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Integer> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Integer value1, Integer value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Integer value1, Integer value2) {
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

        public Criteria andCatpathCnIsNull() {
            addCriterion("catpath_cn is null");
            return (Criteria) this;
        }

        public Criteria andCatpathCnIsNotNull() {
            addCriterion("catpath_cn is not null");
            return (Criteria) this;
        }

        public Criteria andCatpathCnEqualTo(String value) {
            addCriterion("catpath_cn =", value, "catpathCn");
            return (Criteria) this;
        }

        public Criteria andCatpathCnNotEqualTo(String value) {
            addCriterion("catpath_cn <>", value, "catpathCn");
            return (Criteria) this;
        }

        public Criteria andCatpathCnGreaterThan(String value) {
            addCriterion("catpath_cn >", value, "catpathCn");
            return (Criteria) this;
        }

        public Criteria andCatpathCnGreaterThanOrEqualTo(String value) {
            addCriterion("catpath_cn >=", value, "catpathCn");
            return (Criteria) this;
        }

        public Criteria andCatpathCnLessThan(String value) {
            addCriterion("catpath_cn <", value, "catpathCn");
            return (Criteria) this;
        }

        public Criteria andCatpathCnLessThanOrEqualTo(String value) {
            addCriterion("catpath_cn <=", value, "catpathCn");
            return (Criteria) this;
        }

        public Criteria andCatpathCnLike(String value) {
            addCriterion("catpath_cn like", value, "catpathCn");
            return (Criteria) this;
        }

        public Criteria andCatpathCnNotLike(String value) {
            addCriterion("catpath_cn not like", value, "catpathCn");
            return (Criteria) this;
        }

        public Criteria andCatpathCnIn(List<String> values) {
            addCriterion("catpath_cn in", values, "catpathCn");
            return (Criteria) this;
        }

        public Criteria andCatpathCnNotIn(List<String> values) {
            addCriterion("catpath_cn not in", values, "catpathCn");
            return (Criteria) this;
        }

        public Criteria andCatpathCnBetween(String value1, String value2) {
            addCriterion("catpath_cn between", value1, value2, "catpathCn");
            return (Criteria) this;
        }

        public Criteria andCatpathCnNotBetween(String value1, String value2) {
            addCriterion("catpath_cn not between", value1, value2, "catpathCn");
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNull() {
            addCriterion("postcode is null");
            return (Criteria) this;
        }

        public Criteria andPostcodeIsNotNull() {
            addCriterion("postcode is not null");
            return (Criteria) this;
        }

        public Criteria andPostcodeEqualTo(String value) {
            addCriterion("postcode =", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotEqualTo(String value) {
            addCriterion("postcode <>", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThan(String value) {
            addCriterion("postcode >", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeGreaterThanOrEqualTo(String value) {
            addCriterion("postcode >=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThan(String value) {
            addCriterion("postcode <", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLessThanOrEqualTo(String value) {
            addCriterion("postcode <=", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeLike(String value) {
            addCriterion("postcode like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotLike(String value) {
            addCriterion("postcode not like", value, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeIn(List<String> values) {
            addCriterion("postcode in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotIn(List<String> values) {
            addCriterion("postcode not in", values, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeBetween(String value1, String value2) {
            addCriterion("postcode between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPostcodeNotBetween(String value1, String value2) {
            addCriterion("postcode not between", value1, value2, "postcode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeIsNull() {
            addCriterion("phonecode is null");
            return (Criteria) this;
        }

        public Criteria andPhonecodeIsNotNull() {
            addCriterion("phonecode is not null");
            return (Criteria) this;
        }

        public Criteria andPhonecodeEqualTo(String value) {
            addCriterion("phonecode =", value, "phonecode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeNotEqualTo(String value) {
            addCriterion("phonecode <>", value, "phonecode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeGreaterThan(String value) {
            addCriterion("phonecode >", value, "phonecode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeGreaterThanOrEqualTo(String value) {
            addCriterion("phonecode >=", value, "phonecode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeLessThan(String value) {
            addCriterion("phonecode <", value, "phonecode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeLessThanOrEqualTo(String value) {
            addCriterion("phonecode <=", value, "phonecode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeLike(String value) {
            addCriterion("phonecode like", value, "phonecode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeNotLike(String value) {
            addCriterion("phonecode not like", value, "phonecode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeIn(List<String> values) {
            addCriterion("phonecode in", values, "phonecode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeNotIn(List<String> values) {
            addCriterion("phonecode not in", values, "phonecode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeBetween(String value1, String value2) {
            addCriterion("phonecode between", value1, value2, "phonecode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeNotBetween(String value1, String value2) {
            addCriterion("phonecode not between", value1, value2, "phonecode");
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

        public Criteria andCatpathLikeInsensitive(String value) {
            addCriterion("upper(catpath) like", value.toUpperCase(), "catpath");
            return (Criteria) this;
        }

        public Criteria andCatpathCnLikeInsensitive(String value) {
            addCriterion("upper(catpath_cn) like", value.toUpperCase(), "catpathCn");
            return (Criteria) this;
        }

        public Criteria andCatLikeInsensitive(String value) {
            addCriterion("upper(cat) like", value.toUpperCase(), "cat");
            return (Criteria) this;
        }

        public Criteria andCodeLikeInsensitive(String value) {
            addCriterion("upper(code) like", value.toUpperCase(), "code");
            return (Criteria) this;
        }

        public Criteria andPostcodeLikeInsensitive(String value) {
            addCriterion("upper(postcode) like", value.toUpperCase(), "postcode");
            return (Criteria) this;
        }

        public Criteria andPhonecodeLikeInsensitive(String value) {
            addCriterion("upper(phonecode) like", value.toUpperCase(), "phonecode");
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