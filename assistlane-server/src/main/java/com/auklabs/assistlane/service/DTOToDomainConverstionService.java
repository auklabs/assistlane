package com.auklabs.assistlane.service;

import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.auklabs.assistlane.domain.FaqArticle;
import com.auklabs.assistlane.domain.FaqCategory;
import com.auklabs.assistlane.dto.models.FaqArticleDTO;
import com.auklabs.assistlane.dto.models.FaqCategoryDTO;
import com.auklabs.assistlane.dto.models.FaqDTO;

@Service
public class DTOToDomainConverstionService {

	/*public Faq convertFAQ(FaqDTO faqDTO) {
		
		Faq faq = new Faq();
		Set<FaqCategory> faqCategorieList = new HashSet<FaqCategory>();

		for (FaqCategoryDTO faqCategoryDTO : faqDTO.getFaqCategory()) {
			Set<FaqArticle> faqArticleList = new HashSet<FaqArticle>();
			FaqCategory faqCategory = new FaqCategory();
			faqCategory.setDisplayName(faqCategoryDTO.getDisplayName());
			faqCategory.setSummary(faqCategoryDTO.getSummary());
			faqCategory.setFaq(faq);
			for (FaqArticleDTO faqArticleDTO : faqCategoryDTO.getFaqArticleDTO()) {
				FaqArticle faqArticle = new FaqArticle();
				faqArticle.setBody(faqArticleDTO.getBody());
				faqArticle.setIsPublish(faqArticleDTO.getIsPublish());
				faqArticle.setFaqRelatedItem(faqArticleDTO.getFaqRelatedItem());
				faqArticle.setKeyword(faqArticleDTO.getKeyword());
				faqArticle.setFaqCategory(faqCategory);
				faqArticleList.add(faqArticle);
			}

			faqCategory.setFaqCategory(faqArticleList);
			faqCategorieList.add(faqCategory);
		}

		faq.setFaqCategory(faqCategorieList);
		return faq;
	}
*/
}
