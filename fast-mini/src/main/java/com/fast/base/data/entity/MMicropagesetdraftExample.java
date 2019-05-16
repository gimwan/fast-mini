package com.fast.base.data.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MMicropagesetdraftExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MMicropagesetdraftExample() {
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

        public Criteria andMicropageidIsNull() {
            addCriterion("MicroPageID is null");
            return (Criteria) this;
        }

        public Criteria andMicropageidIsNotNull() {
            addCriterion("MicroPageID is not null");
            return (Criteria) this;
        }

        public Criteria andMicropageidEqualTo(Integer value) {
            addCriterion("MicroPageID =", value, "micropageid");
            return (Criteria) this;
        }

        public Criteria andMicropageidNotEqualTo(Integer value) {
            addCriterion("MicroPageID <>", value, "micropageid");
            return (Criteria) this;
        }

        public Criteria andMicropageidGreaterThan(Integer value) {
            addCriterion("MicroPageID >", value, "micropageid");
            return (Criteria) this;
        }

        public Criteria andMicropageidGreaterThanOrEqualTo(Integer value) {
            addCriterion("MicroPageID >=", value, "micropageid");
            return (Criteria) this;
        }

        public Criteria andMicropageidLessThan(Integer value) {
            addCriterion("MicroPageID <", value, "micropageid");
            return (Criteria) this;
        }

        public Criteria andMicropageidLessThanOrEqualTo(Integer value) {
            addCriterion("MicroPageID <=", value, "micropageid");
            return (Criteria) this;
        }

        public Criteria andMicropageidIn(List<Integer> values) {
            addCriterion("MicroPageID in", values, "micropageid");
            return (Criteria) this;
        }

        public Criteria andMicropageidNotIn(List<Integer> values) {
            addCriterion("MicroPageID not in", values, "micropageid");
            return (Criteria) this;
        }

        public Criteria andMicropageidBetween(Integer value1, Integer value2) {
            addCriterion("MicroPageID between", value1, value2, "micropageid");
            return (Criteria) this;
        }

        public Criteria andMicropageidNotBetween(Integer value1, Integer value2) {
            addCriterion("MicroPageID not between", value1, value2, "micropageid");
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

        public Criteria andIndexIsNull() {
            addCriterion("Index is null");
            return (Criteria) this;
        }

        public Criteria andIndexIsNotNull() {
            addCriterion("Index is not null");
            return (Criteria) this;
        }

        public Criteria andIndexEqualTo(Integer value) {
            addCriterion("Index =", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotEqualTo(Integer value) {
            addCriterion("Index <>", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexGreaterThan(Integer value) {
            addCriterion("Index >", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("Index >=", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexLessThan(Integer value) {
            addCriterion("Index <", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexLessThanOrEqualTo(Integer value) {
            addCriterion("Index <=", value, "index");
            return (Criteria) this;
        }

        public Criteria andIndexIn(List<Integer> values) {
            addCriterion("Index in", values, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotIn(List<Integer> values) {
            addCriterion("Index not in", values, "index");
            return (Criteria) this;
        }

        public Criteria andIndexBetween(Integer value1, Integer value2) {
            addCriterion("Index between", value1, value2, "index");
            return (Criteria) this;
        }

        public Criteria andIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("Index not between", value1, value2, "index");
            return (Criteria) this;
        }

        public Criteria andShownameIsNull() {
            addCriterion("ShowName is null");
            return (Criteria) this;
        }

        public Criteria andShownameIsNotNull() {
            addCriterion("ShowName is not null");
            return (Criteria) this;
        }

        public Criteria andShownameEqualTo(Byte value) {
            addCriterion("ShowName =", value, "showname");
            return (Criteria) this;
        }

        public Criteria andShownameNotEqualTo(Byte value) {
            addCriterion("ShowName <>", value, "showname");
            return (Criteria) this;
        }

        public Criteria andShownameGreaterThan(Byte value) {
            addCriterion("ShowName >", value, "showname");
            return (Criteria) this;
        }

        public Criteria andShownameGreaterThanOrEqualTo(Byte value) {
            addCriterion("ShowName >=", value, "showname");
            return (Criteria) this;
        }

        public Criteria andShownameLessThan(Byte value) {
            addCriterion("ShowName <", value, "showname");
            return (Criteria) this;
        }

        public Criteria andShownameLessThanOrEqualTo(Byte value) {
            addCriterion("ShowName <=", value, "showname");
            return (Criteria) this;
        }

        public Criteria andShownameIn(List<Byte> values) {
            addCriterion("ShowName in", values, "showname");
            return (Criteria) this;
        }

        public Criteria andShownameNotIn(List<Byte> values) {
            addCriterion("ShowName not in", values, "showname");
            return (Criteria) this;
        }

        public Criteria andShownameBetween(Byte value1, Byte value2) {
            addCriterion("ShowName between", value1, value2, "showname");
            return (Criteria) this;
        }

        public Criteria andShownameNotBetween(Byte value1, Byte value2) {
            addCriterion("ShowName not between", value1, value2, "showname");
            return (Criteria) this;
        }

        public Criteria andShowpriceIsNull() {
            addCriterion("ShowPrice is null");
            return (Criteria) this;
        }

        public Criteria andShowpriceIsNotNull() {
            addCriterion("ShowPrice is not null");
            return (Criteria) this;
        }

        public Criteria andShowpriceEqualTo(Byte value) {
            addCriterion("ShowPrice =", value, "showprice");
            return (Criteria) this;
        }

        public Criteria andShowpriceNotEqualTo(Byte value) {
            addCriterion("ShowPrice <>", value, "showprice");
            return (Criteria) this;
        }

        public Criteria andShowpriceGreaterThan(Byte value) {
            addCriterion("ShowPrice >", value, "showprice");
            return (Criteria) this;
        }

        public Criteria andShowpriceGreaterThanOrEqualTo(Byte value) {
            addCriterion("ShowPrice >=", value, "showprice");
            return (Criteria) this;
        }

        public Criteria andShowpriceLessThan(Byte value) {
            addCriterion("ShowPrice <", value, "showprice");
            return (Criteria) this;
        }

        public Criteria andShowpriceLessThanOrEqualTo(Byte value) {
            addCriterion("ShowPrice <=", value, "showprice");
            return (Criteria) this;
        }

        public Criteria andShowpriceIn(List<Byte> values) {
            addCriterion("ShowPrice in", values, "showprice");
            return (Criteria) this;
        }

        public Criteria andShowpriceNotIn(List<Byte> values) {
            addCriterion("ShowPrice not in", values, "showprice");
            return (Criteria) this;
        }

        public Criteria andShowpriceBetween(Byte value1, Byte value2) {
            addCriterion("ShowPrice between", value1, value2, "showprice");
            return (Criteria) this;
        }

        public Criteria andShowpriceNotBetween(Byte value1, Byte value2) {
            addCriterion("ShowPrice not between", value1, value2, "showprice");
            return (Criteria) this;
        }

        public Criteria andImagestyleIsNull() {
            addCriterion("ImageStyle is null");
            return (Criteria) this;
        }

        public Criteria andImagestyleIsNotNull() {
            addCriterion("ImageStyle is not null");
            return (Criteria) this;
        }

        public Criteria andImagestyleEqualTo(Byte value) {
            addCriterion("ImageStyle =", value, "imagestyle");
            return (Criteria) this;
        }

        public Criteria andImagestyleNotEqualTo(Byte value) {
            addCriterion("ImageStyle <>", value, "imagestyle");
            return (Criteria) this;
        }

        public Criteria andImagestyleGreaterThan(Byte value) {
            addCriterion("ImageStyle >", value, "imagestyle");
            return (Criteria) this;
        }

        public Criteria andImagestyleGreaterThanOrEqualTo(Byte value) {
            addCriterion("ImageStyle >=", value, "imagestyle");
            return (Criteria) this;
        }

        public Criteria andImagestyleLessThan(Byte value) {
            addCriterion("ImageStyle <", value, "imagestyle");
            return (Criteria) this;
        }

        public Criteria andImagestyleLessThanOrEqualTo(Byte value) {
            addCriterion("ImageStyle <=", value, "imagestyle");
            return (Criteria) this;
        }

        public Criteria andImagestyleIn(List<Byte> values) {
            addCriterion("ImageStyle in", values, "imagestyle");
            return (Criteria) this;
        }

        public Criteria andImagestyleNotIn(List<Byte> values) {
            addCriterion("ImageStyle not in", values, "imagestyle");
            return (Criteria) this;
        }

        public Criteria andImagestyleBetween(Byte value1, Byte value2) {
            addCriterion("ImageStyle between", value1, value2, "imagestyle");
            return (Criteria) this;
        }

        public Criteria andImagestyleNotBetween(Byte value1, Byte value2) {
            addCriterion("ImageStyle not between", value1, value2, "imagestyle");
            return (Criteria) this;
        }

        public Criteria andOrderbyIsNull() {
            addCriterion("OrderBy is null");
            return (Criteria) this;
        }

        public Criteria andOrderbyIsNotNull() {
            addCriterion("OrderBy is not null");
            return (Criteria) this;
        }

        public Criteria andOrderbyEqualTo(Byte value) {
            addCriterion("OrderBy =", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyNotEqualTo(Byte value) {
            addCriterion("OrderBy <>", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyGreaterThan(Byte value) {
            addCriterion("OrderBy >", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyGreaterThanOrEqualTo(Byte value) {
            addCriterion("OrderBy >=", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyLessThan(Byte value) {
            addCriterion("OrderBy <", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyLessThanOrEqualTo(Byte value) {
            addCriterion("OrderBy <=", value, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyIn(List<Byte> values) {
            addCriterion("OrderBy in", values, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyNotIn(List<Byte> values) {
            addCriterion("OrderBy not in", values, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyBetween(Byte value1, Byte value2) {
            addCriterion("OrderBy between", value1, value2, "orderby");
            return (Criteria) this;
        }

        public Criteria andOrderbyNotBetween(Byte value1, Byte value2) {
            addCriterion("OrderBy not between", value1, value2, "orderby");
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