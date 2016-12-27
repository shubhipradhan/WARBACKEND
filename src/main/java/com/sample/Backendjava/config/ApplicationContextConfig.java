package com.sample.Backendjava.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.sample.Backendjava.dao.UserDAO;
import com.sample.Backendjava.dao.UserDAOImpl;
import com.sample.Backendjava.model.User;

@Configuration
@ComponentScan("com.sample")
@EnableTransactionManagement
public class ApplicationContextConfig {

	@Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
	
	@Bean(name = "dataSource")
   	public DataSource getH2DataSource() {
   		DriverManagerDataSource dataSource = new DriverManagerDataSource();
   		dataSource.setDriverClassName("org.h2.Driver");
   		dataSource.setUrl("jdbc:h2:~/rest");

   		dataSource.setUsername("sa");
   		dataSource.setPassword("");
   		
   		
   		return dataSource;
   	}
	   private Properties getHibernateProperties() {
	       	Properties properties = new Properties();
	       	properties.setProperty("hibernate.hbm2ddl.auto", "update");
	       	properties.put("hibernate.show_sql", "true");
	       	properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	       	return properties;
	       }
	   @Autowired
       @Bean(name = "sessionFactory")
       public SessionFactory getSessionFactory(DataSource dataSource) {
       	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
       	sessionBuilder.addProperties(getHibernateProperties());
       	sessionBuilder.addAnnotatedClasses(User.class);
       	return sessionBuilder.buildSessionFactory();
       }
		@Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
			return transactionManager;
		}
		  @Autowired
		    @Bean(name = "userDao")
		    public UserDAO getUserDao(SessionFactory sessionFactory) {
		    	return new UserDAOImpl(sessionFactory);
		    }
}
