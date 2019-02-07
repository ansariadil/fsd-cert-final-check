package com.cts.newsroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.newsroom.bean.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
	
	public Language findById(@Param("id")int id);

}
