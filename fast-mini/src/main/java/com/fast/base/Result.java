package com.fast.base;

import java.io.Serializable;

/**
 * 接口结果类
 * @author gimwan
 *
 */
public class Result implements Serializable {
	
	private static final long serialVersionUID = -2318858336536299973L;

	// 0:成功，-1:未知异常错误,其它数值为指定明确原因的错误，可显示	
	private Integer errcode = -1;
	private Integer id;
	private String message;
	private Object data = null;
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
