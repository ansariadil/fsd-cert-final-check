package com.cts.newsroom.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.newsroom.bean.Article;
import com.cts.newsroom.bean.User;
import com.cts.newsroom.service.ArticleService;
import com.cts.newsroom.service.UserService;

@RestController
@RequestMapping("/rest/article")
public class ArticleController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;	
	
	@RequestMapping(value = "/saveArticle", method = RequestMethod.POST)
	public User saveArticle(@RequestBody User user) {
		LOGGER.info("Start saveArticle()");
		LOGGER.debug("user {}", user);
		Article article = user.getArticle().get(0);
		String url = article.getUrl();
		
		Article actualArticle = articleService.getArticleByUrl(url);
		User actualUser = userService.getUserByEmail(user.getEmail());
		
		if (actualArticle == null) {
			actualArticle = articleService.saveArticle(article);
			LOGGER.info("New Article Saved");
		}else{
			LOGGER.info("Article Already Exist");
		}
		
		if (actualArticle.getId() != 0) {
			LOGGER.debug("users fav article  {}", actualUser.getArticle());
			actualUser.getArticle().add(actualArticle);
			userService.updateUser(actualUser);
		} 
		LOGGER.info("End");
		return user;
	}
	
	@RequestMapping(value = "/deleteArticle/{email}", method = RequestMethod.POST)
	public User deleteArticle(@RequestBody Article article, @PathVariable("email")String email) {
		System.out.println(email);
		
		User user = userService.getUserByEmail(email);
		System.out.println(user.getArticle());
		int i = 0;
		for (Article userArticle : user.getArticle()) {
			System.out.println(i);
			if(userArticle.getId() == article.getId()){
				user.getArticle().remove(userArticle);
				return userService.updateUser(user);
			}
			i++;
		}
		return null;
	}
	
}
