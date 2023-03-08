import app.Dao.CityRepository;
import app.Dao.RegionalBaseFeeRepository;
import app.Dao.VehicleRepository;
import app.Dao.WeatherRepository;
import app.model.City;
import app.model.RegionalBaseFee;
import app.model.Vehicle;
import app.model.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ContextConfiguration(classes = app.Application.class)
public class RepositoryTests {
    @Autowired
    WeatherRepository weatherRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private RegionalBaseFeeRepository rbfRepository;

    @Test
    public void shouldAddWeatherToDatabase(){
        Weather weather = new Weather();
        weather.setName("testWeather");

        assertThat(weather.getId()).isNull();
        weatherRepository.save(weather);
        assertThat(weather.getId()).isNotNull();
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

}
