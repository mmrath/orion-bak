package uy.montdeo.orion.test.unitary.repository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import uy.montdeo.orion.core.exception.EntityNotFoundException;
import uy.montdeo.orion.database.entity.Document;
import uy.montdeo.orion.database.repository.DocumentRepository;
import uy.montdeo.orion.test.AbstractUnitaryTestSupport;
import uy.montdeo.orion.test.ModulePeopleSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link Document} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractUnitaryTestSupport
 * @see DocumentRepository
 */
public class DocumentRepositoryTestSuite extends AbstractUnitaryTestSupport implements ModulePeopleSupport {
	
	@Autowired
	private DocumentRepository documentRepository;
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		
	}
	
	/****************************************************
	 * 					LIST DOCUMENTS
	 ****************************************************/
	
	@Test
	public void testFindAll_shouldReturnResults() {
		try {
			
			Iterable<Document> results = documentRepository.findAll();
						
			assertNotNull("Results cannot be null", results);
			assertThat("Results contents is not empty", results, iterableWithSize(25));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/****************************************************
	 * 				GET DOCUMENT BY ID
	 ****************************************************/
		
	@Test
	public void testFindOne_withValidId_shouldReturnResult() {
		try {
			Document existing = documentRepository.findOne(ID_000001);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Value cannot be null", existing.getValue());
			assertNotNull("Results - Type code cannot be null", existing.getType());
			assertNotNull("Results - Country code cannot be null", existing.getCountry());
			assertThat("Results ID does not match with the ID passed", existing.getId(), is(ID_000001));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindOne_withInvalidId_shouldThrowException() {
		documentRepository.findOne(ID_100000);
	}
	
	/****************************************************
	 * 			GET DOCUMENT BY VALUE
	 ****************************************************/
	
	@Test
	public void testGetByValue_withValidValue_shouldReturnResult() {
		try {
			Document existing = documentRepository.getByCountryIdAndTypeIdAndValue(ID_000001, ID_000001, CODE_VALUE);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Value cannot be null", existing.getValue());
			assertNotNull("Results - Type code cannot be null", existing.getType());
			assertNotNull("Results - Country code cannot be null", existing.getCountry());
			assertThat("Results Alpha 2 code does not match with the code passed", existing.getValue(), is(CODE_VALUE));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testGetByValue_withInvalidValue_shouldThrowException() {
		documentRepository.getByCountryIdAndTypeIdAndValue(ID_000001, ID_000001, TEXT_DUMMY);
	}
	
	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}
	
}
