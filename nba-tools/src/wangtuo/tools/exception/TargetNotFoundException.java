package wangtuo.tools.exception;

public class TargetNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TargetNotFoundException() {
		super("can not find target");
	}
}
