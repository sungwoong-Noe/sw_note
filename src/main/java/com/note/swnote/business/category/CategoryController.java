package com.note.swnote.business.category;


import com.note.swnote.business.category.service.CategoryService;
import com.note.swnote.dto.request.category.CategoryRequest;
import com.note.swnote.dto.response.category.ChildResponse;
import com.note.swnote.dto.response.category.ParentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping("/category")
    public void regist(@RequestBody CategoryRequest request) {
        categoryService.regist(request);
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
