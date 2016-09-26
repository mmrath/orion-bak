package uy.montdeo.orion.test;

public interface ModuleSupport extends CommonSupport {
	
	public String TEMPLATE_URL_COUNTRIES					= "/services/rest/countries";
	public String TEMPLATE_URL_COUNTRY						= "/services/rest/countries/{id}";
	public String TEMPLATE_URL_STATES 						= "/services/rest/states";
	public String TEMPLATE_URL_STATE 						= "/services/rest/states/{id}";
	public String TEMPLATE_URL_STATE_COUNTRY 				= "/services/rest/states/{id}/country";
	public String TEMPLATE_URL_CITIES						= "/services/rest/cities";
	public String TEMPLATE_URL_CITY 						= "/services/rest/cities/{id}";
	public String TEMPLATE_URL_CITY_STATE 					= "/services/rest/cities/{id}/state";
	public String TEMPLATE_URL_ADDRESSES					= "/services/rest/addresses";
	public String TEMPLATE_URL_ADDRESS				 		= "/services/rest/addresses/{id}";
	public String TEMPLATE_URL_ADDRESS_CITY 				= "/services/rest/addresses/{id}/city";
	
	public String TEMPLATE_URL_DOCUMENTS				 	= "/services/rest/documents";
	public String TEMPLATE_URL_DOCUMENT						= "/services/rest/documents/{id}";
	public String TEMPLATE_URL_DOCUMENT_COUNTRY				= "/services/rest/documents/{id}/country";
	public String TEMPLATE_URL_DOCUMENT_DOCTYPE				= "/services/rest/documents/{id}/type";
	public String TEMPLATE_URL_DOCUMENT_TYPES				= "/services/rest/document-types";
	public String TEMPLATE_URL_DOCUMENT_TYPE				= "/services/rest/document-types/{id}";
	public String TEMPLATE_URL_DOCUMENT_FORMATS			 	= "/services/rest/document-formats";
	public String TEMPLATE_URL_DOCUMENT_FORMAT				= "/services/rest/document-formats/{id}";
	public String TEMPLATE_URL_DOCUMENT_FORMAT_COUNTRY		= "/services/rest/document-formats/{id}/country";
	public String TEMPLATE_URL_DOCUMENT_FORMAT_TYPE			= "/services/rest/document-formats/{id}/type";
		
	public String CODE_ALPHA2				= "UY";
	public String CODE_ALPHA3				= "URY";
	public String CODE_NUMERIC				= "858";
	
	public String CODE_DOCUMENT				= "01.111.111-1";
}
