package uy.montdeo.orion.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import uy.montdeo.orion.database.entity.DocumentType;

/**
 * Interface extending the JPA's {@link CrudRepository} interface for accessing the {@link DocumentType} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see CrudRepository
 */
@RepositoryRestResource(path = "documents/types", collectionResourceRel = "types", itemResourceRel = "type")
public interface DocumentTypeRepository extends CrudRepository<DocumentType, Integer> {

}
