package uy.montdeo.orion.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import uy.montdeo.orion.database.entity.Document;

/**
 * Interface extending the JPA's {@link CrudRepository} interface for accessing the {@link Document} entity.
 * Also extending the {@link RevisionRepository} interface for accessing the revision history for this entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see CrudRepository
 * @see RevisionRepository
 */
@RepositoryRestResource(path = "documents", collectionResourceRel = "documents", itemResourceRel = "document")
public interface DocumentRepository extends RevisionRepository<Document, Long, Integer>, CrudRepository<Document, Integer> {
	
	@RestResource(path = "withParams", rel = "withParams")
	Document getByCountryIdAndTypeIdAndValue(@Param("country") Integer country, @Param("type") Integer type, @Param("value") String value);

}
