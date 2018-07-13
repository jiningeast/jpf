package com.joiest.jpf.common.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayCloudCompactStaffExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected long pageNo;

    protected long pageSize;

    /**
     *
     */
    public PayCloudCompactStaffExample() {
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

        public Criteria andCompactNoIsNull() {
            addCriterion("compact_no is null");
            return (Criteria) this;
        }

        public Criteria andCompactNoIsNotNull() {
            addCriterion("compact_no is not null");
            return (Criteria) this;
        }

        public Criteria andCompactNoEqualTo(String value) {
            addCriterion("compact_no =", value, "compactNo");
            return (Criteria) this;
        }

        public Criteria andCompactNoNotEqualTo(String value) {
            addCriterion("compact_no <>", value, "compactNo");
            return (Criteria) this;
        }

        public Criteria andCompactNoGreaterThan(String value) {
            addCriterion("compact_no >", value, "compactNo");
            return (Criteria) this;
        }

        public Criteria andCompactNoGreaterThanOrEqualTo(String value) {
            addCriterion("compact_no >=", value, "compactNo");
            return (Criteria) this;
        }

        public Criteria andCompactNoLessThan(String value) {
            addCriterion("compact_no <", value, "compactNo");
            return (Criteria) this;
        }

        public Criteria andCompactNoLessThanOrEqualTo(String value) {
            addCriterion("compact_no <=", value, "compactNo");
            return (Criteria) this;
        }

        public Criteria andCompactNoLike(String value) {
            addCriterion("compact_no like", value, "compactNo");
            return (Criteria) this;
        }

        public Criteria andCompactNoNotLike(String value) {
            addCriterion("compact_no not like", value, "compactNo");
            return (Criteria) this;
        }

        public Criteria andCompactNoIn(List<String> values) {
            addCriterion("compact_no in", values, "compactNo");
            return (Criteria) this;
        }

        public Criteria andCompactNoNotIn(List<String> values) {
            addCriterion("compact_no not in", values, "compactNo");
            return (Criteria) this;
        }

        public Criteria andCompactNoBetween(String value1, String value2) {
            addCriterion("compact_no between", value1, value2, "compactNo");
            return (Criteria) this;
        }

        public Criteria andCompactNoNotBetween(String value1, String value2) {
            addCriterion("compact_no not between", value1, value2, "compactNo");
            return (Criteria) this;
        }

        public Criteria andStaffidIsNull() {
            addCriterion("staffid is null");
            return (Criteria) this;
        }

        public Criteria andStaffidIsNotNull() {
            addCriterion("staffid is not null");
            return (Criteria) this;
        }

        public Criteria andStaffidEqualTo(Long value) {
            addCriterion("staffid =", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidNotEqualTo(Long value) {
            addCriterion("staffid <>", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidGreaterThan(Long value) {
            addCriterion("staffid >", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidGreaterThanOrEqualTo(Long value) {
            addCriterion("staffid >=", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidLessThan(Long value) {
            addCriterion("staffid <", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidLessThanOrEqualTo(Long value) {
            addCriterion("staffid <=", value, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidIn(List<Long> values) {
            addCriterion("staffid in", values, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidNotIn(List<Long> values) {
            addCriterion("staffid not in", values, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidBetween(Long value1, Long value2) {
            addCriterion("staffid between", value1, value2, "staffid");
            return (Criteria) this;
        }

        public Criteria andStaffidNotBetween(Long value1, Long value2) {
            addCriterion("staffid not between", value1, value2, "staffid");
            return (Criteria) this;
        }

        public Criteria andDfidIsNull() {
            addCriterion("dfid is null");
            return (Criteria) this;
        }

        public Criteria andDfidIsNotNull() {
            addCriterion("dfid is not null");
            return (Criteria) this;
        }

        public Criteria andDfidEqualTo(Long value) {
            addCriterion("dfid =", value, "dfid");
            return (Criteria) this;
        }

        public Criteria andDfidNotEqualTo(Long value) {
            addCriterion("dfid <>", value, "dfid");
            return (Criteria) this;
        }

        public Criteria andDfidGreaterThan(Long value) {
            addCriterion("dfid >", value, "dfid");
            return (Criteria) this;
        }

        public Criteria andDfidGreaterThanOrEqualTo(Long value) {
            addCriterion("dfid >=", value, "dfid");
            return (Criteria) this;
        }

        public Criteria andDfidLessThan(Long value) {
            addCriterion("dfid <", value, "dfid");
            return (Criteria) this;
        }

        public Criteria andDfidLessThanOrEqualTo(Long value) {
            addCriterion("dfid <=", value, "dfid");
            return (Criteria) this;
        }

        public Criteria andDfidIn(List<Long> values) {
            addCriterion("dfid in", values, "dfid");
            return (Criteria) this;
        }

        public Criteria andDfidNotIn(List<Long> values) {
            addCriterion("dfid not in", values, "dfid");
            return (Criteria) this;
        }

        public Criteria andDfidBetween(Long value1, Long value2) {
            addCriterion("dfid between", value1, value2, "dfid");
            return (Criteria) this;
        }

        public Criteria andDfidNotBetween(Long value1, Long value2) {
            addCriterion("dfid not between", value1, value2, "dfid");
            return (Criteria) this;
        }

        public Criteria andPactnoIsNull() {
            addCriterion("pactno is null");
            return (Criteria) this;
        }

        public Criteria andPactnoIsNotNull() {
            addCriterion("pactno is not null");
            return (Criteria) this;
        }

        public Criteria andPactnoEqualTo(String value) {
            addCriterion("pactno =", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoNotEqualTo(String value) {
            addCriterion("pactno <>", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoGreaterThan(String value) {
            addCriterion("pactno >", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoGreaterThanOrEqualTo(String value) {
            addCriterion("pactno >=", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoLessThan(String value) {
            addCriterion("pactno <", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoLessThanOrEqualTo(String value) {
            addCriterion("pactno <=", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoLike(String value) {
            addCriterion("pactno like", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoNotLike(String value) {
            addCriterion("pactno not like", value, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoIn(List<String> values) {
            addCriterion("pactno in", values, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoNotIn(List<String> values) {
            addCriterion("pactno not in", values, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoBetween(String value1, String value2) {
            addCriterion("pactno between", value1, value2, "pactno");
            return (Criteria) this;
        }

        public Criteria andPactnoNotBetween(String value1, String value2) {
            addCriterion("pactno not between", value1, value2, "pactno");
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

        public Criteria andCompactActiveIsNull() {
            addCriterion("compact_active is null");
            return (Criteria) this;
        }

        public Criteria andCompactActiveIsNotNull() {
            addCriterion("compact_active is not null");
            return (Criteria) this;
        }

        public Criteria andCompactActiveEqualTo(Byte value) {
            addCriterion("compact_active =", value, "compactActive");
            return (Criteria) this;
        }

        public Criteria andCompactActiveNotEqualTo(Byte value) {
            addCriterion("compact_active <>", value, "compactActive");
            return (Criteria) this;
        }

        public Criteria andCompactActiveGreaterThan(Byte value) {
            addCriterion("compact_active >", value, "compactActive");
            return (Criteria) this;
        }

        public Criteria andCompactActiveGreaterThanOrEqualTo(Byte value) {
            addCriterion("compact_active >=", value, "compactActive");
            return (Criteria) this;
        }

        public Criteria andCompactActiveLessThan(Byte value) {
            addCriterion("compact_active <", value, "compactActive");
            return (Criteria) this;
        }

        public Criteria andCompactActiveLessThanOrEqualTo(Byte value) {
            addCriterion("compact_active <=", value, "compactActive");
            return (Criteria) this;
        }

        public Criteria andCompactActiveIn(List<Byte> values) {
            addCriterion("compact_active in", values, "compactActive");
            return (Criteria) this;
        }

        public Criteria andCompactActiveNotIn(List<Byte> values) {
            addCriterion("compact_active not in", values, "compactActive");
            return (Criteria) this;
        }

        public Criteria andCompactActiveBetween(Byte value1, Byte value2) {
            addCriterion("compact_active between", value1, value2, "compactActive");
            return (Criteria) this;
        }

        public Criteria andCompactActiveNotBetween(Byte value1, Byte value2) {
            addCriterion("compact_active not between", value1, value2, "compactActive");
            return (Criteria) this;
        }

        public Criteria andCompactidIsNull() {
            addCriterion("compactId is null");
            return (Criteria) this;
        }

        public Criteria andCompactidIsNotNull() {
            addCriterion("compactId is not null");
            return (Criteria) this;
        }

        public Criteria andCompactidEqualTo(Long value) {
            addCriterion("compactId =", value, "compactid");
            return (Criteria) this;
        }

        public Criteria andCompactidNotEqualTo(Long value) {
            addCriterion("compactId <>", value, "compactid");
            return (Criteria) this;
        }

        public Criteria andCompactidGreaterThan(Long value) {
            addCriterion("compactId >", value, "compactid");
            return (Criteria) this;
        }

        public Criteria andCompactidGreaterThanOrEqualTo(Long value) {
            addCriterion("compactId >=", value, "compactid");
            return (Criteria) this;
        }

        public Criteria andCompactidLessThan(Long value) {
            addCriterion("compactId <", value, "compactid");
            return (Criteria) this;
        }

        public Criteria andCompactidLessThanOrEqualTo(Long value) {
            addCriterion("compactId <=", value, "compactid");
            return (Criteria) this;
        }

        public Criteria andCompactidIn(List<Long> values) {
            addCriterion("compactId in", values, "compactid");
            return (Criteria) this;
        }

        public Criteria andCompactidNotIn(List<Long> values) {
            addCriterion("compactId not in", values, "compactid");
            return (Criteria) this;
        }

        public Criteria andCompactidBetween(Long value1, Long value2) {
            addCriterion("compactId between", value1, value2, "compactid");
            return (Criteria) this;
        }

        public Criteria andCompactidNotBetween(Long value1, Long value2) {
            addCriterion("compactId not between", value1, value2, "compactid");
            return (Criteria) this;
        }

        public Criteria andTicketidIsNull() {
            addCriterion("ticketId is null");
            return (Criteria) this;
        }

        public Criteria andTicketidIsNotNull() {
            addCriterion("ticketId is not null");
            return (Criteria) this;
        }

        public Criteria andTicketidEqualTo(Integer value) {
            addCriterion("ticketId =", value, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidNotEqualTo(Integer value) {
            addCriterion("ticketId <>", value, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidGreaterThan(Integer value) {
            addCriterion("ticketId >", value, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ticketId >=", value, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidLessThan(Integer value) {
            addCriterion("ticketId <", value, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidLessThanOrEqualTo(Integer value) {
            addCriterion("ticketId <=", value, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidIn(List<Integer> values) {
            addCriterion("ticketId in", values, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidNotIn(List<Integer> values) {
            addCriterion("ticketId not in", values, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidBetween(Integer value1, Integer value2) {
            addCriterion("ticketId between", value1, value2, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketidNotBetween(Integer value1, Integer value2) {
            addCriterion("ticketId not between", value1, value2, "ticketid");
            return (Criteria) this;
        }

        public Criteria andTicketcontentIsNull() {
            addCriterion("ticketContent is null");
            return (Criteria) this;
        }

        public Criteria andTicketcontentIsNotNull() {
            addCriterion("ticketContent is not null");
            return (Criteria) this;
        }

        public Criteria andTicketcontentEqualTo(String value) {
            addCriterion("ticketContent =", value, "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andTicketcontentNotEqualTo(String value) {
            addCriterion("ticketContent <>", value, "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andTicketcontentGreaterThan(String value) {
            addCriterion("ticketContent >", value, "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andTicketcontentGreaterThanOrEqualTo(String value) {
            addCriterion("ticketContent >=", value, "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andTicketcontentLessThan(String value) {
            addCriterion("ticketContent <", value, "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andTicketcontentLessThanOrEqualTo(String value) {
            addCriterion("ticketContent <=", value, "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andTicketcontentLike(String value) {
            addCriterion("ticketContent like", value, "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andTicketcontentNotLike(String value) {
            addCriterion("ticketContent not like", value, "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andTicketcontentIn(List<String> values) {
            addCriterion("ticketContent in", values, "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andTicketcontentNotIn(List<String> values) {
            addCriterion("ticketContent not in", values, "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andTicketcontentBetween(String value1, String value2) {
            addCriterion("ticketContent between", value1, value2, "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andTicketcontentNotBetween(String value1, String value2) {
            addCriterion("ticketContent not between", value1, value2, "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andEntryidIsNull() {
            addCriterion("entryId is null");
            return (Criteria) this;
        }

        public Criteria andEntryidIsNotNull() {
            addCriterion("entryId is not null");
            return (Criteria) this;
        }

        public Criteria andEntryidEqualTo(String value) {
            addCriterion("entryId =", value, "entryid");
            return (Criteria) this;
        }

        public Criteria andEntryidNotEqualTo(String value) {
            addCriterion("entryId <>", value, "entryid");
            return (Criteria) this;
        }

        public Criteria andEntryidGreaterThan(String value) {
            addCriterion("entryId >", value, "entryid");
            return (Criteria) this;
        }

        public Criteria andEntryidGreaterThanOrEqualTo(String value) {
            addCriterion("entryId >=", value, "entryid");
            return (Criteria) this;
        }

        public Criteria andEntryidLessThan(String value) {
            addCriterion("entryId <", value, "entryid");
            return (Criteria) this;
        }

        public Criteria andEntryidLessThanOrEqualTo(String value) {
            addCriterion("entryId <=", value, "entryid");
            return (Criteria) this;
        }

        public Criteria andEntryidLike(String value) {
            addCriterion("entryId like", value, "entryid");
            return (Criteria) this;
        }

        public Criteria andEntryidNotLike(String value) {
            addCriterion("entryId not like", value, "entryid");
            return (Criteria) this;
        }

        public Criteria andEntryidIn(List<String> values) {
            addCriterion("entryId in", values, "entryid");
            return (Criteria) this;
        }

        public Criteria andEntryidNotIn(List<String> values) {
            addCriterion("entryId not in", values, "entryid");
            return (Criteria) this;
        }

        public Criteria andEntryidBetween(String value1, String value2) {
            addCriterion("entryId between", value1, value2, "entryid");
            return (Criteria) this;
        }

        public Criteria andEntryidNotBetween(String value1, String value2) {
            addCriterion("entryId not between", value1, value2, "entryid");
            return (Criteria) this;
        }

        public Criteria andEntrynameIsNull() {
            addCriterion("entryName is null");
            return (Criteria) this;
        }

        public Criteria andEntrynameIsNotNull() {
            addCriterion("entryName is not null");
            return (Criteria) this;
        }

        public Criteria andEntrynameEqualTo(String value) {
            addCriterion("entryName =", value, "entryname");
            return (Criteria) this;
        }

        public Criteria andEntrynameNotEqualTo(String value) {
            addCriterion("entryName <>", value, "entryname");
            return (Criteria) this;
        }

        public Criteria andEntrynameGreaterThan(String value) {
            addCriterion("entryName >", value, "entryname");
            return (Criteria) this;
        }

        public Criteria andEntrynameGreaterThanOrEqualTo(String value) {
            addCriterion("entryName >=", value, "entryname");
            return (Criteria) this;
        }

        public Criteria andEntrynameLessThan(String value) {
            addCriterion("entryName <", value, "entryname");
            return (Criteria) this;
        }

        public Criteria andEntrynameLessThanOrEqualTo(String value) {
            addCriterion("entryName <=", value, "entryname");
            return (Criteria) this;
        }

        public Criteria andEntrynameLike(String value) {
            addCriterion("entryName like", value, "entryname");
            return (Criteria) this;
        }

        public Criteria andEntrynameNotLike(String value) {
            addCriterion("entryName not like", value, "entryname");
            return (Criteria) this;
        }

        public Criteria andEntrynameIn(List<String> values) {
            addCriterion("entryName in", values, "entryname");
            return (Criteria) this;
        }

        public Criteria andEntrynameNotIn(List<String> values) {
            addCriterion("entryName not in", values, "entryname");
            return (Criteria) this;
        }

        public Criteria andEntrynameBetween(String value1, String value2) {
            addCriterion("entryName between", value1, value2, "entryname");
            return (Criteria) this;
        }

        public Criteria andEntrynameNotBetween(String value1, String value2) {
            addCriterion("entryName not between", value1, value2, "entryname");
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

        public Criteria andCompactNoLikeInsensitive(String value) {
            addCriterion("upper(compact_no) like", value.toUpperCase(), "compactNo");
            return (Criteria) this;
        }

        public Criteria andPactnoLikeInsensitive(String value) {
            addCriterion("upper(pactno) like", value.toUpperCase(), "pactno");
            return (Criteria) this;
        }

        public Criteria andContentLikeInsensitive(String value) {
            addCriterion("upper(content) like", value.toUpperCase(), "content");
            return (Criteria) this;
        }

        public Criteria andTicketcontentLikeInsensitive(String value) {
            addCriterion("upper(ticketContent) like", value.toUpperCase(), "ticketcontent");
            return (Criteria) this;
        }

        public Criteria andEntryidLikeInsensitive(String value) {
            addCriterion("upper(entryId) like", value.toUpperCase(), "entryid");
            return (Criteria) this;
        }

        public Criteria andEntrynameLikeInsensitive(String value) {
            addCriterion("upper(entryName) like", value.toUpperCase(), "entryname");
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