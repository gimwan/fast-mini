package com.fast.base.data.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MCouponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MCouponExample() {
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

        public Criteria andAmountIsNull() {
            addCriterion("Amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("Amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(BigDecimal value) {
            addCriterion("Amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(BigDecimal value) {
            addCriterion("Amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(BigDecimal value) {
            addCriterion("Amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(BigDecimal value) {
            addCriterion("Amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<BigDecimal> values) {
            addCriterion("Amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<BigDecimal> values) {
            addCriterion("Amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andEnableamountIsNull() {
            addCriterion("EnableAmount is null");
            return (Criteria) this;
        }

        public Criteria andEnableamountIsNotNull() {
            addCriterion("EnableAmount is not null");
            return (Criteria) this;
        }

        public Criteria andEnableamountEqualTo(BigDecimal value) {
            addCriterion("EnableAmount =", value, "enableamount");
            return (Criteria) this;
        }

        public Criteria andEnableamountNotEqualTo(BigDecimal value) {
            addCriterion("EnableAmount <>", value, "enableamount");
            return (Criteria) this;
        }

        public Criteria andEnableamountGreaterThan(BigDecimal value) {
            addCriterion("EnableAmount >", value, "enableamount");
            return (Criteria) this;
        }

        public Criteria andEnableamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("EnableAmount >=", value, "enableamount");
            return (Criteria) this;
        }

        public Criteria andEnableamountLessThan(BigDecimal value) {
            addCriterion("EnableAmount <", value, "enableamount");
            return (Criteria) this;
        }

        public Criteria andEnableamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("EnableAmount <=", value, "enableamount");
            return (Criteria) this;
        }

        public Criteria andEnableamountIn(List<BigDecimal> values) {
            addCriterion("EnableAmount in", values, "enableamount");
            return (Criteria) this;
        }

        public Criteria andEnableamountNotIn(List<BigDecimal> values) {
            addCriterion("EnableAmount not in", values, "enableamount");
            return (Criteria) this;
        }

        public Criteria andEnableamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EnableAmount between", value1, value2, "enableamount");
            return (Criteria) this;
        }

        public Criteria andEnableamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("EnableAmount not between", value1, value2, "enableamount");
            return (Criteria) this;
        }

        public Criteria andLimitquantityIsNull() {
            addCriterion("LimitQuantity is null");
            return (Criteria) this;
        }

        public Criteria andLimitquantityIsNotNull() {
            addCriterion("LimitQuantity is not null");
            return (Criteria) this;
        }

        public Criteria andLimitquantityEqualTo(Integer value) {
            addCriterion("LimitQuantity =", value, "limitquantity");
            return (Criteria) this;
        }

        public Criteria andLimitquantityNotEqualTo(Integer value) {
            addCriterion("LimitQuantity <>", value, "limitquantity");
            return (Criteria) this;
        }

        public Criteria andLimitquantityGreaterThan(Integer value) {
            addCriterion("LimitQuantity >", value, "limitquantity");
            return (Criteria) this;
        }

        public Criteria andLimitquantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("LimitQuantity >=", value, "limitquantity");
            return (Criteria) this;
        }

        public Criteria andLimitquantityLessThan(Integer value) {
            addCriterion("LimitQuantity <", value, "limitquantity");
            return (Criteria) this;
        }

        public Criteria andLimitquantityLessThanOrEqualTo(Integer value) {
            addCriterion("LimitQuantity <=", value, "limitquantity");
            return (Criteria) this;
        }

        public Criteria andLimitquantityIn(List<Integer> values) {
            addCriterion("LimitQuantity in", values, "limitquantity");
            return (Criteria) this;
        }

        public Criteria andLimitquantityNotIn(List<Integer> values) {
            addCriterion("LimitQuantity not in", values, "limitquantity");
            return (Criteria) this;
        }

        public Criteria andLimitquantityBetween(Integer value1, Integer value2) {
            addCriterion("LimitQuantity between", value1, value2, "limitquantity");
            return (Criteria) this;
        }

        public Criteria andLimitquantityNotBetween(Integer value1, Integer value2) {
            addCriterion("LimitQuantity not between", value1, value2, "limitquantity");
            return (Criteria) this;
        }

        public Criteria andTotalquantityIsNull() {
            addCriterion("TotalQuantity is null");
            return (Criteria) this;
        }

        public Criteria andTotalquantityIsNotNull() {
            addCriterion("TotalQuantity is not null");
            return (Criteria) this;
        }

        public Criteria andTotalquantityEqualTo(Integer value) {
            addCriterion("TotalQuantity =", value, "totalquantity");
            return (Criteria) this;
        }

        public Criteria andTotalquantityNotEqualTo(Integer value) {
            addCriterion("TotalQuantity <>", value, "totalquantity");
            return (Criteria) this;
        }

        public Criteria andTotalquantityGreaterThan(Integer value) {
            addCriterion("TotalQuantity >", value, "totalquantity");
            return (Criteria) this;
        }

        public Criteria andTotalquantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("TotalQuantity >=", value, "totalquantity");
            return (Criteria) this;
        }

        public Criteria andTotalquantityLessThan(Integer value) {
            addCriterion("TotalQuantity <", value, "totalquantity");
            return (Criteria) this;
        }

        public Criteria andTotalquantityLessThanOrEqualTo(Integer value) {
            addCriterion("TotalQuantity <=", value, "totalquantity");
            return (Criteria) this;
        }

        public Criteria andTotalquantityIn(List<Integer> values) {
            addCriterion("TotalQuantity in", values, "totalquantity");
            return (Criteria) this;
        }

        public Criteria andTotalquantityNotIn(List<Integer> values) {
            addCriterion("TotalQuantity not in", values, "totalquantity");
            return (Criteria) this;
        }

        public Criteria andTotalquantityBetween(Integer value1, Integer value2) {
            addCriterion("TotalQuantity between", value1, value2, "totalquantity");
            return (Criteria) this;
        }

        public Criteria andTotalquantityNotBetween(Integer value1, Integer value2) {
            addCriterion("TotalQuantity not between", value1, value2, "totalquantity");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNull() {
            addCriterion("BeginTime is null");
            return (Criteria) this;
        }

        public Criteria andBegintimeIsNotNull() {
            addCriterion("BeginTime is not null");
            return (Criteria) this;
        }

        public Criteria andBegintimeEqualTo(Date value) {
            addCriterion("BeginTime =", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotEqualTo(Date value) {
            addCriterion("BeginTime <>", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThan(Date value) {
            addCriterion("BeginTime >", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeGreaterThanOrEqualTo(Date value) {
            addCriterion("BeginTime >=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThan(Date value) {
            addCriterion("BeginTime <", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeLessThanOrEqualTo(Date value) {
            addCriterion("BeginTime <=", value, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeIn(List<Date> values) {
            addCriterion("BeginTime in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotIn(List<Date> values) {
            addCriterion("BeginTime not in", values, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeBetween(Date value1, Date value2) {
            addCriterion("BeginTime between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andBegintimeNotBetween(Date value1, Date value2) {
            addCriterion("BeginTime not between", value1, value2, "begintime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("EndTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("EndTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("EndTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("EndTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("EndTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("EndTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("EndTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("EndTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("EndTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("EndTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("EndTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("EndTime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andColorIsNull() {
            addCriterion("Color is null");
            return (Criteria) this;
        }

        public Criteria andColorIsNotNull() {
            addCriterion("Color is not null");
            return (Criteria) this;
        }

        public Criteria andColorEqualTo(String value) {
            addCriterion("Color =", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotEqualTo(String value) {
            addCriterion("Color <>", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThan(String value) {
            addCriterion("Color >", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorGreaterThanOrEqualTo(String value) {
            addCriterion("Color >=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThan(String value) {
            addCriterion("Color <", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLessThanOrEqualTo(String value) {
            addCriterion("Color <=", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorLike(String value) {
            addCriterion("Color like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotLike(String value) {
            addCriterion("Color not like", value, "color");
            return (Criteria) this;
        }

        public Criteria andColorIn(List<String> values) {
            addCriterion("Color in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotIn(List<String> values) {
            addCriterion("Color not in", values, "color");
            return (Criteria) this;
        }

        public Criteria andColorBetween(String value1, String value2) {
            addCriterion("Color between", value1, value2, "color");
            return (Criteria) this;
        }

        public Criteria andColorNotBetween(String value1, String value2) {
            addCriterion("Color not between", value1, value2, "color");
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

        public Criteria andSuittypeIsNull() {
            addCriterion("SuitType is null");
            return (Criteria) this;
        }

        public Criteria andSuittypeIsNotNull() {
            addCriterion("SuitType is not null");
            return (Criteria) this;
        }

        public Criteria andSuittypeEqualTo(Byte value) {
            addCriterion("SuitType =", value, "suittype");
            return (Criteria) this;
        }

        public Criteria andSuittypeNotEqualTo(Byte value) {
            addCriterion("SuitType <>", value, "suittype");
            return (Criteria) this;
        }

        public Criteria andSuittypeGreaterThan(Byte value) {
            addCriterion("SuitType >", value, "suittype");
            return (Criteria) this;
        }

        public Criteria andSuittypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("SuitType >=", value, "suittype");
            return (Criteria) this;
        }

        public Criteria andSuittypeLessThan(Byte value) {
            addCriterion("SuitType <", value, "suittype");
            return (Criteria) this;
        }

        public Criteria andSuittypeLessThanOrEqualTo(Byte value) {
            addCriterion("SuitType <=", value, "suittype");
            return (Criteria) this;
        }

        public Criteria andSuittypeIn(List<Byte> values) {
            addCriterion("SuitType in", values, "suittype");
            return (Criteria) this;
        }

        public Criteria andSuittypeNotIn(List<Byte> values) {
            addCriterion("SuitType not in", values, "suittype");
            return (Criteria) this;
        }

        public Criteria andSuittypeBetween(Byte value1, Byte value2) {
            addCriterion("SuitType between", value1, value2, "suittype");
            return (Criteria) this;
        }

        public Criteria andSuittypeNotBetween(Byte value1, Byte value2) {
            addCriterion("SuitType not between", value1, value2, "suittype");
            return (Criteria) this;
        }

        public Criteria andEffectivetimeIsNull() {
            addCriterion("EffectiveTime is null");
            return (Criteria) this;
        }

        public Criteria andEffectivetimeIsNotNull() {
            addCriterion("EffectiveTime is not null");
            return (Criteria) this;
        }

        public Criteria andEffectivetimeEqualTo(Integer value) {
            addCriterion("EffectiveTime =", value, "effectivetime");
            return (Criteria) this;
        }

        public Criteria andEffectivetimeNotEqualTo(Integer value) {
            addCriterion("EffectiveTime <>", value, "effectivetime");
            return (Criteria) this;
        }

        public Criteria andEffectivetimeGreaterThan(Integer value) {
            addCriterion("EffectiveTime >", value, "effectivetime");
            return (Criteria) this;
        }

        public Criteria andEffectivetimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("EffectiveTime >=", value, "effectivetime");
            return (Criteria) this;
        }

        public Criteria andEffectivetimeLessThan(Integer value) {
            addCriterion("EffectiveTime <", value, "effectivetime");
            return (Criteria) this;
        }

        public Criteria andEffectivetimeLessThanOrEqualTo(Integer value) {
            addCriterion("EffectiveTime <=", value, "effectivetime");
            return (Criteria) this;
        }

        public Criteria andEffectivetimeIn(List<Integer> values) {
            addCriterion("EffectiveTime in", values, "effectivetime");
            return (Criteria) this;
        }

        public Criteria andEffectivetimeNotIn(List<Integer> values) {
            addCriterion("EffectiveTime not in", values, "effectivetime");
            return (Criteria) this;
        }

        public Criteria andEffectivetimeBetween(Integer value1, Integer value2) {
            addCriterion("EffectiveTime between", value1, value2, "effectivetime");
            return (Criteria) this;
        }

        public Criteria andEffectivetimeNotBetween(Integer value1, Integer value2) {
            addCriterion("EffectiveTime not between", value1, value2, "effectivetime");
            return (Criteria) this;
        }

        public Criteria andExtidIsNull() {
            addCriterion("ExtID is null");
            return (Criteria) this;
        }

        public Criteria andExtidIsNotNull() {
            addCriterion("ExtID is not null");
            return (Criteria) this;
        }

        public Criteria andExtidEqualTo(String value) {
            addCriterion("ExtID =", value, "extid");
            return (Criteria) this;
        }

        public Criteria andExtidNotEqualTo(String value) {
            addCriterion("ExtID <>", value, "extid");
            return (Criteria) this;
        }

        public Criteria andExtidGreaterThan(String value) {
            addCriterion("ExtID >", value, "extid");
            return (Criteria) this;
        }

        public Criteria andExtidGreaterThanOrEqualTo(String value) {
            addCriterion("ExtID >=", value, "extid");
            return (Criteria) this;
        }

        public Criteria andExtidLessThan(String value) {
            addCriterion("ExtID <", value, "extid");
            return (Criteria) this;
        }

        public Criteria andExtidLessThanOrEqualTo(String value) {
            addCriterion("ExtID <=", value, "extid");
            return (Criteria) this;
        }

        public Criteria andExtidLike(String value) {
            addCriterion("ExtID like", value, "extid");
            return (Criteria) this;
        }

        public Criteria andExtidNotLike(String value) {
            addCriterion("ExtID not like", value, "extid");
            return (Criteria) this;
        }

        public Criteria andExtidIn(List<String> values) {
            addCriterion("ExtID in", values, "extid");
            return (Criteria) this;
        }

        public Criteria andExtidNotIn(List<String> values) {
            addCriterion("ExtID not in", values, "extid");
            return (Criteria) this;
        }

        public Criteria andExtidBetween(String value1, String value2) {
            addCriterion("ExtID between", value1, value2, "extid");
            return (Criteria) this;
        }

        public Criteria andExtidNotBetween(String value1, String value2) {
            addCriterion("ExtID not between", value1, value2, "extid");
            return (Criteria) this;
        }

        public Criteria andSuitgoodstypeIsNull() {
            addCriterion("SuitGoodsType is null");
            return (Criteria) this;
        }

        public Criteria andSuitgoodstypeIsNotNull() {
            addCriterion("SuitGoodsType is not null");
            return (Criteria) this;
        }

        public Criteria andSuitgoodstypeEqualTo(Byte value) {
            addCriterion("SuitGoodsType =", value, "suitgoodstype");
            return (Criteria) this;
        }

        public Criteria andSuitgoodstypeNotEqualTo(Byte value) {
            addCriterion("SuitGoodsType <>", value, "suitgoodstype");
            return (Criteria) this;
        }

        public Criteria andSuitgoodstypeGreaterThan(Byte value) {
            addCriterion("SuitGoodsType >", value, "suitgoodstype");
            return (Criteria) this;
        }

        public Criteria andSuitgoodstypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("SuitGoodsType >=", value, "suitgoodstype");
            return (Criteria) this;
        }

        public Criteria andSuitgoodstypeLessThan(Byte value) {
            addCriterion("SuitGoodsType <", value, "suitgoodstype");
            return (Criteria) this;
        }

        public Criteria andSuitgoodstypeLessThanOrEqualTo(Byte value) {
            addCriterion("SuitGoodsType <=", value, "suitgoodstype");
            return (Criteria) this;
        }

        public Criteria andSuitgoodstypeIn(List<Byte> values) {
            addCriterion("SuitGoodsType in", values, "suitgoodstype");
            return (Criteria) this;
        }

        public Criteria andSuitgoodstypeNotIn(List<Byte> values) {
            addCriterion("SuitGoodsType not in", values, "suitgoodstype");
            return (Criteria) this;
        }

        public Criteria andSuitgoodstypeBetween(Byte value1, Byte value2) {
            addCriterion("SuitGoodsType between", value1, value2, "suitgoodstype");
            return (Criteria) this;
        }

        public Criteria andSuitgoodstypeNotBetween(Byte value1, Byte value2) {
            addCriterion("SuitGoodsType not between", value1, value2, "suitgoodstype");
            return (Criteria) this;
        }

        public Criteria andSuitdepartmenttypeIsNull() {
            addCriterion("SuitDepartmentType is null");
            return (Criteria) this;
        }

        public Criteria andSuitdepartmenttypeIsNotNull() {
            addCriterion("SuitDepartmentType is not null");
            return (Criteria) this;
        }

        public Criteria andSuitdepartmenttypeEqualTo(Byte value) {
            addCriterion("SuitDepartmentType =", value, "suitdepartmenttype");
            return (Criteria) this;
        }

        public Criteria andSuitdepartmenttypeNotEqualTo(Byte value) {
            addCriterion("SuitDepartmentType <>", value, "suitdepartmenttype");
            return (Criteria) this;
        }

        public Criteria andSuitdepartmenttypeGreaterThan(Byte value) {
            addCriterion("SuitDepartmentType >", value, "suitdepartmenttype");
            return (Criteria) this;
        }

        public Criteria andSuitdepartmenttypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("SuitDepartmentType >=", value, "suitdepartmenttype");
            return (Criteria) this;
        }

        public Criteria andSuitdepartmenttypeLessThan(Byte value) {
            addCriterion("SuitDepartmentType <", value, "suitdepartmenttype");
            return (Criteria) this;
        }

        public Criteria andSuitdepartmenttypeLessThanOrEqualTo(Byte value) {
            addCriterion("SuitDepartmentType <=", value, "suitdepartmenttype");
            return (Criteria) this;
        }

        public Criteria andSuitdepartmenttypeIn(List<Byte> values) {
            addCriterion("SuitDepartmentType in", values, "suitdepartmenttype");
            return (Criteria) this;
        }

        public Criteria andSuitdepartmenttypeNotIn(List<Byte> values) {
            addCriterion("SuitDepartmentType not in", values, "suitdepartmenttype");
            return (Criteria) this;
        }

        public Criteria andSuitdepartmenttypeBetween(Byte value1, Byte value2) {
            addCriterion("SuitDepartmentType between", value1, value2, "suitdepartmenttype");
            return (Criteria) this;
        }

        public Criteria andSuitdepartmenttypeNotBetween(Byte value1, Byte value2) {
            addCriterion("SuitDepartmentType not between", value1, value2, "suitdepartmenttype");
            return (Criteria) this;
        }

        public Criteria andTimetypeIsNull() {
            addCriterion("TimeType is null");
            return (Criteria) this;
        }

        public Criteria andTimetypeIsNotNull() {
            addCriterion("TimeType is not null");
            return (Criteria) this;
        }

        public Criteria andTimetypeEqualTo(Byte value) {
            addCriterion("TimeType =", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeNotEqualTo(Byte value) {
            addCriterion("TimeType <>", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeGreaterThan(Byte value) {
            addCriterion("TimeType >", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("TimeType >=", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeLessThan(Byte value) {
            addCriterion("TimeType <", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeLessThanOrEqualTo(Byte value) {
            addCriterion("TimeType <=", value, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeIn(List<Byte> values) {
            addCriterion("TimeType in", values, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeNotIn(List<Byte> values) {
            addCriterion("TimeType not in", values, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeBetween(Byte value1, Byte value2) {
            addCriterion("TimeType between", value1, value2, "timetype");
            return (Criteria) this;
        }

        public Criteria andTimetypeNotBetween(Byte value1, Byte value2) {
            addCriterion("TimeType not between", value1, value2, "timetype");
            return (Criteria) this;
        }

        public Criteria andHintIsNull() {
            addCriterion("Hint is null");
            return (Criteria) this;
        }

        public Criteria andHintIsNotNull() {
            addCriterion("Hint is not null");
            return (Criteria) this;
        }

        public Criteria andHintEqualTo(String value) {
            addCriterion("Hint =", value, "hint");
            return (Criteria) this;
        }

        public Criteria andHintNotEqualTo(String value) {
            addCriterion("Hint <>", value, "hint");
            return (Criteria) this;
        }

        public Criteria andHintGreaterThan(String value) {
            addCriterion("Hint >", value, "hint");
            return (Criteria) this;
        }

        public Criteria andHintGreaterThanOrEqualTo(String value) {
            addCriterion("Hint >=", value, "hint");
            return (Criteria) this;
        }

        public Criteria andHintLessThan(String value) {
            addCriterion("Hint <", value, "hint");
            return (Criteria) this;
        }

        public Criteria andHintLessThanOrEqualTo(String value) {
            addCriterion("Hint <=", value, "hint");
            return (Criteria) this;
        }

        public Criteria andHintLike(String value) {
            addCriterion("Hint like", value, "hint");
            return (Criteria) this;
        }

        public Criteria andHintNotLike(String value) {
            addCriterion("Hint not like", value, "hint");
            return (Criteria) this;
        }

        public Criteria andHintIn(List<String> values) {
            addCriterion("Hint in", values, "hint");
            return (Criteria) this;
        }

        public Criteria andHintNotIn(List<String> values) {
            addCriterion("Hint not in", values, "hint");
            return (Criteria) this;
        }

        public Criteria andHintBetween(String value1, String value2) {
            addCriterion("Hint between", value1, value2, "hint");
            return (Criteria) this;
        }

        public Criteria andHintNotBetween(String value1, String value2) {
            addCriterion("Hint not between", value1, value2, "hint");
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