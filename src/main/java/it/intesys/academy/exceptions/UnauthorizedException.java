package it.intesys.academy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.Instant;

public class UnauthorizedException extends ErrorResponseException {

        public UnauthorizedException(String message) {
            super(HttpStatus.UNAUTHORIZED, asProblemDetail(message), null);
        }

        private static ProblemDetail asProblemDetail(String message) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, message);
            problemDetail.setTitle("Unauthorized error");
            problemDetail.setType(URI.create("https://localhost:8080/errors/unauthorized"));
            problemDetail.setProperty("timestamp", Instant.now());
            return problemDetail;
        }
}
