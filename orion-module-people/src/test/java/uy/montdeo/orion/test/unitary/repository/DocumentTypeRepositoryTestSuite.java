package uy.montdeo.orion.test.unitary.repository;

import org.junit.After;
import org.junit.Before;

import uy.montdeo.orion.database.entity.DocumentType;
import uy.montdeo.orion.database.repository.DocumentTypeRepository;
import uy.montdeo.orion.test.AbstractJpaRepositoryTestSupport.AbstractCrudRepositoryTestSupport;

/**
 * Tests for verifying the correct response of the platform JPA repository for the {@link DocumentType} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractCrudRepositoryTestSupport
 * @see DocumentTypeRepository
 */
public class DocumentTypeRepositoryTestSuite extends AbstractCrudRepositoryTestSupport<DocumentTypeRepository, DocumentType>{
	
	/*  	SETUP		 */
	@Before
	public void setUpTest() throws Exception {
		
	}
		
	/*  	TEAR DOWN		 */
	@After
	public void tearDownTest() {
		
	}
	
}
