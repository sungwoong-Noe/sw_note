package com.note.swnote.business.category.service;


import com.note.swnote.business.category.repository.CategoryRepository;
import com.note.swnote.domain.Category;
import com.note.swnote.dto.request.category.CategoryRequest;
import com.note.swnote.dto.response.category.ChildResponse;
import com.note.swnote.dto.response.category.CategoryResponse;
import com.note.swnote.exception.cateogry.ParentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public CategoryResponse regist(CategoryRequest request) {

        Category category = request.toEntity();

        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId()).orElseThrow(() -> new ParentNotFoundException());
            category.setParentCategory(parent);
        }

        categoryRepository.save(category);

        return category.toParentResponse();
    }

    @Override
    public List<CategoryResponse> parentList() {

        List<Category> parentList  = categoryRepository.findByParentIsNull();

        return parentList.stream()
                .map(parent -> parent.toParentResponse())
                .collect(Collectors.toList());
    }

    @Override
    public List<ChildResponse> childList(Long parentId) {

        List<Category> childList = categoryRepository.findByParentId(parentId);

        return childList.stream()
                .map(child -> child.toChildResponse())
                .collect(Collectors.toList());
    }

}
