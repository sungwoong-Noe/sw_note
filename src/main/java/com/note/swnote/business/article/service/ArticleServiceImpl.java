package com.note.swnote.business.article.service;

import com.note.swnote.business.article.repository.ArticleRepository;
import com.note.swnote.domain.Article;
import com.note.swnote.dto.request.ArticleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "article")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    @Transactional
    @Override
    public Long registArticle(ArticleRequest request) {

        Article article = request.toEntity();

        articleRepository.save(article);

        return article.getId();
    }
}
