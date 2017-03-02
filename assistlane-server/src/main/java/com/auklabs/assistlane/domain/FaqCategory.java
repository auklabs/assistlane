package com.auklabs.assistlane.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.envers.Audited;
import com.auklabs.assistlane.repository.event.AbstractEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@EntityListeners({ AbstractEntityListener.class })
@Audited
public class FaqCategory extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "display_name")
	private String displayName;

	private String summary;

	@OneToMany(mappedBy = "faqCategory", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private Set<FaqArticle> faqArticle = new HashSet<FaqArticle>();
}
