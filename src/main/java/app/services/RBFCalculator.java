package app.services;

import app.Dao.RegionalBaseFeeRepository;
import app.model.City;
import app.model.RegionalBaseFee;
import app.model.Vehicle;
import app.model.PriceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RBFCalculator extends AbstractFeeCalculator{
    @Autowired
    private RegionalBaseFeeRepository rbfRepository;

    @Override
    public PriceModel setFeeRate(PriceModel priceModel){
        City city = priceModel.getCity();
        Vehicle vehicle = priceModel.getVehicle();
        RegionalBaseFee regionalBaseFee = GetRBF(city, vehicle);

        priceModel.setRegionalBaseFee(regionalBaseFee);

        return priceModel;
    }

    private RegionalBaseFee GetRBF(City city, Vehicle vehicle){
        return rbfRepository.GetRegionalBaseFeeByIds(city.getId(), vehicle.getId());
    }

}
