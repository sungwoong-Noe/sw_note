package com.note.swnote.exception.cateogry;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class CategoryException extends RuntimeException{

    private final Map<String, String> validation = new HashMap<>();

    public CategoryException(String message) {
        super(message);
    }

    public CategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int statusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
