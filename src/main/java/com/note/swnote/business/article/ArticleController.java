package com.note.swnote.business.article;

import com.note.swnote.business.article.service.ArticleService;
import com.note.swnote.domain.Article;
import com.note.swnote.dto.response.article.ArticlePagingResponse;
import com.note.swnote.dto.response.article.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping(value= {"/", "/articles" })
    public String articles(@PageableDefault(size = 10, page = 0, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model) {

        ArticlePagingResponse articleList = articleService.getArticleList(pageable);
        model.addAttribute("articleList", articleList);

        return "article/articleList";
    }

    @GetMapping("/article/form")
    public String registForm() {

        return "article/registForm";
    }

    @GetMapping("/article/{articleSeq}")
    public String getArticle(@PathVariable Long articleSeq, Model model) {

        ArticleResponse response = articleService.getArticle(articleSeq);

        model.addAttribute("article", response);
        return "article/articleView";
    }
}
