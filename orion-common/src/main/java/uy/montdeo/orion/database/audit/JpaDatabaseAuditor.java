package uy.montdeo.orion.database.audit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;

/**
 * Implementation of the {@link AuditorAware} interface for getting the current user 
 * and pass its username to the JPA repositories for auditing database changes.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AuditorAware
 */
public class JpaDatabaseAuditor implements AuditorAware<String> {
	
	private static Logger log = LoggerFactory.getLogger(JpaDatabaseAuditor.class.getName());
	
	/**
	 * Method to verify that all dependencies and requirements have been set.
	 * 
	 * @author fabian.lobo
	 * @since 1.0
	 */
	@PostConstruct
	public void init() {
		log.info("Orion - DatabaseAuditor has been successfully initialized.");
	}

	@Override
	public String getCurrentAuditor() {
		String username = "IS_AUTHENTICATED_ANONYMOUSLY";
		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if(authentication == null) {
//			//When a user is trying to login			
//			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) getRequestAttributes();
//		    HttpServletRequest currentRequest = servletRequestAttributes.getRequest();
//		    username = currentRequest.getParameter("username");
//		}	else {
//			//When a user is already logged-in
//			if(authentication.getPrincipal() instanceof UserDetails) {
//				username = ((UserDetails) authentication.getPrincipal()).getUsername();
//			}			
//		}
		
		return username;
	}

}
