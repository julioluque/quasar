package ar.com.jluque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.com.jluque.exception.custom.BadRequestCustomException;
import ar.com.jluque.exception.custom.ConflictCustomException;
import ar.com.jluque.exception.custom.ForbiddenCustomException;
import ar.com.jluque.exception.custom.NotFoundCustomException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GeneralHandlerException {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class, IllegalArgumentException.class })
	@ResponseBody
	public ErrorMessage unexpectedHandler(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NotFoundCustomException.class })
	@ResponseBody
	public ErrorMessage notFoundHandler(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ BadRequestCustomException.class,
			org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
			org.springframework.web.HttpRequestMethodNotSupportedException.class,
			org.springframework.web.bind.MethodArgumentNotValidException.class,
			org.springframework.web.bind.MissingRequestHeaderException.class,
			org.springframework.web.bind.MissingServletRequestParameterException.class,
			org.springframework.http.converter.HttpMessageNotReadableException.class })
	@ResponseBody
	public ErrorMessage badRequestHandler(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({ ForbiddenCustomException.class })
	@ResponseBody
	public ErrorMessage forbiddenHandler(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({ ConflictCustomException.class, org.springframework.dao.DuplicateKeyException.class,
			org.springframework.dao.DataIntegrityViolationException.class })
	@ResponseBody
	public ErrorMessage conflictHandler(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

}
