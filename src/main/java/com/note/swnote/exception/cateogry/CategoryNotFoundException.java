package com.note.swnote.exception.cateogry;


import java.util.Map;

public class CategoryNotFoundException extends CategoryException{


    private final static String MESSAGE = "카테고리를 찾을 수 없습니다.";
    private final static int STATUS_CODE = 404;

    public CategoryNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return STATUS_CODE;
    }

}
