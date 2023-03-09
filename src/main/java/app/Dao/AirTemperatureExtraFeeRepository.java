package app.Dao;

import app.model.AirTemperatureExtraFee;
import app.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirTemperatureExtraFeeRepository extends JpaRepository<AirTemperatureExtraFee, Long> {
    @Query(value = "SELECT * FROM air_temperature_extra_fee " +
            "WHERE lower_temp <= :temperature AND :temperature <= higher_temp " +
            "AND vehicle_id = :vehicleId",nativeQuery = true)
    AirTemperatureExtraFee findATEFRateByTemp(
            @Param("temperature") Double temperature,
            @Param("vehicleId") Long vehicleId);
}