package com.note.swnote.exception.cateogry;


public class ParentNotFoundException extends CategoryException {

    private static final String MESSAGE = "상위 카테고리가 존재하지 않습니다.";
    private static final int STATUS_CODE = 404;


    public ParentNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int statusCode() {
        return STATUS_CODE;
    }

}
