package com.cts.newsroom.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.newsroom.bean.Language;
import com.cts.newsroom.service.LanguageService;

@RestController
@RequestMapping(value = "/language")
public class LanguageController {
	@Autowired
	private LanguageService languageService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Language> getLanguageList() {
		return languageService.getAllLanguages();
	}

}
