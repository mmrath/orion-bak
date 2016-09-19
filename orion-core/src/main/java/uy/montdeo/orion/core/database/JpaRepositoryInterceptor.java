package uy.montdeo.orion.core.database;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.util.Assert.notNull;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import uy.montdeo.orion.core.exception.EntityNotFoundException;

/**
 * Class in charge of respond after a JPA repository is called, and throw a business exception for any unexpected result.
 *
 * @author fabian.lobo
 * @since 1.0
 */
@Aspect
@Component
public class JpaRepositoryInterceptor {

	private static Logger log = LoggerFactory.getLogger(JpaRepositoryInterceptor.class.getName());
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Method to verify that all dependencies and requirements have been set.
	 * 
	 * @author fabian.lobo
	 * @since 1.0
	 */
	@PostConstruct
	public void init() {
		notNull(messageSource, "The property [messageSource] has not been set.");
		
		log.info("Orion - JpaRepositoryInterceptor has been successfully initialized.");
	}
	
	/**
	 * Aspect advice in charge of intercepting a 'findOne...' method call of any JPA repository and, if the entity does not exist, throw an exception
	 * 
	 * @param joinPoint - The aspect join point
	 * @return The entity found
	 * @throws EntityNotFoundException if no entity is found
	 */
	@Around("execution(* org.springframework.data.repository.CrudRepository+.findOne(..))")
	public Object handleFindOneRepositoryCall(ProceedingJoinPoint joinPoint) throws EntityNotFoundException {
		log.debug("Intercepting call for the method " + joinPoint.getSignature().getName() + " on class " + joinPoint.getSignature().getClass().getSimpleName());
		
		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
		if(result == null) {
			throw new EntityNotFoundException(messageSource.getMessage("error.404.id", joinPoint.getArgs(), getLocale()));
		}		
		return result;
	}
	
	/**
	 * Aspect advice in charge of intercepting a 'getBy...' method call of any JPA repository and, if the entity does not exist, throw an exception
	 * 
	 * @param joinPoint - The aspect join point
	 * @return The entity found
	 * @throws EntityNotFoundException if no entity is found
	 */
	@Around("execution(* org.springframework.data.repository.CrudRepository+.getBy*(..))")
	public Object handleGetByRepositoryCall(ProceedingJoinPoint joinPoint) throws EntityNotFoundException {
		log.debug("Intercepting call for the method " + joinPoint.getSignature().getName() + " on class " + joinPoint.getSignature().getClass().getSimpleName());
		
		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			log.error(e.getMessage(), e);
		}
		if(result == null) {
			throw new EntityNotFoundException(messageSource.getMessage("error.404.attribute", joinPoint.getArgs(), getLocale()));
		}		
		return result;
	}
}
