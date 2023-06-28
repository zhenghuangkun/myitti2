package com.awslabplatform_admin.entity;

import java.io.Serializable;

/**
 * 消息返回实体
 * @author weixin
 * @version 2018-3-21
 */
public class Result implements Serializable{
	
		private static final long serialVersionUID = 1L;
		
		/*结果标志*/
		Boolean resultFlag;
		/*返回码*/  
	    private int code;  
	    /*返回信息提示*/  
	    private String message;  
	    /*返回的数据*/  
	    private Object data;  
	      

		public Result(){}  
	      
	    public Result(Boolean resultFlag, int code, String message, Object data) {  
	    	this.resultFlag = resultFlag;
	        this.code = code;  
	        this.message = message;  
	        this.data = data;  
	    }  
	    
	    public Result(Boolean resultFlag, int code, String message) { 
	    	this.code = code;  
	    	this.resultFlag = resultFlag;
	        this.message = message;  
	    }
	    
	    public Result(Boolean resultFlag, String message, Object data) {  
	    	this.resultFlag = resultFlag;
	        this.message = message; 
	        this.data = data;  
	    }
	    
	    public Result(Boolean resultFlag, String message) {  
	    	this.resultFlag = resultFlag;
	        this.message = message;  
	    }
	    
	      
	    public Boolean getResultFlag() {
			return resultFlag;
		}

		public void setResultFlag(Boolean resultFlag) {
			this.resultFlag = resultFlag;
		}

		@Override  
	    public String toString() {  
	        return "Result [resultFlag="+ this.resultFlag +",code=" + this.code + ", message=" + this.message + ", data=" + this.data + "]";  
	    }  
	      
	    public int getCode() {  
	        return code;  
	    }  
	    public void setCode(int code) {  
	        this.code = code;  
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
