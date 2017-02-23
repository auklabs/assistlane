package com.auklabs.assistlane.dto.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class FaqArticleDTO implements Serializable{

	private static final long serialVersionUID = -4489134220347950919L;
	
	private Long id;
	
	private String body;
	
    private Set<String> keywords = new HashSet<String>();
    
    private Set<FaqArticleDTO> faqRelatedArticles = new HashSet<FaqArticleDTO>();
    
    private Boolean publish;
}
