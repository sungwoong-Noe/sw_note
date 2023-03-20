package com.note.swnote.business.article;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.swnote.business.article.service.ArticleService;
import com.note.swnote.dto.response.article.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final ObjectMapper objectMapper;

    @GetMapping("/article/form")
    public String registForm() {
        return "article/registForm";
    }

    @GetMapping("/article/{articleSeq}")
    public String getArticle(@PathVariable Long articleSeq, Model model) {

        ArticleResponse response = articleService.getArticle(articleSeq);

        model.addAttribute("article", response);

//        try {
//            String responseJSON = objectMapper.writeValueAsString(response);
//            model.addAttribute("article", responseJSON);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException("JSON 파싱 에러");
//        }

        return "article/articleView";
    }
}
