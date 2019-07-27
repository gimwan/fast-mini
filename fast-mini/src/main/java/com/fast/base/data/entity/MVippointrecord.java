package com.fast.base.data.entity;

import java.io.Serializable;
import java.util.Date;

public class MVippointrecord implements Serializable {
	
	private static final long serialVersionUID = 71148004875517941L;
	
    private Integer id;

    private Integer vipid;

    private Integer point;

    private Integer newpoint;

    private Byte type;

    private Date updatedtime;

    private String memo;

    private Integer refid;

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

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getNewpoint() {
        return newpoint;
    }

    public void setNewpoint(Integer newpoint) {
        this.newpoint = newpoint;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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

    public Integer getRefid() {
        return refid;
    }

    public void setRefid(Integer refid) {
        this.refid = refid;
    }
}