package app.Dao;

import app.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT * FROM vehicle WHERE lower(vehicle_type) = lower(:name)",nativeQuery = true)
    Vehicle findByVehicleType(@Param("name") String name);

}
