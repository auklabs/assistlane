package com.auklabs.assistlane.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import com.auklabs.assistlane.domain.FaqArticle;

public class FaqArticleResource extends Resource<FaqArticle> {

	public FaqArticleResource(FaqArticle content,Iterable<Link> links) {
		super(content, links);
	}
}
