package com.fast.base.data.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MMicropageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MMicropageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("Code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("Code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("Code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("Code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("Code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("Code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("Code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("Code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("Code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("Code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("Code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("Code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("Code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("Code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("Name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("Name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("Name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("Name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("Name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("Name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("Name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("Name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("Name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("Name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("Name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("Name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("Name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("Name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andHomeflagIsNull() {
            addCriterion("HomeFlag is null");
            return (Criteria) this;
        }

        public Criteria andHomeflagIsNotNull() {
            addCriterion("HomeFlag is not null");
            return (Criteria) this;
        }

        public Criteria andHomeflagEqualTo(Byte value) {
            addCriterion("HomeFlag =", value, "homeflag");
            return (Criteria) this;
        }

        public Criteria andHomeflagNotEqualTo(Byte value) {
            addCriterion("HomeFlag <>", value, "homeflag");
            return (Criteria) this;
        }

        public Criteria andHomeflagGreaterThan(Byte value) {
            addCriterion("HomeFlag >", value, "homeflag");
            return (Criteria) this;
        }

        public Criteria andHomeflagGreaterThanOrEqualTo(Byte value) {
            addCriterion("HomeFlag >=", value, "homeflag");
            return (Criteria) this;
        }

        public Criteria andHomeflagLessThan(Byte value) {
            addCriterion("HomeFlag <", value, "homeflag");
            return (Criteria) this;
        }

        public Criteria andHomeflagLessThanOrEqualTo(Byte value) {
            addCriterion("HomeFlag <=", value, "homeflag");
            return (Criteria) this;
        }

        public Criteria andHomeflagIn(List<Byte> values) {
            addCriterion("HomeFlag in", values, "homeflag");
            return (Criteria) this;
        }

        public Criteria andHomeflagNotIn(List<Byte> values) {
            addCriterion("HomeFlag not in", values, "homeflag");
            return (Criteria) this;
        }

        public Criteria andHomeflagBetween(Byte value1, Byte value2) {
            addCriterion("HomeFlag between", value1, value2, "homeflag");
            return (Criteria) this;
        }

        public Criteria andHomeflagNotBetween(Byte value1, Byte value2) {
            addCriterion("HomeFlag not between", value1, value2, "homeflag");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("Memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("Memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("Memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("Memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("Memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("Memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("Memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("Memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("Memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("Memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("Memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("Memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("Memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("Memo not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andUseflagIsNull() {
            addCriterion("UseFlag is null");
            return (Criteria) this;
        }

        public Criteria andUseflagIsNotNull() {
            addCriterion("UseFlag is not null");
            return (Criteria) this;
        }

        public Criteria andUseflagEqualTo(Byte value) {
            addCriterion("UseFlag =", value, "useflag");
            return (Criteria) this;
        }

        public Criteria andUseflagNotEqualTo(Byte value) {
            addCriterion("UseFlag <>", value, "useflag");
            return (Criteria) this;
        }

        public Criteria andUseflagGreaterThan(Byte value) {
            addCriterion("UseFlag >", value, "useflag");
            return (Criteria) this;
        }

        public Criteria andUseflagGreaterThanOrEqualTo(Byte value) {
            addCriterion("UseFlag >=", value, "useflag");
            return (Criteria) this;
        }

        public Criteria andUseflagLessThan(Byte value) {
            addCriterion("UseFlag <", value, "useflag");
            return (Criteria) this;
        }

        public Criteria andUseflagLessThanOrEqualTo(Byte value) {
            addCriterion("UseFlag <=", value, "useflag");
            return (Criteria) this;
        }

        public Criteria andUseflagIn(List<Byte> values) {
            addCriterion("UseFlag in", values, "useflag");
            return (Criteria) this;
        }

        public Criteria andUseflagNotIn(List<Byte> values) {
            addCriterion("UseFlag not in", values, "useflag");
            return (Criteria) this;
        }

        public Criteria andUseflagBetween(Byte value1, Byte value2) {
            addCriterion("UseFlag between", value1, value2, "useflag");
            return (Criteria) this;
        }

        public Criteria andUseflagNotBetween(Byte value1, Byte value2) {
            addCriterion("UseFlag not between", value1, value2, "useflag");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("CreateTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("CreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("CreateTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("CreateTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("CreateTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CreateTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("CreateTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("CreateTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("CreateTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("CreateTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("CreateTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("CreateTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("Creator is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("Creator is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("Creator =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("Creator <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("Creator >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("Creator >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("Creator <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("Creator <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("Creator like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("Creator not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("Creator in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("Creator not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("Creator between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("Creator not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andModifytimeIsNull() {
            addCriterion("ModifyTime is null");
            return (Criteria) this;
        }

        public Criteria andModifytimeIsNotNull() {
            addCriterion("ModifyTime is not null");
            return (Criteria) this;
        }

        public Criteria andModifytimeEqualTo(Date value) {
            addCriterion("ModifyTime =", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotEqualTo(Date value) {
            addCriterion("ModifyTime <>", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeGreaterThan(Date value) {
            addCriterion("ModifyTime >", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ModifyTime >=", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeLessThan(Date value) {
            addCriterion("ModifyTime <", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeLessThanOrEqualTo(Date value) {
            addCriterion("ModifyTime <=", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeIn(List<Date> values) {
            addCriterion("ModifyTime in", values, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotIn(List<Date> values) {
            addCriterion("ModifyTime not in", values, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeBetween(Date value1, Date value2) {
            addCriterion("ModifyTime between", value1, value2, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotBetween(Date value1, Date value2) {
            addCriterion("ModifyTime not between", value1, value2, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("Modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("Modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("Modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("Modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("Modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("Modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("Modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("Modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("Modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("Modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("Modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("Modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("Modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("Modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeIsNull() {
            addCriterion("UpdatedTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeIsNotNull() {
            addCriterion("UpdatedTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeEqualTo(Date value) {
            addCriterion("UpdatedTime =", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeNotEqualTo(Date value) {
            addCriterion("UpdatedTime <>", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeGreaterThan(Date value) {
            addCriterion("UpdatedTime >", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UpdatedTime >=", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeLessThan(Date value) {
            addCriterion("UpdatedTime <", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeLessThanOrEqualTo(Date value) {
            addCriterion("UpdatedTime <=", value, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeIn(List<Date> values) {
            addCriterion("UpdatedTime in", values, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeNotIn(List<Date> values) {
            addCriterion("UpdatedTime not in", values, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeBetween(Date value1, Date value2) {
            addCriterion("UpdatedTime between", value1, value2, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andUpdatedtimeNotBetween(Date value1, Date value2) {
            addCriterion("UpdatedTime not between", value1, value2, "updatedtime");
            return (Criteria) this;
        }

        public Criteria andPublishflagIsNull() {
            addCriterion("PublishFlag is null");
            return (Criteria) this;
        }

        public Criteria andPublishflagIsNotNull() {
            addCriterion("PublishFlag is not null");
            return (Criteria) this;
        }

        public Criteria andPublishflagEqualTo(Byte value) {
            addCriterion("PublishFlag =", value, "publishflag");
            return (Criteria) this;
        }

        public Criteria andPublishflagNotEqualTo(Byte value) {
            addCriterion("PublishFlag <>", value, "publishflag");
            return (Criteria) this;
        }

        public Criteria andPublishflagGreaterThan(Byte value) {
            addCriterion("PublishFlag >", value, "publishflag");
            return (Criteria) this;
        }

        public Criteria andPublishflagGreaterThanOrEqualTo(Byte value) {
            addCriterion("PublishFlag >=", value, "publishflag");
            return (Criteria) this;
        }

        public Criteria andPublishflagLessThan(Byte value) {
            addCriterion("PublishFlag <", value, "publishflag");
            return (Criteria) this;
        }

        public Criteria andPublishflagLessThanOrEqualTo(Byte value) {
            addCriterion("PublishFlag <=", value, "publishflag");
            return (Criteria) this;
        }

        public Criteria andPublishflagIn(List<Byte> values) {
            addCriterion("PublishFlag in", values, "publishflag");
            return (Criteria) this;
        }

        public Criteria andPublishflagNotIn(List<Byte> values) {
            addCriterion("PublishFlag not in", values, "publishflag");
            return (Criteria) this;
        }

        public Criteria andPublishflagBetween(Byte value1, Byte value2) {
            addCriterion("PublishFlag between", value1, value2, "publishflag");
            return (Criteria) this;
        }

        public Criteria andPublishflagNotBetween(Byte value1, Byte value2) {
            addCriterion("PublishFlag not between", value1, value2, "publishflag");
            return (Criteria) this;
        }

        public Criteria andPublisherIsNull() {
            addCriterion("Publisher is null");
            return (Criteria) this;
        }

        public Criteria andPublisherIsNotNull() {
            addCriterion("Publisher is not null");
            return (Criteria) this;
        }

        public Criteria andPublisherEqualTo(String value) {
            addCriterion("Publisher =", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotEqualTo(String value) {
            addCriterion("Publisher <>", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherGreaterThan(String value) {
            addCriterion("Publisher >", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherGreaterThanOrEqualTo(String value) {
            addCriterion("Publisher >=", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLessThan(String value) {
            addCriterion("Publisher <", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLessThanOrEqualTo(String value) {
            addCriterion("Publisher <=", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherLike(String value) {
            addCriterion("Publisher like", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotLike(String value) {
            addCriterion("Publisher not like", value, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherIn(List<String> values) {
            addCriterion("Publisher in", values, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotIn(List<String> values) {
            addCriterion("Publisher not in", values, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherBetween(String value1, String value2) {
            addCriterion("Publisher between", value1, value2, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublisherNotBetween(String value1, String value2) {
            addCriterion("Publisher not between", value1, value2, "publisher");
            return (Criteria) this;
        }

        public Criteria andPublishtimeIsNull() {
            addCriterion("PublishTime is null");
            return (Criteria) this;
        }

        public Criteria andPublishtimeIsNotNull() {
            addCriterion("PublishTime is not null");
            return (Criteria) this;
        }

        public Criteria andPublishtimeEqualTo(Date value) {
            addCriterion("PublishTime =", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeNotEqualTo(Date value) {
            addCriterion("PublishTime <>", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeGreaterThan(Date value) {
            addCriterion("PublishTime >", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PublishTime >=", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeLessThan(Date value) {
            addCriterion("PublishTime <", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeLessThanOrEqualTo(Date value) {
            addCriterion("PublishTime <=", value, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeIn(List<Date> values) {
            addCriterion("PublishTime in", values, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeNotIn(List<Date> values) {
            addCriterion("PublishTime not in", values, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeBetween(Date value1, Date value2) {
            addCriterion("PublishTime between", value1, value2, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPublishtimeNotBetween(Date value1, Date value2) {
            addCriterion("PublishTime not between", value1, value2, "publishtime");
            return (Criteria) this;
        }

        public Criteria andPageviewIsNull() {
            addCriterion("PageView is null");
            return (Criteria) this;
        }

        public Criteria andPageviewIsNotNull() {
            addCriterion("PageView is not null");
            return (Criteria) this;
        }

        public Criteria andPageviewEqualTo(Long value) {
            addCriterion("PageView =", value, "pageview");
            return (Criteria) this;
        }

        public Criteria andPageviewNotEqualTo(Long value) {
            addCriterion("PageView <>", value, "pageview");
            return (Criteria) this;
        }

        public Criteria andPageviewGreaterThan(Long value) {
            addCriterion("PageView >", value, "pageview");
            return (Criteria) this;
        }

        public Criteria andPageviewGreaterThanOrEqualTo(Long value) {
            addCriterion("PageView >=", value, "pageview");
            return (Criteria) this;
        }

        public Criteria andPageviewLessThan(Long value) {
            addCriterion("PageView <", value, "pageview");
            return (Criteria) this;
        }

        public Criteria andPageviewLessThanOrEqualTo(Long value) {
            addCriterion("PageView <=", value, "pageview");
            return (Criteria) this;
        }

        public Criteria andPageviewIn(List<Long> values) {
            addCriterion("PageView in", values, "pageview");
            return (Criteria) this;
        }

        public Criteria andPageviewNotIn(List<Long> values) {
            addCriterion("PageView not in", values, "pageview");
            return (Criteria) this;
        }

        public Criteria andPageviewBetween(Long value1, Long value2) {
            addCriterion("PageView between", value1, value2, "pageview");
            return (Criteria) this;
        }

        public Criteria andPageviewNotBetween(Long value1, Long value2) {
            addCriterion("PageView not between", value1, value2, "pageview");
            return (Criteria) this;
        }

        public Criteria andVipviewIsNull() {
            addCriterion("VipView is null");
            return (Criteria) this;
        }

        public Criteria andVipviewIsNotNull() {
            addCriterion("VipView is not null");
            return (Criteria) this;
        }

        public Criteria andVipviewEqualTo(Long value) {
            addCriterion("VipView =", value, "vipview");
            return (Criteria) this;
        }

        public Criteria andVipviewNotEqualTo(Long value) {
            addCriterion("VipView <>", value, "vipview");
            return (Criteria) this;
        }

        public Criteria andVipviewGreaterThan(Long value) {
            addCriterion("VipView >", value, "vipview");
            return (Criteria) this;
        }

        public Criteria andVipviewGreaterThanOrEqualTo(Long value) {
            addCriterion("VipView >=", value, "vipview");
            return (Criteria) this;
        }

        public Criteria andVipviewLessThan(Long value) {
            addCriterion("VipView <", value, "vipview");
            return (Criteria) this;
        }

        public Criteria andVipviewLessThanOrEqualTo(Long value) {
            addCriterion("VipView <=", value, "vipview");
            return (Criteria) this;
        }

        public Criteria andVipviewIn(List<Long> values) {
            addCriterion("VipView in", values, "vipview");
            return (Criteria) this;
        }

        public Criteria andVipviewNotIn(List<Long> values) {
            addCriterion("VipView not in", values, "vipview");
            return (Criteria) this;
        }

        public Criteria andVipviewBetween(Long value1, Long value2) {
            addCriterion("VipView between", value1, value2, "vipview");
            return (Criteria) this;
        }

        public Criteria andVipviewNotBetween(Long value1, Long value2) {
            addCriterion("VipView not between", value1, value2, "vipview");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidIsNull() {
            addCriterion("PublicPlatformID is null");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidIsNotNull() {
            addCriterion("PublicPlatformID is not null");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidEqualTo(Integer value) {
            addCriterion("PublicPlatformID =", value, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidNotEqualTo(Integer value) {
            addCriterion("PublicPlatformID <>", value, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidGreaterThan(Integer value) {
            addCriterion("PublicPlatformID >", value, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidGreaterThanOrEqualTo(Integer value) {
            addCriterion("PublicPlatformID >=", value, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidLessThan(Integer value) {
            addCriterion("PublicPlatformID <", value, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidLessThanOrEqualTo(Integer value) {
            addCriterion("PublicPlatformID <=", value, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidIn(List<Integer> values) {
            addCriterion("PublicPlatformID in", values, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidNotIn(List<Integer> values) {
            addCriterion("PublicPlatformID not in", values, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidBetween(Integer value1, Integer value2) {
            addCriterion("PublicPlatformID between", value1, value2, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidNotBetween(Integer value1, Integer value2) {
            addCriterion("PublicPlatformID not between", value1, value2, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidIsNull() {
            addCriterion("MiniProgramID is null");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidIsNotNull() {
            addCriterion("MiniProgramID is not null");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidEqualTo(Integer value) {
            addCriterion("MiniProgramID =", value, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidNotEqualTo(Integer value) {
            addCriterion("MiniProgramID <>", value, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidGreaterThan(Integer value) {
            addCriterion("MiniProgramID >", value, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidGreaterThanOrEqualTo(Integer value) {
            addCriterion("MiniProgramID >=", value, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidLessThan(Integer value) {
            addCriterion("MiniProgramID <", value, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidLessThanOrEqualTo(Integer value) {
            addCriterion("MiniProgramID <=", value, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidIn(List<Integer> values) {
            addCriterion("MiniProgramID in", values, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidNotIn(List<Integer> values) {
            addCriterion("MiniProgramID not in", values, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidBetween(Integer value1, Integer value2) {
            addCriterion("MiniProgramID between", value1, value2, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidNotBetween(Integer value1, Integer value2) {
            addCriterion("MiniProgramID not between", value1, value2, "miniprogramid");
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