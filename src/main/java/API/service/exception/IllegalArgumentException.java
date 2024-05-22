package API.service.exception;

import java.io.Serial;

public class IllegalArgumentException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	public IllegalArgumentException(String msg) {
		super(msg);
	}
}
