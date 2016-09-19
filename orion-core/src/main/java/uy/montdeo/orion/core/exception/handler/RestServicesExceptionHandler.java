package uy.montdeo.orion.core.exception.handler;

import static uy.montdeo.orion.model.MessageSeverity.ERROR;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.rest.webmvc.RepositoryRestExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import uy.montdeo.orion.core.exception.BusinessException;
import uy.montdeo.orion.model.Message;
import uy.montdeo.orion.model.MessageCode;

/**
 * Manager in charge of serving a managed response for any exception occurred during a REST endpoint execution.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@ControllerAdvice(basePackageClasses = RepositoryRestExceptionHandler.class)
public class RestServicesExceptionHandler {

	private static Logger log = LoggerFactory.getLogger(RestServicesExceptionHandler.class.getName());
	
	@Autowired		
	protected MessageSource messageSource;

	/**
	 * Method to verify that all dependencies and requirements have been set.
	 * 
	 * @author fabian.lobo
	 * @since 1.0
	 */
	@PostConstruct
	public void init() {
		log.info("Orion - RestServicesExceptionHandler has been successfully initialized.");
	}
	
	/**
	 * Method to send a proper message when an unexpected exception is raised.
	 * 
	 * @param exception - The raised exception
	 * @return a message to be sent to the user in JSON format with a HTTP 500 status code.
	 */
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody Message throwable(Throwable exception) {
		String messageText = exception.getMessage(); 
		log.error(messageText, exception);

		return new Message(MessageCode.ERROR, ERROR, messageText);
	}
		
	/**
	 * Method to send a proper message when a business exception is raised.
	 * 
	 * @param exception - The raised exception
	 * @return a message to be sent to the user in JSON format
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Message>  businessException(BusinessException exception) {
		String messageText = exception.getMessage(); 
		log.error(messageText, exception);
		
		return new ResponseEntity<Message>(new Message(exception.getMessageCode(), ERROR, messageText), exception.getHttpStatus());
	}
}
