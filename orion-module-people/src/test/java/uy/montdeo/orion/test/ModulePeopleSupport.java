package uy.montdeo.orion.test;

public interface ModulePeopleSupport {
	
	public String TEMPLATE_URL_DOCUMENTS				 	= "/services/rest/documents";
	public String TEMPLATE_URL_DOCUMENT						= "/services/rest/documents/{id}";
	public String TEMPLATE_URL_DOCUMENT_COUNTRY				= "/services/rest/documents/{id}/country";
	public String TEMPLATE_URL_DOCUMENT_DOCTYPE				= "/services/rest/documents/{id}/type";
	public String TEMPLATE_URL_DOCUMENT_TYPES				= "/services/rest/documents/types";
	public String TEMPLATE_URL_DOCUMENT_TYPE				= "/services/rest/documents/types/{id}";
	public String TEMPLATE_URL_DOCUMENT_FORMATS			 	= "/services/rest/documents/formats";
	public String TEMPLATE_URL_DOCUMENT_FORMAT				= "/services/rest/documents/formats/{id}";
	public String TEMPLATE_URL_DOCUMENT_FORMAT_COUNTRY		= "/services/rest/documents/formats/{id}/country";
	public String TEMPLATE_URL_DOCUMENT_FORMAT_TYPE			= "/services/rest/documents/formats/{id}/type";
	
	public final String CODE_VALUE				= "01.111.111-1";

}
