package com.karol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.karol.*")
public class AppConfig extends WebMvcConfigurerAdapter{

	
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/projectB5");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("");
	    return driverManagerDataSource;
	}
	
	  @Bean
	    public ViewResolver viewResolver() {
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setViewClass(JstlView.class);
	        viewResolver.setPrefix("/WEB-INF/views/");
	        viewResolver.setSuffix(".jsp");
	 
	        return viewResolver;
	    }
	 
	     /*
	     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
	     */
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	    }
	    
//	    @Bean
//	    public MessageSource messageSource() {
//	        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//	        messageSource.setBasename("messages");
//	        return messageSource;
//	    }
	
}
