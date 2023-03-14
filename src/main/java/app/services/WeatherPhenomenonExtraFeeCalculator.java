package app.services;

import app.Dao.WeatherPhenomenonExtraFeeRepository;
import app.model.PriceModel;
import app.model.WeatherPhenomenonExtraFee;
import org.springframework.stereotype.Service;

/**
 * The type Weather phenomenon extra fee calculator.
 */
@Service
public class WeatherPhenomenonExtraFeeCalculator extends AbstractFeeCalculator{
    private final WeatherPhenomenonExtraFeeRepository weatherPhenomenonExtraFeeRepository;

    /**
     * Instantiates a new Weather phenomenon extra fee calculator.
     *
     * @param weatherPhenomenonExtraFeeRepository the weather phenomenon extra fee repository
     */
    public WeatherPhenomenonExtraFeeCalculator(WeatherPhenomenonExtraFeeRepository weatherPhenomenonExtraFeeRepository) {
        this.weatherPhenomenonExtraFeeRepository = weatherPhenomenonExtraFeeRepository;
    }

    @Override
    public PriceModel applyFeeRate(PriceModel priceModel) {

        String phenomenon = priceModel
                .getWeather().getPhenomenon();
        System.out.println(phenomenon);

        Long vehicleId = priceModel.getVehicle().getId();

        WeatherPhenomenonExtraFee weatherPhenomenonExtraFee = weatherPhenomenonExtraFeeRepository
                .findWPEFRateByPhenomenonAndVehicleId(phenomenon, vehicleId);

        if (weatherPhenomenonExtraFee == null){
            weatherPhenomenonExtraFee = new WeatherPhenomenonExtraFee();
            weatherPhenomenonExtraFee.setWeatherPhenomenonExtraFee(0.0);
        }

        if (weatherPhenomenonExtraFee.getForbidden()){
            throw new IllegalArgumentException("Usage of selected vehicle type is forbidden");
        }

        priceModel.setWeatherPhenomenonExtraFee(weatherPhenomenonExtraFee);

        return priceModel;
    }
}
