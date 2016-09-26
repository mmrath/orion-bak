package uy.montdeo.orion.test.unitary.repository;

import org.junit.After;
import org.junit.Before;

import uy.montdeo.orion.database.entity.Person;
import uy.montdeo.orion.database.repository.PersonRepository;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractPageableRepositoryTestSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link Person} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractPageableRepositoryTestSupport
 * @see PersonRepository
 */
public class PersonRepositoryTestSuite extends AbstractPageableRepositoryTestSupport<PersonRepository, Person> {
	
	/*  	SETUP		 */
	@Before
	public void setUpTest() throws Exception {
		
	}
		
	/*  	TEAR DOWN		 */
	@After
	public void tearDownTest() {
		
	}
	
}
