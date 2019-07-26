package com.fast.base.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MViptype implements Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
    private Integer id;

    private String code;

    private String name;

    private Integer grade;

    private Byte defaultflag;

    private Date createtime;

    private String creator;

    private Date modifytime;

    private String modifier;

    private Date updatedtime;

    private Byte useflag;

    private String memo;

    private BigDecimal discount;

    private BigDecimal birthdaydiscount;

    private Integer pointrate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Byte getDefaultflag() {
        return defaultflag;
    }

    public void setDefaultflag(Byte defaultflag) {
        this.defaultflag = defaultflag;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getUpdatedtime() {
        return updatedtime;
    }

    public void setUpdatedtime(Date updatedtime) {
        this.updatedtime = updatedtime;
    }

    public Byte getUseflag() {
        return useflag;
    }

    public void setUseflag(Byte useflag) {
        this.useflag = useflag;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getBirthdaydiscount() {
        return birthdaydiscount;
    }

    public void setBirthdaydiscount(BigDecimal birthdaydiscount) {
        this.birthdaydiscount = birthdaydiscount;
    }

    public Integer getPointrate() {
        return pointrate;
    }

    public void setPointrate(Integer pointrate) {
        this.pointrate = pointrate;
    }
}