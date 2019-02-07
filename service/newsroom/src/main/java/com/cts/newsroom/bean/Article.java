package com.cts.newsroom.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "article")
@NamedQueries({
		@NamedQuery(name = "Article.findByDescription", query = "select distinct a from Article a "
				+ " where a.description=:description"),
		@NamedQuery(name = "Article.findLastInsertedArticle", query = "select distinct a from Article a order by a.id desc") })
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ar_id")
	private int id;

	@Column(name = "ar_author")
	private String author;

	@Column(name = "ar_title")
	private String title;

	@Column(name = "ar_description")
	private String description;

	@Column(name = "ar_published_at")
	private Date publishedAt;

	@Column(name = "ar_content")
	private String content;

	@Column(name = "ar_url_to_image")
	private String urlToImage;

	@Column(name = "ar_url")
	private String url;
	
	@Column(name = "ar_source")
	private String source;



	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Article(int id, String author, String title, String description, Date publishedAt, String content,
			String urlToImage, String url, String source) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.description = description;
		this.publishedAt = publishedAt;
		this.content = content;
		this.urlToImage = urlToImage;
		this.url = url;
		this.source = source;
	}

	@Override
	public String toString() {
		return "FavoriteAriticle [id=" + id + ", author=" + author + ", title=" + title + ", description=" + description
				+ ", publishedAt=" + publishedAt + ", content=" + content + ", urlToImage=" + urlToImage + ", url="
				+ url + ", source=" + source + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String auther) {
		this.author = auther;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
