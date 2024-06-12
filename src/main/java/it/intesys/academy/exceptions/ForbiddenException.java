package it.intesys.academy.exceptions;

import java.net.URI;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class ForbiddenException extends ErrorResponseException {

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, asProblemDetail(message), null);
    }

    private static ProblemDetail asProblemDetail(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, message);
        problemDetail.setTitle("Forbidden error");
        problemDetail.setType(URI.create("https://localhost:8080/errors/forbidden-error"));
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
