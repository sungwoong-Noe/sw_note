package com.note.swnote.exception.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.swnote.dto.response.ErrorResponse;
import com.note.swnote.exception.article.BlogException;
import com.note.swnote.exception.cateogry.CategoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {

    private final ObjectMapper mapper;

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {

        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
    }


    @ExceptionHandler(BlogException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String blogException(BlogException e, Model model) {

        int statusCode = e.statusCode();

        ErrorResponse response = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        log.info("error =========> {} : {}", response.getCode(), response.getMessage());

        model.addAttribute("errorResponse", response);
        return "error/error_404";
    }

    @ResponseBody
    @ExceptionHandler(CategoryException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String categoryException(CategoryException e) throws JsonProcessingException {
        int statusCode = e.statusCode();

        ErrorResponse response = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        return mapper.writeValueAsString(response);
    }
}
