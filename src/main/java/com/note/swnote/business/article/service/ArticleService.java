package com.note.swnote.business.article.service;

import com.note.swnote.domain.Article;
import com.note.swnote.dto.request.article.ArticleRequest;
import com.note.swnote.dto.response.article.ArticleByCategoryPagingResponse;
import com.note.swnote.dto.response.article.ArticlePagingResponse;
import com.note.swnote.dto.response.article.ArticleResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ArticleService {

    Long registArticle(ArticleRequest request);

    ArticleResponse getArticle(Long articleSeq);

    ArticlePagingResponse getArticleList(Pageable pageable);

    ArticleByCategoryPagingResponse getArticleByCategory(Long categorySeq, Pageable pageable);
}
