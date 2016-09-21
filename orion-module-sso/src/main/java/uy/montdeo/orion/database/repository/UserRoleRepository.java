package uy.montdeo.orion.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import uy.montdeo.orion.database.entity.UserRole;

/**
 * Interface extending the JPA's {@link PagingAndSortingRepository} interface for accessing the {@link UserRole} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see {@link PagingAndSortingRepository}
 */
@RepositoryRestResource(path = "roles", collectionResourceRel = "roles", itemResourceRel = "role")
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Integer> {

}
