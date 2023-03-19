package com.note.swnote.dto.request;

import com.note.swnote.domain.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class ArticleRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;


    private String thumbnail;

    @Builder
    public ArticleRequest(String title, String content, String thumbnail) {
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
    }

    public Article toEntity(){

         return Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }

}
