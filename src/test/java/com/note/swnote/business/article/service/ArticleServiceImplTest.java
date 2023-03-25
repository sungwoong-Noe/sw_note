package com.note.swnote.business.article.service;

import com.note.swnote.business.article.repository.ArticleRepository;
import com.note.swnote.domain.Article;
import com.note.swnote.dto.request.article.ArticleRequest;
import com.note.swnote.dto.response.article.ArticleResponse;
import com.note.swnote.exception.ArticleNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    void setup() {
        articleRepository.deleteAll();
    }


    @Test
    @DisplayName("게시글 저장")
    void test1() {
        //given
        ArticleRequest request = ArticleRequest.builder()
                .title("제목")
                .content("내용")
                .build();

        //when
        Long articleId = articleService.registArticle(request);

        //then
        assertThat(1L).isEqualTo(articleId);
    }

    @Test
    @DisplayName("게시글 조회")
    void test2() {
        //given
        ArticleRequest request = ArticleRequest.builder()
                .title("제목")
                .content("내영")
                .build();

        Long articleSeq = articleService.registArticle(request);
        Article article = articleRepository.findById(articleSeq).get();

        //when
        ArticleResponse response = articleService.getArticle(articleSeq);


        //then
        assertThat(article.getTitle()).isEqualTo(response.getTitle());
        assertThat(article.getContent()).isEqualTo(response.getContent());
    }

    @Test
    @DisplayName("게시글 조회 - 예외")
    void test3() {

        //expected
        assertThatThrownBy(() -> articleService.getArticle(1L))
                .isInstanceOf(ArticleNotFound.class);
    }
}