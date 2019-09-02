package com.fast.base.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "m_goodsingroup")
public class MGoodsingroup implements Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Id
    private Integer id;

    private Integer goodsid;

    private Integer groupingid;

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

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getGroupingid() {
        return groupingid;
    }

    public void setGroupingid(Integer groupingid) {
        this.groupingid = groupingid;
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