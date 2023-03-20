package com.note.swnote.business.article;

import com.note.swnote.business.article.service.ArticleService;
import com.note.swnote.dto.request.article.ArticleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ArticleRestController {

    private final ArticleService articleService;


    @PostMapping("/article/regist")
    public Long regist(@RequestBody @Valid ArticleRequest request) {

        Long articleId = articleService.registArticle(request);

        return articleId;
    }


}
