package com.note.swnote.dto.response.article;


import com.note.swnote.domain.Article;
import com.note.swnote.dto.response.category.ChildResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class ArticleByCategoryPagingResponse {
    private boolean hasNext;
    private boolean first;
    private int number;
    private boolean last;
    private int numberOfElements;
    private List<ArticleResponse> articleResponses;

    private ChildResponse category;


    public static ArticleByCategoryPagingResponse create(Slice<Article> articleSlice, ChildResponse category) {

        ArticleByCategoryPagingResponse response = new ArticleByCategoryPagingResponse(articleSlice, category);
        return response;
    }

    public ArticleByCategoryPagingResponse(Slice<Article> articles, ChildResponse category) {

        this.hasNext = articles.hasNext();
        this.numberOfElements = articles.getNumberOfElements();
        this.first = articles.isFirst();
        this.last = articles.isLast();
        this.number = articles.getNumber();
        this.category = category;

        List<Article> articleList = articles.getContent();
        this.articleResponses = articleList.stream()
                .map(a -> a.toResponseEntity())
                .collect(Collectors.toList());
    }
}
