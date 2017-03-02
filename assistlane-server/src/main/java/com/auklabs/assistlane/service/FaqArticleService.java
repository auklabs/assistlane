package com.auklabs.assistlane.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.auklabs.assistlane.domain.FaqArticle;
import com.auklabs.assistlane.dto.models.FaqArticleDTO;
import com.auklabs.assistlane.repository.FaqArticleRepository;

@Service
public class FaqArticleService extends AbstractService<FaqArticle, Long> {

	@Autowired
	private FaqArticleRepository faqArticleRepository;

	@Autowired
	private DTOToDomainConverstionService dtoToDomainConverstionService;

	public FaqArticleService(FaqArticleRepository faqArticleRepository) {
		super(faqArticleRepository);
		this.faqArticleRepository = faqArticleRepository;
	}

	public FaqArticle getById(Long id) {
		return faqArticleRepository.findOne(id);
	}

	@Transactional
	public FaqArticle createFaqArticle(FaqArticleDTO faqArticleDTO) {
		FaqArticle faqArticle = dtoToDomainConverstionService.convertFaqArticle(faqArticleDTO);
		return faqArticleRepository.save(faqArticle);
	}

	public Page<FaqArticle> getAllFaqArticle(Pageable pageable) {
		return faqArticleRepository.findAll(pageable);
	}
	
	public List<FaqArticle> getAllFaqArticle() {
		return faqArticleRepository.findAll();
	}

	@Transactional
	public void deleteFaqArticle(Long id) {
		FaqArticle faqArticle = getById(id);
		faqArticleRepository.delete(faqArticle);
	}

	@Transactional
	public void deleteAllFaqArticle() {
		faqArticleRepository.deleteAll();
	}

}
