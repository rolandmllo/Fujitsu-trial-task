package app.services;

import app.Dao.AirTemperatureExtraFeeRepository;
import app.model.AirTemperatureExtraFee;
import app.model.PriceModel;
import org.springframework.stereotype.Service;

/**
 * The type Air temperature extra fee calculator.
 */
@Service
public class AirTemperatureExtraFeeCalculator extends AbstractFeeCalculator {
    private final AirTemperatureExtraFeeRepository airTemperatureExtraFeeRepository;

    /**
     * Instantiates a new Air temperature extra fee calculator.
     *
     * @param airTemperatureExtraFeeRepository the air temperature extra fee repository
     */
    public AirTemperatureExtraFeeCalculator(AirTemperatureExtraFeeRepository airTemperatureExtraFeeRepository) {
        this.airTemperatureExtraFeeRepository = airTemperatureExtraFeeRepository;
    }

    @Override
    public PriceModel applyFeeRate(PriceModel priceModel){

        Double airTemperature = priceModel.getWeather().getAirTemperature();
        Long vehicleId = priceModel.getVehicle().getId();

        AirTemperatureExtraFee airTemperatureExtraFee = airTemperatureExtraFeeRepository
                .findATEFRateByTempAndVehicleId(airTemperature, vehicleId);

        if (airTemperatureExtraFee == null){

            airTemperatureExtraFee = new AirTemperatureExtraFee();
            airTemperatureExtraFee.setAirTemperatureExtraFee(0.0);
        }

        if (airTemperatureExtraFee.getForbidden()){
            throw new IllegalArgumentException("“Usage of\n" +
                    "selected vehicle type is forbidden”");
        }

        priceModel.setAirTemperatureExtraFee(airTemperatureExtraFee);

        return priceModel;
    }
}
