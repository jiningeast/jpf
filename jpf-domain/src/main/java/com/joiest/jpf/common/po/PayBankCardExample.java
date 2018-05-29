package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayBankCardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayBankCardExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNull() {
            addCriterion("bankName is null");
            return (Criteria) this;
        }

        public Criteria andBanknameIsNotNull() {
            addCriterion("bankName is not null");
            return (Criteria) this;
        }

        public Criteria andBanknameEqualTo(String value) {
            addCriterion("bankName =", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotEqualTo(String value) {
            addCriterion("bankName <>", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThan(String value) {
            addCriterion("bankName >", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameGreaterThanOrEqualTo(String value) {
            addCriterion("bankName >=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThan(String value) {
            addCriterion("bankName <", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLessThanOrEqualTo(String value) {
            addCriterion("bankName <=", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameLike(String value) {
            addCriterion("bankName like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotLike(String value) {
            addCriterion("bankName not like", value, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameIn(List<String> values) {
            addCriterion("bankName in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotIn(List<String> values) {
            addCriterion("bankName not in", values, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameBetween(String value1, String value2) {
            addCriterion("bankName between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andBanknameNotBetween(String value1, String value2) {
            addCriterion("bankName not between", value1, value2, "bankname");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
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

        public Criteria andCardnameIsNull() {
            addCriterion("cardName is null");
            return (Criteria) this;
        }

        public Criteria andCardnameIsNotNull() {
            addCriterion("cardName is not null");
            return (Criteria) this;
        }

        public Criteria andCardnameEqualTo(String value) {
            addCriterion("cardName =", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameNotEqualTo(String value) {
            addCriterion("cardName <>", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameGreaterThan(String value) {
            addCriterion("cardName >", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameGreaterThanOrEqualTo(String value) {
            addCriterion("cardName >=", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameLessThan(String value) {
            addCriterion("cardName <", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameLessThanOrEqualTo(String value) {
            addCriterion("cardName <=", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameLike(String value) {
            addCriterion("cardName like", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameNotLike(String value) {
            addCriterion("cardName not like", value, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameIn(List<String> values) {
            addCriterion("cardName in", values, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameNotIn(List<String> values) {
            addCriterion("cardName not in", values, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameBetween(String value1, String value2) {
            addCriterion("cardName between", value1, value2, "cardname");
            return (Criteria) this;
        }

        public Criteria andCardnameNotBetween(String value1, String value2) {
            addCriterion("cardName not between", value1, value2, "cardname");
            return (Criteria) this;
        }

        public Criteria andIsatmIsNull() {
            addCriterion("isAtm is null");
            return (Criteria) this;
        }

        public Criteria andIsatmIsNotNull() {
            addCriterion("isAtm is not null");
            return (Criteria) this;
        }

        public Criteria andIsatmEqualTo(String value) {
            addCriterion("isAtm =", value, "isatm");
            return (Criteria) this;
        }

        public Criteria andIsatmNotEqualTo(String value) {
            addCriterion("isAtm <>", value, "isatm");
            return (Criteria) this;
        }

        public Criteria andIsatmGreaterThan(String value) {
            addCriterion("isAtm >", value, "isatm");
            return (Criteria) this;
        }

        public Criteria andIsatmGreaterThanOrEqualTo(String value) {
            addCriterion("isAtm >=", value, "isatm");
            return (Criteria) this;
        }

        public Criteria andIsatmLessThan(String value) {
            addCriterion("isAtm <", value, "isatm");
            return (Criteria) this;
        }

        public Criteria andIsatmLessThanOrEqualTo(String value) {
            addCriterion("isAtm <=", value, "isatm");
            return (Criteria) this;
        }

        public Criteria andIsatmLike(String value) {
            addCriterion("isAtm like", value, "isatm");
            return (Criteria) this;
        }

        public Criteria andIsatmNotLike(String value) {
            addCriterion("isAtm not like", value, "isatm");
            return (Criteria) this;
        }

        public Criteria andIsatmIn(List<String> values) {
            addCriterion("isAtm in", values, "isatm");
            return (Criteria) this;
        }

        public Criteria andIsatmNotIn(List<String> values) {
            addCriterion("isAtm not in", values, "isatm");
            return (Criteria) this;
        }

        public Criteria andIsatmBetween(String value1, String value2) {
            addCriterion("isAtm between", value1, value2, "isatm");
            return (Criteria) this;
        }

        public Criteria andIsatmNotBetween(String value1, String value2) {
            addCriterion("isAtm not between", value1, value2, "isatm");
            return (Criteria) this;
        }

        public Criteria andIsposIsNull() {
            addCriterion("isPos is null");
            return (Criteria) this;
        }

        public Criteria andIsposIsNotNull() {
            addCriterion("isPos is not null");
            return (Criteria) this;
        }

        public Criteria andIsposEqualTo(String value) {
            addCriterion("isPos =", value, "ispos");
            return (Criteria) this;
        }

        public Criteria andIsposNotEqualTo(String value) {
            addCriterion("isPos <>", value, "ispos");
            return (Criteria) this;
        }

        public Criteria andIsposGreaterThan(String value) {
            addCriterion("isPos >", value, "ispos");
            return (Criteria) this;
        }

        public Criteria andIsposGreaterThanOrEqualTo(String value) {
            addCriterion("isPos >=", value, "ispos");
            return (Criteria) this;
        }

        public Criteria andIsposLessThan(String value) {
            addCriterion("isPos <", value, "ispos");
            return (Criteria) this;
        }

        public Criteria andIsposLessThanOrEqualTo(String value) {
            addCriterion("isPos <=", value, "ispos");
            return (Criteria) this;
        }

        public Criteria andIsposLike(String value) {
            addCriterion("isPos like", value, "ispos");
            return (Criteria) this;
        }

        public Criteria andIsposNotLike(String value) {
            addCriterion("isPos not like", value, "ispos");
            return (Criteria) this;
        }

        public Criteria andIsposIn(List<String> values) {
            addCriterion("isPos in", values, "ispos");
            return (Criteria) this;
        }

        public Criteria andIsposNotIn(List<String> values) {
            addCriterion("isPos not in", values, "ispos");
            return (Criteria) this;
        }

        public Criteria andIsposBetween(String value1, String value2) {
            addCriterion("isPos between", value1, value2, "ispos");
            return (Criteria) this;
        }

        public Criteria andIsposNotBetween(String value1, String value2) {
            addCriterion("isPos not between", value1, value2, "ispos");
            return (Criteria) this;
        }

        public Criteria andCardlenIsNull() {
            addCriterion("cardLen is null");
            return (Criteria) this;
        }

        public Criteria andCardlenIsNotNull() {
            addCriterion("cardLen is not null");
            return (Criteria) this;
        }

        public Criteria andCardlenEqualTo(Integer value) {
            addCriterion("cardLen =", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenNotEqualTo(Integer value) {
            addCriterion("cardLen <>", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenGreaterThan(Integer value) {
            addCriterion("cardLen >", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenGreaterThanOrEqualTo(Integer value) {
            addCriterion("cardLen >=", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenLessThan(Integer value) {
            addCriterion("cardLen <", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenLessThanOrEqualTo(Integer value) {
            addCriterion("cardLen <=", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenLike(Integer value) {
            addCriterion("cardLen like", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenNotLike(Integer value) {
            addCriterion("cardLen not like", value, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenIn(List<Integer> values) {
            addCriterion("cardLen in", values, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenNotIn(List<Integer> values) {
            addCriterion("cardLen not in", values, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenBetween(Integer value1, Integer value2) {
            addCriterion("cardLen between", value1, value2, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardlenNotBetween(Integer value1, Integer value2) {
            addCriterion("cardLen not between", value1, value2, "cardlen");
            return (Criteria) this;
        }

        public Criteria andCardpreIsNull() {
            addCriterion("cardPre is null");
            return (Criteria) this;
        }

        public Criteria andCardpreIsNotNull() {
            addCriterion("cardPre is not null");
            return (Criteria) this;
        }

        public Criteria andCardpreEqualTo(String value) {
            addCriterion("cardPre =", value, "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardpreNotEqualTo(String value) {
            addCriterion("cardPre <>", value, "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardpreGreaterThan(String value) {
            addCriterion("cardPre >", value, "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardpreGreaterThanOrEqualTo(String value) {
            addCriterion("cardPre >=", value, "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardpreLessThan(String value) {
            addCriterion("cardPre <", value, "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardpreLessThanOrEqualTo(String value) {
            addCriterion("cardPre <=", value, "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardpreLike(String value) {
            addCriterion("cardPre like", value, "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardpreNotLike(String value) {
            addCriterion("cardPre not like", value, "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardpreIn(List<String> values) {
            addCriterion("cardPre in", values, "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardpreNotIn(List<String> values) {
            addCriterion("cardPre not in", values, "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardpreBetween(String value1, String value2) {
            addCriterion("cardPre between", value1, value2, "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardpreNotBetween(String value1, String value2) {
            addCriterion("cardPre not between", value1, value2, "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardprelenIsNull() {
            addCriterion("cardPreLen is null");
            return (Criteria) this;
        }

        public Criteria andCardprelenIsNotNull() {
            addCriterion("cardPreLen is not null");
            return (Criteria) this;
        }

        public Criteria andCardprelenEqualTo(String value) {
            addCriterion("cardPreLen =", value, "cardprelen");
            return (Criteria) this;
        }

        public Criteria andCardprelenNotEqualTo(String value) {
            addCriterion("cardPreLen <>", value, "cardprelen");
            return (Criteria) this;
        }

        public Criteria andCardprelenGreaterThan(String value) {
            addCriterion("cardPreLen >", value, "cardprelen");
            return (Criteria) this;
        }

        public Criteria andCardprelenGreaterThanOrEqualTo(String value) {
            addCriterion("cardPreLen >=", value, "cardprelen");
            return (Criteria) this;
        }

        public Criteria andCardprelenLessThan(String value) {
            addCriterion("cardPreLen <", value, "cardprelen");
            return (Criteria) this;
        }

        public Criteria andCardprelenLessThanOrEqualTo(String value) {
            addCriterion("cardPreLen <=", value, "cardprelen");
            return (Criteria) this;
        }

        public Criteria andCardprelenLike(String value) {
            addCriterion("cardPreLen like", value, "cardprelen");
            return (Criteria) this;
        }

        public Criteria andCardprelenNotLike(String value) {
            addCriterion("cardPreLen not like", value, "cardprelen");
            return (Criteria) this;
        }

        public Criteria andCardprelenIn(List<String> values) {
            addCriterion("cardPreLen in", values, "cardprelen");
            return (Criteria) this;
        }

        public Criteria andCardprelenNotIn(List<String> values) {
            addCriterion("cardPreLen not in", values, "cardprelen");
            return (Criteria) this;
        }

        public Criteria andCardprelenBetween(String value1, String value2) {
            addCriterion("cardPreLen between", value1, value2, "cardprelen");
            return (Criteria) this;
        }

        public Criteria andCardprelenNotBetween(String value1, String value2) {
            addCriterion("cardPreLen not between", value1, value2, "cardprelen");
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

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andFindcodeIsNull() {
            addCriterion("findCode is null");
            return (Criteria) this;
        }

        public Criteria andFindcodeIsNotNull() {
            addCriterion("findCode is not null");
            return (Criteria) this;
        }

        public Criteria andFindcodeEqualTo(String value) {
            addCriterion("findCode =", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeNotEqualTo(String value) {
            addCriterion("findCode <>", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeGreaterThan(String value) {
            addCriterion("findCode >", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeGreaterThanOrEqualTo(String value) {
            addCriterion("findCode >=", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeLessThan(String value) {
            addCriterion("findCode <", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeLessThanOrEqualTo(String value) {
            addCriterion("findCode <=", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeLike(String value) {
            addCriterion("findCode like", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeNotLike(String value) {
            addCriterion("findCode not like", value, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeIn(List<String> values) {
            addCriterion("findCode in", values, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeNotIn(List<String> values) {
            addCriterion("findCode not in", values, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeBetween(String value1, String value2) {
            addCriterion("findCode between", value1, value2, "findcode");
            return (Criteria) this;
        }

        public Criteria andFindcodeNotBetween(String value1, String value2) {
            addCriterion("findCode not between", value1, value2, "findcode");
            return (Criteria) this;
        }

        public Criteria andSinglelimitIsNull() {
            addCriterion("Singlelimit is null");
            return (Criteria) this;
        }

        public Criteria andSinglelimitIsNotNull() {
            addCriterion("Singlelimit is not null");
            return (Criteria) this;
        }

        public Criteria andSinglelimitEqualTo(BigDecimal value) {
            addCriterion("Singlelimit =", value, "singlelimit");
            return (Criteria) this;
        }

        public Criteria andSinglelimitNotEqualTo(BigDecimal value) {
            addCriterion("Singlelimit <>", value, "singlelimit");
            return (Criteria) this;
        }

        public Criteria andSinglelimitGreaterThan(BigDecimal value) {
            addCriterion("Singlelimit >", value, "singlelimit");
            return (Criteria) this;
        }

        public Criteria andSinglelimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Singlelimit >=", value, "singlelimit");
            return (Criteria) this;
        }

        public Criteria andSinglelimitLessThan(BigDecimal value) {
            addCriterion("Singlelimit <", value, "singlelimit");
            return (Criteria) this;
        }

        public Criteria andSinglelimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Singlelimit <=", value, "singlelimit");
            return (Criteria) this;
        }

        public Criteria andSinglelimitIn(List<BigDecimal> values) {
            addCriterion("Singlelimit in", values, "singlelimit");
            return (Criteria) this;
        }

        public Criteria andSinglelimitNotIn(List<BigDecimal> values) {
            addCriterion("Singlelimit not in", values, "singlelimit");
            return (Criteria) this;
        }

        public Criteria andSinglelimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Singlelimit between", value1, value2, "singlelimit");
            return (Criteria) this;
        }

        public Criteria andSinglelimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Singlelimit not between", value1, value2, "singlelimit");
            return (Criteria) this;
        }

        public Criteria andDailylimitIsNull() {
            addCriterion("Dailylimit is null");
            return (Criteria) this;
        }

        public Criteria andDailylimitIsNotNull() {
            addCriterion("Dailylimit is not null");
            return (Criteria) this;
        }

        public Criteria andDailylimitEqualTo(BigDecimal value) {
            addCriterion("Dailylimit =", value, "dailylimit");
            return (Criteria) this;
        }

        public Criteria andDailylimitNotEqualTo(BigDecimal value) {
            addCriterion("Dailylimit <>", value, "dailylimit");
            return (Criteria) this;
        }

        public Criteria andDailylimitGreaterThan(BigDecimal value) {
            addCriterion("Dailylimit >", value, "dailylimit");
            return (Criteria) this;
        }

        public Criteria andDailylimitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Dailylimit >=", value, "dailylimit");
            return (Criteria) this;
        }

        public Criteria andDailylimitLessThan(BigDecimal value) {
            addCriterion("Dailylimit <", value, "dailylimit");
            return (Criteria) this;
        }

        public Criteria andDailylimitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Dailylimit <=", value, "dailylimit");
            return (Criteria) this;
        }

        public Criteria andDailylimitIn(List<BigDecimal> values) {
            addCriterion("Dailylimit in", values, "dailylimit");
            return (Criteria) this;
        }

        public Criteria andDailylimitNotIn(List<BigDecimal> values) {
            addCriterion("Dailylimit not in", values, "dailylimit");
            return (Criteria) this;
        }

        public Criteria andDailylimitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Dailylimit between", value1, value2, "dailylimit");
            return (Criteria) this;
        }

        public Criteria andDailylimitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Dailylimit not between", value1, value2, "dailylimit");
            return (Criteria) this;
        }

        public Criteria andMonthlyquotaIsNull() {
            addCriterion("Monthlyquota is null");
            return (Criteria) this;
        }

        public Criteria andMonthlyquotaIsNotNull() {
            addCriterion("Monthlyquota is not null");
            return (Criteria) this;
        }

        public Criteria andMonthlyquotaEqualTo(BigDecimal value) {
            addCriterion("Monthlyquota =", value, "monthlyquota");
            return (Criteria) this;
        }

        public Criteria andMonthlyquotaNotEqualTo(BigDecimal value) {
            addCriterion("Monthlyquota <>", value, "monthlyquota");
            return (Criteria) this;
        }

        public Criteria andMonthlyquotaGreaterThan(BigDecimal value) {
            addCriterion("Monthlyquota >", value, "monthlyquota");
            return (Criteria) this;
        }

        public Criteria andMonthlyquotaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Monthlyquota >=", value, "monthlyquota");
            return (Criteria) this;
        }

        public Criteria andMonthlyquotaLessThan(BigDecimal value) {
            addCriterion("Monthlyquota <", value, "monthlyquota");
            return (Criteria) this;
        }

        public Criteria andMonthlyquotaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Monthlyquota <=", value, "monthlyquota");
            return (Criteria) this;
        }

        public Criteria andMonthlyquotaIn(List<BigDecimal> values) {
            addCriterion("Monthlyquota in", values, "monthlyquota");
            return (Criteria) this;
        }

        public Criteria andMonthlyquotaNotIn(List<BigDecimal> values) {
            addCriterion("Monthlyquota not in", values, "monthlyquota");
            return (Criteria) this;
        }

        public Criteria andMonthlyquotaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Monthlyquota between", value1, value2, "monthlyquota");
            return (Criteria) this;
        }

        public Criteria andMonthlyquotaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Monthlyquota not between", value1, value2, "monthlyquota");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(String value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(String value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(String value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(String value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(String value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(String value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLike(String value) {
            addCriterion("sort like", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotLike(String value) {
            addCriterion("sort not like", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<String> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<String> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(String value1, String value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(String value1, String value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andImagesIsNull() {
            addCriterion("images is null");
            return (Criteria) this;
        }

        public Criteria andImagesIsNotNull() {
            addCriterion("images is not null");
            return (Criteria) this;
        }

        public Criteria andImagesEqualTo(String value) {
            addCriterion("images =", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotEqualTo(String value) {
            addCriterion("images <>", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesGreaterThan(String value) {
            addCriterion("images >", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesGreaterThanOrEqualTo(String value) {
            addCriterion("images >=", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLessThan(String value) {
            addCriterion("images <", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLessThanOrEqualTo(String value) {
            addCriterion("images <=", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesLike(String value) {
            addCriterion("images like", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotLike(String value) {
            addCriterion("images not like", value, "images");
            return (Criteria) this;
        }

        public Criteria andImagesIn(List<String> values) {
            addCriterion("images in", values, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotIn(List<String> values) {
            addCriterion("images not in", values, "images");
            return (Criteria) this;
        }

        public Criteria andImagesBetween(String value1, String value2) {
            addCriterion("images between", value1, value2, "images");
            return (Criteria) this;
        }

        public Criteria andImagesNotBetween(String value1, String value2) {
            addCriterion("images not between", value1, value2, "images");
            return (Criteria) this;
        }

        public Criteria andSmalimagesIsNull() {
            addCriterion("smalImages is null");
            return (Criteria) this;
        }

        public Criteria andSmalimagesIsNotNull() {
            addCriterion("smalImages is not null");
            return (Criteria) this;
        }

        public Criteria andSmalimagesEqualTo(String value) {
            addCriterion("smalImages =", value, "smalimages");
            return (Criteria) this;
        }

        public Criteria andSmalimagesNotEqualTo(String value) {
            addCriterion("smalImages <>", value, "smalimages");
            return (Criteria) this;
        }

        public Criteria andSmalimagesGreaterThan(String value) {
            addCriterion("smalImages >", value, "smalimages");
            return (Criteria) this;
        }

        public Criteria andSmalimagesGreaterThanOrEqualTo(String value) {
            addCriterion("smalImages >=", value, "smalimages");
            return (Criteria) this;
        }

        public Criteria andSmalimagesLessThan(String value) {
            addCriterion("smalImages <", value, "smalimages");
            return (Criteria) this;
        }

        public Criteria andSmalimagesLessThanOrEqualTo(String value) {
            addCriterion("smalImages <=", value, "smalimages");
            return (Criteria) this;
        }

        public Criteria andSmalimagesLike(String value) {
            addCriterion("smalImages like", value, "smalimages");
            return (Criteria) this;
        }

        public Criteria andSmalimagesNotLike(String value) {
            addCriterion("smalImages not like", value, "smalimages");
            return (Criteria) this;
        }

        public Criteria andSmalimagesIn(List<String> values) {
            addCriterion("smalImages in", values, "smalimages");
            return (Criteria) this;
        }

        public Criteria andSmalimagesNotIn(List<String> values) {
            addCriterion("smalImages not in", values, "smalimages");
            return (Criteria) this;
        }

        public Criteria andSmalimagesBetween(String value1, String value2) {
            addCriterion("smalImages between", value1, value2, "smalimages");
            return (Criteria) this;
        }

        public Criteria andSmalimagesNotBetween(String value1, String value2) {
            addCriterion("smalImages not between", value1, value2, "smalimages");
            return (Criteria) this;
        }

        public Criteria andYftcodeIsNull() {
            addCriterion("yftcode is null");
            return (Criteria) this;
        }

        public Criteria andYftcodeIsNotNull() {
            addCriterion("yftcode is not null");
            return (Criteria) this;
        }

        public Criteria andYftcodeEqualTo(String value) {
            addCriterion("yftcode =", value, "yftcode");
            return (Criteria) this;
        }

        public Criteria andYftcodeNotEqualTo(String value) {
            addCriterion("yftcode <>", value, "yftcode");
            return (Criteria) this;
        }

        public Criteria andYftcodeGreaterThan(String value) {
            addCriterion("yftcode >", value, "yftcode");
            return (Criteria) this;
        }

        public Criteria andYftcodeGreaterThanOrEqualTo(String value) {
            addCriterion("yftcode >=", value, "yftcode");
            return (Criteria) this;
        }

        public Criteria andYftcodeLessThan(String value) {
            addCriterion("yftcode <", value, "yftcode");
            return (Criteria) this;
        }

        public Criteria andYftcodeLessThanOrEqualTo(String value) {
            addCriterion("yftcode <=", value, "yftcode");
            return (Criteria) this;
        }

        public Criteria andYftcodeLike(String value) {
            addCriterion("yftcode like", value, "yftcode");
            return (Criteria) this;
        }

        public Criteria andYftcodeNotLike(String value) {
            addCriterion("yftcode not like", value, "yftcode");
            return (Criteria) this;
        }

        public Criteria andYftcodeIn(List<String> values) {
            addCriterion("yftcode in", values, "yftcode");
            return (Criteria) this;
        }

        public Criteria andYftcodeNotIn(List<String> values) {
            addCriterion("yftcode not in", values, "yftcode");
            return (Criteria) this;
        }

        public Criteria andYftcodeBetween(String value1, String value2) {
            addCriterion("yftcode between", value1, value2, "yftcode");
            return (Criteria) this;
        }

        public Criteria andYftcodeNotBetween(String value1, String value2) {
            addCriterion("yftcode not between", value1, value2, "yftcode");
            return (Criteria) this;
        }

        public Criteria andBanknameLikeInsensitive(String value) {
            addCriterion("upper(bankName) like", value.toUpperCase(), "bankname");
            return (Criteria) this;
        }

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(title) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andCodeLikeInsensitive(String value) {
            addCriterion("upper(code) like", value.toUpperCase(), "code");
            return (Criteria) this;
        }

        public Criteria andCardnameLikeInsensitive(String value) {
            addCriterion("upper(cardName) like", value.toUpperCase(), "cardname");
            return (Criteria) this;
        }

        public Criteria andIsatmLikeInsensitive(String value) {
            addCriterion("upper(isAtm) like", value.toUpperCase(), "isatm");
            return (Criteria) this;
        }

        public Criteria andIsposLikeInsensitive(String value) {
            addCriterion("upper(isPos) like", value.toUpperCase(), "ispos");
            return (Criteria) this;
        }

        public Criteria andCardpreLikeInsensitive(String value) {
            addCriterion("upper(cardPre) like", value.toUpperCase(), "cardpre");
            return (Criteria) this;
        }

        public Criteria andCardprelenLikeInsensitive(String value) {
            addCriterion("upper(cardPreLen) like", value.toUpperCase(), "cardprelen");
            return (Criteria) this;
        }

        public Criteria andTypeLikeInsensitive(String value) {
            addCriterion("upper(type) like", value.toUpperCase(), "type");
            return (Criteria) this;
        }

        public Criteria andFindcodeLikeInsensitive(String value) {
            addCriterion("upper(findCode) like", value.toUpperCase(), "findcode");
            return (Criteria) this;
        }

        public Criteria andSortLikeInsensitive(String value) {
            addCriterion("upper(sort) like", value.toUpperCase(), "sort");
            return (Criteria) this;
        }

        public Criteria andSourceLikeInsensitive(String value) {
            addCriterion("upper(source) like", value.toUpperCase(), "source");
            return (Criteria) this;
        }

        public Criteria andImagesLikeInsensitive(String value) {
            addCriterion("upper(images) like", value.toUpperCase(), "images");
            return (Criteria) this;
        }

        public Criteria andSmalimagesLikeInsensitive(String value) {
            addCriterion("upper(smalImages) like", value.toUpperCase(), "smalimages");
            return (Criteria) this;
        }

        public Criteria andYftcodeLikeInsensitive(String value) {
            addCriterion("upper(yftcode) like", value.toUpperCase(), "yftcode");
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