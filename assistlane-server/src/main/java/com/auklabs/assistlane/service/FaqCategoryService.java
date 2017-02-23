package com.auklabs.assistlane.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
	
	@Transactional
	public FaqCategory createFaqCategory(FaqCategoryDTO faqCategoryDTO){
		FaqCategory faqCategory = dtoToDomainConverstionService.convertFAQCategory(faqCategoryDTO);
		return faqCategoryRepository.save(faqCategory);
	}
	
	public Page<FaqCategory> getAllFaqCategory(Pageable pageable){
		return faqCategoryRepository.findAll(pageable);
	}
	
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
