package com.fast.base.data.entity;

import java.io.Serializable;
import java.util.Date;

public class MCoupon implements Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
    private Integer id;

    private String code;

    private String name;

    private Integer amount;

    private Integer enableamount;

    private Integer limitquantity;

    private Integer totalquantity;

    private Date begintime;

    private Date endtime;

    private String color;

    private Byte useflag;

    private String memo;

    private Date createtime;

    private String creator;

    private Date modifytime;

    private String modifier;

    private Date updatedtime;

    private Byte suittype;

    private Integer effectivetime;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getEnableamount() {
        return enableamount;
    }

    public void setEnableamount(Integer enableamount) {
        this.enableamount = enableamount;
    }

    public Integer getLimitquantity() {
        return limitquantity;
    }

    public void setLimitquantity(Integer limitquantity) {
        this.limitquantity = limitquantity;
    }

    public Integer getTotalquantity() {
        return totalquantity;
    }

    public void setTotalquantity(Integer totalquantity) {
        this.totalquantity = totalquantity;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Byte getSuittype() {
        return suittype;
    }

    public void setSuittype(Byte suittype) {
        this.suittype = suittype;
    }

    public Integer getEffectivetime() {
        return effectivetime;
    }

    public void setEffectivetime(Integer effectivetime) {
        this.effectivetime = effectivetime;
    }
}