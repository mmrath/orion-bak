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
import uy.montdeo.orion.database.entity.DocumentType;
import uy.montdeo.orion.database.repository.DocumentTypeRepository;
import uy.montdeo.orion.test.AbstractUnitaryTestSupport;
import uy.montdeo.orion.test.ModulePeopleSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link DocumentType} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractUnitaryTestSupport
 * @see DocumentTypeRepository
 */
public class DocumentTypeRepositoryTestSuite extends AbstractUnitaryTestSupport implements ModulePeopleSupport {
	
	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		
	}
	
	/****************************************************
	 * 				LIST DOCUMENT TYPES
	 ****************************************************/
	
	@Test
	public void testFindAll_shouldReturnResults() {
		try {
			
			Iterable<DocumentType> results = documentTypeRepository.findAll();
						
			assertNotNull("Results cannot be null", results);
			assertThat("Results contents is not empty", results, iterableWithSize(6));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/****************************************************
	 * 			GET DOCUMENT TYPE BY ID
	 ****************************************************/
		
	@Test
	public void testFindOne_withValidId_shouldReturnResult() {
		try {
			DocumentType existing = documentTypeRepository.findOne(ID_000001);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Code cannot be null", existing.getCode());
			assertNotNull("Results - Name code cannot be null", existing.getName());
			assertThat("Results ID does not match with the ID passed", existing.getId(), is(ID_000001));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindOne_withInvalidId_shouldThrowException() {
		documentTypeRepository.findOne(ID_100000);
	}

	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}
	
}
