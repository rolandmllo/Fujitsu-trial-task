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
    public PriceModel applyFeeRate(PriceModel priceModel){

        Double airTemperature = priceModel.getWeather().getAirTemperature();
        Long vehicleId = priceModel.getVehicle().getId();

        AirTemperatureExtraFee airTemperatureExtraFee = airTemperatureExtraFeeRepository
                .findATEFRateByTempAndVehicleId(airTemperature, vehicleId);

        if (airTemperatureExtraFee.getForbidden()){
            throw new IllegalArgumentException("“Usage of\n" +
                    "selected vehicle type is forbidden”");
        }

        if (airTemperatureExtraFee == null){

            airTemperatureExtraFee = new AirTemperatureExtraFee();
            airTemperatureExtraFee.setAirTemperatureExtraFee(0.0);
        }

        priceModel.setAirTemperatureExtraFee(airTemperatureExtraFee);

        return priceModel;
    }
}
