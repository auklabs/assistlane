package com.auklabs.assistlane.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import com.auklabs.assistlane.repository.event.AbstractEntityListener;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AbstractEntityListener.class)
public abstract class AbstractEntity {

	@Column(name = "creation_date")
	private Date creationDate;

	@Column(name = "modification_date")
	private Date modificationDate;
}