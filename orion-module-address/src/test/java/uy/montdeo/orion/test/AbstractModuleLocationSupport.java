package uy.montdeo.orion.test;

public interface AbstractModuleLocationSupport {
	
	public String TEMPLATE_URL_COUNTRIES		= "/services/rest/countries";
	public String TEMPLATE_URL_COUNTRY			= "/services/rest/countries/{id}";
	public String TEMPLATE_URL_STATES 			= "/services/rest/states";
	public String TEMPLATE_URL_STATE 			= "/services/rest/states/{id}";
	public String TEMPLATE_URL_STATE_COUNTRY 	= "/services/rest/states/{id}/country";
	public String TEMPLATE_URL_CITIES			= "/services/rest/cities";
	public String TEMPLATE_URL_CITY 			= "/services/rest/cities/{id}";
	public String TEMPLATE_URL_CITY_STATE 		= "/services/rest/cities/{id}/state";
	public String TEMPLATE_URL_ADDRESSES		= "/services/rest/addresses";
	public String TEMPLATE_URL_ADDRESS	 		= "/services/rest/addresses/{id}";
	public String TEMPLATE_URL_ADDRESS_CITY 	= "/services/rest/addresses/{id}/city";
		
	public final String CODE_ALPHA2				= "UY";
	public final String CODE_ALPHA3				= "URY";
	public final String CODE_NUMERIC			= "858";
}
