package wangtuo.tools.exception;

public class ResultNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResultNotFoundException() {
		super("can not find result");
	}
}
