package app.services;

import app.Dao.RegionalBaseFeeRepository;
import app.model.City;
import app.model.RegionalBaseFee;
import app.model.Vehicle;
import app.model.PriceModel;
import org.springframework.stereotype.Service;

@Service
public class RegionalBaseFeeCalculator extends AbstractFeeCalculator{
    private final RegionalBaseFeeRepository rbfRepository;

    public RegionalBaseFeeCalculator(RegionalBaseFeeRepository rbfRepository) {
        this.rbfRepository = rbfRepository;
    }

    @Override
    public PriceModel applyFeeRate(PriceModel priceModel){
        City city = priceModel.getCity();
        Vehicle vehicle = priceModel.getVehicle();
        RegionalBaseFee regionalBaseFee = getRBF(city, vehicle);

        priceModel.setRegionalBaseFee(regionalBaseFee);

        return priceModel;
    }

    private RegionalBaseFee getRBF(City city, Vehicle vehicle){
        return rbfRepository.GetRegionalBaseFeeByIds(city.getId(), vehicle.getId());
    }

}
