package uy.montdeo.orion.test.config;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import liquibase.integration.spring.SpringLiquibase;

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
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(
			"uy.montdeo.orion.database.entity", 
			"org.springframework.data.jpa.convert.threeten"
		);
		factory.setDataSource(dataSource);
		factory.setJpaProperties(jpaProperties(dataSource));

		return factory;
	}

	private Properties jpaProperties(DataSource dataSource) {
		Properties properties = new Properties();
		properties.putAll(this.jpaProperties.getProperties());
		properties.putAll(this.jpaProperties.getHibernateProperties(dataSource));

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
