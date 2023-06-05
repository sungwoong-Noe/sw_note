package com.note.swnote.business.article.service;

import com.note.swnote.business.article.repository.ArticleRepository;
import com.note.swnote.business.category.repository.CategoryRepository;
import com.note.swnote.business.category.service.CategoryService;
import com.note.swnote.domain.Article;
import com.note.swnote.domain.Category;
import com.note.swnote.dto.request.article.ArticleRequest;
import com.note.swnote.dto.request.category.CategoryRequest;
import com.note.swnote.dto.response.article.ArticleByCategoryPagingResponse;
import com.note.swnote.dto.response.article.ArticlePagingResponse;
import com.note.swnote.dto.response.article.ArticleResponse;
import com.note.swnote.dto.response.category.CategoryResponse;
import com.note.swnote.exception.article.ArticleNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    void setup() {
        articleRepository.deleteAll();
        categoryRepository.deleteAll();
    }


//    @Test
//    @DisplayName("게시글 저장")
//    void test1() {
//        //given
//        ArticleRequest request = ArticleRequest.builder()
//                .title("제목")
//                .content("내용")
//                .build();
//
//        //when
//        long prevCount = articleRepository.count();
//        Long articleId = articleService.registArticle(request);
//        long afterCount = articleRepository.count();
//
//        //then
//        assertThat(prevCount + 1).isEqualTo(afterCount);
//    }

    @Test
    @DisplayName("게시글 조회")
    @Transactional
    void test2() {
        //given
        CategoryRequest parent = CategoryRequest.builder()
                .categoryName("parent")
                .build();

        CategoryResponse parentCategory = categoryService.regist(parent);

        CategoryRequest child = CategoryRequest.builder()
                .categoryName("child")
                .parentId(parentCategory.getId())
                .build();

        CategoryResponse childCategory = categoryService.regist(child);

        ArticleRequest request = ArticleRequest.builder()
                .title("제목")
                .content("내영")
                .categoryId(childCategory.getId())
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


    // TODO: 2023/05/29 테스트 코드 수정 - ArtocleList DTO 리팩토링으로 인한 테스트 수정
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


        Pageable page = PageRequest.of(0, 10);

        //when
        ArticlePagingResponse articleList = articleService.getArticleList(page);



        //then
        assertThat(10).isEqualTo(articleList.getArticleResponses().size());
        assertThat(articleList.getArticleResponses().get(0).getTitle()).isEqualTo("제목0");
    }


    @Test
    @DisplayName("카테고리 포함해서 저장")
    @Transactional
    void test5() {
        //given

        CategoryRequest parent = CategoryRequest.builder()
                .categoryName("parent")
                .build();

        CategoryResponse parentCategory = categoryService.regist(parent);

        CategoryRequest child = CategoryRequest.builder()
                .categoryName("child")
                .parentId(parentCategory.getId())
                .build();

        CategoryResponse childCategory = categoryService.regist(child);

        ArticleRequest request = ArticleRequest.builder()
                .title("제목")
                .content("내용")
                .categoryId(childCategory.getId())
                .thumbnail("썸네일")
                .build();


        //when
        Long articleId = articleService.registArticle(request);
        Optional<Article> article = articleRepository.findById(articleId);

        //then
        assertThat(article.get().getCategory().getCategoryName()).isEqualTo(child.getCategoryName());
    }


    @Test
    @DisplayName("카테고리별 게시글 조회")
    void getArticleByCategory() {
        //given
        Long childCategoryId = this.createArticleByCategoryEx();
        Pageable page = PageRequest.of(0, 10);

        Category category = categoryRepository.findById(childCategoryId).get();

        //then
        ArticleByCategoryPagingResponse articleByCategory = articleService.getArticleByCategory(childCategoryId, page);

        //when
        assertThat(articleByCategory.getArticleResponses().size()).isEqualTo(5);



    }



    @Transactional
    Long createArticleByCategoryEx() {
        //given
        Category parent = Category.builder()
                .categoryName("parent")
                .build();
        categoryRepository.save(parent);


        Category child = Category.builder()
                .categoryName("child")
                .build();
        child.setParentCategory(parent);

        categoryRepository.save(child);

        IntStream.range(0, 5).forEach(i -> {
            Article article = Article.builder()
                    .category(child)
                    .title("article" + i)
                    .content("content" + i)
                    .build();
            articleRepository.save(article);
        });
        return child.getId();
    }
}