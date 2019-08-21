package com.fast.base.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "m_vipcart")
public class MVipcart implements Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
	@Id
    private Integer id;

    private Integer vipid;

    private Integer goodsid;

    private Integer colorid;

    private Integer patternid;

    private Integer sizeid;

    private Integer quantity;

    private Integer recommenderid;

    private Integer publicplatformid;

    private Byte useflag;

    private Byte type;

    private Date createtime;

    private String creator;

    private Date modifytime;

    private String modifier;

    private Date updatedtime;

    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVipid() {
        return vipid;
    }

    public void setVipid(Integer vipid) {
        this.vipid = vipid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getColorid() {
        return colorid;
    }

    public void setColorid(Integer colorid) {
        this.colorid = colorid;
    }

    public Integer getPatternid() {
        return patternid;
    }

    public void setPatternid(Integer patternid) {
        this.patternid = patternid;
    }

    public Integer getSizeid() {
        return sizeid;
    }

    public void setSizeid(Integer sizeid) {
        this.sizeid = sizeid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getRecommenderid() {
        return recommenderid;
    }

    public void setRecommenderid(Integer recommenderid) {
        this.recommenderid = recommenderid;
    }

    public Integer getPublicplatformid() {
        return publicplatformid;
    }

    public void setPublicplatformid(Integer publicplatformid) {
        this.publicplatformid = publicplatformid;
    }

    public Byte getUseflag() {
        return useflag;
    }

    public void setUseflag(Byte useflag) {
        this.useflag = useflag;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}