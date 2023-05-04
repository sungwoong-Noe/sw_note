package com.note.swnote.business.category;

import com.note.swnote.business.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/category")
    public String registForm(Model model) {
        model.addAttribute("parentCategories", categoryService.parentList());
        return "category/registForm";
    }
}
