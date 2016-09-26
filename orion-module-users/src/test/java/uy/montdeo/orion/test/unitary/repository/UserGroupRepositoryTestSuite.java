package uy.montdeo.orion.test.unitary.repository;

import org.junit.After;
import org.junit.Before;

import uy.montdeo.orion.database.entity.UserGroup;
import uy.montdeo.orion.database.repository.UserGroupRepository;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractCrudRepositoryTestSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link UserRole} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractCrudRepositoryTestSupport
 * @see UserGroupRepository
 */
public class UserGroupRepositoryTestSuite extends AbstractCrudRepositoryTestSupport<UserGroupRepository, UserGroup> {
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		
	}

	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}
}
