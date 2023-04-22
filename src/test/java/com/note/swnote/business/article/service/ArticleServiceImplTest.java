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

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.INTEGER;


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
        long prevCount = articleRepository.count();
        Long articleId = articleService.registArticle(request);
        long afterCount = articleRepository.count();

        //then
        assertThat(prevCount + 1).isEqualTo(afterCount);
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


    @Test
    @DisplayName("게시글 목록 조회")
    void test4() {
        //given
        IntStream.range(0, 10).forEach(index -> {
            Article article = Article.builder()
                    .title("제목" + index)
                    .content("내용" + index)
                    .build();
            articleRepository.save(article);
        });

        //when
//        List<ArticleResponse> articleList = articleService.getArticleList();


        //then
//        assertThat(10).isEqualTo(articleList.size());
//        assertThat(articleList.get(1).getTitle()).isEqualTo("제목1");
    }
}