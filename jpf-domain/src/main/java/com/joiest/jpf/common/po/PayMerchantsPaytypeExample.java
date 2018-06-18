package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayMerchantsPaytypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayMerchantsPaytypeExample() {
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

        public Criteria andBankidIsNull() {
            addCriterion("bankid is null");
            return (Criteria) this;
        }

        public Criteria andBankidIsNotNull() {
            addCriterion("bankid is not null");
            return (Criteria) this;
        }

        public Criteria andBankidEqualTo(Long value) {
            addCriterion("bankid =", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidNotEqualTo(Long value) {
            addCriterion("bankid <>", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidGreaterThan(Long value) {
            addCriterion("bankid >", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidGreaterThanOrEqualTo(Long value) {
            addCriterion("bankid >=", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidLessThan(Long value) {
            addCriterion("bankid <", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidLessThanOrEqualTo(Long value) {
            addCriterion("bankid <=", value, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidIn(List<Long> values) {
            addCriterion("bankid in", values, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidNotIn(List<Long> values) {
            addCriterion("bankid not in", values, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidBetween(Long value1, Long value2) {
            addCriterion("bankid between", value1, value2, "bankid");
            return (Criteria) this;
        }

        public Criteria andBankidNotBetween(Long value1, Long value2) {
            addCriterion("bankid not between", value1, value2, "bankid");
            return (Criteria) this;
        }

        public Criteria andTpidIsNull() {
            addCriterion("tpid is null");
            return (Criteria) this;
        }

        public Criteria andTpidIsNotNull() {
            addCriterion("tpid is not null");
            return (Criteria) this;
        }

        public Criteria andTpidEqualTo(Integer value) {
            addCriterion("tpid =", value, "tpid");
            return (Criteria) this;
        }

        public Criteria andTpidNotEqualTo(Integer value) {
            addCriterion("tpid <>", value, "tpid");
            return (Criteria) this;
        }

        public Criteria andTpidGreaterThan(Integer value) {
            addCriterion("tpid >", value, "tpid");
            return (Criteria) this;
        }

        public Criteria andTpidGreaterThanOrEqualTo(Integer value) {
            addCriterion("tpid >=", value, "tpid");
            return (Criteria) this;
        }

        public Criteria andTpidLessThan(Integer value) {
            addCriterion("tpid <", value, "tpid");
            return (Criteria) this;
        }

        public Criteria andTpidLessThanOrEqualTo(Integer value) {
            addCriterion("tpid <=", value, "tpid");
            return (Criteria) this;
        }

        public Criteria andTpidIn(List<Integer> values) {
            addCriterion("tpid in", values, "tpid");
            return (Criteria) this;
        }

        public Criteria andTpidNotIn(List<Integer> values) {
            addCriterion("tpid not in", values, "tpid");
            return (Criteria) this;
        }

        public Criteria andTpidBetween(Integer value1, Integer value2) {
            addCriterion("tpid between", value1, value2, "tpid");
            return (Criteria) this;
        }

        public Criteria andTpidNotBetween(Integer value1, Integer value2) {
            addCriterion("tpid not between", value1, value2, "tpid");
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

        public Criteria andBankcatidIsNull() {
            addCriterion("bankcatid is null");
            return (Criteria) this;
        }

        public Criteria andBankcatidIsNotNull() {
            addCriterion("bankcatid is not null");
            return (Criteria) this;
        }

        public Criteria andBankcatidEqualTo(String value) {
            addCriterion("bankcatid =", value, "bankcatid");
            return (Criteria) this;
        }

        public Criteria andBankcatidNotEqualTo(String value) {
            addCriterion("bankcatid <>", value, "bankcatid");
            return (Criteria) this;
        }

        public Criteria andBankcatidGreaterThan(String value) {
            addCriterion("bankcatid >", value, "bankcatid");
            return (Criteria) this;
        }

        public Criteria andBankcatidGreaterThanOrEqualTo(String value) {
            addCriterion("bankcatid >=", value, "bankcatid");
            return (Criteria) this;
        }

        public Criteria andBankcatidLessThan(String value) {
            addCriterion("bankcatid <", value, "bankcatid");
            return (Criteria) this;
        }

        public Criteria andBankcatidLessThanOrEqualTo(String value) {
            addCriterion("bankcatid <=", value, "bankcatid");
            return (Criteria) this;
        }

        public Criteria andBankcatidLike(String value) {
            addCriterion("bankcatid like", value, "bankcatid");
            return (Criteria) this;
        }

        public Criteria andBankcatidNotLike(String value) {
            addCriterion("bankcatid not like", value, "bankcatid");
            return (Criteria) this;
        }

        public Criteria andBankcatidIn(List<String> values) {
            addCriterion("bankcatid in", values, "bankcatid");
            return (Criteria) this;
        }

        public Criteria andBankcatidNotIn(List<String> values) {
            addCriterion("bankcatid not in", values, "bankcatid");
            return (Criteria) this;
        }

        public Criteria andBankcatidBetween(String value1, String value2) {
            addCriterion("bankcatid between", value1, value2, "bankcatid");
            return (Criteria) this;
        }

        public Criteria andBankcatidNotBetween(String value1, String value2) {
            addCriterion("bankcatid not between", value1, value2, "bankcatid");
            return (Criteria) this;
        }

        public Criteria andParamIsNull() {
            addCriterion("param is null");
            return (Criteria) this;
        }

        public Criteria andParamIsNotNull() {
            addCriterion("param is not null");
            return (Criteria) this;
        }

        public Criteria andParamEqualTo(String value) {
            addCriterion("param =", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotEqualTo(String value) {
            addCriterion("param <>", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamGreaterThan(String value) {
            addCriterion("param >", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamGreaterThanOrEqualTo(String value) {
            addCriterion("param >=", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLessThan(String value) {
            addCriterion("param <", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLessThanOrEqualTo(String value) {
            addCriterion("param <=", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLike(String value) {
            addCriterion("param like", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotLike(String value) {
            addCriterion("param not like", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamIn(List<String> values) {
            addCriterion("param in", values, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotIn(List<String> values) {
            addCriterion("param not in", values, "param");
            return (Criteria) this;
        }

        public Criteria andParamBetween(String value1, String value2) {
            addCriterion("param between", value1, value2, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotBetween(String value1, String value2) {
            addCriterion("param not between", value1, value2, "param");
            return (Criteria) this;
        }

        public Criteria andParamSecIsNull() {
            addCriterion("param_sec is null");
            return (Criteria) this;
        }

        public Criteria andParamSecIsNotNull() {
            addCriterion("param_sec is not null");
            return (Criteria) this;
        }

        public Criteria andParamSecEqualTo(String value) {
            addCriterion("param_sec =", value, "paramSec");
            return (Criteria) this;
        }

        public Criteria andParamSecNotEqualTo(String value) {
            addCriterion("param_sec <>", value, "paramSec");
            return (Criteria) this;
        }

        public Criteria andParamSecGreaterThan(String value) {
            addCriterion("param_sec >", value, "paramSec");
            return (Criteria) this;
        }

        public Criteria andParamSecGreaterThanOrEqualTo(String value) {
            addCriterion("param_sec >=", value, "paramSec");
            return (Criteria) this;
        }

        public Criteria andParamSecLessThan(String value) {
            addCriterion("param_sec <", value, "paramSec");
            return (Criteria) this;
        }

        public Criteria andParamSecLessThanOrEqualTo(String value) {
            addCriterion("param_sec <=", value, "paramSec");
            return (Criteria) this;
        }

        public Criteria andParamSecLike(String value) {
            addCriterion("param_sec like", value, "paramSec");
            return (Criteria) this;
        }

        public Criteria andParamSecNotLike(String value) {
            addCriterion("param_sec not like", value, "paramSec");
            return (Criteria) this;
        }

        public Criteria andParamSecIn(List<String> values) {
            addCriterion("param_sec in", values, "paramSec");
            return (Criteria) this;
        }

        public Criteria andParamSecNotIn(List<String> values) {
            addCriterion("param_sec not in", values, "paramSec");
            return (Criteria) this;
        }

        public Criteria andParamSecBetween(String value1, String value2) {
            addCriterion("param_sec between", value1, value2, "paramSec");
            return (Criteria) this;
        }

        public Criteria andParamSecNotBetween(String value1, String value2) {
            addCriterion("param_sec not between", value1, value2, "paramSec");
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

        public Criteria andCatpathLikeInsensitive(String value) {
            addCriterion("upper(catpath) like", value.toUpperCase(), "catpath");
            return (Criteria) this;
        }

        public Criteria andBankcatidLikeInsensitive(String value) {
            addCriterion("upper(bankcatid) like", value.toUpperCase(), "bankcatid");
            return (Criteria) this;
        }

        public Criteria andParamLikeInsensitive(String value) {
            addCriterion("upper(param) like", value.toUpperCase(), "param");
            return (Criteria) this;
        }

        public Criteria andParamSecLikeInsensitive(String value) {
            addCriterion("upper(param_sec) like", value.toUpperCase(), "paramSec");
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