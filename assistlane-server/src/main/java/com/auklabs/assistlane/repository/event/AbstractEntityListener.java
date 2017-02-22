package com.auklabs.assistlane.repository.event;

import java.util.Date;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.joda.time.DateTime;
import com.auklabs.assistlane.domain.AbstractEntity;


public class AbstractEntityListener {

	@PreUpdate
	public void preUpdate(AbstractEntity abstractEntity) {
		abstractEntity.setModificationDate(DateTime.now().toDate());
	}

	@PrePersist
	public void prePersist(AbstractEntity abstractEntity) {
		Date currentDate = DateTime.now().toDate();
		abstractEntity.setCreationDate(currentDate);
		abstractEntity.setModificationDate(currentDate);
	}

}