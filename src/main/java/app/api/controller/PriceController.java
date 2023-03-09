package app.api.controller;

import app.Dao.CityRepository;
import app.Dao.WeatherRepository;
import app.model.City;
import app.model.PriceModel;
import app.model.Weather;
import app.services.FeesCalculator;
import app.services.PriceModelPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PriceController {

    @Autowired
    private PriceModelPopulator priceModelPopulator;
    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("api/price/{city}&{vehicle}")
    public Double getItem(@PathVariable String city, @PathVariable String vehicle) {

        PriceModel priceModel = priceModelPopulator.Populate(city, vehicle);

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
