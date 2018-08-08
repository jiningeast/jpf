package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayWeixinMpExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayWeixinMpExample() {
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

        public Criteria andEncryptIsNull() {
            addCriterion("encrypt is null");
            return (Criteria) this;
        }

        public Criteria andEncryptIsNotNull() {
            addCriterion("encrypt is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptEqualTo(String value) {
            addCriterion("encrypt =", value, "encrypt");
            return (Criteria) this;
        }

        public Criteria andEncryptNotEqualTo(String value) {
            addCriterion("encrypt <>", value, "encrypt");
            return (Criteria) this;
        }

        public Criteria andEncryptGreaterThan(String value) {
            addCriterion("encrypt >", value, "encrypt");
            return (Criteria) this;
        }

        public Criteria andEncryptGreaterThanOrEqualTo(String value) {
            addCriterion("encrypt >=", value, "encrypt");
            return (Criteria) this;
        }

        public Criteria andEncryptLessThan(String value) {
            addCriterion("encrypt <", value, "encrypt");
            return (Criteria) this;
        }

        public Criteria andEncryptLessThanOrEqualTo(String value) {
            addCriterion("encrypt <=", value, "encrypt");
            return (Criteria) this;
        }

        public Criteria andEncryptLike(String value) {
            addCriterion("encrypt like", value, "encrypt");
            return (Criteria) this;
        }

        public Criteria andEncryptNotLike(String value) {
            addCriterion("encrypt not like", value, "encrypt");
            return (Criteria) this;
        }

        public Criteria andEncryptIn(List<String> values) {
            addCriterion("encrypt in", values, "encrypt");
            return (Criteria) this;
        }

        public Criteria andEncryptNotIn(List<String> values) {
            addCriterion("encrypt not in", values, "encrypt");
            return (Criteria) this;
        }

        public Criteria andEncryptBetween(String value1, String value2) {
            addCriterion("encrypt between", value1, value2, "encrypt");
            return (Criteria) this;
        }

        public Criteria andEncryptNotBetween(String value1, String value2) {
            addCriterion("encrypt not between", value1, value2, "encrypt");
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

        public Criteria andFollowqrIsNull() {
            addCriterion("followQr is null");
            return (Criteria) this;
        }

        public Criteria andFollowqrIsNotNull() {
            addCriterion("followQr is not null");
            return (Criteria) this;
        }

        public Criteria andFollowqrEqualTo(String value) {
            addCriterion("followQr =", value, "followqr");
            return (Criteria) this;
        }

        public Criteria andFollowqrNotEqualTo(String value) {
            addCriterion("followQr <>", value, "followqr");
            return (Criteria) this;
        }

        public Criteria andFollowqrGreaterThan(String value) {
            addCriterion("followQr >", value, "followqr");
            return (Criteria) this;
        }

        public Criteria andFollowqrGreaterThanOrEqualTo(String value) {
            addCriterion("followQr >=", value, "followqr");
            return (Criteria) this;
        }

        public Criteria andFollowqrLessThan(String value) {
            addCriterion("followQr <", value, "followqr");
            return (Criteria) this;
        }

        public Criteria andFollowqrLessThanOrEqualTo(String value) {
            addCriterion("followQr <=", value, "followqr");
            return (Criteria) this;
        }

        public Criteria andFollowqrLike(String value) {
            addCriterion("followQr like", value, "followqr");
            return (Criteria) this;
        }

        public Criteria andFollowqrNotLike(String value) {
            addCriterion("followQr not like", value, "followqr");
            return (Criteria) this;
        }

        public Criteria andFollowqrIn(List<String> values) {
            addCriterion("followQr in", values, "followqr");
            return (Criteria) this;
        }

        public Criteria andFollowqrNotIn(List<String> values) {
            addCriterion("followQr not in", values, "followqr");
            return (Criteria) this;
        }

        public Criteria andFollowqrBetween(String value1, String value2) {
            addCriterion("followQr between", value1, value2, "followqr");
            return (Criteria) this;
        }

        public Criteria andFollowqrNotBetween(String value1, String value2) {
            addCriterion("followQr not between", value1, value2, "followqr");
            return (Criteria) this;
        }

        public Criteria andAppidIsNull() {
            addCriterion("appid is null");
            return (Criteria) this;
        }

        public Criteria andAppidIsNotNull() {
            addCriterion("appid is not null");
            return (Criteria) this;
        }

        public Criteria andAppidEqualTo(String value) {
            addCriterion("appid =", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotEqualTo(String value) {
            addCriterion("appid <>", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThan(String value) {
            addCriterion("appid >", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThanOrEqualTo(String value) {
            addCriterion("appid >=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThan(String value) {
            addCriterion("appid <", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThanOrEqualTo(String value) {
            addCriterion("appid <=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLike(String value) {
            addCriterion("appid like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotLike(String value) {
            addCriterion("appid not like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidIn(List<String> values) {
            addCriterion("appid in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotIn(List<String> values) {
            addCriterion("appid not in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidBetween(String value1, String value2) {
            addCriterion("appid between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotBetween(String value1, String value2) {
            addCriterion("appid not between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppsecretIsNull() {
            addCriterion("appSecret is null");
            return (Criteria) this;
        }

        public Criteria andAppsecretIsNotNull() {
            addCriterion("appSecret is not null");
            return (Criteria) this;
        }

        public Criteria andAppsecretEqualTo(String value) {
            addCriterion("appSecret =", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotEqualTo(String value) {
            addCriterion("appSecret <>", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretGreaterThan(String value) {
            addCriterion("appSecret >", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretGreaterThanOrEqualTo(String value) {
            addCriterion("appSecret >=", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretLessThan(String value) {
            addCriterion("appSecret <", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretLessThanOrEqualTo(String value) {
            addCriterion("appSecret <=", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretLike(String value) {
            addCriterion("appSecret like", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotLike(String value) {
            addCriterion("appSecret not like", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretIn(List<String> values) {
            addCriterion("appSecret in", values, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotIn(List<String> values) {
            addCriterion("appSecret not in", values, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretBetween(String value1, String value2) {
            addCriterion("appSecret between", value1, value2, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotBetween(String value1, String value2) {
            addCriterion("appSecret not between", value1, value2, "appsecret");
            return (Criteria) this;
        }

        public Criteria andTokenIsNull() {
            addCriterion("token is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("token is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(String value) {
            addCriterion("token =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(String value) {
            addCriterion("token <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(String value) {
            addCriterion("token >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(String value) {
            addCriterion("token >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(String value) {
            addCriterion("token <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(String value) {
            addCriterion("token <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLike(String value) {
            addCriterion("token like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotLike(String value) {
            addCriterion("token not like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<String> values) {
            addCriterion("token in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<String> values) {
            addCriterion("token not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(String value1, String value2) {
            addCriterion("token between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(String value1, String value2) {
            addCriterion("token not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andAccesstokenIsNull() {
            addCriterion("accessToken is null");
            return (Criteria) this;
        }

        public Criteria andAccesstokenIsNotNull() {
            addCriterion("accessToken is not null");
            return (Criteria) this;
        }

        public Criteria andAccesstokenEqualTo(String value) {
            addCriterion("accessToken =", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenNotEqualTo(String value) {
            addCriterion("accessToken <>", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenGreaterThan(String value) {
            addCriterion("accessToken >", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenGreaterThanOrEqualTo(String value) {
            addCriterion("accessToken >=", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenLessThan(String value) {
            addCriterion("accessToken <", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenLessThanOrEqualTo(String value) {
            addCriterion("accessToken <=", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenLike(String value) {
            addCriterion("accessToken like", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenNotLike(String value) {
            addCriterion("accessToken not like", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenIn(List<String> values) {
            addCriterion("accessToken in", values, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenNotIn(List<String> values) {
            addCriterion("accessToken not in", values, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenBetween(String value1, String value2) {
            addCriterion("accessToken between", value1, value2, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenNotBetween(String value1, String value2) {
            addCriterion("accessToken not between", value1, value2, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andTokenexpiresIsNull() {
            addCriterion("tokenExpires is null");
            return (Criteria) this;
        }

        public Criteria andTokenexpiresIsNotNull() {
            addCriterion("tokenExpires is not null");
            return (Criteria) this;
        }

        public Criteria andTokenexpiresEqualTo(Date value) {
            addCriterion("tokenExpires =", value, "tokenexpires");
            return (Criteria) this;
        }

        public Criteria andTokenexpiresNotEqualTo(Date value) {
            addCriterion("tokenExpires <>", value, "tokenexpires");
            return (Criteria) this;
        }

        public Criteria andTokenexpiresGreaterThan(Date value) {
            addCriterion("tokenExpires >", value, "tokenexpires");
            return (Criteria) this;
        }

        public Criteria andTokenexpiresGreaterThanOrEqualTo(Date value) {
            addCriterion("tokenExpires >=", value, "tokenexpires");
            return (Criteria) this;
        }

        public Criteria andTokenexpiresLessThan(Date value) {
            addCriterion("tokenExpires <", value, "tokenexpires");
            return (Criteria) this;
        }

        public Criteria andTokenexpiresLessThanOrEqualTo(Date value) {
            addCriterion("tokenExpires <=", value, "tokenexpires");
            return (Criteria) this;
        }

        public Criteria andTokenexpiresIn(List<Date> values) {
            addCriterion("tokenExpires in", values, "tokenexpires");
            return (Criteria) this;
        }

        public Criteria andTokenexpiresNotIn(List<Date> values) {
            addCriterion("tokenExpires not in", values, "tokenexpires");
            return (Criteria) this;
        }

        public Criteria andTokenexpiresBetween(Date value1, Date value2) {
            addCriterion("tokenExpires between", value1, value2, "tokenexpires");
            return (Criteria) this;
        }

        public Criteria andTokenexpiresNotBetween(Date value1, Date value2) {
            addCriterion("tokenExpires not between", value1, value2, "tokenexpires");
            return (Criteria) this;
        }

        public Criteria andFollowreplyIsNull() {
            addCriterion("followReply is null");
            return (Criteria) this;
        }

        public Criteria andFollowreplyIsNotNull() {
            addCriterion("followReply is not null");
            return (Criteria) this;
        }

        public Criteria andFollowreplyEqualTo(String value) {
            addCriterion("followReply =", value, "followreply");
            return (Criteria) this;
        }

        public Criteria andFollowreplyNotEqualTo(String value) {
            addCriterion("followReply <>", value, "followreply");
            return (Criteria) this;
        }

        public Criteria andFollowreplyGreaterThan(String value) {
            addCriterion("followReply >", value, "followreply");
            return (Criteria) this;
        }

        public Criteria andFollowreplyGreaterThanOrEqualTo(String value) {
            addCriterion("followReply >=", value, "followreply");
            return (Criteria) this;
        }

        public Criteria andFollowreplyLessThan(String value) {
            addCriterion("followReply <", value, "followreply");
            return (Criteria) this;
        }

        public Criteria andFollowreplyLessThanOrEqualTo(String value) {
            addCriterion("followReply <=", value, "followreply");
            return (Criteria) this;
        }

        public Criteria andFollowreplyLike(String value) {
            addCriterion("followReply like", value, "followreply");
            return (Criteria) this;
        }

        public Criteria andFollowreplyNotLike(String value) {
            addCriterion("followReply not like", value, "followreply");
            return (Criteria) this;
        }

        public Criteria andFollowreplyIn(List<String> values) {
            addCriterion("followReply in", values, "followreply");
            return (Criteria) this;
        }

        public Criteria andFollowreplyNotIn(List<String> values) {
            addCriterion("followReply not in", values, "followreply");
            return (Criteria) this;
        }

        public Criteria andFollowreplyBetween(String value1, String value2) {
            addCriterion("followReply between", value1, value2, "followreply");
            return (Criteria) this;
        }

        public Criteria andFollowreplyNotBetween(String value1, String value2) {
            addCriterion("followReply not between", value1, value2, "followreply");
            return (Criteria) this;
        }

        public Criteria andMerchantIsNull() {
            addCriterion("merchant is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIsNotNull() {
            addCriterion("merchant is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantEqualTo(String value) {
            addCriterion("merchant =", value, "merchant");
            return (Criteria) this;
        }

        public Criteria andMerchantNotEqualTo(String value) {
            addCriterion("merchant <>", value, "merchant");
            return (Criteria) this;
        }

        public Criteria andMerchantGreaterThan(String value) {
            addCriterion("merchant >", value, "merchant");
            return (Criteria) this;
        }

        public Criteria andMerchantGreaterThanOrEqualTo(String value) {
            addCriterion("merchant >=", value, "merchant");
            return (Criteria) this;
        }

        public Criteria andMerchantLessThan(String value) {
            addCriterion("merchant <", value, "merchant");
            return (Criteria) this;
        }

        public Criteria andMerchantLessThanOrEqualTo(String value) {
            addCriterion("merchant <=", value, "merchant");
            return (Criteria) this;
        }

        public Criteria andMerchantLike(String value) {
            addCriterion("merchant like", value, "merchant");
            return (Criteria) this;
        }

        public Criteria andMerchantNotLike(String value) {
            addCriterion("merchant not like", value, "merchant");
            return (Criteria) this;
        }

        public Criteria andMerchantIn(List<String> values) {
            addCriterion("merchant in", values, "merchant");
            return (Criteria) this;
        }

        public Criteria andMerchantNotIn(List<String> values) {
            addCriterion("merchant not in", values, "merchant");
            return (Criteria) this;
        }

        public Criteria andMerchantBetween(String value1, String value2) {
            addCriterion("merchant between", value1, value2, "merchant");
            return (Criteria) this;
        }

        public Criteria andMerchantNotBetween(String value1, String value2) {
            addCriterion("merchant not between", value1, value2, "merchant");
            return (Criteria) this;
        }

        public Criteria andMerkeyIsNull() {
            addCriterion("merKey is null");
            return (Criteria) this;
        }

        public Criteria andMerkeyIsNotNull() {
            addCriterion("merKey is not null");
            return (Criteria) this;
        }

        public Criteria andMerkeyEqualTo(String value) {
            addCriterion("merKey =", value, "merkey");
            return (Criteria) this;
        }

        public Criteria andMerkeyNotEqualTo(String value) {
            addCriterion("merKey <>", value, "merkey");
            return (Criteria) this;
        }

        public Criteria andMerkeyGreaterThan(String value) {
            addCriterion("merKey >", value, "merkey");
            return (Criteria) this;
        }

        public Criteria andMerkeyGreaterThanOrEqualTo(String value) {
            addCriterion("merKey >=", value, "merkey");
            return (Criteria) this;
        }

        public Criteria andMerkeyLessThan(String value) {
            addCriterion("merKey <", value, "merkey");
            return (Criteria) this;
        }

        public Criteria andMerkeyLessThanOrEqualTo(String value) {
            addCriterion("merKey <=", value, "merkey");
            return (Criteria) this;
        }

        public Criteria andMerkeyLike(String value) {
            addCriterion("merKey like", value, "merkey");
            return (Criteria) this;
        }

        public Criteria andMerkeyNotLike(String value) {
            addCriterion("merKey not like", value, "merkey");
            return (Criteria) this;
        }

        public Criteria andMerkeyIn(List<String> values) {
            addCriterion("merKey in", values, "merkey");
            return (Criteria) this;
        }

        public Criteria andMerkeyNotIn(List<String> values) {
            addCriterion("merKey not in", values, "merkey");
            return (Criteria) this;
        }

        public Criteria andMerkeyBetween(String value1, String value2) {
            addCriterion("merKey between", value1, value2, "merkey");
            return (Criteria) this;
        }

        public Criteria andMerkeyNotBetween(String value1, String value2) {
            addCriterion("merKey not between", value1, value2, "merkey");
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

        public Criteria andEncryptLikeInsensitive(String value) {
            addCriterion("upper(encrypt) like", value.toUpperCase(), "encrypt");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andFollowqrLikeInsensitive(String value) {
            addCriterion("upper(followQr) like", value.toUpperCase(), "followqr");
            return (Criteria) this;
        }

        public Criteria andAppidLikeInsensitive(String value) {
            addCriterion("upper(appid) like", value.toUpperCase(), "appid");
            return (Criteria) this;
        }

        public Criteria andAppsecretLikeInsensitive(String value) {
            addCriterion("upper(appSecret) like", value.toUpperCase(), "appsecret");
            return (Criteria) this;
        }

        public Criteria andTokenLikeInsensitive(String value) {
            addCriterion("upper(token) like", value.toUpperCase(), "token");
            return (Criteria) this;
        }

        public Criteria andAccesstokenLikeInsensitive(String value) {
            addCriterion("upper(accessToken) like", value.toUpperCase(), "accesstoken");
            return (Criteria) this;
        }

        public Criteria andFollowreplyLikeInsensitive(String value) {
            addCriterion("upper(followReply) like", value.toUpperCase(), "followreply");
            return (Criteria) this;
        }

        public Criteria andMerchantLikeInsensitive(String value) {
            addCriterion("upper(merchant) like", value.toUpperCase(), "merchant");
            return (Criteria) this;
        }

        public Criteria andMerkeyLikeInsensitive(String value) {
            addCriterion("upper(merKey) like", value.toUpperCase(), "merkey");
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