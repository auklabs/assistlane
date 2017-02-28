package com.auklabs.assistlane.config;

import org.hibernate.envers.RevisionListener;

public class AuditRevisionListener implements RevisionListener {

	 public final static String USERNAME = "anonymous_user";
	 
	@Override
	public void newRevision(Object revisionEntity) {
		final AuditEntity auditEntity = (AuditEntity) revisionEntity;
		auditEntity.setUsername(USERNAME);
	}
}