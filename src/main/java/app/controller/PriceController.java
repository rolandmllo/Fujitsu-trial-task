package app.controller;


import app.model.PriceModel;
import app.services.FeesCalculator;
import app.services.PriceModelPopulator;
import app.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import app.services.WeatherService;

@RestController
public class PriceController {

    @Autowired
    private PriceModelPopulator priceModelPopulator;
    @Autowired
    private FeesCalculator feesCalculator;

    @GetMapping("api/price/{city}&{vehicle}")
    public Double getItem(@PathVariable String city, @PathVariable String vehicle) {

        PriceModel priceModel = priceModelPopulator.Populate(city, vehicle);

        return priceModel.getTotalPrice();
    }

    @GetMapping("api/all")
    public String getAll() {
        return "";
    }



}
