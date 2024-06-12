package it.intesys.academy.exceptions;

import java.net.URI;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class BadRequestException extends ErrorResponseException {

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, asProblemDetail(message), null);
    }

    private static ProblemDetail asProblemDetail(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
        problemDetail.setTitle("Bad request error");
        problemDetail.setType(URI.create("https://localhost:8080/errors/bad-request-error"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
