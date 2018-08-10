package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayWeixinUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayWeixinUserExample() {
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

        public Criteria andMpidIsNull() {
            addCriterion("mpid is null");
            return (Criteria) this;
        }

        public Criteria andMpidIsNotNull() {
            addCriterion("mpid is not null");
            return (Criteria) this;
        }

        public Criteria andMpidEqualTo(Long value) {
            addCriterion("mpid =", value, "mpid");
            return (Criteria) this;
        }

        public Criteria andMpidNotEqualTo(Long value) {
            addCriterion("mpid <>", value, "mpid");
            return (Criteria) this;
        }

        public Criteria andMpidGreaterThan(Long value) {
            addCriterion("mpid >", value, "mpid");
            return (Criteria) this;
        }

        public Criteria andMpidGreaterThanOrEqualTo(Long value) {
            addCriterion("mpid >=", value, "mpid");
            return (Criteria) this;
        }

        public Criteria andMpidLessThan(Long value) {
            addCriterion("mpid <", value, "mpid");
            return (Criteria) this;
        }

        public Criteria andMpidLessThanOrEqualTo(Long value) {
            addCriterion("mpid <=", value, "mpid");
            return (Criteria) this;
        }

        public Criteria andMpidIn(List<Long> values) {
            addCriterion("mpid in", values, "mpid");
            return (Criteria) this;
        }

        public Criteria andMpidNotIn(List<Long> values) {
            addCriterion("mpid not in", values, "mpid");
            return (Criteria) this;
        }

        public Criteria andMpidBetween(Long value1, Long value2) {
            addCriterion("mpid between", value1, value2, "mpid");
            return (Criteria) this;
        }

        public Criteria andMpidNotBetween(Long value1, Long value2) {
            addCriterion("mpid not between", value1, value2, "mpid");
            return (Criteria) this;
        }

        public Criteria andSubscribeIsNull() {
            addCriterion("subscribe is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeIsNotNull() {
            addCriterion("subscribe is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeEqualTo(Byte value) {
            addCriterion("subscribe =", value, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeNotEqualTo(Byte value) {
            addCriterion("subscribe <>", value, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeGreaterThan(Byte value) {
            addCriterion("subscribe >", value, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeGreaterThanOrEqualTo(Byte value) {
            addCriterion("subscribe >=", value, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeLessThan(Byte value) {
            addCriterion("subscribe <", value, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeLessThanOrEqualTo(Byte value) {
            addCriterion("subscribe <=", value, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeIn(List<Byte> values) {
            addCriterion("subscribe in", values, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeNotIn(List<Byte> values) {
            addCriterion("subscribe not in", values, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeBetween(Byte value1, Byte value2) {
            addCriterion("subscribe between", value1, value2, "subscribe");
            return (Criteria) this;
        }

        public Criteria andSubscribeNotBetween(Byte value1, Byte value2) {
            addCriterion("subscribe not between", value1, value2, "subscribe");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeIsNull() {
            addCriterion("nicknameEncode is null");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeIsNotNull() {
            addCriterion("nicknameEncode is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeEqualTo(String value) {
            addCriterion("nicknameEncode =", value, "nicknameencode");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeNotEqualTo(String value) {
            addCriterion("nicknameEncode <>", value, "nicknameencode");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeGreaterThan(String value) {
            addCriterion("nicknameEncode >", value, "nicknameencode");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeGreaterThanOrEqualTo(String value) {
            addCriterion("nicknameEncode >=", value, "nicknameencode");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeLessThan(String value) {
            addCriterion("nicknameEncode <", value, "nicknameencode");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeLessThanOrEqualTo(String value) {
            addCriterion("nicknameEncode <=", value, "nicknameencode");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeLike(String value) {
            addCriterion("nicknameEncode like", value, "nicknameencode");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeNotLike(String value) {
            addCriterion("nicknameEncode not like", value, "nicknameencode");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeIn(List<String> values) {
            addCriterion("nicknameEncode in", values, "nicknameencode");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeNotIn(List<String> values) {
            addCriterion("nicknameEncode not in", values, "nicknameencode");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeBetween(String value1, String value2) {
            addCriterion("nicknameEncode between", value1, value2, "nicknameencode");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeNotBetween(String value1, String value2) {
            addCriterion("nicknameEncode not between", value1, value2, "nicknameencode");
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

        public Criteria andSexEqualTo(Byte value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Byte value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Byte value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Byte value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Byte value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Byte value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Byte> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Byte> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Byte value1, Byte value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Byte value1, Byte value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNull() {
            addCriterion("language is null");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNotNull() {
            addCriterion("language is not null");
            return (Criteria) this;
        }

        public Criteria andLanguageEqualTo(String value) {
            addCriterion("language =", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotEqualTo(String value) {
            addCriterion("language <>", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThan(String value) {
            addCriterion("language >", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("language >=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThan(String value) {
            addCriterion("language <", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThanOrEqualTo(String value) {
            addCriterion("language <=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLike(String value) {
            addCriterion("language like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotLike(String value) {
            addCriterion("language not like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageIn(List<String> values) {
            addCriterion("language in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotIn(List<String> values) {
            addCriterion("language not in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageBetween(String value1, String value2) {
            addCriterion("language between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotBetween(String value1, String value2) {
            addCriterion("language not between", value1, value2, "language");
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

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
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

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCountryIsNull() {
            addCriterion("country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlIsNull() {
            addCriterion("headimgurl is null");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlIsNotNull() {
            addCriterion("headimgurl is not null");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlEqualTo(String value) {
            addCriterion("headimgurl =", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlNotEqualTo(String value) {
            addCriterion("headimgurl <>", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlGreaterThan(String value) {
            addCriterion("headimgurl >", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlGreaterThanOrEqualTo(String value) {
            addCriterion("headimgurl >=", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlLessThan(String value) {
            addCriterion("headimgurl <", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlLessThanOrEqualTo(String value) {
            addCriterion("headimgurl <=", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlLike(String value) {
            addCriterion("headimgurl like", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlNotLike(String value) {
            addCriterion("headimgurl not like", value, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlIn(List<String> values) {
            addCriterion("headimgurl in", values, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlNotIn(List<String> values) {
            addCriterion("headimgurl not in", values, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlBetween(String value1, String value2) {
            addCriterion("headimgurl between", value1, value2, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlNotBetween(String value1, String value2) {
            addCriterion("headimgurl not between", value1, value2, "headimgurl");
            return (Criteria) this;
        }

        public Criteria andServerheadimgIsNull() {
            addCriterion("serverheadimg is null");
            return (Criteria) this;
        }

        public Criteria andServerheadimgIsNotNull() {
            addCriterion("serverheadimg is not null");
            return (Criteria) this;
        }

        public Criteria andServerheadimgEqualTo(String value) {
            addCriterion("serverheadimg =", value, "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andServerheadimgNotEqualTo(String value) {
            addCriterion("serverheadimg <>", value, "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andServerheadimgGreaterThan(String value) {
            addCriterion("serverheadimg >", value, "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andServerheadimgGreaterThanOrEqualTo(String value) {
            addCriterion("serverheadimg >=", value, "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andServerheadimgLessThan(String value) {
            addCriterion("serverheadimg <", value, "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andServerheadimgLessThanOrEqualTo(String value) {
            addCriterion("serverheadimg <=", value, "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andServerheadimgLike(String value) {
            addCriterion("serverheadimg like", value, "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andServerheadimgNotLike(String value) {
            addCriterion("serverheadimg not like", value, "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andServerheadimgIn(List<String> values) {
            addCriterion("serverheadimg in", values, "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andServerheadimgNotIn(List<String> values) {
            addCriterion("serverheadimg not in", values, "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andServerheadimgBetween(String value1, String value2) {
            addCriterion("serverheadimg between", value1, value2, "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andServerheadimgNotBetween(String value1, String value2) {
            addCriterion("serverheadimg not between", value1, value2, "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andSubscribetimeIsNull() {
            addCriterion("subscribeTime is null");
            return (Criteria) this;
        }

        public Criteria andSubscribetimeIsNotNull() {
            addCriterion("subscribeTime is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribetimeEqualTo(Date value) {
            addCriterion("subscribeTime =", value, "subscribetime");
            return (Criteria) this;
        }

        public Criteria andSubscribetimeNotEqualTo(Date value) {
            addCriterion("subscribeTime <>", value, "subscribetime");
            return (Criteria) this;
        }

        public Criteria andSubscribetimeGreaterThan(Date value) {
            addCriterion("subscribeTime >", value, "subscribetime");
            return (Criteria) this;
        }

        public Criteria andSubscribetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("subscribeTime >=", value, "subscribetime");
            return (Criteria) this;
        }

        public Criteria andSubscribetimeLessThan(Date value) {
            addCriterion("subscribeTime <", value, "subscribetime");
            return (Criteria) this;
        }

        public Criteria andSubscribetimeLessThanOrEqualTo(Date value) {
            addCriterion("subscribeTime <=", value, "subscribetime");
            return (Criteria) this;
        }

        public Criteria andSubscribetimeIn(List<Date> values) {
            addCriterion("subscribeTime in", values, "subscribetime");
            return (Criteria) this;
        }

        public Criteria andSubscribetimeNotIn(List<Date> values) {
            addCriterion("subscribeTime not in", values, "subscribetime");
            return (Criteria) this;
        }

        public Criteria andSubscribetimeBetween(Date value1, Date value2) {
            addCriterion("subscribeTime between", value1, value2, "subscribetime");
            return (Criteria) this;
        }

        public Criteria andSubscribetimeNotBetween(Date value1, Date value2) {
            addCriterion("subscribeTime not between", value1, value2, "subscribetime");
            return (Criteria) this;
        }

        public Criteria andUnionidIsNull() {
            addCriterion("unionid is null");
            return (Criteria) this;
        }

        public Criteria andUnionidIsNotNull() {
            addCriterion("unionid is not null");
            return (Criteria) this;
        }

        public Criteria andUnionidEqualTo(String value) {
            addCriterion("unionid =", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidNotEqualTo(String value) {
            addCriterion("unionid <>", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidGreaterThan(String value) {
            addCriterion("unionid >", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidGreaterThanOrEqualTo(String value) {
            addCriterion("unionid >=", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidLessThan(String value) {
            addCriterion("unionid <", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidLessThanOrEqualTo(String value) {
            addCriterion("unionid <=", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidLike(String value) {
            addCriterion("unionid like", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidNotLike(String value) {
            addCriterion("unionid not like", value, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidIn(List<String> values) {
            addCriterion("unionid in", values, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidNotIn(List<String> values) {
            addCriterion("unionid not in", values, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidBetween(String value1, String value2) {
            addCriterion("unionid between", value1, value2, "unionid");
            return (Criteria) this;
        }

        public Criteria andUnionidNotBetween(String value1, String value2) {
            addCriterion("unionid not between", value1, value2, "unionid");
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

        public Criteria andGroupidIsNull() {
            addCriterion("groupid is null");
            return (Criteria) this;
        }

        public Criteria andGroupidIsNotNull() {
            addCriterion("groupid is not null");
            return (Criteria) this;
        }

        public Criteria andGroupidEqualTo(Byte value) {
            addCriterion("groupid =", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotEqualTo(Byte value) {
            addCriterion("groupid <>", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThan(Byte value) {
            addCriterion("groupid >", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidGreaterThanOrEqualTo(Byte value) {
            addCriterion("groupid >=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThan(Byte value) {
            addCriterion("groupid <", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidLessThanOrEqualTo(Byte value) {
            addCriterion("groupid <=", value, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidIn(List<Byte> values) {
            addCriterion("groupid in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotIn(List<Byte> values) {
            addCriterion("groupid not in", values, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidBetween(Byte value1, Byte value2) {
            addCriterion("groupid between", value1, value2, "groupid");
            return (Criteria) this;
        }

        public Criteria andGroupidNotBetween(Byte value1, Byte value2) {
            addCriterion("groupid not between", value1, value2, "groupid");
            return (Criteria) this;
        }

        public Criteria andTagidListIsNull() {
            addCriterion("tagid_list is null");
            return (Criteria) this;
        }

        public Criteria andTagidListIsNotNull() {
            addCriterion("tagid_list is not null");
            return (Criteria) this;
        }

        public Criteria andTagidListEqualTo(String value) {
            addCriterion("tagid_list =", value, "tagidList");
            return (Criteria) this;
        }

        public Criteria andTagidListNotEqualTo(String value) {
            addCriterion("tagid_list <>", value, "tagidList");
            return (Criteria) this;
        }

        public Criteria andTagidListGreaterThan(String value) {
            addCriterion("tagid_list >", value, "tagidList");
            return (Criteria) this;
        }

        public Criteria andTagidListGreaterThanOrEqualTo(String value) {
            addCriterion("tagid_list >=", value, "tagidList");
            return (Criteria) this;
        }

        public Criteria andTagidListLessThan(String value) {
            addCriterion("tagid_list <", value, "tagidList");
            return (Criteria) this;
        }

        public Criteria andTagidListLessThanOrEqualTo(String value) {
            addCriterion("tagid_list <=", value, "tagidList");
            return (Criteria) this;
        }

        public Criteria andTagidListLike(String value) {
            addCriterion("tagid_list like", value, "tagidList");
            return (Criteria) this;
        }

        public Criteria andTagidListNotLike(String value) {
            addCriterion("tagid_list not like", value, "tagidList");
            return (Criteria) this;
        }

        public Criteria andTagidListIn(List<String> values) {
            addCriterion("tagid_list in", values, "tagidList");
            return (Criteria) this;
        }

        public Criteria andTagidListNotIn(List<String> values) {
            addCriterion("tagid_list not in", values, "tagidList");
            return (Criteria) this;
        }

        public Criteria andTagidListBetween(String value1, String value2) {
            addCriterion("tagid_list between", value1, value2, "tagidList");
            return (Criteria) this;
        }

        public Criteria andTagidListNotBetween(String value1, String value2) {
            addCriterion("tagid_list not between", value1, value2, "tagidList");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneIsNull() {
            addCriterion("subscribe_scene is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneIsNotNull() {
            addCriterion("subscribe_scene is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneEqualTo(String value) {
            addCriterion("subscribe_scene =", value, "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneNotEqualTo(String value) {
            addCriterion("subscribe_scene <>", value, "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneGreaterThan(String value) {
            addCriterion("subscribe_scene >", value, "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneGreaterThanOrEqualTo(String value) {
            addCriterion("subscribe_scene >=", value, "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneLessThan(String value) {
            addCriterion("subscribe_scene <", value, "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneLessThanOrEqualTo(String value) {
            addCriterion("subscribe_scene <=", value, "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneLike(String value) {
            addCriterion("subscribe_scene like", value, "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneNotLike(String value) {
            addCriterion("subscribe_scene not like", value, "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneIn(List<String> values) {
            addCriterion("subscribe_scene in", values, "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneNotIn(List<String> values) {
            addCriterion("subscribe_scene not in", values, "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneBetween(String value1, String value2) {
            addCriterion("subscribe_scene between", value1, value2, "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneNotBetween(String value1, String value2) {
            addCriterion("subscribe_scene not between", value1, value2, "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneIsNull() {
            addCriterion("qr_scene is null");
            return (Criteria) this;
        }

        public Criteria andQrSceneIsNotNull() {
            addCriterion("qr_scene is not null");
            return (Criteria) this;
        }

        public Criteria andQrSceneEqualTo(String value) {
            addCriterion("qr_scene =", value, "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneNotEqualTo(String value) {
            addCriterion("qr_scene <>", value, "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneGreaterThan(String value) {
            addCriterion("qr_scene >", value, "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneGreaterThanOrEqualTo(String value) {
            addCriterion("qr_scene >=", value, "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneLessThan(String value) {
            addCriterion("qr_scene <", value, "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneLessThanOrEqualTo(String value) {
            addCriterion("qr_scene <=", value, "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneLike(String value) {
            addCriterion("qr_scene like", value, "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneNotLike(String value) {
            addCriterion("qr_scene not like", value, "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneIn(List<String> values) {
            addCriterion("qr_scene in", values, "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneNotIn(List<String> values) {
            addCriterion("qr_scene not in", values, "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneBetween(String value1, String value2) {
            addCriterion("qr_scene between", value1, value2, "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneNotBetween(String value1, String value2) {
            addCriterion("qr_scene not between", value1, value2, "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrIsNull() {
            addCriterion("qr_scene_str is null");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrIsNotNull() {
            addCriterion("qr_scene_str is not null");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrEqualTo(String value) {
            addCriterion("qr_scene_str =", value, "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrNotEqualTo(String value) {
            addCriterion("qr_scene_str <>", value, "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrGreaterThan(String value) {
            addCriterion("qr_scene_str >", value, "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrGreaterThanOrEqualTo(String value) {
            addCriterion("qr_scene_str >=", value, "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrLessThan(String value) {
            addCriterion("qr_scene_str <", value, "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrLessThanOrEqualTo(String value) {
            addCriterion("qr_scene_str <=", value, "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrLike(String value) {
            addCriterion("qr_scene_str like", value, "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrNotLike(String value) {
            addCriterion("qr_scene_str not like", value, "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrIn(List<String> values) {
            addCriterion("qr_scene_str in", values, "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrNotIn(List<String> values) {
            addCriterion("qr_scene_str not in", values, "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrBetween(String value1, String value2) {
            addCriterion("qr_scene_str between", value1, value2, "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrNotBetween(String value1, String value2) {
            addCriterion("qr_scene_str not between", value1, value2, "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIsNull() {
            addCriterion("privilege is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIsNotNull() {
            addCriterion("privilege is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeEqualTo(String value) {
            addCriterion("privilege =", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNotEqualTo(String value) {
            addCriterion("privilege <>", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeGreaterThan(String value) {
            addCriterion("privilege >", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeGreaterThanOrEqualTo(String value) {
            addCriterion("privilege >=", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeLessThan(String value) {
            addCriterion("privilege <", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeLessThanOrEqualTo(String value) {
            addCriterion("privilege <=", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeLike(String value) {
            addCriterion("privilege like", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNotLike(String value) {
            addCriterion("privilege not like", value, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeIn(List<String> values) {
            addCriterion("privilege in", values, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNotIn(List<String> values) {
            addCriterion("privilege not in", values, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeBetween(String value1, String value2) {
            addCriterion("privilege between", value1, value2, "privilege");
            return (Criteria) this;
        }

        public Criteria andPrivilegeNotBetween(String value1, String value2) {
            addCriterion("privilege not between", value1, value2, "privilege");
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

        public Criteria andOpenidLikeInsensitive(String value) {
            addCriterion("upper(openid) like", value.toUpperCase(), "openid");
            return (Criteria) this;
        }

        public Criteria andNicknameLikeInsensitive(String value) {
            addCriterion("upper(nickname) like", value.toUpperCase(), "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameencodeLikeInsensitive(String value) {
            addCriterion("upper(nicknameEncode) like", value.toUpperCase(), "nicknameencode");
            return (Criteria) this;
        }

        public Criteria andLanguageLikeInsensitive(String value) {
            addCriterion("upper(language) like", value.toUpperCase(), "language");
            return (Criteria) this;
        }

        public Criteria andCityLikeInsensitive(String value) {
            addCriterion("upper(city) like", value.toUpperCase(), "city");
            return (Criteria) this;
        }

        public Criteria andProvinceLikeInsensitive(String value) {
            addCriterion("upper(province) like", value.toUpperCase(), "province");
            return (Criteria) this;
        }

        public Criteria andCountryLikeInsensitive(String value) {
            addCriterion("upper(country) like", value.toUpperCase(), "country");
            return (Criteria) this;
        }

        public Criteria andHeadimgurlLikeInsensitive(String value) {
            addCriterion("upper(headimgurl) like", value.toUpperCase(), "headimgurl");
            return (Criteria) this;
        }

        public Criteria andServerheadimgLikeInsensitive(String value) {
            addCriterion("upper(serverheadimg) like", value.toUpperCase(), "serverheadimg");
            return (Criteria) this;
        }

        public Criteria andUnionidLikeInsensitive(String value) {
            addCriterion("upper(unionid) like", value.toUpperCase(), "unionid");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
            return (Criteria) this;
        }

        public Criteria andTagidListLikeInsensitive(String value) {
            addCriterion("upper(tagid_list) like", value.toUpperCase(), "tagidList");
            return (Criteria) this;
        }

        public Criteria andSubscribeSceneLikeInsensitive(String value) {
            addCriterion("upper(subscribe_scene) like", value.toUpperCase(), "subscribeScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneLikeInsensitive(String value) {
            addCriterion("upper(qr_scene) like", value.toUpperCase(), "qrScene");
            return (Criteria) this;
        }

        public Criteria andQrSceneStrLikeInsensitive(String value) {
            addCriterion("upper(qr_scene_str) like", value.toUpperCase(), "qrSceneStr");
            return (Criteria) this;
        }

        public Criteria andPrivilegeLikeInsensitive(String value) {
            addCriterion("upper(privilege) like", value.toUpperCase(), "privilege");
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