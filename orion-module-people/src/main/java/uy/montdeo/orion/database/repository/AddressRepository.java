package uy.montdeo.orion.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import uy.montdeo.orion.database.entity.Address;

/**
 * Interface extending the JPA's {@link PagingAndSortingRepository} interface for accessing the {@link Address} entity.
 * Also extending the {@link RevisionRepository} interface for accessing the revision history for this entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see PagingAndSortingRepository
 * @see RevisionRepository
 */
@RepositoryRestResource(path = "addresses", collectionResourceRel = "addresses", itemResourceRel = "address")
public interface AddressRepository extends RevisionRepository<Address, Integer, Integer>, PagingAndSortingRepository<Address, Integer> {

}
