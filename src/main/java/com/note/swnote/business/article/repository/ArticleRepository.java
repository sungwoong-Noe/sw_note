package com.note.swnote.business.article.repository;

import com.note.swnote.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
