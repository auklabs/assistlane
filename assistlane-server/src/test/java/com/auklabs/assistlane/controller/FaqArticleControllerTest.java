package com.auklabs.assistlane.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import com.auklabs.assistlane.AssistlaneAppApplicationTests;
import com.auklabs.assistlane.domain.FaqArticle;
import com.auklabs.assistlane.domain.FaqCategory;
import com.auklabs.assistlane.dto.models.FaqArticleDTO;
import com.auklabs.assistlane.dto.models.FaqCategoryDTO;
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
	
	@Test
	public void TestGetAllArticleInCategory() throws Exception{
		
		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("title2");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);
		Set<FaqArticleDTO> faqArticleDTOs2 = new HashSet<FaqArticleDTO>();
		faqArticleDTO2.setFaqRelatedArticles(faqArticleDTOs2);
		
		
		FaqArticleDTO faqArticleDTO3 = new FaqArticleDTO();
		faqArticleDTO3.setBody("Article3");
		faqArticleDTO3.setTitle("title3");
		faqArticleDTO3.setPublish(false);
		Set<String> keywords3 = new HashSet<String>();
		keywords3.add("Java3");
		keywords3.add("login3");
		faqArticleDTO3.setKeywords(keywords3);
		Set<FaqArticleDTO> faqArticleDTOs3 = new HashSet<FaqArticleDTO>();
		faqArticleDTO3.setFaqRelatedArticles(faqArticleDTOs3);
		
		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("title1");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);
		
		Set<FaqArticleDTO> faqArticleDTOs1 = new HashSet<FaqArticleDTO>();
		faqArticleDTOs1.add(faqArticleDTO2);
		faqArticleDTOs1.add(faqArticleDTO3);
		faqArticleDTO1.setFaqRelatedArticles(faqArticleDTOs1);
		
		Set<FaqArticleDTO> faqArticleDTOs = new HashSet<FaqArticleDTO>();
		faqArticleDTOs.add(faqArticleDTO1);
		
		FaqCategoryDTO faqCategoryDTO = new FaqCategoryDTO();
		faqCategoryDTO.setDisplayName("Sales");
		faqCategoryDTO.setSummary("It belongs To Sales Department");
		faqCategoryDTO.setFaqArticleDTO(faqArticleDTOs);
		
		FaqCategory faqCategory = faqCategoryService.createFaqCategory(faqCategoryDTO);
		Long id = faqCategory.getId();
		
		mockMvc.perform(get("/faqArticles/faqCategorys/{id}", id)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void TestUpdateArticle() throws Exception{
		
		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("title1");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);
		
		FaqArticle faqArticle = faqArticleService.createFaqArticle(faqArticleDTO1);
		Long id = faqArticle.getId();
		
		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("title2");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(faqArticleDTO2);
		
		mockMvc.perform(put("/faqArticles/{id}", id).contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson)).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void TestAddArticleInsideArticle() throws Exception{
		
		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("title1");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);
		
		FaqArticle faqArticle1 = faqArticleService.createFaqArticle(faqArticleDTO1);
		Long id1 = faqArticle1.getId();
		
		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("title2");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);
		
		FaqArticle faqArticle2 = faqArticleService.createFaqArticle(faqArticleDTO2);
		Long id2 = faqArticle2.getId();
		
		mockMvc.perform(put("/faqArticles/{id1}/{id2}", id1,id2)).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void TestaddArticleInsideCategory() throws Exception{
		
		FaqCategoryDTO faqCategoryDTO = new FaqCategoryDTO();
		faqCategoryDTO.setDisplayName("Sales");
		faqCategoryDTO.setSummary("It belongs To Sales Department");
		
		FaqCategory category = faqCategoryService.createFaqCategory(faqCategoryDTO);
		Long categoryid = category.getId();
		
		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("title1");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);
		
		FaqArticle faqArticle1 = faqArticleService.createFaqArticle(faqArticleDTO1);
		Long id = faqArticle1.getId();
		
		mockMvc.perform(put("/faqArticles/add/{id}/{categoryid}",id,categoryid)).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void TestDeleteArticle() throws Exception{
		
		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("title2");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);
		Set<FaqArticleDTO> faqArticleDTOs2 = new HashSet<FaqArticleDTO>();
		faqArticleDTO2.setFaqRelatedArticles(faqArticleDTOs2);

		FaqArticleDTO faqArticleDTO3 = new FaqArticleDTO();
		faqArticleDTO3.setBody("Article3");
		faqArticleDTO3.setTitle("title3");
		faqArticleDTO3.setPublish(false);
		Set<String> keywords3 = new HashSet<String>();
		keywords3.add("Java3");
		keywords3.add("login3");
		faqArticleDTO3.setKeywords(keywords3);
		Set<FaqArticleDTO> faqArticleDTOs3 = new HashSet<FaqArticleDTO>();
		faqArticleDTO3.setFaqRelatedArticles(faqArticleDTOs3);

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("title1");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);

		Set<FaqArticleDTO> faqArticleDTOs1 = new HashSet<FaqArticleDTO>();
		faqArticleDTOs1.add(faqArticleDTO2);
		faqArticleDTOs1.add(faqArticleDTO3);
		faqArticleDTO1.setFaqRelatedArticles(faqArticleDTOs1);

		Set<FaqArticleDTO> faqArticleDTOs = new HashSet<FaqArticleDTO>();
		faqArticleDTOs.add(faqArticleDTO1);

		FaqCategoryDTO faqCategoryDTO = new FaqCategoryDTO();
		faqCategoryDTO.setDisplayName("Sales");
		faqCategoryDTO.setSummary("It belongs To Sales Department");
		faqCategoryDTO.setFaqArticleDTO(faqArticleDTOs);
		
		faqCategoryService.createFaqCategory(faqCategoryDTO);
		Long id = 2L;
		mockMvc.perform(delete("/faqArticles/{id}", id)).andExpect(status().isNoContent()).andDo(print());
		
		
	}
	
	@Test
	public void TestDeleteAllArticle() throws Exception{
		
		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("title2");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);
		Set<FaqArticleDTO> faqArticleDTOs2 = new HashSet<FaqArticleDTO>();
		faqArticleDTO2.setFaqRelatedArticles(faqArticleDTOs2);

		FaqArticleDTO faqArticleDTO3 = new FaqArticleDTO();
		faqArticleDTO3.setBody("Article3");
		faqArticleDTO3.setTitle("title3");
		faqArticleDTO3.setPublish(false);
		Set<String> keywords3 = new HashSet<String>();
		keywords3.add("Java3");
		keywords3.add("login3");
		faqArticleDTO3.setKeywords(keywords3);
		Set<FaqArticleDTO> faqArticleDTOs3 = new HashSet<FaqArticleDTO>();
		faqArticleDTO3.setFaqRelatedArticles(faqArticleDTOs3);

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("title1");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);

		Set<FaqArticleDTO> faqArticleDTOs1 = new HashSet<FaqArticleDTO>();
		faqArticleDTOs1.add(faqArticleDTO2);
		faqArticleDTOs1.add(faqArticleDTO3);
		faqArticleDTO1.setFaqRelatedArticles(faqArticleDTOs1);

		Set<FaqArticleDTO> faqArticleDTOs = new HashSet<FaqArticleDTO>();
		faqArticleDTOs.add(faqArticleDTO1);

		FaqCategoryDTO faqCategoryDTO = new FaqCategoryDTO();
		faqCategoryDTO.setDisplayName("Sales");
		faqCategoryDTO.setSummary("It belongs To Sales Department");
		faqCategoryDTO.setFaqArticleDTO(faqArticleDTOs);
		
		faqCategoryService.createFaqCategory(faqCategoryDTO);
	
		mockMvc.perform(delete("/faqArticles")).andExpect(status().isNoContent()).andDo(print());		
	}
	
}
