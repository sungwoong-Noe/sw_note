package com.note.swnote.dto.request.article;

import com.note.swnote.domain.Article;
import com.note.swnote.domain.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ArticleRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;


    private Long categoryId;

    private String thumbnail;

    @Builder
    public ArticleRequest(String title, String content, String thumbnail, Long categoryId) {
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.categoryId = categoryId;
    }

    public Article toEntity(Category category) {
        return Article.builder()
                .title(this.title)
                .content(this.content)
                .category(category)
                .build();
    }
}

