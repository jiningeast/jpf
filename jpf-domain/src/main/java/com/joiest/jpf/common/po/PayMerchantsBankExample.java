package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayMerchantsBankExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayMerchantsBankExample() {
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

        public Criteria andMuseridIsNull() {
            addCriterion("muserid is null");
            return (Criteria) this;
        }

        public Criteria andMuseridIsNotNull() {
            addCriterion("muserid is not null");
            return (Criteria) this;
        }

        public Criteria andMuseridEqualTo(Long value) {
            addCriterion("muserid =", value, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridNotEqualTo(Long value) {
            addCriterion("muserid <>", value, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridGreaterThan(Long value) {
            addCriterion("muserid >", value, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridGreaterThanOrEqualTo(Long value) {
            addCriterion("muserid >=", value, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridLessThan(Long value) {
            addCriterion("muserid <", value, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridLessThanOrEqualTo(Long value) {
            addCriterion("muserid <=", value, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridIn(List<Long> values) {
            addCriterion("muserid in", values, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridNotIn(List<Long> values) {
            addCriterion("muserid not in", values, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridBetween(Long value1, Long value2) {
            addCriterion("muserid between", value1, value2, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridNotBetween(Long value1, Long value2) {
            addCriterion("muserid not between", value1, value2, "muserid");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(Long value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(Long value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(Long value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(Long value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(Long value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(Long value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<Long> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<Long> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(Long value1, Long value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(Long value1, Long value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(Long value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(Long value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(Long value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(Long value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(Long value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(Long value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<Long> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<Long> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(Long value1, Long value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(Long value1, Long value2) {
            addCriterion("city not between", value1, value2, "city");
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

        public Criteria andBanktypeIsNull() {
            addCriterion("banktype is null");
            return (Criteria) this;
        }

        public Criteria andBanktypeIsNotNull() {
            addCriterion("banktype is not null");
            return (Criteria) this;
        }

        public Criteria andBanktypeEqualTo(Long value) {
            addCriterion("banktype =", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeNotEqualTo(Long value) {
            addCriterion("banktype <>", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeGreaterThan(Long value) {
            addCriterion("banktype >", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeGreaterThanOrEqualTo(Long value) {
            addCriterion("banktype >=", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeLessThan(Long value) {
            addCriterion("banktype <", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeLessThanOrEqualTo(Long value) {
            addCriterion("banktype <=", value, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeIn(List<Long> values) {
            addCriterion("banktype in", values, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeNotIn(List<Long> values) {
            addCriterion("banktype not in", values, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeBetween(Long value1, Long value2) {
            addCriterion("banktype between", value1, value2, "banktype");
            return (Criteria) this;
        }

        public Criteria andBanktypeNotBetween(Long value1, Long value2) {
            addCriterion("banktype not between", value1, value2, "banktype");
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

        public Criteria andBanksubnameIsNull() {
            addCriterion("banksubname is null");
            return (Criteria) this;
        }

        public Criteria andBanksubnameIsNotNull() {
            addCriterion("banksubname is not null");
            return (Criteria) this;
        }

        public Criteria andBanksubnameEqualTo(String value) {
            addCriterion("banksubname =", value, "banksubname");
            return (Criteria) this;
        }

        public Criteria andBanksubnameNotEqualTo(String value) {
            addCriterion("banksubname <>", value, "banksubname");
            return (Criteria) this;
        }

        public Criteria andBanksubnameGreaterThan(String value) {
            addCriterion("banksubname >", value, "banksubname");
            return (Criteria) this;
        }

        public Criteria andBanksubnameGreaterThanOrEqualTo(String value) {
            addCriterion("banksubname >=", value, "banksubname");
            return (Criteria) this;
        }

        public Criteria andBanksubnameLessThan(String value) {
            addCriterion("banksubname <", value, "banksubname");
            return (Criteria) this;
        }

        public Criteria andBanksubnameLessThanOrEqualTo(String value) {
            addCriterion("banksubname <=", value, "banksubname");
            return (Criteria) this;
        }

        public Criteria andBanksubnameLike(String value) {
            addCriterion("banksubname like", value, "banksubname");
            return (Criteria) this;
        }

        public Criteria andBanksubnameNotLike(String value) {
            addCriterion("banksubname not like", value, "banksubname");
            return (Criteria) this;
        }

        public Criteria andBanksubnameIn(List<String> values) {
            addCriterion("banksubname in", values, "banksubname");
            return (Criteria) this;
        }

        public Criteria andBanksubnameNotIn(List<String> values) {
            addCriterion("banksubname not in", values, "banksubname");
            return (Criteria) this;
        }

        public Criteria andBanksubnameBetween(String value1, String value2) {
            addCriterion("banksubname between", value1, value2, "banksubname");
            return (Criteria) this;
        }

        public Criteria andBanksubnameNotBetween(String value1, String value2) {
            addCriterion("banksubname not between", value1, value2, "banksubname");
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

        public Criteria andBanknameLikeInsensitive(String value) {
            addCriterion("upper(bankname) like", value.toUpperCase(), "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknoLikeInsensitive(String value) {
            addCriterion("upper(bankno) like", value.toUpperCase(), "bankno");
            return (Criteria) this;
        }

        public Criteria andBanksubnameLikeInsensitive(String value) {
            addCriterion("upper(banksubname) like", value.toUpperCase(), "banksubname");
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