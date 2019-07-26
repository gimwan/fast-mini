package com.fast.base.data.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MVipaddressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MVipaddressExample() {
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

        public Criteria andProvinceidIsNull() {
            addCriterion("ProvinceID is null");
            return (Criteria) this;
        }

        public Criteria andProvinceidIsNotNull() {
            addCriterion("ProvinceID is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceidEqualTo(Integer value) {
            addCriterion("ProvinceID =", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidNotEqualTo(Integer value) {
            addCriterion("ProvinceID <>", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidGreaterThan(Integer value) {
            addCriterion("ProvinceID >", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidGreaterThanOrEqualTo(Integer value) {
            addCriterion("ProvinceID >=", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidLessThan(Integer value) {
            addCriterion("ProvinceID <", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidLessThanOrEqualTo(Integer value) {
            addCriterion("ProvinceID <=", value, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidIn(List<Integer> values) {
            addCriterion("ProvinceID in", values, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidNotIn(List<Integer> values) {
            addCriterion("ProvinceID not in", values, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidBetween(Integer value1, Integer value2) {
            addCriterion("ProvinceID between", value1, value2, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceidNotBetween(Integer value1, Integer value2) {
            addCriterion("ProvinceID not between", value1, value2, "provinceid");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("Province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("Province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("Province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("Province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("Province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("Province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("Province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("Province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("Province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("Province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("Province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("Province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("Province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("Province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityidIsNull() {
            addCriterion("CityID is null");
            return (Criteria) this;
        }

        public Criteria andCityidIsNotNull() {
            addCriterion("CityID is not null");
            return (Criteria) this;
        }

        public Criteria andCityidEqualTo(Integer value) {
            addCriterion("CityID =", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotEqualTo(Integer value) {
            addCriterion("CityID <>", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidGreaterThan(Integer value) {
            addCriterion("CityID >", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidGreaterThanOrEqualTo(Integer value) {
            addCriterion("CityID >=", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLessThan(Integer value) {
            addCriterion("CityID <", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidLessThanOrEqualTo(Integer value) {
            addCriterion("CityID <=", value, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidIn(List<Integer> values) {
            addCriterion("CityID in", values, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotIn(List<Integer> values) {
            addCriterion("CityID not in", values, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidBetween(Integer value1, Integer value2) {
            addCriterion("CityID between", value1, value2, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityidNotBetween(Integer value1, Integer value2) {
            addCriterion("CityID not between", value1, value2, "cityid");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("City is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("City is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("City =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("City <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("City >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("City >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("City <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("City <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("City like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("City not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("City in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("City not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("City between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("City not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCountyidIsNull() {
            addCriterion("CountyID is null");
            return (Criteria) this;
        }

        public Criteria andCountyidIsNotNull() {
            addCriterion("CountyID is not null");
            return (Criteria) this;
        }

        public Criteria andCountyidEqualTo(Integer value) {
            addCriterion("CountyID =", value, "countyid");
            return (Criteria) this;
        }

        public Criteria andCountyidNotEqualTo(Integer value) {
            addCriterion("CountyID <>", value, "countyid");
            return (Criteria) this;
        }

        public Criteria andCountyidGreaterThan(Integer value) {
            addCriterion("CountyID >", value, "countyid");
            return (Criteria) this;
        }

        public Criteria andCountyidGreaterThanOrEqualTo(Integer value) {
            addCriterion("CountyID >=", value, "countyid");
            return (Criteria) this;
        }

        public Criteria andCountyidLessThan(Integer value) {
            addCriterion("CountyID <", value, "countyid");
            return (Criteria) this;
        }

        public Criteria andCountyidLessThanOrEqualTo(Integer value) {
            addCriterion("CountyID <=", value, "countyid");
            return (Criteria) this;
        }

        public Criteria andCountyidIn(List<Integer> values) {
            addCriterion("CountyID in", values, "countyid");
            return (Criteria) this;
        }

        public Criteria andCountyidNotIn(List<Integer> values) {
            addCriterion("CountyID not in", values, "countyid");
            return (Criteria) this;
        }

        public Criteria andCountyidBetween(Integer value1, Integer value2) {
            addCriterion("CountyID between", value1, value2, "countyid");
            return (Criteria) this;
        }

        public Criteria andCountyidNotBetween(Integer value1, Integer value2) {
            addCriterion("CountyID not between", value1, value2, "countyid");
            return (Criteria) this;
        }

        public Criteria andCountyIsNull() {
            addCriterion("County is null");
            return (Criteria) this;
        }

        public Criteria andCountyIsNotNull() {
            addCriterion("County is not null");
            return (Criteria) this;
        }

        public Criteria andCountyEqualTo(String value) {
            addCriterion("County =", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotEqualTo(String value) {
            addCriterion("County <>", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThan(String value) {
            addCriterion("County >", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyGreaterThanOrEqualTo(String value) {
            addCriterion("County >=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThan(String value) {
            addCriterion("County <", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLessThanOrEqualTo(String value) {
            addCriterion("County <=", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyLike(String value) {
            addCriterion("County like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotLike(String value) {
            addCriterion("County not like", value, "county");
            return (Criteria) this;
        }

        public Criteria andCountyIn(List<String> values) {
            addCriterion("County in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotIn(List<String> values) {
            addCriterion("County not in", values, "county");
            return (Criteria) this;
        }

        public Criteria andCountyBetween(String value1, String value2) {
            addCriterion("County between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andCountyNotBetween(String value1, String value2) {
            addCriterion("County not between", value1, value2, "county");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("Address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("Address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("Address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("Address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("Address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("Address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("Address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("Address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("Address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("Address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("Address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("Address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("Address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("Address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andIsdefaultIsNull() {
            addCriterion("IsDefault is null");
            return (Criteria) this;
        }

        public Criteria andIsdefaultIsNotNull() {
            addCriterion("IsDefault is not null");
            return (Criteria) this;
        }

        public Criteria andIsdefaultEqualTo(Byte value) {
            addCriterion("IsDefault =", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotEqualTo(Byte value) {
            addCriterion("IsDefault <>", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultGreaterThan(Byte value) {
            addCriterion("IsDefault >", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultGreaterThanOrEqualTo(Byte value) {
            addCriterion("IsDefault >=", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultLessThan(Byte value) {
            addCriterion("IsDefault <", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultLessThanOrEqualTo(Byte value) {
            addCriterion("IsDefault <=", value, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultIn(List<Byte> values) {
            addCriterion("IsDefault in", values, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotIn(List<Byte> values) {
            addCriterion("IsDefault not in", values, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultBetween(Byte value1, Byte value2) {
            addCriterion("IsDefault between", value1, value2, "isdefault");
            return (Criteria) this;
        }

        public Criteria andIsdefaultNotBetween(Byte value1, Byte value2) {
            addCriterion("IsDefault not between", value1, value2, "isdefault");
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

        public Criteria andPhoneIsNull() {
            addCriterion("Phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("Phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("Phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("Phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("Phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("Phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("Phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("Phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("Phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("Phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("Phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("Phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("Phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("Phone not between", value1, value2, "phone");
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

        public Criteria andTypeIsNull() {
            addCriterion("Type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("Type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("Type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("Type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("Type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("Type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("Type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("Type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("Type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("Type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("Type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("Type not between", value1, value2, "type");
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