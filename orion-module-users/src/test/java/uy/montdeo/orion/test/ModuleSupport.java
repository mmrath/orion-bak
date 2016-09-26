package uy.montdeo.orion.test;

public interface ModuleSupport extends CommonSupport {
	
	public String TEMPLATE_URL_USERS				= "/services/rest/users";
	public String TEMPLATE_URL_USER					= "/services/rest/users/{id}";
	public String TEMPLATE_URL_USER_ROLE			= "/services/rest/users/{id}/role";
	public String TEMPLATE_URL_ROLES 				= "/services/rest/roles";
	public String TEMPLATE_URL_ROLE 				= "/services/rest/roles/{id}";
	public String TEMPLATE_URL_ROLE_PERMISSIONS 	= "/services/rest/roles/{id}/permissions";
	public String TEMPLATE_URL_PERMISSIONS			= "/services/rest/permissions";
	public String TEMPLATE_URL_PERMISSION			= "/services/rest/permissions/{id}";

	public String TEXT_NAME				= "ORION";
	public String TEXT_USERNAME			= "admin@orion.uy";

}
