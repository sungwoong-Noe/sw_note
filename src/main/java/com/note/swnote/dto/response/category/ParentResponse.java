package com.note.swnote.dto.response.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ParentResponse {

    private Long id;
    private String categoryName;


    @Builder
    public ParentResponse(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
}
