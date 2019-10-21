package com.test.catalogue.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.annotation.JsonInclude;

@RestControllerAdvice
public class ApiExceptionHandler {
   
	@SuppressWarnings("rawtypes")
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handle(ResourceNotFoundException e) {
		ApiError error = new ApiError();
        error.setCode("404");
        error.setMessage(e.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
	
    public static class ApiError {

        @JsonInclude(JsonInclude.Include.NON_NULL) private String code;

        private String message;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    public static class ApiResponse {

        private List<ApiError> errors = new ArrayList<>();

        public List<ApiError> getErrors() {
            return errors;
        }

        public void setErrors(List<ApiError> errors) {
            this.errors = errors;
        }

        public void addError(ApiError error) {
            this.errors.add(error);
        }

    }
}
