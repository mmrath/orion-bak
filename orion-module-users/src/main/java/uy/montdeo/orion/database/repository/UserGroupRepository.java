package uy.montdeo.orion.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import uy.montdeo.orion.database.entity.UserGroup;

/**
 * Interface extending the JPA's {@link CrudRepository} interface for accessing the {@link UserGroup} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see {@link CrudRepository}
 */
@RepositoryRestResource(path = "groups", collectionResourceRel = "groups", itemResourceRel = "group")
public interface UserGroupRepository extends CrudRepository<UserGroup, Integer> {

	@RestResource(path = "withName", rel = "withName")
	UserGroup getByName(@Param("name") String name);

}
