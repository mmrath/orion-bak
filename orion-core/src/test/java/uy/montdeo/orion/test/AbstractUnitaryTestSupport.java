package uy.montdeo.orion.test;

import static java.util.Locale.forLanguageTag;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import uy.montdeo.orion.OrionApplication;
import uy.montdeo.orion.test.config.OrionTestConfiguration;

/**
 * Abstract class for setup the platform contest for testing the platform elements.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractTransactionalJUnit4SpringContextTests
 * @see SpringBootTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {	OrionApplication.class, OrionTestConfiguration.class  }, webEnvironment = RANDOM_PORT)
public abstract class AbstractUnitaryTestSupport extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	protected WebApplicationContext context;
	
	@Autowired
	private MessageSource messageSource;
	
	protected Locale LANGUAGE_EN_US 				= forLanguageTag("en-US;q=1.0,en;q=0.8");
	
	protected Pageable CUSTOM_PAGE 					= new PageRequest(0, 1);
	protected Pageable FIRST_PAGE 					= new PageRequest(0, 10);
	
	protected static final Integer ID_000001 		= Integer.valueOf(1);
	protected static final Integer ID_100000 		= Integer.valueOf(100000);
	
	protected static final String TEXT_DUMMY		= "dummy";
	protected static final String TEXT_INVALID 		= "invalid";
		
	protected final Integer VAL_000003 				= Integer.valueOf(3);
	protected final Integer VAL_000004 				= Integer.valueOf(4);
	protected final Integer VAL_000015 				= Integer.valueOf(15);
	protected final Long VAL_000025 				= Long.valueOf(25);
	protected final Long VAL_000040 				= Long.valueOf(40);
	
	protected String NOT_FOUND_ID_MSG, NOT_FOUND_VALUE_MSG, WELCOME_MSG, ERROR_MSG = null;
	
	@Before
	public void setUpGlobal() throws Exception {
		NOT_FOUND_ID_MSG = messageSource.getMessage("error.404.id", new Integer[] { ID_100000}, LANGUAGE_EN_US);
		NOT_FOUND_VALUE_MSG = messageSource.getMessage("error.404.attribute", new String[] { TEXT_DUMMY}, LANGUAGE_EN_US);
		WELCOME_MSG = messageSource.getMessage("label.welcome", null, LANGUAGE_EN_US);
		ERROR_MSG = messageSource.getMessage("error.500.title", null, LANGUAGE_EN_US);
	}
	
	@After
	public void tearDownGlobal() {
		
	}

}
