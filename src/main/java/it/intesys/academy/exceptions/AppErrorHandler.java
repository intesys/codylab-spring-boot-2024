package it.intesys.academy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class AppErrorHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(Exception.class)
  ProblemDetail handleGenericException(Exception e) {
    logger.error("An unknown error has occurred", e);
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    problemDetail.setTitle("An unknown error has occurred");
    problemDetail.setType(URI.create("https://localhost:8080/errors/internal-server-error"));
    return problemDetail;
  }
}
