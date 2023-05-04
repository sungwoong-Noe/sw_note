package com.note.swnote.dto.response.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ParentResponse {

    private Long id;
    private String categoryName;

    private List<ChildResponse> childList;


    @Builder
    public ParentResponse(Long id, String categoryName, List<ChildResponse> childList) {
        this.id = id;
        this.categoryName = categoryName;
        this.childList = childList;
    }
}
