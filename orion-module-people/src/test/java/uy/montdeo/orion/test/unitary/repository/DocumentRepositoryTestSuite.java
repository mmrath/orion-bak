package uy.montdeo.orion.test.unitary.repository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uy.montdeo.orion.database.entity.Document;
import uy.montdeo.orion.database.repository.DocumentRepository;
import uy.montdeo.orion.exception.EntityNotFoundException;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractCrudRepositoryTestSupport;
import uy.montdeo.orion.test.ModuleSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link Document} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractCrudRepositoryTestSupport
 * @see DocumentRepository
 */
public class DocumentRepositoryTestSuite extends AbstractCrudRepositoryTestSupport<DocumentRepository, Document> implements ModuleSupport {
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		
	}
	
	/****************************************************
	 * 			GET DOCUMENT BY VALUE
	 ****************************************************/
	
	@Test
	public void testGetByValue_withValidValue_shouldReturnResult() {
		try {
			Document existing = repository.getByCountryIdAndTypeIdAndValue(ID_000001, ID_000001, CODE_DOCUMENT);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Value cannot be null", existing.getValue());
			assertNotNull("Results - Type code cannot be null", existing.getType());
			assertNotNull("Results - Country code cannot be null", existing.getCountry());
			assertThat("Results Alpha 2 code does not match with the code passed", existing.getValue(), is(CODE_DOCUMENT));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testGetByValue_withInvalidValue_shouldThrowException() {
		repository.getByCountryIdAndTypeIdAndValue(ID_000001, ID_000001, TEXT_DUMMY);
	}
	
	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}
	
}
