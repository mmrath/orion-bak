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
import uy.montdeo.orion.database.entity.Country;
import uy.montdeo.orion.database.repository.CountryRepository;
import uy.montdeo.orion.test.AbstractModuleLocationSupport;
import uy.montdeo.orion.test.AbstractUnitaryTestSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link Country} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractUnitaryTestSupport
 * @see CountryRepository
 */
public class CountryRepositoryTestSuite extends AbstractUnitaryTestSupport implements AbstractModuleLocationSupport {
	
	@Autowired
	private CountryRepository countryRepository;
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		
	}	
	
	/****************************************************
	 * 					LIST COUNTRIES
	 ****************************************************/
	
	@Test
	public void testFindAll_shouldReturnResults() {
		try {
			
			Iterable<Country> results = countryRepository.findAll();
						
			assertNotNull("Results cannot be null", results);
			assertThat("Results contents is not empty", results, iterableWithSize(239));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/****************************************************
	 * 				GET COUNTRY BY ID
	 ****************************************************/
		
	@Test
	public void testFindOne_withValidId_shouldReturnResult() {
		try {
			Country existing = countryRepository.findOne(ID_000001);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Name cannot be null", existing.getName());
			assertNotNull("Results - Alpha-2 code cannot be null", existing.getAlpha2Code());
			assertNotNull("Results - Alpha-3 code cannot be null", existing.getAlpha3Code());
			assertNotNull("Results - Numeric code cannot be null", existing.getNumericCode());
			assertNotNull("Results - Phone code cannot be null", existing.getPhoneCode());
			assertThat("Results ID does not match with the ID passed", existing.getId(), is(ID_000001));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindOne_withInvalidId_shouldThrowException() {
		countryRepository.findOne(ID_100000);
	}
	
	/****************************************************
	 * 			GET COUNTRY BY ALPHA 2 CODE
	 ****************************************************/
	
	@Test
	public void testGetByAlpha2Code_withValidCode_shouldReturnResult() {
		try {
			Country existing = countryRepository.getByAlpha2Code(CODE_ALPHA2);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Name cannot be null", existing.getName());
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
		countryRepository.getByAlpha2Code(TEXT_DUMMY);
	}
	
	/****************************************************
	 * 			GET COUNTRY BY ALPHA 3 CODE
	 ****************************************************/
	
	@Test
	public void testGetByAlpha3Code_withValidCode_shouldReturnResult() {
		try {
			Country existing = countryRepository.getByAlpha3Code(CODE_ALPHA3);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Name cannot be null", existing.getName());
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
		countryRepository.getByAlpha3Code(TEXT_DUMMY);
	}
	
	/****************************************************
	 * 			GET COUNTRY BY NUMERIC CODE
	 ****************************************************/
	
	@Test
	public void testGetByNumericCode_withValidCode_shouldReturnResult() {
		try {
			Country existing = countryRepository.getByNumericCode(CODE_NUMERIC);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Name cannot be null", existing.getName());
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
		countryRepository.getByNumericCode(TEXT_DUMMY);
	}

	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}
}
