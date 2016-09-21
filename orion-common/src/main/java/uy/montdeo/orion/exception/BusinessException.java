package uy.montdeo.orion.exception;

import org.springframework.http.HttpStatus;

import uy.montdeo.orion.model.MessageCode;

/**
 * Abstract class in top of the application exceptions' hierarchy.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
public abstract class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -3366221034322947488L;
	
	private final MessageCode code;
	
	private final HttpStatus status;
	
	public BusinessException(String message, MessageCode code, HttpStatus status) {
		super(message);
		
		this.code = code;
		this.status = status;
	}

	/**
	 * @return the {@link MessageCode} related to this exception
	 */
	public MessageCode getMessageCode() {
		return code;
	}
	
	/**
	 * 
	 * @return the {@link HttpStatus} related to this exception
	 */
	public HttpStatus getHttpStatus() {
		return status;
	}
	
}
