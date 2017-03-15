package com.auklabs.assistlane.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.auklabs.assistlane.domain.FaqCategory;

public interface FaqCategoryRepository extends JpaRepository<FaqCategory, Long> {

	FaqCategory findByCategoryId(String categoryId);
}
