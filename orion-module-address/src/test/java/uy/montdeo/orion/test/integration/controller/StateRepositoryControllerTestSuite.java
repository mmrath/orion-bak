package uy.montdeo.orion.test.integration.controller;

import static org.hamcrest.Matchers.emptyIterable;
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

import uy.montdeo.orion.database.entity.State;
import uy.montdeo.orion.database.repository.StateRepository;
import uy.montdeo.orion.test.ModuleAddressSupport;
import uy.montdeo.orion.test.AbstractRestIntegrationTestSupport;

/**
 * Tests for verifying the correct response of the platform REST endpoints for the {@link State} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractRestIntegrationTestSupport
 * @see StateRepository
 */
public class StateRepositoryControllerTestSuite extends AbstractRestIntegrationTestSupport implements ModuleAddressSupport {
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		this.url = TEMPLATE_URL_STATES;
	}
	
	/****************************************************
	 * 				LIST STATES FOR COUNTRY
	 ****************************************************/
	
	@Test
	public void testListStatesForCountry_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListStatesForCountry_shouldReturnResults() {
		try {
			String body = template.getForObject(getSearchForCountryEndpoint(), String.class, ID_000001);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);

			List<State> content = context.read("$._embedded.states");
			
			assertNotNull("Results - Entity list cannot be null", content);
			assertThat("Results - Entity list does not match with the expected number", content, iterableWithSize(19));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListStatesForCountry_shouldReturnEmptyResponse() {
		try {
			String body = template.getForObject(getSearchForCountryEndpoint(), String.class, ID_100000);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);

			List<State> content = context.read("$._embedded.states");
			
			assertNotNull("Results - Entity list cannot be null", content);
			assertThat("Results - Entity list does not match with the expected number", content, emptyIterable());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/****************************************************
	 * 				GET STATE BY ID
	 ****************************************************/
		
	@Test
	public void testGetState_withValidId_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetState_withValidId_shouldReturnResult() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_STATE), String.class, ID_000001);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);
			
			Integer id = context.read("$.id");
			String name = context.read("$.name");
						
			assertNotNull("Results - ID cannot be null", id);
			assertNotNull("Results - Name cannot be null", name);
			assertThat("Results ID does not match with the ID passed", id, is(ID_000001));
			
			String hrefSelf = context.read("$._links.self.href");
			String hrefState = context.read("$._links.state.href");
			String hrefCountry = context.read("$._links.country.href");
			
			assertNotNull("Results - HREF Self URL cannot be null", hrefSelf);
			assertNotNull("Results - HREF State URL cannot be null", hrefState);
			assertNotNull("Results - HREF Country URL cannot be null", hrefCountry);
			
			assertThat("Results - HREF Self URL does not match with the expected url", hrefSelf, is(getExpectedEndpoint(TEMPLATE_URL_STATE)));
			assertThat("Results - HREF State URL does not match with the expected url", hrefState, is(getExpectedEndpoint(TEMPLATE_URL_STATE)));
			assertThat("Results - HREF Country URL does not match with the expected url", hrefCountry, is(getExpectedEndpoint(TEMPLATE_URL_STATE_COUNTRY)));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetState_withInvalidId_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_STATE), String.class, ID_100000);
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
	 * 				GET COUNTRY FOR STATE
	 ****************************************************/
	
	@Test
	public void testGetStateCountry_withValidId_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetStateCountry_withValidId_shouldReturnResult() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_STATE_COUNTRY), String.class, ID_000001);
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
	public void testGetStateCountry_withInvalidId_shouldReturnObjectNotFound() {
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
	private String getSearchForCountryEndpoint() {
		return getEndpoint().concat("/search/forCountry?id={id}");
	}
	
}
