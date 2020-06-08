package com.opentag.bookstore.userservice.controller.errorhandle;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opentag.bookstore.userservice.exception.UserDoesNotExistException;
import com.opentag.bookstore.userservice.exception.IncorrectUserNamePasswordException;
import com.opentag.bookstore.userservice.exception.UserAlreadyExistsException;

import springfox.documentation.annotations.ApiIgnore;

/**
 * Error handler class for all Rest controllers in the application.
 */
@ApiIgnore
@ControllerAdvice
public class ControllerValidationErrorsHandler {

	/**
	 * Exception Handler in case of MethodArgumentNotValidException thrown by the validation annotations like Max, Min and so on.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<List<SimpleFieldError>> processNotValidArguments(MethodArgumentNotValidException e) {
		List<SimpleFieldError> messages = e.getBindingResult().getFieldErrors().stream()
				.map(cv -> new SimpleFieldError(cv.getField(), cv.getDefaultMessage()))
				.collect(Collectors.toList());
		return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Exception Handler in case of ConstraintViolationException thrown by the validation annotations like Max, Min and so on.
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ResponseEntity<List<SimpleFieldError>> processConstraintViolations(ConstraintViolationException e) {
		List<SimpleFieldError> messages = e.getConstraintViolations().stream()
				.map(cv -> new SimpleFieldError(cv.getPropertyPath().toString(), cv.getMessage()))
				.collect(Collectors.toList());
		return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Exception Handler in case of UserDoesNotExistException thrown by the UserService.
	 */
	@ExceptionHandler(UserDoesNotExistException.class)
	@ResponseBody
	public ResponseEntity<String> processUserNotFound(UserDoesNotExistException e) {
		return new ResponseEntity<>("Specified user does not exist!", HttpStatus.NOT_FOUND);
	}

	/**
	 * Exception Handler in case of UserAlreadyExistsException thrown by the UserService.
	 */
	@ExceptionHandler(UserAlreadyExistsException.class)
	@ResponseBody
	public ResponseEntity<String> processUserNotFound(UserAlreadyExistsException e) {
		return new ResponseEntity<>("Specified user already exists!", HttpStatus.CONFLICT);
	}

	/**
	 * Exception Handler in case of IncorrectUserNamePasswordException thrown by the LoginService.
	 */
	@ExceptionHandler(IncorrectUserNamePasswordException.class)
	@ResponseBody
	public ResponseEntity<String> processUserNotFound(IncorrectUserNamePasswordException e) {
		return new ResponseEntity<>("Invalid user name or password!", HttpStatus.UNAUTHORIZED);
	}

}