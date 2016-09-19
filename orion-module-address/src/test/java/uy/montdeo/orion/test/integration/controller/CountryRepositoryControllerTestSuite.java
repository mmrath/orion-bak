package uy.montdeo.orion.test.integration.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static uy.montdeo.orion.model.MessageCode.ENTITY_NOT_FOUND;
import static uy.montdeo.orion.model.MessageSeverity.ERROR;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import uy.montdeo.orion.database.entity.Country;
import uy.montdeo.orion.database.repository.CountryRepository;
import uy.montdeo.orion.test.ModuleAddressSupport;
import uy.montdeo.orion.test.AbstractRestIntegrationTestSupport;

/**
 * Tests for verifying the correct response of the platform REST endpoints for the {@link Country} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractRestIntegrationTestSupport
 * @see CountryRepository
 */
public class CountryRepositoryControllerTestSuite extends AbstractRestIntegrationTestSupport implements ModuleAddressSupport {
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		this.url = TEMPLATE_URL_COUNTRIES;
	}
	
	/****************************************************
	 * 					LIST COUNTRIES
	 ****************************************************/
	
	@Test
	public void testListCountries_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListCountries_shouldReturnResults() {
		try {
			String body = template.getForObject(getEndpoint(), String.class);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);

			List<Country> content = context.read("$._embedded.countries");
			
			assertNotNull("Results - Entity list cannot be null", content);
			
			assertThat("Results - Entity list does not match with the expected number", content, iterableWithSize(239));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/****************************************************
	 * 				GET COUNTRY BY ID
	 ****************************************************/
		
	@Test
	public void testGetCountry_withValidId_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetCountry_withValidId_shouldReturnResult() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_COUNTRY), String.class, ID_000001);
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
	public void testGetCountry_withInvalidId_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_COUNTRY), String.class, ID_100000);
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
	 * 			GET COUNTRY BY ALPHA 2 CODE
	 ****************************************************/
	
	@Test
	public void testGetCountry_withValidAlpha2Code_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetCountry_withValidAlpha2Code_shouldReturnResult() {
		try {
			String body = template.getForObject(getSearchByAlpha2CodeEndpoint(), String.class, CODE_ALPHA2);
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
			assertThat("Results - ID does not match with the ID passed", id, is(ID_000001));
			assertThat("Results - Alpha 2 code does not match with the code passed", alpha2, is(CODE_ALPHA2));
			
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
	public void testGetCountry_withinvalidAlpha2Code_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getSearchByAlpha2CodeEndpoint(), String.class, TEXT_DUMMY);
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
			assertThat("Results - Description does not match with the expected value", description, is(NOT_FOUND_VALUE_MSG));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/****************************************************
	 * 			GET COUNTRY BY ALPHA 3 CODE
	 ****************************************************/
	
	@Test
	public void testGetCountry_withValidAlpha3Code_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetCountry_withValidAlpha3Code_shouldReturnResult() {
		try {
			String body = template.getForObject(getSearchByAlpha3CodeEndpoint(), String.class, CODE_ALPHA3);
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
			assertThat("Results - ID does not match with the ID passed", id, is(ID_000001));
			assertThat("Results - Alpha 3 code does not match with the code passed", alpha3, is(CODE_ALPHA3));
			
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
	public void testGetCountry_withInvalidAlpha3Code_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getSearchByAlpha3CodeEndpoint(), String.class, TEXT_DUMMY);
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
			assertThat("Results - Description does not match with the expected value", description, is(NOT_FOUND_VALUE_MSG));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/****************************************************
	 * 			GET COUNTRY BY NUMERIC CODE
	 ****************************************************/
	
	@Test
	public void testGetCountry_withValidNumericCode_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetCountry_withValidNumericCode_shouldReturnResult() {
		try {
			String body = template.getForObject(getSearchByNumericCodeEndpoint(), String.class, CODE_NUMERIC);
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
			assertThat("Results - ID does not match with the ID passed", id, is(ID_000001));
			assertThat("Results - Numeric code does not match with the code passed", numeric, is(CODE_NUMERIC));
			
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
	public void testGetCountry_withInvalidNumericCode_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getSearchByNumericCodeEndpoint(), String.class, TEXT_DUMMY);
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
			assertThat("Results - Description does not match with the expected value", description, is(NOT_FOUND_VALUE_MSG));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
				
	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}
	
	/*  	PRIVATE METHODS		 */
	private String getSearchByAlpha2CodeEndpoint() {
		return getEndpoint().concat("/search/withAlpha2Code?code={code}");
	}
	
	private String getSearchByAlpha3CodeEndpoint() {
		return getEndpoint().concat("/search/withAlpha3Code?code={code}");
	}
	
	private String getSearchByNumericCodeEndpoint() {
		return getEndpoint().concat("/search/withNumericCode?code={code}");
	}
}
