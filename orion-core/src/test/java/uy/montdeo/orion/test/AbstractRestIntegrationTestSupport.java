package uy.montdeo.orion.test;

import static java.lang.String.format;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

/**
 * Abstract class for sharing common elements and setup for testing the platform REST endpoints.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see AbstractUnitaryTestSupport
 */
public abstract class AbstractRestIntegrationTestSupport extends AbstractUnitaryTestSupport {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	protected TestRestTemplate template;
	
	@LocalServerPort
	private Integer port;
	
	protected MediaType CONTENT_TYPE_HAL_JSON	= MediaType.valueOf("application/hal+json;charset=UTF-8");
	
	protected Configuration configuration;
	
	protected String url;
	
	private int p = 0;
	private int l = 10;
		
	@Before
	public void setUpContext() throws Exception {
				
		JacksonMappingProvider mappingProvider = new JacksonMappingProvider(objectMapper);
				
		configuration = Configuration.defaultConfiguration().mappingProvider(mappingProvider);
	}
	
	@After
	public void tearDownContext() {
		
	}
	
	protected String getEndpoint(String url) {
		return "https://www.orion.uy:".concat(Integer.toString(port)).concat(url);
	}
	
	protected String getEndpoint() {
		return getEndpoint(url);
	}
	
	protected String getExpectedEndpoint(String url) {
		return fromUriString(getEndpoint(url)).buildAndExpand(ID_000001).toUriString();
	}
		
	protected String getFirst() {
		return getPaginatedUrl(0, this.l);
	}
	
	protected String getNext() {
		return getPaginatedUrl(this.p + 1, this.l);
	}
	
	protected String getLast(int pages) {
		return getPaginatedUrl(pages - 1, this.l);
	}
	
	protected String getPaginatedUrl(int p, int l) {
		this.p = p;
		this.l = l;
		
		return getEndpoint(url).concat(format("?p=%1$d&l=%2$d", this.p, this.l));
	}
}
