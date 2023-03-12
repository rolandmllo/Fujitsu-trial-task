package app.services;

import app.Dao.CityRepository;
import app.Dao.VehicleRepository;
import app.Dao.WeatherRepository;
import app.model.City;
import app.model.PriceModel;
import app.model.Vehicle;
import app.model.Weather;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * The type Price model populator.
 * Populate price model with city, vehicle and waether data before fees calculation.
 */
@Service
public class PriceModelPopulator {

    private WeatherRepository weatherRepository;

    private final CityRepository cityRepository;

    private final VehicleRepository vehicleRepository;

    private final DeliveryFeeCalculator deliveryFeeCalculator;

    private PriceModel priceModel;

    /**
     * Instantiates a new Price model populator.
     *
     * @param weatherRepository     the weather repository
     * @param cityRepository        the city repository
     * @param vehicleRepository     the vehicle repository
     * @param deliveryFeeCalculator the delivery fee calculator
     */
    public PriceModelPopulator(WeatherRepository weatherRepository, CityRepository cityRepository, VehicleRepository vehicleRepository, DeliveryFeeCalculator deliveryFeeCalculator) {
        this.weatherRepository = weatherRepository;
        this.cityRepository = cityRepository;
        this.vehicleRepository = vehicleRepository;
        this.deliveryFeeCalculator = deliveryFeeCalculator;
    }

    /**
     * Populate price model.
     *
     * @param cityInput       the city from input as string
     * @param vehicleInput    the vehicle from input as string
     * @param weatherDateTime the weather date time from input as LocalDateTime
     * @return the price model
     */
    public PriceModel populate(String cityInput, String vehicleInput, LocalDateTime weatherDateTime) {
        priceModel = new PriceModel();

        setInputs(cityInput, vehicleInput, weatherDateTime);

        deliveryFeeCalculator.calculateFees(priceModel);
        return priceModel;

    }

    private void setInputs(String cityInput, String vehicleInput, LocalDateTime weatherDateTime){

        City city = getCity(cityInput.trim());
        Vehicle vehicle = getVehicle(vehicleInput.trim());

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
    private Vehicle getVehicle(String vehicleType) {
        Vehicle vehicle = vehicleRepository.findByVehicleType(vehicleType);

        if (vehicle != null) return vehicle;

        throw new IllegalArgumentException("Invalid vehicle type: " + vehicleType);
    }

    private City getCity(String cityName){
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
