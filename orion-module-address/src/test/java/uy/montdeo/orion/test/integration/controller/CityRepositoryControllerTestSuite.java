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

import uy.montdeo.orion.database.entity.City;
import uy.montdeo.orion.database.entity.State;
import uy.montdeo.orion.database.repository.CityRepository;
import uy.montdeo.orion.test.ModuleAddressSupport;
import uy.montdeo.orion.test.AbstractRestIntegrationTestSupport;

/**
 * Tests for verifying the correct response of the platform REST endpoints for the {@link City} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractRestIntegrationTestSupport
 * @see CityRepository
 */
public class CityRepositoryControllerTestSuite extends AbstractRestIntegrationTestSupport implements ModuleAddressSupport {
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		this.url = TEMPLATE_URL_CITIES;
	}
	
	/****************************************************
	 * 				LIST CITIES FOR STATE
	 ****************************************************/
		
	@Test
	public void testListCitiesForState_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListCitiesForState_shouldReturnResults() {
		try {
			String body = template.getForObject(getSearchForStateEndpoint(), String.class, ID_000001);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);

			List<State> content = context.read("$._embedded.cities");
			
			assertNotNull("Results - Entity list cannot be null", content);
			assertThat("Results - Entity list does not match with the expected number", content, iterableWithSize(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListCitiesForState_shouldReturnEmptyResponse() {
		try {
			String body = template.getForObject(getSearchForStateEndpoint(), String.class, ID_100000);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);

			List<State> content = context.read("$._embedded.cities");
			
			assertNotNull("Results - Entity list cannot be null", content);
			assertThat("Results - Entity list does not match with the expected number", content, emptyIterable());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/****************************************************
	 * 				GET CITY BY ID
	 ****************************************************/
	
	@Test
	public void testGetCity_withValidId_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetCity_withValidId_shouldReturnResult() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_CITY), String.class, ID_000001);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);
			
			Integer id = context.read("$.id");
			String name = context.read("$.name");
			Double latitude = context.read("$.latitude", Double.class);
			Double longitude = context.read("$.longitude", Double.class);
						
			assertNotNull("Results - ID cannot be null", id);
			assertNotNull("Results - Name cannot be null", name);
			assertNotNull("Results - Latitude cannot be null", latitude);
			assertNotNull("Results - Longitude cannot be null", longitude);
			assertThat("Results ID does not match with the ID passed", id, is(ID_000001));
			
			String hrefSelf = context.read("$._links.self.href");
			String hrefCity = context.read("$._links.city.href");
			String hrefState = context.read("$._links.state.href");
			
			assertNotNull("Results - HREF Self URL cannot be null", hrefSelf);
			assertNotNull("Results - HREF City URL cannot be null", hrefCity);
			assertNotNull("Results - HREF State URL cannot be null", hrefState);
			
			assertThat("Results - HREF Self URL does not match with the expected url", hrefSelf, is(getExpectedEndpoint(TEMPLATE_URL_CITY)));
			assertThat("Results - HREF City URL does not match with the expected url", hrefCity, is(getExpectedEndpoint(TEMPLATE_URL_CITY)));
			assertThat("Results - HREF State URL does not match with the expected url", hrefState, is(getExpectedEndpoint(TEMPLATE_URL_CITY_STATE)));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetCity_withInvalidId_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_CITY), String.class, ID_100000);
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
	 * 				GET STATE FOR CITY
	 ****************************************************/
	
	@Test
	public void testGetCityState_withValidId_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetCityState_withValidId_shouldReturnResult() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_CITY_STATE), String.class, ID_000001);
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
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetCityState_withInvalidId_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_CITY_STATE), String.class, ID_100000);
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
	private String getSearchForStateEndpoint() {
		return getEndpoint().concat("/search/forState?id={id}");
	}
}
