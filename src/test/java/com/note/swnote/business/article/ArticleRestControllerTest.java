package com.note.swnote.business.article;

import com.note.swnote.business.article.repository.ArticleRepository;
import com.note.swnote.business.article.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleRestControllerTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        articleRepository.deleteAll();
    }


    @Test
    @DisplayName("게시글 등록 - 예외")
    void test1 () throws Exception {

        //expected
        mockMvc.perform(post("/article/regist")
                        .contentType(APPLICATION_JSON)
                        .content("{\"title\": \"\", \"content\": \"\" }"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}