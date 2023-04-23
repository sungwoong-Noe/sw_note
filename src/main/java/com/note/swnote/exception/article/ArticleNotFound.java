package com.note.swnote.exception.article;

public class ArticleNotFound extends BlogException {

    private static final String MESSAGE = "존재하지 않는 글입니다.";

    private static final int STATUS_CODE = 404;

    public ArticleNotFound() {
        super(MESSAGE);
    }

    public ArticleNotFound(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int statusCode() {
        return STATUS_CODE;
    }
}
