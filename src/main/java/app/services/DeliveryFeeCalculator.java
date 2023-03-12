package app.services;

import app.model.PriceModel;
import org.springframework.stereotype.Service;

@Service
public class DeliveryFeeCalculator {
    protected final RegionalBaseFeeCalculator regionalBaseFeeCalculator;
    protected final AirTemperatureExtraFeeCalculator airTemperatureExtraFeeCalculator;
    protected final WindSpeedExtraFeeCalculator windSpeedExtraFeeCalculator;
    protected final WeatherPhenomenonExtraFeeCalculator weatherPhenomenonExtraFeeCalculator;

    public DeliveryFeeCalculator(RegionalBaseFeeCalculator regionalBaseFeeCalculator, AirTemperatureExtraFeeCalculator airTemperatureExtraFeeCalculator, WindSpeedExtraFeeCalculator windSpeedExtraFeeCalculator, WeatherPhenomenonExtraFeeCalculator weatherPhenomenonExtraFeeCalculator) {
        this.regionalBaseFeeCalculator = regionalBaseFeeCalculator;
        this.airTemperatureExtraFeeCalculator = airTemperatureExtraFeeCalculator;
        this.windSpeedExtraFeeCalculator = windSpeedExtraFeeCalculator;
        this.weatherPhenomenonExtraFeeCalculator = weatherPhenomenonExtraFeeCalculator;
    }

    public void calculateFees(PriceModel priceModel){

        regionalBaseFeeCalculator.applyFeeRate(priceModel);
        airTemperatureExtraFeeCalculator.applyFeeRate(priceModel);
        windSpeedExtraFeeCalculator.applyFeeRate(priceModel);
        weatherPhenomenonExtraFeeCalculator.applyFeeRate(priceModel);

        calculatePriceModelTotal(priceModel);
    }

    private void calculatePriceModelTotal(PriceModel priceModel){
        double total;

        total = priceModel.getRegionalBaseFee().getRegionalBaseFee() +
        priceModel.getAirTemperatureExtraFee().getAirTemperatureExtraFee() +
        priceModel.getWindSpeedExtraFee().getWindSpeedExtraFee() +
        priceModel.getWeatherPhenomenonExtraFee().getWeatherPhenomenonExtraFee();

        priceModel.setTotalPrice(total);
    }

}
