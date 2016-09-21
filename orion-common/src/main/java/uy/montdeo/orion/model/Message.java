package uy.montdeo.orion.model;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Class that represents a message sent to the user (both in REST or SOAP services).
 * 
 * @author fabian.lobo
 * @since 1.0
 */
@JsonInclude(value = NON_EMPTY)
@JsonPropertyOrder(value = {"code", "severity", "description"})
@JsonRootName(value = "message")
public class Message implements Serializable {

	private static final long serialVersionUID = 8852570689192792008L;

	/*  	FIELDS		 */
	private final String code;
	
	private final String severity;
	
	private final String description;
	
	/*  	GETTERS AND SETTERS 		*/
	public String getCode() {								return code;								}
			
	public String getSeverity() {							return severity;							}
			
	public String getDescription() {						return description;							}	
		
	/*  	CONSTRUCTORS		 */
	public Message() {
		this.code = MessageCode.OK.toString();
		this.severity = MessageSeverity.INFO.toString();
		this.description = "OK";
	}

	public Message(MessageCode code, MessageSeverity severity, String description) {
		super();
		this.code = code.toString();
		this.severity = severity.toString();
		this.description = description;
	}
}
