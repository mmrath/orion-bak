package uy.montdeo.orion.test.unitary.repository;

import org.junit.After;
import org.junit.Before;

import uy.montdeo.orion.database.entity.Address;
import uy.montdeo.orion.database.repository.AddressRepository;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractPageableRepositoryTestSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link Address} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractPageableRepositoryTestSupport
 * @see AddressRepository
 */
public class AddressRepositoryTestSuite extends AbstractPageableRepositoryTestSupport<AddressRepository, Address> {
	
	/*  	SETUP		 */
	@Before
	public void setUpTest() throws Exception {
		
	}
		
	/*  	TEAR DOWN		 */
	@After
	public void tearDownTest() {
		
	}
	
}
