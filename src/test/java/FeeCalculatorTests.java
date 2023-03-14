import app.Dao.CityRepository;
import app.Dao.VehicleRepository;
import app.Dao.WeatherRepository;
import app.model.City;
import app.model.PriceModel;
import app.model.Vehicle;
import app.model.Weather;
import app.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = app.Application.class)
@Transactional
public class FeeCalculatorTests {


    @Autowired
    DeliveryFeeCalculator deliveryFeeCalculator;
    @Autowired
    RegionalBaseFeeCalculator rbfCalculator;
    @Autowired
    AirTemperatureExtraFeeCalculator atefCalculator;
    @Autowired
    WindSpeedExtraFeeCalculator wsefCalculator;
    @Autowired
    WeatherPhenomenonExtraFeeCalculator wpefCalculator;
    @Autowired
    WeatherRepository weatherRepository;
    @Autowired
    PriceModelPopulator priceModelPopulator;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    CityRepository cityRepository;

    LocalDateTime time;

    PriceModel priceModel;
    @BeforeEach
    void setup() {
        priceModel = null;
        time = LocalDateTime.now();
    }

    @Test
    public void testDeliveryFeeCalculatorGetTotalPrice(){
        City city = cityRepository.findByCityName("Tartu");
        Vehicle vehicleTypeBike = vehicleRepository.findByVehicleType("Bike");
        Weather weather = getNewWeather("Tartu-Tõravere",
                -2.1, 4.7, "Light snow shower");
        priceModel = getPriceModel(weather, vehicleTypeBike);
        priceModel.setCity(city);

        deliveryFeeCalculator.calculateFees(priceModel);

        assertThat(priceModel.getTotalPrice()).isEqualTo(4.0);
    }

    @Test
    public void testPopulatePricemodelShouldGetWeatherByCityName(){
        String city = "Pärnu";
        String vehicle = "Bike";

        getNewWeather(city, null, null, null);

        priceModel = priceModelPopulator.populate(city, vehicle, null);

        assertThat(priceModel.getWeather()).isNotNull();
        assertThat(priceModel.getCity().getCityName()).isEqualTo(city);
    }

    @Test
    public void testPopulatePricemodelShouldGetVehicleTypeByInput(){
        String city = "Pärnu";
        String vehicleType = "Scooter";

        getNewWeather(city,10.0 , null, null);


        priceModel = priceModelPopulator.populate(city, vehicleType, null);

        assertThat(priceModel.getVehicle()).isNotNull();
        assertThat(priceModel.getVehicle().getVehicleType()).isEqualTo(vehicleType);
    }
    @Test
    public void testRBFCalculator(){
        City city1 = cityRepository.findByCityName("Tallinn");

        Vehicle vehicleTypeCar = vehicleRepository.findByVehicleType("car");
        Vehicle vehicleTypeScooter = vehicleRepository.findByVehicleType("Scooter");
        Vehicle vehicleTypeBike = vehicleRepository.findByVehicleType("Bike");

        PriceModel priceModel1 = getPriceModel(null, vehicleTypeCar, city1);
        PriceModel priceModel2 = getPriceModel(null, vehicleTypeScooter, city1);
        PriceModel priceModel3 = getPriceModel(null, vehicleTypeBike, city1);


        var rbfTallinnCar= rbfCalculator.applyFeeRate(priceModel1);
        var rbfTallinnScooter= rbfCalculator.applyFeeRate(priceModel2);
        var rbfTallinnBike = rbfCalculator.applyFeeRate(priceModel3);

        assertThat(rbfTallinnCar.getRegionalBaseFee().getRegionalBaseFee()).isEqualTo(4);
        assertThat(rbfTallinnScooter.getRegionalBaseFee().getRegionalBaseFee()).isEqualTo(3.5);
        assertThat(rbfTallinnBike.getRegionalBaseFee().getRegionalBaseFee()).isEqualTo(3.0);

    }

    @Test
    public void testATEFCalculatorWithFreezingWeather(){
        Weather weatherFreezing = getNewWeather("Tallinn", -10.1,
                null, null);

        var vehicleCar = vehicleRepository.findByVehicleType("car");
        var vehicleScooter = vehicleRepository.findByVehicleType("scooter");
        var vehicleBike = vehicleRepository.findByVehicleType("bike");

        PriceModel priceModel1 = getPriceModel(weatherFreezing, vehicleCar);
        PriceModel priceModel2 = getPriceModel(weatherFreezing, vehicleScooter);
        PriceModel priceModel3 = getPriceModel(weatherFreezing, vehicleBike);

        var resultCar = atefCalculator.applyFeeRate(priceModel1);
        var resultScooter = atefCalculator.applyFeeRate(priceModel2);
        var resultBike = atefCalculator.applyFeeRate(priceModel3);

        assertThat(resultCar.getAirTemperatureExtraFee().getAirTemperatureExtraFee()).isEqualTo(0);
        assertThat(resultScooter.getAirTemperatureExtraFee().getAirTemperatureExtraFee()).isEqualTo(1);
        assertThat(resultBike.getAirTemperatureExtraFee().getAirTemperatureExtraFee()).isEqualTo(1);
    }
    @Test
    public void testATEFCalculatorWithColdWeather(){
        Weather weatherCold = getNewWeather("Tallinn", -1.1,
                null, null);

        var vehicleCar = vehicleRepository.findByVehicleType("car");
        var vehicleScooter = vehicleRepository.findByVehicleType("scooter");
        var vehicleBike = vehicleRepository.findByVehicleType("bike");

        PriceModel priceModel1 = getPriceModel(weatherCold, vehicleCar);
        PriceModel priceModel2 = getPriceModel(weatherCold, vehicleScooter);
        PriceModel priceModel3 = getPriceModel(weatherCold, vehicleBike);

        var resultCar = atefCalculator.applyFeeRate(priceModel1);
        var resultScooter = atefCalculator.applyFeeRate(priceModel2);
        var resultBike = atefCalculator.applyFeeRate(priceModel3);

        assertThat(resultCar.getAirTemperatureExtraFee().getAirTemperatureExtraFee()).isEqualTo(0);
        assertThat(resultScooter.getAirTemperatureExtraFee().getAirTemperatureExtraFee()).isEqualTo(0.5);
        assertThat(resultBike.getAirTemperatureExtraFee().getAirTemperatureExtraFee()).isEqualTo(0.5);
    }
    @Test
    public void testATEFCalculatorWithwarmWeather(){
        Weather weatherWarm = getNewWeather("Tallinn", 0.1,
                null, null);

        var vehicleCar = vehicleRepository.findByVehicleType("car");
        var vehicleScooter = vehicleRepository.findByVehicleType("scooter");

        PriceModel priceModel1 = getPriceModel(weatherWarm, vehicleCar);
        PriceModel priceModel2 = getPriceModel(weatherWarm, vehicleScooter);

        var resultCar = atefCalculator.applyFeeRate(priceModel1);
        var resultScooter = atefCalculator.applyFeeRate(priceModel2);

        assertThat(resultCar.getAirTemperatureExtraFee().getAirTemperatureExtraFee()).isEqualTo(0);
        assertThat(resultScooter.getAirTemperatureExtraFee().getAirTemperatureExtraFee()).isEqualTo(0.0);
    }

    @Test
    public void testWSEFCalculatorBikeShouldworkWithMediumWind(){
        Weather weather= getNewWeather("Tallinn", null,
                11.0, null);
        var vehicleBike = vehicleRepository.findByVehicleType("Bike");
        PriceModel priceModel = getPriceModel(weather, vehicleBike);

        var result = wsefCalculator.applyFeeRate(priceModel);

        assertThat(result.getWindSpeedExtraFee().getWindSpeedExtraFee()).isEqualTo(0.5);
    }

    @Test
    public void testWSEFCalculatorBikeShouldThrowWithStorm(){
        Weather weather= getNewWeather("Tallinn", null,
                20.1, null);
        var vehicleBike = vehicleRepository.findByVehicleType("Bike");
        PriceModel priceModel = getPriceModel(weather, vehicleBike);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {

                    wsefCalculator.applyFeeRate(priceModel);
                });

        assertThat(exception).hasMessageContaining("Usage of selected vehicle type is forbidden");
    }

    @Test
    public void testWPEFWThrowsWithGlaze(){
        Weather weather= getNewWeather("Tallinn", null,
                null, "Glaze");
        var vehicleBike = vehicleRepository.findByVehicleType("bike");
        PriceModel priceModel = getPriceModel(weather, vehicleBike);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {

            wpefCalculator.applyFeeRate(priceModel);
                });

        assertThat(exception).hasMessageContaining("Usage of selected vehicle type is forbidden");
    }

    @Test
    public void testWPEFWWithSnow(){
        Weather weather= getNewWeather("Tallinn", null,
                null, "Light snow shower");
        var vehicleBike = vehicleRepository.findByVehicleType("bike");
        PriceModel priceModel = getPriceModel(weather, vehicleBike);

        var result = wpefCalculator.applyFeeRate(priceModel);
        System.out.println(result);

        assertThat(result.getWeatherPhenomenonExtraFee().getWeatherPhenomenonExtraFee()).isEqualTo(1.0);
    }

    @Test
    public void testWPEFWWithRain(){
        Weather weather= getNewWeather("Tallinn", null,
                null, "Heavy rain");
        var vehicleScooter = vehicleRepository.findByVehicleType("Scooter");
        PriceModel priceModel = getPriceModel(weather, vehicleScooter);

        var result = wpefCalculator.applyFeeRate(priceModel);
        System.out.println(result);

        assertThat(result.getWeatherPhenomenonExtraFee().getWeatherPhenomenonExtraFee()).isEqualTo(0.5);
    }

    private PriceModel getPriceModel(Weather weather, Vehicle vehicleCar, City city) {
        var pricemodel = getPriceModel(weather, vehicleCar);
        pricemodel.setCity(city);
        return pricemodel;
    }


        private PriceModel getPriceModel(Weather weather, Vehicle vehicleCar) {
        PriceModel priceModel = new PriceModel();
        priceModel.setWeather(weather);
        priceModel.setVehicle(vehicleCar);
        return priceModel;
    }


    private Weather getNewWeather(String city, Double airTemperature, Double windSpeed, String phenomenon) {
        Weather weather = new Weather();
        weather.setTimestamp(time);
        weather.setObservationStationName(city);
        weather.setAirTemperature(airTemperature);
        weather.setWindSpeed(windSpeed);
        weather.setPhenomenon(phenomenon);
        return weatherRepository.save(weather);

    }
}
