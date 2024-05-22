package API.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import API.service.exception.DatabaseException;
import API.service.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice // componente de tratamento de exceções global
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class) // erro capturado, sera enviado para esse metodo
	public ResponseEntity<StandardError> resourcenotFound(ResourceNotFoundException exeception, HttpServletRequest request) { //  HttpServletRequest Ele é usado para processar a solicitação do cliente no servidor.
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND; //retorna o erro HTTP"404" indicando que nao foi encontrado
		StandardError standardError = new StandardError(Instant.now(), status.value(), error, exeception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> Database(DatabaseException exeception, HttpServletRequest request) {
		String error = "Data base error";
		HttpStatus status = HttpStatus.BAD_REQUEST;// retorna o erro 400 indicando que foi um erro da solicitação do client
		StandardError standardError = new StandardError(Instant.now(), status.value(), error, exeception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException exception, HttpServletRequest request) {
		String error = "Illegal argument error";
		HttpStatus status = HttpStatus.BAD_REQUEST; // retorna o erro 400 indicando que foi um erro da solicitação do client
		StandardError standardError = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(standardError);
	}


}
