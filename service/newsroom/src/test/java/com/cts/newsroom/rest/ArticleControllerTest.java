package com.cts.newsroom.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cts.newsroom.service.ArticleService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleControllerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleControllerTest.class);
	private ArticleService articleService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void jSaveArticleTest() throws Exception {

		LOGGER.info("Start jSaveArticleTest()");
		
		String testJson = "{\"email\":\"adil\", \"article\":[{\"url\":\"urlTestArticle\"}]}";
		LOGGER.debug("test data:{}", testJson);
		
		mockMvc.perform(post("/article/saveArticle").content(testJson).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.email").value("adil"))
				.andExpect(jsonPath("$.article[0].url").value("urlTestArticle"));
		
		LOGGER.info("End jSaveArticleTest()");
	}

	@Test
	public void deleteArticleTest() throws Exception {

		LOGGER.info("Start deleteArticleTest()");
		
		String testJson = "{\"email\":\"adil\", \"article\":[{\"url\":\"urlTestArticle\"}]}";
		
		LOGGER.debug("test data:{}", testJson);
		
		mockMvc.perform(post("/article/saveArticle").content(testJson).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.email").value("adil"))
		.andExpect(jsonPath("$.article[0].url").value("urlTestArticle"));
		
		mockMvc.perform(post("/article/deleteArticle/{email}", "adil").content(testJson).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk());
		
		LOGGER.info("End deleteArticleTest()");
	}

}
