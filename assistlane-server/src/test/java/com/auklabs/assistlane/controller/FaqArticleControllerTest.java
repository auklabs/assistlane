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
		faqArticleDTO1.setTitle("How to create User");
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
		faqArticleDTO1.setTitle("How to create User");
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
		faqArticleDTO1.setTitle("How to create User");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords = new HashSet<String>();
		keywords.add("SQL");
		keywords.add("SignUp");
		faqArticleDTO1.setKeywords(keywords);
		faqArticleService.createFaqArticle(faqArticleDTO1);

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("FaqArticle");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("SQL");
		keywords2.add("SignUp");
		faqArticleDTO2.setKeywords(keywords2);
		faqArticleService.createFaqArticle(faqArticleDTO2);

		mockMvc.perform(get("/faqArticles")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void TestGetAllArticleInCategory() throws Exception {

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);
		Set<FaqArticleDTO> faqArticleDTOs2 = new HashSet<FaqArticleDTO>();
		faqArticleDTO2.setFaqRelatedArticles(faqArticleDTOs2);

		FaqArticleDTO faqArticleDTO3 = new FaqArticleDTO();
		faqArticleDTO3.setBody("Article3");
		faqArticleDTO3.setTitle("How to create Lassi");
		faqArticleDTO3.setPublish(false);
		Set<String> keywords3 = new HashSet<String>();
		keywords3.add("Java3");
		keywords3.add("login3");
		faqArticleDTO3.setKeywords(keywords3);
		Set<FaqArticleDTO> faqArticleDTOs3 = new HashSet<FaqArticleDTO>();
		faqArticleDTO3.setFaqRelatedArticles(faqArticleDTOs3);

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
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
	public void TestUpdateArticle() throws Exception {

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);

		FaqArticle faqArticle = faqArticleService.createFaqArticle(faqArticleDTO1);
		Long id = faqArticle.getId();

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(faqArticleDTO2);

		mockMvc.perform(put("/faqArticles/{id}", id).contentType(MediaType.APPLICATION_JSON_UTF8).content(requestJson))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void TestAddArticleInsideArticle() throws Exception {

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);

		FaqArticle faqArticle1 = faqArticleService.createFaqArticle(faqArticleDTO1);
		Long id1 = faqArticle1.getId();

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);

		FaqArticle faqArticle2 = faqArticleService.createFaqArticle(faqArticleDTO2);
		Long id2 = faqArticle2.getId();

		mockMvc.perform(put("/faqArticles/{id1}/{id2}", id1, id2)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void TestaddArticleInsideCategory() throws Exception {

		FaqCategoryDTO faqCategoryDTO = new FaqCategoryDTO();
		faqCategoryDTO.setDisplayName("Sales");
		faqCategoryDTO.setSummary("It belongs To Sales Department");

		FaqCategory category = faqCategoryService.createFaqCategory(faqCategoryDTO);
		Long categoryid = category.getId();

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);

		FaqArticle faqArticle1 = faqArticleService.createFaqArticle(faqArticleDTO1);
		Long id = faqArticle1.getId();

		mockMvc.perform(put("/faqArticles/add/{id}/{categoryid}", id, categoryid)).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void TestDeleteArticle() throws Exception {

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);
		Set<FaqArticleDTO> faqArticleDTOs2 = new HashSet<FaqArticleDTO>();
		faqArticleDTO2.setFaqRelatedArticles(faqArticleDTOs2);

		FaqArticleDTO faqArticleDTO3 = new FaqArticleDTO();
		faqArticleDTO3.setBody("Article3");
		faqArticleDTO3.setTitle("How to create Lassi");
		faqArticleDTO3.setPublish(false);
		Set<String> keywords3 = new HashSet<String>();
		keywords3.add("Java3");
		keywords3.add("login3");
		faqArticleDTO3.setKeywords(keywords3);
		Set<FaqArticleDTO> faqArticleDTOs3 = new HashSet<FaqArticleDTO>();
		faqArticleDTO3.setFaqRelatedArticles(faqArticleDTOs3);

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
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
	public void TestDeleteAllArticle() throws Exception {

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);
		Set<FaqArticleDTO> faqArticleDTOs2 = new HashSet<FaqArticleDTO>();
		faqArticleDTO2.setFaqRelatedArticles(faqArticleDTOs2);

		FaqArticleDTO faqArticleDTO3 = new FaqArticleDTO();
		faqArticleDTO3.setBody("Article3");
		faqArticleDTO3.setTitle("How to create Lassi");
		faqArticleDTO3.setPublish(false);
		Set<String> keywords3 = new HashSet<String>();
		keywords3.add("Java3");
		keywords3.add("login3");
		faqArticleDTO3.setKeywords(keywords3);
		Set<FaqArticleDTO> faqArticleDTOs3 = new HashSet<FaqArticleDTO>();
		faqArticleDTO3.setFaqRelatedArticles(faqArticleDTOs3);

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
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

	@Test
	public void UpdateArticleTest() throws Exception {

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);

		FaqArticle faqArticle = faqArticleService.createFaqArticle(faqArticleDTO1);
		String articleId = faqArticle.getArticleId();

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article1");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);

		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Hibernate4Module());
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(faqArticleDTO2);

		mockMvc.perform(put("/faqArticles/article/{articleId}", articleId).contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(requestJson)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void GetArticleByArticleIdWithoutRelatedDataTest() throws Exception {

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);

		FaqArticle article2 = faqArticleService.createFaqArticle(faqArticleDTO2);

		FaqArticleDTO faqArticleDTO3 = new FaqArticleDTO();
		faqArticleDTO3.setBody("Article3");
		faqArticleDTO3.setTitle("How to create Lassi");
		faqArticleDTO3.setPublish(false);
		Set<String> keywords3 = new HashSet<String>();
		keywords3.add("Java3");
		keywords3.add("login3");
		faqArticleDTO3.setKeywords(keywords3);

		FaqArticle article3 = faqArticleService.createFaqArticle(faqArticleDTO3);

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);
		FaqArticle article1 = faqArticleService.createFaqArticle(faqArticleDTO1);

		String articleId = article1.getArticleId();

		mockMvc.perform(get("/faqArticles/article/get/{articleId}", articleId)).andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void GetArticleByIdWithoutRelatedDataTest() throws Exception {

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);

		FaqArticle article2 = faqArticleService.createFaqArticle(faqArticleDTO2);

		FaqArticleDTO faqArticleDTO3 = new FaqArticleDTO();
		faqArticleDTO3.setBody("Article3");
		faqArticleDTO3.setTitle("How to create Lassi");
		faqArticleDTO3.setPublish(false);
		Set<String> keywords3 = new HashSet<String>();
		keywords3.add("Java3");
		keywords3.add("login3");
		faqArticleDTO3.setKeywords(keywords3);

		FaqArticle article3 = faqArticleService.createFaqArticle(faqArticleDTO3);

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);
		FaqArticle article1 = faqArticleService.createFaqArticle(faqArticleDTO1);

		Long id = article1.getId();

		mockMvc.perform(get("/faqArticles/get/{id}", id)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void GetArticleWithDataByArticleIdTest() throws Exception {

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);
		Set<FaqArticleDTO> faqArticleDTOs2 = new HashSet<FaqArticleDTO>();
		faqArticleDTO2.setFaqRelatedArticles(faqArticleDTOs2);

		FaqArticleDTO faqArticleDTO3 = new FaqArticleDTO();
		faqArticleDTO3.setBody("Article3");
		faqArticleDTO3.setTitle("How to create Lassi");
		faqArticleDTO3.setPublish(false);
		Set<String> keywords3 = new HashSet<String>();
		keywords3.add("Java3");
		keywords3.add("login3");
		faqArticleDTO3.setKeywords(keywords3);
		Set<FaqArticleDTO> faqArticleDTOs3 = new HashSet<FaqArticleDTO>();
		faqArticleDTO3.setFaqRelatedArticles(faqArticleDTOs3);

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
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
		FaqArticle article = faqCategory.getFaqArticle().iterator().next();
		String articleId = article.getArticleId();

		mockMvc.perform(get("/faqArticles/article/{articleId}", articleId)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void GetAllArticleInFacCategoryByCategoryIdTest() throws Exception {

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);
		Set<FaqArticleDTO> faqArticleDTOs2 = new HashSet<FaqArticleDTO>();
		faqArticleDTO2.setFaqRelatedArticles(faqArticleDTOs2);

		FaqArticleDTO faqArticleDTO3 = new FaqArticleDTO();
		faqArticleDTO3.setBody("Article3");
		faqArticleDTO3.setTitle("How to create Lassi");
		faqArticleDTO3.setPublish(false);
		Set<String> keywords3 = new HashSet<String>();
		keywords3.add("Java3");
		keywords3.add("login3");
		faqArticleDTO3.setKeywords(keywords3);
		Set<FaqArticleDTO> faqArticleDTOs3 = new HashSet<FaqArticleDTO>();
		faqArticleDTO3.setFaqRelatedArticles(faqArticleDTOs3);

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
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
		String categoryId = faqCategory.getCategoryId();

		mockMvc.perform(get("/faqArticles/faqCategorys/cateory/{categoryId}", categoryId)).andExpect(status().isOk())
				.andDo(print());

	}

	@Test
	public void TestAddArticleInSideFaqArticle() throws Exception {

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);

		FaqArticle faqArticle1 = faqArticleService.createFaqArticle(faqArticleDTO1);
		String articleId1 = faqArticle1.getArticleId();

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);

		FaqArticle faqArticle2 = faqArticleService.createFaqArticle(faqArticleDTO2);
		String articleId2 = faqArticle2.getArticleId();

		mockMvc.perform(put("/faqArticles/article/{articleId1}/{articleId2}", articleId1, articleId2))
				.andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void TestAddArticleInCategoryByArticleId() throws Exception {

		FaqCategoryDTO faqCategoryDTO = new FaqCategoryDTO();
		faqCategoryDTO.setDisplayName("Sales");
		faqCategoryDTO.setSummary("It belongs To Sales Department");

		FaqCategory category = faqCategoryService.createFaqCategory(faqCategoryDTO);
		String categoryId = category.getCategoryId();

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);

		FaqArticle faqArticle1 = faqArticleService.createFaqArticle(faqArticleDTO1);
		String articleId = faqArticle1.getArticleId();

		mockMvc.perform(put("/faqArticles/add/article/{articleId}/{categoryId}", articleId, categoryId))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void TestDeleteArticleByArticleId() throws Exception {

		FaqArticleDTO faqArticleDTO2 = new FaqArticleDTO();
		faqArticleDTO2.setBody("Article2");
		faqArticleDTO2.setTitle("How to create Smosa");
		faqArticleDTO2.setPublish(false);
		Set<String> keywords2 = new HashSet<String>();
		keywords2.add("Java2");
		keywords2.add("login2");
		faqArticleDTO2.setKeywords(keywords2);

		FaqArticle article2 = faqArticleService.createFaqArticle(faqArticleDTO2);

		FaqArticleDTO faqArticleDTO3 = new FaqArticleDTO();
		faqArticleDTO3.setBody("Article3");
		faqArticleDTO3.setTitle("How to create Lassi");
		faqArticleDTO3.setPublish(false);
		Set<String> keywords3 = new HashSet<String>();
		keywords3.add("Java3");
		keywords3.add("login3");
		faqArticleDTO3.setKeywords(keywords3);

		FaqArticle article3 = faqArticleService.createFaqArticle(faqArticleDTO3);

		FaqArticleDTO faqArticleDTO1 = new FaqArticleDTO();
		faqArticleDTO1.setBody("Article1");
		faqArticleDTO1.setTitle("How to create User");
		faqArticleDTO1.setPublish(false);
		Set<String> keywords1 = new HashSet<String>();
		keywords1.add("Java1");
		keywords1.add("login1");
		faqArticleDTO1.setKeywords(keywords1);

		FaqArticle article1 = faqArticleService.createFaqArticle(faqArticleDTO1);

		Set<FaqArticle> faqArticles = new HashSet<FaqArticle>();
		faqArticles.add(article3);
		faqArticles.add(article2);
		article1.setFaqRelatedArticles(faqArticles);
		faqArticleService.save(article1);
		String articleId = article3.getArticleId();
		mockMvc.perform(delete("/faqArticles/article/{articleId}", articleId)).andExpect(status().isNoContent())
				.andDo(print());

	}
}
