package uy.montdeo.orion.test.unitary.repository;

import org.junit.After;
import org.junit.Before;

import uy.montdeo.orion.database.entity.AddressType;
import uy.montdeo.orion.database.repository.AddressTypeRepository;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractCrudRepositoryTestSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link AddressType} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractCrudRepositoryTestSupport
 * @see AddressTypeRepository
 */
public class AddressTypeRepositoryTestSuite extends AbstractCrudRepositoryTestSupport<AddressTypeRepository, AddressType> {
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		
	}
		
	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}
	
}
