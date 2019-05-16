package com.fast.base.data.entity;

import java.util.Date;

public class MMicropage {
    private Integer id;

    private String code;

    private String name;

    private Byte homeflag;

    private String memo;

    private Byte useflag;

    private Date createtime;

    private String creator;

    private Date modifytime;

    private String modifier;

    private Date updatedtime;

    private Byte publishflag;

    private String publisher;

    private Date publishtime;

    private Long pageview;

    private Long vipview;

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

    public Byte getHomeflag() {
        return homeflag;
    }

    public void setHomeflag(Byte homeflag) {
        this.homeflag = homeflag;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Byte getUseflag() {
        return useflag;
    }

    public void setUseflag(Byte useflag) {
        this.useflag = useflag;
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

    public Byte getPublishflag() {
        return publishflag;
    }

    public void setPublishflag(Byte publishflag) {
        this.publishflag = publishflag;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public Long getPageview() {
        return pageview;
    }

    public void setPageview(Long pageview) {
        this.pageview = pageview;
    }

    public Long getVipview() {
        return vipview;
    }

    public void setVipview(Long vipview) {
        this.vipview = vipview;
    }
}