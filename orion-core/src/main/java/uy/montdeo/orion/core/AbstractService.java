package uy.montdeo.orion.core;

import static org.springframework.util.Assert.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public abstract class AbstractService {
	
	@Autowired		
	protected MessageSource messageSource;
	
	/**
	 * Method to verify that all dependencies and requirements have been set.
	 * 
	 * @author fabian.lobo
	 * @since 1.0
	 */
	public void init() {
		notNull(messageSource, "The property [messageSource] has not been set.");
	}

}
