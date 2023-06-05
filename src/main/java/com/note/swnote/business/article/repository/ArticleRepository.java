package com.note.swnote.business.article.repository;

import com.note.swnote.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Slice<Article> getArticleByCategory_Id(Long categorySeq, Pageable pageable);
}