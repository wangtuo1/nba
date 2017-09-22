package wangtuo.tools.exception;

public class RequestBodyInvalidException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public RequestBodyInvalidException(String message) {
		super(message);
	}
}
