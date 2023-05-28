package com.note.swnote.business.article;

import com.note.swnote.business.article.service.ArticleService;
import com.note.swnote.domain.Article;
import com.note.swnote.dto.request.article.ArticleRequest;
import com.note.swnote.dto.response.article.ArticlePagingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleService articleService;

    @PostMapping("/article/regist")
    public Long regist(@RequestBody @Valid ArticleRequest request) {

        Long articleId = articleService.registArticle(request);

        return articleId;
    }

    @GetMapping("/article/next")
    public ArticlePagingResponse getArticleList(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        ArticlePagingResponse response = articleService.getArticleList(pageable);

        return response;
    }
}