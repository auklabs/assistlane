package com.auklabs.assistlane.web.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.hateoas.core.EmbeddedWrappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.auklabs.assistlane.domain.FaqCategory;
import com.auklabs.assistlane.dto.models.FaqCategoryDTO;
import com.auklabs.assistlane.resource.FaqCategoryResource;
import com.auklabs.assistlane.resource.assembler.FaqCategoryResourceAssembler;
import com.auklabs.assistlane.service.FaqCategoryService;

@RestController
@RequestMapping(value = "/api/v1/category")
public class FaqCategoryController {
	
	@Autowired
	private FaqCategoryService faqCategoryService;

	@Autowired
	private FaqCategoryResourceAssembler faqResourseAssembler;

	@Autowired
	private PagedResourcesAssembler<FaqCategory> pagedResourcesAssembler;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getAllCategory", method = RequestMethod.GET)
	public ResponseEntity<PagedResources> getAllFaqCategory(Pageable pageable) {
		Page<FaqCategory> faqCategoryPage = faqCategoryService.getAllFaqCategory(pageable);
		PagedResources adminPagedResources = pagedResourcesAssembler.toResource(faqCategoryPage, faqResourseAssembler);

		if (faqCategoryPage.getContent() == null || faqCategoryPage.getContent().isEmpty()) {
			EmbeddedWrappers wrappers = new EmbeddedWrappers(false);
			EmbeddedWrapper wrapper = wrappers.emptyCollectionOf(FaqCategory.class);
			List<EmbeddedWrapper> embedded = Collections.singletonList(wrapper);
			adminPagedResources = new PagedResources(embedded, adminPagedResources.getMetadata(),
					adminPagedResources.getLinks());
		}
		return new ResponseEntity<PagedResources>(adminPagedResources, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getCategory/{id}", method = RequestMethod.GET)
	public ResponseEntity<FaqCategoryResource> getFaqCategory(@PathVariable Long id){
		FaqCategory faqCategory = faqCategoryService.getById(id);
		FaqCategoryResource rsource = faqResourseAssembler.toResource(faqCategory);
		return  new ResponseEntity<FaqCategoryResource>(rsource, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<FaqCategoryResource> saveFaqCategory(@RequestBody FaqCategoryDTO faqCategoryDTO){
		FaqCategory faqCategory = faqCategoryService.createFaqCategory(faqCategoryDTO);
		FaqCategoryResource rsource = faqResourseAssembler.toResource(faqCategory);
		return  new ResponseEntity<FaqCategoryResource>(rsource, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteFaqCategory(@PathVariable Long id){
		faqCategoryService.deleteFaqCategory(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/delete/all" , method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAllFaqCategory(){
		faqCategoryService.deleteAllFaqCategory();
		return ResponseEntity.noContent().build();
	}
}
