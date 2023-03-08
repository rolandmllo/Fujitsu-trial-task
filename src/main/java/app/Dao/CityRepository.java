package app.Dao;

import app.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    @Query(value = "SELECT * FROM city WHERE lower(city_name) = lower(:name)",nativeQuery = true)
    City findByCityName(@Param("name") String name);

}
