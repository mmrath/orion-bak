package uy.montdeo.orion.test.unitary.repository;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uy.montdeo.orion.database.entity.Organization;
import uy.montdeo.orion.database.entity.User;
import uy.montdeo.orion.database.repository.OrganizationRepository;
import uy.montdeo.orion.exception.EntityNotFoundException;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractPageableRepositoryTestSupport;
import uy.montdeo.orion.test.ModuleSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link User} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractPageableRepositoryTestSupport
 * @see OrganizationRepository
 */
public class OrganizationRepositoryTestSuite extends AbstractPageableRepositoryTestSupport<OrganizationRepository, Organization> implements ModuleSupport {
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		
	}
		
	/****************************************************
	 * 			GET ORGANIZATION BY NAME
	 ****************************************************/
	
	@Test
	public void testGetByName_withValidName_shouldReturnResult() {
		try {
			Organization existing = repository.getByName(TEXT_NAME);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Name cannot be null", existing.getName());
			assertNotNull("Results - Active cannot be null", existing.isActive());
			assertNotNull("Results - Deleted cannot be null", existing.isDeleted());
			assertThat("Results name does not match with the name passed", existing.getName(), is(TEXT_NAME));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testGetByName_withInvalidName_shouldThrowException() {
		repository.getByName(TEXT_INVALID);		
	}

	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}
}
