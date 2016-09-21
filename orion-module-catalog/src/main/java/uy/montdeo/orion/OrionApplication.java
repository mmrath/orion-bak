package uy.montdeo.orion;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import uy.montdeo.orion.config.CoreConfiguration;

@SpringBootApplication
public class OrionApplication {

	public static void main(String[] args) {
		SpringApplication platform = new SpringApplication(OrionApplication.class, CoreConfiguration.class);
		platform.setBannerMode(Mode.LOG);
		platform.run(args);
    }
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return container -> {
			((TomcatEmbeddedServletContainerFactory) container)
				.getTomcatContextCustomizers()
					.add(new TomcatContextCustomizer() {
				
						@Override
						public void customize(Context context) {
							SecurityConstraint securityConstraint = new SecurityConstraint();
					        securityConstraint.setUserConstraint("CONFIDENTIAL");
					        SecurityCollection collection = new SecurityCollection();
					        collection.addPattern("/*");
					        securityConstraint.addCollection(collection);
					        context.addConstraint(securityConstraint);
							
						}
					});
		};
	}
}
