package API.service.exception;

import java.io.Serial;
/*
Exceção personalisado para erros em banco de dados
*/
public class DatabaseException extends RuntimeException{
	@Serial
	private static final long serialVersionUID = 1L;

	public DatabaseException (String msg){
		super(msg);
	}
}