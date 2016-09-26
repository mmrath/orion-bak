package uy.montdeo.orion.test;

import static java.util.Locale.forLanguageTag;

import java.util.Locale;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CommonSupport {
	
	public Locale LANGUAGE_EN_US 		= forLanguageTag("en-US;q=1.0,en;q=0.8");
	
	public Pageable CUSTOM_PAGE		 	= new PageRequest(0, 1);
	public Pageable FIRST_PAGE 			= new PageRequest(0, 10);
	
	public Integer ID_000001 			= Integer.valueOf(1);
	public Integer ID_100000 			= Integer.valueOf(100000);
	
	public String TEXT_DUMMY			= "dummy";
	public String TEXT_INVALID 			= "invalid";
		
	public Integer VAL_000003 			= Integer.valueOf(3);
	public Integer VAL_000004 			= Integer.valueOf(4);
	public Integer VAL_000015 			= Integer.valueOf(15);
	public Long VAL_000025 				= Long.valueOf(25);
	public Long VAL_000040 				= Long.valueOf(40);

}
