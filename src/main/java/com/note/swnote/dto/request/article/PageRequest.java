package com.note.swnote.dto.request.article;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageRequest {

    private int page = 1;
    private int size = 10;

    public PageRequest(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
