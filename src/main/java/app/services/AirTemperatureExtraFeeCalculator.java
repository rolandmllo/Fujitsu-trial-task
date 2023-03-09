package app.services;

import app.Dao.AirTemperatureExtraFeeRepository;
import app.model.AirTemperatureExtraFee;
import app.model.PriceModel;
import app.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirTemperatureExtraFeeCalculator extends AbstractFeeCalculator {
    @Autowired
    private AirTemperatureExtraFeeRepository airTemperatureExtraFeeRepository;
    public PriceModel calculateFee(PriceModel priceModel){
        List<AirTemperatureExtraFee> atefList = airTemperatureExtraFeeRepository.findAll();
        System.out.println(atefList);

        var airTemp = priceModel.getWeather().getAirTemperature();
        var vehicleType = priceModel.getVehicle().getId();

        if (airTemp != null && vehicleType != null){
            var atef = airTemperatureExtraFeeRepository.findATEFRateByTemp(airTemp, vehicleType);
            priceModel.setAirTemperatureExtraFee(atef);

            System.out.println("current" + airTemp + " - " +priceModel.getWeather());
            System.out.println(atef);

        }



        return priceModel;
    }

}
