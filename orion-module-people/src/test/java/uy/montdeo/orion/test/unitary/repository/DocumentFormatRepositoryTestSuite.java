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
import uy.montdeo.orion.database.entity.DocumentFormat;
import uy.montdeo.orion.database.repository.DocumentFormatRepository;
import uy.montdeo.orion.test.AbstractUnitaryTestSupport;
import uy.montdeo.orion.test.ModulePeopleSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link DocumentFormat} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractUnitaryTestSupport
 * @see DocumentFormatRepository
 */
public class DocumentFormatRepositoryTestSuite extends AbstractUnitaryTestSupport implements ModulePeopleSupport {
	
	@Autowired
	private DocumentFormatRepository documentFormatRepository;
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		
	}
	
	/****************************************************
	 * 				LIST DOCUMENT FORMATS
	 ****************************************************/
	
	@Test
	public void testFindAll_shouldReturnResults() {
		try {
			
			Iterable<DocumentFormat> results = documentFormatRepository.findAll();
						
			assertNotNull("Results cannot be null", results);
			assertThat("Results contents is not empty", results, iterableWithSize(6));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/****************************************************
	 * 			GET DOCUMENT FORMAT BY ID
	 ****************************************************/
		
	@Test
	public void testFindOne_withValidId_shouldReturnResult() {
		try {
			DocumentFormat existing = documentFormatRepository.findOne(ID_000001);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Country cannot be null", existing.getCountry());
			assertNotNull("Results - Type cannot be null", existing.getType());
			assertNotNull("Results - Pattern code cannot be null", existing.getPattern());
			assertThat("Results ID does not match with the ID passed", existing.getId(), is(ID_000001));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindOne_withInvalidId_shouldThrowException() {
		documentFormatRepository.findOne(ID_100000);
	}
	
	/****************************************************
	 * 		GET DOCUMENT FORMAT BY COUNTRY AND TYPE
	 ****************************************************/
		
	@Test
	public void testGetByCountryIdAndTypeId_withValidId_shouldReturnResult() {
		try {
			DocumentFormat existing = documentFormatRepository.getByCountryIdAndTypeId(ID_000001, ID_000001);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Country cannot be null", existing.getCountry());
			assertNotNull("Results - Type cannot be null", existing.getType());
			assertNotNull("Results - Pattern code cannot be null", existing.getPattern());
			assertThat("Results - Country ID does not match with the ID passed", existing.getCountry().getId(), is(ID_000001));
			assertThat("Results - Type ID does not match with the ID passed", existing.getType().getId(), is(ID_000001));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testGetByCountryIdAndTypeId_withInvalidId_shouldThrowException() {
		documentFormatRepository.getByCountryIdAndTypeId(ID_100000, ID_100000);
	}

	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}
	
}
