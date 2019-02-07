package com.cts.newsroom.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.newsroom.bean.Article;
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	public Article findArticleByUrl(@Param("url") String url);
	
//	public Article findLastInsertedArticle();
	
	Page<Article> findLastInsertedArticle(Pageable pageable);

}
