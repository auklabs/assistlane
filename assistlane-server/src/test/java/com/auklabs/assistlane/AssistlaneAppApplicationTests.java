package com.auklabs.assistlane;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.auklabs.assistlane.repository.FaqArticleRepository;
import com.auklabs.assistlane.repository.FaqCategoryRepository;
import com.auklabs.assistlane.repository.UserRepository;
import com.auklabs.assistlane.service.FaqArticleService;
import com.auklabs.assistlane.service.FaqCategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssistlaneAppApplicationTests {

	protected MockMvc mockMvc;
	
	@Autowired
	protected WebApplicationContext context;
	
	@Autowired
	protected FaqCategoryRepository faqCategoryRepository;
	
	@Autowired
	protected FaqArticleRepository faqArticleRepository;
	
	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	protected FaqCategoryService faqCategoryService;
	
	@Autowired
	protected FaqArticleService faqArticleService;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).dispatchOptions(true).build();
	}
	
	@Test
	public void contextLoads() {
	}

}
