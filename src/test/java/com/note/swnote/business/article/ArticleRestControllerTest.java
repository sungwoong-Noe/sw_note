package com.note.swnote.business.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.swnote.business.article.repository.ArticleRepository;
import com.note.swnote.business.article.service.ArticleService;
import com.note.swnote.domain.Article;
import com.note.swnote.dto.request.article.ArticleRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.stream.IntStream;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleRestControllerTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        articleRepository.deleteAll();
    }


    @Test
    @DisplayName("게시글 등록 - 예외")
    void test1() throws Exception {

        //expected
        mockMvc.perform(post("/article/regist")
                        .contentType(APPLICATION_JSON)
                        .content("{\"title\": \"\", \"content\": \"\" }"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 등록")
    void test2() throws Exception {

        //given
        ArticleRequest request = ArticleRequest.builder()
                .title("게시글 등록 테스트")
                .content("게시글 내용")
                .build();


        //expected
        mockMvc.perform(post("/article/regist")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(25L)));
    }

    @Test
    @DisplayName("게시글 조회")
    void test3() throws Exception {
        //given
        Article article = Article.builder()
                .title("title123")
                .content("content123")
                .build();

        //when
        articleRepository.save(article);

        //expected
        mockMvc.perform(get("/article/{articleSeq}", article.getId())
                        .contentType(TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("article/articleView"))
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 조회 - 예외")
    void test4() throws Exception {
        mockMvc.perform(get("/article/1")
                        .contentType(TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("error/error_404"))
                .andDo(print());
    }

    @Test
    @DisplayName("게시글 목록 - 페이징")
    void test5 () throws Exception {
        //given
        IntStream.range(0, 30).forEach(index -> {
            Article article = Article.builder()
                    .title("제목" + index)
                    .content("내용" + index)
                    .build();
            articleRepository.save(article);
        });

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", "0");
        params.add("size", "10");


        //expected
        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(view().name("article/articleList"))
                .andExpect(model().attributeExists("articleList"))
                .andDo(print());
    }

}