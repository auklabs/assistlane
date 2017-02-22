package com.auklabs.assistlane.domain;

import java.util.HashSet;
import java.util.Set;
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
import com.auklabs.assistlane.repository.event.AbstractEntityListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@EqualsAndHashCode(callSuper = false,exclude={"faqCategory"})
@EntityListeners({ AbstractEntityListener.class })
@ToString(exclude={"faqCategory"})
public class FaqArticle extends AbstractEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String body;
	
	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "keyword", joinColumns = { @JoinColumn(name = "id") })
    private Set<String> keywords = new HashSet<String>();
	
	@ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "faqRelatedArticles", joinColumns = { @JoinColumn(name = "id") })
    private Set<FaqArticle> faqRelatedArticles = new HashSet<FaqArticle>();
	
	@Column(name ="publish")
	private Boolean publish;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private FaqCategory faqCategory;

}
