package com.cts.newsroom.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@NamedQueries({
	@NamedQuery(name = "User.findByNameLike", query = "select distinct u from User u "
			+ " where u.name LIKE CONCAT('%',:name,'%') ") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "us_id")
	private int id;

	@Column(name = "us_name")
	private String name;

	@Column(name = "us_email")
	private String email;

	@Column(name = "us_password")
	private String password;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "us_ur_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	@JsonView(Views.Internal.class)
	private Role role;

	@Column(name = "us_active")
//	@JsonView(Views.Public.class)
	private String userStatus;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "us_ln_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	@JsonView(Views.Internal.class)
	private Language language;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_article", joinColumns = { @JoinColumn(name = "ua_us_id") }, inverseJoinColumns = {
			@JoinColumn(name = "ua_ar_id") })
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private List<Article> article;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", userStatus=" + userStatus + ", language=" + language + ", article=" + article + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public List<Article> getArticle() {
		return article;
	}

	public void setArticle(List<Article> article) {
		this.article = article;
	}	
	
	public User(int id, String name, String email, String password, Role role, String userStatus, Language language,
			List<Article> article) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.userStatus = userStatus;
		this.language = language;
		this.article = article;
	}
	

	public User(int id, String name, String email, String password, Role role, String userStatus, Language language) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.userStatus = userStatus;
		this.language = language;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
}
