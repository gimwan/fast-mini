package com.fast.base.data.entity;

import java.util.ArrayList;
import java.util.List;

public class MMicropagesetdtldraftExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MMicropagesetdtldraftExample() {
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

        public Criteria andMicropagesetidIsNull() {
            addCriterion("MicroPageSetID is null");
            return (Criteria) this;
        }

        public Criteria andMicropagesetidIsNotNull() {
            addCriterion("MicroPageSetID is not null");
            return (Criteria) this;
        }

        public Criteria andMicropagesetidEqualTo(Integer value) {
            addCriterion("MicroPageSetID =", value, "micropagesetid");
            return (Criteria) this;
        }

        public Criteria andMicropagesetidNotEqualTo(Integer value) {
            addCriterion("MicroPageSetID <>", value, "micropagesetid");
            return (Criteria) this;
        }

        public Criteria andMicropagesetidGreaterThan(Integer value) {
            addCriterion("MicroPageSetID >", value, "micropagesetid");
            return (Criteria) this;
        }

        public Criteria andMicropagesetidGreaterThanOrEqualTo(Integer value) {
            addCriterion("MicroPageSetID >=", value, "micropagesetid");
            return (Criteria) this;
        }

        public Criteria andMicropagesetidLessThan(Integer value) {
            addCriterion("MicroPageSetID <", value, "micropagesetid");
            return (Criteria) this;
        }

        public Criteria andMicropagesetidLessThanOrEqualTo(Integer value) {
            addCriterion("MicroPageSetID <=", value, "micropagesetid");
            return (Criteria) this;
        }

        public Criteria andMicropagesetidIn(List<Integer> values) {
            addCriterion("MicroPageSetID in", values, "micropagesetid");
            return (Criteria) this;
        }

        public Criteria andMicropagesetidNotIn(List<Integer> values) {
            addCriterion("MicroPageSetID not in", values, "micropagesetid");
            return (Criteria) this;
        }

        public Criteria andMicropagesetidBetween(Integer value1, Integer value2) {
            addCriterion("MicroPageSetID between", value1, value2, "micropagesetid");
            return (Criteria) this;
        }

        public Criteria andMicropagesetidNotBetween(Integer value1, Integer value2) {
            addCriterion("MicroPageSetID not between", value1, value2, "micropagesetid");
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

        public Criteria andFirstIsNull() {
            addCriterion("First is null");
            return (Criteria) this;
        }

        public Criteria andFirstIsNotNull() {
            addCriterion("First is not null");
            return (Criteria) this;
        }

        public Criteria andFirstEqualTo(Integer value) {
            addCriterion("First =", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotEqualTo(Integer value) {
            addCriterion("First <>", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstGreaterThan(Integer value) {
            addCriterion("First >", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstGreaterThanOrEqualTo(Integer value) {
            addCriterion("First >=", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstLessThan(Integer value) {
            addCriterion("First <", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstLessThanOrEqualTo(Integer value) {
            addCriterion("First <=", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstIn(List<Integer> values) {
            addCriterion("First in", values, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotIn(List<Integer> values) {
            addCriterion("First not in", values, "first");
            return (Criteria) this;
        }

        public Criteria andFirstBetween(Integer value1, Integer value2) {
            addCriterion("First between", value1, value2, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotBetween(Integer value1, Integer value2) {
            addCriterion("First not between", value1, value2, "first");
            return (Criteria) this;
        }

        public Criteria andSecondIsNull() {
            addCriterion("Second is null");
            return (Criteria) this;
        }

        public Criteria andSecondIsNotNull() {
            addCriterion("Second is not null");
            return (Criteria) this;
        }

        public Criteria andSecondEqualTo(Integer value) {
            addCriterion("Second =", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotEqualTo(Integer value) {
            addCriterion("Second <>", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondGreaterThan(Integer value) {
            addCriterion("Second >", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondGreaterThanOrEqualTo(Integer value) {
            addCriterion("Second >=", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondLessThan(Integer value) {
            addCriterion("Second <", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondLessThanOrEqualTo(Integer value) {
            addCriterion("Second <=", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondIn(List<Integer> values) {
            addCriterion("Second in", values, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotIn(List<Integer> values) {
            addCriterion("Second not in", values, "second");
            return (Criteria) this;
        }

        public Criteria andSecondBetween(Integer value1, Integer value2) {
            addCriterion("Second between", value1, value2, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotBetween(Integer value1, Integer value2) {
            addCriterion("Second not between", value1, value2, "second");
            return (Criteria) this;
        }

        public Criteria andThirdIsNull() {
            addCriterion("Third is null");
            return (Criteria) this;
        }

        public Criteria andThirdIsNotNull() {
            addCriterion("Third is not null");
            return (Criteria) this;
        }

        public Criteria andThirdEqualTo(Integer value) {
            addCriterion("Third =", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdNotEqualTo(Integer value) {
            addCriterion("Third <>", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdGreaterThan(Integer value) {
            addCriterion("Third >", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Third >=", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdLessThan(Integer value) {
            addCriterion("Third <", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdLessThanOrEqualTo(Integer value) {
            addCriterion("Third <=", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdIn(List<Integer> values) {
            addCriterion("Third in", values, "third");
            return (Criteria) this;
        }

        public Criteria andThirdNotIn(List<Integer> values) {
            addCriterion("Third not in", values, "third");
            return (Criteria) this;
        }

        public Criteria andThirdBetween(Integer value1, Integer value2) {
            addCriterion("Third between", value1, value2, "third");
            return (Criteria) this;
        }

        public Criteria andThirdNotBetween(Integer value1, Integer value2) {
            addCriterion("Third not between", value1, value2, "third");
            return (Criteria) this;
        }

        public Criteria andTextIsNull() {
            addCriterion("Text is null");
            return (Criteria) this;
        }

        public Criteria andTextIsNotNull() {
            addCriterion("Text is not null");
            return (Criteria) this;
        }

        public Criteria andTextEqualTo(String value) {
            addCriterion("Text =", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotEqualTo(String value) {
            addCriterion("Text <>", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextGreaterThan(String value) {
            addCriterion("Text >", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextGreaterThanOrEqualTo(String value) {
            addCriterion("Text >=", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLessThan(String value) {
            addCriterion("Text <", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLessThanOrEqualTo(String value) {
            addCriterion("Text <=", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextLike(String value) {
            addCriterion("Text like", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotLike(String value) {
            addCriterion("Text not like", value, "text");
            return (Criteria) this;
        }

        public Criteria andTextIn(List<String> values) {
            addCriterion("Text in", values, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotIn(List<String> values) {
            addCriterion("Text not in", values, "text");
            return (Criteria) this;
        }

        public Criteria andTextBetween(String value1, String value2) {
            addCriterion("Text between", value1, value2, "text");
            return (Criteria) this;
        }

        public Criteria andTextNotBetween(String value1, String value2) {
            addCriterion("Text not between", value1, value2, "text");
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

        public Criteria andTargetpathIsNull() {
            addCriterion("TargetPath is null");
            return (Criteria) this;
        }

        public Criteria andTargetpathIsNotNull() {
            addCriterion("TargetPath is not null");
            return (Criteria) this;
        }

        public Criteria andTargetpathEqualTo(String value) {
            addCriterion("TargetPath =", value, "targetpath");
            return (Criteria) this;
        }

        public Criteria andTargetpathNotEqualTo(String value) {
            addCriterion("TargetPath <>", value, "targetpath");
            return (Criteria) this;
        }

        public Criteria andTargetpathGreaterThan(String value) {
            addCriterion("TargetPath >", value, "targetpath");
            return (Criteria) this;
        }

        public Criteria andTargetpathGreaterThanOrEqualTo(String value) {
            addCriterion("TargetPath >=", value, "targetpath");
            return (Criteria) this;
        }

        public Criteria andTargetpathLessThan(String value) {
            addCriterion("TargetPath <", value, "targetpath");
            return (Criteria) this;
        }

        public Criteria andTargetpathLessThanOrEqualTo(String value) {
            addCriterion("TargetPath <=", value, "targetpath");
            return (Criteria) this;
        }

        public Criteria andTargetpathLike(String value) {
            addCriterion("TargetPath like", value, "targetpath");
            return (Criteria) this;
        }

        public Criteria andTargetpathNotLike(String value) {
            addCriterion("TargetPath not like", value, "targetpath");
            return (Criteria) this;
        }

        public Criteria andTargetpathIn(List<String> values) {
            addCriterion("TargetPath in", values, "targetpath");
            return (Criteria) this;
        }

        public Criteria andTargetpathNotIn(List<String> values) {
            addCriterion("TargetPath not in", values, "targetpath");
            return (Criteria) this;
        }

        public Criteria andTargetpathBetween(String value1, String value2) {
            addCriterion("TargetPath between", value1, value2, "targetpath");
            return (Criteria) this;
        }

        public Criteria andTargetpathNotBetween(String value1, String value2) {
            addCriterion("TargetPath not between", value1, value2, "targetpath");
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

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("Type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("Type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("Type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("Type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("Type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("Type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("Type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("Type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("Type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
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