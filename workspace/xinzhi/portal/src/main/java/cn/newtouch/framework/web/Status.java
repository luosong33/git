package cn.newtouch.framework.web;

public class Status {

	public static Status ok() {
		Status status = new Status();
		status.ok = true;
		return status;
	}

	public static Status ok(Object data) {
		Status status = new Status();
		status.ok = true;
		status.data = data;
		return status;
	}

	public static Status fail(String msg) {
		Status status = new Status();
		status.msg = msg;
		return status;
	}

	private boolean ok = false;
	private String msg;
	private Object data;

	private Status() {
	}

	public boolean isOk() {
		return ok;
	}

	public String getMsg() {
		return msg;
	}
	
	public Object getData() {
		return data;
	}

}
