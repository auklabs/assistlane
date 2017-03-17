package com.auklabs.assistlane.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.auklabs.assistlane.domain.FaqArticle;
import com.auklabs.assistlane.domain.FaqCategory;
import com.auklabs.assistlane.dto.models.FaqCategoryDTO;
import com.auklabs.assistlane.repository.FaqCategoryRepository;

@Service
public class FaqCategoryService extends AbstractService<FaqCategory, Long> {

	@Autowired
	private FaqCategoryRepository faqCategoryRepository;

	@Autowired
	private DTOToDomainConverstionService dtoToDomainConverstionService;

	@Autowired
	public FaqCategoryService(FaqCategoryRepository faqCategoryRepository) {
		super(faqCategoryRepository);
		this.faqCategoryRepository = faqCategoryRepository;
	}

	public FaqCategory getById(Long id) {
		return faqCategoryRepository.findOne(id);
	}

	/**
	 * @param faqCategoryDTO
	 * @return FaqCategory
	 */
	@Transactional
	public FaqCategory createFaqCategory(FaqCategoryDTO faqCategoryDTO) {
		FaqCategory faqCategory = dtoToDomainConverstionService.convertFAQCategory(faqCategoryDTO);

		Set<FaqArticle> faqArticles = faqCategory.getFaqArticle();
		Set<FaqArticle> faqArticles2 = new HashSet<FaqArticle>();

		for (FaqArticle faqArticle : faqArticles) {
			String str[] = faqArticle.getTitle().split(" ");
			String articleId = "";
			for (String str1 : str) {
				articleId += str1 + "_";
			}
			faqArticle.setArticleId(articleId);
			faqArticles2.add(faqArticle);
		}
		faqCategory.setFaqArticle(faqArticles2);
		String categoryId = faqCategory.getDisplayName() + "_";
		faqCategory.setCategoryId(categoryId);
		return faqCategoryRepository.save(faqCategory);
	}

	/**
	 * @param id
	 * @param faqCategoryDTO
	 * @return FaqCategory
	 */
	@Transactional
	public FaqCategory updateFaqCategory(Long id, FaqCategoryDTO faqCategoryDTO) {
		FaqCategory category = faqCategoryRepository.findOne(id);

		if (faqCategoryDTO.getDisplayName() != null && !faqCategoryDTO.getDisplayName().equals("")) {
			category.setDisplayName(faqCategoryDTO.getDisplayName());
			category.setCategoryId(faqCategoryDTO.getDisplayName() + "_");
		}

		else
			category.setDisplayName(category.getDisplayName());

		if (faqCategoryDTO.getSummary() != null && !faqCategoryDTO.getSummary().equals(""))
			category.setSummary(faqCategoryDTO.getSummary());
		else
			category.setSummary(category.getSummary());
		category.setCreationDate(category.getCreationDate());
		category = faqCategoryRepository.save(category);
		return category;
	}

	@Transactional
	public FaqCategory updateFaqCategory(String categoryId, FaqCategoryDTO faqCategoryDTO) {
		FaqCategory category = faqCategoryRepository.findByCategoryId(categoryId);

		if (faqCategoryDTO.getDisplayName() != null && !faqCategoryDTO.getDisplayName().equals("")) {
			category.setDisplayName(faqCategoryDTO.getDisplayName());
			category.setCategoryId(category.getDisplayName() + "_");
		} else
			category.setDisplayName(category.getDisplayName());

		if (faqCategoryDTO.getSummary() != null && !faqCategoryDTO.getSummary().equals(""))
			category.setSummary(faqCategoryDTO.getSummary());
		else
			category.setSummary(category.getSummary());
		category.setCreationDate(category.getCreationDate());
		category = faqCategoryRepository.save(category);
		return category;
	}

	/**
	 * @param pageable
	 * @return Page<FaqCategory>
	 */
	public Page<FaqCategory> getAllFaqCategory(Pageable pageable) {
		Page<FaqCategory> pages = faqCategoryRepository.findAll(pageable);
		List<FaqCategory> faqCategories = pages.getContent();
		List<FaqCategory> newfaqCategories = new ArrayList<FaqCategory>();
		for(FaqCategory category : faqCategories){
			int size = category.getFaqArticle().size();
			FaqCategory faqCategorie = new FaqCategory();
			faqCategorie.setCreationDate(category.getCreationDate());
			faqCategorie.setModificationDate(category.getModificationDate());
			faqCategorie.setId(category.getId());
			faqCategorie.setCategoryId(category.getCategoryId());
			faqCategorie.setDisplayName(category.getDisplayName());
			faqCategorie.setSummary(category.getSummary());
			Set<FaqArticle> faqArticles  = new HashSet<FaqArticle>();
			for(FaqArticle article : category.getFaqArticle()){
				article.setRelatedArticleCount(String.valueOf(article.getFaqRelatedArticles().size()));
				faqArticles.add(article);
			}
			faqCategorie.setFaqArticle(faqArticles);
			faqCategorie.setArticleCount(String.valueOf(size));
			newfaqCategories.add(faqCategorie);
		}
		
		Page<FaqCategory> page = new PageImpl<FaqCategory>(newfaqCategories, new PageRequest(0, 20, null),
				newfaqCategories.size());
		return page;
	}

	public Page<FaqCategory> getAllOnlyFaqCategory() {

		List<FaqCategory> faq = faqCategoryRepository.findAll();
		List<FaqCategory> newfaqCategories = new ArrayList<FaqCategory>();
		for (FaqCategory category : faq) {
			FaqCategory faqCategorie = new FaqCategory();
			faqCategorie.setCreationDate(category.getCreationDate());
			faqCategorie.setModificationDate(category.getModificationDate());
			faqCategorie.setId(category.getId());
			faqCategorie.setCategoryId(category.getCategoryId());
			faqCategorie.setDisplayName(category.getDisplayName());
			faqCategorie.setArticleCount(String.valueOf(category.getFaqArticle().size()));
			faqCategorie.setSummary(category.getSummary());
			newfaqCategories.add(faqCategorie);
		}
		Page<FaqCategory> page = new PageImpl<FaqCategory>(newfaqCategories, new PageRequest(0, 20, null),
				newfaqCategories.size());
		return page;
	}

	public FaqCategory getOnlyFaqCategory(Long id, Boolean isAll) {

		FaqCategory onlyCategory = new FaqCategory();
		FaqCategory category = getById(id);
		int size = category.getFaqArticle().size();
		onlyCategory.setCreationDate(category.getCreationDate());
		onlyCategory.setModificationDate(category.getModificationDate());
		onlyCategory.setDisplayName(category.getDisplayName());
		onlyCategory.setSummary(category.getSummary());
		onlyCategory.setId(category.getId());
		onlyCategory.setCategoryId(category.getCategoryId());
		onlyCategory.setArticleCount(String.valueOf(size));
		if (isAll) {
			onlyCategory.setFaqArticle(category.getFaqArticle());
		}
		return onlyCategory;
	}

	public FaqCategory getOnlyFaqCategory(String categoryId, Boolean isAll) {

		FaqCategory onlyCategory = new FaqCategory();
		FaqCategory category = faqCategoryRepository.findByCategoryId(categoryId);
		int size = category.getFaqArticle().size();
		onlyCategory.setCreationDate(category.getCreationDate());
		onlyCategory.setModificationDate(category.getModificationDate());
		onlyCategory.setDisplayName(category.getDisplayName());
		onlyCategory.setSummary(category.getSummary());
		onlyCategory.setId(category.getId());
		onlyCategory.setCategoryId(category.getCategoryId());
		onlyCategory.setArticleCount(String.valueOf(size));
		if (isAll) {
			onlyCategory.setFaqArticle(category.getFaqArticle());
		}
		return onlyCategory;
	}

	/**
	 * @param id
	 */
	@Transactional
	public void deleteFaqCategory(Long id) {
		FaqCategory faqCategory = getById(id);
		faqCategoryRepository.delete(faqCategory);
	}

	@Transactional
	public void deleteFaqCategory(String categoryId) {
		FaqCategory faqCategory = faqCategoryRepository.findByCategoryId(categoryId);
		faqCategoryRepository.delete(faqCategory);
	}

	@Transactional
	public void deleteAllFaqCategory() {
		faqCategoryRepository.deleteAll();
	}
}
