package it.intesys.academy.exceptions;

import java.net.URI;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class NotFoundException extends ErrorResponseException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, asProblemDetail(message), null);
    }

    private static ProblemDetail asProblemDetail(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
        problemDetail.setTitle("Not found error");
        problemDetail.setType(URI.create("http://localhost:8080/errors/not-found-error"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
