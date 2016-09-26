package uy.montdeo.orion.test.unitary.repository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uy.montdeo.orion.database.entity.DocumentFormat;
import uy.montdeo.orion.database.repository.DocumentFormatRepository;
import uy.montdeo.orion.exception.EntityNotFoundException;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractCrudRepositoryTestSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link DocumentFormat} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractCrudRepositoryTestSupport
 * @see DocumentFormatRepository
 */
public class DocumentFormatRepositoryTestSuite extends AbstractCrudRepositoryTestSupport<DocumentFormatRepository, DocumentFormat> {
	
	/*  	SETUP		 */
	@Before
	public void setUpTest() throws Exception {
		
	}
	
	/****************************************************
	 * 		GET DOCUMENT FORMAT BY COUNTRY AND TYPE
	 ****************************************************/
		
	@Test
	public void testGetByCountryIdAndTypeId_withValidId_shouldReturnResult() {
		try {
			DocumentFormat existing = repository.getByCountryIdAndTypeId(ID_000001, ID_000001);
			
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
		repository.getByCountryIdAndTypeId(ID_100000, ID_100000);
	}

	/*  	TEAR DOWN		 */
	@After
	public void tearDownTest() {
		
	}
	
}
