package uy.montdeo.orion.model;


/**
 * Enumeration that give to the current situation an ID to identify the root cause more easily.
 * 
 * @author fabian.lobo
 * @since 1.0
 */
public enum MessageCode {

	/* GENERAL */
	OK("GRL-00000"),
	ERROR("GRL-99999"),
	
	/* DATABASE */
	ENTITY_NOT_FOUND("DTB-00001"),
	
	/* BUSINESS */
	INVALID_DATA("BUS-00001"),
	
	/* SECURITY */
	UNAUTHENTICATED("SEC-00001"),
	UNAUTHORIZED_ACCESS("SEC-00002");
	
	private String code;
	
	MessageCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}
