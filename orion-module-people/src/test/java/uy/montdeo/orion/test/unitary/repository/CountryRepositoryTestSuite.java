package uy.montdeo.orion.test.unitary.repository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uy.montdeo.orion.database.entity.Country;
import uy.montdeo.orion.database.repository.CountryRepository;
import uy.montdeo.orion.exception.EntityNotFoundException;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractCrudRepositoryTestSupport;
import uy.montdeo.orion.test.ModuleSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link Country} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractCrudRepositoryTestSupport
 * @see CountryRepository
 */
public class CountryRepositoryTestSuite extends AbstractCrudRepositoryTestSupport<CountryRepository, Country> implements ModuleSupport {
	
	/*  	SETUP		 */
	@Before
	public void setUpTest() throws Exception {
		
	}	
	
	/****************************************************
	 * 			GET COUNTRY BY ALPHA 2 CODE
	 ****************************************************/
	
	@Test
	public void testGetByAlpha2Code_withValidCode_shouldReturnResult() {
		try {
			Country existing = repository.getByAlpha2Code(CODE_ALPHA2);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Name cannot be null", existing.getDescription());
			assertNotNull("Results - Alpha-2 code cannot be null", existing.getAlpha2Code());
			assertNotNull("Results - Alpha-3 code cannot be null", existing.getAlpha3Code());
			assertNotNull("Results - Numeric code cannot be null", existing.getNumericCode());
			assertNotNull("Results - Phone code cannot be null", existing.getPhoneCode());
			assertThat("Results Alpha 2 code does not match with the code passed", existing.getAlpha2Code(), is(CODE_ALPHA2));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testGetByAlpha2Code_withInvalidCode_shouldThrowException() {
		repository.getByAlpha2Code(TEXT_DUMMY);
	}
	
	/****************************************************
	 * 			GET COUNTRY BY ALPHA 3 CODE
	 ****************************************************/
	
	@Test
	public void testGetByAlpha3Code_withValidCode_shouldReturnResult() {
		try {
			Country existing = repository.getByAlpha3Code(CODE_ALPHA3);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Name cannot be null", existing.getDescription());
			assertNotNull("Results - Alpha-2 code cannot be null", existing.getAlpha2Code());
			assertNotNull("Results - Alpha-3 code cannot be null", existing.getAlpha3Code());
			assertNotNull("Results - Numeric code cannot be null", existing.getNumericCode());
			assertNotNull("Results - Phone code cannot be null", existing.getPhoneCode());
			assertThat("Results Alpha 2 code does not match with the code passed", existing.getAlpha3Code(), is(CODE_ALPHA3));
						
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testGetByAlpha3Code_withInvalidCode_shouldThrowException() {
		repository.getByAlpha3Code(TEXT_DUMMY);
	}
	
	/****************************************************
	 * 			GET COUNTRY BY NUMERIC CODE
	 ****************************************************/
	
	@Test
	public void testGetByNumericCode_withValidCode_shouldReturnResult() {
		try {
			Country existing = repository.getByNumericCode(CODE_NUMERIC);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Name cannot be null", existing.getDescription());
			assertNotNull("Results - Alpha-2 code cannot be null", existing.getAlpha2Code());
			assertNotNull("Results - Alpha-3 code cannot be null", existing.getAlpha3Code());
			assertNotNull("Results - Numeric code cannot be null", existing.getNumericCode());
			assertNotNull("Results - Phone code cannot be null", existing.getPhoneCode());
			assertThat("Results numeric code does not match with the code passed", existing.getNumericCode(), is(CODE_NUMERIC));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testGetByNumericCode_withInvalidCode_shouldThrowException() {
		repository.getByNumericCode(TEXT_DUMMY);
	}

	/*  	TEAR DOWN		 */
	@After
	public void tearDownTest() {
		
	}
}
