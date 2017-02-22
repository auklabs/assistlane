package com.auklabs.assistlane.resource;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import com.auklabs.assistlane.domain.User;

public class UserResource extends Resource<User> {

	public UserResource(User content,Iterable<Link> links) {
		super(content,links);
	}
}
