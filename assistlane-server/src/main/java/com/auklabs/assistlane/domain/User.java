package com.auklabs.assistlane.domain;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.envers.Audited;
import com.auklabs.assistlane.enums.UserRole;
import com.auklabs.assistlane.repository.event.AbstractEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@EntityListeners({ AbstractEntityListener.class})
@Audited
public class User extends AbstractEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private Boolean active;
	
	private String role = UserRole.ADMIN.name();

}
