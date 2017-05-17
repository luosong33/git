package org.jumao.bi.utis.exceptions;


import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;


public class FailedOperationException extends Exception {

	private static final long serialVersionUID = -695496421961156401L;
	private final Logger logger = Logger.getLogger(this.getClass());

	private String msg;
	
	private String code = "1000";
	
	public FailedOperationException(String msg) {
		super(msg);
		
		this.msg = msg;
	}
	
	public FailedOperationException(String code, String msg) {
		super(msg);
		
		this.code = code;
		this.msg = msg;
	}
	
	public FailedOperationException(int code, String msg) {
		super(msg);
		
		this.code = String.valueOf(code);
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}
	
	public int getCodeInt() {
		return Integer.parseInt(code);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFailedResponse() {
		JSONObject result = new JSONObject();
		try {
			result.put("code", this.code);
			result.put("msg", msg);
		} catch (Exception e) {
			logger.error("", e);
		}
		return result.toString();
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
