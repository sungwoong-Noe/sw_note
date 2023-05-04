package com.note.swnote.business.category.service;

import com.note.swnote.business.category.repository.CategoryRepository;
import com.note.swnote.domain.Category;
import com.note.swnote.dto.request.category.CategoryRequest;
import com.note.swnote.dto.response.category.ChildResponse;
import com.note.swnote.dto.response.category.ParentResponse;
import com.note.swnote.exception.cateogry.ParentNotFound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CategoryServiceImplTest {


    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;


    @Test
    @DisplayName("카테고리 등록 - 대분류")
    void test1() {
        //given
        CategoryRequest request = CategoryRequest.builder()
                .categoryName("카테고리 테스트")
                .build();

        //when
        categoryService.regist(request);

        //then
        assertThat(categoryRepository.count()).isEqualTo(1);
    }


    @Test
    @DisplayName("카테고리 등록 - 소분류")
    void test2() {
        //given
        CategoryRequest parent = CategoryRequest.builder()
                .categoryName("부모 카테고리")
                .build();

        CategoryRequest.CategoryRequestBuilder child = CategoryRequest.builder()
                .categoryName("자식 카테고리");

        //when
        categoryService.regist(parent);
        CategoryRequest childRequest = child.parentId(1L).build();

        categoryService.regist(childRequest);


        List<Category> all = categoryRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(2L);
        assertThat(all.get(1).getParent().getCategoryName()).isEqualTo("부모 카테고리");
    }

    @Test
    @DisplayName("부모 카테고리 리스트 조회")
    void test3() {
        //given
        IntStream.range(0, 10).forEach(i -> {
            CategoryRequest request = CategoryRequest.builder()
                    .categoryName("부모 카테고리 " + i)
                    .build();

            categoryService.regist(request);
        });


        //when
        List<ParentResponse> parentResponses = categoryService.parentList();


        //then
        assertThat(10).isEqualTo(parentResponses.size());
    }


    @Test
    @DisplayName("자식 카테고리 등록 - 예외")
    void test4() {
        //given
        CategoryRequest request = CategoryRequest.builder()
                .categoryName("자식 카테고리")
                .parentId(1L)
                .build();


        //expected
        assertThrows(ParentNotFound.class, () -> categoryService.regist(request));
    }


    @Test
    @DisplayName("자식 카테고리 조회")
    void test5() {
        //given
        Category parent = Category.builder()
                .categoryName("parent")
                .build();

        categoryRepository.save(parent);


        IntStream.range(0, 5).forEach(i -> {
            CategoryRequest request = CategoryRequest.builder()
                    .categoryName("child " + i)
                    .parentId(parent.getId())
                    .build();

            categoryService.regist(request);
        });



        //when
        List<ChildResponse> childList = categoryService.childList(parent.getId());

        //then
        assertThat(5L).isEqualTo(childList.size());
        assertThat(childList.get(0).getParent().getCategoryName()).isEqualTo(parent.getCategoryName());


    }

    @Test
    @DisplayName("카테고리 목록 - 자식 포함")
    void test6() {
        //given
        Category parent = Category.builder()
                .categoryName("parent")
                .build();

        categoryRepository.save(parent);

        CategoryRequest child1 = CategoryRequest.builder()
                .parentId(1L)
                .categoryName("child1")
                .build();

        categoryService.regist(child1);

        CategoryRequest child2 = CategoryRequest.builder()
                .parentId(1L)
                .categoryName("child2")
                .build();

        categoryService.regist(child2);



        //when
        List<ParentResponse> parents = categoryService.parentList();


        //then
        assertThat(parents.get(0).getChildList().size()).isEqualTo(2L);


    }
}