package app.services;

import app.model.PriceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeesCalculator{
    @Autowired
    protected RBFCalculator rbfCalculator;
    @Autowired
    protected AirTemperatureExtraFeeCalculator atefCalculator;
    @Autowired
    protected WindSpeedExtraFeeCalculator windSpeedExtraFeeCalculator;
    @Autowired
    protected WeatherPhenomenonExtraFeeCalculator weatherPhenomenonExtraFeeCalculator;

    public PriceModel calculateFees(PriceModel priceModel){

        rbfCalculator.setFeeRate(priceModel);
        atefCalculator.setFeeRate(priceModel);
        windSpeedExtraFeeCalculator.setFeeRate(priceModel);
        weatherPhenomenonExtraFeeCalculator.setFeeRate(priceModel);


        System.out.println(priceModel);

        calculatePriceModelTotal(priceModel);
        return priceModel;
    }

    private void calculatePriceModelTotal(PriceModel priceModel){
        Double total = 0.0;

        total = priceModel.getRegionalBaseFee().getRegionalBaseFee() +
        priceModel.getAirTemperatureExtraFee().getAirTemperatureExtraFee() +
        priceModel.getWindSpeedExtraFee().getWindSpeedExtraFee() +
        priceModel.getWeatherPhenomenonExtraFee().getWeatherPhenomenonExtraFee();

        priceModel.setTotalPrice(total);
    }

}
