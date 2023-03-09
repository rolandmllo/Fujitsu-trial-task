package app.Dao;

import app.model.AirTemperatureExtraFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AirTemperatureExtraFeeRepository extends JpaRepository<AirTemperatureExtraFee, Long> {

    @Query(value = "SELECT * FROM air_temperature_extra_fee " +
            "WHERE lower_temp <= :temperature AND :temperature <= higher_temp " +
            "AND vehicle_id = :vehicleId",nativeQuery = true)
    AirTemperatureExtraFee findATEFRateByTempAndVehicleId(@Param("temperature") Double temperature,
                                                          @Param("vehicleId") Long vehicleId);

}
