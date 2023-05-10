package com.sachin.usermicroservice.exceptionhandler;



import com.sachin.usermicroservice.exception.NotFoundException;
import com.sachin.usermicroservice.response.ErrorResponse;
import com.sachin.usermicroservice.response.GenericResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GenericResponse> Exception(Exception exception) {
        ErrorResponse errorResponse;
        GenericResponse genericResponse=new GenericResponse();


        if (exception instanceof NotFoundException){
            errorResponse = new ErrorResponse("NOT_FOUND_EXCEPTION",exception.getMessage());
            genericResponse.setStatus(HttpStatus.NOT_FOUND);
            genericResponse.setCode(HttpStatus.NOT_FOUND.value());
        }
        else if (exception instanceof DataIntegrityViolationException) {
            errorResponse = new ErrorResponse( "DataIntegrityViolationException",exception.getCause().getCause().getLocalizedMessage());
            genericResponse.setStatus(HttpStatus.BAD_REQUEST);
            genericResponse.setCode(HttpStatus.BAD_REQUEST.value());

        } else if (exception instanceof BadCredentialsException) {
            errorResponse = new ErrorResponse( "BAD_CREDENTIALS_EXCEPTION",exception.getMessage());
            genericResponse.setStatus(HttpStatus.UNAUTHORIZED);
            genericResponse.setCode(HttpStatus.UNAUTHORIZED.value());
        }
        else {

             errorResponse = new ErrorResponse( "Unexpected Error",exception.getMessage());
             genericResponse.setStatus(HttpStatus.BAD_REQUEST);
            genericResponse.setCode(HttpStatus.BAD_REQUEST.value());
        }

        genericResponse.setError(errorResponse);
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        GenericResponse genericResponse=new GenericResponse();
        genericResponse.setError(new ErrorResponse("Invalid arguments in JSON body",Objects.requireNonNull(exception.getFieldError()).getDefaultMessage()));
        genericResponse.setStatus(HttpStatus.BAD_REQUEST);
        genericResponse.setCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);

    }


}

