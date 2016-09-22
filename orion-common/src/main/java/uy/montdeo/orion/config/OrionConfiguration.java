package uy.montdeo.orion.config;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import liquibase.integration.spring.SpringLiquibase;
import uy.montdeo.orion.database.audit.JpaDatabaseAuditor;

/**
 * Class in charge of starting up and setting the platform application context.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see SpringBootApplication
 */
@ComponentScan(basePackages = "uy.montdeo.orion")
@EnableCaching
@EnableConfigurationProperties(value = {	JpaProperties.class		})
@EnableDiscoveryClient
@EnableJpaAuditing(auditorAwareRef = "databaseAuditor")
@EnableJpaRepositories(
	basePackages = {	"uy.montdeo.orion.database.repository"	},
	repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class
)
@EnableSpringDataWebSupport
@EnableTransactionManagement
public class OrionConfiguration {
	
	private static Logger log = LoggerFactory.getLogger(OrionConfiguration.class.getName());
	
	@Autowired		
	private JpaProperties jpaProperties;
	
	/**
	 * Method to verify that all dependencies and requirements have been set.
	 * 
	 * @author fabian.lobo
	 * @since 1.0
	 */
	@PostConstruct
	public void init() {
		log.info("Orion - CoreConfiguration has been successfully initialized.");
	}
	
	/* ***		DATABASE		*****/
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter vendorAdapter) {
		Properties jpaProperties = new Properties();
		jpaProperties.putAll(this.jpaProperties.getProperties());
		jpaProperties.putAll(this.jpaProperties.getHibernateProperties(dataSource));
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
			factory.setJtaDataSource(dataSource);
			factory.setBeanName("entityManagerFactory");
			factory.setJpaVendorAdapter(vendorAdapter);
			factory.setJpaProperties(jpaProperties);
			factory.setPackagesToScan(
				"uy.montdeo.orion.database.entity", 
				"org.springframework.data.jpa.convert.threeten"
			);			
		
		return factory;
	}
	
	@Bean
	public SpringLiquibase liquibase(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
		SpringLiquibase liquibase = new SpringLiquibase();
			liquibase.setChangeLog("classpath:/liquibase-changelog.xml");
			liquibase.setDataSource(dataSource);
		
		return liquibase;
	}
	
	@Bean
    public AuditorAware<String> databaseAuditor() {
        return new JpaDatabaseAuditor();
    }
		
	/* ***		I18N		*****/
	@Bean
	public LocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
	@Bean
	public static HandlerInterceptor localeInterceptor() {
		LocaleChangeInterceptor locale = new LocaleChangeInterceptor();
			locale.setParamName("lang");
			
		return locale;
	}
	
	@Configuration
	public static class WebConfiguration extends WebMvcConfigurerAdapter {

		/*
		 * (non-Javadoc)
		 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#configureDefaultServletHandling(org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer)
		 */
		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
		}

		/*
		 * (non-Javadoc)
		 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
		 */
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/").setViewName("home");
		}

		/*
		 * (non-Javadoc)
		 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry)
		 */
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(localeInterceptor());
		}		
	}

}
