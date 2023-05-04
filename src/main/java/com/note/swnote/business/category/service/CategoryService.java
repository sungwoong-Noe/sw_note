package com.note.swnote.business.category.service;

import com.note.swnote.dto.request.category.CategoryRequest;
import com.note.swnote.dto.response.category.ChildResponse;
import com.note.swnote.dto.response.category.ParentResponse;

import java.util.List;

public interface CategoryService {

    ParentResponse regist(CategoryRequest request);
    List<ParentResponse> parentList();

    List<ChildResponse> childList(Long parentId);
}
