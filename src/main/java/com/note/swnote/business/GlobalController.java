package com.note.swnote.business;

import com.note.swnote.business.category.service.CategoryService;
import com.note.swnote.dto.response.category.CategoryResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalController {

    private final CategoryService categoryService;
    @ModelAttribute
    public void sidebarCategories(Model model, HttpServletRequest request) {


        List<CategoryResponse> parentCategoryList = categoryService.parentList();

        model.addAttribute("request", request);
        model.addAttribute("sidebarCategories", parentCategoryList);
    }
}
