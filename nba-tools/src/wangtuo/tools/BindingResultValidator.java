package wangtuo.tools;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import wangtuo.tools.exception.RequestBodyInvalidException;

public class BindingResultValidator {
	public static void checkBindingResult(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			bindingResult.getAllErrors().forEach(e -> sb.append(e.getObjectName() + ".")
					.append(((FieldError) e).getField()).append(e.getDefaultMessage()).append("  "));
			throw new RequestBodyInvalidException(sb.toString());
		}
	}
}
