package com.note.swnote.dto.response.article;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ArticleResponse {

    private Long articleSeq;
    private String title;
    private String content;

    @Builder
    public ArticleResponse(Long articleSeq, String title, String content) {
        this.articleSeq = articleSeq;
        this.title = title;
        this.content = content;
    }
}
