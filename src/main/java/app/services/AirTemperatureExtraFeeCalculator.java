package app.services;

import app.Dao.AirTemperatureExtraFeeRepository;
import app.model.AirTemperatureExtraFee;
import app.model.PriceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirTemperatureExtraFeeCalculator extends AbstractFeeCalculator {
    @Autowired
    private AirTemperatureExtraFeeRepository airTemperatureExtraFeeRepository;

    @Override
    public PriceModel setFeeRate(PriceModel priceModel){

        Double windTemperature = priceModel.getWeather().getWindSpeed();
        Long vehicleId = priceModel.getVehicle().getId();

        AirTemperatureExtraFee airTemperatureExtraFee = airTemperatureExtraFeeRepository
                .findATEFRateByTempAndVehicleId(windTemperature, vehicleId);

        if (airTemperatureExtraFee == null){

            airTemperatureExtraFee = new AirTemperatureExtraFee();
            airTemperatureExtraFee.setAirTemperatureExtraFee(0.0);
        }

        priceModel.setAirTemperatureExtraFee(airTemperatureExtraFee);

        return priceModel;
    }
}
