package com.note.swnote.business.article.service;

import com.note.swnote.dto.request.article.ArticleRequest;
import com.note.swnote.dto.response.article.ArticleResponse;

public interface ArticleService {

    public Long registArticle(ArticleRequest request);

    public ArticleResponse getArticle(Long articleSeq);
}
