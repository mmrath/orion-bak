package uy.montdeo.orion.exception.handler;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import uy.montdeo.orion.exception.BusinessException;
import uy.montdeo.orion.model.Message;
import uy.montdeo.orion.model.MessageCode;
import uy.montdeo.orion.model.MessageSeverity;

/**
 * Manager in charge of serving a managed response for any exception occurred during a REST endpoint execution.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@ControllerAdvice(annotations = RestController.class)
public class RestServicesExceptionHandler extends ResponseEntityExceptionHandler {

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

		return new Message(MessageCode.ERROR, MessageSeverity.ERROR, messageText);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleExceptionInternal(java.lang.Exception, java.lang.Object, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
	 */
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseEntity<Object> response = super.handleExceptionInternal(ex, body, headers, status, request);
		if(!response.hasBody() && ex instanceof BusinessException) {
			BusinessException exception = (BusinessException) ex;
			
			return new ResponseEntity<Object>(
				new Message(exception.getMessageCode(), MessageSeverity.ERROR, exception.getMessage()), exception.getHttpStatus()
			);
		}
		return response;
	}
}
