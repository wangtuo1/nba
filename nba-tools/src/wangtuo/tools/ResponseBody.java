package wangtuo.tools;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class ResponseBody<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonInclude(Include.NON_NULL)
	private String message;

	@JsonInclude(Include.NON_NULL)
	private T data;

	public ResponseBody(String message, T data) {
		super();
		this.message = message;
		this.data = data;
	}

	public ResponseBody(T o) {
		if(o instanceof String) {
			this.message=(String)o;
		}
		else {
			this.data=o;
		}
	}
	
	public ResponseBody() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResponseBody [message=" + message + ", data=" + data + "]";
	}
}
