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
import com.auklabs.assistlane.dto.models.FaqArticleDTO;
import com.auklabs.assistlane.repository.FaqArticleRepository;
import com.auklabs.assistlane.repository.FaqCategoryRepository;

@Service
public class FaqArticleService extends AbstractService<FaqArticle, Long> {

	@Autowired
	private FaqArticleRepository faqArticleRepository;
	
	@Autowired
	private FaqCategoryRepository faqCategoryRepository;

	@Autowired
	private DTOToDomainConverstionService dtoToDomainConverstionService;

	public FaqArticleService(FaqArticleRepository faqArticleRepository) {
		super(faqArticleRepository);
		this.faqArticleRepository = faqArticleRepository;
	}

	public FaqArticle getById(Long id) {
		return faqArticleRepository.findOne(id);
	}
	
	public FaqArticle getByArticleId(String articleId){
		return faqArticleRepository.findByArticleId(articleId);
	}

	/**
	 * @param faqArticleDTO
	 * @return FaqArticle
	 */
	@Transactional
	public FaqArticle createFaqArticle(FaqArticleDTO faqArticleDTO) {
		FaqArticle faqArticle = dtoToDomainConverstionService.convertFaqArticle(faqArticleDTO);
		String str[] = faqArticle.getTitle().split(" ");
		String articleId ="";
		for(String str1 : str){
			articleId += str1+"_";
		}
		faqArticle.setArticleId(articleId);
		return faqArticleRepository.save(faqArticle);
	}
	
	/**
	 * @param id
	 * @param faqArticleDTO
	 * @return FaqArticle
	 */
	@Transactional
	public FaqArticle updateFaqArticle(Long id, FaqArticleDTO faqArticleDTO) {
		FaqArticle updateFaqArticle = faqArticleRepository.findOne(id);

		if (faqArticleDTO.getBody() != null)
			updateFaqArticle.setBody(faqArticleDTO.getBody());
		else
			updateFaqArticle.setBody(updateFaqArticle.getBody());

		if (faqArticleDTO.getTitle() != null && !faqArticleDTO.getFaqRelatedArticles().isEmpty()){
			updateFaqArticle.setTitle(faqArticleDTO.getTitle());
			String str[] = faqArticleDTO.getTitle().split(" ");
			String articlId ="";
			for(String str1 : str){
				articlId += str1+"_";
			}
			updateFaqArticle.setArticleId(articlId);
		}
			
		else
			updateFaqArticle.setTitle(updateFaqArticle.getTitle());

		if (faqArticleDTO.getFaqRelatedArticles() != null && !faqArticleDTO.getFaqRelatedArticles().isEmpty()) {

			Set<FaqArticle> faqArticles = new HashSet<FaqArticle>();
			for (FaqArticleDTO faqArticleDTO2 : faqArticleDTO.getFaqRelatedArticles()) {

				FaqArticle subfaqArticle = new FaqArticle();
				subfaqArticle.setTitle(faqArticleDTO2.getTitle());
				subfaqArticle.setBody(faqArticleDTO2.getBody());
				subfaqArticle.setPublish(faqArticleDTO2.getPublish());
				subfaqArticle.setKeywords(faqArticleDTO2.getKeywords());
				faqArticles.add(subfaqArticle);
			}
			updateFaqArticle.setFaqRelatedArticles(faqArticles);
		} else
			updateFaqArticle.setFaqRelatedArticles(updateFaqArticle.getFaqRelatedArticles());

		if (faqArticleDTO.getKeywords() != null && !faqArticleDTO.getKeywords().isEmpty())
			updateFaqArticle.setKeywords(faqArticleDTO.getKeywords());
		else
			updateFaqArticle.setKeywords(updateFaqArticle.getKeywords());

		updateFaqArticle.setPublish(faqArticleDTO.getPublish());
		updateFaqArticle.setCreationDate(updateFaqArticle.getCreationDate());

		return faqArticleRepository.save(updateFaqArticle);

	}
	
	@Transactional
	public FaqArticle updateFaqArticle(String articleId, FaqArticleDTO faqArticleDTO) {
		FaqArticle updateFaqArticle = faqArticleRepository.findByArticleId(articleId);

		if (faqArticleDTO.getBody() != null)
			updateFaqArticle.setBody(faqArticleDTO.getBody());
		else
			updateFaqArticle.setBody(updateFaqArticle.getBody());

		if (faqArticleDTO.getTitle() != null && !faqArticleDTO.getTitle().equals("")){
			updateFaqArticle.setTitle(faqArticleDTO.getTitle());
			String str[] = faqArticleDTO.getTitle().split(" ");
			String articlId ="";
			for(String str1 : str){
				articlId += str1+"_";
			}
			updateFaqArticle.setArticleId(articlId);
		}
			
		else
			updateFaqArticle.setTitle(updateFaqArticle.getTitle());

		if (faqArticleDTO.getFaqRelatedArticles() != null && !faqArticleDTO.getFaqRelatedArticles().isEmpty()) {

			Set<FaqArticle> faqArticles = new HashSet<FaqArticle>();
			for (FaqArticleDTO faqArticleDTO2 : faqArticleDTO.getFaqRelatedArticles()) {

				FaqArticle subfaqArticle = new FaqArticle();
				subfaqArticle.setTitle(faqArticleDTO2.getTitle());
				subfaqArticle.setBody(faqArticleDTO2.getBody());
				subfaqArticle.setPublish(faqArticleDTO2.getPublish());
				subfaqArticle.setKeywords(faqArticleDTO2.getKeywords());
				faqArticles.add(subfaqArticle);
			}
			updateFaqArticle.setFaqRelatedArticles(faqArticles);
		} else
			updateFaqArticle.setFaqRelatedArticles(updateFaqArticle.getFaqRelatedArticles());

		if (faqArticleDTO.getKeywords() != null && !faqArticleDTO.getKeywords().isEmpty())
			updateFaqArticle.setKeywords(faqArticleDTO.getKeywords());
		else
			updateFaqArticle.setKeywords(updateFaqArticle.getKeywords());

		updateFaqArticle.setPublish(faqArticleDTO.getPublish());
		updateFaqArticle.setCreationDate(updateFaqArticle.getCreationDate());

		return faqArticleRepository.save(updateFaqArticle);

	}
	
	/**
	 * @param id1
	 * @param id2
	 * @return FaqArticle
	 */
	@Transactional
	public FaqArticle addRelatedArticle(Long id1, Long id2) {
		FaqArticle updateFaqArticle = faqArticleRepository.findOne(id1);
		FaqArticle toBeAddedArticle = faqArticleRepository.findOne(id2);

		Set<FaqArticle> faqArticles = new HashSet<FaqArticle>();
		faqArticles.add(toBeAddedArticle);

		updateFaqArticle.setBody(updateFaqArticle.getBody());
		updateFaqArticle.setTitle(updateFaqArticle.getTitle());
		updateFaqArticle.setArticleId(updateFaqArticle.getArticleId());
		updateFaqArticle.setPublish(updateFaqArticle.getPublish());
		updateFaqArticle.setKeywords(updateFaqArticle.getKeywords());
		updateFaqArticle.setCreationDate(updateFaqArticle.getCreationDate());
		updateFaqArticle.setFaqRelatedArticles(faqArticles);

		return faqArticleRepository.save(updateFaqArticle);

	}
	
	@Transactional
	public FaqArticle addRelatedArticle(String articleId1, String articleId2) {
		FaqArticle updateFaqArticle = faqArticleRepository.findByArticleId(articleId1);
		FaqArticle toBeAddedArticle = faqArticleRepository.findByArticleId(articleId2);

		Set<FaqArticle> faqArticles = new HashSet<FaqArticle>();
		faqArticles.add(toBeAddedArticle);

		updateFaqArticle.setBody(updateFaqArticle.getBody());
		updateFaqArticle.setTitle(updateFaqArticle.getTitle());
		updateFaqArticle.setArticleId(updateFaqArticle.getArticleId());
		updateFaqArticle.setPublish(updateFaqArticle.getPublish());
		updateFaqArticle.setKeywords(updateFaqArticle.getKeywords());
		updateFaqArticle.setCreationDate(updateFaqArticle.getCreationDate());
		updateFaqArticle.setFaqRelatedArticles(faqArticles);

		return faqArticleRepository.save(updateFaqArticle);

	}
	
	/**
	 * @param artilceId
	 * @param categoryId
	 * @return FaqArticle
	 */
	@Transactional
	public FaqArticle addArticleInCategory(Long artilceId , Long categoryId){
		FaqArticle updateFaqArticle = faqArticleRepository.findOne(artilceId);
		
		FaqCategory category = faqCategoryRepository.findOne(categoryId);
		updateFaqArticle.setBody(updateFaqArticle.getBody());
		updateFaqArticle.setTitle(updateFaqArticle.getTitle());
		updateFaqArticle.setPublish(updateFaqArticle.getPublish());
		updateFaqArticle.setKeywords(updateFaqArticle.getKeywords());
		updateFaqArticle.setCreationDate(updateFaqArticle.getCreationDate());
		updateFaqArticle.setFaqRelatedArticles(updateFaqArticle.getFaqRelatedArticles());
		updateFaqArticle.setFaqCategory(category);
		
		return faqArticleRepository.save(updateFaqArticle);
		
	}
	
	/**
	 * @param artilceId
	 * @param categoryId
	 * @return
	 */
	@Transactional
	public FaqArticle addArticleInCategory(String artilceId , String categoryId){
		FaqArticle updateFaqArticle = faqArticleRepository.findByArticleId(artilceId);
		
		FaqCategory category = faqCategoryRepository.findByCategoryId(categoryId);
		updateFaqArticle.setBody(updateFaqArticle.getBody());
		updateFaqArticle.setTitle(updateFaqArticle.getTitle());
		updateFaqArticle.setArticleId(updateFaqArticle.getArticleId());
		updateFaqArticle.setPublish(updateFaqArticle.getPublish());
		updateFaqArticle.setKeywords(updateFaqArticle.getKeywords());
		updateFaqArticle.setCreationDate(updateFaqArticle.getCreationDate());
		updateFaqArticle.setFaqRelatedArticles(updateFaqArticle.getFaqRelatedArticles());
		updateFaqArticle.setFaqCategory(category);
		
		return faqArticleRepository.save(updateFaqArticle);
		
	}

	/**
	 * @param pageable
	 * @return Page<FaqArticle>
	 */
	public Page<FaqArticle> getAllFaqArticle(Pageable pageable) {
		 
		Page<FaqArticle> pages = faqArticleRepository.findAll(pageable);
		List<FaqArticle> articles = new ArrayList<FaqArticle>();
		for(FaqArticle article : pages.getContent()){
			FaqArticle fqarticle = new FaqArticle();
			fqarticle.setCreationDate(article.getCreationDate());
			fqarticle.setId(article.getId());
			fqarticle.setModificationDate(article.getModificationDate());
			fqarticle.setArticleId(article.getArticleId());
			fqarticle.setBody(article.getBody());
			fqarticle.setKeywords(article.getKeywords());
			fqarticle.setPublish(article.getPublish());
			fqarticle.setTitle(article.getTitle());
			fqarticle.setRelatedArticleCount(String.valueOf(article.getFaqRelatedArticles().size()));
			Set<FaqArticle> relatedArticles = new HashSet<FaqArticle>();
			for(FaqArticle artilce : article.getFaqRelatedArticles()){
				artilce.setRelatedArticleCount(String.valueOf(artilce.getFaqRelatedArticles().size()));
				relatedArticles.add(artilce);
			}
			fqarticle.setFaqRelatedArticles(relatedArticles);
			fqarticle.setFaqCategory(article.getFaqCategory());
            articles.add(fqarticle);
		}
		Page<FaqArticle> page = new PageImpl<FaqArticle>(articles, new PageRequest(0, 10, null), articles.size());
		return page;
		
		
	}
	
	/**
	 * @return List<FaqArticle>
	 */
	public List<FaqArticle> getAllFaqArticle() {
		return faqArticleRepository.findAll();
	}
	
	/**
	 * @param id
	 * @param pageable
	 * @return Page<FaqArticle>
	 */
	public Page<FaqArticle> getAllArticleInCategory(Long id,Pageable pageable) {
		Page<FaqArticle> faqArticles = faqArticleRepository.findByFaqCategoryId(id, pageable);
		return faqArticles;
	}
	
	public Page<FaqArticle> getAllArticleInCategory(String categoryId,Pageable pageable) {
		Page<FaqArticle> faqArticles = faqArticleRepository.findByFaqCategoryCategoryId(categoryId, pageable);
		return faqArticles;
	}

	/**
	 * @param id
	 */
	@Transactional
	public void deleteFaqArticle(Long id) {
		
		FaqArticle faqArticle = faqArticleRepository.findByFaqRelatedArticlesId(id);
		Set<FaqArticle> faqRelatedArticles = new HashSet<FaqArticle>();
		if (faqArticle != null) {
			for (FaqArticle faqArticle2 : faqArticle.getFaqRelatedArticles()) {
				if (!faqArticle2.getId().equals(id)) {
					faqRelatedArticles.add(faqArticle2);
				}
			}
			faqArticle.setBody(faqArticle.getBody());
			faqArticle.setKeywords(faqArticle.getKeywords());
			faqArticle.setPublish(faqArticle.getPublish());
			faqArticle.setTitle(faqArticle.getTitle());
			faqArticle.setCreationDate(faqArticle.getCreationDate());
			faqArticle.setFaqRelatedArticles(faqRelatedArticles);
			faqArticle.setFaqCategory(faqArticle.getFaqCategory());
			faqArticleRepository.save(faqArticle);
			faqArticleRepository.delete(id);
		} else {
			faqArticleRepository.delete(id);
		}
	}
	
	@Transactional
	public void deleteFaqArticle(String articleId) {
		
		FaqArticle faqArticle = faqArticleRepository.findByFaqRelatedArticlesArticleId(articleId);
		Set<FaqArticle> faqRelatedArticles = new HashSet<FaqArticle>();
		if (faqArticle != null) {
			for (FaqArticle faqArticle2 : faqArticle.getFaqRelatedArticles()) {
				if (!faqArticle2.getArticleId().equals(articleId)) {
					faqRelatedArticles.add(faqArticle2);
				}
			}
			faqArticle.setBody(faqArticle.getBody());
			faqArticle.setArticleId(faqArticle.getArticleId());
			faqArticle.setKeywords(faqArticle.getKeywords());
			faqArticle.setPublish(faqArticle.getPublish());
			faqArticle.setTitle(faqArticle.getTitle());
			faqArticle.setCreationDate(faqArticle.getCreationDate());
			faqArticle.setFaqRelatedArticles(faqRelatedArticles);
			faqArticle.setFaqCategory(faqArticle.getFaqCategory());
			faqArticleRepository.save(faqArticle);
			FaqArticle articletoBeDelete = faqArticleRepository.findByArticleId(articleId);
			faqArticleRepository.delete(articletoBeDelete.getId());
		} else {
			faqArticleRepository.delete(faqArticleRepository.findByArticleId(articleId).getId());
		}
	}

	@Transactional
	public void deleteAllFaqArticle() {
		faqArticleRepository.deleteAll();
	}
	
	public Page<FaqArticle> getOnlyFaqArticle(){
		
		List<FaqArticle> faqArticles = faqArticleRepository.findAll();
		List<FaqArticle> articles = new ArrayList<FaqArticle>();
		for(FaqArticle article : faqArticles){
			FaqArticle fqarticle = new FaqArticle();
			fqarticle.setCreationDate(article.getCreationDate());
			fqarticle.setId(article.getId());
			fqarticle.setModificationDate(article.getModificationDate());
			fqarticle.setBody(article.getBody());
			fqarticle.setKeywords(article.getKeywords());
			fqarticle.setPublish(article.getPublish());
			fqarticle.setTitle(article.getTitle());
			fqarticle.setArticleId(article.getArticleId());
			fqarticle.setRelatedArticleCount(String.valueOf(article.getFaqRelatedArticles().size()));
			fqarticle.setFaqCategory(article.getFaqCategory());
            articles.add(fqarticle);
		}
		Page<FaqArticle> page = new PageImpl<FaqArticle>(articles, new PageRequest(0, 10, null), articles.size());
		return page;
	}
	
	public FaqArticle getOnlyFaqArticle(Long id,Boolean isAll){
		
		FaqArticle faqArticle = getById(id);
		int faqRelatedAtricleSize = faqArticle.getFaqRelatedArticles().size();
		
		FaqArticle article = new FaqArticle();
		article.setBody(faqArticle.getBody());
		article.setTitle(faqArticle.getTitle());
		article.setPublish(faqArticle.getPublish());
		article.setKeywords(faqArticle.getKeywords());
		article.setArticleId(faqArticle.getArticleId());
		article.setModificationDate(faqArticle.getModificationDate());
		article.setCreationDate(faqArticle.getCreationDate());
		article.setId(faqArticle.getId());
		article.setRelatedArticleCount(String.valueOf(faqRelatedAtricleSize));
		article.setFaqCategory(faqArticle.getFaqCategory());
		
		if(isAll){
			article.setFaqRelatedArticles(faqArticle.getFaqRelatedArticles());
		}
		return article;
	}
	
public FaqArticle getOnlyFaqArticle(String articleId,Boolean isAll){
		
		FaqArticle faqArticle = faqArticleRepository.findByArticleId(articleId);
		int faqRelatedAtricleSize = faqArticle.getFaqRelatedArticles().size();
		
		FaqArticle article = new FaqArticle();
		article.setBody(faqArticle.getBody());
		article.setTitle(faqArticle.getTitle());
		article.setPublish(faqArticle.getPublish());
		article.setKeywords(faqArticle.getKeywords());
		article.setArticleId(faqArticle.getArticleId());
		article.setModificationDate(faqArticle.getModificationDate());
		article.setCreationDate(faqArticle.getCreationDate());
		article.setId(faqArticle.getId());
		article.setRelatedArticleCount(String.valueOf(faqRelatedAtricleSize));
		article.setFaqCategory(faqArticle.getFaqCategory());
		
		if(isAll){
			article.setFaqRelatedArticles(faqArticle.getFaqRelatedArticles());
		}
		return article;
	}
}
