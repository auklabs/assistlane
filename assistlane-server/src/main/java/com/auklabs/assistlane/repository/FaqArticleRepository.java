package com.auklabs.assistlane.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.auklabs.assistlane.domain.FaqArticle;

public interface FaqArticleRepository extends JpaRepository<FaqArticle, Long> {

	Page<FaqArticle> findByFaqCategoryId(Long id,Pageable pageable);
}
