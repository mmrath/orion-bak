package uy.montdeo.orion.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import uy.montdeo.orion.database.entity.DocumentFormat;

/**
 * Interface extending the JPA's {@link CrudRepository} interface for accessing the {@link DocumentFormat} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see CrudRepository
 */
@RepositoryRestResource(path = "documents/formats", collectionResourceRel = "formats", itemResourceRel = "format")
public interface DocumentFormatRepository extends CrudRepository<DocumentFormat, Integer> {
	
	@RestResource(path = "forCountryAndType", rel = "forCountryAndType")
	DocumentFormat getByCountryIdAndTypeId(@Param("country") Integer country, @Param("type") Integer type);

}
