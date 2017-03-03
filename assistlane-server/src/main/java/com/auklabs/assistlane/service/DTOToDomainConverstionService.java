package com.auklabs.assistlane.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.auklabs.assistlane.domain.FaqArticle;
import com.auklabs.assistlane.domain.FaqCategory;
import com.auklabs.assistlane.dto.models.FaqArticleDTO;
import com.auklabs.assistlane.dto.models.FaqCategoryDTO;

@Service
public class DTOToDomainConverstionService {

	public FaqCategory convertFAQCategory(FaqCategoryDTO faqCategoryDTO) {

		FaqCategory faqCategory = new FaqCategory();
		Set<FaqArticleDTO> faqArticleDTOs = faqCategoryDTO.getFaqArticleDTO();
		Set<FaqArticle> faqArticles = new HashSet<FaqArticle>();
		for (FaqArticleDTO faqArticleDTO : faqArticleDTOs) {
			FaqArticle faqArticle = new FaqArticle();
			faqArticle.setTitle(faqArticleDTO.getTitle());
			faqArticle.setBody(faqArticleDTO.getBody());
			faqArticle.setPublish(faqArticleDTO.getPublish());
			faqArticle.setKeywords(faqArticleDTO.getKeywords());
			faqArticle.setFaqCategory(faqCategory);
			Set<FaqArticle> subfaqArticles = new HashSet<FaqArticle>();
			for (FaqArticleDTO subfaqArticleDTO : faqArticleDTO.getFaqRelatedArticles()) {
				FaqArticle subfaqArticle = new FaqArticle();
				subfaqArticle.setTitle(faqArticleDTO.getTitle());
				subfaqArticle.setBody(subfaqArticleDTO.getBody());
				subfaqArticle.setPublish(subfaqArticleDTO.getPublish());
				subfaqArticle.setKeywords(subfaqArticleDTO.getKeywords());
				subfaqArticle.setFaqCategory(faqCategory);
				subfaqArticles.add(subfaqArticle);
			}
			faqArticle.setFaqRelatedArticles(subfaqArticles);
			faqArticles.add(faqArticle);
		}
		faqCategory.setDisplayName(faqCategoryDTO.getDisplayName());
		faqCategory.setSummary(faqCategoryDTO.getSummary());
		faqCategory.setFaqArticle(faqArticles);
		return faqCategory;
	}

	public FaqArticle convertFaqArticle(FaqArticleDTO faqArticleDTO) {

		FaqArticle faqArticle = new FaqArticle();
		
		faqArticle.setBody(faqArticleDTO.getBody());
		faqArticle.setTitle(faqArticleDTO.getTitle());
		faqArticle.setPublish(faqArticleDTO.getPublish());
		faqArticle.setKeywords(faqArticleDTO.getKeywords());
		/*Set<FaqArticle> subfaqArticles = new HashSet<FaqArticle>();
		for (FaqArticleDTO subfaqArticleDTO : faqArticleDTO.getFaqRelatedArticles()) {
			FaqArticle subfaqArticle = new FaqArticle();
			subfaqArticle.setBody(subfaqArticleDTO.getBody());
			subfaqArticle.setPublish(subfaqArticleDTO.getPublish());
			subfaqArticle.setKeywords(subfaqArticleDTO.getKeywords());
			subfaqArticles.add(subfaqArticle);
		}
		faqArticle.setFaqRelatedArticles(subfaqArticles);*/
		return faqArticle;
	}
}
