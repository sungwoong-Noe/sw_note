package com.note.swnote.business.category;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.swnote.business.category.service.CategoryService;
import com.note.swnote.dto.request.category.CategoryRequest;
import com.note.swnote.dto.response.category.ChildResponse;
import com.note.swnote.dto.response.category.ParentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;

    @PostMapping("/category")
    public String regist(@RequestBody CategoryRequest request) throws JsonProcessingException {
        ParentResponse parent = categoryService.regist(request);
        return  objectMapper.writeValueAsString(parent);
    }

    @GetMapping("/categories/parent")
    public List<ParentResponse> getParents() {
        return categoryService.parentList();
    }


    @GetMapping("/categories/child/{parentId}")
    public List<ChildResponse> getChildList(@PathVariable Long parentId) {

        List<ChildResponse> childList = categoryService.childList(parentId);

        return childList;
    }
}
