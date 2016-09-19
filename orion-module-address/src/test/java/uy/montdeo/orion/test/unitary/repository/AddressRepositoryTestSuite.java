package uy.montdeo.orion.test.unitary.repository;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static java.math.BigInteger.ZERO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import uy.montdeo.orion.core.exception.EntityNotFoundException;
import uy.montdeo.orion.database.entity.Address;
import uy.montdeo.orion.database.repository.AddressRepository;
import uy.montdeo.orion.test.AbstractUnitaryTestSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link Address} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractUnitaryTestSupport
 * @see AddressRepository
 */
public class AddressRepositoryTestSuite extends AbstractUnitaryTestSupport {
	
	@Autowired
	private AddressRepository addressRepository;
	
	/*  	SETUP		 */
	@Before
	public void setUp() throws Exception {
		
	}
	
	/****************************************************
	 * 				LIST ADDRESSES
	 ****************************************************/
	
	@Test
	public void testListAddresses_withDefaultPagination_shouldReturnResults() {
		try {
			Page<Address> result = addressRepository.findAll(FIRST_PAGE);
			
			assertNotNull("Results cannot be null", result);
			assertNotNull("Results - Entity list cannot be null", result.getContent());
			assertNotNull("Results - Page number cannot be null", result.getNumber());
			assertNotNull("Results - Page size cannot be null", result.getSize());
			assertNotNull("Results - Total elements cannot be null", result.getTotalElements());
			assertNotNull("Results - Total pages cannot be null", result.getTotalPages());
			
			assertThat("Results - Entity list does not match with the expected number", result.getContent(), iterableWithSize(TEN.intValue()));
			assertThat("Results - Page number does not match with the expected number", result.getNumber(), is(ZERO.intValue()));
			assertThat("Results - Page size does not match with the expected number", result.getSize(), is(TEN.intValue()));
			assertThat("Results - Total elements does not match with the expected number", result.getTotalElements(), is(VAL_000025));
			assertThat("Results - Total pages does not match with the expected number", result.getTotalPages(), is(VAL_000003));
	           
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testListAddresses_withCustomPagination_shouldReturnResults() {
		try {
			Page<Address> result = addressRepository.findAll(CUSTOM_PAGE);
			
			assertNotNull("Results cannot be null", result);
			assertNotNull("Results - Entity list cannot be null", result.getContent());
			assertNotNull("Results - Page number cannot be null", result.getNumber());
			assertNotNull("Results - Page size cannot be null", result.getSize());
			assertNotNull("Results - Total elements cannot be null", result.getTotalElements());
			assertNotNull("Results - Total pages cannot be null", result.getTotalPages());
			
			assertThat("Results - Entity list does not match with the expected number", result.getContent(), iterableWithSize(ONE.intValue()));
			assertThat("Results - Page number does not match with the expected number", result.getNumber(), is(ZERO.intValue()));
			assertThat("Results - Page size does not match with the expected number", result.getSize(), is(ONE.intValue()));
			assertThat("Results - Total elements does not match with the expected number", result.getTotalElements(), is(VAL_000025));
			assertThat("Results - Total pages does not match with the expected number", result.getTotalPages(), is(VAL_000025.intValue()));
	           
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	
	/****************************************************
	 * 				GET ADDRESS BY ID
	 ****************************************************/
	
	@Test
	public void testFindOne_withValidId_shouldReturnResult() {
		try {
			Address existing = addressRepository.findOne(ID_000001);
			
			assertNotNull("Results cannot be null", existing);
			assertNotNull("Results - ID cannot be null", existing.getId());
			assertNotNull("Results - Street cannot be null", existing.getStreet());
			assertNotNull("Results - Number cannot be null", existing.getNumber());
			
			assertThat("Results ID does not match with the ID passed", existing.getId(), is(ID_000001));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testFindOne_withInvalidId_shouldThrowException() {
		addressRepository.findOne(ID_100000);
	}
	
	/*  	TEAR DOWN		 */
	@After
	public void tearDown() {
		
	}
	
}
