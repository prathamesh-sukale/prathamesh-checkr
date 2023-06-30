package com.zemoso.checkr.app;

import com.zemoso.checkr.exception.*;
import com.zemoso.checkr.model.base.ApiResponse;
import com.zemoso.checkr.model.base.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(CandidateNotFoundException exception, WebRequest webRequest) {
        ApiResponse response = new ApiResponse();
        response.setApiResult(ApiResult.failed("Requested Candidate Not Found"));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ChargeNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(ChargeNotFoundException exception, WebRequest webRequest) {
        ApiResponse response = new ApiResponse();
        response.setApiResult(ApiResult.failed("Requested Charge Not Found"));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourtSearchNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(CourtSearchNotFoundException exception, WebRequest webRequest) {
        ApiResponse response = new ApiResponse();
        response.setApiResult(ApiResult.failed("Requested Court Search Not Found"));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReportNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(ReportNotFoundException exception, WebRequest webRequest) {
        ApiResponse response = new ApiResponse();
        response.setApiResult(ApiResult.failed("Requested Report Not Found"));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(UserNotFoundException exception, WebRequest webRequest) {
        ApiResponse response = new ApiResponse();
        response.setApiResult(ApiResult.failed("Requested User Not Found"));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
