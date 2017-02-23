package com.auklabs.assistlane.dto.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class FaqCategoryDTO implements Serializable{
	
	private static final long serialVersionUID = 3458437626421990297L;
	
	private Long id;
	
	private String displayName;
	
	private String summary;
	
	private Set<FaqArticleDTO> faqArticleDTO = new HashSet<FaqArticleDTO>();
}
