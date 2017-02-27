//package com.auklabs.assistlane.config;
//
//import java.sql.SQLException;
//import java.util.Properties;
//
//import javax.sql.DataSource;
//
//import org.hibernate.cfg.AvailableSettings;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.hibernate4.HibernateTransactionManager;
//import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//
////@Configuration
////@EnableConfigurationProperties(JpaProperties.class)
//public class DatabaseConfig {
//
// /*
//    
//    @Value("${spring.datasource.url}")
//	private String url;
//
//	@Value("${spring.datasource.dataSourceClassName}")
//	private String dataSourceClassName;
//
//	@Value("${spring.datasource.username}")
//	private String user;
//
//	@Value("${spring.datasource.password}")
//	private String password;
//
//	@Value("${spring.jpa.properties.hibernate.dialect}")
//	private String dialect;
//
//	@Value("${spring.jpa.hibernate.ddl-auto}")
//	private String ddlAuto;
//    
// 
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
// 
//        dataSource.setDriverClassName(dataSourceClassName);
//        dataSource.setUrl(url);
//        dataSource.setUsername(user);
//        dataSource.setPassword(password);
// 
//        return dataSource;
//    }
// 
//    @Bean(name = "entityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean locationEntityManagerFactory() throws SQLException {
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(dataSource());
//		em.setPackagesToScan("com.auklabs.assistlane");
//		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		em.setJpaVendorAdapter(vendorAdapter);
//		em.setJpaProperties(hibProperties());
//		return em;
//	}
// 
//    private Properties hibProperties() {
//    	Properties properties = new Properties();
//		properties.setProperty(AvailableSettings.DIALECT, dialect);
//		properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto);
//		properties.setProperty("show-sql", "true");
//		
//		properties.setProperty("org.hibernate.envers.audit_table_suffix", "_audit");
//		properties.setProperty("org.hibernate.envers.store_data_at_delete", "true");
//  
//		return properties;
//    }*/
//    
// /*   @Bean
//    public PlatformTransactionManager jpaTransactionManager() throws SQLException {
//        final JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(locationEntityManagerFactory().getObject());
//        return transactionManager;
//    }
//    
//    @Bean
//    public PlatformTransactionManager hibernateTransactionManager() {
//        final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//        return transactionManager;
//    }
//    
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan(new String[] { "com.auklabs.assistlane" });
//        sessionFactory.setHibernateProperties(hibProperties());
//
//        return sessionFactory;
//    }
// */
//	
//}
