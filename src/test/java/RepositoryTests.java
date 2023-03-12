import app.Dao.*;
import app.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ContextConfiguration(classes = app.Application.class)
public class RepositoryTests {
    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private RegionalBaseFeeRepository rbfRepository;
    @Autowired
    WindSpeedExtraFeeRepository wsefRepository;
    @Autowired
    AirTemperatureExtraFeeRepository atefRepository;
    @Autowired
    WeatherPhenomenonExtraFeeRepository wpefRepository;

    @Test
    public void shouldAddWeatherToDatabase(){
        Weather weather = new Weather();
        weather.setObservationStationName("testWeather");

        assertThat(weather.getId()).isNull();
        weatherRepository.save(weather);
        assertThat(weather.getId()).isNotNull();
    }

    @Test
    public void shouldFindWeatherByCityFromDatabase(){
        Weather weather = new Weather();
        String name = "testWeather";
        weather.setObservationStationName(name);

        weatherRepository.save(weather);

        var savedWeather = weatherRepository.findFirstByObservationStationNameIsOrderByTimestampDesc(
                weather.getObservationStationName());

        assertThat(savedWeather.getObservationStationName()).isEqualTo(name);
    }

    @Test
    public void shouldFindWeatherByCityAndDateTimeFromDatabase(){
        Weather weather = new Weather();
        String name = "testWeather";
        LocalDateTime dateTime = LocalDateTime.now();
        weather.setObservationStationName(name);
        weather.setTimestamp(dateTime);

        weatherRepository.save(weather);

        var savedWeather = weatherRepository.findByObservationStationNameAndTimestamp(
                weather.getObservationStationName(), dateTime);

        assertThat(savedWeather.getObservationStationName()).isEqualTo(name);
    }
    @Test
    public void shouldAddCityToDatabase(){
        City city = new City();
        String cityName = "testCity";
        city.setCityName(cityName);

        assertThat(city.getId()).isNull();
        cityRepository.save(city);
        assertThat(city.getId()).isNotNull();
    }

    @Test
    public void shouldFindCityByName(){
        City city = new City();
        String cityName = "testCity";
        city.setCityName(cityName);
        city.setWeatherObservationStation("testObservationStation");

        cityRepository.save(city);

        var result = cityRepository.findByCityName(cityName);
        assertThat(result.getCityName()).isEqualTo(cityName);
        assertThat(result.getId()).isNotNull();

    }

    @Test
    public void shouldAddVehicleToDatabase(){
        Vehicle vehicle = new Vehicle();
        String vehicleType = "testVehicle";
        vehicle.setVehicleType(vehicleType);

        assertThat(vehicle.getId()).isNull();
        vehicleRepository.save(vehicle);
        assertThat(vehicle.getId()).isNotNull();
    }
    @Test
    public void shouldFindVehicleByName(){
        Vehicle vehicle = new Vehicle();
        String vehicleType = "testVehicle";
        vehicle.setVehicleType(vehicleType);

        vehicleRepository.save(vehicle);

        var result = vehicleRepository.findByVehicleType(vehicleType);
        assertThat(result.getVehicleType()).isEqualTo(vehicleType);
    }

    @Test
    public void shouldAddRegionalBaseFeeToDatabase(){
        City city = new City();
        String cityName = "testCity";
        city.setCityName(cityName);
        cityRepository.save(city);

        Vehicle vehicle = new Vehicle();
        String vehicleType = "testVehicle";
        vehicle.setVehicleType(vehicleType);
        vehicleRepository.save(vehicle);

        RegionalBaseFee rbf = new RegionalBaseFee();
        rbf.setCity(city);
        rbf.setVehicle(vehicle);
        rbf.setRegionalBaseFee(7.7);

        assertThat(rbf.getId()).isNull();
        rbfRepository.save(rbf);
        assertThat(rbf.getId()).isNotNull();
    }

    @Test
    public void shouldFindRegionalBaseFeeByCityIdAndVehicleId(){
        City city = new City();
        String cityName = "testCity";
        city.setCityName(cityName);
        cityRepository.save(city);

        Vehicle vehicle = new Vehicle();
        String vehicleType = "testVehicle";
        vehicle.setVehicleType(vehicleType);
        vehicleRepository.save(vehicle);

        RegionalBaseFee rbf = new RegionalBaseFee();
        rbf.setCity(city);
        rbf.setVehicle(vehicle);
        rbf.setRegionalBaseFee(7.7);
        rbfRepository.save(rbf);

        var result = rbfRepository.GetRegionalBaseFeeByIds(city.getId(), vehicle.getId());

        assertThat(result.getRegionalBaseFee()).isEqualTo(7.7);
    }

    @Test
    public void shouldAddWindSpeedExtraFeeToDatabase(){

        Vehicle vehicle = new Vehicle();
        String vehicleType = "testVehicle";
        vehicle.setVehicleType(vehicleType);
        vehicleRepository.save(vehicle);

        WindSpeedExtraFee wsef = new WindSpeedExtraFee();
        wsef.setVehicle(vehicle);
        wsef.setWindSpeedExtraFee(7.7);

        assertThat(wsef.getId()).isNull();
        wsefRepository.save(wsef);
        assertThat(wsef.getId()).isNotNull();
    }
    @Test
    public void shouldAddAirSpeedExtraFeeToDatabase(){

        Vehicle vehicle = new Vehicle();
        String vehicleType = "testVehicle";
        vehicle.setVehicleType(vehicleType);
        vehicleRepository.save(vehicle);

        AirTemperatureExtraFee atef = new AirTemperatureExtraFee();
        atef.setVehicle(vehicle);
        atef.setAirTemperatureExtraFee(8.7);

        assertThat(atef.getId()).isNull();
        atefRepository.save(atef);
        assertThat(atef.getId()).isNotNull();
    }

    @Test
    public void shouldAddWeatherPhenomenonExtraFeeToDatabase(){

        Vehicle vehicle = new Vehicle();
        String vehicleType = "testVehicle";
        vehicle.setVehicleType(vehicleType);
        vehicleRepository.save(vehicle);

        WeatherPhenomenonExtraFee wpef = new WeatherPhenomenonExtraFee();
        wpef.setVehicle(vehicle);
        wpef.setWeatherPhenomenon("light snow");

        assertThat(wpef.getId()).isNull();
        wpefRepository.save(wpef);
        assertThat(wpef.getId()).isNotNull();
    }

    @Test
    public void shouldFindWeatherPhenomenonExtraFeeByPartialNameFromDatabase(){

        Vehicle vehicle = new Vehicle();
        String vehicleType = "testVehicle";
        vehicle.setVehicleType(vehicleType);
        vehicleRepository.save(vehicle);

        WeatherPhenomenonExtraFee wpef = new WeatherPhenomenonExtraFee();
        wpef.setVehicle(vehicle);
        wpef.setWeatherPhenomenon("light snow");
        wpefRepository.save(wpef);

        var savedWPEF = wpefRepository.findWPEFRateByPhenomenonAndVehicleId("snow",
                vehicle.getId());

        assertThat(savedWPEF).isEqualTo(wpef);
    }



}
