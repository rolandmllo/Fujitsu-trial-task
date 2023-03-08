package app.services;

import app.model.PriceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeesCalculator {
    @Autowired
    private RBFCalculator rbfCalculator;
    public PriceModel calculateFees(PriceModel priceModel){
        rbfCalculator.CalculateRBF(priceModel);


        System.out.println(priceModel);

        calculatePriceModelTotal(priceModel);
        return priceModel;
    }

    private void calculatePriceModelTotal(PriceModel priceModel){
        Double total = 0.0;

        total = priceModel.getRegionalBaseFee().getRegionalBaseFee();

        priceModel.setTotalPrice(total);
    }

}
