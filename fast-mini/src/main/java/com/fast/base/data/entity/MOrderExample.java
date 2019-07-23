package com.fast.base.data.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MOrderExample() {
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

        public Criteria andNoIsNull() {
            addCriterion("No is null");
            return (Criteria) this;
        }

        public Criteria andNoIsNotNull() {
            addCriterion("No is not null");
            return (Criteria) this;
        }

        public Criteria andNoEqualTo(String value) {
            addCriterion("No =", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotEqualTo(String value) {
            addCriterion("No <>", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThan(String value) {
            addCriterion("No >", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoGreaterThanOrEqualTo(String value) {
            addCriterion("No >=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThan(String value) {
            addCriterion("No <", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLessThanOrEqualTo(String value) {
            addCriterion("No <=", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoLike(String value) {
            addCriterion("No like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotLike(String value) {
            addCriterion("No not like", value, "no");
            return (Criteria) this;
        }

        public Criteria andNoIn(List<String> values) {
            addCriterion("No in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotIn(List<String> values) {
            addCriterion("No not in", values, "no");
            return (Criteria) this;
        }

        public Criteria andNoBetween(String value1, String value2) {
            addCriterion("No between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andNoNotBetween(String value1, String value2) {
            addCriterion("No not between", value1, value2, "no");
            return (Criteria) this;
        }

        public Criteria andKindIsNull() {
            addCriterion("Kind is null");
            return (Criteria) this;
        }

        public Criteria andKindIsNotNull() {
            addCriterion("Kind is not null");
            return (Criteria) this;
        }

        public Criteria andKindEqualTo(Integer value) {
            addCriterion("Kind =", value, "kind");
            return (Criteria) this;
        }

        public Criteria andKindNotEqualTo(Integer value) {
            addCriterion("Kind <>", value, "kind");
            return (Criteria) this;
        }

        public Criteria andKindGreaterThan(Integer value) {
            addCriterion("Kind >", value, "kind");
            return (Criteria) this;
        }

        public Criteria andKindGreaterThanOrEqualTo(Integer value) {
            addCriterion("Kind >=", value, "kind");
            return (Criteria) this;
        }

        public Criteria andKindLessThan(Integer value) {
            addCriterion("Kind <", value, "kind");
            return (Criteria) this;
        }

        public Criteria andKindLessThanOrEqualTo(Integer value) {
            addCriterion("Kind <=", value, "kind");
            return (Criteria) this;
        }

        public Criteria andKindIn(List<Integer> values) {
            addCriterion("Kind in", values, "kind");
            return (Criteria) this;
        }

        public Criteria andKindNotIn(List<Integer> values) {
            addCriterion("Kind not in", values, "kind");
            return (Criteria) this;
        }

        public Criteria andKindBetween(Integer value1, Integer value2) {
            addCriterion("Kind between", value1, value2, "kind");
            return (Criteria) this;
        }

        public Criteria andKindNotBetween(Integer value1, Integer value2) {
            addCriterion("Kind not between", value1, value2, "kind");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("Source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("Source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Integer value) {
            addCriterion("Source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Integer value) {
            addCriterion("Source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Integer value) {
            addCriterion("Source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("Source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Integer value) {
            addCriterion("Source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Integer value) {
            addCriterion("Source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Integer> values) {
            addCriterion("Source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Integer> values) {
            addCriterion("Source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Integer value1, Integer value2) {
            addCriterion("Source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("Source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andVipidIsNull() {
            addCriterion("VipID is null");
            return (Criteria) this;
        }

        public Criteria andVipidIsNotNull() {
            addCriterion("VipID is not null");
            return (Criteria) this;
        }

        public Criteria andVipidEqualTo(Integer value) {
            addCriterion("VipID =", value, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidNotEqualTo(Integer value) {
            addCriterion("VipID <>", value, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidGreaterThan(Integer value) {
            addCriterion("VipID >", value, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidGreaterThanOrEqualTo(Integer value) {
            addCriterion("VipID >=", value, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidLessThan(Integer value) {
            addCriterion("VipID <", value, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidLessThanOrEqualTo(Integer value) {
            addCriterion("VipID <=", value, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidIn(List<Integer> values) {
            addCriterion("VipID in", values, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidNotIn(List<Integer> values) {
            addCriterion("VipID not in", values, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidBetween(Integer value1, Integer value2) {
            addCriterion("VipID between", value1, value2, "vipid");
            return (Criteria) this;
        }

        public Criteria andVipidNotBetween(Integer value1, Integer value2) {
            addCriterion("VipID not between", value1, value2, "vipid");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("Quantity is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("Quantity is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(Integer value) {
            addCriterion("Quantity =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(Integer value) {
            addCriterion("Quantity <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(Integer value) {
            addCriterion("Quantity >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("Quantity >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(Integer value) {
            addCriterion("Quantity <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("Quantity <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<Integer> values) {
            addCriterion("Quantity in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<Integer> values) {
            addCriterion("Quantity not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(Integer value1, Integer value2) {
            addCriterion("Quantity between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("Quantity not between", value1, value2, "quantity");
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

        public Criteria andBaseamountIsNull() {
            addCriterion("BaseAmount is null");
            return (Criteria) this;
        }

        public Criteria andBaseamountIsNotNull() {
            addCriterion("BaseAmount is not null");
            return (Criteria) this;
        }

        public Criteria andBaseamountEqualTo(BigDecimal value) {
            addCriterion("BaseAmount =", value, "baseamount");
            return (Criteria) this;
        }

        public Criteria andBaseamountNotEqualTo(BigDecimal value) {
            addCriterion("BaseAmount <>", value, "baseamount");
            return (Criteria) this;
        }

        public Criteria andBaseamountGreaterThan(BigDecimal value) {
            addCriterion("BaseAmount >", value, "baseamount");
            return (Criteria) this;
        }

        public Criteria andBaseamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BaseAmount >=", value, "baseamount");
            return (Criteria) this;
        }

        public Criteria andBaseamountLessThan(BigDecimal value) {
            addCriterion("BaseAmount <", value, "baseamount");
            return (Criteria) this;
        }

        public Criteria andBaseamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BaseAmount <=", value, "baseamount");
            return (Criteria) this;
        }

        public Criteria andBaseamountIn(List<BigDecimal> values) {
            addCriterion("BaseAmount in", values, "baseamount");
            return (Criteria) this;
        }

        public Criteria andBaseamountNotIn(List<BigDecimal> values) {
            addCriterion("BaseAmount not in", values, "baseamount");
            return (Criteria) this;
        }

        public Criteria andBaseamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BaseAmount between", value1, value2, "baseamount");
            return (Criteria) this;
        }

        public Criteria andBaseamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BaseAmount not between", value1, value2, "baseamount");
            return (Criteria) this;
        }

        public Criteria andSaleamountIsNull() {
            addCriterion("SaleAmount is null");
            return (Criteria) this;
        }

        public Criteria andSaleamountIsNotNull() {
            addCriterion("SaleAmount is not null");
            return (Criteria) this;
        }

        public Criteria andSaleamountEqualTo(BigDecimal value) {
            addCriterion("SaleAmount =", value, "saleamount");
            return (Criteria) this;
        }

        public Criteria andSaleamountNotEqualTo(BigDecimal value) {
            addCriterion("SaleAmount <>", value, "saleamount");
            return (Criteria) this;
        }

        public Criteria andSaleamountGreaterThan(BigDecimal value) {
            addCriterion("SaleAmount >", value, "saleamount");
            return (Criteria) this;
        }

        public Criteria andSaleamountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SaleAmount >=", value, "saleamount");
            return (Criteria) this;
        }

        public Criteria andSaleamountLessThan(BigDecimal value) {
            addCriterion("SaleAmount <", value, "saleamount");
            return (Criteria) this;
        }

        public Criteria andSaleamountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SaleAmount <=", value, "saleamount");
            return (Criteria) this;
        }

        public Criteria andSaleamountIn(List<BigDecimal> values) {
            addCriterion("SaleAmount in", values, "saleamount");
            return (Criteria) this;
        }

        public Criteria andSaleamountNotIn(List<BigDecimal> values) {
            addCriterion("SaleAmount not in", values, "saleamount");
            return (Criteria) this;
        }

        public Criteria andSaleamountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SaleAmount between", value1, value2, "saleamount");
            return (Criteria) this;
        }

        public Criteria andSaleamountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SaleAmount not between", value1, value2, "saleamount");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("Discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("Discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(BigDecimal value) {
            addCriterion("Discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(BigDecimal value) {
            addCriterion("Discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(BigDecimal value) {
            addCriterion("Discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(BigDecimal value) {
            addCriterion("Discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<BigDecimal> values) {
            addCriterion("Discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<BigDecimal> values) {
            addCriterion("Discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyIsNull() {
            addCriterion("DiscountMoney is null");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyIsNotNull() {
            addCriterion("DiscountMoney is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyEqualTo(BigDecimal value) {
            addCriterion("DiscountMoney =", value, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyNotEqualTo(BigDecimal value) {
            addCriterion("DiscountMoney <>", value, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyGreaterThan(BigDecimal value) {
            addCriterion("DiscountMoney >", value, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("DiscountMoney >=", value, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyLessThan(BigDecimal value) {
            addCriterion("DiscountMoney <", value, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("DiscountMoney <=", value, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyIn(List<BigDecimal> values) {
            addCriterion("DiscountMoney in", values, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyNotIn(List<BigDecimal> values) {
            addCriterion("DiscountMoney not in", values, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DiscountMoney between", value1, value2, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDiscountmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("DiscountMoney not between", value1, value2, "discountmoney");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("Deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("Deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(BigDecimal value) {
            addCriterion("Deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(BigDecimal value) {
            addCriterion("Deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(BigDecimal value) {
            addCriterion("Deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(BigDecimal value) {
            addCriterion("Deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<BigDecimal> values) {
            addCriterion("Deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<BigDecimal> values) {
            addCriterion("Deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Deposit not between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andPointIsNull() {
            addCriterion("Point is null");
            return (Criteria) this;
        }

        public Criteria andPointIsNotNull() {
            addCriterion("Point is not null");
            return (Criteria) this;
        }

        public Criteria andPointEqualTo(Integer value) {
            addCriterion("Point =", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotEqualTo(Integer value) {
            addCriterion("Point <>", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThan(Integer value) {
            addCriterion("Point >", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("Point >=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThan(Integer value) {
            addCriterion("Point <", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointLessThanOrEqualTo(Integer value) {
            addCriterion("Point <=", value, "point");
            return (Criteria) this;
        }

        public Criteria andPointIn(List<Integer> values) {
            addCriterion("Point in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotIn(List<Integer> values) {
            addCriterion("Point not in", values, "point");
            return (Criteria) this;
        }

        public Criteria andPointBetween(Integer value1, Integer value2) {
            addCriterion("Point between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andPointNotBetween(Integer value1, Integer value2) {
            addCriterion("Point not between", value1, value2, "point");
            return (Criteria) this;
        }

        public Criteria andPointrateIsNull() {
            addCriterion("PointRate is null");
            return (Criteria) this;
        }

        public Criteria andPointrateIsNotNull() {
            addCriterion("PointRate is not null");
            return (Criteria) this;
        }

        public Criteria andPointrateEqualTo(Integer value) {
            addCriterion("PointRate =", value, "pointrate");
            return (Criteria) this;
        }

        public Criteria andPointrateNotEqualTo(Integer value) {
            addCriterion("PointRate <>", value, "pointrate");
            return (Criteria) this;
        }

        public Criteria andPointrateGreaterThan(Integer value) {
            addCriterion("PointRate >", value, "pointrate");
            return (Criteria) this;
        }

        public Criteria andPointrateGreaterThanOrEqualTo(Integer value) {
            addCriterion("PointRate >=", value, "pointrate");
            return (Criteria) this;
        }

        public Criteria andPointrateLessThan(Integer value) {
            addCriterion("PointRate <", value, "pointrate");
            return (Criteria) this;
        }

        public Criteria andPointrateLessThanOrEqualTo(Integer value) {
            addCriterion("PointRate <=", value, "pointrate");
            return (Criteria) this;
        }

        public Criteria andPointrateIn(List<Integer> values) {
            addCriterion("PointRate in", values, "pointrate");
            return (Criteria) this;
        }

        public Criteria andPointrateNotIn(List<Integer> values) {
            addCriterion("PointRate not in", values, "pointrate");
            return (Criteria) this;
        }

        public Criteria andPointrateBetween(Integer value1, Integer value2) {
            addCriterion("PointRate between", value1, value2, "pointrate");
            return (Criteria) this;
        }

        public Criteria andPointrateNotBetween(Integer value1, Integer value2) {
            addCriterion("PointRate not between", value1, value2, "pointrate");
            return (Criteria) this;
        }

        public Criteria andPointmoneyIsNull() {
            addCriterion("PointMoney is null");
            return (Criteria) this;
        }

        public Criteria andPointmoneyIsNotNull() {
            addCriterion("PointMoney is not null");
            return (Criteria) this;
        }

        public Criteria andPointmoneyEqualTo(BigDecimal value) {
            addCriterion("PointMoney =", value, "pointmoney");
            return (Criteria) this;
        }

        public Criteria andPointmoneyNotEqualTo(BigDecimal value) {
            addCriterion("PointMoney <>", value, "pointmoney");
            return (Criteria) this;
        }

        public Criteria andPointmoneyGreaterThan(BigDecimal value) {
            addCriterion("PointMoney >", value, "pointmoney");
            return (Criteria) this;
        }

        public Criteria andPointmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PointMoney >=", value, "pointmoney");
            return (Criteria) this;
        }

        public Criteria andPointmoneyLessThan(BigDecimal value) {
            addCriterion("PointMoney <", value, "pointmoney");
            return (Criteria) this;
        }

        public Criteria andPointmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PointMoney <=", value, "pointmoney");
            return (Criteria) this;
        }

        public Criteria andPointmoneyIn(List<BigDecimal> values) {
            addCriterion("PointMoney in", values, "pointmoney");
            return (Criteria) this;
        }

        public Criteria andPointmoneyNotIn(List<BigDecimal> values) {
            addCriterion("PointMoney not in", values, "pointmoney");
            return (Criteria) this;
        }

        public Criteria andPointmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PointMoney between", value1, value2, "pointmoney");
            return (Criteria) this;
        }

        public Criteria andPointmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PointMoney not between", value1, value2, "pointmoney");
            return (Criteria) this;
        }

        public Criteria andCouponidIsNull() {
            addCriterion("CouponID is null");
            return (Criteria) this;
        }

        public Criteria andCouponidIsNotNull() {
            addCriterion("CouponID is not null");
            return (Criteria) this;
        }

        public Criteria andCouponidEqualTo(Integer value) {
            addCriterion("CouponID =", value, "couponid");
            return (Criteria) this;
        }

        public Criteria andCouponidNotEqualTo(Integer value) {
            addCriterion("CouponID <>", value, "couponid");
            return (Criteria) this;
        }

        public Criteria andCouponidGreaterThan(Integer value) {
            addCriterion("CouponID >", value, "couponid");
            return (Criteria) this;
        }

        public Criteria andCouponidGreaterThanOrEqualTo(Integer value) {
            addCriterion("CouponID >=", value, "couponid");
            return (Criteria) this;
        }

        public Criteria andCouponidLessThan(Integer value) {
            addCriterion("CouponID <", value, "couponid");
            return (Criteria) this;
        }

        public Criteria andCouponidLessThanOrEqualTo(Integer value) {
            addCriterion("CouponID <=", value, "couponid");
            return (Criteria) this;
        }

        public Criteria andCouponidIn(List<Integer> values) {
            addCriterion("CouponID in", values, "couponid");
            return (Criteria) this;
        }

        public Criteria andCouponidNotIn(List<Integer> values) {
            addCriterion("CouponID not in", values, "couponid");
            return (Criteria) this;
        }

        public Criteria andCouponidBetween(Integer value1, Integer value2) {
            addCriterion("CouponID between", value1, value2, "couponid");
            return (Criteria) this;
        }

        public Criteria andCouponidNotBetween(Integer value1, Integer value2) {
            addCriterion("CouponID not between", value1, value2, "couponid");
            return (Criteria) this;
        }

        public Criteria andCouponmoneyIsNull() {
            addCriterion("CouponMoney is null");
            return (Criteria) this;
        }

        public Criteria andCouponmoneyIsNotNull() {
            addCriterion("CouponMoney is not null");
            return (Criteria) this;
        }

        public Criteria andCouponmoneyEqualTo(BigDecimal value) {
            addCriterion("CouponMoney =", value, "couponmoney");
            return (Criteria) this;
        }

        public Criteria andCouponmoneyNotEqualTo(BigDecimal value) {
            addCriterion("CouponMoney <>", value, "couponmoney");
            return (Criteria) this;
        }

        public Criteria andCouponmoneyGreaterThan(BigDecimal value) {
            addCriterion("CouponMoney >", value, "couponmoney");
            return (Criteria) this;
        }

        public Criteria andCouponmoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CouponMoney >=", value, "couponmoney");
            return (Criteria) this;
        }

        public Criteria andCouponmoneyLessThan(BigDecimal value) {
            addCriterion("CouponMoney <", value, "couponmoney");
            return (Criteria) this;
        }

        public Criteria andCouponmoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CouponMoney <=", value, "couponmoney");
            return (Criteria) this;
        }

        public Criteria andCouponmoneyIn(List<BigDecimal> values) {
            addCriterion("CouponMoney in", values, "couponmoney");
            return (Criteria) this;
        }

        public Criteria andCouponmoneyNotIn(List<BigDecimal> values) {
            addCriterion("CouponMoney not in", values, "couponmoney");
            return (Criteria) this;
        }

        public Criteria andCouponmoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CouponMoney between", value1, value2, "couponmoney");
            return (Criteria) this;
        }

        public Criteria andCouponmoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CouponMoney not between", value1, value2, "couponmoney");
            return (Criteria) this;
        }

        public Criteria andPaymoneyIsNull() {
            addCriterion("PayMoney is null");
            return (Criteria) this;
        }

        public Criteria andPaymoneyIsNotNull() {
            addCriterion("PayMoney is not null");
            return (Criteria) this;
        }

        public Criteria andPaymoneyEqualTo(BigDecimal value) {
            addCriterion("PayMoney =", value, "paymoney");
            return (Criteria) this;
        }

        public Criteria andPaymoneyNotEqualTo(BigDecimal value) {
            addCriterion("PayMoney <>", value, "paymoney");
            return (Criteria) this;
        }

        public Criteria andPaymoneyGreaterThan(BigDecimal value) {
            addCriterion("PayMoney >", value, "paymoney");
            return (Criteria) this;
        }

        public Criteria andPaymoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PayMoney >=", value, "paymoney");
            return (Criteria) this;
        }

        public Criteria andPaymoneyLessThan(BigDecimal value) {
            addCriterion("PayMoney <", value, "paymoney");
            return (Criteria) this;
        }

        public Criteria andPaymoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PayMoney <=", value, "paymoney");
            return (Criteria) this;
        }

        public Criteria andPaymoneyIn(List<BigDecimal> values) {
            addCriterion("PayMoney in", values, "paymoney");
            return (Criteria) this;
        }

        public Criteria andPaymoneyNotIn(List<BigDecimal> values) {
            addCriterion("PayMoney not in", values, "paymoney");
            return (Criteria) this;
        }

        public Criteria andPaymoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PayMoney between", value1, value2, "paymoney");
            return (Criteria) this;
        }

        public Criteria andPaymoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PayMoney not between", value1, value2, "paymoney");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("Freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("Freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(BigDecimal value) {
            addCriterion("Freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(BigDecimal value) {
            addCriterion("Freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(BigDecimal value) {
            addCriterion("Freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(BigDecimal value) {
            addCriterion("Freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<BigDecimal> values) {
            addCriterion("Freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<BigDecimal> values) {
            addCriterion("Freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNull() {
            addCriterion("PayTime is null");
            return (Criteria) this;
        }

        public Criteria andPaytimeIsNotNull() {
            addCriterion("PayTime is not null");
            return (Criteria) this;
        }

        public Criteria andPaytimeEqualTo(Date value) {
            addCriterion("PayTime =", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotEqualTo(Date value) {
            addCriterion("PayTime <>", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThan(Date value) {
            addCriterion("PayTime >", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PayTime >=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThan(Date value) {
            addCriterion("PayTime <", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeLessThanOrEqualTo(Date value) {
            addCriterion("PayTime <=", value, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeIn(List<Date> values) {
            addCriterion("PayTime in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotIn(List<Date> values) {
            addCriterion("PayTime not in", values, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeBetween(Date value1, Date value2) {
            addCriterion("PayTime between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andPaytimeNotBetween(Date value1, Date value2) {
            addCriterion("PayTime not between", value1, value2, "paytime");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoIsNull() {
            addCriterion("WechatPayNo is null");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoIsNotNull() {
            addCriterion("WechatPayNo is not null");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoEqualTo(String value) {
            addCriterion("WechatPayNo =", value, "wechatpayno");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoNotEqualTo(String value) {
            addCriterion("WechatPayNo <>", value, "wechatpayno");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoGreaterThan(String value) {
            addCriterion("WechatPayNo >", value, "wechatpayno");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoGreaterThanOrEqualTo(String value) {
            addCriterion("WechatPayNo >=", value, "wechatpayno");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoLessThan(String value) {
            addCriterion("WechatPayNo <", value, "wechatpayno");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoLessThanOrEqualTo(String value) {
            addCriterion("WechatPayNo <=", value, "wechatpayno");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoLike(String value) {
            addCriterion("WechatPayNo like", value, "wechatpayno");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoNotLike(String value) {
            addCriterion("WechatPayNo not like", value, "wechatpayno");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoIn(List<String> values) {
            addCriterion("WechatPayNo in", values, "wechatpayno");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoNotIn(List<String> values) {
            addCriterion("WechatPayNo not in", values, "wechatpayno");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoBetween(String value1, String value2) {
            addCriterion("WechatPayNo between", value1, value2, "wechatpayno");
            return (Criteria) this;
        }

        public Criteria andWechatpaynoNotBetween(String value1, String value2) {
            addCriterion("WechatPayNo not between", value1, value2, "wechatpayno");
            return (Criteria) this;
        }

        public Criteria andPaystatusIsNull() {
            addCriterion("PayStatus is null");
            return (Criteria) this;
        }

        public Criteria andPaystatusIsNotNull() {
            addCriterion("PayStatus is not null");
            return (Criteria) this;
        }

        public Criteria andPaystatusEqualTo(Byte value) {
            addCriterion("PayStatus =", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusNotEqualTo(Byte value) {
            addCriterion("PayStatus <>", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusGreaterThan(Byte value) {
            addCriterion("PayStatus >", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("PayStatus >=", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusLessThan(Byte value) {
            addCriterion("PayStatus <", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusLessThanOrEqualTo(Byte value) {
            addCriterion("PayStatus <=", value, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusIn(List<Byte> values) {
            addCriterion("PayStatus in", values, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusNotIn(List<Byte> values) {
            addCriterion("PayStatus not in", values, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusBetween(Byte value1, Byte value2) {
            addCriterion("PayStatus between", value1, value2, "paystatus");
            return (Criteria) this;
        }

        public Criteria andPaystatusNotBetween(Byte value1, Byte value2) {
            addCriterion("PayStatus not between", value1, value2, "paystatus");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeIsNull() {
            addCriterion("DeliveryType is null");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeIsNotNull() {
            addCriterion("DeliveryType is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeEqualTo(Byte value) {
            addCriterion("DeliveryType =", value, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeNotEqualTo(Byte value) {
            addCriterion("DeliveryType <>", value, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeGreaterThan(Byte value) {
            addCriterion("DeliveryType >", value, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("DeliveryType >=", value, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeLessThan(Byte value) {
            addCriterion("DeliveryType <", value, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeLessThanOrEqualTo(Byte value) {
            addCriterion("DeliveryType <=", value, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeIn(List<Byte> values) {
            addCriterion("DeliveryType in", values, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeNotIn(List<Byte> values) {
            addCriterion("DeliveryType not in", values, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeBetween(Byte value1, Byte value2) {
            addCriterion("DeliveryType between", value1, value2, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andDeliverytypeNotBetween(Byte value1, Byte value2) {
            addCriterion("DeliveryType not between", value1, value2, "deliverytype");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("Receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("Receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("Receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("Receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("Receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("Receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("Receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("Receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("Receiver like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("Receiver not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("Receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("Receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("Receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("Receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneIsNull() {
            addCriterion("ReceiverPhone is null");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneIsNotNull() {
            addCriterion("ReceiverPhone is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneEqualTo(String value) {
            addCriterion("ReceiverPhone =", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneNotEqualTo(String value) {
            addCriterion("ReceiverPhone <>", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneGreaterThan(String value) {
            addCriterion("ReceiverPhone >", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneGreaterThanOrEqualTo(String value) {
            addCriterion("ReceiverPhone >=", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneLessThan(String value) {
            addCriterion("ReceiverPhone <", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneLessThanOrEqualTo(String value) {
            addCriterion("ReceiverPhone <=", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneLike(String value) {
            addCriterion("ReceiverPhone like", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneNotLike(String value) {
            addCriterion("ReceiverPhone not like", value, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneIn(List<String> values) {
            addCriterion("ReceiverPhone in", values, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneNotIn(List<String> values) {
            addCriterion("ReceiverPhone not in", values, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneBetween(String value1, String value2) {
            addCriterion("ReceiverPhone between", value1, value2, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverphoneNotBetween(String value1, String value2) {
            addCriterion("ReceiverPhone not between", value1, value2, "receiverphone");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceIsNull() {
            addCriterion("ReceiverProvince is null");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceIsNotNull() {
            addCriterion("ReceiverProvince is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceEqualTo(String value) {
            addCriterion("ReceiverProvince =", value, "receiverprovince");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceNotEqualTo(String value) {
            addCriterion("ReceiverProvince <>", value, "receiverprovince");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceGreaterThan(String value) {
            addCriterion("ReceiverProvince >", value, "receiverprovince");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceGreaterThanOrEqualTo(String value) {
            addCriterion("ReceiverProvince >=", value, "receiverprovince");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceLessThan(String value) {
            addCriterion("ReceiverProvince <", value, "receiverprovince");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceLessThanOrEqualTo(String value) {
            addCriterion("ReceiverProvince <=", value, "receiverprovince");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceLike(String value) {
            addCriterion("ReceiverProvince like", value, "receiverprovince");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceNotLike(String value) {
            addCriterion("ReceiverProvince not like", value, "receiverprovince");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceIn(List<String> values) {
            addCriterion("ReceiverProvince in", values, "receiverprovince");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceNotIn(List<String> values) {
            addCriterion("ReceiverProvince not in", values, "receiverprovince");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceBetween(String value1, String value2) {
            addCriterion("ReceiverProvince between", value1, value2, "receiverprovince");
            return (Criteria) this;
        }

        public Criteria andReceiverprovinceNotBetween(String value1, String value2) {
            addCriterion("ReceiverProvince not between", value1, value2, "receiverprovince");
            return (Criteria) this;
        }

        public Criteria andReceivercityIsNull() {
            addCriterion("ReceiverCity is null");
            return (Criteria) this;
        }

        public Criteria andReceivercityIsNotNull() {
            addCriterion("ReceiverCity is not null");
            return (Criteria) this;
        }

        public Criteria andReceivercityEqualTo(String value) {
            addCriterion("ReceiverCity =", value, "receivercity");
            return (Criteria) this;
        }

        public Criteria andReceivercityNotEqualTo(String value) {
            addCriterion("ReceiverCity <>", value, "receivercity");
            return (Criteria) this;
        }

        public Criteria andReceivercityGreaterThan(String value) {
            addCriterion("ReceiverCity >", value, "receivercity");
            return (Criteria) this;
        }

        public Criteria andReceivercityGreaterThanOrEqualTo(String value) {
            addCriterion("ReceiverCity >=", value, "receivercity");
            return (Criteria) this;
        }

        public Criteria andReceivercityLessThan(String value) {
            addCriterion("ReceiverCity <", value, "receivercity");
            return (Criteria) this;
        }

        public Criteria andReceivercityLessThanOrEqualTo(String value) {
            addCriterion("ReceiverCity <=", value, "receivercity");
            return (Criteria) this;
        }

        public Criteria andReceivercityLike(String value) {
            addCriterion("ReceiverCity like", value, "receivercity");
            return (Criteria) this;
        }

        public Criteria andReceivercityNotLike(String value) {
            addCriterion("ReceiverCity not like", value, "receivercity");
            return (Criteria) this;
        }

        public Criteria andReceivercityIn(List<String> values) {
            addCriterion("ReceiverCity in", values, "receivercity");
            return (Criteria) this;
        }

        public Criteria andReceivercityNotIn(List<String> values) {
            addCriterion("ReceiverCity not in", values, "receivercity");
            return (Criteria) this;
        }

        public Criteria andReceivercityBetween(String value1, String value2) {
            addCriterion("ReceiverCity between", value1, value2, "receivercity");
            return (Criteria) this;
        }

        public Criteria andReceivercityNotBetween(String value1, String value2) {
            addCriterion("ReceiverCity not between", value1, value2, "receivercity");
            return (Criteria) this;
        }

        public Criteria andReceivercountyIsNull() {
            addCriterion("ReceiverCounty is null");
            return (Criteria) this;
        }

        public Criteria andReceivercountyIsNotNull() {
            addCriterion("ReceiverCounty is not null");
            return (Criteria) this;
        }

        public Criteria andReceivercountyEqualTo(String value) {
            addCriterion("ReceiverCounty =", value, "receivercounty");
            return (Criteria) this;
        }

        public Criteria andReceivercountyNotEqualTo(String value) {
            addCriterion("ReceiverCounty <>", value, "receivercounty");
            return (Criteria) this;
        }

        public Criteria andReceivercountyGreaterThan(String value) {
            addCriterion("ReceiverCounty >", value, "receivercounty");
            return (Criteria) this;
        }

        public Criteria andReceivercountyGreaterThanOrEqualTo(String value) {
            addCriterion("ReceiverCounty >=", value, "receivercounty");
            return (Criteria) this;
        }

        public Criteria andReceivercountyLessThan(String value) {
            addCriterion("ReceiverCounty <", value, "receivercounty");
            return (Criteria) this;
        }

        public Criteria andReceivercountyLessThanOrEqualTo(String value) {
            addCriterion("ReceiverCounty <=", value, "receivercounty");
            return (Criteria) this;
        }

        public Criteria andReceivercountyLike(String value) {
            addCriterion("ReceiverCounty like", value, "receivercounty");
            return (Criteria) this;
        }

        public Criteria andReceivercountyNotLike(String value) {
            addCriterion("ReceiverCounty not like", value, "receivercounty");
            return (Criteria) this;
        }

        public Criteria andReceivercountyIn(List<String> values) {
            addCriterion("ReceiverCounty in", values, "receivercounty");
            return (Criteria) this;
        }

        public Criteria andReceivercountyNotIn(List<String> values) {
            addCriterion("ReceiverCounty not in", values, "receivercounty");
            return (Criteria) this;
        }

        public Criteria andReceivercountyBetween(String value1, String value2) {
            addCriterion("ReceiverCounty between", value1, value2, "receivercounty");
            return (Criteria) this;
        }

        public Criteria andReceivercountyNotBetween(String value1, String value2) {
            addCriterion("ReceiverCounty not between", value1, value2, "receivercounty");
            return (Criteria) this;
        }

        public Criteria andLogisticsidIsNull() {
            addCriterion("LogisticsID is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsidIsNotNull() {
            addCriterion("LogisticsID is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsidEqualTo(Integer value) {
            addCriterion("LogisticsID =", value, "logisticsid");
            return (Criteria) this;
        }

        public Criteria andLogisticsidNotEqualTo(Integer value) {
            addCriterion("LogisticsID <>", value, "logisticsid");
            return (Criteria) this;
        }

        public Criteria andLogisticsidGreaterThan(Integer value) {
            addCriterion("LogisticsID >", value, "logisticsid");
            return (Criteria) this;
        }

        public Criteria andLogisticsidGreaterThanOrEqualTo(Integer value) {
            addCriterion("LogisticsID >=", value, "logisticsid");
            return (Criteria) this;
        }

        public Criteria andLogisticsidLessThan(Integer value) {
            addCriterion("LogisticsID <", value, "logisticsid");
            return (Criteria) this;
        }

        public Criteria andLogisticsidLessThanOrEqualTo(Integer value) {
            addCriterion("LogisticsID <=", value, "logisticsid");
            return (Criteria) this;
        }

        public Criteria andLogisticsidIn(List<Integer> values) {
            addCriterion("LogisticsID in", values, "logisticsid");
            return (Criteria) this;
        }

        public Criteria andLogisticsidNotIn(List<Integer> values) {
            addCriterion("LogisticsID not in", values, "logisticsid");
            return (Criteria) this;
        }

        public Criteria andLogisticsidBetween(Integer value1, Integer value2) {
            addCriterion("LogisticsID between", value1, value2, "logisticsid");
            return (Criteria) this;
        }

        public Criteria andLogisticsidNotBetween(Integer value1, Integer value2) {
            addCriterion("LogisticsID not between", value1, value2, "logisticsid");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoIsNull() {
            addCriterion("LogisticsNo is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoIsNotNull() {
            addCriterion("LogisticsNo is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoEqualTo(String value) {
            addCriterion("LogisticsNo =", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoNotEqualTo(String value) {
            addCriterion("LogisticsNo <>", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoGreaterThan(String value) {
            addCriterion("LogisticsNo >", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoGreaterThanOrEqualTo(String value) {
            addCriterion("LogisticsNo >=", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoLessThan(String value) {
            addCriterion("LogisticsNo <", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoLessThanOrEqualTo(String value) {
            addCriterion("LogisticsNo <=", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoLike(String value) {
            addCriterion("LogisticsNo like", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoNotLike(String value) {
            addCriterion("LogisticsNo not like", value, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoIn(List<String> values) {
            addCriterion("LogisticsNo in", values, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoNotIn(List<String> values) {
            addCriterion("LogisticsNo not in", values, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoBetween(String value1, String value2) {
            addCriterion("LogisticsNo between", value1, value2, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andLogisticsnoNotBetween(String value1, String value2) {
            addCriterion("LogisticsNo not between", value1, value2, "logisticsno");
            return (Criteria) this;
        }

        public Criteria andDelivererdepartmentidIsNull() {
            addCriterion("DelivererDepartmentID is null");
            return (Criteria) this;
        }

        public Criteria andDelivererdepartmentidIsNotNull() {
            addCriterion("DelivererDepartmentID is not null");
            return (Criteria) this;
        }

        public Criteria andDelivererdepartmentidEqualTo(Integer value) {
            addCriterion("DelivererDepartmentID =", value, "delivererdepartmentid");
            return (Criteria) this;
        }

        public Criteria andDelivererdepartmentidNotEqualTo(Integer value) {
            addCriterion("DelivererDepartmentID <>", value, "delivererdepartmentid");
            return (Criteria) this;
        }

        public Criteria andDelivererdepartmentidGreaterThan(Integer value) {
            addCriterion("DelivererDepartmentID >", value, "delivererdepartmentid");
            return (Criteria) this;
        }

        public Criteria andDelivererdepartmentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("DelivererDepartmentID >=", value, "delivererdepartmentid");
            return (Criteria) this;
        }

        public Criteria andDelivererdepartmentidLessThan(Integer value) {
            addCriterion("DelivererDepartmentID <", value, "delivererdepartmentid");
            return (Criteria) this;
        }

        public Criteria andDelivererdepartmentidLessThanOrEqualTo(Integer value) {
            addCriterion("DelivererDepartmentID <=", value, "delivererdepartmentid");
            return (Criteria) this;
        }

        public Criteria andDelivererdepartmentidIn(List<Integer> values) {
            addCriterion("DelivererDepartmentID in", values, "delivererdepartmentid");
            return (Criteria) this;
        }

        public Criteria andDelivererdepartmentidNotIn(List<Integer> values) {
            addCriterion("DelivererDepartmentID not in", values, "delivererdepartmentid");
            return (Criteria) this;
        }

        public Criteria andDelivererdepartmentidBetween(Integer value1, Integer value2) {
            addCriterion("DelivererDepartmentID between", value1, value2, "delivererdepartmentid");
            return (Criteria) this;
        }

        public Criteria andDelivererdepartmentidNotBetween(Integer value1, Integer value2) {
            addCriterion("DelivererDepartmentID not between", value1, value2, "delivererdepartmentid");
            return (Criteria) this;
        }

        public Criteria andDelivererIsNull() {
            addCriterion("Deliverer is null");
            return (Criteria) this;
        }

        public Criteria andDelivererIsNotNull() {
            addCriterion("Deliverer is not null");
            return (Criteria) this;
        }

        public Criteria andDelivererEqualTo(String value) {
            addCriterion("Deliverer =", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererNotEqualTo(String value) {
            addCriterion("Deliverer <>", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererGreaterThan(String value) {
            addCriterion("Deliverer >", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererGreaterThanOrEqualTo(String value) {
            addCriterion("Deliverer >=", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererLessThan(String value) {
            addCriterion("Deliverer <", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererLessThanOrEqualTo(String value) {
            addCriterion("Deliverer <=", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererLike(String value) {
            addCriterion("Deliverer like", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererNotLike(String value) {
            addCriterion("Deliverer not like", value, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererIn(List<String> values) {
            addCriterion("Deliverer in", values, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererNotIn(List<String> values) {
            addCriterion("Deliverer not in", values, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererBetween(String value1, String value2) {
            addCriterion("Deliverer between", value1, value2, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDelivererNotBetween(String value1, String value2) {
            addCriterion("Deliverer not between", value1, value2, "deliverer");
            return (Criteria) this;
        }

        public Criteria andDeliverertimeIsNull() {
            addCriterion("DelivererTime is null");
            return (Criteria) this;
        }

        public Criteria andDeliverertimeIsNotNull() {
            addCriterion("DelivererTime is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverertimeEqualTo(Date value) {
            addCriterion("DelivererTime =", value, "deliverertime");
            return (Criteria) this;
        }

        public Criteria andDeliverertimeNotEqualTo(Date value) {
            addCriterion("DelivererTime <>", value, "deliverertime");
            return (Criteria) this;
        }

        public Criteria andDeliverertimeGreaterThan(Date value) {
            addCriterion("DelivererTime >", value, "deliverertime");
            return (Criteria) this;
        }

        public Criteria andDeliverertimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DelivererTime >=", value, "deliverertime");
            return (Criteria) this;
        }

        public Criteria andDeliverertimeLessThan(Date value) {
            addCriterion("DelivererTime <", value, "deliverertime");
            return (Criteria) this;
        }

        public Criteria andDeliverertimeLessThanOrEqualTo(Date value) {
            addCriterion("DelivererTime <=", value, "deliverertime");
            return (Criteria) this;
        }

        public Criteria andDeliverertimeIn(List<Date> values) {
            addCriterion("DelivererTime in", values, "deliverertime");
            return (Criteria) this;
        }

        public Criteria andDeliverertimeNotIn(List<Date> values) {
            addCriterion("DelivererTime not in", values, "deliverertime");
            return (Criteria) this;
        }

        public Criteria andDeliverertimeBetween(Date value1, Date value2) {
            addCriterion("DelivererTime between", value1, value2, "deliverertime");
            return (Criteria) this;
        }

        public Criteria andDeliverertimeNotBetween(Date value1, Date value2) {
            addCriterion("DelivererTime not between", value1, value2, "deliverertime");
            return (Criteria) this;
        }

        public Criteria andReceivertimeIsNull() {
            addCriterion("ReceiverTime is null");
            return (Criteria) this;
        }

        public Criteria andReceivertimeIsNotNull() {
            addCriterion("ReceiverTime is not null");
            return (Criteria) this;
        }

        public Criteria andReceivertimeEqualTo(Date value) {
            addCriterion("ReceiverTime =", value, "receivertime");
            return (Criteria) this;
        }

        public Criteria andReceivertimeNotEqualTo(Date value) {
            addCriterion("ReceiverTime <>", value, "receivertime");
            return (Criteria) this;
        }

        public Criteria andReceivertimeGreaterThan(Date value) {
            addCriterion("ReceiverTime >", value, "receivertime");
            return (Criteria) this;
        }

        public Criteria andReceivertimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ReceiverTime >=", value, "receivertime");
            return (Criteria) this;
        }

        public Criteria andReceivertimeLessThan(Date value) {
            addCriterion("ReceiverTime <", value, "receivertime");
            return (Criteria) this;
        }

        public Criteria andReceivertimeLessThanOrEqualTo(Date value) {
            addCriterion("ReceiverTime <=", value, "receivertime");
            return (Criteria) this;
        }

        public Criteria andReceivertimeIn(List<Date> values) {
            addCriterion("ReceiverTime in", values, "receivertime");
            return (Criteria) this;
        }

        public Criteria andReceivertimeNotIn(List<Date> values) {
            addCriterion("ReceiverTime not in", values, "receivertime");
            return (Criteria) this;
        }

        public Criteria andReceivertimeBetween(Date value1, Date value2) {
            addCriterion("ReceiverTime between", value1, value2, "receivertime");
            return (Criteria) this;
        }

        public Criteria andReceivertimeNotBetween(Date value1, Date value2) {
            addCriterion("ReceiverTime not between", value1, value2, "receivertime");
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

        public Criteria andRemarkIsNull() {
            addCriterion("Remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("Remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("Remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("Remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("Remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("Remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("Remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("Remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("Remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("Remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("Remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("Remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("Remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("Remark not between", value1, value2, "remark");
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

        public Criteria andStatusIsNull() {
            addCriterion("Status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("Status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("Status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("Status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("Status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("Status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("Status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("Status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("Status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("Status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("Status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("Status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidIsNull() {
            addCriterion("MiniprogramID is null");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidIsNotNull() {
            addCriterion("MiniprogramID is not null");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidEqualTo(Integer value) {
            addCriterion("MiniprogramID =", value, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidNotEqualTo(Integer value) {
            addCriterion("MiniprogramID <>", value, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidGreaterThan(Integer value) {
            addCriterion("MiniprogramID >", value, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidGreaterThanOrEqualTo(Integer value) {
            addCriterion("MiniprogramID >=", value, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidLessThan(Integer value) {
            addCriterion("MiniprogramID <", value, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidLessThanOrEqualTo(Integer value) {
            addCriterion("MiniprogramID <=", value, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidIn(List<Integer> values) {
            addCriterion("MiniprogramID in", values, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidNotIn(List<Integer> values) {
            addCriterion("MiniprogramID not in", values, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidBetween(Integer value1, Integer value2) {
            addCriterion("MiniprogramID between", value1, value2, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andMiniprogramidNotBetween(Integer value1, Integer value2) {
            addCriterion("MiniprogramID not between", value1, value2, "miniprogramid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidIsNull() {
            addCriterion("PublicplatformID is null");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidIsNotNull() {
            addCriterion("PublicplatformID is not null");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidEqualTo(Integer value) {
            addCriterion("PublicplatformID =", value, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidNotEqualTo(Integer value) {
            addCriterion("PublicplatformID <>", value, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidGreaterThan(Integer value) {
            addCriterion("PublicplatformID >", value, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidGreaterThanOrEqualTo(Integer value) {
            addCriterion("PublicplatformID >=", value, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidLessThan(Integer value) {
            addCriterion("PublicplatformID <", value, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidLessThanOrEqualTo(Integer value) {
            addCriterion("PublicplatformID <=", value, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidIn(List<Integer> values) {
            addCriterion("PublicplatformID in", values, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidNotIn(List<Integer> values) {
            addCriterion("PublicplatformID not in", values, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidBetween(Integer value1, Integer value2) {
            addCriterion("PublicplatformID between", value1, value2, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andPublicplatformidNotBetween(Integer value1, Integer value2) {
            addCriterion("PublicplatformID not between", value1, value2, "publicplatformid");
            return (Criteria) this;
        }

        public Criteria andRetuenpaystatusIsNull() {
            addCriterion("RetuenPayStatus is null");
            return (Criteria) this;
        }

        public Criteria andRetuenpaystatusIsNotNull() {
            addCriterion("RetuenPayStatus is not null");
            return (Criteria) this;
        }

        public Criteria andRetuenpaystatusEqualTo(Byte value) {
            addCriterion("RetuenPayStatus =", value, "retuenpaystatus");
            return (Criteria) this;
        }

        public Criteria andRetuenpaystatusNotEqualTo(Byte value) {
            addCriterion("RetuenPayStatus <>", value, "retuenpaystatus");
            return (Criteria) this;
        }

        public Criteria andRetuenpaystatusGreaterThan(Byte value) {
            addCriterion("RetuenPayStatus >", value, "retuenpaystatus");
            return (Criteria) this;
        }

        public Criteria andRetuenpaystatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("RetuenPayStatus >=", value, "retuenpaystatus");
            return (Criteria) this;
        }

        public Criteria andRetuenpaystatusLessThan(Byte value) {
            addCriterion("RetuenPayStatus <", value, "retuenpaystatus");
            return (Criteria) this;
        }

        public Criteria andRetuenpaystatusLessThanOrEqualTo(Byte value) {
            addCriterion("RetuenPayStatus <=", value, "retuenpaystatus");
            return (Criteria) this;
        }

        public Criteria andRetuenpaystatusIn(List<Byte> values) {
            addCriterion("RetuenPayStatus in", values, "retuenpaystatus");
            return (Criteria) this;
        }

        public Criteria andRetuenpaystatusNotIn(List<Byte> values) {
            addCriterion("RetuenPayStatus not in", values, "retuenpaystatus");
            return (Criteria) this;
        }

        public Criteria andRetuenpaystatusBetween(Byte value1, Byte value2) {
            addCriterion("RetuenPayStatus between", value1, value2, "retuenpaystatus");
            return (Criteria) this;
        }

        public Criteria andRetuenpaystatusNotBetween(Byte value1, Byte value2) {
            addCriterion("RetuenPayStatus not between", value1, value2, "retuenpaystatus");
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