package uy.montdeo.orion.starter;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import liquibase.integration.spring.SpringLiquibase;
import uy.montdeo.orion.core.database.JpaDatabaseAuditor;
import uy.montdeo.orion.starter.container.OrionServletContainerFactory;

/**
 * Class in charge of starting up and setting the platform application context.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see SpringBootApplication
 */
@EnableCaching
@EnableConfigurationProperties(value = {	JpaProperties.class		})
@EnableJpaAuditing(auditorAwareRef = "databaseAuditor")
@EnableJpaRepositories(
	basePackages = {	"uy.montdeo.orion.database.repository"	},
	repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class
)
@EnableSpringDataWebSupport
@EnableTransactionManagement
@SpringBootApplication
public class OrionApplication {
	
	@Autowired		
	private JpaProperties jpaProperties;
	
	public static void main(String[] args) {
		SpringApplication platform = new SpringApplication(OrionApplication.class);
		platform.setBannerMode(Mode.LOG);
		platform.run(args);
    }
	
	/* ***		SERVER		*****/
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
	    return new OrionServletContainerFactory();
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
	
}
