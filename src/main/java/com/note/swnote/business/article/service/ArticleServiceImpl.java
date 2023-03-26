package com.note.swnote.business.article.service;

import com.note.swnote.business.article.repository.ArticleRepository;
import com.note.swnote.domain.Article;
import com.note.swnote.dto.request.article.ArticleRequest;
import com.note.swnote.dto.response.article.ArticlePagingResponse;
import com.note.swnote.dto.response.article.ArticleResponse;
import com.note.swnote.exception.ArticleNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ArticleResponse getArticle(Long articleSeq) {

        Article article = articleRepository.findById(articleSeq).orElseThrow(ArticleNotFound::new);

        return article.toResponseEntity();
    }


    @Override
    public Slice<Article> getArticleList(Pageable pageable) {

        Slice<Article> slice = articleRepository.findAll(pageable);

        return slice;
    }
}
