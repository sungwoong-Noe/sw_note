package com.note.swnote.domain;

import com.note.swnote.dto.request.ArticleRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_seq")
    private Long id;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotBlank(message = "썸네일 이미지를 등록해주세요")
    private String thumbnail;


    @Builder
    public Article(String title, String content, String thumbnail) {
        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
    }

    public static Article newArticle(ArticleRequest request) {

        return Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .thumbnail(request.getThumbnail())
                .build();
    }
}
