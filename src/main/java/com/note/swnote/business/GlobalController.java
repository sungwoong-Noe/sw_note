package com.note.swnote.business;

import com.note.swnote.business.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalController {

    private final CategoryService categoryService;
    @ModelAttribute
    public void sidebarCategories(Model model) {
        model.addAttribute("sidebarCategories", categoryService.parentList());
    }
}
