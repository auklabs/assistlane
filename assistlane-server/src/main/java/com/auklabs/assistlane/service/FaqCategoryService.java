package com.auklabs.assistlane.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.auklabs.assistlane.domain.FaqCategory;
import com.auklabs.assistlane.dto.models.FaqCategoryDTO;
import com.auklabs.assistlane.repository.FaqCategoryRepository;

@Service
public class FaqCategoryService extends AbstractService<FaqCategory, Long>{

	@Autowired
	private FaqCategoryRepository faqCategoryRepository;
	
	@Autowired
	private DTOToDomainConverstionService dtoToDomainConverstionService;
	
	@Autowired
	public FaqCategoryService(FaqCategoryRepository faqCategoryRepository) {
		super(faqCategoryRepository);
		this.faqCategoryRepository = faqCategoryRepository;
	}
    
	public FaqCategory getById(Long id){
		return faqCategoryRepository.findOne(id);
	}
	
	/**
	 * @param faqCategoryDTO
	 * @return FaqCategory
	 */
	@Transactional
	public FaqCategory createFaqCategory(FaqCategoryDTO faqCategoryDTO){
		FaqCategory faqCategory = dtoToDomainConverstionService.convertFAQCategory(faqCategoryDTO);
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

		if (faqCategoryDTO.getDisplayName() != null && !faqCategoryDTO.getDisplayName().equals(""))
			category.setDisplayName(faqCategoryDTO.getDisplayName());
		else
			category.setDisplayName(category.getDisplayName());

		if (faqCategoryDTO.getSummary() != null && !faqCategoryDTO.getSummary().equals(""))
			category.setSummary(faqCategoryDTO.getSummary());
		else
			category.setSummary(category.getSummary());
		category.setCreationDate(category.getCreationDate());
		return category;
	}
	
	/**
	 * @param pageable
	 * @return Page<FaqCategory>
	 */
	public Page<FaqCategory> getAllFaqCategory(Pageable pageable){
		return faqCategoryRepository.findAll(pageable);
	}
	
	public Page<FaqCategory> getAllOnlyFaqCategory(){
		
		List<FaqCategory> faq = faqCategoryRepository.findAll();
		List<FaqCategory> newfaqCategories = new  ArrayList<FaqCategory>();
		for(FaqCategory category : faq){
			FaqCategory faqCategorie = new FaqCategory();
			faqCategorie.setCreationDate(category.getCreationDate());
			faqCategorie.setModificationDate(category.getModificationDate());
			faqCategorie.setId(category.getId());
			faqCategorie.setDisplayName(category.getDisplayName());
			faqCategorie.setSummary(category.getSummary());
			newfaqCategories.add(faqCategorie);
		}
		Page<FaqCategory> page = new PageImpl<FaqCategory>(newfaqCategories, new PageRequest(0, 10, null), newfaqCategories.size());
		return page;
	}
	
	public FaqCategory getOnlyFaqCategory(Long id,Boolean isAll){
		
		FaqCategory onlyCategory = new FaqCategory();
		FaqCategory category = getById(id);
		int size = category.getFaqArticle().size();
		onlyCategory.setCreationDate(category.getCreationDate());
		onlyCategory.setModificationDate(category.getModificationDate());
		onlyCategory.setDisplayName(category.getDisplayName());
		onlyCategory.setSummary(category.getSummary());
		onlyCategory.setId(category.getId());
		onlyCategory.setArticleCount(String.valueOf(size));
		if(isAll){
			onlyCategory.setFaqArticle(category.getFaqArticle());
		}
		return onlyCategory;
	}
	
	/**
	 * @param id
	 */
	@Transactional
	public void deleteFaqCategory(Long id){
		FaqCategory faqCategory = getById(id);
		faqCategoryRepository.delete(faqCategory);
	}
	
	@Transactional
	public void deleteAllFaqCategory(){
		faqCategoryRepository.deleteAll();
	}
}
