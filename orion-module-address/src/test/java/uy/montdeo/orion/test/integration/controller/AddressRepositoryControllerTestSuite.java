package uy.montdeo.orion.test.integration.controller;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;
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

import uy.montdeo.orion.database.entity.Address;
import uy.montdeo.orion.database.repository.AddressRepository;
import uy.montdeo.orion.test.ModuleAddressSupport;
import uy.montdeo.orion.test.AbstractRestIntegrationTestSupport;

/**
 * Tests for verifying the correct response of the platform REST endpoints for the {@link Address} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractRestIntegrationTestSupport
 * @see AddressRepository
 */
public class AddressRepositoryControllerTestSuite extends AbstractRestIntegrationTestSupport implements ModuleAddressSupport {
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		this.url = TEMPLATE_URL_ADDRESSES;
	}
	
	/****************************************************
	 * 				LIST ADDRESSES
	 ****************************************************/
	
	@Test
	public void testListAddresses_withoutPagination_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListAddresses_withoutPagination_shouldReturnResults() {
		try {
			String body = template.getForObject(getEndpoint(), String.class);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);

			List<Address> content = context.read("$._embedded.addresses");
			
			Integer pageSize = context.read("$.page.size");
			Integer pageNumber = context.read("$.page.number");
			Long totalElements = context.read("$.page.totalElements", Long.class);
			Integer totalPages = context.read("$.page.totalPages");
			
			assertNotNull("Results - Entity list cannot be null", content);
			assertNotNull("Results - Page number cannot be null", pageNumber);
			assertNotNull("Results - Page size cannot be null", pageSize);
			assertNotNull("Results - Total elements cannot be null", totalElements);
			assertNotNull("Results - Total pages cannot be null", totalPages);
			
			assertThat("Results - Entity list does not match with the expected number", content, iterableWithSize(TEN.intValue()));
			assertThat("Results - Page number does not match with the expected number", pageNumber, is(ZERO.intValue()));
			assertThat("Results - Page size does not match with the expected number", pageSize, is(TEN.intValue()));
			assertThat("Results - Total elements does not match with the expected number", totalElements, is(VAL_000025));
			assertThat("Results - Total pages does not match with the expected number", totalPages, is(VAL_000003));
			
			String hrefFirst = context.read("$._links.first.href");
			String hrefSelf = context.read("$._links.self.href");
			String hrefNext = context.read("$._links.next.href");
			String hrefLast = context.read("$._links.last.href");
			
			assertNotNull("Results - HREF First URL cannot be null", hrefFirst);
			assertNotNull("Results - HREF Self URL cannot be null", hrefSelf);
			assertNotNull("Results - HREF Next URL cannot be null", hrefNext);
			assertNotNull("Results - HREF Last URL cannot be null", hrefLast);
			
			assertThat("Results - HREF First URL does not match with the expected url", hrefFirst, is(getFirst()));
			assertThat("Results - HREF Self URL does not match with the expected url", hrefSelf, is(getEndpoint()));
			assertThat("Results - HREF Next URL does not match with the expected url", hrefNext, is(getNext()));
			assertThat("Results - HREF Last URL does not match with the expected url", hrefLast, is(getLast(totalPages)));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListAddresses_withPagination_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListAddresses_withPagination_shouldReturnResults() {
		try {
			String body = template.getForObject(getPaginatedUrl(0, 1), String.class);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);

			List<Address> content = context.read("$._embedded.addresses");
						
			Integer pageSize = context.read("$.page.size");
			Integer pageNumber = context.read("$.page.number");
			Long totalElements = context.read("$.page.totalElements", Long.class);
			Integer totalPages = context.read("$.page.totalPages");
			
			assertNotNull("Results - Entity list cannot be null", content);
			assertNotNull("Results - Page number cannot be null", pageNumber);
			assertNotNull("Results - Page size cannot be null", pageSize);
			assertNotNull("Results - Total elements cannot be null", totalElements);
			assertNotNull("Results - Total pages cannot be null", totalPages);
			
			assertThat("Results - Entity list does not match with the expected number", content, iterableWithSize(ONE.intValue()));
			assertThat("Results - Page number does not match with the expected number", pageNumber, is(ZERO.intValue()));
			assertThat("Results - Page size does not match with the expected number", pageSize, is(ONE.intValue()));
			assertThat("Results - Total elements does not match with the expected number", totalElements, is(VAL_000025));
			assertThat("Results - Total pages does not match with the expected number", totalPages, is(VAL_000025.intValue()));
			
			String hrefFirst = context.read("$._links.first.href");
			String hrefSelf = context.read("$._links.self.href");
			String hrefNext = context.read("$._links.next.href");
			String hrefLast = context.read("$._links.last.href");
			
			assertNotNull("Results - HREF First URL cannot be null", hrefFirst);
			assertNotNull("Results - HREF Self URL cannot be null", hrefSelf);
			assertNotNull("Results - HREF Next URL cannot be null", hrefNext);
			assertNotNull("Results - HREF Last URL cannot be null", hrefLast);
			
			assertThat("Results - HREF First URL does not match with the expected url", hrefFirst, is(getFirst()));
			assertThat("Results - HREF Self URL does not match with the expected url", hrefSelf, is(getEndpoint()));
			assertThat("Results - HREF Next URL does not match with the expected url", hrefNext, is(getNext()));
			assertThat("Results - HREF Last URL does not match with the expected url", hrefLast, is(getLast(totalPages)));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
		
	/****************************************************
	 * 				GET ADDRESS BY ID
	 ****************************************************/
	
	@Test
	public void testGetAddress_withValidId_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetAddress_withValidId_shouldReturnResult() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_ADDRESS), String.class, ID_000001);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);
			
			Integer id = context.read("$.id");
			String street = context.read("$.street");
			String number = context.read("$.number");
			Double latitude = context.read("$.latitude", Double.class);
			Double longitude = context.read("$.longitude", Double.class);
						
			assertNotNull("Results - ID cannot be null", id);
			assertNotNull("Results - Street cannot be null", street);
			assertNotNull("Results - Number cannot be null", number);
			assertNotNull("Results - Latitude cannot be null", latitude);
			assertNotNull("Results - Longitude cannot be null", longitude);
			assertThat("Results ID does not match with the ID passed", id, is(ID_000001));
			
			String hrefSelf = context.read("$._links.self.href");
			String hrefAddress = context.read("$._links.address.href");
			String hrefCity = context.read("$._links.city.href");
			
			assertNotNull("Results - HREF Self URL cannot be null", hrefSelf);
			assertNotNull("Results - HREF Address URL cannot be null", hrefAddress);
			assertNotNull("Results - HREF City URL cannot be null", hrefCity);
			
			assertThat("Results - HREF Self URL does not match with the expected url", hrefSelf, is(getExpectedEndpoint(TEMPLATE_URL_ADDRESS)));
			assertThat("Results - HREF Address URL does not match with the expected url", hrefAddress, is(getExpectedEndpoint(TEMPLATE_URL_ADDRESS)));
			assertThat("Results - HREF City URL does not match with the expected url", hrefCity, is(getExpectedEndpoint(TEMPLATE_URL_ADDRESS_CITY)));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetAddress_withInvalidId_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_ADDRESS), String.class, ID_100000);
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
	 * 				GET CITY FOR ADDRESS
	 ****************************************************/
	
	@Test
	public void testGetAddressCity_withValidId_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetAddressCity_withValidId_shouldReturnResult() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_ADDRESS_CITY), String.class, ID_000001);
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
			
			String hrefSelf = context.read("$._links.self.href");
			String hrefCity = context.read("$._links.city.href");
			
			assertNotNull("Results - HREF Self URL cannot be null", hrefSelf);
			assertNotNull("Results - HREF City URL cannot be null", hrefCity);
			
			assertThat("Results - HREF Self URL does not match with the expected url", hrefSelf, is(getExpectedEndpoint(TEMPLATE_URL_CITY)));
			assertThat("Results - HREF City URL does not match with the expected url", hrefCity, is(getExpectedEndpoint(TEMPLATE_URL_CITY)));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetAddressCity_withInvalidId_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_ADDRESS_CITY), String.class, ID_100000);
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
}
