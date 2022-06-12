package com.rohlik.orders.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rohlik.orders.dto.ErrorResponseObject;
import com.rohlik.orders.dto.ErrorResponseObjectWithDetails;
import com.rohlik.orders.dto.ValidationErrorResponse;

public class RohlikRootController
{
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ValidationErrorResponse> handleBindingErrors(MethodArgumentNotValidException ex)
    {
        var response = new ValidationErrorResponse();
        response.error(new ErrorResponseObjectWithDetails());
        var bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        fieldErrors.forEach(fieldError ->
        {
            ErrorResponseObjectWithDetails details = new ErrorResponseObjectWithDetails()
                .code(fieldError.getCode())
                .target(fieldError.getField())
                .message(fieldError.getDefaultMessage());
            response.getError().addDetailsItem(details);
        });

        response.getError().message("Validation Error");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseObject> handleRuntimeException(RuntimeException e)
    {
        var response = new ErrorResponseObject();
        var sb = new StringBuilder();
        sb.append(e.getMessage());
        appendCause(sb, e.getCause());
        response.setMessage(sb.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(response);
    }


    public void appendCause(@NotNull StringBuilder sb, Throwable cause) {
        if (cause != null) {
            sb.append("\n[CAUSE]: ").append(cause.getMessage());
            appendCause(sb, cause.getCause());
        }
    }
}
