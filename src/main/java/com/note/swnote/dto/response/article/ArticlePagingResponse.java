package com.note.swnote.dto.response.article;


import com.note.swnote.domain.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class ArticlePagingResponse {

    private boolean hasNext;

    private boolean first;

    private int number;
    private boolean last;
    private int numberOfElements;
    private List<ArticleResponse> articleResponses;


    public static ArticlePagingResponse create(Slice<Article> articleSlice) {
        ArticlePagingResponse response = new ArticlePagingResponse(articleSlice);
        return response;
    }

    public ArticlePagingResponse(Slice<Article> articles) {

        this.hasNext = articles.hasNext();
        this.numberOfElements = articles.getNumberOfElements();
        this.first = articles.isFirst();
        this.last = articles.isLast();
        this.number = articles.getNumber();


        List<Article> articleList = articles.getContent();
        this.articleResponses = articleList.stream()
                .map(a -> a.toResponseEntity())
                .collect(Collectors.toList());
    }
}
