package com.joiest.jpf.common.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayOrderRefundExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayOrderRefundExample() {
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

        public Criteria andOrderidIsNull() {
            addCriterion("orderid is null");
            return (Criteria) this;
        }

        public Criteria andOrderidIsNotNull() {
            addCriterion("orderid is not null");
            return (Criteria) this;
        }

        public Criteria andOrderidEqualTo(String value) {
            addCriterion("orderid =", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotEqualTo(String value) {
            addCriterion("orderid <>", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThan(String value) {
            addCriterion("orderid >", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("orderid >=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThan(String value) {
            addCriterion("orderid <", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidLessThanOrEqualTo(String value) {
            addCriterion("orderid <=", value, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidIn(List<String> values) {
            addCriterion("orderid in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotIn(List<String> values) {
            addCriterion("orderid not in", values, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidBetween(String value1, String value2) {
            addCriterion("orderid between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andOrderidNotBetween(String value1, String value2) {
            addCriterion("orderid not between", value1, value2, "orderid");
            return (Criteria) this;
        }

        public Criteria andRefundOrderidIsNull() {
            addCriterion("refund_orderid is null");
            return (Criteria) this;
        }

        public Criteria andRefundOrderidIsNotNull() {
            addCriterion("refund_orderid is not null");
            return (Criteria) this;
        }

        public Criteria andRefundOrderidEqualTo(String value) {
            addCriterion("refund_orderid =", value, "refundOrderid");
            return (Criteria) this;
        }

        public Criteria andRefundOrderidNotEqualTo(String value) {
            addCriterion("refund_orderid <>", value, "refundOrderid");
            return (Criteria) this;
        }

        public Criteria andRefundOrderidGreaterThan(String value) {
            addCriterion("refund_orderid >", value, "refundOrderid");
            return (Criteria) this;
        }

        public Criteria andRefundOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("refund_orderid >=", value, "refundOrderid");
            return (Criteria) this;
        }

        public Criteria andRefundOrderidLessThan(String value) {
            addCriterion("refund_orderid <", value, "refundOrderid");
            return (Criteria) this;
        }

        public Criteria andRefundOrderidLessThanOrEqualTo(String value) {
            addCriterion("refund_orderid <=", value, "refundOrderid");
            return (Criteria) this;
        }

        public Criteria andRefundOrderidIn(List<String> values) {
            addCriterion("refund_orderid in", values, "refundOrderid");
            return (Criteria) this;
        }

        public Criteria andRefundOrderidNotIn(List<String> values) {
            addCriterion("refund_orderid not in", values, "refundOrderid");
            return (Criteria) this;
        }

        public Criteria andRefundOrderidBetween(String value1, String value2) {
            addCriterion("refund_orderid between", value1, value2, "refundOrderid");
            return (Criteria) this;
        }

        public Criteria andRefundOrderidNotBetween(String value1, String value2) {
            addCriterion("refund_orderid not between", value1, value2, "refundOrderid");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andBackurlIsNull() {
            addCriterion("backurl is null");
            return (Criteria) this;
        }

        public Criteria andBackurlIsNotNull() {
            addCriterion("backurl is not null");
            return (Criteria) this;
        }

        public Criteria andBackurlEqualTo(String value) {
            addCriterion("backurl =", value, "backurl");
            return (Criteria) this;
        }

        public Criteria andBackurlNotEqualTo(String value) {
            addCriterion("backurl <>", value, "backurl");
            return (Criteria) this;
        }

        public Criteria andBackurlGreaterThan(String value) {
            addCriterion("backurl >", value, "backurl");
            return (Criteria) this;
        }

        public Criteria andBackurlGreaterThanOrEqualTo(String value) {
            addCriterion("backurl >=", value, "backurl");
            return (Criteria) this;
        }

        public Criteria andBackurlLessThan(String value) {
            addCriterion("backurl <", value, "backurl");
            return (Criteria) this;
        }

        public Criteria andBackurlLessThanOrEqualTo(String value) {
            addCriterion("backurl <=", value, "backurl");
            return (Criteria) this;
        }

        public Criteria andBackurlLike(String value) {
            addCriterion("backurl like", value, "backurl");
            return (Criteria) this;
        }

        public Criteria andBackurlNotLike(String value) {
            addCriterion("backurl not like", value, "backurl");
            return (Criteria) this;
        }

        public Criteria andBackurlIn(List<String> values) {
            addCriterion("backurl in", values, "backurl");
            return (Criteria) this;
        }

        public Criteria andBackurlNotIn(List<String> values) {
            addCriterion("backurl not in", values, "backurl");
            return (Criteria) this;
        }

        public Criteria andBackurlBetween(String value1, String value2) {
            addCriterion("backurl between", value1, value2, "backurl");
            return (Criteria) this;
        }

        public Criteria andBackurlNotBetween(String value1, String value2) {
            addCriterion("backurl not between", value1, value2, "backurl");
            return (Criteria) this;
        }

        public Criteria andTrantypeIsNull() {
            addCriterion("tranType is null");
            return (Criteria) this;
        }

        public Criteria andTrantypeIsNotNull() {
            addCriterion("tranType is not null");
            return (Criteria) this;
        }

        public Criteria andTrantypeEqualTo(Byte value) {
            addCriterion("tranType =", value, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeNotEqualTo(Byte value) {
            addCriterion("tranType <>", value, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeGreaterThan(Byte value) {
            addCriterion("tranType >", value, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("tranType >=", value, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeLessThan(Byte value) {
            addCriterion("tranType <", value, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeLessThanOrEqualTo(Byte value) {
            addCriterion("tranType <=", value, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeIn(List<Byte> values) {
            addCriterion("tranType in", values, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeNotIn(List<Byte> values) {
            addCriterion("tranType not in", values, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeBetween(Byte value1, Byte value2) {
            addCriterion("tranType between", value1, value2, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrantypeNotBetween(Byte value1, Byte value2) {
            addCriterion("tranType not between", value1, value2, "trantype");
            return (Criteria) this;
        }

        public Criteria andTrannoIsNull() {
            addCriterion("tranNo is null");
            return (Criteria) this;
        }

        public Criteria andTrannoIsNotNull() {
            addCriterion("tranNo is not null");
            return (Criteria) this;
        }

        public Criteria andTrannoEqualTo(String value) {
            addCriterion("tranNo =", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotEqualTo(String value) {
            addCriterion("tranNo <>", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoGreaterThan(String value) {
            addCriterion("tranNo >", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoGreaterThanOrEqualTo(String value) {
            addCriterion("tranNo >=", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLessThan(String value) {
            addCriterion("tranNo <", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLessThanOrEqualTo(String value) {
            addCriterion("tranNo <=", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoLike(String value) {
            addCriterion("tranNo like", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotLike(String value) {
            addCriterion("tranNo not like", value, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoIn(List<String> values) {
            addCriterion("tranNo in", values, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotIn(List<String> values) {
            addCriterion("tranNo not in", values, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoBetween(String value1, String value2) {
            addCriterion("tranNo between", value1, value2, "tranno");
            return (Criteria) this;
        }

        public Criteria andTrannoNotBetween(String value1, String value2) {
            addCriterion("tranNo not between", value1, value2, "tranno");
            return (Criteria) this;
        }

        public Criteria andReturnContentIsNull() {
            addCriterion("return_content is null");
            return (Criteria) this;
        }

        public Criteria andReturnContentIsNotNull() {
            addCriterion("return_content is not null");
            return (Criteria) this;
        }

        public Criteria andReturnContentEqualTo(String value) {
            addCriterion("return_content =", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentNotEqualTo(String value) {
            addCriterion("return_content <>", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentGreaterThan(String value) {
            addCriterion("return_content >", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentGreaterThanOrEqualTo(String value) {
            addCriterion("return_content >=", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentLessThan(String value) {
            addCriterion("return_content <", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentLessThanOrEqualTo(String value) {
            addCriterion("return_content <=", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentLike(String value) {
            addCriterion("return_content like", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentNotLike(String value) {
            addCriterion("return_content not like", value, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentIn(List<String> values) {
            addCriterion("return_content in", values, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentNotIn(List<String> values) {
            addCriterion("return_content not in", values, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentBetween(String value1, String value2) {
            addCriterion("return_content between", value1, value2, "returnContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentNotBetween(String value1, String value2) {
            addCriterion("return_content not between", value1, value2, "returnContent");
            return (Criteria) this;
        }

        public Criteria andResponsParamIsNull() {
            addCriterion("respons_param is null");
            return (Criteria) this;
        }

        public Criteria andResponsParamIsNotNull() {
            addCriterion("respons_param is not null");
            return (Criteria) this;
        }

        public Criteria andResponsParamEqualTo(String value) {
            addCriterion("respons_param =", value, "responsParam");
            return (Criteria) this;
        }

        public Criteria andResponsParamNotEqualTo(String value) {
            addCriterion("respons_param <>", value, "responsParam");
            return (Criteria) this;
        }

        public Criteria andResponsParamGreaterThan(String value) {
            addCriterion("respons_param >", value, "responsParam");
            return (Criteria) this;
        }

        public Criteria andResponsParamGreaterThanOrEqualTo(String value) {
            addCriterion("respons_param >=", value, "responsParam");
            return (Criteria) this;
        }

        public Criteria andResponsParamLessThan(String value) {
            addCriterion("respons_param <", value, "responsParam");
            return (Criteria) this;
        }

        public Criteria andResponsParamLessThanOrEqualTo(String value) {
            addCriterion("respons_param <=", value, "responsParam");
            return (Criteria) this;
        }

        public Criteria andResponsParamLike(String value) {
            addCriterion("respons_param like", value, "responsParam");
            return (Criteria) this;
        }

        public Criteria andResponsParamNotLike(String value) {
            addCriterion("respons_param not like", value, "responsParam");
            return (Criteria) this;
        }

        public Criteria andResponsParamIn(List<String> values) {
            addCriterion("respons_param in", values, "responsParam");
            return (Criteria) this;
        }

        public Criteria andResponsParamNotIn(List<String> values) {
            addCriterion("respons_param not in", values, "responsParam");
            return (Criteria) this;
        }

        public Criteria andResponsParamBetween(String value1, String value2) {
            addCriterion("respons_param between", value1, value2, "responsParam");
            return (Criteria) this;
        }

        public Criteria andResponsParamNotBetween(String value1, String value2) {
            addCriterion("respons_param not between", value1, value2, "responsParam");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeIsNull() {
            addCriterion("notify_time is null");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeIsNotNull() {
            addCriterion("notify_time is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeEqualTo(Date value) {
            addCriterion("notify_time =", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeNotEqualTo(Date value) {
            addCriterion("notify_time <>", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeGreaterThan(Date value) {
            addCriterion("notify_time >", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("notify_time >=", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeLessThan(Date value) {
            addCriterion("notify_time <", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("notify_time <=", value, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeIn(List<Date> values) {
            addCriterion("notify_time in", values, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeNotIn(List<Date> values) {
            addCriterion("notify_time not in", values, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeBetween(Date value1, Date value2) {
            addCriterion("notify_time between", value1, value2, "notifyTime");
            return (Criteria) this;
        }

        public Criteria andNotifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("notify_time not between", value1, value2, "notifyTime");
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

        public Criteria andBackurlLikeInsensitive(String value) {
            addCriterion("upper(backurl) like", value.toUpperCase(), "backurl");
            return (Criteria) this;
        }

        public Criteria andTrannoLikeInsensitive(String value) {
            addCriterion("upper(tranNo) like", value.toUpperCase(), "tranno");
            return (Criteria) this;
        }

        public Criteria andReturnContentLikeInsensitive(String value) {
            addCriterion("upper(return_content) like", value.toUpperCase(), "returnContent");
            return (Criteria) this;
        }

        public Criteria andResponsParamLikeInsensitive(String value) {
            addCriterion("upper(respons_param) like", value.toUpperCase(), "responsParam");
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