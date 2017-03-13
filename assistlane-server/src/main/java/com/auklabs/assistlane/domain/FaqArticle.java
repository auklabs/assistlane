package com.auklabs.assistlane.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.envers.Audited;
import org.springframework.data.rest.core.annotation.RestResource;
import com.auklabs.assistlane.repository.event.AbstractEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@EqualsAndHashCode(callSuper = false, exclude = { "faqCategory", "keywords","faqRelatedArticles"})
@EntityListeners({ AbstractEntityListener.class })
@ToString(exclude = { "faqCategory", "keywords", "faqRelatedArticles"})
@Audited
public class FaqArticle extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String body;
	
	private String title;
	
	@Transient
	private String relatedArticleCount;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "keyword", joinColumns = { @JoinColumn(name = "id") })
	private Set<String> keywords = new HashSet<String>();

	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@RestResource(exported = false) // for Showing this details in FaqArticle
	private Set<FaqArticle> faqRelatedArticles = new HashSet<FaqArticle>();

	@Column(name = "publish")
	private Boolean publish;

	@ManyToOne(fetch = FetchType.EAGER)
	private FaqCategory faqCategory;
}
