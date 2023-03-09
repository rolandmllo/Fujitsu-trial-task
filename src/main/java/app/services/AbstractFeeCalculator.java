package app.services;

import app.Dao.WeatherRepository;
import app.model.PriceModel;
import app.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractFeeCalculator {
    protected abstract PriceModel calculateFee(PriceModel priceModel);

}
