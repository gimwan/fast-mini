package com.fast.base.data.entity;

import java.util.Date;

public class MMicropageset {
    private Integer id;

    private Integer micropageid;

    private Integer kind;

    private Integer index;

    private Byte showname;

    private Byte showprice;

    private Byte imagestyle;

    private Byte orderby;

    private String memo;

    private Date createtime;

    private String creator;

    private Date modifytime;

    private String modifier;

    private Date updatedtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMicropageid() {
        return micropageid;
    }

    public void setMicropageid(Integer micropageid) {
        this.micropageid = micropageid;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Byte getShowname() {
        return showname;
    }

    public void setShowname(Byte showname) {
        this.showname = showname;
    }

    public Byte getShowprice() {
        return showprice;
    }

    public void setShowprice(Byte showprice) {
        this.showprice = showprice;
    }

    public Byte getImagestyle() {
        return imagestyle;
    }

    public void setImagestyle(Byte imagestyle) {
        this.imagestyle = imagestyle;
    }

    public Byte getOrderby() {
        return orderby;
    }

    public void setOrderby(Byte orderby) {
        this.orderby = orderby;
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
}