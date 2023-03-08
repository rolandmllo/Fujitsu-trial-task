package app.services;

import app.model.PriceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceModelPopulator {

    @Autowired
    SearchService searchService;

    private PriceModel priceModel;

    public PriceModel Populate(String cityInput, String vehicleInput){
        priceModel = new PriceModel();

        searchService.ReadInputs(cityInput, vehicleInput, priceModel);




        return priceModel;
    }
}
