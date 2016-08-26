
package com.tianyalan.common.port.adapter.rest;

import java.io.Serializable;

public class RESTResponse<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private boolean isSuccess = true;   
	
	private String errorMsg;     
	
	private T returnObject; 

	public RESTResponse(){}
	
	public RESTResponse(T returnObject) {
		this.returnObject = returnObject;
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(T returnObject) {
		this.returnObject = returnObject;
	}
	
}
