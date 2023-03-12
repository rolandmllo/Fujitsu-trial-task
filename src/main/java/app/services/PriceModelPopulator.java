package app.services;

import app.Dao.CityRepository;
import app.Dao.VehicleRepository;
import app.Dao.WeatherRepository;
import app.model.City;
import app.model.PriceModel;
import app.model.Vehicle;
import app.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PriceModelPopulator {

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DeliveryFeeCalculator deliveryFeeCalculator;

    private PriceModel priceModel;

    public PriceModel Populate(String cityInput, String vehicleInput, LocalDateTime weatherDateTime) {
        priceModel = new PriceModel();

        setInputs(cityInput, vehicleInput, weatherDateTime);

        deliveryFeeCalculator.calculateFees(priceModel);
        return priceModel;

    }

    public void setInputs(String cityInput, String vehicleInput, LocalDateTime weatherDateTime){

        City city = GetCity(cityInput.trim());
        Vehicle vehicle = GetVehicle(vehicleInput.trim());

        priceModel.setCity(city);
        priceModel.setVehicle(vehicle);

        setWeatherData(weatherDateTime);

    }

    private void setWeatherData(LocalDateTime weatherDateTime){
        if (weatherDateTime != null) {
            addSpecificDateTimeWeatherToPriceModel(weatherDateTime);
        }else {
            addLatestWeatherToPriceModel(priceModel);
        }

    }
    private Vehicle GetVehicle(String vehicleType) {
        Vehicle vehicle = vehicleRepository.findByVehicleType(vehicleType);

        if (vehicle != null) return vehicle;

        throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
    }

    private City GetCity(String cityName){
        City city = cityRepository.findByCityName(cityName);
        if(city != null) return city;

        throw new IllegalArgumentException("Invalid city: " + cityName);
    }

    private void addLatestWeatherToPriceModel(PriceModel priceModel) {
        Weather weather = weatherRepository
                .findFirstByObservationStationNameIsOrderByTimestampDesc
                        (priceModel.getCity().getWeatherObservationStation());
        priceModel.setWeather(weather);
    }

    private void addSpecificDateTimeWeatherToPriceModel(LocalDateTime dateTime){
        System.out.println(dateTime);

        Weather weather = weatherRepository
                .findByObservationStationNameAndTimestamp(
                        priceModel.getCity().getWeatherObservationStation(),
                        dateTime);

        if (weather == null){
            throw new IllegalArgumentException("No weather data found for city: " + priceModel.getCity() +
                    " at dateTime: " + dateTime);
        }
        priceModel.setWeather(weather);
    }

}
