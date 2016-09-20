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

import uy.montdeo.orion.database.entity.DocumentType;
import uy.montdeo.orion.database.entity.State;
import uy.montdeo.orion.database.repository.DocumentTypeRepository;
import uy.montdeo.orion.test.AbstractRestIntegrationTestSupport;
import uy.montdeo.orion.test.ModuleAddressSupport;
import uy.montdeo.orion.test.ModulePeopleSupport;

/**
 * Tests for verifying the correct response of the platform REST endpoints for the {@link DocumentType} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractRestIntegrationTestSupport
 * @see DocumentTypeRepository
 */
public class DocumentTypeRepositoryControllerTestSuite extends AbstractRestIntegrationTestSupport implements ModulePeopleSupport, ModuleAddressSupport {

	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		this.url = TEMPLATE_URL_DOCUMENT_TYPES;
	}
	
	/****************************************************
	 * 				LIST DOCUMENT TYPES
	 ****************************************************/
	
	@Test
	public void testListDocumentTypes_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListDocumentTypes_shouldReturnResults() {
		try {
			String body = template.getForObject(getEndpoint(), String.class);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);

			List<State> content = context.read("$._embedded.types");
			
			assertNotNull("Results - Entity list cannot be null", content);
			assertThat("Results - Entity list does not match with the expected number", content, iterableWithSize(6));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
		
	/****************************************************
	 * 			GET DOCUMENT TYPE BY ID
	 ****************************************************/
		
	@Test
	public void testGetDocumentType_withValidId_shouldReturnUnauthorized() {
		try {
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetDocumentType_withValidId_shouldReturnResult() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_DOCUMENT_TYPE), String.class, ID_000001);
			assertNotNull("Results cannot be null", body);
			
			ReadContext context = JsonPath.parse(body, configuration);
			
			Integer id = context.read("$.id");
			String name = context.read("$.name");
						
			assertNotNull("Results - ID cannot be null", id);
			assertNotNull("Results - Name cannot be null", name);
			assertThat("Results ID does not match with the ID passed", id, is(ID_000001));
			
			String hrefSelf = context.read("$._links.self.href");
			String hrefType = context.read("$._links.type.href");
			
			assertNotNull("Results - HREF Self URL cannot be null", hrefSelf);
			assertNotNull("Results - HREF Type URL cannot be null", hrefType);
			
			assertThat("Results - HREF Self URL does not match with the expected url", hrefSelf, is(getExpectedEndpoint(TEMPLATE_URL_DOCUMENT_TYPE)));
			assertThat("Results - HREF Type URL does not match with the expected url", hrefType, is(getExpectedEndpoint(TEMPLATE_URL_DOCUMENT_TYPE)));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testGetDocumentType_withInvalidId_shouldReturnObjectNotFound() {
		try {
			String body = template.getForObject(getEndpoint(TEMPLATE_URL_DOCUMENT_TYPE), String.class, ID_100000);
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
