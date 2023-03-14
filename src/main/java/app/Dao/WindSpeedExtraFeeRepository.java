package app.Dao;

import app.model.WindSpeedExtraFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WindSpeedExtraFeeRepository extends JpaRepository<WindSpeedExtraFee, Long> {

    @Query(value = "SELECT * FROM wind_speed_extra_fee " +
            "WHERE lower_wind_speed <= :windSpeed AND :windSpeed <= higher_wind_speed " +
            "AND vehicle_id = :vehicleId LIMIT 1", nativeQuery = true)
    WindSpeedExtraFee findWSEFRateByTempAndVehicleId(@Param("windSpeed") Double windSpeed,
                                                     @Param("vehicleId") Long vehicleId);


}
