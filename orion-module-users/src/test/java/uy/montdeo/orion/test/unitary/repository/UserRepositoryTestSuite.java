package uy.montdeo.orion.test.unitary.repository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uy.montdeo.orion.database.entity.User;
import uy.montdeo.orion.database.repository.UserRepository;
import uy.montdeo.orion.exception.EntityNotFoundException;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractPageableRepositoryTestSupport;
import uy.montdeo.orion.test.ModuleSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link User} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractPageableRepositoryTestSupport
 * @see UserRepository
 */
public class UserRepositoryTestSuite extends AbstractPageableRepositoryTestSupport<UserRepository, User> implements ModuleSupport {
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		
	}
		
	/****************************************************
	 * 				GET USER BY USERNAME
	 ****************************************************/
	
	@Test
	public void testGetByUsername_withValidUsername_shouldReturnResult() {
		try {
			User existing = repository.getByUsername(TEXT_USERNAME);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Username cannot be null", existing.getUsername());
			assertNotNull("Results - Attempts cannot be null", existing.getAttempts());
			assertNotNull("Results - Locked cannot be null", existing.isLocked());
			assertNotNull("Results - Group cannot be null", existing.getGroup());
			assertNotNull("Results - Settings cannot be null", existing.getSettings());
			assertThat("Results username does not match with the username passed", existing.getUsername(), is(TEXT_USERNAME));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testGetByUsername_withInvalidUsername_shouldThrowException() {
		repository.getByUsername(TEXT_INVALID);		
	}

	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}
}
