package com.auklabs.assistlane.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.springframework.http.MediaType;
import com.auklabs.assistlane.AssistlaneAppApplicationTests;
import com.auklabs.assistlane.domain.FaqArticle;
import com.auklabs.assistlane.dto.models.FaqArticleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class FaqArticleControllerTest extends AssistlaneAppApplicationTests {

	@Test
	public void createFaqArticleTest() throws Exception {

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("FaqArticle");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords = new HashSet<String>();
		keywords.add("SQL");
		keywords.add("SignUp");
		faqArticleDTO1.setKeywords(keywords);

		Set<FaqArticleDTO> faqArticleDTOs = new HashSet<FaqArticleDTO>();
		faqArticleDTO1.setFaqRelatedArticles(faqArticleDTOs);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(faqArticleDTO1);

		mockMvc.perform(post("/faqArticles").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().is2xxSuccessful()).andDo(print());
	}
	
	
	@Test
	public void Test(){
		
		
		FaqArticle faqArticle = faqArticleRepository.findOne(2L);
		FaqArticle faqArticle2 = faqArticleRepository.findOne(1L);
		
		Set<FaqArticle> faqArticles = new HashSet<FaqArticle>();
		faqArticles.add(faqArticle);
		faqArticle2.setFaqRelatedArticles(faqArticles);
		faqArticleRepository.save(faqArticle2);
		
		
	}
}
