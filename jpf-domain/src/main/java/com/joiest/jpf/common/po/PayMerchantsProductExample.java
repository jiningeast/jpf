package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayMerchantsProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayMerchantsProductExample() {
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

        public Criteria andPidIsNull() {
            addCriterion("pid is null");
            return (Criteria) this;
        }

        public Criteria andPidIsNotNull() {
            addCriterion("pid is not null");
            return (Criteria) this;
        }

        public Criteria andPidEqualTo(Long value) {
            addCriterion("pid =", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotEqualTo(Long value) {
            addCriterion("pid <>", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThan(Long value) {
            addCriterion("pid >", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidGreaterThanOrEqualTo(Long value) {
            addCriterion("pid >=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThan(Long value) {
            addCriterion("pid <", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidLessThanOrEqualTo(Long value) {
            addCriterion("pid <=", value, "pid");
            return (Criteria) this;
        }

        public Criteria andPidIn(List<Long> values) {
            addCriterion("pid in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotIn(List<Long> values) {
            addCriterion("pid not in", values, "pid");
            return (Criteria) this;
        }

        public Criteria andPidBetween(Long value1, Long value2) {
            addCriterion("pid between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andPidNotBetween(Long value1, Long value2) {
            addCriterion("pid not between", value1, value2, "pid");
            return (Criteria) this;
        }

        public Criteria andMtsidIsNull() {
            addCriterion("mtsid is null");
            return (Criteria) this;
        }

        public Criteria andMtsidIsNotNull() {
            addCriterion("mtsid is not null");
            return (Criteria) this;
        }

        public Criteria andMtsidEqualTo(Long value) {
            addCriterion("mtsid =", value, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidNotEqualTo(Long value) {
            addCriterion("mtsid <>", value, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidGreaterThan(Long value) {
            addCriterion("mtsid >", value, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidGreaterThanOrEqualTo(Long value) {
            addCriterion("mtsid >=", value, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidLessThan(Long value) {
            addCriterion("mtsid <", value, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidLessThanOrEqualTo(Long value) {
            addCriterion("mtsid <=", value, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidIn(List<Long> values) {
            addCriterion("mtsid in", values, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidNotIn(List<Long> values) {
            addCriterion("mtsid not in", values, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidBetween(Long value1, Long value2) {
            addCriterion("mtsid between", value1, value2, "mtsid");
            return (Criteria) this;
        }

        public Criteria andMtsidNotBetween(Long value1, Long value2) {
            addCriterion("mtsid not between", value1, value2, "mtsid");
            return (Criteria) this;
        }

        public Criteria andPnameIsNull() {
            addCriterion("pname is null");
            return (Criteria) this;
        }

        public Criteria andPnameIsNotNull() {
            addCriterion("pname is not null");
            return (Criteria) this;
        }

        public Criteria andPnameEqualTo(String value) {
            addCriterion("pname =", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameNotEqualTo(String value) {
            addCriterion("pname <>", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameGreaterThan(String value) {
            addCriterion("pname >", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameGreaterThanOrEqualTo(String value) {
            addCriterion("pname >=", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameLessThan(String value) {
            addCriterion("pname <", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameLessThanOrEqualTo(String value) {
            addCriterion("pname <=", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameLike(String value) {
            addCriterion("pname like", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameNotLike(String value) {
            addCriterion("pname not like", value, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameIn(List<String> values) {
            addCriterion("pname in", values, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameNotIn(List<String> values) {
            addCriterion("pname not in", values, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameBetween(String value1, String value2) {
            addCriterion("pname between", value1, value2, "pname");
            return (Criteria) this;
        }

        public Criteria andPnameNotBetween(String value1, String value2) {
            addCriterion("pname not between", value1, value2, "pname");
            return (Criteria) this;
        }

        public Criteria andPintroIsNull() {
            addCriterion("pintro is null");
            return (Criteria) this;
        }

        public Criteria andPintroIsNotNull() {
            addCriterion("pintro is not null");
            return (Criteria) this;
        }

        public Criteria andPintroEqualTo(String value) {
            addCriterion("pintro =", value, "pintro");
            return (Criteria) this;
        }

        public Criteria andPintroNotEqualTo(String value) {
            addCriterion("pintro <>", value, "pintro");
            return (Criteria) this;
        }

        public Criteria andPintroGreaterThan(String value) {
            addCriterion("pintro >", value, "pintro");
            return (Criteria) this;
        }

        public Criteria andPintroGreaterThanOrEqualTo(String value) {
            addCriterion("pintro >=", value, "pintro");
            return (Criteria) this;
        }

        public Criteria andPintroLessThan(String value) {
            addCriterion("pintro <", value, "pintro");
            return (Criteria) this;
        }

        public Criteria andPintroLessThanOrEqualTo(String value) {
            addCriterion("pintro <=", value, "pintro");
            return (Criteria) this;
        }

        public Criteria andPintroLike(String value) {
            addCriterion("pintro like", value, "pintro");
            return (Criteria) this;
        }

        public Criteria andPintroNotLike(String value) {
            addCriterion("pintro not like", value, "pintro");
            return (Criteria) this;
        }

        public Criteria andPintroIn(List<String> values) {
            addCriterion("pintro in", values, "pintro");
            return (Criteria) this;
        }

        public Criteria andPintroNotIn(List<String> values) {
            addCriterion("pintro not in", values, "pintro");
            return (Criteria) this;
        }

        public Criteria andPintroBetween(String value1, String value2) {
            addCriterion("pintro between", value1, value2, "pintro");
            return (Criteria) this;
        }

        public Criteria andPintroNotBetween(String value1, String value2) {
            addCriterion("pintro not between", value1, value2, "pintro");
            return (Criteria) this;
        }

        public Criteria andPmoneyIsNull() {
            addCriterion("pmoney is null");
            return (Criteria) this;
        }

        public Criteria andPmoneyIsNotNull() {
            addCriterion("pmoney is not null");
            return (Criteria) this;
        }

        public Criteria andPmoneyEqualTo(BigDecimal value) {
            addCriterion("pmoney =", value, "pmoney");
            return (Criteria) this;
        }

        public Criteria andPmoneyNotEqualTo(BigDecimal value) {
            addCriterion("pmoney <>", value, "pmoney");
            return (Criteria) this;
        }

        public Criteria andPmoneyGreaterThan(BigDecimal value) {
            addCriterion("pmoney >", value, "pmoney");
            return (Criteria) this;
        }

        public Criteria andPmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pmoney >=", value, "pmoney");
            return (Criteria) this;
        }

        public Criteria andPmoneyLessThan(BigDecimal value) {
            addCriterion("pmoney <", value, "pmoney");
            return (Criteria) this;
        }

        public Criteria andPmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pmoney <=", value, "pmoney");
            return (Criteria) this;
        }

        public Criteria andPmoneyIn(List<BigDecimal> values) {
            addCriterion("pmoney in", values, "pmoney");
            return (Criteria) this;
        }

        public Criteria andPmoneyNotIn(List<BigDecimal> values) {
            addCriterion("pmoney not in", values, "pmoney");
            return (Criteria) this;
        }

        public Criteria andPmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pmoney between", value1, value2, "pmoney");
            return (Criteria) this;
        }

        public Criteria andPmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pmoney not between", value1, value2, "pmoney");
            return (Criteria) this;
        }

        public Criteria andPdpictureIsNull() {
            addCriterion("pdpicture is null");
            return (Criteria) this;
        }

        public Criteria andPdpictureIsNotNull() {
            addCriterion("pdpicture is not null");
            return (Criteria) this;
        }

        public Criteria andPdpictureEqualTo(String value) {
            addCriterion("pdpicture =", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureNotEqualTo(String value) {
            addCriterion("pdpicture <>", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureGreaterThan(String value) {
            addCriterion("pdpicture >", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureGreaterThanOrEqualTo(String value) {
            addCriterion("pdpicture >=", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureLessThan(String value) {
            addCriterion("pdpicture <", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureLessThanOrEqualTo(String value) {
            addCriterion("pdpicture <=", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureLike(String value) {
            addCriterion("pdpicture like", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureNotLike(String value) {
            addCriterion("pdpicture not like", value, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureIn(List<String> values) {
            addCriterion("pdpicture in", values, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureNotIn(List<String> values) {
            addCriterion("pdpicture not in", values, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureBetween(String value1, String value2) {
            addCriterion("pdpicture between", value1, value2, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andPdpictureNotBetween(String value1, String value2) {
            addCriterion("pdpicture not between", value1, value2, "pdpicture");
            return (Criteria) this;
        }

        public Criteria andZftypeIsNull() {
            addCriterion("zftype is null");
            return (Criteria) this;
        }

        public Criteria andZftypeIsNotNull() {
            addCriterion("zftype is not null");
            return (Criteria) this;
        }

        public Criteria andZftypeEqualTo(String value) {
            addCriterion("zftype =", value, "zftype");
            return (Criteria) this;
        }

        public Criteria andZftypeNotEqualTo(String value) {
            addCriterion("zftype <>", value, "zftype");
            return (Criteria) this;
        }

        public Criteria andZftypeGreaterThan(String value) {
            addCriterion("zftype >", value, "zftype");
            return (Criteria) this;
        }

        public Criteria andZftypeGreaterThanOrEqualTo(String value) {
            addCriterion("zftype >=", value, "zftype");
            return (Criteria) this;
        }

        public Criteria andZftypeLessThan(String value) {
            addCriterion("zftype <", value, "zftype");
            return (Criteria) this;
        }

        public Criteria andZftypeLessThanOrEqualTo(String value) {
            addCriterion("zftype <=", value, "zftype");
            return (Criteria) this;
        }

        public Criteria andZftypeLike(String value) {
            addCriterion("zftype like", value, "zftype");
            return (Criteria) this;
        }

        public Criteria andZftypeNotLike(String value) {
            addCriterion("zftype not like", value, "zftype");
            return (Criteria) this;
        }

        public Criteria andZftypeIn(List<String> values) {
            addCriterion("zftype in", values, "zftype");
            return (Criteria) this;
        }

        public Criteria andZftypeNotIn(List<String> values) {
            addCriterion("zftype not in", values, "zftype");
            return (Criteria) this;
        }

        public Criteria andZftypeBetween(String value1, String value2) {
            addCriterion("zftype between", value1, value2, "zftype");
            return (Criteria) this;
        }

        public Criteria andZftypeNotBetween(String value1, String value2) {
            addCriterion("zftype not between", value1, value2, "zftype");
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

        public Criteria andPnameLikeInsensitive(String value) {
            addCriterion("upper(pname) like", value.toUpperCase(), "pname");
            return (Criteria) this;
        }

        public Criteria andPintroLikeInsensitive(String value) {
            addCriterion("upper(pintro) like", value.toUpperCase(), "pintro");
            return (Criteria) this;
        }

        public Criteria andPdpictureLikeInsensitive(String value) {
            addCriterion("upper(pdpicture) like", value.toUpperCase(), "pdpicture");
            return (Criteria) this;
        }

        public Criteria andZftypeLikeInsensitive(String value) {
            addCriterion("upper(zftype) like", value.toUpperCase(), "zftype");
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