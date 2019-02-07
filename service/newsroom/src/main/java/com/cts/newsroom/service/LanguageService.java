package com.cts.newsroom.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.newsroom.bean.Language;
import com.cts.newsroom.repository.LanguageRepository;

@Service
public class LanguageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LanguageService.class);
	
	@Autowired
	private LanguageRepository languageRepository;

	@Transactional
	public List<Language> getAllLanguages() {
		return languageRepository.findAll();
	}
	
	@Transactional
	public Language getLanguageById(int id){
		return languageRepository.findById(id);		
	}

}
