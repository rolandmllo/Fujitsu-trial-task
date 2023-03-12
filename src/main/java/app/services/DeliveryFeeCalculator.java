package app.services;

import app.model.PriceModel;
import org.springframework.stereotype.Service;

/**
 * Delivery fee calculator is main class for calculating delivery fee.
 */
@Service
public class DeliveryFeeCalculator {

    protected final RegionalBaseFeeCalculator regionalBaseFeeCalculator;

    protected final AirTemperatureExtraFeeCalculator airTemperatureExtraFeeCalculator;

    protected final WindSpeedExtraFeeCalculator windSpeedExtraFeeCalculator;

    protected final WeatherPhenomenonExtraFeeCalculator weatherPhenomenonExtraFeeCalculator;

    /**
     * Instantiates a new Delivery fee calculator.
     *
     * @param regionalBaseFeeCalculator           the regional base fee calculator
     * @param airTemperatureExtraFeeCalculator    the air temperature extra fee calculator
     * @param windSpeedExtraFeeCalculator         the wind speed extra fee calculator
     * @param weatherPhenomenonExtraFeeCalculator the weather phenomenon extra fee calculator
     */
    public DeliveryFeeCalculator(RegionalBaseFeeCalculator regionalBaseFeeCalculator, AirTemperatureExtraFeeCalculator airTemperatureExtraFeeCalculator, WindSpeedExtraFeeCalculator windSpeedExtraFeeCalculator, WeatherPhenomenonExtraFeeCalculator weatherPhenomenonExtraFeeCalculator) {
        this.regionalBaseFeeCalculator = regionalBaseFeeCalculator;
        this.airTemperatureExtraFeeCalculator = airTemperatureExtraFeeCalculator;
        this.windSpeedExtraFeeCalculator = windSpeedExtraFeeCalculator;
        this.weatherPhenomenonExtraFeeCalculator = weatherPhenomenonExtraFeeCalculator;
    }

    /**
     * Calculate fees.
     *
     * @param priceModel the price model with city, vehicle and weather data
     */
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
