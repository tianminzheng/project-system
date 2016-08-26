package com.tianyalan.common.port.adapter.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class ResultMessageBuilder {

	public static ResultMessage build() {
		return new ResultMessage();
	}

	public static ResultMessage build(Object data) {
		return new ResultMessage(data);
	}

	public static ResultMessage build(boolean success, String errMsg) {
		return new ResultMessage(success, errMsg);
	}

	public static ResultMessage build(boolean success, Object data) {
		return new ResultMessage(success, data);
	}
	
	public static ResultMessage build(boolean success, String errMsg, Object data) {
		return new ResultMessage(success, errMsg, data);
	}

	public static ResultMessage build(boolean success, String header, String errMsg, Object data) {
		return new ResultMessage(success, header, errMsg, data);
	}
	
	public static ResultMessageRaw buildRaw(String jsonStr) {
		return new ResultMessageRaw(jsonStr);
	}

	public static ResultMessageRaw buildRawWithDateFormat(Object obj, String dateFormat) {
		return new ResultMessageRaw(JSON.toJSONStringWithDateFormat(obj,
				dateFormat, SerializerFeature.DisableCircularReferenceDetect));
	}
	
	public static class ResultMessage {

		private boolean success = true;
		private String errMsg = null;
		private Object data = null;
		private String header;//消息头  , 用来区分返回信息的类型

		public ResultMessage() {
			
		}

		public ResultMessage(Object data) {
			this.data = data;
		}

		public ResultMessage(boolean success, String errMsg) {
			this.success = success;
			this.errMsg = errMsg;
		}
		
		public ResultMessage(boolean success, Object data) {
			this.success = success;
			this.data = data;
		}

		public ResultMessage(boolean success, String errMsg, Object data) {
			this.success = success;
			this.errMsg = errMsg;
			this.data = data;
		}

		public ResultMessage(boolean success,String header ,String errMsg, Object data) {
			this.success = success;
			this.errMsg = errMsg;
			this.data = data;
			this.header = header ; 
		}
		
		public boolean isSuccess() {
			return success;
		}

		public ResultMessage setSuccess(boolean success) {
			this.success = success;
			return this;
		}

		public String getErrMsg() {
			return errMsg;
		}

		public ResultMessage setErrMsg(String errMsg) {
			this.errMsg = errMsg;
			return this;
		}

		public Object getData() {
			return data;
		}

		public ResultMessage setData(Object data) {
			this.data = data;
			return this;
		}

		public String getHeader() {
			return header;
		}

		public void setHeader(String header) {
			this.header = header;
		}
		
		public String toJSONString() {
			return JSON.toJSONString(this,new SerializerFeature[] { SerializerFeature.DisableCircularReferenceDetect });
		}
	}

	public static class ResultMessageRaw {

		private String jsonStr = null;

		public ResultMessageRaw() {
			
		}

		public ResultMessageRaw(String jsonStr) {
			this.jsonStr = jsonStr;
		}

		public String getJsonStr() {
			return jsonStr;
		}

		public void setJsonStr(String jsonStr) {
			this.jsonStr = jsonStr;
		}

		public String toJSONString() {
			StringBuilder buff = new StringBuilder("{\"success\": true, \"data\": ");
			buff.append(jsonStr).append("}");
			return buff.toString();
		}
	}
}
