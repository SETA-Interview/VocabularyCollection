package org.example.collection.vocabulary.exception;

import lombok.extern.slf4j.Slf4j;
import org.example.collection.vocabulary.model.response.ErrorResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Slf4j
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(value = {ResourceNotFoundException.class})
	protected ResponseEntity<ErrorResponse> handleResourceNotFoundException(final ResourceNotFoundException ex,
																			final WebRequest request) {
		log.error("IO Exception ", ex);
		return ResponseEntity.status(400)
							 .contentType(MediaType.APPLICATION_JSON)
							 .body(ErrorResponse.builder()
												.message(ex.getMessage())
												.path(request.getContextPath())
												.timestamp(Instant.now())
												.build());
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(value = {AuthorizationDeniedException.class})
	protected ResponseEntity<ErrorResponse> handleAuthorizationDeniedException(final AuthorizationDeniedException ex,
																			   final WebRequest request) {
		log.error("Authorization Denied Exception ", ex);
		return ResponseEntity.status(400)
							 .contentType(MediaType.APPLICATION_JSON)
							 .body(ErrorResponse.builder()
												.message(ex.getMessage())
												.path(request.getContextPath())
												.timestamp(Instant.now())
												.build());
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
	protected ResponseEntity<ErrorResponse> handleUnknownException(final Exception ex, final WebRequest request) {
		log.error("Unknown exception", ex);
		return ResponseEntity.status(500)
							 .contentType(MediaType.APPLICATION_JSON)
							 .body(ErrorResponse.builder()
												.message("Internal Server Error. " + ex.getMessage())
												.path(request.getContextPath())
												.timestamp(Instant.now())
												.build());
	}
}
