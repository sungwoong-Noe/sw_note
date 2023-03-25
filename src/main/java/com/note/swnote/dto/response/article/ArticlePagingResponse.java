package com.note.swnote.dto.response.article;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ArticlePagingResponse {

    private List<ArticleResponse> articleResponses;

    private int totalPages;

    private long totalElements;


    @Builder
    public ArticlePagingResponse(List<ArticleResponse> articleResponses, int totalPages, long totalElements) {
        this.articleResponses = articleResponses;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }
}
