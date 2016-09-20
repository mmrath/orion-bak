package uy.montdeo.orion.core.container;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;

/**
 * Class extending the {@link TomcatEmbeddedServletContainerFactory} to customize the embedded server for each module
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see TomcatEmbeddedServletContainerFactory
 */
public class OrionServletContainerFactory extends TomcatEmbeddedServletContainerFactory {
		
	public OrionServletContainerFactory() {
		super();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory#postProcessContext(org.apache.catalina.Context)
	 */
	@Override
	protected void postProcessContext(Context context) {
		SecurityConstraint securityConstraint = new SecurityConstraint();
        securityConstraint.setUserConstraint("CONFIDENTIAL");
        SecurityCollection collection = new SecurityCollection();
        collection.addPattern("/*");
        securityConstraint.addCollection(collection);
        context.addConstraint(securityConstraint);
	}

}
