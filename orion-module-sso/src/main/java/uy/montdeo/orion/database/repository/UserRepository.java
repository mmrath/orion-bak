package uy.montdeo.orion.database.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import uy.montdeo.orion.database.entity.User;

/**
 * Interface extending the JPA's {@link PagingAndSortingRepository} interface for accessing the {@link User} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see {@link PagingAndSortingRepository}
 */
@RepositoryRestResource(path = "users", collectionResourceRel = "users", itemResourceRel = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	@RestResource(path = "withUsername", rel = "withUsername")
	User getByUsername(@Param("username") String username);

}
