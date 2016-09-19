package uy.montdeo.orion.test.config;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;
import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.HSQL;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import liquibase.integration.spring.SpringLiquibase;

@ComponentScan(basePackages = "uy.montdeo.orion")
@EnableAutoConfiguration(
	exclude = { 	LiquibaseAutoConfiguration.class	}
)
@EnableConfigurationProperties(value = { JpaProperties.class, ServerProperties.class })
@EnableHypermediaSupport(type = HAL)
@EnableJpaRepositories(
	basePackages = {	"uy.montdeo.orion.database.repository"	},
	repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class
)
@EnableSpringDataWebSupport
@EnableTransactionManagement
@EnableWebMvc
public class OrionTestConfiguration {

	private Logger log = LoggerFactory.getLogger(OrionTestConfiguration.class.getName());

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
		log.info("Orion - OrionTestConfiguration has been successfully initialized.");
	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(HSQL).build();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(
			"uy.montdeo.orion.database.entity", 
			"org.springframework.data.jpa.convert.threeten"
		);
		factory.setDataSource(dataSource());
		factory.setJpaProperties(jpaProperties());

		return factory;
	}

	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.putAll(this.jpaProperties.getProperties());
		properties.putAll(this.jpaProperties.getHibernateProperties(dataSource()));

		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(entityManagerFactory);
		return manager;
	}

	@Bean
	public SpringLiquibase liquibase(DataSource dataSource, EntityManagerFactory entityManagerFactory) {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:/liquibase-changelog-test.xml");
		liquibase.setDataSource(dataSource);

		return liquibase;
	}

}
