package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysParaInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public SysParaInfoExample() {
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

        public Criteria andParaIdIsNull() {
            addCriterion("PARA_ID is null");
            return (Criteria) this;
        }

        public Criteria andParaIdIsNotNull() {
            addCriterion("PARA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParaIdEqualTo(Long value) {
            addCriterion("PARA_ID =", value, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdNotEqualTo(Long value) {
            addCriterion("PARA_ID <>", value, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdGreaterThan(Long value) {
            addCriterion("PARA_ID >", value, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PARA_ID >=", value, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdLessThan(Long value) {
            addCriterion("PARA_ID <", value, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdLessThanOrEqualTo(Long value) {
            addCriterion("PARA_ID <=", value, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdIn(List<Long> values) {
            addCriterion("PARA_ID in", values, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdNotIn(List<Long> values) {
            addCriterion("PARA_ID not in", values, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdBetween(Long value1, Long value2) {
            addCriterion("PARA_ID between", value1, value2, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaIdNotBetween(Long value1, Long value2) {
            addCriterion("PARA_ID not between", value1, value2, "paraId");
            return (Criteria) this;
        }

        public Criteria andParaCodeIsNull() {
            addCriterion("PARA_CODE is null");
            return (Criteria) this;
        }

        public Criteria andParaCodeIsNotNull() {
            addCriterion("PARA_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andParaCodeEqualTo(String value) {
            addCriterion("PARA_CODE =", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeNotEqualTo(String value) {
            addCriterion("PARA_CODE <>", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeGreaterThan(String value) {
            addCriterion("PARA_CODE >", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PARA_CODE >=", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeLessThan(String value) {
            addCriterion("PARA_CODE <", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeLessThanOrEqualTo(String value) {
            addCriterion("PARA_CODE <=", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeLike(String value) {
            addCriterion("PARA_CODE like", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeNotLike(String value) {
            addCriterion("PARA_CODE not like", value, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeIn(List<String> values) {
            addCriterion("PARA_CODE in", values, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeNotIn(List<String> values) {
            addCriterion("PARA_CODE not in", values, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeBetween(String value1, String value2) {
            addCriterion("PARA_CODE between", value1, value2, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaCodeNotBetween(String value1, String value2) {
            addCriterion("PARA_CODE not between", value1, value2, "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaNameIsNull() {
            addCriterion("PARA_NAME is null");
            return (Criteria) this;
        }

        public Criteria andParaNameIsNotNull() {
            addCriterion("PARA_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andParaNameEqualTo(String value) {
            addCriterion("PARA_NAME =", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameNotEqualTo(String value) {
            addCriterion("PARA_NAME <>", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameGreaterThan(String value) {
            addCriterion("PARA_NAME >", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARA_NAME >=", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameLessThan(String value) {
            addCriterion("PARA_NAME <", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameLessThanOrEqualTo(String value) {
            addCriterion("PARA_NAME <=", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameLike(String value) {
            addCriterion("PARA_NAME like", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameNotLike(String value) {
            addCriterion("PARA_NAME not like", value, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameIn(List<String> values) {
            addCriterion("PARA_NAME in", values, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameNotIn(List<String> values) {
            addCriterion("PARA_NAME not in", values, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameBetween(String value1, String value2) {
            addCriterion("PARA_NAME between", value1, value2, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaNameNotBetween(String value1, String value2) {
            addCriterion("PARA_NAME not between", value1, value2, "paraName");
            return (Criteria) this;
        }

        public Criteria andParaValueIsNull() {
            addCriterion("PARA_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andParaValueIsNotNull() {
            addCriterion("PARA_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andParaValueEqualTo(String value) {
            addCriterion("PARA_VALUE =", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueNotEqualTo(String value) {
            addCriterion("PARA_VALUE <>", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueGreaterThan(String value) {
            addCriterion("PARA_VALUE >", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueGreaterThanOrEqualTo(String value) {
            addCriterion("PARA_VALUE >=", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueLessThan(String value) {
            addCriterion("PARA_VALUE <", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueLessThanOrEqualTo(String value) {
            addCriterion("PARA_VALUE <=", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueLike(String value) {
            addCriterion("PARA_VALUE like", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueNotLike(String value) {
            addCriterion("PARA_VALUE not like", value, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueIn(List<String> values) {
            addCriterion("PARA_VALUE in", values, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueNotIn(List<String> values) {
            addCriterion("PARA_VALUE not in", values, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueBetween(String value1, String value2) {
            addCriterion("PARA_VALUE between", value1, value2, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaValueNotBetween(String value1, String value2) {
            addCriterion("PARA_VALUE not between", value1, value2, "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaDescIsNull() {
            addCriterion("PARA_DESC is null");
            return (Criteria) this;
        }

        public Criteria andParaDescIsNotNull() {
            addCriterion("PARA_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andParaDescEqualTo(String value) {
            addCriterion("PARA_DESC =", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescNotEqualTo(String value) {
            addCriterion("PARA_DESC <>", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescGreaterThan(String value) {
            addCriterion("PARA_DESC >", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescGreaterThanOrEqualTo(String value) {
            addCriterion("PARA_DESC >=", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescLessThan(String value) {
            addCriterion("PARA_DESC <", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescLessThanOrEqualTo(String value) {
            addCriterion("PARA_DESC <=", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescLike(String value) {
            addCriterion("PARA_DESC like", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescNotLike(String value) {
            addCriterion("PARA_DESC not like", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescIn(List<String> values) {
            addCriterion("PARA_DESC in", values, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescNotIn(List<String> values) {
            addCriterion("PARA_DESC not in", values, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescBetween(String value1, String value2) {
            addCriterion("PARA_DESC between", value1, value2, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescNotBetween(String value1, String value2) {
            addCriterion("PARA_DESC not between", value1, value2, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andIsUsedIsNull() {
            addCriterion("IS_USED is null");
            return (Criteria) this;
        }

        public Criteria andIsUsedIsNotNull() {
            addCriterion("IS_USED is not null");
            return (Criteria) this;
        }

        public Criteria andIsUsedEqualTo(Boolean value) {
            addCriterion("IS_USED =", value, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedNotEqualTo(Boolean value) {
            addCriterion("IS_USED <>", value, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedGreaterThan(Boolean value) {
            addCriterion("IS_USED >", value, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IS_USED >=", value, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedLessThan(Boolean value) {
            addCriterion("IS_USED <", value, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedLessThanOrEqualTo(Boolean value) {
            addCriterion("IS_USED <=", value, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedIn(List<Boolean> values) {
            addCriterion("IS_USED in", values, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedNotIn(List<Boolean> values) {
            addCriterion("IS_USED not in", values, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_USED between", value1, value2, "isUsed");
            return (Criteria) this;
        }

        public Criteria andIsUsedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_USED not between", value1, value2, "isUsed");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andParaCodeLikeInsensitive(String value) {
            addCriterion("upper(PARA_CODE) like", value.toUpperCase(), "paraCode");
            return (Criteria) this;
        }

        public Criteria andParaNameLikeInsensitive(String value) {
            addCriterion("upper(PARA_NAME) like", value.toUpperCase(), "paraName");
            return (Criteria) this;
        }

        public Criteria andParaValueLikeInsensitive(String value) {
            addCriterion("upper(PARA_VALUE) like", value.toUpperCase(), "paraValue");
            return (Criteria) this;
        }

        public Criteria andParaDescLikeInsensitive(String value) {
            addCriterion("upper(PARA_DESC) like", value.toUpperCase(), "paraDesc");
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