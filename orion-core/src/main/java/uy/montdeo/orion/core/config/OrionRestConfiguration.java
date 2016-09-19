package uy.montdeo.orion.core.config;

import java.util.Set;

import org.reflections.Reflections;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import uy.montdeo.orion.core.AbstractEntity;

@Configuration
public class OrionRestConfiguration extends RepositoryRestConfigurerAdapter {

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
