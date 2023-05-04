package com.note.swnote.business.category;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.swnote.business.category.repository.CategoryRepository;
import com.note.swnote.business.category.service.CategoryService;
import com.note.swnote.domain.Category;
import com.note.swnote.dto.request.category.CategoryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepository categoryRepository;


    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void setup() {
        categoryRepository.deleteAll();
    }


    @Test
    @DisplayName("카테고리 등록 - 부모 카테고리")
    void test1() throws Exception {
        //given
        CategoryRequest request = CategoryRequest.builder()
                .categoryName("부모 카테고리")
                .build();

        //expected
        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @DisplayName("카테고리 등록 - 자식 카테고리")
    void test2() throws Exception {
        //given
        Category parent = Category.builder()
                .categoryName("부모")
                .build();


        categoryRepository.save(parent);

        CategoryRequest request = CategoryRequest.builder()
                .categoryName("자식 카테고리")
                .parentId(parent.getId())
                .build();


        //expected
        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("카테고리 등록 - 자식, 예외")
    void test3() throws Exception {

        //given
        CategoryRequest request = CategoryRequest.builder()
                .categoryName("child")
                .parentId(1L)
                .build();

        //expected
        mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andDo(print());

    }


    @Test
    @DisplayName("카테고리 조회 - 부모")
    void test4() throws Exception {
        //given
        IntStream.range(0, 5).forEach(i -> {
            Category parent = Category.builder()
                    .categoryName("parent" + i)
                    .build();

            categoryRepository.save(parent);
        });


        //expected
        mockMvc.perform(get("/categories/parent")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("카테고리 조회 - 자식")
    void test5() throws Exception {
        //given
        Category parent = Category.builder()
                .categoryName("parent")
                .build();
        categoryRepository.save(parent);


        IntStream.range(0, 5).forEach(i -> {
            CategoryRequest child = CategoryRequest.builder()
                    .parentId(parent.getId())
                    .categoryName("child" + i)
                    .build();

            categoryService.regist(child);
        });


        //expected
        mockMvc.perform(get("/categories/child/{parentId}", parent.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}