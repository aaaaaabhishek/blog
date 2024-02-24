package com.blog.exception;

import com.blog.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {//acts like catch block
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails>resourceNotFoundException(
        ResourceNotFoundException exception,
        WebRequest webRequest
    ){
        ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails>handleGlobalException(
            Exception exception,
            WebRequest webRequest
    ){
        ErrorDetails errorDetails=new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
