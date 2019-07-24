package com.fast.base.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MOrder implements Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
    private Integer id;

    private String no;

    private Integer kind;

    private Integer source;

    private Integer vipid;

    private Integer quantity;

    private BigDecimal amount;

    private BigDecimal baseamount;

    private BigDecimal saleamount;

    private BigDecimal discount;

    private BigDecimal discountmoney;

    private BigDecimal deposit;

    private Integer point;

    private Integer pointrate;

    private BigDecimal pointmoney;

    private Integer couponid;

    private BigDecimal couponmoney;

    private BigDecimal paymoney;

    private BigDecimal freight;

    private Date paytime;

    private String wechatpayno;

    private Byte paystatus;

    private Byte deliverytype;

    private String receiver;

    private String receiverphone;

    private String receiverprovince;

    private String receivercity;

    private String receivercounty;

    private Integer logisticsid;

    private String logisticsno;

    private Integer delivererdepartmentid;

    private String deliverer;

    private Date deliverertime;

    private Date receivertime;

    private String memo;

    private String remark;

    private Date createtime;

    private String creator;

    private Date modifytime;

    private String modifier;

    private Date updatedtime;

    private Byte useflag;

    private Byte status;

    private Integer miniprogramid;

    private Integer publicplatformid;

    private Byte retuenpaystatus;

    private String receiveraddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getVipid() {
        return vipid;
    }

    public void setVipid(Integer vipid) {
        this.vipid = vipid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBaseamount() {
        return baseamount;
    }

    public void setBaseamount(BigDecimal baseamount) {
        this.baseamount = baseamount;
    }

    public BigDecimal getSaleamount() {
        return saleamount;
    }

    public void setSaleamount(BigDecimal saleamount) {
        this.saleamount = saleamount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getDiscountmoney() {
        return discountmoney;
    }

    public void setDiscountmoney(BigDecimal discountmoney) {
        this.discountmoney = discountmoney;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getPointrate() {
        return pointrate;
    }

    public void setPointrate(Integer pointrate) {
        this.pointrate = pointrate;
    }

    public BigDecimal getPointmoney() {
        return pointmoney;
    }

    public void setPointmoney(BigDecimal pointmoney) {
        this.pointmoney = pointmoney;
    }

    public Integer getCouponid() {
        return couponid;
    }

    public void setCouponid(Integer couponid) {
        this.couponid = couponid;
    }

    public BigDecimal getCouponmoney() {
        return couponmoney;
    }

    public void setCouponmoney(BigDecimal couponmoney) {
        this.couponmoney = couponmoney;
    }

    public BigDecimal getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(BigDecimal paymoney) {
        this.paymoney = paymoney;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getWechatpayno() {
        return wechatpayno;
    }

    public void setWechatpayno(String wechatpayno) {
        this.wechatpayno = wechatpayno;
    }

    public Byte getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(Byte paystatus) {
        this.paystatus = paystatus;
    }

    public Byte getDeliverytype() {
        return deliverytype;
    }

    public void setDeliverytype(Byte deliverytype) {
        this.deliverytype = deliverytype;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverphone() {
        return receiverphone;
    }

    public void setReceiverphone(String receiverphone) {
        this.receiverphone = receiverphone;
    }

    public String getReceiverprovince() {
        return receiverprovince;
    }

    public void setReceiverprovince(String receiverprovince) {
        this.receiverprovince = receiverprovince;
    }

    public String getReceivercity() {
        return receivercity;
    }

    public void setReceivercity(String receivercity) {
        this.receivercity = receivercity;
    }

    public String getReceivercounty() {
        return receivercounty;
    }

    public void setReceivercounty(String receivercounty) {
        this.receivercounty = receivercounty;
    }

    public Integer getLogisticsid() {
        return logisticsid;
    }

    public void setLogisticsid(Integer logisticsid) {
        this.logisticsid = logisticsid;
    }

    public String getLogisticsno() {
        return logisticsno;
    }

    public void setLogisticsno(String logisticsno) {
        this.logisticsno = logisticsno;
    }

    public Integer getDelivererdepartmentid() {
        return delivererdepartmentid;
    }

    public void setDelivererdepartmentid(Integer delivererdepartmentid) {
        this.delivererdepartmentid = delivererdepartmentid;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public Date getDeliverertime() {
        return deliverertime;
    }

    public void setDeliverertime(Date deliverertime) {
        this.deliverertime = deliverertime;
    }

    public Date getReceivertime() {
        return receivertime;
    }

    public void setReceivertime(Date receivertime) {
        this.receivertime = receivertime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getMiniprogramid() {
        return miniprogramid;
    }

    public void setMiniprogramid(Integer miniprogramid) {
        this.miniprogramid = miniprogramid;
    }

    public Integer getPublicplatformid() {
        return publicplatformid;
    }

    public void setPublicplatformid(Integer publicplatformid) {
        this.publicplatformid = publicplatformid;
    }

    public Byte getRetuenpaystatus() {
        return retuenpaystatus;
    }

    public void setRetuenpaystatus(Byte retuenpaystatus) {
        this.retuenpaystatus = retuenpaystatus;
    }

    public String getReceiveraddress() {
        return receiveraddress;
    }

    public void setReceiveraddress(String receiveraddress) {
        this.receiveraddress = receiveraddress;
    }
}