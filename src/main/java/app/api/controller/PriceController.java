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

@RestController
public class PriceController {

    private final PriceModelPopulator priceModelPopulator;
    private final WeatherRepository weatherRepository;

    private final CityRepository cityRepository;

    public PriceController(PriceModelPopulator priceModelPopulator, WeatherRepository weatherRepository, CityRepository cityRepository) {
        this.priceModelPopulator = priceModelPopulator;
        this.weatherRepository = weatherRepository;
        this.cityRepository = cityRepository;
    }


    @GetMapping("api/price")
    public Double getDeliveryFee(@RequestParam String city,
                                 @RequestParam String vehicle,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetime) {

        System.out.println(city);
        System.out.println(vehicle);
        System.out.println(datetime);
        System.out.println();
        PriceModel priceModel = priceModelPopulator.populate(city, vehicle, datetime);

        return priceModel.getTotalPrice();
    }




    @GetMapping("api/weather")
    public List<Weather> getWeatherData(){
        var weather =  weatherRepository.findAll();
        System.out.println(weather);
        return weather;
    }

    @GetMapping("api/cities")
    public List<City> getCityData(){
        var city =  cityRepository.findAll();
        System.out.println(city);
        return city;
    }
}
