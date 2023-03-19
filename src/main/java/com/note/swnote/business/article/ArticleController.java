package com.note.swnote.business.article;

import com.note.swnote.business.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/article/form")
    public String registForm() {
        return "article/registForm";
    }




}
