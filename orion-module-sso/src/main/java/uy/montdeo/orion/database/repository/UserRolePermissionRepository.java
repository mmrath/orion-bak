package uy.montdeo.orion.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import uy.montdeo.orion.database.entity.UserRolePermission;

/**
 * Interface extending the JPA's {@link PagingAndSortingRepository} interface for accessing the {@link UserRolePermission} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see {@link PagingAndSortingRepository}
 */
@RepositoryRestResource(path = "permissions", collectionResourceRel = "permissions", itemResourceRel = "permission")
public interface UserRolePermissionRepository extends PagingAndSortingRepository<UserRolePermission, Integer> {

}
