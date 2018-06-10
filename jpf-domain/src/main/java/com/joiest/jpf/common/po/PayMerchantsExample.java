package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayMerchantsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayMerchantsExample() {
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

        public Criteria andMerchNameIsNull() {
            addCriterion("merch_name is null");
            return (Criteria) this;
        }

        public Criteria andMerchNameIsNotNull() {
            addCriterion("merch_name is not null");
            return (Criteria) this;
        }

        public Criteria andMerchNameEqualTo(String value) {
            addCriterion("merch_name =", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameNotEqualTo(String value) {
            addCriterion("merch_name <>", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameGreaterThan(String value) {
            addCriterion("merch_name >", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameGreaterThanOrEqualTo(String value) {
            addCriterion("merch_name >=", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameLessThan(String value) {
            addCriterion("merch_name <", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameLessThanOrEqualTo(String value) {
            addCriterion("merch_name <=", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameLike(String value) {
            addCriterion("merch_name like", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameNotLike(String value) {
            addCriterion("merch_name not like", value, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameIn(List<String> values) {
            addCriterion("merch_name in", values, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameNotIn(List<String> values) {
            addCriterion("merch_name not in", values, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameBetween(String value1, String value2) {
            addCriterion("merch_name between", value1, value2, "merchName");
            return (Criteria) this;
        }

        public Criteria andMerchNameNotBetween(String value1, String value2) {
            addCriterion("merch_name not between", value1, value2, "merchName");
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

        public Criteria andUserpwdIsNull() {
            addCriterion("userpwd is null");
            return (Criteria) this;
        }

        public Criteria andUserpwdIsNotNull() {
            addCriterion("userpwd is not null");
            return (Criteria) this;
        }

        public Criteria andUserpwdEqualTo(String value) {
            addCriterion("userpwd =", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdNotEqualTo(String value) {
            addCriterion("userpwd <>", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdGreaterThan(String value) {
            addCriterion("userpwd >", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdGreaterThanOrEqualTo(String value) {
            addCriterion("userpwd >=", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdLessThan(String value) {
            addCriterion("userpwd <", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdLessThanOrEqualTo(String value) {
            addCriterion("userpwd <=", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdLike(String value) {
            addCriterion("userpwd like", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdNotLike(String value) {
            addCriterion("userpwd not like", value, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdIn(List<String> values) {
            addCriterion("userpwd in", values, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdNotIn(List<String> values) {
            addCriterion("userpwd not in", values, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdBetween(String value1, String value2) {
            addCriterion("userpwd between", value1, value2, "userpwd");
            return (Criteria) this;
        }

        public Criteria andUserpwdNotBetween(String value1, String value2) {
            addCriterion("userpwd not between", value1, value2, "userpwd");
            return (Criteria) this;
        }

        public Criteria andCompanynameIsNull() {
            addCriterion("companyname is null");
            return (Criteria) this;
        }

        public Criteria andCompanynameIsNotNull() {
            addCriterion("companyname is not null");
            return (Criteria) this;
        }

        public Criteria andCompanynameEqualTo(String value) {
            addCriterion("companyname =", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotEqualTo(String value) {
            addCriterion("companyname <>", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameGreaterThan(String value) {
            addCriterion("companyname >", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameGreaterThanOrEqualTo(String value) {
            addCriterion("companyname >=", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameLessThan(String value) {
            addCriterion("companyname <", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameLessThanOrEqualTo(String value) {
            addCriterion("companyname <=", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameLike(String value) {
            addCriterion("companyname like", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotLike(String value) {
            addCriterion("companyname not like", value, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameIn(List<String> values) {
            addCriterion("companyname in", values, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotIn(List<String> values) {
            addCriterion("companyname not in", values, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameBetween(String value1, String value2) {
            addCriterion("companyname between", value1, value2, "companyname");
            return (Criteria) this;
        }

        public Criteria andCompanynameNotBetween(String value1, String value2) {
            addCriterion("companyname not between", value1, value2, "companyname");
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

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(Long value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(Long value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(Long value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(Long value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(Long value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(Long value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<Long> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<Long> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(Long value1, Long value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(Long value1, Long value2) {
            addCriterion("region not between", value1, value2, "region");
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

        public Criteria andLinknameIsNull() {
            addCriterion("linkname is null");
            return (Criteria) this;
        }

        public Criteria andLinknameIsNotNull() {
            addCriterion("linkname is not null");
            return (Criteria) this;
        }

        public Criteria andLinknameEqualTo(String value) {
            addCriterion("linkname =", value, "linkname");
            return (Criteria) this;
        }

        public Criteria andLinknameNotEqualTo(String value) {
            addCriterion("linkname <>", value, "linkname");
            return (Criteria) this;
        }

        public Criteria andLinknameGreaterThan(String value) {
            addCriterion("linkname >", value, "linkname");
            return (Criteria) this;
        }

        public Criteria andLinknameGreaterThanOrEqualTo(String value) {
            addCriterion("linkname >=", value, "linkname");
            return (Criteria) this;
        }

        public Criteria andLinknameLessThan(String value) {
            addCriterion("linkname <", value, "linkname");
            return (Criteria) this;
        }

        public Criteria andLinknameLessThanOrEqualTo(String value) {
            addCriterion("linkname <=", value, "linkname");
            return (Criteria) this;
        }

        public Criteria andLinknameLike(String value) {
            addCriterion("linkname like", value, "linkname");
            return (Criteria) this;
        }

        public Criteria andLinknameNotLike(String value) {
            addCriterion("linkname not like", value, "linkname");
            return (Criteria) this;
        }

        public Criteria andLinknameIn(List<String> values) {
            addCriterion("linkname in", values, "linkname");
            return (Criteria) this;
        }

        public Criteria andLinknameNotIn(List<String> values) {
            addCriterion("linkname not in", values, "linkname");
            return (Criteria) this;
        }

        public Criteria andLinknameBetween(String value1, String value2) {
            addCriterion("linkname between", value1, value2, "linkname");
            return (Criteria) this;
        }

        public Criteria andLinknameNotBetween(String value1, String value2) {
            addCriterion("linkname not between", value1, value2, "linkname");
            return (Criteria) this;
        }

        public Criteria andLinkphoneIsNull() {
            addCriterion("linkphone is null");
            return (Criteria) this;
        }

        public Criteria andLinkphoneIsNotNull() {
            addCriterion("linkphone is not null");
            return (Criteria) this;
        }

        public Criteria andLinkphoneEqualTo(String value) {
            addCriterion("linkphone =", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneNotEqualTo(String value) {
            addCriterion("linkphone <>", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneGreaterThan(String value) {
            addCriterion("linkphone >", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneGreaterThanOrEqualTo(String value) {
            addCriterion("linkphone >=", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneLessThan(String value) {
            addCriterion("linkphone <", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneLessThanOrEqualTo(String value) {
            addCriterion("linkphone <=", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneLike(String value) {
            addCriterion("linkphone like", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneNotLike(String value) {
            addCriterion("linkphone not like", value, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneIn(List<String> values) {
            addCriterion("linkphone in", values, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneNotIn(List<String> values) {
            addCriterion("linkphone not in", values, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneBetween(String value1, String value2) {
            addCriterion("linkphone between", value1, value2, "linkphone");
            return (Criteria) this;
        }

        public Criteria andLinkphoneNotBetween(String value1, String value2) {
            addCriterion("linkphone not between", value1, value2, "linkphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneIsNull() {
            addCriterion("salerphone is null");
            return (Criteria) this;
        }

        public Criteria andSalerphoneIsNotNull() {
            addCriterion("salerphone is not null");
            return (Criteria) this;
        }

        public Criteria andSalerphoneEqualTo(String value) {
            addCriterion("salerphone =", value, "salerphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneNotEqualTo(String value) {
            addCriterion("salerphone <>", value, "salerphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneGreaterThan(String value) {
            addCriterion("salerphone >", value, "salerphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneGreaterThanOrEqualTo(String value) {
            addCriterion("salerphone >=", value, "salerphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneLessThan(String value) {
            addCriterion("salerphone <", value, "salerphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneLessThanOrEqualTo(String value) {
            addCriterion("salerphone <=", value, "salerphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneLike(String value) {
            addCriterion("salerphone like", value, "salerphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneNotLike(String value) {
            addCriterion("salerphone not like", value, "salerphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneIn(List<String> values) {
            addCriterion("salerphone in", values, "salerphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneNotIn(List<String> values) {
            addCriterion("salerphone not in", values, "salerphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneBetween(String value1, String value2) {
            addCriterion("salerphone between", value1, value2, "salerphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneNotBetween(String value1, String value2) {
            addCriterion("salerphone not between", value1, value2, "salerphone");
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

        public Criteria andRegisteripIsNull() {
            addCriterion("registerip is null");
            return (Criteria) this;
        }

        public Criteria andRegisteripIsNotNull() {
            addCriterion("registerip is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteripEqualTo(String value) {
            addCriterion("registerip =", value, "registerip");
            return (Criteria) this;
        }

        public Criteria andRegisteripNotEqualTo(String value) {
            addCriterion("registerip <>", value, "registerip");
            return (Criteria) this;
        }

        public Criteria andRegisteripGreaterThan(String value) {
            addCriterion("registerip >", value, "registerip");
            return (Criteria) this;
        }

        public Criteria andRegisteripGreaterThanOrEqualTo(String value) {
            addCriterion("registerip >=", value, "registerip");
            return (Criteria) this;
        }

        public Criteria andRegisteripLessThan(String value) {
            addCriterion("registerip <", value, "registerip");
            return (Criteria) this;
        }

        public Criteria andRegisteripLessThanOrEqualTo(String value) {
            addCriterion("registerip <=", value, "registerip");
            return (Criteria) this;
        }

        public Criteria andRegisteripLike(String value) {
            addCriterion("registerip like", value, "registerip");
            return (Criteria) this;
        }

        public Criteria andRegisteripNotLike(String value) {
            addCriterion("registerip not like", value, "registerip");
            return (Criteria) this;
        }

        public Criteria andRegisteripIn(List<String> values) {
            addCriterion("registerip in", values, "registerip");
            return (Criteria) this;
        }

        public Criteria andRegisteripNotIn(List<String> values) {
            addCriterion("registerip not in", values, "registerip");
            return (Criteria) this;
        }

        public Criteria andRegisteripBetween(String value1, String value2) {
            addCriterion("registerip between", value1, value2, "registerip");
            return (Criteria) this;
        }

        public Criteria andRegisteripNotBetween(String value1, String value2) {
            addCriterion("registerip not between", value1, value2, "registerip");
            return (Criteria) this;
        }

        public Criteria andLastloginipIsNull() {
            addCriterion("lastloginip is null");
            return (Criteria) this;
        }

        public Criteria andLastloginipIsNotNull() {
            addCriterion("lastloginip is not null");
            return (Criteria) this;
        }

        public Criteria andLastloginipEqualTo(String value) {
            addCriterion("lastloginip =", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipNotEqualTo(String value) {
            addCriterion("lastloginip <>", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipGreaterThan(String value) {
            addCriterion("lastloginip >", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipGreaterThanOrEqualTo(String value) {
            addCriterion("lastloginip >=", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipLessThan(String value) {
            addCriterion("lastloginip <", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipLessThanOrEqualTo(String value) {
            addCriterion("lastloginip <=", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipLike(String value) {
            addCriterion("lastloginip like", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipNotLike(String value) {
            addCriterion("lastloginip not like", value, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipIn(List<String> values) {
            addCriterion("lastloginip in", values, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipNotIn(List<String> values) {
            addCriterion("lastloginip not in", values, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipBetween(String value1, String value2) {
            addCriterion("lastloginip between", value1, value2, "lastloginip");
            return (Criteria) this;
        }

        public Criteria andLastloginipNotBetween(String value1, String value2) {
            addCriterion("lastloginip not between", value1, value2, "lastloginip");
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

        public Criteria andBslicenseIsNull() {
            addCriterion("bslicense is null");
            return (Criteria) this;
        }

        public Criteria andBslicenseIsNotNull() {
            addCriterion("bslicense is not null");
            return (Criteria) this;
        }

        public Criteria andBslicenseEqualTo(String value) {
            addCriterion("bslicense =", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseNotEqualTo(String value) {
            addCriterion("bslicense <>", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseGreaterThan(String value) {
            addCriterion("bslicense >", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseGreaterThanOrEqualTo(String value) {
            addCriterion("bslicense >=", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseLessThan(String value) {
            addCriterion("bslicense <", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseLessThanOrEqualTo(String value) {
            addCriterion("bslicense <=", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseLike(String value) {
            addCriterion("bslicense like", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseNotLike(String value) {
            addCriterion("bslicense not like", value, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseIn(List<String> values) {
            addCriterion("bslicense in", values, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseNotIn(List<String> values) {
            addCriterion("bslicense not in", values, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseBetween(String value1, String value2) {
            addCriterion("bslicense between", value1, value2, "bslicense");
            return (Criteria) this;
        }

        public Criteria andBslicenseNotBetween(String value1, String value2) {
            addCriterion("bslicense not between", value1, value2, "bslicense");
            return (Criteria) this;
        }

        public Criteria andAptitudeIsNull() {
            addCriterion("aptitude is null");
            return (Criteria) this;
        }

        public Criteria andAptitudeIsNotNull() {
            addCriterion("aptitude is not null");
            return (Criteria) this;
        }

        public Criteria andAptitudeEqualTo(String value) {
            addCriterion("aptitude =", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeNotEqualTo(String value) {
            addCriterion("aptitude <>", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeGreaterThan(String value) {
            addCriterion("aptitude >", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeGreaterThanOrEqualTo(String value) {
            addCriterion("aptitude >=", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeLessThan(String value) {
            addCriterion("aptitude <", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeLessThanOrEqualTo(String value) {
            addCriterion("aptitude <=", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeLike(String value) {
            addCriterion("aptitude like", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeNotLike(String value) {
            addCriterion("aptitude not like", value, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeIn(List<String> values) {
            addCriterion("aptitude in", values, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeNotIn(List<String> values) {
            addCriterion("aptitude not in", values, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeBetween(String value1, String value2) {
            addCriterion("aptitude between", value1, value2, "aptitude");
            return (Criteria) this;
        }

        public Criteria andAptitudeNotBetween(String value1, String value2) {
            addCriterion("aptitude not between", value1, value2, "aptitude");
            return (Criteria) this;
        }

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andAttestationIsNull() {
            addCriterion("attestation is null");
            return (Criteria) this;
        }

        public Criteria andAttestationIsNotNull() {
            addCriterion("attestation is not null");
            return (Criteria) this;
        }

        public Criteria andAttestationEqualTo(Boolean value) {
            addCriterion("attestation =", value, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationNotEqualTo(Boolean value) {
            addCriterion("attestation <>", value, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationGreaterThan(Boolean value) {
            addCriterion("attestation >", value, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationGreaterThanOrEqualTo(Boolean value) {
            addCriterion("attestation >=", value, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationLessThan(Boolean value) {
            addCriterion("attestation <", value, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationLessThanOrEqualTo(Boolean value) {
            addCriterion("attestation <=", value, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationIn(List<Boolean> values) {
            addCriterion("attestation in", values, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationNotIn(List<Boolean> values) {
            addCriterion("attestation not in", values, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationBetween(Boolean value1, Boolean value2) {
            addCriterion("attestation between", value1, value2, "attestation");
            return (Criteria) this;
        }

        public Criteria andAttestationNotBetween(Boolean value1, Boolean value2) {
            addCriterion("attestation not between", value1, value2, "attestation");
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

        public Criteria andMuseridEqualTo(Integer value) {
            addCriterion("muserid =", value, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridNotEqualTo(Integer value) {
            addCriterion("muserid <>", value, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridGreaterThan(Integer value) {
            addCriterion("muserid >", value, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("muserid >=", value, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridLessThan(Integer value) {
            addCriterion("muserid <", value, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridLessThanOrEqualTo(Integer value) {
            addCriterion("muserid <=", value, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridIn(List<Integer> values) {
            addCriterion("muserid in", values, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridNotIn(List<Integer> values) {
            addCriterion("muserid not in", values, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridBetween(Integer value1, Integer value2) {
            addCriterion("muserid between", value1, value2, "muserid");
            return (Criteria) this;
        }

        public Criteria andMuseridNotBetween(Integer value1, Integer value2) {
            addCriterion("muserid not between", value1, value2, "muserid");
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

        public Criteria andLegalnameIsNull() {
            addCriterion("legalname is null");
            return (Criteria) this;
        }

        public Criteria andLegalnameIsNotNull() {
            addCriterion("legalname is not null");
            return (Criteria) this;
        }

        public Criteria andLegalnameEqualTo(String value) {
            addCriterion("legalname =", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameNotEqualTo(String value) {
            addCriterion("legalname <>", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameGreaterThan(String value) {
            addCriterion("legalname >", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameGreaterThanOrEqualTo(String value) {
            addCriterion("legalname >=", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameLessThan(String value) {
            addCriterion("legalname <", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameLessThanOrEqualTo(String value) {
            addCriterion("legalname <=", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameLike(String value) {
            addCriterion("legalname like", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameNotLike(String value) {
            addCriterion("legalname not like", value, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameIn(List<String> values) {
            addCriterion("legalname in", values, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameNotIn(List<String> values) {
            addCriterion("legalname not in", values, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameBetween(String value1, String value2) {
            addCriterion("legalname between", value1, value2, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalnameNotBetween(String value1, String value2) {
            addCriterion("legalname not between", value1, value2, "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalidcardIsNull() {
            addCriterion("legalidcard is null");
            return (Criteria) this;
        }

        public Criteria andLegalidcardIsNotNull() {
            addCriterion("legalidcard is not null");
            return (Criteria) this;
        }

        public Criteria andLegalidcardEqualTo(String value) {
            addCriterion("legalidcard =", value, "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalidcardNotEqualTo(String value) {
            addCriterion("legalidcard <>", value, "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalidcardGreaterThan(String value) {
            addCriterion("legalidcard >", value, "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalidcardGreaterThanOrEqualTo(String value) {
            addCriterion("legalidcard >=", value, "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalidcardLessThan(String value) {
            addCriterion("legalidcard <", value, "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalidcardLessThanOrEqualTo(String value) {
            addCriterion("legalidcard <=", value, "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalidcardLike(String value) {
            addCriterion("legalidcard like", value, "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalidcardNotLike(String value) {
            addCriterion("legalidcard not like", value, "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalidcardIn(List<String> values) {
            addCriterion("legalidcard in", values, "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalidcardNotIn(List<String> values) {
            addCriterion("legalidcard not in", values, "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalidcardBetween(String value1, String value2) {
            addCriterion("legalidcard between", value1, value2, "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalidcardNotBetween(String value1, String value2) {
            addCriterion("legalidcard not between", value1, value2, "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalfaceIsNull() {
            addCriterion("legalface is null");
            return (Criteria) this;
        }

        public Criteria andLegalfaceIsNotNull() {
            addCriterion("legalface is not null");
            return (Criteria) this;
        }

        public Criteria andLegalfaceEqualTo(String value) {
            addCriterion("legalface =", value, "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalfaceNotEqualTo(String value) {
            addCriterion("legalface <>", value, "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalfaceGreaterThan(String value) {
            addCriterion("legalface >", value, "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalfaceGreaterThanOrEqualTo(String value) {
            addCriterion("legalface >=", value, "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalfaceLessThan(String value) {
            addCriterion("legalface <", value, "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalfaceLessThanOrEqualTo(String value) {
            addCriterion("legalface <=", value, "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalfaceLike(String value) {
            addCriterion("legalface like", value, "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalfaceNotLike(String value) {
            addCriterion("legalface not like", value, "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalfaceIn(List<String> values) {
            addCriterion("legalface in", values, "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalfaceNotIn(List<String> values) {
            addCriterion("legalface not in", values, "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalfaceBetween(String value1, String value2) {
            addCriterion("legalface between", value1, value2, "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalfaceNotBetween(String value1, String value2) {
            addCriterion("legalface not between", value1, value2, "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalbackIsNull() {
            addCriterion("legalback is null");
            return (Criteria) this;
        }

        public Criteria andLegalbackIsNotNull() {
            addCriterion("legalback is not null");
            return (Criteria) this;
        }

        public Criteria andLegalbackEqualTo(String value) {
            addCriterion("legalback =", value, "legalback");
            return (Criteria) this;
        }

        public Criteria andLegalbackNotEqualTo(String value) {
            addCriterion("legalback <>", value, "legalback");
            return (Criteria) this;
        }

        public Criteria andLegalbackGreaterThan(String value) {
            addCriterion("legalback >", value, "legalback");
            return (Criteria) this;
        }

        public Criteria andLegalbackGreaterThanOrEqualTo(String value) {
            addCriterion("legalback >=", value, "legalback");
            return (Criteria) this;
        }

        public Criteria andLegalbackLessThan(String value) {
            addCriterion("legalback <", value, "legalback");
            return (Criteria) this;
        }

        public Criteria andLegalbackLessThanOrEqualTo(String value) {
            addCriterion("legalback <=", value, "legalback");
            return (Criteria) this;
        }

        public Criteria andLegalbackLike(String value) {
            addCriterion("legalback like", value, "legalback");
            return (Criteria) this;
        }

        public Criteria andLegalbackNotLike(String value) {
            addCriterion("legalback not like", value, "legalback");
            return (Criteria) this;
        }

        public Criteria andLegalbackIn(List<String> values) {
            addCriterion("legalback in", values, "legalback");
            return (Criteria) this;
        }

        public Criteria andLegalbackNotIn(List<String> values) {
            addCriterion("legalback not in", values, "legalback");
            return (Criteria) this;
        }

        public Criteria andLegalbackBetween(String value1, String value2) {
            addCriterion("legalback between", value1, value2, "legalback");
            return (Criteria) this;
        }

        public Criteria andLegalbackNotBetween(String value1, String value2) {
            addCriterion("legalback not between", value1, value2, "legalback");
            return (Criteria) this;
        }

        public Criteria andIdstartdateIsNull() {
            addCriterion("idstartdate is null");
            return (Criteria) this;
        }

        public Criteria andIdstartdateIsNotNull() {
            addCriterion("idstartdate is not null");
            return (Criteria) this;
        }

        public Criteria andIdstartdateEqualTo(String value) {
            addCriterion("idstartdate =", value, "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdstartdateNotEqualTo(String value) {
            addCriterion("idstartdate <>", value, "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdstartdateGreaterThan(String value) {
            addCriterion("idstartdate >", value, "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdstartdateGreaterThanOrEqualTo(String value) {
            addCriterion("idstartdate >=", value, "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdstartdateLessThan(String value) {
            addCriterion("idstartdate <", value, "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdstartdateLessThanOrEqualTo(String value) {
            addCriterion("idstartdate <=", value, "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdstartdateLike(String value) {
            addCriterion("idstartdate like", value, "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdstartdateNotLike(String value) {
            addCriterion("idstartdate not like", value, "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdstartdateIn(List<String> values) {
            addCriterion("idstartdate in", values, "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdstartdateNotIn(List<String> values) {
            addCriterion("idstartdate not in", values, "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdstartdateBetween(String value1, String value2) {
            addCriterion("idstartdate between", value1, value2, "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdstartdateNotBetween(String value1, String value2) {
            addCriterion("idstartdate not between", value1, value2, "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdenddateIsNull() {
            addCriterion("idenddate is null");
            return (Criteria) this;
        }

        public Criteria andIdenddateIsNotNull() {
            addCriterion("idenddate is not null");
            return (Criteria) this;
        }

        public Criteria andIdenddateEqualTo(String value) {
            addCriterion("idenddate =", value, "idenddate");
            return (Criteria) this;
        }

        public Criteria andIdenddateNotEqualTo(String value) {
            addCriterion("idenddate <>", value, "idenddate");
            return (Criteria) this;
        }

        public Criteria andIdenddateGreaterThan(String value) {
            addCriterion("idenddate >", value, "idenddate");
            return (Criteria) this;
        }

        public Criteria andIdenddateGreaterThanOrEqualTo(String value) {
            addCriterion("idenddate >=", value, "idenddate");
            return (Criteria) this;
        }

        public Criteria andIdenddateLessThan(String value) {
            addCriterion("idenddate <", value, "idenddate");
            return (Criteria) this;
        }

        public Criteria andIdenddateLessThanOrEqualTo(String value) {
            addCriterion("idenddate <=", value, "idenddate");
            return (Criteria) this;
        }

        public Criteria andIdenddateLike(String value) {
            addCriterion("idenddate like", value, "idenddate");
            return (Criteria) this;
        }

        public Criteria andIdenddateNotLike(String value) {
            addCriterion("idenddate not like", value, "idenddate");
            return (Criteria) this;
        }

        public Criteria andIdenddateIn(List<String> values) {
            addCriterion("idenddate in", values, "idenddate");
            return (Criteria) this;
        }

        public Criteria andIdenddateNotIn(List<String> values) {
            addCriterion("idenddate not in", values, "idenddate");
            return (Criteria) this;
        }

        public Criteria andIdenddateBetween(String value1, String value2) {
            addCriterion("idenddate between", value1, value2, "idenddate");
            return (Criteria) this;
        }

        public Criteria andIdenddateNotBetween(String value1, String value2) {
            addCriterion("idenddate not between", value1, value2, "idenddate");
            return (Criteria) this;
        }

        public Criteria andLefalhandIsNull() {
            addCriterion("lefalhand is null");
            return (Criteria) this;
        }

        public Criteria andLefalhandIsNotNull() {
            addCriterion("lefalhand is not null");
            return (Criteria) this;
        }

        public Criteria andLefalhandEqualTo(String value) {
            addCriterion("lefalhand =", value, "lefalhand");
            return (Criteria) this;
        }

        public Criteria andLefalhandNotEqualTo(String value) {
            addCriterion("lefalhand <>", value, "lefalhand");
            return (Criteria) this;
        }

        public Criteria andLefalhandGreaterThan(String value) {
            addCriterion("lefalhand >", value, "lefalhand");
            return (Criteria) this;
        }

        public Criteria andLefalhandGreaterThanOrEqualTo(String value) {
            addCriterion("lefalhand >=", value, "lefalhand");
            return (Criteria) this;
        }

        public Criteria andLefalhandLessThan(String value) {
            addCriterion("lefalhand <", value, "lefalhand");
            return (Criteria) this;
        }

        public Criteria andLefalhandLessThanOrEqualTo(String value) {
            addCriterion("lefalhand <=", value, "lefalhand");
            return (Criteria) this;
        }

        public Criteria andLefalhandLike(String value) {
            addCriterion("lefalhand like", value, "lefalhand");
            return (Criteria) this;
        }

        public Criteria andLefalhandNotLike(String value) {
            addCriterion("lefalhand not like", value, "lefalhand");
            return (Criteria) this;
        }

        public Criteria andLefalhandIn(List<String> values) {
            addCriterion("lefalhand in", values, "lefalhand");
            return (Criteria) this;
        }

        public Criteria andLefalhandNotIn(List<String> values) {
            addCriterion("lefalhand not in", values, "lefalhand");
            return (Criteria) this;
        }

        public Criteria andLefalhandBetween(String value1, String value2) {
            addCriterion("lefalhand between", value1, value2, "lefalhand");
            return (Criteria) this;
        }

        public Criteria andLefalhandNotBetween(String value1, String value2) {
            addCriterion("lefalhand not between", value1, value2, "lefalhand");
            return (Criteria) this;
        }

        public Criteria andCertificateIsNull() {
            addCriterion("certificate is null");
            return (Criteria) this;
        }

        public Criteria andCertificateIsNotNull() {
            addCriterion("certificate is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateEqualTo(String value) {
            addCriterion("certificate =", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotEqualTo(String value) {
            addCriterion("certificate <>", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateGreaterThan(String value) {
            addCriterion("certificate >", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateGreaterThanOrEqualTo(String value) {
            addCriterion("certificate >=", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLessThan(String value) {
            addCriterion("certificate <", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLessThanOrEqualTo(String value) {
            addCriterion("certificate <=", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateLike(String value) {
            addCriterion("certificate like", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotLike(String value) {
            addCriterion("certificate not like", value, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateIn(List<String> values) {
            addCriterion("certificate in", values, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotIn(List<String> values) {
            addCriterion("certificate not in", values, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateBetween(String value1, String value2) {
            addCriterion("certificate between", value1, value2, "certificate");
            return (Criteria) this;
        }

        public Criteria andCertificateNotBetween(String value1, String value2) {
            addCriterion("certificate not between", value1, value2, "certificate");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyIsNull() {
            addCriterion("private_key is null");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyIsNotNull() {
            addCriterion("private_key is not null");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyEqualTo(String value) {
            addCriterion("private_key =", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyNotEqualTo(String value) {
            addCriterion("private_key <>", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyGreaterThan(String value) {
            addCriterion("private_key >", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyGreaterThanOrEqualTo(String value) {
            addCriterion("private_key >=", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyLessThan(String value) {
            addCriterion("private_key <", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyLessThanOrEqualTo(String value) {
            addCriterion("private_key <=", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyLike(String value) {
            addCriterion("private_key like", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyNotLike(String value) {
            addCriterion("private_key not like", value, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyIn(List<String> values) {
            addCriterion("private_key in", values, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyNotIn(List<String> values) {
            addCriterion("private_key not in", values, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyBetween(String value1, String value2) {
            addCriterion("private_key between", value1, value2, "privateKey");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyNotBetween(String value1, String value2) {
            addCriterion("private_key not between", value1, value2, "privateKey");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(String value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(String value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(String value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(String value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(String value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(String value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLike(String value) {
            addCriterion("rate like", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotLike(String value) {
            addCriterion("rate not like", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<String> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<String> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(String value1, String value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(String value1, String value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andMerchNoLikeInsensitive(String value) {
            addCriterion("upper(merch_no) like", value.toUpperCase(), "merchNo");
            return (Criteria) this;
        }

        public Criteria andMerchNameLikeInsensitive(String value) {
            addCriterion("upper(merch_name) like", value.toUpperCase(), "merchName");
            return (Criteria) this;
        }

        public Criteria andUsernameLikeInsensitive(String value) {
            addCriterion("upper(username) like", value.toUpperCase(), "username");
            return (Criteria) this;
        }

        public Criteria andUserpwdLikeInsensitive(String value) {
            addCriterion("upper(userpwd) like", value.toUpperCase(), "userpwd");
            return (Criteria) this;
        }

        public Criteria andCompanynameLikeInsensitive(String value) {
            addCriterion("upper(companyname) like", value.toUpperCase(), "companyname");
            return (Criteria) this;
        }

        public Criteria andAddressLikeInsensitive(String value) {
            addCriterion("upper(address) like", value.toUpperCase(), "address");
            return (Criteria) this;
        }

        public Criteria andLinknameLikeInsensitive(String value) {
            addCriterion("upper(linkname) like", value.toUpperCase(), "linkname");
            return (Criteria) this;
        }

        public Criteria andLinkphoneLikeInsensitive(String value) {
            addCriterion("upper(linkphone) like", value.toUpperCase(), "linkphone");
            return (Criteria) this;
        }

        public Criteria andSalerphoneLikeInsensitive(String value) {
            addCriterion("upper(salerphone) like", value.toUpperCase(), "salerphone");
            return (Criteria) this;
        }

        public Criteria andRegisteripLikeInsensitive(String value) {
            addCriterion("upper(registerip) like", value.toUpperCase(), "registerip");
            return (Criteria) this;
        }

        public Criteria andLastloginipLikeInsensitive(String value) {
            addCriterion("upper(lastloginip) like", value.toUpperCase(), "lastloginip");
            return (Criteria) this;
        }

        public Criteria andBslicenseLikeInsensitive(String value) {
            addCriterion("upper(bslicense) like", value.toUpperCase(), "bslicense");
            return (Criteria) this;
        }

        public Criteria andAptitudeLikeInsensitive(String value) {
            addCriterion("upper(aptitude) like", value.toUpperCase(), "aptitude");
            return (Criteria) this;
        }

        public Criteria andLogoLikeInsensitive(String value) {
            addCriterion("upper(logo) like", value.toUpperCase(), "logo");
            return (Criteria) this;
        }

        public Criteria andContentLikeInsensitive(String value) {
            addCriterion("upper(content) like", value.toUpperCase(), "content");
            return (Criteria) this;
        }

        public Criteria andLegalnameLikeInsensitive(String value) {
            addCriterion("upper(legalname) like", value.toUpperCase(), "legalname");
            return (Criteria) this;
        }

        public Criteria andLegalidcardLikeInsensitive(String value) {
            addCriterion("upper(legalidcard) like", value.toUpperCase(), "legalidcard");
            return (Criteria) this;
        }

        public Criteria andLegalfaceLikeInsensitive(String value) {
            addCriterion("upper(legalface) like", value.toUpperCase(), "legalface");
            return (Criteria) this;
        }

        public Criteria andLegalbackLikeInsensitive(String value) {
            addCriterion("upper(legalback) like", value.toUpperCase(), "legalback");
            return (Criteria) this;
        }

        public Criteria andIdstartdateLikeInsensitive(String value) {
            addCriterion("upper(idstartdate) like", value.toUpperCase(), "idstartdate");
            return (Criteria) this;
        }

        public Criteria andIdenddateLikeInsensitive(String value) {
            addCriterion("upper(idenddate) like", value.toUpperCase(), "idenddate");
            return (Criteria) this;
        }

        public Criteria andLefalhandLikeInsensitive(String value) {
            addCriterion("upper(lefalhand) like", value.toUpperCase(), "lefalhand");
            return (Criteria) this;
        }

        public Criteria andCertificateLikeInsensitive(String value) {
            addCriterion("upper(certificate) like", value.toUpperCase(), "certificate");
            return (Criteria) this;
        }

        public Criteria andPrivateKeyLikeInsensitive(String value) {
            addCriterion("upper(private_key) like", value.toUpperCase(), "privateKey");
            return (Criteria) this;
        }

        public Criteria andRateLikeInsensitive(String value) {
            addCriterion("upper(rate) like", value.toUpperCase(), "rate");
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