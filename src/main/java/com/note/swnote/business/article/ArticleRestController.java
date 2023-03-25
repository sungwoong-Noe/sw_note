package com.note.swnote.business.article;

import com.note.swnote.business.article.service.ArticleService;
import com.note.swnote.domain.Article;
import com.note.swnote.dto.request.article.ArticleRequest;
import com.note.swnote.dto.response.article.ArticlePagingResponse;
import com.note.swnote.dto.response.article.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleService articleService;

    @PostMapping("/article/regist")
    public Long regist(@RequestBody @Valid ArticleRequest request) {

        Long articleId = articleService.registArticle(request);

        return articleId;
    }

    @GetMapping("/article/list")
    public Slice<Article> getArticleList(@PageableDefault(size = 10, page = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Slice<Article> response = articleService.getArticleList(pageable);

        return response;
    }
}