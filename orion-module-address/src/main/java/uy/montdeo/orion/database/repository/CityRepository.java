package uy.montdeo.orion.database.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import uy.montdeo.orion.database.entity.City;

/**
 * Interface extending the JPA's {@link CrudRepository} interface for accessing the {@link City} entity.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see CrudRepository
 */
@RepositoryRestResource(path = "cities", collectionResourceRel = "cities", itemResourceRel = "city")
public interface CityRepository extends CrudRepository<City, Integer> {

	@RestResource(path = "forState", rel = "forState")
	List<City> findByStateId(@Param("id") Integer id);
}
