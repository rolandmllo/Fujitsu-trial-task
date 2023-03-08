package app.Dao;

import app.model.City;
import app.model.RegionalBaseFee;
import app.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionalBaseFeeRepository extends JpaRepository<RegionalBaseFee, Long> {
    @Query(value = "SELECT * FROM regional_base_fee WHERE " +
            "city_id = :city_id AND vehicle_id = :vehicle_id",nativeQuery = true)
    RegionalBaseFee GetRegionalBaseFeeByIds(@Param("city_id") long cityId,
                                            @Param("vehicle_id") long vehicleId);


}
