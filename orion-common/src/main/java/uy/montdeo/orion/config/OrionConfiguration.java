package uy.montdeo.orion.config;

import static java.util.Collections.emptyList;
import static org.springframework.util.StringUtils.hasText;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
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

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.kv.model.PutParams;
import com.ecwid.consul.v1.session.model.NewSession;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MulticastConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.config.TcpIpConfig;
import com.hazelcast.map.merge.PassThroughMergePolicy;

import liquibase.integration.spring.SpringLiquibase;
import uy.montdeo.orion.database.AbstractEntity;
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
@EnableConfigurationProperties(value = {	JpaProperties.class	})
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
	
	private static final int TIME_TO_LIVE_IN_SECONDS = 24 * 60 * 60;
	private static final int MAX_IDLE_TIME_IN_SECONDS = 2 * 60 * 60;
	private static final int INFINITE = 0;
	
	private static final String LEADER_SESSION_NAME 		= "hazelcast_leader_session";
	private static final String LEADER_VALID_FOR 			= "300s"; // 5 minutes
	private static final String LEADER_IP_PROPERTY_NAME 	= "hazelcast.leader.ip";
	
	@Autowired
	private ConsulClient consulClient;
	
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
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaProperties jpaProperties, DataSource dataSource, JpaVendorAdapter vendorAdapter) {
		Properties properties = new Properties();
		properties.putAll(jpaProperties.getProperties());
		properties.putAll(jpaProperties.getHibernateProperties(dataSource));
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
			factory.setJtaDataSource(dataSource);
			factory.setBeanName("entityManagerFactory");
			factory.setJpaVendorAdapter(vendorAdapter);
			factory.setJpaProperties(properties);
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
	
	/* ***		HAZELCAST		*****/
	@Bean
	public Config hazelcastConfig() {
		List<String> members = getClusterMembersIps();
		
		MulticastConfig multicastConfig = new MulticastConfig().setEnabled(false);
		TcpIpConfig tcpIpConfig = new TcpIpConfig().setEnabled(true).setMembers(members);
				
		return new Config()
			.addMapConfig(mapConfig("tokens", TIME_TO_LIVE_IN_SECONDS, MAX_IDLE_TIME_IN_SECONDS))
			.addMapConfig(mapConfig("countries", INFINITE, INFINITE))
			.setNetworkConfig(
				new NetworkConfig().setJoin(
					new JoinConfig()
						.setMulticastConfig(multicastConfig)
						.setTcpIpConfig(tcpIpConfig)
				)
			)
			;
	}
	
	private List<String> getClusterMembersIps() {
		try {
	      return Optional.of(fetchRunningClusterStartupLeader()).filter(list -> !list.isEmpty()).orElse(electNewClusterStartupLeader());
	    } catch (Exception ex) {
	    	log.error(ex.getMessage());
	    	throw new RuntimeException("Cannot connect to cluster.", ex);
	    }
	}

	private List<String> fetchRunningClusterStartupLeader() {
		String encodedLeaderIp = consulClient.getKVValue(LEADER_IP_PROPERTY_NAME).getValue().getValue();
		String leaderPublicIp = new String(Base64.getDecoder().decode(encodedLeaderIp));
		if(hasText(leaderPublicIp)) {
			return Arrays.asList(leaderPublicIp);
		}
		return emptyList();
	}
	
	private List<String> electNewClusterStartupLeader() {
		InetUtils utils = new InetUtils(new InetUtilsProperties());
		String myPublicIp = utils.findFirstNonLoopbackHostInfo().getIpAddress();
		
		utils.close();
		
		PutParams leaderKeyParams = new PutParams();
	    leaderKeyParams.setAcquireSession(createSessionLock());
	    consulClient.setKVValue(LEADER_IP_PROPERTY_NAME, myPublicIp, leaderKeyParams);
	    
	    return Arrays.asList(myPublicIp);
	}
	
	private String createSessionLock() {
	    NewSession lockingSession = new NewSession();
	    lockingSession.setName(LEADER_SESSION_NAME);
	    lockingSession.setTtl(LEADER_VALID_FOR);
	    return consulClient.sessionCreate(lockingSession, QueryParams.DEFAULT).getValue();
	}

	private MapConfig mapConfig(String name, int ttls, int mis) {
	    return new MapConfig()
	    	.setEvictionPolicy(EvictionPolicy.LRU)
	    	.setMaxIdleSeconds(mis)
	        .setMergePolicy(PassThroughMergePolicy.class.getName())
	        .setName(name)
	        .setReadBackupData(true)
	        .setStatisticsEnabled(true)
	        .setTimeToLiveSeconds(ttls)
	        ;
	  }
		
	/* ***		REST		*****/
	@Configuration
	public static class OrionRestConfiguration extends RepositoryRestConfigurerAdapter {
		
		private Reflections reflections = new Reflections("uy.montdeo.orion");

		/*
		 * (non-Javadoc)
		 * @see org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter#configureRepositoryRestConfiguration(org.springframework.data.rest.core.config.RepositoryRestConfiguration)
		 */
		@Override
		public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
			Set<Class<? extends AbstractEntity>> children = reflections.getSubTypesOf(AbstractEntity.class);
			config.exposeIdsFor(children.toArray(new Class<?>[children.size()]));
		}
		
	}
	
	@Configuration
	public static class OrionWebConfiguration extends WebMvcConfigurerAdapter {

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
		
		@Bean
		public HandlerInterceptor localeInterceptor() {
			LocaleChangeInterceptor locale = new LocaleChangeInterceptor();
				locale.setParamName("lang");
				
			return locale;
		}
	}

}
