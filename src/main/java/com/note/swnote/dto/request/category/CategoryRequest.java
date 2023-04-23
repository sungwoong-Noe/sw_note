package com.note.swnote.dto.request.category;

import com.note.swnote.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryRequest {
    private String categoryName;
    private Long parentId;

    @Builder
    public CategoryRequest(String categoryName, Long parentId) {
        this.categoryName = categoryName;
        this.parentId = parentId;
    }

    public Category toEntity() {

        Category category = Category.builder()
                .categoryName(this.categoryName)
                .build();

        return category;
    }
}
