package app.services;

import app.Dao.WindSpeedExtraFeeRepository;
import app.model.PriceModel;
import app.model.WeatherPhenomenonExtraFee;
import app.model.WindSpeedExtraFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WindSpeedExtraFeeCalculator extends AbstractFeeCalculator{
    @Autowired
    private WindSpeedExtraFeeRepository windSpeedExtraFeeRepository;
    @Override
    public PriceModel setFeeRate(PriceModel priceModel) {

        Double windSpeed = priceModel.getWeather().getWindSpeed();
        Long vehicleId = priceModel.getVehicle().getId();

        WindSpeedExtraFee windSpeedExtraFee = windSpeedExtraFeeRepository
                .findWSEFRateByTempAndVehicleId(windSpeed, vehicleId);

        if (windSpeedExtraFee == null){
            windSpeedExtraFee = new WindSpeedExtraFee();
            windSpeedExtraFee.setWindSpeedExtraFee(0.0);
        }

        priceModel.setWindSpeedExtraFee(windSpeedExtraFee);

        return priceModel;
    }
}
