package com.client.wsRasmooplus.Exception.handler;

import com.client.wsRasmooplus.Exception.BadRequestException;
import com.client.wsRasmooplus.Exception.BusinessException;
import com.client.wsRasmooplus.Exception.EmailFail;
import com.client.wsRasmooplus.Exception.NotFoundException;
import com.client.wsRasmooplus.dto.ErrorResponseDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorResponseDto notFoundException(NotFoundException ex) {
        return ErrorResponseDto.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponseDto badRequest(BadRequestException ex) {

        return ErrorResponseDto.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorResponseDto businessException(BusinessException ex) {

        return ErrorResponseDto.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(HttpStatus.CONFLICT.value())
                .build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var erros = new HashMap<String, String>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            erros.put(fieldName, errorMessage);
        });
        return erros;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponseDto dataIntegrityViolationException(DataIntegrityViolationException ex) {

        return ErrorResponseDto.builder()
                .message(ex.getCause().getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(EmailFail.class)
    ErrorResponseDto emailFail(EmailFail emailFail) {

        return ErrorResponseDto.builder()
                .message(emailFail.getMessage())
                .build();
    }

}
