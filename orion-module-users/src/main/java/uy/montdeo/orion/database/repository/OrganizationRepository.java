package uy.montdeo.orion.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import uy.montdeo.orion.database.entity.Organization;

/**
 * Interface extending the JPA's {@link PagingAndSortingRepository} interface for accessing the {@link Organization} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see {@link PagingAndSortingRepository}
 */
@RepositoryRestResource(path = "organizations", collectionResourceRel = "organizations", itemResourceRel = "organization")
public interface OrganizationRepository extends PagingAndSortingRepository<Organization, Integer> {

	@RestResource(path = "withName", rel = "withName")
	Organization getByName(@Param("name") String name);

}
