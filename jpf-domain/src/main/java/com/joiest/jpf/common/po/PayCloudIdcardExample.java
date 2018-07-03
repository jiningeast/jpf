package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayCloudIdcardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayCloudIdcardExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(String value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(String value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(String value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(String value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(String value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(String value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLike(String value) {
            addCriterion("num like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotLike(String value) {
            addCriterion("num not like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<String> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<String> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(String value1, String value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(String value1, String value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNull() {
            addCriterion("nationality is null");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNotNull() {
            addCriterion("nationality is not null");
            return (Criteria) this;
        }

        public Criteria andNationalityEqualTo(String value) {
            addCriterion("nationality =", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotEqualTo(String value) {
            addCriterion("nationality <>", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThan(String value) {
            addCriterion("nationality >", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThanOrEqualTo(String value) {
            addCriterion("nationality >=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThan(String value) {
            addCriterion("nationality <", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThanOrEqualTo(String value) {
            addCriterion("nationality <=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLike(String value) {
            addCriterion("nationality like", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotLike(String value) {
            addCriterion("nationality not like", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityIn(List<String> values) {
            addCriterion("nationality in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotIn(List<String> values) {
            addCriterion("nationality not in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityBetween(String value1, String value2) {
            addCriterion("nationality between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotBetween(String value1, String value2) {
            addCriterion("nationality not between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andBirthIsNull() {
            addCriterion("birth is null");
            return (Criteria) this;
        }

        public Criteria andBirthIsNotNull() {
            addCriterion("birth is not null");
            return (Criteria) this;
        }

        public Criteria andBirthEqualTo(String value) {
            addCriterion("birth =", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthNotEqualTo(String value) {
            addCriterion("birth <>", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthGreaterThan(String value) {
            addCriterion("birth >", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthGreaterThanOrEqualTo(String value) {
            addCriterion("birth >=", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthLessThan(String value) {
            addCriterion("birth <", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthLessThanOrEqualTo(String value) {
            addCriterion("birth <=", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthLike(String value) {
            addCriterion("birth like", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthNotLike(String value) {
            addCriterion("birth not like", value, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthIn(List<String> values) {
            addCriterion("birth in", values, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthNotIn(List<String> values) {
            addCriterion("birth not in", values, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthBetween(String value1, String value2) {
            addCriterion("birth between", value1, value2, "birth");
            return (Criteria) this;
        }

        public Criteria andBirthNotBetween(String value1, String value2) {
            addCriterion("birth not between", value1, value2, "birth");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalIsNull() {
            addCriterion("faceImgLocal is null");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalIsNotNull() {
            addCriterion("faceImgLocal is not null");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalEqualTo(String value) {
            addCriterion("faceImgLocal =", value, "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalNotEqualTo(String value) {
            addCriterion("faceImgLocal <>", value, "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalGreaterThan(String value) {
            addCriterion("faceImgLocal >", value, "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalGreaterThanOrEqualTo(String value) {
            addCriterion("faceImgLocal >=", value, "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalLessThan(String value) {
            addCriterion("faceImgLocal <", value, "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalLessThanOrEqualTo(String value) {
            addCriterion("faceImgLocal <=", value, "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalLike(String value) {
            addCriterion("faceImgLocal like", value, "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalNotLike(String value) {
            addCriterion("faceImgLocal not like", value, "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalIn(List<String> values) {
            addCriterion("faceImgLocal in", values, "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalNotIn(List<String> values) {
            addCriterion("faceImgLocal not in", values, "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalBetween(String value1, String value2) {
            addCriterion("faceImgLocal between", value1, value2, "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalNotBetween(String value1, String value2) {
            addCriterion("faceImgLocal not between", value1, value2, "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverIsNull() {
            addCriterion("faceImgServer is null");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverIsNotNull() {
            addCriterion("faceImgServer is not null");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverEqualTo(String value) {
            addCriterion("faceImgServer =", value, "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverNotEqualTo(String value) {
            addCriterion("faceImgServer <>", value, "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverGreaterThan(String value) {
            addCriterion("faceImgServer >", value, "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverGreaterThanOrEqualTo(String value) {
            addCriterion("faceImgServer >=", value, "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverLessThan(String value) {
            addCriterion("faceImgServer <", value, "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverLessThanOrEqualTo(String value) {
            addCriterion("faceImgServer <=", value, "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverLike(String value) {
            addCriterion("faceImgServer like", value, "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverNotLike(String value) {
            addCriterion("faceImgServer not like", value, "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverIn(List<String> values) {
            addCriterion("faceImgServer in", values, "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverNotIn(List<String> values) {
            addCriterion("faceImgServer not in", values, "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverBetween(String value1, String value2) {
            addCriterion("faceImgServer between", value1, value2, "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverNotBetween(String value1, String value2) {
            addCriterion("faceImgServer not between", value1, value2, "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFrequestIdIsNull() {
            addCriterion("frequest_id is null");
            return (Criteria) this;
        }

        public Criteria andFrequestIdIsNotNull() {
            addCriterion("frequest_id is not null");
            return (Criteria) this;
        }

        public Criteria andFrequestIdEqualTo(String value) {
            addCriterion("frequest_id =", value, "frequestId");
            return (Criteria) this;
        }

        public Criteria andFrequestIdNotEqualTo(String value) {
            addCriterion("frequest_id <>", value, "frequestId");
            return (Criteria) this;
        }

        public Criteria andFrequestIdGreaterThan(String value) {
            addCriterion("frequest_id >", value, "frequestId");
            return (Criteria) this;
        }

        public Criteria andFrequestIdGreaterThanOrEqualTo(String value) {
            addCriterion("frequest_id >=", value, "frequestId");
            return (Criteria) this;
        }

        public Criteria andFrequestIdLessThan(String value) {
            addCriterion("frequest_id <", value, "frequestId");
            return (Criteria) this;
        }

        public Criteria andFrequestIdLessThanOrEqualTo(String value) {
            addCriterion("frequest_id <=", value, "frequestId");
            return (Criteria) this;
        }

        public Criteria andFrequestIdLike(String value) {
            addCriterion("frequest_id like", value, "frequestId");
            return (Criteria) this;
        }

        public Criteria andFrequestIdNotLike(String value) {
            addCriterion("frequest_id not like", value, "frequestId");
            return (Criteria) this;
        }

        public Criteria andFrequestIdIn(List<String> values) {
            addCriterion("frequest_id in", values, "frequestId");
            return (Criteria) this;
        }

        public Criteria andFrequestIdNotIn(List<String> values) {
            addCriterion("frequest_id not in", values, "frequestId");
            return (Criteria) this;
        }

        public Criteria andFrequestIdBetween(String value1, String value2) {
            addCriterion("frequest_id between", value1, value2, "frequestId");
            return (Criteria) this;
        }

        public Criteria andFrequestIdNotBetween(String value1, String value2) {
            addCriterion("frequest_id not between", value1, value2, "frequestId");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStartDateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStartDateEqualTo(String value) {
            addCriterion("start_date =", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotEqualTo(String value) {
            addCriterion("start_date <>", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThan(String value) {
            addCriterion("start_date >", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateGreaterThanOrEqualTo(String value) {
            addCriterion("start_date >=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThan(String value) {
            addCriterion("start_date <", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLessThanOrEqualTo(String value) {
            addCriterion("start_date <=", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateLike(String value) {
            addCriterion("start_date like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotLike(String value) {
            addCriterion("start_date not like", value, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateIn(List<String> values) {
            addCriterion("start_date in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotIn(List<String> values) {
            addCriterion("start_date not in", values, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateBetween(String value1, String value2) {
            addCriterion("start_date between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andStartDateNotBetween(String value1, String value2) {
            addCriterion("start_date not between", value1, value2, "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEndDateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEndDateEqualTo(String value) {
            addCriterion("end_date =", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotEqualTo(String value) {
            addCriterion("end_date <>", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThan(String value) {
            addCriterion("end_date >", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateGreaterThanOrEqualTo(String value) {
            addCriterion("end_date >=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThan(String value) {
            addCriterion("end_date <", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLessThanOrEqualTo(String value) {
            addCriterion("end_date <=", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLike(String value) {
            addCriterion("end_date like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotLike(String value) {
            addCriterion("end_date not like", value, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateIn(List<String> values) {
            addCriterion("end_date in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotIn(List<String> values) {
            addCriterion("end_date not in", values, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateBetween(String value1, String value2) {
            addCriterion("end_date between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andEndDateNotBetween(String value1, String value2) {
            addCriterion("end_date not between", value1, value2, "endDate");
            return (Criteria) this;
        }

        public Criteria andIssueIsNull() {
            addCriterion("issue is null");
            return (Criteria) this;
        }

        public Criteria andIssueIsNotNull() {
            addCriterion("issue is not null");
            return (Criteria) this;
        }

        public Criteria andIssueEqualTo(String value) {
            addCriterion("issue =", value, "issue");
            return (Criteria) this;
        }

        public Criteria andIssueNotEqualTo(String value) {
            addCriterion("issue <>", value, "issue");
            return (Criteria) this;
        }

        public Criteria andIssueGreaterThan(String value) {
            addCriterion("issue >", value, "issue");
            return (Criteria) this;
        }

        public Criteria andIssueGreaterThanOrEqualTo(String value) {
            addCriterion("issue >=", value, "issue");
            return (Criteria) this;
        }

        public Criteria andIssueLessThan(String value) {
            addCriterion("issue <", value, "issue");
            return (Criteria) this;
        }

        public Criteria andIssueLessThanOrEqualTo(String value) {
            addCriterion("issue <=", value, "issue");
            return (Criteria) this;
        }

        public Criteria andIssueLike(String value) {
            addCriterion("issue like", value, "issue");
            return (Criteria) this;
        }

        public Criteria andIssueNotLike(String value) {
            addCriterion("issue not like", value, "issue");
            return (Criteria) this;
        }

        public Criteria andIssueIn(List<String> values) {
            addCriterion("issue in", values, "issue");
            return (Criteria) this;
        }

        public Criteria andIssueNotIn(List<String> values) {
            addCriterion("issue not in", values, "issue");
            return (Criteria) this;
        }

        public Criteria andIssueBetween(String value1, String value2) {
            addCriterion("issue between", value1, value2, "issue");
            return (Criteria) this;
        }

        public Criteria andIssueNotBetween(String value1, String value2) {
            addCriterion("issue not between", value1, value2, "issue");
            return (Criteria) this;
        }

        public Criteria andBrequestIdIsNull() {
            addCriterion("brequest_id is null");
            return (Criteria) this;
        }

        public Criteria andBrequestIdIsNotNull() {
            addCriterion("brequest_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrequestIdEqualTo(String value) {
            addCriterion("brequest_id =", value, "brequestId");
            return (Criteria) this;
        }

        public Criteria andBrequestIdNotEqualTo(String value) {
            addCriterion("brequest_id <>", value, "brequestId");
            return (Criteria) this;
        }

        public Criteria andBrequestIdGreaterThan(String value) {
            addCriterion("brequest_id >", value, "brequestId");
            return (Criteria) this;
        }

        public Criteria andBrequestIdGreaterThanOrEqualTo(String value) {
            addCriterion("brequest_id >=", value, "brequestId");
            return (Criteria) this;
        }

        public Criteria andBrequestIdLessThan(String value) {
            addCriterion("brequest_id <", value, "brequestId");
            return (Criteria) this;
        }

        public Criteria andBrequestIdLessThanOrEqualTo(String value) {
            addCriterion("brequest_id <=", value, "brequestId");
            return (Criteria) this;
        }

        public Criteria andBrequestIdLike(String value) {
            addCriterion("brequest_id like", value, "brequestId");
            return (Criteria) this;
        }

        public Criteria andBrequestIdNotLike(String value) {
            addCriterion("brequest_id not like", value, "brequestId");
            return (Criteria) this;
        }

        public Criteria andBrequestIdIn(List<String> values) {
            addCriterion("brequest_id in", values, "brequestId");
            return (Criteria) this;
        }

        public Criteria andBrequestIdNotIn(List<String> values) {
            addCriterion("brequest_id not in", values, "brequestId");
            return (Criteria) this;
        }

        public Criteria andBrequestIdBetween(String value1, String value2) {
            addCriterion("brequest_id between", value1, value2, "brequestId");
            return (Criteria) this;
        }

        public Criteria andBrequestIdNotBetween(String value1, String value2) {
            addCriterion("brequest_id not between", value1, value2, "brequestId");
            return (Criteria) this;
        }

        public Criteria andBackimglocalIsNull() {
            addCriterion("backImgLocal is null");
            return (Criteria) this;
        }

        public Criteria andBackimglocalIsNotNull() {
            addCriterion("backImgLocal is not null");
            return (Criteria) this;
        }

        public Criteria andBackimglocalEqualTo(String value) {
            addCriterion("backImgLocal =", value, "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimglocalNotEqualTo(String value) {
            addCriterion("backImgLocal <>", value, "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimglocalGreaterThan(String value) {
            addCriterion("backImgLocal >", value, "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimglocalGreaterThanOrEqualTo(String value) {
            addCriterion("backImgLocal >=", value, "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimglocalLessThan(String value) {
            addCriterion("backImgLocal <", value, "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimglocalLessThanOrEqualTo(String value) {
            addCriterion("backImgLocal <=", value, "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimglocalLike(String value) {
            addCriterion("backImgLocal like", value, "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimglocalNotLike(String value) {
            addCriterion("backImgLocal not like", value, "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimglocalIn(List<String> values) {
            addCriterion("backImgLocal in", values, "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimglocalNotIn(List<String> values) {
            addCriterion("backImgLocal not in", values, "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimglocalBetween(String value1, String value2) {
            addCriterion("backImgLocal between", value1, value2, "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimglocalNotBetween(String value1, String value2) {
            addCriterion("backImgLocal not between", value1, value2, "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimgserverIsNull() {
            addCriterion("backImgServer is null");
            return (Criteria) this;
        }

        public Criteria andBackimgserverIsNotNull() {
            addCriterion("backImgServer is not null");
            return (Criteria) this;
        }

        public Criteria andBackimgserverEqualTo(String value) {
            addCriterion("backImgServer =", value, "backimgserver");
            return (Criteria) this;
        }

        public Criteria andBackimgserverNotEqualTo(String value) {
            addCriterion("backImgServer <>", value, "backimgserver");
            return (Criteria) this;
        }

        public Criteria andBackimgserverGreaterThan(String value) {
            addCriterion("backImgServer >", value, "backimgserver");
            return (Criteria) this;
        }

        public Criteria andBackimgserverGreaterThanOrEqualTo(String value) {
            addCriterion("backImgServer >=", value, "backimgserver");
            return (Criteria) this;
        }

        public Criteria andBackimgserverLessThan(String value) {
            addCriterion("backImgServer <", value, "backimgserver");
            return (Criteria) this;
        }

        public Criteria andBackimgserverLessThanOrEqualTo(String value) {
            addCriterion("backImgServer <=", value, "backimgserver");
            return (Criteria) this;
        }

        public Criteria andBackimgserverLike(String value) {
            addCriterion("backImgServer like", value, "backimgserver");
            return (Criteria) this;
        }

        public Criteria andBackimgserverNotLike(String value) {
            addCriterion("backImgServer not like", value, "backimgserver");
            return (Criteria) this;
        }

        public Criteria andBackimgserverIn(List<String> values) {
            addCriterion("backImgServer in", values, "backimgserver");
            return (Criteria) this;
        }

        public Criteria andBackimgserverNotIn(List<String> values) {
            addCriterion("backImgServer not in", values, "backimgserver");
            return (Criteria) this;
        }

        public Criteria andBackimgserverBetween(String value1, String value2) {
            addCriterion("backImgServer between", value1, value2, "backimgserver");
            return (Criteria) this;
        }

        public Criteria andBackimgserverNotBetween(String value1, String value2) {
            addCriterion("backImgServer not between", value1, value2, "backimgserver");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andNumLikeInsensitive(String value) {
            addCriterion("upper(num) like", value.toUpperCase(), "num");
            return (Criteria) this;
        }

        public Criteria andNationalityLikeInsensitive(String value) {
            addCriterion("upper(nationality) like", value.toUpperCase(), "nationality");
            return (Criteria) this;
        }

        public Criteria andAddressLikeInsensitive(String value) {
            addCriterion("upper(address) like", value.toUpperCase(), "address");
            return (Criteria) this;
        }

        public Criteria andBirthLikeInsensitive(String value) {
            addCriterion("upper(birth) like", value.toUpperCase(), "birth");
            return (Criteria) this;
        }

        public Criteria andSexLikeInsensitive(String value) {
            addCriterion("upper(sex) like", value.toUpperCase(), "sex");
            return (Criteria) this;
        }

        public Criteria andFaceimglocalLikeInsensitive(String value) {
            addCriterion("upper(faceImgLocal) like", value.toUpperCase(), "faceimglocal");
            return (Criteria) this;
        }

        public Criteria andFaceimgserverLikeInsensitive(String value) {
            addCriterion("upper(faceImgServer) like", value.toUpperCase(), "faceimgserver");
            return (Criteria) this;
        }

        public Criteria andFrequestIdLikeInsensitive(String value) {
            addCriterion("upper(frequest_id) like", value.toUpperCase(), "frequestId");
            return (Criteria) this;
        }

        public Criteria andStartDateLikeInsensitive(String value) {
            addCriterion("upper(start_date) like", value.toUpperCase(), "startDate");
            return (Criteria) this;
        }

        public Criteria andEndDateLikeInsensitive(String value) {
            addCriterion("upper(end_date) like", value.toUpperCase(), "endDate");
            return (Criteria) this;
        }

        public Criteria andIssueLikeInsensitive(String value) {
            addCriterion("upper(issue) like", value.toUpperCase(), "issue");
            return (Criteria) this;
        }

        public Criteria andBrequestIdLikeInsensitive(String value) {
            addCriterion("upper(brequest_id) like", value.toUpperCase(), "brequestId");
            return (Criteria) this;
        }

        public Criteria andBackimglocalLikeInsensitive(String value) {
            addCriterion("upper(backImgLocal) like", value.toUpperCase(), "backimglocal");
            return (Criteria) this;
        }

        public Criteria andBackimgserverLikeInsensitive(String value) {
            addCriterion("upper(backImgServer) like", value.toUpperCase(), "backimgserver");
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