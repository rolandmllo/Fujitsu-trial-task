package app.services;

import app.Dao.WeatherPhenomenonExtraFeeRepository;
import app.model.PriceModel;
import app.model.WeatherPhenomenonExtraFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WindSpeedExtraFeeCalculator extends AbstractFeeCalculator{
    @Autowired
    private WeatherPhenomenonExtraFeeRepository weatherPhenomenonExtraFeeRepository;
    @Override
    public PriceModel setFeeRate(PriceModel priceModel) {

        String phenomenon = priceModel.getWeather().getPhenomenon();
        Long vehicleId = priceModel.getVehicle().getId();

        WeatherPhenomenonExtraFee weatherPhenomenonExtraFee = weatherPhenomenonExtraFeeRepository
                .findWPEFRateByPhenomenonAndVehicleId(phenomenon, vehicleId);

        if (weatherPhenomenonExtraFee == null){
            weatherPhenomenonExtraFee = new WeatherPhenomenonExtraFee();
            weatherPhenomenonExtraFee.setWeatherPhenomenonExtraFee(0.0);
        }

        priceModel.setWeatherPhenomenonExtraFee(weatherPhenomenonExtraFee);

        return priceModel;
    }
}
