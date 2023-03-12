package app.services;

import app.model.PriceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryFeeCalculator {
    @Autowired
    protected RegionalBaseFeeCalculator regionalBaseFeeCalculator;
    @Autowired
    protected AirTemperatureExtraFeeCalculator airTemperatureExtraFeeCalculator;
    @Autowired
    protected WindSpeedExtraFeeCalculator windSpeedExtraFeeCalculator;
    @Autowired
    protected WeatherPhenomenonExtraFeeCalculator weatherPhenomenonExtraFeeCalculator;

    public PriceModel calculateFees(PriceModel priceModel){

        regionalBaseFeeCalculator.applyFeeRate(priceModel);
        airTemperatureExtraFeeCalculator.applyFeeRate(priceModel);
        windSpeedExtraFeeCalculator.applyFeeRate(priceModel);
        weatherPhenomenonExtraFeeCalculator.applyFeeRate(priceModel);


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
