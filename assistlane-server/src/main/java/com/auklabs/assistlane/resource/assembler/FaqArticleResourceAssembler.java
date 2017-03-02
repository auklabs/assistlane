package com.auklabs.assistlane.resource.assembler;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import com.auklabs.assistlane.domain.FaqArticle;
import com.auklabs.assistlane.resource.FaqArticleResource;
import com.auklabs.assistlane.web.controller.FaqArticleController;

@Component
public class FaqArticleResourceAssembler extends ResourceAssemblerSupport<FaqArticle, FaqArticleResource> {

	@Autowired
    private RepositoryEntityLinks repositoryEntityLinks;
	
	public FaqArticleResourceAssembler() {
		super(FaqArticleController.class, FaqArticleResource.class);
	}

	@Override
	public FaqArticleResource toResource(FaqArticle faqArticle) {
		Link faqArticleLink = repositoryEntityLinks.linkToSingleResource(FaqArticle.class, faqArticle.getId());
        Link selfLink = new Link(faqArticleLink.getHref(), Link.REL_SELF);
        return new FaqArticleResource(faqArticle, Arrays.asList(selfLink, faqArticleLink));
	}
}
