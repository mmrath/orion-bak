package uy.montdeo.orion.test.unitary.repository;

import org.junit.After;
import org.junit.Before;

import uy.montdeo.orion.database.entity.UserGroupPermission;
import uy.montdeo.orion.database.repository.UserGroupPermissionRepository;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractCrudRepositoryTestSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link UserRolePermission} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractCrudRepositoryTestSupport
 * @see UserGroupPermissionRepository
 */
public class UserGroupPermissionRepositoryTestSuite extends AbstractCrudRepositoryTestSupport<UserGroupPermissionRepository, UserGroupPermission> {
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		
	}
	
	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}

}
