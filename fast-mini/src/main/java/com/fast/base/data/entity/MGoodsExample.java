package com.fast.base.data.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MGoodsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MGoodsExample() {
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

        public Criteria andBrandidIsNull() {
            addCriterion("BrandID is null");
            return (Criteria) this;
        }

        public Criteria andBrandidIsNotNull() {
            addCriterion("BrandID is not null");
            return (Criteria) this;
        }

        public Criteria andBrandidEqualTo(Integer value) {
            addCriterion("BrandID =", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidNotEqualTo(Integer value) {
            addCriterion("BrandID <>", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidGreaterThan(Integer value) {
            addCriterion("BrandID >", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidGreaterThanOrEqualTo(Integer value) {
            addCriterion("BrandID >=", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidLessThan(Integer value) {
            addCriterion("BrandID <", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidLessThanOrEqualTo(Integer value) {
            addCriterion("BrandID <=", value, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidIn(List<Integer> values) {
            addCriterion("BrandID in", values, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidNotIn(List<Integer> values) {
            addCriterion("BrandID not in", values, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidBetween(Integer value1, Integer value2) {
            addCriterion("BrandID between", value1, value2, "brandid");
            return (Criteria) this;
        }

        public Criteria andBrandidNotBetween(Integer value1, Integer value2) {
            addCriterion("BrandID not between", value1, value2, "brandid");
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

        public Criteria andKindEqualTo(Byte value) {
            addCriterion("Kind =", value, "kind");
            return (Criteria) this;
        }

        public Criteria andKindNotEqualTo(Byte value) {
            addCriterion("Kind <>", value, "kind");
            return (Criteria) this;
        }

        public Criteria andKindGreaterThan(Byte value) {
            addCriterion("Kind >", value, "kind");
            return (Criteria) this;
        }

        public Criteria andKindGreaterThanOrEqualTo(Byte value) {
            addCriterion("Kind >=", value, "kind");
            return (Criteria) this;
        }

        public Criteria andKindLessThan(Byte value) {
            addCriterion("Kind <", value, "kind");
            return (Criteria) this;
        }

        public Criteria andKindLessThanOrEqualTo(Byte value) {
            addCriterion("Kind <=", value, "kind");
            return (Criteria) this;
        }

        public Criteria andKindIn(List<Byte> values) {
            addCriterion("Kind in", values, "kind");
            return (Criteria) this;
        }

        public Criteria andKindNotIn(List<Byte> values) {
            addCriterion("Kind not in", values, "kind");
            return (Criteria) this;
        }

        public Criteria andKindBetween(Byte value1, Byte value2) {
            addCriterion("Kind between", value1, value2, "kind");
            return (Criteria) this;
        }

        public Criteria andKindNotBetween(Byte value1, Byte value2) {
            addCriterion("Kind not between", value1, value2, "kind");
            return (Criteria) this;
        }

        public Criteria andPurchasepriceIsNull() {
            addCriterion("PurchasePrice is null");
            return (Criteria) this;
        }

        public Criteria andPurchasepriceIsNotNull() {
            addCriterion("PurchasePrice is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasepriceEqualTo(BigDecimal value) {
            addCriterion("PurchasePrice =", value, "purchaseprice");
            return (Criteria) this;
        }

        public Criteria andPurchasepriceNotEqualTo(BigDecimal value) {
            addCriterion("PurchasePrice <>", value, "purchaseprice");
            return (Criteria) this;
        }

        public Criteria andPurchasepriceGreaterThan(BigDecimal value) {
            addCriterion("PurchasePrice >", value, "purchaseprice");
            return (Criteria) this;
        }

        public Criteria andPurchasepriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PurchasePrice >=", value, "purchaseprice");
            return (Criteria) this;
        }

        public Criteria andPurchasepriceLessThan(BigDecimal value) {
            addCriterion("PurchasePrice <", value, "purchaseprice");
            return (Criteria) this;
        }

        public Criteria andPurchasepriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PurchasePrice <=", value, "purchaseprice");
            return (Criteria) this;
        }

        public Criteria andPurchasepriceIn(List<BigDecimal> values) {
            addCriterion("PurchasePrice in", values, "purchaseprice");
            return (Criteria) this;
        }

        public Criteria andPurchasepriceNotIn(List<BigDecimal> values) {
            addCriterion("PurchasePrice not in", values, "purchaseprice");
            return (Criteria) this;
        }

        public Criteria andPurchasepriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PurchasePrice between", value1, value2, "purchaseprice");
            return (Criteria) this;
        }

        public Criteria andPurchasepriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PurchasePrice not between", value1, value2, "purchaseprice");
            return (Criteria) this;
        }

        public Criteria andBasepriceIsNull() {
            addCriterion("BasePrice is null");
            return (Criteria) this;
        }

        public Criteria andBasepriceIsNotNull() {
            addCriterion("BasePrice is not null");
            return (Criteria) this;
        }

        public Criteria andBasepriceEqualTo(BigDecimal value) {
            addCriterion("BasePrice =", value, "baseprice");
            return (Criteria) this;
        }

        public Criteria andBasepriceNotEqualTo(BigDecimal value) {
            addCriterion("BasePrice <>", value, "baseprice");
            return (Criteria) this;
        }

        public Criteria andBasepriceGreaterThan(BigDecimal value) {
            addCriterion("BasePrice >", value, "baseprice");
            return (Criteria) this;
        }

        public Criteria andBasepriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("BasePrice >=", value, "baseprice");
            return (Criteria) this;
        }

        public Criteria andBasepriceLessThan(BigDecimal value) {
            addCriterion("BasePrice <", value, "baseprice");
            return (Criteria) this;
        }

        public Criteria andBasepriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("BasePrice <=", value, "baseprice");
            return (Criteria) this;
        }

        public Criteria andBasepriceIn(List<BigDecimal> values) {
            addCriterion("BasePrice in", values, "baseprice");
            return (Criteria) this;
        }

        public Criteria andBasepriceNotIn(List<BigDecimal> values) {
            addCriterion("BasePrice not in", values, "baseprice");
            return (Criteria) this;
        }

        public Criteria andBasepriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BasePrice between", value1, value2, "baseprice");
            return (Criteria) this;
        }

        public Criteria andBasepriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("BasePrice not between", value1, value2, "baseprice");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("Price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("Price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("Price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("Price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("Price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("Price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("Price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("Price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andShowcolorIsNull() {
            addCriterion("ShowColor is null");
            return (Criteria) this;
        }

        public Criteria andShowcolorIsNotNull() {
            addCriterion("ShowColor is not null");
            return (Criteria) this;
        }

        public Criteria andShowcolorEqualTo(Byte value) {
            addCriterion("ShowColor =", value, "showcolor");
            return (Criteria) this;
        }

        public Criteria andShowcolorNotEqualTo(Byte value) {
            addCriterion("ShowColor <>", value, "showcolor");
            return (Criteria) this;
        }

        public Criteria andShowcolorGreaterThan(Byte value) {
            addCriterion("ShowColor >", value, "showcolor");
            return (Criteria) this;
        }

        public Criteria andShowcolorGreaterThanOrEqualTo(Byte value) {
            addCriterion("ShowColor >=", value, "showcolor");
            return (Criteria) this;
        }

        public Criteria andShowcolorLessThan(Byte value) {
            addCriterion("ShowColor <", value, "showcolor");
            return (Criteria) this;
        }

        public Criteria andShowcolorLessThanOrEqualTo(Byte value) {
            addCriterion("ShowColor <=", value, "showcolor");
            return (Criteria) this;
        }

        public Criteria andShowcolorIn(List<Byte> values) {
            addCriterion("ShowColor in", values, "showcolor");
            return (Criteria) this;
        }

        public Criteria andShowcolorNotIn(List<Byte> values) {
            addCriterion("ShowColor not in", values, "showcolor");
            return (Criteria) this;
        }

        public Criteria andShowcolorBetween(Byte value1, Byte value2) {
            addCriterion("ShowColor between", value1, value2, "showcolor");
            return (Criteria) this;
        }

        public Criteria andShowcolorNotBetween(Byte value1, Byte value2) {
            addCriterion("ShowColor not between", value1, value2, "showcolor");
            return (Criteria) this;
        }

        public Criteria andShowpatternIsNull() {
            addCriterion("ShowPattern is null");
            return (Criteria) this;
        }

        public Criteria andShowpatternIsNotNull() {
            addCriterion("ShowPattern is not null");
            return (Criteria) this;
        }

        public Criteria andShowpatternEqualTo(Byte value) {
            addCriterion("ShowPattern =", value, "showpattern");
            return (Criteria) this;
        }

        public Criteria andShowpatternNotEqualTo(Byte value) {
            addCriterion("ShowPattern <>", value, "showpattern");
            return (Criteria) this;
        }

        public Criteria andShowpatternGreaterThan(Byte value) {
            addCriterion("ShowPattern >", value, "showpattern");
            return (Criteria) this;
        }

        public Criteria andShowpatternGreaterThanOrEqualTo(Byte value) {
            addCriterion("ShowPattern >=", value, "showpattern");
            return (Criteria) this;
        }

        public Criteria andShowpatternLessThan(Byte value) {
            addCriterion("ShowPattern <", value, "showpattern");
            return (Criteria) this;
        }

        public Criteria andShowpatternLessThanOrEqualTo(Byte value) {
            addCriterion("ShowPattern <=", value, "showpattern");
            return (Criteria) this;
        }

        public Criteria andShowpatternIn(List<Byte> values) {
            addCriterion("ShowPattern in", values, "showpattern");
            return (Criteria) this;
        }

        public Criteria andShowpatternNotIn(List<Byte> values) {
            addCriterion("ShowPattern not in", values, "showpattern");
            return (Criteria) this;
        }

        public Criteria andShowpatternBetween(Byte value1, Byte value2) {
            addCriterion("ShowPattern between", value1, value2, "showpattern");
            return (Criteria) this;
        }

        public Criteria andShowpatternNotBetween(Byte value1, Byte value2) {
            addCriterion("ShowPattern not between", value1, value2, "showpattern");
            return (Criteria) this;
        }

        public Criteria andShowsizeIsNull() {
            addCriterion("ShowSize is null");
            return (Criteria) this;
        }

        public Criteria andShowsizeIsNotNull() {
            addCriterion("ShowSize is not null");
            return (Criteria) this;
        }

        public Criteria andShowsizeEqualTo(Byte value) {
            addCriterion("ShowSize =", value, "showsize");
            return (Criteria) this;
        }

        public Criteria andShowsizeNotEqualTo(Byte value) {
            addCriterion("ShowSize <>", value, "showsize");
            return (Criteria) this;
        }

        public Criteria andShowsizeGreaterThan(Byte value) {
            addCriterion("ShowSize >", value, "showsize");
            return (Criteria) this;
        }

        public Criteria andShowsizeGreaterThanOrEqualTo(Byte value) {
            addCriterion("ShowSize >=", value, "showsize");
            return (Criteria) this;
        }

        public Criteria andShowsizeLessThan(Byte value) {
            addCriterion("ShowSize <", value, "showsize");
            return (Criteria) this;
        }

        public Criteria andShowsizeLessThanOrEqualTo(Byte value) {
            addCriterion("ShowSize <=", value, "showsize");
            return (Criteria) this;
        }

        public Criteria andShowsizeIn(List<Byte> values) {
            addCriterion("ShowSize in", values, "showsize");
            return (Criteria) this;
        }

        public Criteria andShowsizeNotIn(List<Byte> values) {
            addCriterion("ShowSize not in", values, "showsize");
            return (Criteria) this;
        }

        public Criteria andShowsizeBetween(Byte value1, Byte value2) {
            addCriterion("ShowSize between", value1, value2, "showsize");
            return (Criteria) this;
        }

        public Criteria andShowsizeNotBetween(Byte value1, Byte value2) {
            addCriterion("ShowSize not between", value1, value2, "showsize");
            return (Criteria) this;
        }

        public Criteria andShiptypeIsNull() {
            addCriterion("ShipType is null");
            return (Criteria) this;
        }

        public Criteria andShiptypeIsNotNull() {
            addCriterion("ShipType is not null");
            return (Criteria) this;
        }

        public Criteria andShiptypeEqualTo(Byte value) {
            addCriterion("ShipType =", value, "shiptype");
            return (Criteria) this;
        }

        public Criteria andShiptypeNotEqualTo(Byte value) {
            addCriterion("ShipType <>", value, "shiptype");
            return (Criteria) this;
        }

        public Criteria andShiptypeGreaterThan(Byte value) {
            addCriterion("ShipType >", value, "shiptype");
            return (Criteria) this;
        }

        public Criteria andShiptypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("ShipType >=", value, "shiptype");
            return (Criteria) this;
        }

        public Criteria andShiptypeLessThan(Byte value) {
            addCriterion("ShipType <", value, "shiptype");
            return (Criteria) this;
        }

        public Criteria andShiptypeLessThanOrEqualTo(Byte value) {
            addCriterion("ShipType <=", value, "shiptype");
            return (Criteria) this;
        }

        public Criteria andShiptypeIn(List<Byte> values) {
            addCriterion("ShipType in", values, "shiptype");
            return (Criteria) this;
        }

        public Criteria andShiptypeNotIn(List<Byte> values) {
            addCriterion("ShipType not in", values, "shiptype");
            return (Criteria) this;
        }

        public Criteria andShiptypeBetween(Byte value1, Byte value2) {
            addCriterion("ShipType between", value1, value2, "shiptype");
            return (Criteria) this;
        }

        public Criteria andShiptypeNotBetween(Byte value1, Byte value2) {
            addCriterion("ShipType not between", value1, value2, "shiptype");
            return (Criteria) this;
        }

        public Criteria andOnsaleIsNull() {
            addCriterion("OnSale is null");
            return (Criteria) this;
        }

        public Criteria andOnsaleIsNotNull() {
            addCriterion("OnSale is not null");
            return (Criteria) this;
        }

        public Criteria andOnsaleEqualTo(Byte value) {
            addCriterion("OnSale =", value, "onsale");
            return (Criteria) this;
        }

        public Criteria andOnsaleNotEqualTo(Byte value) {
            addCriterion("OnSale <>", value, "onsale");
            return (Criteria) this;
        }

        public Criteria andOnsaleGreaterThan(Byte value) {
            addCriterion("OnSale >", value, "onsale");
            return (Criteria) this;
        }

        public Criteria andOnsaleGreaterThanOrEqualTo(Byte value) {
            addCriterion("OnSale >=", value, "onsale");
            return (Criteria) this;
        }

        public Criteria andOnsaleLessThan(Byte value) {
            addCriterion("OnSale <", value, "onsale");
            return (Criteria) this;
        }

        public Criteria andOnsaleLessThanOrEqualTo(Byte value) {
            addCriterion("OnSale <=", value, "onsale");
            return (Criteria) this;
        }

        public Criteria andOnsaleIn(List<Byte> values) {
            addCriterion("OnSale in", values, "onsale");
            return (Criteria) this;
        }

        public Criteria andOnsaleNotIn(List<Byte> values) {
            addCriterion("OnSale not in", values, "onsale");
            return (Criteria) this;
        }

        public Criteria andOnsaleBetween(Byte value1, Byte value2) {
            addCriterion("OnSale between", value1, value2, "onsale");
            return (Criteria) this;
        }

        public Criteria andOnsaleNotBetween(Byte value1, Byte value2) {
            addCriterion("OnSale not between", value1, value2, "onsale");
            return (Criteria) this;
        }

        public Criteria andOnlyshowIsNull() {
            addCriterion("OnlyShow is null");
            return (Criteria) this;
        }

        public Criteria andOnlyshowIsNotNull() {
            addCriterion("OnlyShow is not null");
            return (Criteria) this;
        }

        public Criteria andOnlyshowEqualTo(Byte value) {
            addCriterion("OnlyShow =", value, "onlyshow");
            return (Criteria) this;
        }

        public Criteria andOnlyshowNotEqualTo(Byte value) {
            addCriterion("OnlyShow <>", value, "onlyshow");
            return (Criteria) this;
        }

        public Criteria andOnlyshowGreaterThan(Byte value) {
            addCriterion("OnlyShow >", value, "onlyshow");
            return (Criteria) this;
        }

        public Criteria andOnlyshowGreaterThanOrEqualTo(Byte value) {
            addCriterion("OnlyShow >=", value, "onlyshow");
            return (Criteria) this;
        }

        public Criteria andOnlyshowLessThan(Byte value) {
            addCriterion("OnlyShow <", value, "onlyshow");
            return (Criteria) this;
        }

        public Criteria andOnlyshowLessThanOrEqualTo(Byte value) {
            addCriterion("OnlyShow <=", value, "onlyshow");
            return (Criteria) this;
        }

        public Criteria andOnlyshowIn(List<Byte> values) {
            addCriterion("OnlyShow in", values, "onlyshow");
            return (Criteria) this;
        }

        public Criteria andOnlyshowNotIn(List<Byte> values) {
            addCriterion("OnlyShow not in", values, "onlyshow");
            return (Criteria) this;
        }

        public Criteria andOnlyshowBetween(Byte value1, Byte value2) {
            addCriterion("OnlyShow between", value1, value2, "onlyshow");
            return (Criteria) this;
        }

        public Criteria andOnlyshowNotBetween(Byte value1, Byte value2) {
            addCriterion("OnlyShow not between", value1, value2, "onlyshow");
            return (Criteria) this;
        }

        public Criteria andPhotourlIsNull() {
            addCriterion("PhotoUrl is null");
            return (Criteria) this;
        }

        public Criteria andPhotourlIsNotNull() {
            addCriterion("PhotoUrl is not null");
            return (Criteria) this;
        }

        public Criteria andPhotourlEqualTo(String value) {
            addCriterion("PhotoUrl =", value, "photourl");
            return (Criteria) this;
        }

        public Criteria andPhotourlNotEqualTo(String value) {
            addCriterion("PhotoUrl <>", value, "photourl");
            return (Criteria) this;
        }

        public Criteria andPhotourlGreaterThan(String value) {
            addCriterion("PhotoUrl >", value, "photourl");
            return (Criteria) this;
        }

        public Criteria andPhotourlGreaterThanOrEqualTo(String value) {
            addCriterion("PhotoUrl >=", value, "photourl");
            return (Criteria) this;
        }

        public Criteria andPhotourlLessThan(String value) {
            addCriterion("PhotoUrl <", value, "photourl");
            return (Criteria) this;
        }

        public Criteria andPhotourlLessThanOrEqualTo(String value) {
            addCriterion("PhotoUrl <=", value, "photourl");
            return (Criteria) this;
        }

        public Criteria andPhotourlLike(String value) {
            addCriterion("PhotoUrl like", value, "photourl");
            return (Criteria) this;
        }

        public Criteria andPhotourlNotLike(String value) {
            addCriterion("PhotoUrl not like", value, "photourl");
            return (Criteria) this;
        }

        public Criteria andPhotourlIn(List<String> values) {
            addCriterion("PhotoUrl in", values, "photourl");
            return (Criteria) this;
        }

        public Criteria andPhotourlNotIn(List<String> values) {
            addCriterion("PhotoUrl not in", values, "photourl");
            return (Criteria) this;
        }

        public Criteria andPhotourlBetween(String value1, String value2) {
            addCriterion("PhotoUrl between", value1, value2, "photourl");
            return (Criteria) this;
        }

        public Criteria andPhotourlNotBetween(String value1, String value2) {
            addCriterion("PhotoUrl not between", value1, value2, "photourl");
            return (Criteria) this;
        }

        public Criteria andExchangepointIsNull() {
            addCriterion("ExchangePoint is null");
            return (Criteria) this;
        }

        public Criteria andExchangepointIsNotNull() {
            addCriterion("ExchangePoint is not null");
            return (Criteria) this;
        }

        public Criteria andExchangepointEqualTo(Integer value) {
            addCriterion("ExchangePoint =", value, "exchangepoint");
            return (Criteria) this;
        }

        public Criteria andExchangepointNotEqualTo(Integer value) {
            addCriterion("ExchangePoint <>", value, "exchangepoint");
            return (Criteria) this;
        }

        public Criteria andExchangepointGreaterThan(Integer value) {
            addCriterion("ExchangePoint >", value, "exchangepoint");
            return (Criteria) this;
        }

        public Criteria andExchangepointGreaterThanOrEqualTo(Integer value) {
            addCriterion("ExchangePoint >=", value, "exchangepoint");
            return (Criteria) this;
        }

        public Criteria andExchangepointLessThan(Integer value) {
            addCriterion("ExchangePoint <", value, "exchangepoint");
            return (Criteria) this;
        }

        public Criteria andExchangepointLessThanOrEqualTo(Integer value) {
            addCriterion("ExchangePoint <=", value, "exchangepoint");
            return (Criteria) this;
        }

        public Criteria andExchangepointIn(List<Integer> values) {
            addCriterion("ExchangePoint in", values, "exchangepoint");
            return (Criteria) this;
        }

        public Criteria andExchangepointNotIn(List<Integer> values) {
            addCriterion("ExchangePoint not in", values, "exchangepoint");
            return (Criteria) this;
        }

        public Criteria andExchangepointBetween(Integer value1, Integer value2) {
            addCriterion("ExchangePoint between", value1, value2, "exchangepoint");
            return (Criteria) this;
        }

        public Criteria andExchangepointNotBetween(Integer value1, Integer value2) {
            addCriterion("ExchangePoint not between", value1, value2, "exchangepoint");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeIsNull() {
            addCriterion("OnSaleTime is null");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeIsNotNull() {
            addCriterion("OnSaleTime is not null");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeEqualTo(Date value) {
            addCriterion("OnSaleTime =", value, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeNotEqualTo(Date value) {
            addCriterion("OnSaleTime <>", value, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeGreaterThan(Date value) {
            addCriterion("OnSaleTime >", value, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeGreaterThanOrEqualTo(Date value) {
            addCriterion("OnSaleTime >=", value, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeLessThan(Date value) {
            addCriterion("OnSaleTime <", value, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeLessThanOrEqualTo(Date value) {
            addCriterion("OnSaleTime <=", value, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeIn(List<Date> values) {
            addCriterion("OnSaleTime in", values, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeNotIn(List<Date> values) {
            addCriterion("OnSaleTime not in", values, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeBetween(Date value1, Date value2) {
            addCriterion("OnSaleTime between", value1, value2, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaletimeNotBetween(Date value1, Date value2) {
            addCriterion("OnSaleTime not between", value1, value2, "onsaletime");
            return (Criteria) this;
        }

        public Criteria andOnsaleerIsNull() {
            addCriterion("OnSaleer is null");
            return (Criteria) this;
        }

        public Criteria andOnsaleerIsNotNull() {
            addCriterion("OnSaleer is not null");
            return (Criteria) this;
        }

        public Criteria andOnsaleerEqualTo(String value) {
            addCriterion("OnSaleer =", value, "onsaleer");
            return (Criteria) this;
        }

        public Criteria andOnsaleerNotEqualTo(String value) {
            addCriterion("OnSaleer <>", value, "onsaleer");
            return (Criteria) this;
        }

        public Criteria andOnsaleerGreaterThan(String value) {
            addCriterion("OnSaleer >", value, "onsaleer");
            return (Criteria) this;
        }

        public Criteria andOnsaleerGreaterThanOrEqualTo(String value) {
            addCriterion("OnSaleer >=", value, "onsaleer");
            return (Criteria) this;
        }

        public Criteria andOnsaleerLessThan(String value) {
            addCriterion("OnSaleer <", value, "onsaleer");
            return (Criteria) this;
        }

        public Criteria andOnsaleerLessThanOrEqualTo(String value) {
            addCriterion("OnSaleer <=", value, "onsaleer");
            return (Criteria) this;
        }

        public Criteria andOnsaleerLike(String value) {
            addCriterion("OnSaleer like", value, "onsaleer");
            return (Criteria) this;
        }

        public Criteria andOnsaleerNotLike(String value) {
            addCriterion("OnSaleer not like", value, "onsaleer");
            return (Criteria) this;
        }

        public Criteria andOnsaleerIn(List<String> values) {
            addCriterion("OnSaleer in", values, "onsaleer");
            return (Criteria) this;
        }

        public Criteria andOnsaleerNotIn(List<String> values) {
            addCriterion("OnSaleer not in", values, "onsaleer");
            return (Criteria) this;
        }

        public Criteria andOnsaleerBetween(String value1, String value2) {
            addCriterion("OnSaleer between", value1, value2, "onsaleer");
            return (Criteria) this;
        }

        public Criteria andOnsaleerNotBetween(String value1, String value2) {
            addCriterion("OnSaleer not between", value1, value2, "onsaleer");
            return (Criteria) this;
        }

        public Criteria andPointtimesIsNull() {
            addCriterion("PointTimes is null");
            return (Criteria) this;
        }

        public Criteria andPointtimesIsNotNull() {
            addCriterion("PointTimes is not null");
            return (Criteria) this;
        }

        public Criteria andPointtimesEqualTo(BigDecimal value) {
            addCriterion("PointTimes =", value, "pointtimes");
            return (Criteria) this;
        }

        public Criteria andPointtimesNotEqualTo(BigDecimal value) {
            addCriterion("PointTimes <>", value, "pointtimes");
            return (Criteria) this;
        }

        public Criteria andPointtimesGreaterThan(BigDecimal value) {
            addCriterion("PointTimes >", value, "pointtimes");
            return (Criteria) this;
        }

        public Criteria andPointtimesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PointTimes >=", value, "pointtimes");
            return (Criteria) this;
        }

        public Criteria andPointtimesLessThan(BigDecimal value) {
            addCriterion("PointTimes <", value, "pointtimes");
            return (Criteria) this;
        }

        public Criteria andPointtimesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PointTimes <=", value, "pointtimes");
            return (Criteria) this;
        }

        public Criteria andPointtimesIn(List<BigDecimal> values) {
            addCriterion("PointTimes in", values, "pointtimes");
            return (Criteria) this;
        }

        public Criteria andPointtimesNotIn(List<BigDecimal> values) {
            addCriterion("PointTimes not in", values, "pointtimes");
            return (Criteria) this;
        }

        public Criteria andPointtimesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PointTimes between", value1, value2, "pointtimes");
            return (Criteria) this;
        }

        public Criteria andPointtimesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PointTimes not between", value1, value2, "pointtimes");
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

        public Criteria andDescribeIsNull() {
            addCriterion("Describe is null");
            return (Criteria) this;
        }

        public Criteria andDescribeIsNotNull() {
            addCriterion("Describe is not null");
            return (Criteria) this;
        }

        public Criteria andDescribeEqualTo(String value) {
            addCriterion("Describe =", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotEqualTo(String value) {
            addCriterion("Describe <>", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeGreaterThan(String value) {
            addCriterion("Describe >", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("Describe >=", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLessThan(String value) {
            addCriterion("Describe <", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLessThanOrEqualTo(String value) {
            addCriterion("Describe <=", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeLike(String value) {
            addCriterion("Describe like", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotLike(String value) {
            addCriterion("Describe not like", value, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeIn(List<String> values) {
            addCriterion("Describe in", values, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotIn(List<String> values) {
            addCriterion("Describe not in", values, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeBetween(String value1, String value2) {
            addCriterion("Describe between", value1, value2, "describe");
            return (Criteria) this;
        }

        public Criteria andDescribeNotBetween(String value1, String value2) {
            addCriterion("Describe not between", value1, value2, "describe");
            return (Criteria) this;
        }

        public Criteria andBigcategoryIsNull() {
            addCriterion("BigCategory is null");
            return (Criteria) this;
        }

        public Criteria andBigcategoryIsNotNull() {
            addCriterion("BigCategory is not null");
            return (Criteria) this;
        }

        public Criteria andBigcategoryEqualTo(Integer value) {
            addCriterion("BigCategory =", value, "bigcategory");
            return (Criteria) this;
        }

        public Criteria andBigcategoryNotEqualTo(Integer value) {
            addCriterion("BigCategory <>", value, "bigcategory");
            return (Criteria) this;
        }

        public Criteria andBigcategoryGreaterThan(Integer value) {
            addCriterion("BigCategory >", value, "bigcategory");
            return (Criteria) this;
        }

        public Criteria andBigcategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("BigCategory >=", value, "bigcategory");
            return (Criteria) this;
        }

        public Criteria andBigcategoryLessThan(Integer value) {
            addCriterion("BigCategory <", value, "bigcategory");
            return (Criteria) this;
        }

        public Criteria andBigcategoryLessThanOrEqualTo(Integer value) {
            addCriterion("BigCategory <=", value, "bigcategory");
            return (Criteria) this;
        }

        public Criteria andBigcategoryIn(List<Integer> values) {
            addCriterion("BigCategory in", values, "bigcategory");
            return (Criteria) this;
        }

        public Criteria andBigcategoryNotIn(List<Integer> values) {
            addCriterion("BigCategory not in", values, "bigcategory");
            return (Criteria) this;
        }

        public Criteria andBigcategoryBetween(Integer value1, Integer value2) {
            addCriterion("BigCategory between", value1, value2, "bigcategory");
            return (Criteria) this;
        }

        public Criteria andBigcategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("BigCategory not between", value1, value2, "bigcategory");
            return (Criteria) this;
        }

        public Criteria andMiddlecategoryIsNull() {
            addCriterion("MiddleCategory is null");
            return (Criteria) this;
        }

        public Criteria andMiddlecategoryIsNotNull() {
            addCriterion("MiddleCategory is not null");
            return (Criteria) this;
        }

        public Criteria andMiddlecategoryEqualTo(Integer value) {
            addCriterion("MiddleCategory =", value, "middlecategory");
            return (Criteria) this;
        }

        public Criteria andMiddlecategoryNotEqualTo(Integer value) {
            addCriterion("MiddleCategory <>", value, "middlecategory");
            return (Criteria) this;
        }

        public Criteria andMiddlecategoryGreaterThan(Integer value) {
            addCriterion("MiddleCategory >", value, "middlecategory");
            return (Criteria) this;
        }

        public Criteria andMiddlecategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("MiddleCategory >=", value, "middlecategory");
            return (Criteria) this;
        }

        public Criteria andMiddlecategoryLessThan(Integer value) {
            addCriterion("MiddleCategory <", value, "middlecategory");
            return (Criteria) this;
        }

        public Criteria andMiddlecategoryLessThanOrEqualTo(Integer value) {
            addCriterion("MiddleCategory <=", value, "middlecategory");
            return (Criteria) this;
        }

        public Criteria andMiddlecategoryIn(List<Integer> values) {
            addCriterion("MiddleCategory in", values, "middlecategory");
            return (Criteria) this;
        }

        public Criteria andMiddlecategoryNotIn(List<Integer> values) {
            addCriterion("MiddleCategory not in", values, "middlecategory");
            return (Criteria) this;
        }

        public Criteria andMiddlecategoryBetween(Integer value1, Integer value2) {
            addCriterion("MiddleCategory between", value1, value2, "middlecategory");
            return (Criteria) this;
        }

        public Criteria andMiddlecategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("MiddleCategory not between", value1, value2, "middlecategory");
            return (Criteria) this;
        }

        public Criteria andSmallcategoryIsNull() {
            addCriterion("SmallCategory is null");
            return (Criteria) this;
        }

        public Criteria andSmallcategoryIsNotNull() {
            addCriterion("SmallCategory is not null");
            return (Criteria) this;
        }

        public Criteria andSmallcategoryEqualTo(Integer value) {
            addCriterion("SmallCategory =", value, "smallcategory");
            return (Criteria) this;
        }

        public Criteria andSmallcategoryNotEqualTo(Integer value) {
            addCriterion("SmallCategory <>", value, "smallcategory");
            return (Criteria) this;
        }

        public Criteria andSmallcategoryGreaterThan(Integer value) {
            addCriterion("SmallCategory >", value, "smallcategory");
            return (Criteria) this;
        }

        public Criteria andSmallcategoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("SmallCategory >=", value, "smallcategory");
            return (Criteria) this;
        }

        public Criteria andSmallcategoryLessThan(Integer value) {
            addCriterion("SmallCategory <", value, "smallcategory");
            return (Criteria) this;
        }

        public Criteria andSmallcategoryLessThanOrEqualTo(Integer value) {
            addCriterion("SmallCategory <=", value, "smallcategory");
            return (Criteria) this;
        }

        public Criteria andSmallcategoryIn(List<Integer> values) {
            addCriterion("SmallCategory in", values, "smallcategory");
            return (Criteria) this;
        }

        public Criteria andSmallcategoryNotIn(List<Integer> values) {
            addCriterion("SmallCategory not in", values, "smallcategory");
            return (Criteria) this;
        }

        public Criteria andSmallcategoryBetween(Integer value1, Integer value2) {
            addCriterion("SmallCategory between", value1, value2, "smallcategory");
            return (Criteria) this;
        }

        public Criteria andSmallcategoryNotBetween(Integer value1, Integer value2) {
            addCriterion("SmallCategory not between", value1, value2, "smallcategory");
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