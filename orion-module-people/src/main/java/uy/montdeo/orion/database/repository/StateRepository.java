package uy.montdeo.orion.database.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import uy.montdeo.orion.database.entity.State;

/**
 * Interface extending the JPA's {@link CrudRepository} interface for accessing the {@link State} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see CrudRepository
 */
@RepositoryRestResource(path = "states", collectionResourceRel = "states", itemResourceRel = "state")
public interface StateRepository extends CrudRepository<State, Integer> {
	
	@RestResource(path = "forCountry", rel = "forCountry")
	List<State> findByCountryId(@Param("id") Integer id);

}
