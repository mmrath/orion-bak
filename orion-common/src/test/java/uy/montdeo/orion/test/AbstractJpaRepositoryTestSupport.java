package uy.montdeo.orion.test;

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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import uy.montdeo.orion.config.OrionConfiguration;
import uy.montdeo.orion.database.AbstractEntity;
import uy.montdeo.orion.database.AbstractTranslatableEntity;
import uy.montdeo.orion.exception.EntityNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {	OrionConfiguration.class  }, webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AbstractJpaRepositoryTestSupport extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	protected WebApplicationContext context;
	
	@Before
	public void setUpGlobal() throws Exception {
		
	}
	
	@After
	public void tearDownGlobal() {
		
	}
	
	public static abstract class AbstractCrudRepositoryTestSupport<R extends CrudRepository<E, Integer>, E extends AbstractEntity> extends AbstractJpaRepositoryTestSupport implements CommonSupport {
		
		@Autowired
		protected R repository;

		/*  	SETUP		 */
		@Before
		public void setUpContext() throws Exception {
			
		}
		
		/****************************************************
		 * 				LIST ALL ENTITIES
		 ****************************************************/
		
		@Test
		public void testFindAll_shouldReturnResults() {
			try {
				
				Iterable<E> results = repository.findAll();
							
				assertNotNull("Results cannot be null", results);
				
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
		
		/****************************************************
		 * 			GET ENTITY BY ID
		 ****************************************************/
			
		@Test
		public void testFindOne_withValidId_shouldReturnResult() {
			try {
				E existing = repository.findOne(ID_000001);
				
				assertNotNull("Results cannot be null", existing);
				assertNotNull("Results - ID cannot be null", existing.getId());
				
				if(existing instanceof AbstractTranslatableEntity) {
					AbstractTranslatableEntity translatable = (AbstractTranslatableEntity) existing;
					assertNotNull("Results - Code cannot be null", translatable.getKey());
					assertNotNull("Results - Name code cannot be null", translatable.getDescription());
				}
				
				assertThat("Results ID does not match with the ID passed", existing.getId(), is(ID_000001));
				
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
		
		@Test(expected = EntityNotFoundException.class)
		public void testFindOne_withInvalidId_shouldThrowException() {
			repository.findOne(ID_100000);
		}

		/*  	TEAR DOWN		 */
		@After
		public void tearDownContext() {
			
		}
	}
	
	public static abstract class AbstractPageableRepositoryTestSupport<R extends PagingAndSortingRepository<E, Integer>, E extends AbstractEntity> extends AbstractJpaRepositoryTestSupport implements CommonSupport {
		
		@Autowired
		protected R repository;

		/*  	SETUP		 */
		@Before
		public void setUpContext() throws Exception {
			
		}
		
		/****************************************************
		 * 				LIST ENTITIES
		 ****************************************************/
		
		@Test
		public void testListAddresses_withDefaultPagination_shouldReturnResults() {
			try {
				Page<E> result = repository.findAll(FIRST_PAGE);
				
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
				Page<E> result = repository.findAll(CUSTOM_PAGE);
				
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
		 * 				GET ENTITY BY ID
		 ****************************************************/
		
		@Test
		public void testFindOne_withValidId_shouldReturnResult() {
			try {
				E existing = repository.findOne(ID_000001);
				
				assertNotNull("Results cannot be null", existing);
				assertNotNull("Results - ID cannot be null", existing.getId());
				
				assertThat("Results ID does not match with the ID passed", existing.getId(), is(ID_000001));
				
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}
		
		@Test(expected = EntityNotFoundException.class)
		public void testFindOne_withInvalidId_shouldThrowException() {
			repository.findOne(ID_100000);
		}

		/*  	TEAR DOWN		 */
		@After
		public void tearDownContext() {
			
		}
	}


}
