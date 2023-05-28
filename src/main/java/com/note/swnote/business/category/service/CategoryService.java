package com.note.swnote.business.category.service;

import com.note.swnote.dto.request.category.CategoryRequest;
import com.note.swnote.dto.response.category.ChildResponse;
import com.note.swnote.dto.response.category.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse regist(CategoryRequest request);
    List<CategoryResponse> parentList();

    List<ChildResponse> childList(Long parentId);
}
