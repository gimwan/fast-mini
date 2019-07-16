package com.fast.base.data.entity;

import java.io.Serializable;
import java.util.Date;

public class MGoods implements Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
    private Integer id;

    private String code;

    private String name;

    private Integer brandid;

    private Byte kind;

    private Long purchaseprice;

    private Long baseprice;

    private Long price;

    private Byte showcolor;

    private Byte showpattern;

    private Byte showsize;

    private Byte shiptype;

    private Byte onsale;

    private Byte onlyshow;

    private String photourl;

    private Integer exchangepoint;

    private Date onsaletime;

    private String onsaleer;

    private Long pointtimes;

    private String memo;

    private Date createtime;

    private String creator;

    private Date modifytime;

    private String modifier;

    private Date updatedtime;

    private Byte useflag;

    private String describe;

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

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public Byte getKind() {
        return kind;
    }

    public void setKind(Byte kind) {
        this.kind = kind;
    }

    public Long getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(Long purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public Long getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(Long baseprice) {
        this.baseprice = baseprice;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Byte getShowcolor() {
        return showcolor;
    }

    public void setShowcolor(Byte showcolor) {
        this.showcolor = showcolor;
    }

    public Byte getShowpattern() {
        return showpattern;
    }

    public void setShowpattern(Byte showpattern) {
        this.showpattern = showpattern;
    }

    public Byte getShowsize() {
        return showsize;
    }

    public void setShowsize(Byte showsize) {
        this.showsize = showsize;
    }

    public Byte getShiptype() {
        return shiptype;
    }

    public void setShiptype(Byte shiptype) {
        this.shiptype = shiptype;
    }

    public Byte getOnsale() {
        return onsale;
    }

    public void setOnsale(Byte onsale) {
        this.onsale = onsale;
    }

    public Byte getOnlyshow() {
        return onlyshow;
    }

    public void setOnlyshow(Byte onlyshow) {
        this.onlyshow = onlyshow;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public Integer getExchangepoint() {
        return exchangepoint;
    }

    public void setExchangepoint(Integer exchangepoint) {
        this.exchangepoint = exchangepoint;
    }

    public Date getOnsaletime() {
        return onsaletime;
    }

    public void setOnsaletime(Date onsaletime) {
        this.onsaletime = onsaletime;
    }

    public String getOnsaleer() {
        return onsaleer;
    }

    public void setOnsaleer(String onsaleer) {
        this.onsaleer = onsaleer;
    }

    public Long getPointtimes() {
        return pointtimes;
    }

    public void setPointtimes(Long pointtimes) {
        this.pointtimes = pointtimes;
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

    public Byte getUseflag() {
        return useflag;
    }

    public void setUseflag(Byte useflag) {
        this.useflag = useflag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}