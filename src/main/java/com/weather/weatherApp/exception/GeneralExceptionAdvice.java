package com.weather.weatherApp.exception;


import jakarta.validation.ConstraintViolationException;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GeneralExceptionAdvice  {

    private static final Logger logger = LoggerFactory.getLogger(GeneralExceptionAdvice.class);

    // MethodArgumentNotValidException için özel bir işlem
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            } else {
                String errorMessage = error.getDefaultMessage();
                errors.put("general", errorMessage);
            }
        });
        logger.info(String.format("Api validation error: %s", errors));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(ConstraintViolationException ex, RedirectAttributes redirectAttributes) {
        String errorMessage = "Boş bırakmayınız";
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage); // Hata mesajını flash attribute olarak ekleyin


        return "redirect:/weather";
    }

    @ExceptionHandler({RuntimeException.class,})
    public String handle(RuntimeException runtimeException,RedirectAttributes redirectAttributes){

        String errorMessage = "Şehir bilgisi bulunamadı";
        redirectAttributes.addFlashAttribute("errorMessage", errorMessage); // Hata mesajını flash attribute olarak ekleyin


        return "redirect:/weather";
    }


}
