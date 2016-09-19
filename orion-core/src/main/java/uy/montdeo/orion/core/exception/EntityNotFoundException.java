package uy.montdeo.orion.core.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static uy.montdeo.orion.model.MessageCode.ENTITY_NOT_FOUND;

/**
 * Exception to be thrown when a user is not found.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
public class EntityNotFoundException extends BusinessException {

	private static final long serialVersionUID = -7323534321922212037L;
	
	public EntityNotFoundException(String message) {
		super(message, ENTITY_NOT_FOUND, NOT_FOUND);
	}

}
