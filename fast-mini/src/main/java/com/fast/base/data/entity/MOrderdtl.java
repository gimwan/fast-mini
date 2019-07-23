package com.fast.base.data.entity;

import java.io.Serializable;
import java.util.Date;

public class MOrderdtl implements Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
    private Integer id;

    private Integer goodsid;

    private Integer colorid;

    private Integer patternid;

    private Integer sizeid;

    private Integer quantity;

    private Long price;

    private Long baseprice;

    private Long amount;

    private Long saleprice;

    private Long baseamount;

    private Long saleamount;

    private Date createtime;

    private String creator;

    private Date modifytime;

    private String modifier;

    private Date updatedtime;

    private Byte useflag;

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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(Long baseprice) {
        this.baseprice = baseprice;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(Long saleprice) {
        this.saleprice = saleprice;
    }

    public Long getBaseamount() {
        return baseamount;
    }

    public void setBaseamount(Long baseamount) {
        this.baseamount = baseamount;
    }

    public Long getSaleamount() {
        return saleamount;
    }

    public void setSaleamount(Long saleamount) {
        this.saleamount = saleamount;
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
}