package uy.montdeo.orion.test.unitary.repository;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import uy.montdeo.orion.core.exception.EntityNotFoundException;
import uy.montdeo.orion.database.entity.Country;
import uy.montdeo.orion.database.entity.State;
import uy.montdeo.orion.database.repository.StateRepository;
import uy.montdeo.orion.test.AbstractUnitaryTestSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link State} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractUnitaryTestSupport
 * @see StateRepository
 */
public class StateRepositoryTestSuite extends AbstractUnitaryTestSupport {
	
	@Autowired
	private StateRepository stateRepository;
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		
	}
	
	/****************************************************
	 * 				LIST STATES FOR COUNTRY
	 ****************************************************/
	
	@Test
	public void testFindByCountryId_withValidCountryId_shouldReturnResults() {
		try {
			
			List<State> results = stateRepository.findByCountryId(ID_000001);
						
			assertNotNull("Results cannot be null", results);
			assertThat("Results contents is not empty", results, iterableWithSize(19));
			assertThat("Country ID from results does not match with the ID passed", results, everyItem(hasProperty("country", isA(Country.class))));
						
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
		
	@Test
	public void testFindByCountryId_withInvalidCountryId_shouldReturnEmptyList() {
		try {
			List<State> results = stateRepository.findByCountryId(ID_100000);
			
			assertNotNull("Results cannot be null", results);
			assertThat("Results contents is not empty", results, emptyIterable());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	/****************************************************
	 * 				GET STATE BY ID
	 ****************************************************/
	
	@Test
	public void testFindOne_withValidId_shouldReturnResult() {
		try {
			State existing = stateRepository.findOne(ID_000001);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Name cannot be null", existing.getName());
			assertThat("Results ID does not match with the ID passed", existing.getId(), is(ID_000001));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindOne_withInvalidId_shouldThrowException() {
		stateRepository.findOne(ID_100000);
	}
	
	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}
}
