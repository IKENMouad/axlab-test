package com.axlab.test.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.axlab.test.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ FlightNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleFlightNotFound(FlightNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(value = { AuthorizationDeniedException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public @ResponseBody ErrorResponse handleRessourceAccessException(AuthorizationDeniedException ex) {
		return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleValidationException(MethodArgumentNotValidException ex) {
		Map<String, Object> errMaps = new HashMap<>();
		errMaps.put("statusCode", HttpStatus.BAD_REQUEST.value());
		errMaps.put("message", "Validation error");
		ex.getBindingResult().getFieldErrors().forEach(el -> errMaps.put(el.getField(), el.getDefaultMessage()));
		return errMaps;
	}

	@ExceptionHandler({ RuntimeException.class })
	public ErrorResponse handleRuntimeException(RuntimeException ex) {
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}
}
