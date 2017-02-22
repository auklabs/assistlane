package com.auklabs.assistlane.resource.assembler;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import com.auklabs.assistlane.domain.User;
import com.auklabs.assistlane.resource.UserResource;
import com.auklabs.assistlane.web.controller.UserController;


@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

	@Autowired
	private RepositoryEntityLinks repositoryEntityLinks;

	public UserResourceAssembler() {
		super(UserController.class, UserResource.class);
	}

	@Override
	public UserResource toResource(User user) {
		Link userLink = repositoryEntityLinks.linkToSingleResource(User.class, user.getId());
		Link selfLink = new Link(userLink.getHref(), Link.REL_SELF);
		return new UserResource(user, Arrays.asList(selfLink, userLink));
	}

}
