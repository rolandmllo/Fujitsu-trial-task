package app.api.controller;

import app.Dao.CityRepository;
import app.Dao.WeatherRepository;
import app.model.City;
import app.model.PriceModel;
import app.model.Weather;
import app.services.PriceModelPopulator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.List;


/**
 * The Price controller.
 */
@RestController
public class PriceController {

    private final PriceModelPopulator priceModelPopulator;
    private final WeatherRepository weatherRepository;

    private final CityRepository cityRepository;

    /**
     * Instantiates a new Price controller.
     *
     * @param priceModelPopulator the price model populator
     * @param weatherRepository   the weather repository
     * @param cityRepository      the city repository
     */
    public PriceController(PriceModelPopulator priceModelPopulator, WeatherRepository weatherRepository, CityRepository cityRepository) {
        this.priceModelPopulator = priceModelPopulator;
        this.weatherRepository = weatherRepository;
        this.cityRepository = cityRepository;
    }


    /**
     * Gets delivery fee.
     *
     * @param city     the city name
     * @param vehicle  the vehicle type
     * @param datetime the datetime - optional datetime for weather data on specific date.
     * @return the delivery fee
     */
    @GetMapping("api/price")
    public Double getDeliveryFee(@RequestParam String city,
                                 @RequestParam String vehicle,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetime) {

        PriceModel priceModel = priceModelPopulator.populate(city, vehicle, datetime);

        return priceModel.getTotalPrice();
    }

}
