package it.intesys.academy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;


import java.net.URI;
import java.time.Instant;

public class BadRequestException extends ErrorResponseException {
  public BadRequestException(String message) {
    super(HttpStatus.BAD_REQUEST, asProblemDetail(message), null);
  }

  private static ProblemDetail asProblemDetail(String message) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
    problemDetail.setTitle("Bad Request error");
    problemDetail.setType(URI.create("https://localhost:8080/errors/bad-request"));
    problemDetail.setProperty("timestamp", Instant.now());
    return problemDetail;
  }
}
