package com.tsolakp.samplespringmvcrest.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonIgnore;

@ControllerAdvice
public class ExceptionResolver{
        
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody Error handleException(Exception e) {
        return new Error().message( e.getMessage() );     
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody Error handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new Error().message( e.getMessage() );       
    }
    
    public class Error {
        String message;
        String cause;
        
        public String getMessage() {
            return message;
        }

        public String getCause() {
            return cause;
        }
        
        @JsonIgnore
        public Error message(String message){
            this.message = message;
            return this;
        }
        
        @JsonIgnore
        public Error error(String cause){
            this.cause = cause;
            return this;
        }
    }
}
