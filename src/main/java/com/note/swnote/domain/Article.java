package com.note.swnote.domain;

import com.note.swnote.dto.request.article.ArticleRequest;
import com.note.swnote.dto.response.article.ArticleResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String thumbnail;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_seq")
    private Category category;


    @Builder
    public Article(String title, String content, String thumbnail, Category category) {

        this.title = title;
        this.content = content;
        this.thumbnail = thumbnail;
        this.category = category;
    }

    public static Article newArticle(ArticleRequest request) {

        return Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .thumbnail(request.getThumbnail())
                .build();
    }

    public ArticleResponse toResponseEntity() {

        return ArticleResponse.builder()
                .articleSeq(this.id)
                .title(this.title)
                .content(this.content)
                .build();

    }
}
