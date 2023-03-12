package app.Dao;

import app.model.Vehicle;
import app.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Weather findFirstByObservationStationNameIsOrderByTimestampDesc(String observationStationName);

    @Query(value = "SELECT * FROM weather WHERE lower(observation_station_name) = lower(:observationStationName) AND " +
            "timestamp = :dateTime",
            nativeQuery = true)
    Weather findByObservationStationNameAndTimestamp(@Param("observationStationName") String observationStationName,
                                                     @Param("dateTime") LocalDateTime dateTime);

}
