package com.auklabs.assistlane.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import com.auklabs.assistlane.AssistlaneAppApplicationTests;
import com.auklabs.assistlane.domain.FaqArticle;
import com.auklabs.assistlane.dto.models.FaqArticleDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

public class FaqArticleControllerTest extends AssistlaneAppApplicationTests {

	@Before
	public void cleanUp() {
		faqArticleRepository.deleteAll();
	}

	@Test
	public void createFaqArticleTest() throws Exception {

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("FaqArticle");
		faqArticleDTO1.setTitle("title1");
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
	public void getFaqArticle() throws Exception {

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("FaqArticle");
		faqArticleDTO1.setTitle("title1");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords = new HashSet<String>();
		keywords.add("SQL");
		keywords.add("SignUp");
		faqArticleDTO1.setKeywords(keywords);

		FaqArticle faqArticle = faqArticleService.createFaqArticle(faqArticleDTO1);

		Long id = faqArticle.getId();
		mockMvc.perform(get("/faqArticles/{id}", id)).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void getALLFaqArticle() throws Exception {

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("FaqArticle");
		faqArticleDTO1.setTitle("title1");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords = new HashSet<String>();
		keywords.add("SQL");
		keywords.add("SignUp");
		faqArticleDTO1.setKeywords(keywords);
		faqArticleService.createFaqArticle(faqArticleDTO1);

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("FaqArticle");
		faqArticleDTO2.setTitle("title2");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("SQL");
		keywords2.add("SignUp");
		faqArticleDTO2.setKeywords(keywords2);
		faqArticleService.createFaqArticle(faqArticleDTO2);

		mockMvc.perform(get("/faqArticles")).andExpect(status().isOk()).andDo(print());

	}

	//@Test
	public void Test() {

		FaqArticle faqArticle = faqArticleRepository.findOne(2L);
		FaqArticle faqArticle2 = faqArticleRepository.findOne(1L);

		Set<FaqArticle> faqArticles = new HashSet<FaqArticle>();
		faqArticles.add(faqArticle);
		faqArticle2.setFaqRelatedArticles(faqArticles);
		faqArticleRepository.save(faqArticle2);

	}
}
