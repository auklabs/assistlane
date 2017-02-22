package com.auklabs.assistlane.dto.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class FaqDTO implements Serializable{
	
	private static final long serialVersionUID = 709855581626874236L;
	
	private Long id;
	
	private Set<FaqCategoryDTO> faqCategory = new HashSet<FaqCategoryDTO>();

}
