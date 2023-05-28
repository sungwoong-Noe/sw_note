package com.note.swnote.business.article.service;

import com.note.swnote.business.article.repository.ArticleRepository;
import com.note.swnote.business.category.repository.CategoryRepository;
import com.note.swnote.domain.Article;
import com.note.swnote.domain.Category;
import com.note.swnote.dto.request.article.ArticleRequest;
import com.note.swnote.dto.response.article.ArticlePagingResponse;
import com.note.swnote.dto.response.article.ArticleResponse;
import com.note.swnote.exception.article.ArticleNotFound;
import com.note.swnote.exception.cateogry.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "article")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Long registArticle(ArticleRequest request) {

        Long categoryId = request.getCategoryId();

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException());

        Article article = request.toEntity(category);

        articleRepository.save(article);

        return article.getId();
    }

    @Override
    public ArticleResponse getArticle(Long articleSeq) {

        Article article = articleRepository.findById(articleSeq)
                        .orElseThrow(ArticleNotFound::new);

        return article.toResponseEntity();
    }


    @Override
    public ArticlePagingResponse getArticleList(Pageable pageable) {

        Slice<Article> slice = articleRepository.findAll(pageable);

        return ArticlePagingResponse.create(slice);
    }
}
