package com.cts.newsroom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.newsroom.bean.Article;
import com.cts.newsroom.repository.ArticleRepository;

@Service
public class ArticleService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);
	@Autowired
	private ArticleRepository articleRepository;
	

	@Transactional
	public Article saveArticle(Article article) {
		articleRepository.save(article);
		System.out.println("done");
		return getArticleByUrl(article.getUrl());
	}
	
	@Transactional
	public Article getArticleByUrl(String url) {
		Article result = articleRepository.findArticleByUrl(url);
		return result;
	}
	
	@Transactional
	public Article getLastInsertedArticle() {
		System.out.println("inside");
		Page<Article> assessmentsPage = articleRepository.findLastInsertedArticle(PageRequest.of(0, 1));
		System.out.println(assessmentsPage);
		Article result =  (Article) assessmentsPage.getContent();	
		System.out.println(result);
		return result;
	}
	

}
