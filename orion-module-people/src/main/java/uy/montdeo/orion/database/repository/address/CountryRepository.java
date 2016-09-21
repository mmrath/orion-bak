package uy.montdeo.orion.database.repository.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import uy.montdeo.orion.database.entity.address.Country;

/**
 * Interface extending the JPA's {@link CrudRepository} interface for accessing the {@link Country} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see CrudRepository
 */
@RepositoryRestResource(path = "countries", collectionResourceRel = "countries", itemResourceRel = "country")
public interface CountryRepository extends CrudRepository<Country, Integer>{
	
	@RestResource(path = "withAlpha2Code", rel = "withAlpha2Code")
	Country getByAlpha2Code(@Param("code") String alpha2Code);
	
	@RestResource(path = "withAlpha3Code", rel = "withAlpha3Code")
	Country getByAlpha3Code(@Param("code") String alpha3Code);
	
	@RestResource(path = "withNumericCode", rel = "withNumericCode")
	Country getByNumericCode(@Param("code") String numericCode);

}
