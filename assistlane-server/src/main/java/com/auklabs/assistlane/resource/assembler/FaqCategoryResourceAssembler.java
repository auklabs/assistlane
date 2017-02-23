package com.auklabs.assistlane.resource.assembler;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import com.auklabs.assistlane.domain.FaqCategory;
import com.auklabs.assistlane.resource.FaqCategoryResource;
import com.auklabs.assistlane.web.controller.FaqCategoryController;

@Component
public class FaqCategoryResourceAssembler extends ResourceAssemblerSupport<FaqCategory, FaqCategoryResource> {

	@Autowired
    private RepositoryEntityLinks repositoryEntityLinks;
	
	
	public FaqCategoryResourceAssembler() {
		super(FaqCategoryController.class, FaqCategoryResource.class);
	}

	@Override
	public FaqCategoryResource toResource(FaqCategory faqCategory) {
		Link faqCategoryLink = repositoryEntityLinks.linkToSingleResource(FaqCategory.class, faqCategory.getId());
        Link selfLink = new Link(faqCategoryLink.getHref(), Link.REL_SELF);
        return new FaqCategoryResource(faqCategory, Arrays.asList(selfLink, faqCategoryLink));
	}
}
