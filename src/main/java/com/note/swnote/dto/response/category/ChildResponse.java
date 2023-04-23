package com.note.swnote.dto.response.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChildResponse {

    private Long id;
    private String categoryName;
    private ParentResponse parent;

    @Builder
    public ChildResponse(Long id, String categoryName, ParentResponse parent) {
        this.id = id;
        this.categoryName = categoryName;
        this.parent = parent;
    }
}
