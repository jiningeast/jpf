package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayOrderCpMessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayOrderCpMessageExample() {
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

        public Criteria andReturnTrannoIsNull() {
            addCriterion("return_tranNo is null");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoIsNotNull() {
            addCriterion("return_tranNo is not null");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoEqualTo(String value) {
            addCriterion("return_tranNo =", value, "returnTranno");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoNotEqualTo(String value) {
            addCriterion("return_tranNo <>", value, "returnTranno");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoGreaterThan(String value) {
            addCriterion("return_tranNo >", value, "returnTranno");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoGreaterThanOrEqualTo(String value) {
            addCriterion("return_tranNo >=", value, "returnTranno");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoLessThan(String value) {
            addCriterion("return_tranNo <", value, "returnTranno");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoLessThanOrEqualTo(String value) {
            addCriterion("return_tranNo <=", value, "returnTranno");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoLike(String value) {
            addCriterion("return_tranNo like", value, "returnTranno");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoNotLike(String value) {
            addCriterion("return_tranNo not like", value, "returnTranno");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoIn(List<String> values) {
            addCriterion("return_tranNo in", values, "returnTranno");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoNotIn(List<String> values) {
            addCriterion("return_tranNo not in", values, "returnTranno");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoBetween(String value1, String value2) {
            addCriterion("return_tranNo between", value1, value2, "returnTranno");
            return (Criteria) this;
        }

        public Criteria andReturnTrannoNotBetween(String value1, String value2) {
            addCriterion("return_tranNo not between", value1, value2, "returnTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoIsNull() {
            addCriterion("notify_tranNo is null");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoIsNotNull() {
            addCriterion("notify_tranNo is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoEqualTo(String value) {
            addCriterion("notify_tranNo =", value, "notifyTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoNotEqualTo(String value) {
            addCriterion("notify_tranNo <>", value, "notifyTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoGreaterThan(String value) {
            addCriterion("notify_tranNo >", value, "notifyTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoGreaterThanOrEqualTo(String value) {
            addCriterion("notify_tranNo >=", value, "notifyTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoLessThan(String value) {
            addCriterion("notify_tranNo <", value, "notifyTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoLessThanOrEqualTo(String value) {
            addCriterion("notify_tranNo <=", value, "notifyTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoLike(String value) {
            addCriterion("notify_tranNo like", value, "notifyTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoNotLike(String value) {
            addCriterion("notify_tranNo not like", value, "notifyTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoIn(List<String> values) {
            addCriterion("notify_tranNo in", values, "notifyTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoNotIn(List<String> values) {
            addCriterion("notify_tranNo not in", values, "notifyTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoBetween(String value1, String value2) {
            addCriterion("notify_tranNo between", value1, value2, "notifyTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoNotBetween(String value1, String value2) {
            addCriterion("notify_tranNo not between", value1, value2, "notifyTranno");
            return (Criteria) this;
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

        public Criteria andSignOrderidIsNull() {
            addCriterion("sign_orderid is null");
            return (Criteria) this;
        }

        public Criteria andSignOrderidIsNotNull() {
            addCriterion("sign_orderid is not null");
            return (Criteria) this;
        }

        public Criteria andSignOrderidEqualTo(String value) {
            addCriterion("sign_orderid =", value, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidNotEqualTo(String value) {
            addCriterion("sign_orderid <>", value, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidGreaterThan(String value) {
            addCriterion("sign_orderid >", value, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidGreaterThanOrEqualTo(String value) {
            addCriterion("sign_orderid >=", value, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidLessThan(String value) {
            addCriterion("sign_orderid <", value, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidLessThanOrEqualTo(String value) {
            addCriterion("sign_orderid <=", value, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidIn(List<String> values) {
            addCriterion("sign_orderid in", values, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidNotIn(List<String> values) {
            addCriterion("sign_orderid not in", values, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidBetween(String value1, String value2) {
            addCriterion("sign_orderid between", value1, value2, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andSignOrderidNotBetween(String value1, String value2) {
            addCriterion("sign_orderid not between", value1, value2, "signOrderid");
            return (Criteria) this;
        }

        public Criteria andRequestContentIsNull() {
            addCriterion("request_content is null");
            return (Criteria) this;
        }

        public Criteria andRequestContentIsNotNull() {
            addCriterion("request_content is not null");
            return (Criteria) this;
        }

        public Criteria andRequestContentEqualTo(String value) {
            addCriterion("request_content =", value, "requestContent");
            return (Criteria) this;
        }

        public Criteria andRequestContentNotEqualTo(String value) {
            addCriterion("request_content <>", value, "requestContent");
            return (Criteria) this;
        }

        public Criteria andRequestContentGreaterThan(String value) {
            addCriterion("request_content >", value, "requestContent");
            return (Criteria) this;
        }

        public Criteria andRequestContentGreaterThanOrEqualTo(String value) {
            addCriterion("request_content >=", value, "requestContent");
            return (Criteria) this;
        }

        public Criteria andRequestContentLessThan(String value) {
            addCriterion("request_content <", value, "requestContent");
            return (Criteria) this;
        }

        public Criteria andRequestContentLessThanOrEqualTo(String value) {
            addCriterion("request_content <=", value, "requestContent");
            return (Criteria) this;
        }

        public Criteria andRequestContentLike(String value) {
            addCriterion("request_content like", value, "requestContent");
            return (Criteria) this;
        }

        public Criteria andRequestContentNotLike(String value) {
            addCriterion("request_content not like", value, "requestContent");
            return (Criteria) this;
        }

        public Criteria andRequestContentIn(List<String> values) {
            addCriterion("request_content in", values, "requestContent");
            return (Criteria) this;
        }

        public Criteria andRequestContentNotIn(List<String> values) {
            addCriterion("request_content not in", values, "requestContent");
            return (Criteria) this;
        }

        public Criteria andRequestContentBetween(String value1, String value2) {
            addCriterion("request_content between", value1, value2, "requestContent");
            return (Criteria) this;
        }

        public Criteria andRequestContentNotBetween(String value1, String value2) {
            addCriterion("request_content not between", value1, value2, "requestContent");
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

        public Criteria andNotifyContentIsNull() {
            addCriterion("notify_content is null");
            return (Criteria) this;
        }

        public Criteria andNotifyContentIsNotNull() {
            addCriterion("notify_content is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyContentEqualTo(String value) {
            addCriterion("notify_content =", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentNotEqualTo(String value) {
            addCriterion("notify_content <>", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentGreaterThan(String value) {
            addCriterion("notify_content >", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentGreaterThanOrEqualTo(String value) {
            addCriterion("notify_content >=", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentLessThan(String value) {
            addCriterion("notify_content <", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentLessThanOrEqualTo(String value) {
            addCriterion("notify_content <=", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentLike(String value) {
            addCriterion("notify_content like", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentNotLike(String value) {
            addCriterion("notify_content not like", value, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentIn(List<String> values) {
            addCriterion("notify_content in", values, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentNotIn(List<String> values) {
            addCriterion("notify_content not in", values, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentBetween(String value1, String value2) {
            addCriterion("notify_content between", value1, value2, "notifyContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentNotBetween(String value1, String value2) {
            addCriterion("notify_content not between", value1, value2, "notifyContent");
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

        public Criteria andReturnTrannoLikeInsensitive(String value) {
            addCriterion("upper(return_tranNo) like", value.toUpperCase(), "returnTranno");
            return (Criteria) this;
        }

        public Criteria andNotifyTrannoLikeInsensitive(String value) {
            addCriterion("upper(notify_tranNo) like", value.toUpperCase(), "notifyTranno");
            return (Criteria) this;
        }

        public Criteria andRequestContentLikeInsensitive(String value) {
            addCriterion("upper(request_content) like", value.toUpperCase(), "requestContent");
            return (Criteria) this;
        }

        public Criteria andReturnContentLikeInsensitive(String value) {
            addCriterion("upper(return_content) like", value.toUpperCase(), "returnContent");
            return (Criteria) this;
        }

        public Criteria andNotifyContentLikeInsensitive(String value) {
            addCriterion("upper(notify_content) like", value.toUpperCase(), "notifyContent");
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