package uy.montdeo.orion.test.integration.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static uy.montdeo.orion.model.MessageCode.ENTITY_NOT_FOUND;
import static uy.montdeo.orion.model.MessageSeverity.ERROR;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import uy.montdeo.orion.database.entity.DocumentFormat;
import uy.montdeo.orion.database.repository.DocumentFormatRepository;
import uy.montdeo.orion.test.AbstractRestIntegrationTestSupport;
import uy.montdeo.orion.test.ModuleAddressSupport;
import uy.montdeo.orion.test.ModulePeopleSupport;

/**
 * Tests for verifying the correct response of the platform REST endpoints for the {@link DocumentFormat} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractRestIntegrationTestSupport
 * @see DocumentFormatRepository
 */
public class DocumentFormatRepositoryControllerTestSuite extends AbstractRestIntegrationTestSupport implements ModulePeopleSupport, ModuleAddressSupport {

	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		this.url = TEMPLATE_URL_DOCUMENT_FORMATS;
	}
	
	/****************************************************
	 * 			GET DOCUMENT FORMAT BY ID
	 ****************************************************/
		
	@Test
	public void testGetDocumentFormat_withValidId_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetDocumentFormat_withValidId_shouldReturnResult() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_DOCUMENT_FORMAT), String.class, ID_000001);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);
			
			Integer id = context.read("$.id");
			String pattern = context.read("$.pattern");
						
			assertNotNull("Results - ID cannot be null", id);
			assertNotNull("Results - Pattern cannot be null", pattern);
			assertThat("Results ID does not match with the ID passed", id, is(ID_000001));
			
			String hrefSelf = context.read("$._links.self.href");
			String hrefFormat = context.read("$._links.format.href");
			String hrefCountry = context.read("$._links.country.href");
			String hrefType = context.read("$._links.type.href");
			
			assertNotNull("Results - HREF Self URL cannot be null", hrefSelf);
			assertNotNull("Results - HREF Format URL cannot be null", hrefFormat);
			assertNotNull("Results - HREF Country URL cannot be null", hrefCountry);
			assertNotNull("Results - HREF Type URL cannot be null", hrefType);
			
			assertThat("Results - HREF Self URL does not match with the expected url", hrefSelf, is(getExpectedEndpoint(TEMPLATE_URL_DOCUMENT_FORMAT)));
			assertThat("Results - HREF Format URL does not match with the expected url", hrefFormat, is(getExpectedEndpoint(TEMPLATE_URL_DOCUMENT_FORMAT)));
			assertThat("Results - HREF Country URL does not match with the expected url", hrefCountry, is(getExpectedEndpoint(TEMPLATE_URL_DOCUMENT_FORMAT_COUNTRY)));
			assertThat("Results - HREF Type URL does not match with the expected url", hrefType, is(getExpectedEndpoint(TEMPLATE_URL_DOCUMENT_FORMAT_TYPE)));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetDocumentFormat_withInvalidId_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_DOCUMENT_FORMAT), String.class, ID_100000);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);
			
			String code = context.read("$.code");
			String severity = context.read("$.severity");
			String description = context.read("$.description");
			
			assertNotNull("Results - Code cannot be null", code);
			assertNotNull("Results - Severity cannot be null", severity);
			assertNotNull("Results - Description cannot be null", description);
			
			assertThat("Results - Code does not match with the expected value", code, is(ENTITY_NOT_FOUND.toString()));
			assertThat("Results - Severity does not match with the expected value", severity, is(ERROR.toString()));
			assertThat("Results - Description does not match with the expected value", description, is(NOT_FOUND_ID_MSG));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/****************************************************
	 * 		GET DOCUMENT FORMAT BY COUNTRY AND TYPE
	 ****************************************************/
		
	@Test
	public void testGetDocumentFormatForCountryAndType_withValidId_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetDocumentFormatForCountryAndType_withValidId_shouldReturnResult() {
		try {
			String body = template.getForObject(getSearchForCountryAndTypeEndpoint(), String.class, ID_000001, ID_000001);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);
			
			Integer id = context.read("$.id");
			String pattern = context.read("$.pattern");
						
			assertNotNull("Results - ID cannot be null", id);
			assertNotNull("Results - Pattern cannot be null", pattern);
			assertThat("Results ID does not match with the ID passed", id, is(ID_000001));
			
			String hrefSelf = context.read("$._links.self.href");
			String hrefFormat = context.read("$._links.format.href");
			String hrefCountry = context.read("$._links.country.href");
			String hrefType = context.read("$._links.type.href");
			
			assertNotNull("Results - HREF Self URL cannot be null", hrefSelf);
			assertNotNull("Results - HREF Format URL cannot be null", hrefFormat);
			assertNotNull("Results - HREF Country URL cannot be null", hrefCountry);
			assertNotNull("Results - HREF Type URL cannot be null", hrefType);
			
			assertThat("Results - HREF Self URL does not match with the expected url", hrefSelf, is(getExpectedEndpoint(TEMPLATE_URL_DOCUMENT_FORMAT)));
			assertThat("Results - HREF Format URL does not match with the expected url", hrefFormat, is(getExpectedEndpoint(TEMPLATE_URL_DOCUMENT_FORMAT)));
			assertThat("Results - HREF Country URL does not match with the expected url", hrefCountry, is(getExpectedEndpoint(TEMPLATE_URL_DOCUMENT_FORMAT_COUNTRY)));
			assertThat("Results - HREF Type URL does not match with the expected url", hrefType, is(getExpectedEndpoint(TEMPLATE_URL_DOCUMENT_FORMAT_TYPE)));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetDocumentFormatForCountryAndType_withInvalidId_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getSearchForCountryAndTypeEndpoint(), String.class, ID_100000, ID_100000);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);
			
			String code = context.read("$.code");
			String severity = context.read("$.severity");
			String description = context.read("$.description");
			
			assertNotNull("Results - Code cannot be null", code);
			assertNotNull("Results - Severity cannot be null", severity);
			assertNotNull("Results - Description cannot be null", description);
			
			assertThat("Results - Code does not match with the expected value", code, is(ENTITY_NOT_FOUND.toString()));
			assertThat("Results - Severity does not match with the expected value", severity, is(ERROR.toString()));
			assertThat("Results - Description does not match with the expected value", description, is(NOT_FOUND_ID_MSG));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/****************************************************
	 * 				GET COUNTRY FOR DOCUMENT FORMAT
	 ****************************************************/
	
	@Test
	public void testGetDocumentFormatCountry_withValidId_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetDocumentFormatCountry_withValidId_shouldReturnResult() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_DOCUMENT_FORMAT_COUNTRY), String.class, ID_000001);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);
			
			Integer id = context.read("$.id");
			String name = context.read("$.name");
			String alpha2 = context.read("$.alpha2Code");
			String alpha3 = context.read("$.alpha3Code");
			String numeric = context.read("$.numericCode");
			String phone = context.read("$.phoneCode");
						
			assertNotNull("Results - ID cannot be null", id);
			assertNotNull("Results - Name cannot be null", name);
			assertNotNull("Results - Alpha 2 code cannot be null", alpha2);
			assertNotNull("Results - Alpha 3 code cannot be null", alpha3);
			assertNotNull("Results - Numeric code cannot be null", numeric);
			assertNotNull("Results - Phone code cannot be null", phone);
			assertThat("Results ID does not match with the ID passed", id, is(ID_000001));
			
			String hrefSelf = context.read("$._links.self.href");
			String hrefCountry = context.read("$._links.country.href");
			
			assertNotNull("Results - HREF Self URL cannot be null", hrefSelf);
			assertNotNull("Results - HREF Country URL cannot be null", hrefCountry);
			
			assertThat("Results - HREF Self URL does not match with the expected url", hrefSelf, is(getExpectedEndpoint(TEMPLATE_URL_COUNTRY)));
			assertThat("Results - HREF Country URL does not match with the expected url", hrefCountry, is(getExpectedEndpoint(TEMPLATE_URL_COUNTRY)));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetDocumentFormatCountry_withInvalidId_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_STATE_COUNTRY), String.class, ID_100000);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);
			
			String code = context.read("$.code");
			String severity = context.read("$.severity");
			String description = context.read("$.description");
			
			assertNotNull("Results - Code cannot be null", code);
			assertNotNull("Results - Severity cannot be null", severity);
			assertNotNull("Results - Description cannot be null", description);
			
			assertThat("Results - Code does not match with the expected value", code, is(ENTITY_NOT_FOUND.toString()));
			assertThat("Results - Severity does not match with the expected value", severity, is(ERROR.toString()));
			assertThat("Results - Description does not match with the expected value", description, is(NOT_FOUND_ID_MSG));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}

	/*  	PRIVATE METHODS		 */
	private String getSearchForCountryAndTypeEndpoint() {
		return getEndpoint().concat("/search/forCountryAndType?country={country}&type={type}");
	}
	
}
