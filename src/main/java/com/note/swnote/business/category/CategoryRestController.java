package com.note.swnote.business.category;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.swnote.business.category.service.CategoryService;
import com.note.swnote.dto.request.category.CategoryRequest;
import com.note.swnote.dto.response.category.ChildResponse;
import com.note.swnote.dto.response.category.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;

    @PostMapping("/category")
    public String regist(@RequestBody CategoryRequest request) throws JsonProcessingException {
        CategoryResponse registedCategory = categoryService.regist(request);

        return  objectMapper.writeValueAsString(registedCategory);
    }

    @GetMapping("/categories/parent")
    public List<CategoryResponse> getParents() {
        return categoryService.parentList();
    }


    @GetMapping("/categories/child/{parentId}")
    public List<ChildResponse> getChildList(@PathVariable Long parentId) {

        List<ChildResponse> childList = categoryService.childList(parentId);

        return childList;
    }
}
