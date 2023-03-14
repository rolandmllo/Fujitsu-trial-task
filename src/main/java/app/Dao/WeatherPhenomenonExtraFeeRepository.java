package app.Dao;

import app.model.WeatherPhenomenonExtraFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WeatherPhenomenonExtraFeeRepository extends JpaRepository<WeatherPhenomenonExtraFee, Long> {

    @Query(value = "SELECT * FROM weather_phenomenon_extra_fee " +
            "WHERE (LOWER(:phenomenon) LIKE CONCAT('%', LOWER(weather_phenomenon), '%') " +
            "OR LOWER(:phenomenon) = LOWER(weather_phenomenon))" +
            " AND vehicle_id = :vehicleId LIMIT 1",nativeQuery = true)
    WeatherPhenomenonExtraFee findWPEFRateByPhenomenonAndVehicleId(@Param("phenomenon") String phenomenon,
                                                                 @Param("vehicleId") Long vehicleId);

}
