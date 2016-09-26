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

import uy.montdeo.orion.database.entity.City;
import uy.montdeo.orion.database.entity.State;
import uy.montdeo.orion.database.repository.CityRepository;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractCrudRepositoryTestSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link City} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractCrudRepositoryTestSupport
 * @see CityRepository
 */
public class CityRepositoryTestSuite extends AbstractCrudRepositoryTestSupport<CityRepository, City> {
	
	/*  	SETUP		 */
	@Before
	public void setUpTest() throws Exception {
		
	}
	
	/****************************************************
	 * 				LIST CITIES FOR STATE
	 ****************************************************/
	
	@Test
	public void testFindByStateId_withValidStateId_shouldReturnResults() {
		try {
			
			List<City> results = repository.findByStateId(ID_000001);
						
			assertNotNull("Results cannot be null", results);
			assertThat("Results contents is not empty", results, iterableWithSize(1));
			assertThat("State attribute from results has not been set", results, everyItem(hasProperty("state", isA(State.class))));
						
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
		
	@Test
	public void testFindByStateId_withInvalidStateId_shouldReturnEmptyList() {
		try {
			List<City> results = repository.findByStateId(ID_100000);
			
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
