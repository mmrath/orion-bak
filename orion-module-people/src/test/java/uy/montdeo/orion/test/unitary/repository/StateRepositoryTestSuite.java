package uy.montdeo.orion.test.unitary.repository;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uy.montdeo.orion.database.entity.Country;
import uy.montdeo.orion.database.entity.State;
import uy.montdeo.orion.database.repository.StateRepository;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractCrudRepositoryTestSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link State} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractJpaRepositoryTestSupport
 * @see StateRepository
 */
public class StateRepositoryTestSuite extends AbstractCrudRepositoryTestSupport<StateRepository, State> {
	
	/*  	SETUP		 */
	@Before
	public void setUpTest() throws Exception {
		
	}
	
	/****************************************************
	 * 				LIST STATES FOR COUNTRY
	 ****************************************************/
	
	@Test
	public void testFindByCountryId_withValidCountryId_shouldReturnResults() {
		try {
			
			List<State> results = repository.findByCountryId(ID_000001);
						
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
			List<State> results = repository.findByCountryId(ID_100000);
			
			assertNotNull("Results cannot be null", results);
			assertThat("Results contents is not empty", results, emptyIterable());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
		
	/*  	TEAR DOWN		 */
	@After
	public void tearDownTest() {
		
	}
}
