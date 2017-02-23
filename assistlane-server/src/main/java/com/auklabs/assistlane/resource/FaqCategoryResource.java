package com.auklabs.assistlane.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import com.auklabs.assistlane.domain.FaqCategory;

public class FaqCategoryResource extends Resource<FaqCategory> {

	public FaqCategoryResource(FaqCategory content,Iterable<Link> links) {
		super(content, links);
	}
}
