package app.services;

import app.model.PriceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceModelPopulator {

    @Autowired
    private SearchService searchService;
    @Autowired
    private FeesCalculator feesCalculator;

    private PriceModel priceModel;

    public PriceModel Populate(String cityInput, String vehicleInput){
        priceModel = new PriceModel();

        searchService.ReadInputs(cityInput, vehicleInput, priceModel);
        feesCalculator.calculateFees(priceModel);




        return priceModel;
    }
}
