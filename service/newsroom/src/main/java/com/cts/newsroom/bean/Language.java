package com.cts.newsroom.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "language")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ln_id")
//	@JsonView(Views.Internal.class)
	private int id;

	@Column(name = "ln_code")
	private String code;
	
	@Column(name = "ln_name")
	private String name;

	public Language() {
		super();
	}

	public Language(int id, String name, String code) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Language [id=" + id + ", code=" + code + ", name=" + name +"]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

