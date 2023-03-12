package app.services;

import app.Dao.WindSpeedExtraFeeRepository;
import app.model.PriceModel;
import app.model.WindSpeedExtraFee;
import org.springframework.stereotype.Service;

@Service
public class WindSpeedExtraFeeCalculator extends AbstractFeeCalculator{
    private final WindSpeedExtraFeeRepository windSpeedExtraFeeRepository;

    public WindSpeedExtraFeeCalculator(WindSpeedExtraFeeRepository windSpeedExtraFeeRepository) {
        this.windSpeedExtraFeeRepository = windSpeedExtraFeeRepository;
    }

    @Override
    public PriceModel applyFeeRate(PriceModel priceModel) {

        Double windSpeed = priceModel.getWeather().getWindSpeed();
        Long vehicleId = priceModel.getVehicle().getId();

        WindSpeedExtraFee windSpeedExtraFee = windSpeedExtraFeeRepository
                .findWSEFRateByTempAndVehicleId(windSpeed, vehicleId);

        if (windSpeedExtraFee == null){
            windSpeedExtraFee = new WindSpeedExtraFee();
            windSpeedExtraFee.setWindSpeedExtraFee(0.0);
        }

        if (windSpeedExtraFee.getForbidden()){
            throw new IllegalArgumentException("“Usage of\n" +
                    "selected vehicle type is forbidden”");
        }


        priceModel.setWindSpeedExtraFee(windSpeedExtraFee);

        return priceModel;
    }
}
